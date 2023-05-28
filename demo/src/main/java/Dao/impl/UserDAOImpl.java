package Dao.impl;

import Dao.UserDAO;
import Dao.base.BaseDAO;
import pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends BaseDAO implements UserDAO {

    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        try {
            conn = getConn();
            String sql = "select * from t_user";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                int uid = rs.getInt(1);
                String name = rs.getString(2);
                int password = rs.getInt(3);
                int remeber = rs.getInt(4);

                User user = new User(uid,name,password,remeber);
                userList.add(user);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }
        return userList;
    }


    @Override
    public boolean addUser(User user) {
        String sql = "insert into t_user values(0,?,?,?)";

        return super.executeUpdate(sql,user.getName(),user.getPassword(),user.getRemeber()) > 0;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "update t_user set password = ? where name = ?";
        return super.executeUpdate(sql,user.getPassword(),user.getName()) > 0;
    }

    @Override
    public User getUserByName(String name) {
        try {
            conn = getConn();
            String sql = "select * from t_user where name like ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,name);
            rs = psmt.executeQuery();

            if (rs.next()) {
                int uid = rs.getInt(1);
                int password = rs.getInt(3);
                int remeber = rs.getInt(4);
                return new User(uid,name,password,remeber);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs,psmt,conn);
        }
        return null;
    }

    @Override
    public boolean delUser(String name) {
        String sql = "delete from t_user where name like ?";
        return super.executeUpdate(sql,name) > 0;
    }
}
