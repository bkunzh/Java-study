package com.bkunzhang.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author bkunzhang
 * @date 2019/10/9
 */
public class JdbcTransactionTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            conn.setAutoCommit(false); //开启事务
            ps1 = conn.prepareStatement("insert into user (username,age) values (?,?)");
            ps1.setObject(1, "小明");
            ps1.setObject(2, 19);
            ps1.execute();
            System.out.println("插入一个用户,小明");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        /*在这里的话，这条SQL语句是有错误的。应该只有俩个？。那么由于这些SQL语句都是处于一个事务
        中的。那么因为这个SQL语句是有错误的。会抛出异常，事务回滚。所有数据库的表中是没有任何变化的。这是要注意的。*/
//            ps2 = conn.prepareStatement("insert into user (username,age) values (?,?,?)");
            ps2 = conn.prepareStatement("insert into user (username,age) values (?,?)");
            ps2.setObject(1, "小红");
            ps2.setObject(2, 18);
            ps2.execute();
            System.out.println("插入一个用户,小红");
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback(); //回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                if (ps1 != null) {
                    ps1.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
