package ordersHW.DAO.goodsDAO;

import ordersHW.DAO.Tools;

import java.sql.*;

public class GoodsDAOImpl implements GoodsDAO{

    private String tableName;
    private Connection connection;

    public GoodsDAOImpl(Connection connection, String tableName) throws SQLException {
        this.connection=connection;
        this.tableName=tableName;
        initDB();
    }

    private void initDB() throws SQLException{
        try(Statement st = connection.createStatement()){
            st.execute("DROP TABLE IF EXISTS "+tableName);
            st.execute("CREATE TABLE "+tableName+" (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, price INT NOT NULL)");
        }
    }

    @Override
    public void addGoods(String name, int price) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO "+tableName+" (name, price) VALUES (?,?)")) {
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.executeUpdate();
        }
    }


    @Override
    public void viewGoods() throws SQLException  {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName)) {
            Tools.printSelected(ps);
        }
    }

}
