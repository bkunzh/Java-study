import java.sql.*;

/**
 * Created by zhbk on 2019/2/24.
 * 比较批量和一条条插入10000条数据的时间
 * 输出：
 *
     批量执行时间=1.659秒
     一条一条总执行时间=14.947秒
 *
 */
public class JdbcBatchAndNotBatch {

    static String url1 = "jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&charactorEncoding=UTF8";
    static Connection conn = null;

    static void truncateTableData() throws SQLException {
//        Statement truncateStat = conn.createStatement();
//        truncateStat.execute("truncate table t_user"); //清空数据
//        if (truncateStat != null) {
//            truncateStat.close();
//        }

        PreparedStatement preparedStatement = conn.prepareStatement("truncate table t_user");
        preparedStatement.execute();
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    //计算批量插入10000条数据的时间
    static void batchInsert() {
        try {
            conn = DriverManager.getConnection(url1);//将所有连接信息都放在URL中
            truncateTableData();
            String sqlInsert = "insert into t_user(id,name,sex,age) value(null,?,?,?)";
            PreparedStatement preStatementInsert = conn.prepareStatement(sqlInsert);
            conn.setAutoCommit(false);//关闭自动提交


            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10000; ++i) {
                preStatementInsert.setString(1, "user" + i);
                preStatementInsert.setString(2, "男");
                preStatementInsert.setInt(3, i);
                //批次增加一个语句
                preStatementInsert.addBatch();
            }

            //批量执行
            preStatementInsert.executeBatch();
            conn.commit();
            long endTime = System.currentTimeMillis();

            System.out.println("批量执行时间=" + (endTime - startTime) / 1000.0 + "秒");

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //计算一条一条插入10000条数据的时间
    static void oneByOneInsert() {
        try {
            conn = DriverManager.getConnection(url1);//将所有连接信息都放在URL中
            truncateTableData();
            String sqlInsert = "insert into t_user(id,name,sex,age) value(null,?,?,?)";
            PreparedStatement preStatementInsert = conn.prepareStatement(sqlInsert);
            //不打开这个选项，每一条都会提交一下，打开和批量提交时间差距不明显
//            conn.setAutoCommit(false);//关闭自动提交


            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 10000; ++i) {
                preStatementInsert.setString(1, "user" + i);
                preStatementInsert.setString(2, "男");
                preStatementInsert.setInt(3, i);
                //一条一条执行
                preStatementInsert.execute();
            }
            long endTime = System.currentTimeMillis();

            System.out.println("一条一条总执行时间=" + (endTime - startTime) / 1000.0 + "秒");

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.jdbc.Driver");


            batchInsert();
            oneByOneInsert();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
