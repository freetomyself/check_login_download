package cn.itcast.dao;

import cn.itcast.bean.User;
import cn.itcast.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: day15_response--cn.itcast.dao
 * @author: WaHotDog 2019-06-13 18:40
 **/


public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录
      * @param loginuser 登录用户
     * @return 返回用户详细信息
     */
    public User login(User loginuser){
//        查询优化
        String sql="select * from User where username = ? and password = ? LIMIT 1";
        try {
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginuser.getUsername(),
                    loginuser.getPassword());
            return user;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
