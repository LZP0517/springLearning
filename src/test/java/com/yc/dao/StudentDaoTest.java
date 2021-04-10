//package com.yc.dao;
//
//import com.yc.StudentConfig;
//import com.yc.biz.StudentBizImpl;
//import junit.framework.TestCase;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * @program: testspring
// * @description:
// * @author: 作者
// * @create: 2021-04-04 14:51
// */
//public class StudentDaoTest extends TestCase {
//
//    private StudentDao dao;
//    private StudentBizImpl biz;
//    private ApplicationContext as; //spring 容器
//
//
//    @Override
//    public void setUp() throws Exception {
//        as = new AnnotationConfigApplicationContext(StudentConfig.class);
//        // 1.能否自动完成 实例化对象操作 -》 IOC 控制 反转 -》由容器实例化对象
//        //dao = new StudentDaoJpaImpl();
//        dao = (StudentDaoJpaImpl) as.getBean("studentDaoJpaImpl");
//        // biz=new StudentBizImpl(dao); //IOC
//        // biz = new StudentBizImpl();
//        biz = (StudentBizImpl) as.getBean("studentBizImpl");
//        //2. 能否自动完成 装配过程 -》DI 依赖注入->有容器装配对象
//        biz.setStudentDao(dao);
//    }
//
//    public void testAdd() {
//        dao.add("张三");
//    }
//
//    public void testUpdate() {
//        dao.update("张三");
//    }
//
//    public void testBizAdd() {
//        biz.add("张三");
//    }
//}
