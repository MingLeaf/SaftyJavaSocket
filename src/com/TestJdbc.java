package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        TestJdbc.jdbccall();
    }
     public static void jdbccall() throws ClassNotFoundException, SQLException {
         Class.forName("com.mysql.cj.jdbc.Driver");
         String url = "jdbc:mysql://localhost:3306/chat?useSSL=FALSE&serverTimezone=Asia/Shanghai";
//         "root","yzm12345"
         String name = "root";
         String pwd = "yzm12345";
         Connection connection = DriverManager.getConnection(url, name, pwd);
         System.out.println("1111");
         System.out.println(connection);
     }

}
