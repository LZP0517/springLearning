//package com.yc.biz;
//
//import com.yc.StudentConfig;
//import com.yc.dao.StudentDaoJpaImpl;
//import junit.framework.TestCase;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class StudentBizImplTest extends TestCase {
//    private ApplicationContext ac;
//    private StudentDaoJpaImpl dao;
//    private StudentBizImpl biz;
//
//    @Override
//    public void setUp() throws Exception {
//        ac = new AnnotationConfigApplicationContext(StudentConfig.class);
//        //dao = (StudentDaoJpaImpl) ac.getBean("studentDaoJpaImpl");
//        biz = (StudentBizImpl) ac.getBean("studentBizImpl");
//        //biz.setStudentDao(dao);
//
//    }
//
//    public void testAdd() {
//        biz.add("张三");
//    }
//
//    public void testUpdate() {
//        biz.update("张三");
//    }
//}