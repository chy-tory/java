package Dao.base;

import java.sql.*;

public abstract class BaseDAO {
    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    public final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public final String URL = "jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public final String USER = "root";
    public final String PWD = "yingxi";



    protected Connection getConn(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL,USER,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void close(ResultSet rs , PreparedStatement psmt , Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psmt != null) {
                psmt.close();
            }
            if (conn != null && !conn.isClosed()){
                conn.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    protected int executeUpdate(String sql , Object...params){
        try {
            conn = getConn();
            psmt = conn.prepareStatement(sql);
            if (params != null && params.length>0){
                for (int i = 0;i < params.length; i++){
                    psmt.setObject(i+1,params[i]);
//                    psmt.executeQuery();
                }
            }
            return psmt.executeUpdate() ;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs,psmt,conn);
        }
        return 0;
    }

}
