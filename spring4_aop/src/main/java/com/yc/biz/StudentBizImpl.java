package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-04 14:46
 */
@Service
public class StudentBizImpl implements StudentBiz {
    private StudentDao studentDao;

    public StudentBizImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public StudentBizImpl() {

    }

    //@Inject //javax的依赖注入 多个对象选择指定一个 @Named("studentDaoJpaImpl") 如果只有一个对象选择 则不用


    //@MyAutowired //springFramework 的依赖注入
    //如果有多个对象可以实例化 如：studentDaoJpaImpl 和 studentDaoMyBatisImpl 指定选择
    @Resource(name = "studentDaoJpaImpl")
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int add(String name) {
        System.out.println("-----------业务层----------");
        System.out.println("用户名是否重名");
        int result = studentDao.add(name);
        System.out.println("-----------业务操作结束-------");
        return result;
    }

    @Override
    public void update(String name) {
        System.out.println("-----------业务层----------");
        System.out.println("用户名是否重名");
        studentDao.update(name);
        System.out.println("-----------业务操作结束-------");
    }

    @Override
    public String find(String name) throws InterruptedException {
        Thread.sleep(new Random().nextInt(2000));
        studentDao.find(name);
        return null;
    }
}
