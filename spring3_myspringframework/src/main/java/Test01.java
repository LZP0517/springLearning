import com.yc.biz.StudentBizImpl;
import com.yc.springframework.MyAppConfig;
import com.yc.springframework.context.MyAnnotationConfigApplicationContext;
import com.yc.springframework.context.MyApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-05 15:01
 */
public class Test01 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        MyApplicationContext ac = new MyAnnotationConfigApplicationContext(MyAppConfig.class);
        StudentBizImpl hw = (StudentBizImpl) ac.getBean("studentBizImpl");
        hw.add("aa");
//        Session sn = (Session) ac.getBean("session");
//        sn.getSession();

    }
}
