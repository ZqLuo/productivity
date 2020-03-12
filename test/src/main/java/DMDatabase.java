import dm.jdbc.driver.DmDriver;

import java.sql.*;

/**
 * 达梦数据库
 */
public class DMDatabase {

    // 定义 DM JDBC 驱动串
    String jdbcString = "dm.jdbc.driver.DmDriver"; // 定义 DM URL 连接串
    String urlString = "jdbc:dm://192.168.199.133:5236";
    // 定义连接用户名
    String userName = "SYSDBA";
    // 定义连接用户口令
    String password = "SYSDBA";
    // 定义连接对象
    Connection conn = null;

    /**
     * 加载 JDBC 驱动程序
     * @throws SQLException
     */
    public void loadJdbcDriver() throws SQLException {
        try {
            System.out.println("Loading JDBC Driver..."); // 加载JDBC驱动程序
            Class.forName(jdbcString);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Load JDBC Driver Error : " + e.getMessage());
        } catch (Exception ex) {
            throw new SQLException("Load JDBC Driver Error :");
        }
    }

    /* 连接 DM 数据库
     * @throws SQLException 异常 */
    public void connect() throws SQLException {
        try {
            Class.forName(jdbcString);
            System.out.println("Connectingto DM Server...");
            // 连接 DM 数据库
            conn = DriverManager.getConnection(urlString, userName, password);
        } catch (Exception e) {
            throw new SQLException("Connect to DM Server Error : "
                    + e.getMessage());
        }
    }

    /* 关闭连接
     * @throws SQLException 异常 */
    public void disConnect() throws SQLException {
        try {
            // 关闭连接
            conn.close();
        } catch (SQLException e) {
            throw new SQLException("close connection error : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            DMDatabase dmDatabase = new DMDatabase();
            dmDatabase.connect();
            String sql = "select * from PERSON.ADDRESS";
            // 创建语句对象
            Statement stmt = dmDatabase.conn.createStatement(); // 执行查询
            ResultSet rs = stmt.executeQuery(sql);
            dmDatabase.displayResultSet(rs);
            dmDatabase.disConnect();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /* 显示结果集
     * @param rs 结果集对象
     * @throws SQLException 异常 */
    private void displayResultSet(ResultSet rs) throws SQLException {
        // 取得结果集元数据
        ResultSetMetaData rsmd = rs.getMetaData(); // 取得结果集所包含的列数
        int numCols = rsmd.getColumnCount();
        // 显示列标头
        for (int i = 1; i <= numCols; i++) {
            if (i > 1) {
                System.out.print(",");
            }
            System.out.print(rsmd.getColumnLabel(i));
        }
        System.out.println();
        // 显示结果集中所有数据
         while (rs.next()) {
             for (int i = 1; i <= numCols; i++) {
                 if (i > 1) {
                     System.out.print(",");
                 }
                 System.out.print(rs.getString(i));
             }
             System.out.println("");
         }
    }
}
