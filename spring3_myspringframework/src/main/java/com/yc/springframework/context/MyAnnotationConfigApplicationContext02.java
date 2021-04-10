package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 14:48
 */
public class MyAnnotationConfigApplicationContext02 implements MyApplicationContext {
    private Map<String, Object> beanMap = new HashMap<>();

    public MyAnnotationConfigApplicationContext02(Class<?>... componentClasses) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        register(componentClasses);
    }

    private void register(Class<?>[] componentClasses) throws ClassNotFoundException {
        //1.实现IOC MyPostConstruct MyPreDestroy
        if (componentClasses == null || componentClasses.length <= 0) {
            return;
        } else {
            //循环所有类
            for (Class cls : componentClasses) {
                if (!cls.isAnnotationPresent(MyComponentScan.class)) {
                    continue;
                }
                //通过此类注解 获取包名
                String[] pkgName = {};
                MyComponentScan cs = (MyComponentScan) cls.getDeclaredAnnotation(MyComponentScan.class);
                pkgName = cs.basePackages();
                if (pkgName != null && pkgName.length > 0) {
                    for (String name : pkgName) {
                        //获取此包下所有类名
                        List<String> list = getClassInPackage(name);
                        for (String s : list) {
                            //实例化 传入beanMap
                            Class c = Class.forName(s);
                            //获取此类的注解 判断
                            Annotation[] ats = c.getDeclaredAnnotations();
                            //如果没有注解
                            if (ats == null || ats.length <= 0) {
                                continue;
                            }
                            for (Annotation a : ats) {
                                if (a instanceof MyComponent || a instanceof MyController || a instanceof MyService) {
                                    //获取键名
                                    String className = c.getName().substring(name.length() + 1).replace("H", "h");
                                    String first = className.substring(0, 1);
                                    String firstLower = first.toLowerCase();
                                    className = className.replace(first, firstLower);
                                    //设值
                                    // Object o = c.newInstance();
                                    beanMap.put(className, c);
                                    break;
                                }
                            }
                        }
                    }
                }

            }

        }
        //2.DI

    }

    public static List<String> getClassInPackage(String pkgName) {
        List<String> ret = new ArrayList<String>();
        String rPath = pkgName.replace('.', '/') + "/";
        try {
            for (File classPath : CLASS_PATH_ARRAY) {
                if (!classPath.exists()) {
                    continue;
                }
                if (classPath.isDirectory()) {
                    File dir = new File(classPath, rPath);
                    if (!dir.exists()) {
                        continue;
                    }
                    for (File file : dir.listFiles()) {
                        if (file.isFile()) {
                            String clsName = file.getName();
                            clsName = pkgName + "." + clsName.substring(0, clsName.length() - 6);
                            ret.add(clsName);
                        }
                    }
                } else {
                    FileInputStream fis = new FileInputStream(classPath);
                    JarInputStream jis = new JarInputStream(fis, false);
                    JarEntry e = null;
                    while ((e = jis.getNextJarEntry()) != null) {
                        String eName = e.getName();
                        if (eName.startsWith(rPath) && !eName.endsWith("/")) {
                            ret.add(eName.replace('/', '.').substring(0, eName.length() - 6));
                        }
                        jis.closeEntry();
                    }
                    jis.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private static String[] CLASS_PATH_PROP = {"java.class.path", "java.ext.dirs", "sun.boot.class.path"};

    private static List<File> CLASS_PATH_ARRAY = getClassPath();

    private static List<File> getClassPath() {
        List<File> ret = new ArrayList<File>();
        String delim = ":";
        if (System.getProperty("os.name").indexOf("Windows") != -1) {
            delim = ";";
        }
        for (String pro : CLASS_PATH_PROP) {
            String[] pathes = System.getProperty(pro).split(delim);
            for (String path : pathes) {
                ret.add(new File(path));
            }
        }
        return ret;
    }


    @Override
    public Object getBean(String id) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class c = (Class) beanMap.get(id);
        //获取此类所有方法
        Method[] ms = c.getDeclaredMethods();
        Method construct = null;
        Method destroy = null;
        if (ms != null && ms.length > 0) {
            for (Method m : ms) {
                if (m.isAnnotationPresent(MyPostConstruct.class)) {
                    construct = m;
                }
            }
        }
        Object o = c.newInstance();
        if (construct != null) {
            construct.invoke(o);
        }

        return o;
    }
}
