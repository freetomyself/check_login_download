package cn.itcast.test;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;
import org.junit.Test;

/**
 * @program: day15_response--cn.itcast.test
 * @author: WaHotDog 2019-06-13 19:29
 **/


public class TestUserDao {

    @Test
    public void TestUserDao(){
        User user = new User();
        user.setUsername("Ballen");
        user.setPassword("123");

        UserDao userDao = new UserDao();
        User login = userDao.login(user);
        System.out.println(login);
    }
}
