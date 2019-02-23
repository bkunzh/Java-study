import java.sql.*;

class JdbcBatchTest1 {

    public static void main(String[] args) throws SQLException {
        String url1 = "jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&charactorEncoding=UTF8";
        Connection conn = null;
        Savepoint point1 = null; //conn.rollback(point1)回退到指定保存点，再提交，只会提交指定保存点之前的更改。和rollback()全部回退不同
        try {
            //1、加载数据库驱动，包名一般为域名反写
            Class.forName("com.mysql.jdbc.Driver");
            /*
			 * 2、获取数据连接对象，DriverManager.getConnection方法有3个重载方法
			 */
            conn = DriverManager.getConnection(url1);//将所有连接信息都放在URL中
            //truncate table xx删除表的所有数据，会将AUTO_INCREMENT设置为1，不删除表结构，drop table会删除表结构和数据；
            // delete不加where语句也删除所有数据，不会设置会将AUTO_INCREMENT。
            //但truncate是DDL语句，ddl语句即时生效，不受事务影响；delete是DML语句，受事务影响，需要事务提交才生效。
            String sqlTruncate = "TRUNCATE TABLE t_user";
            String sqlInsert = "insert into t_user(id,name,sex,age) value(null,?,?,?)";
            String sqlQuery = "select * from t_user where id < ?";

            PreparedStatement preStatementTruncate = conn.prepareStatement(sqlTruncate);
            conn.setAutoCommit(false);//关闭自动提交
            //清空数据,ddl即时生效
//            preStatementTruncate.execute();

            PreparedStatement preparedStatementDel = conn.prepareStatement("DELETE FROM t_user");
            preparedStatementDel.execute();


//            conn.rollback();
//            conn.commit();
//            if (true) {
//                return;
//            }

            PreparedStatement preStatementInsert = conn.prepareStatement(sqlInsert);
            PreparedStatement preStatementQuery = conn.prepareStatement(sqlQuery);


            for (int i = 0; i < 100; i++) {
                preStatementInsert.setString(1, "user_" + i);
                preStatementInsert.setString(2, "n");
                preStatementInsert.setInt(3, i);
                if (i > 50) {
//					preparedStatement_insert.setString(3, "我是错误的尝试");
                }
                preStatementInsert.addBatch();
            }


            Statement statement = conn.createStatement();
            statement.execute("insert into t_user(id,name,sex,age) value(null,'aaa','bb',22)");
            //保存记录点
            point1 = conn.setSavepoint("point1");

            //一次性插入100条记录，如果中间有出错，那么这一次性的插入都不会成功
            int[] updateInt = preStatementInsert.executeBatch();
//
            System.out.println(updateInt.length);

            //出错
//            int ccc = 5/0;

            //回退到指定保存点，再提交，只会提交指定保存点之前的更改
            conn.rollback(point1);
            conn.commit();

            preStatementQuery.setInt(1, 50);
            //4、执行SQL语句，查询语句就获取结果集
            ResultSet result = preStatementQuery.executeQuery();
            while (result.next()) {
                int a = result.getInt("id");
                int b = result.getInt(1);
                System.out.println("第一列值，通过列名:" + a + ",通过下标：" + b);

            }
			/*
			 * 5、关闭结果集、关闭会话、关闭连接
			 */
            result.close();
            preStatementTruncate.close();
            preStatementInsert.close();
            preStatementQuery.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("驱动没有加载到。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("出现sql异常。。。");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



