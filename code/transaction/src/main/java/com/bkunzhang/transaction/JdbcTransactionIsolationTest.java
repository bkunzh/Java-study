package com.bkunzhang.transaction;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bkunzhang
 * @date 2019/10/9
 * 测试脏读、不可重复读、幻读
 */
public class JdbcTransactionIsolationTest {
    //测试脏读
    @Test
    public void readUncommitted() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            System.out.println("事务默认级别是：" + conn.getTransactionIsolation());
//            //设置事务隔离级别
//            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            ps1 = conn.prepareStatement("select * from user");
            ResultSet resultSet = ps1.executeQuery();
            resultSet.last();
            System.out.println("开始前用户表行数：" + resultSet.getRow());

            conn.setAutoCommit(false); //开启事务
            ps1 = conn.prepareStatement("insert into user (username,age) values (?,?)"); //没有提交事务，更新或新增
            ps1.setObject(1, "小明");
            ps1.setObject(2, 19);
            ps1.execute();
            System.out.println("插入一个用户,小明");

            //另一个事务来测试脏读，读到其他没有提交的事务更新或新增的数据
            new Thread(() -> {
                Connection conn2 = null;
                try {
                    conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
                    //设置事务隔离级别
                    conn2.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                    PreparedStatement ps2 = conn2.prepareStatement("select * from user");
                    ResultSet rs = ps2.executeQuery();
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<Object> list = new ArrayList<>();
                    while (rs.next()) {
                        Map rowData = new HashMap();
                        for (int i = 1; i <= columnCount; i++) {
                            rowData.put(metaData.getColumnName(i), rs.getObject(i));
                        }

                        list.add(rowData);
                    }
                    System.out.println("读到的数据:" + list + "\n数据行数:" + list.size());
                } catch (Exception e) {
                    e.printStackTrace();
                    if (conn2 != null) {
                        try {
                            conn2.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } finally {
                    if (conn2 != null) {
                        try {
                            conn2.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //提交事务
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

    //测试不可重复读
    @Test
    public void uprepeatableRead() {
        Connection conn = null;
        PreparedStatement ps1 = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            System.out.println("事务默认级别是：" + conn.getTransactionIsolation());
//            //设置事务隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);
            ps1 = conn.prepareStatement("select * from user where id = 8");
            ResultSet resultSet = ps1.executeQuery();
            if (resultSet.next()) {
                System.out.println("id为8的行：" + resultSet.getObject(1) + ":" + resultSet.getObject(2) + ":" + resultSet.getObject(3));
            }
            new Thread(() -> {
                Connection conn2 = null;
                try {
                    conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
                    //设置事务隔离级别
                    conn2.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                    conn2.setAutoCommit(false);
                    PreparedStatement ps2 = conn2.prepareStatement("update user set username='哈哈2' where id = 8");
                    ps2.executeUpdate();
                    conn2.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (conn2 != null) {
                        try {
                            conn2.rollback();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                } finally {
                    if (conn2 != null) {
                        try {
                            conn2.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //第二次读
            ps1 = conn.prepareStatement("select * from user where id = 8");
            resultSet = ps1.executeQuery();
            if (resultSet.next()) {
                System.out.println("id为8的行：" + resultSet.getObject(1) + ":" + resultSet.getObject(2) + ":" + resultSet.getObject(3));
            }

            //提交事务
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

    boolean waitFlag = false;
    Object lock = new Object();

    //再rr隔离下，一个事务的两次读集合是一样的，幻读，是指先读一个，
    //不可重复读侧重表达 读-读，幻读则是说 读-写，用写来证实读的是鬼影。
    //测试幻读, TRANSACTION_REPEATABLE_READ级别没出现幻读，TRANSACTION_READ_COMMITTED才会出现
    @Test
    public void phantomRead() {
        Connection conn2 = null;
        try {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            conn2.setAutoCommit(false);
            //设置事务隔离级别
//            conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            // for update可以防止幻读，可以不用打开SERIALIZABLE隔离
            String querySql = "select * from user";
            final String name = "张飞" + System.currentTimeMillis();
            String insertSql = "insert into user (username,age) values (?,22)";
//            String querySql = "select * from user where id > 30 --for update";

            PreparedStatement ps2;
            ResultSet rs;
            ResultSetMetaData metaData;
            int columnCount;
            List<Object> list;

            ps2 = conn2.prepareStatement(querySql);
            rs = ps2.executeQuery();
            metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();
            list = new ArrayList<>();
            while (rs.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnName(i), rs.getObject(i));
                }

                list.add(rowData);
            }
            System.out.println("读到的数据:" + list + "\n数据行数:" + list.size());


            new Thread(() -> {
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
                    System.out.println("事务默认级别是：" + conn.getTransactionIsolation());
                    //设置事务隔离级别
//                    conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                    conn.setAutoCommit(false); //开启事务
//                    PreparedStatement ps1 = conn.prepareStatement("insert into user (username,age) values (?,?)"); //没有提交事务，更新或新增
//                    ps1.setObject(1, "小明");
//                    ps1.setObject(2, 19);
                    PreparedStatement ps1 = conn.prepareStatement(insertSql);
                    ps1.setObject(1, name);
                    ps1.execute();
                    conn.commit();
                    System.out.println("新增一个用户:" + name);
                    while (!waitFlag) {
                        synchronized (lock) {
//                            System.out.println("Thread获得锁");
                            lock.notify();
//                            System.out.println("Thread释放锁");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } finally {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //再读取一下行数
            ps2 = conn2.prepareStatement(querySql);
            rs = ps2.executeQuery();
            metaData = rs.getMetaData();
            columnCount = metaData.getColumnCount();
            list = new ArrayList<>();
            boolean flag = false;
            while (rs.next()) {
                Map rowData = new HashMap();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnName(i), rs.getObject(i));
                }

                if (name.equals(rowData.get("username"))) {
                    System.out.println("存在" + name);
                    flag = true;
                }

                list.add(rowData);
            }
            System.out.println("读到的数据:" + list + "\n数据行数:" + list.size());

            //不存在插入，由于幻读，另一个事务插入了，但看不到
            if (!flag) {
                System.out.println("不存在" + name + "，插入");
                ps2 = conn2.prepareStatement(insertSql);
                ps2.setObject(1, name);
                ps2.execute();
            }

            //提交事务
            conn2.commit();
            System.out.println("等待锁");
            synchronized (lock) {
                System.out.println("获得锁");
                lock.wait();
                waitFlag = true;
                System.out.println("释放锁");
            }
            System.out.println("结束");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn2.rollback(); //回滚
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            try {
                if (conn2 != null) {
                    conn2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
