import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * @author bingkun_zhang
 * @date 2020/1/14
 */
public class TestSavePoint {
    public static void main(String[] args) throws Exception {
        TestSavePoint tsp = new TestSavePoint();
        Connection conn = tsp.getConn();
        //设置自动提交失效
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        Savepoint sp1 = conn.setSavepoint("p1");
        stmt.executeUpdate("insert into student(name,sex,grade) values('fengbei','m','2008811237')");
        stmt.executeUpdate("insert into student(name,sex,grade) values('lisong','m','2008811221')");
        Savepoint sp2 = conn.setSavepoint("p2");
        stmt.executeUpdate("insert into student(name,sex,grade) values('heyanjie','m','2008811216')");
        Savepoint sp3 = conn.setSavepoint("p3");
        ResultSet rs = stmt.executeQuery("select * from student where name='wangshuhui'");
        if (!rs.next()) {
//            conn.rollback(sp1);
            conn.rollback(sp2);
        }
        conn.commit();
        rs = stmt.executeQuery("select * from student");
        while (rs.next()) {
            System.out.println("Row: " + rs.getRow() + "\tId: " + rs.getInt(1) + "\tName: " + rs.getString(2)
                    + "\tSex: " + rs.getString(3) + "\tGrade: " + rs.getString(4));
        }
        stmt.close();
        conn.close();
    }

    //获取连接
    public Connection getConn() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/test?user=root&password=root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
