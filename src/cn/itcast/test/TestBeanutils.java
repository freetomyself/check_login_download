package cn.itcast.test;

import cn.itcast.bean.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @program: day15_response--cn.itcast.test
 * @author: WaHotDog 2019-06-14 16:23
 **/


public class TestBeanutils {
    @Test
    public void  test(){

        User user = new User();
        BeanUtils beanUtils = new BeanUtils();
        try {
            beanUtils.setProperty(user,"username","zhangsan");
            System.out.println();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }}
