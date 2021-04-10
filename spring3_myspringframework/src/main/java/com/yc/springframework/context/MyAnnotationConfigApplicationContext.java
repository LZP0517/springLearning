package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 14:48
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {
    private Map<String, Object> beanMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(Class<?>[] componentClasses) throws Exception {
        //1.实现IOC MyPostConstruct MyPreDestroy
        if (componentClasses == null) {
            throw new Exception("没有指定配置类");
        }
        for (Class cl : componentClasses) {
            if (!cl.isAnnotationPresent(MyConfiguration.class)) {
                continue;
            }
            //如果MyComponent没放值
            String[] basePackages = getAppConfigBasePackages(cl);
            //有值
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan mcs = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (mcs.basePackages() != null && mcs.basePackages().length > 0) {
                    basePackages = mcs.basePackages();
                }
            }
            //解决@MyBean的情况
            Object o = cl.newInstance();
            handleAtMyBean(cl, o);
            //处理basePackages包下的所有托管bean
            for (String basePackage : basePackages) {
                scanPackageAndSubPackageClasses(basePackage);
            }
            //处理包下的所有的托管bean
            handleManageBean();
            //循环beanMap中的每个bean 找到它们每个类中的每个有@Autowired @Resource 注解的方法实现di
            handleDi(beanMap);

        }

        //2.DI

    }

    //循环beanMap中的每个bean 找到它们每个类中的每个有@Autowired @Resource 注解的方法实现di
    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        Collection<Object> objectCollection = beanMap.values();
        for (Object obj : objectCollection) {
            Class cls = obj.getClass();
            Method[] ms = cls.getDeclaredMethods();
            for (Method m : ms) {
                if (m.isAnnotationPresent(MyAutowired.class) && m.getName().startsWith("set")) {
                    invokeAutowiredMethod(m, obj);
                } else if (m.isAnnotationPresent(MyResource.class) && m.getName().startsWith("set")) {
                    invokeResourceMethod(m, obj);
                }
            }
            Field[] fs = cls.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(MyAutowired.class)) {

                } else if (field.isAnnotationPresent(MyResource.class)) {

                }
            }
        }
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        //1.取出 MyResource的name值 当成beanId
        MyResource mr = m.getAnnotation(MyResource.class);
        String beanId = mr.name();
        //2.如果没有 则取出 m方法中的参数类型名 改称首字母小写 当成beanid
        if (beanId == null || beanId.equalsIgnoreCase("")) {
            String pname = m.getParameterTypes()[0].getName();
            Class typeClass = Class.forName(pname);
            Set<String> keys = beanMap.keySet();
            for (String key : keys) {
                Object o = beanMap.get(key);
                Class[] interfaces = o.getClass().getInterfaces();
                for (Class c : interfaces) {
                    if (c == typeClass) {
                        m.invoke(obj, o);
                    }
                }
            }
            //beanId = pname.substring(0, 1).toLowerCase() + pname.substring(1);
        } else {
            Object o = beanMap.get(beanId);
            m.invoke(obj, o);
        }

    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1.取出 m的参数类型
        Class typeClass = m.getParameterTypes()[0];
        //2.从beanMap中循环所有object
        Set<String> keys = beanMap.keySet();
        for (String key : keys) {
            Object o = beanMap.get(key);
            //找出参数类型的实例化对象 instanceof
            Class[] interfaces = o.getClass().getInterfaces();
            for (Class c : interfaces) {
                if (c == typeClass) {
                    m.invoke(obj, o);
                    break;
                }
            }

        }
    }

    //处理包下的所有的托管bean
    private void handleManageBean() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class c : ManageBeanClasses) {
            if (c.isAnnotationPresent(MyComponent.class)) {
                //设置键名 值
                saveManageBean(c);
            } else if (c.isAnnotationPresent(MyService.class)) {
                saveManageBean(c);
            } else if (c.isAnnotationPresent(MyRepository.class)) {
                saveManageBean(c);
            } else if (c.isAnnotationPresent(MyController.class)) {
                saveManageBean(c);
            } else {

            }
        }

    }

    private void saveManageBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = c.newInstance();
        handlePostConstruct(o, c);
        String beanId = c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
        beanMap.put(beanId, o);
    }

    //获取包下的所有托管bean
    private void scanPackageAndSubPackageClasses(String basePackage) throws IOException, ClassNotFoundException {
        String packagePath = basePackage.replaceAll("\\.", "/");
        //获取此包的绝对路径
        Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()) {
            URL url = files.nextElement();
            System.out.println("设置扫描路径为:" + url.getFile());
            //查找包下的类 及子目录 处理
            findClassesInPackages(url.getFile(), basePackage);
        }
    }

    private Set<Class> ManageBeanClasses = new HashSet<Class>();

    //查找包下的类 及子目录 处理
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f = new File(file);
        //根据绝对路径获取此文件下的所有子文件
        File[] classFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        //System.out.println(classFiles);
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //如果是目录则 递归获取此目录下 .class文件
                basePackage += "." + cf.getName().substring(cf.getName().lastIndexOf("/") + 1);
                findClassesInPackages(cf.getAbsolutePath(), basePackage);
            } else {
                //加载 cf 作为class文件
                URL[] urls = new URL[]{};
                URLClassLoader ucl = new URLClassLoader(urls);
                // com.yc.bean.Hello.class --> com.yc.bean.Hello
                Class c = ucl.loadClass(basePackage + "." + cf.getName().replace(".class", ""));
                ManageBeanClasses.add(c);
            }
        }


    }

    //解决@MyBean的情况
    private void handleAtMyBean(Class cl, Object o) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cl.getDeclaredMethods();
        //循环方法 判断是否有@MyBean
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyBean.class)) {
                Object obj = m.invoke(o);
                //处理 @PostConstruct问题
                handlePostConstruct(o, o.getClass()); //o:helloworld实例
                beanMap.put(m.getName(), obj);
            }
        }
    }

    //处理 @PostConstruct问题
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyPostConstruct.class)) {
                m.invoke(o);
            }
        }
    }

    //获取当前包路径
    private String[] getAppConfigBasePackages(Class cl) {
        String[] paths = new String[1];
        paths[0] = cl.getPackage().getName();
        return paths;
    }

    @Override
    public Object getBean(String id) {

        return beanMap.get(id);
    }
}
