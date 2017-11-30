package ordersHW.DAO.ordersDAO;

import ordersHW.DAO.Tools;

import java.sql.*;

public class OrdersDAOImpl implements OrdersDAO{

    private String tableName;
    private Connection connection;

    public OrdersDAOImpl(Connection connection, String tableName) throws SQLException {
        this.connection=connection;
        this.tableName=tableName;
        initDB();
    }

    private void initDB() throws SQLException{
        try(Statement st = connection.createStatement()){
            st.execute("DROP TABLE IF EXISTS "+tableName);
            st.execute("CREATE TABLE "+tableName+" (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, client INT NOT NULL, goods INT NOT NULL )");
        }
    }

    @Override
    public void addOrder(int clientId, int goodsId) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO "+tableName+" (client, goods) VALUES (?,?)")) {
            ps.setInt(1, clientId);
            ps.setInt(2, goodsId);
            ps.executeUpdate();
        }
    }

    @Override
    public void viewOrders() throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName)) {
            Tools.printSelected(ps);
        }
    }
}
