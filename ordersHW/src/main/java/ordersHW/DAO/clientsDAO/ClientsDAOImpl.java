package ordersHW.DAO.clientsDAO;

import ordersHW.DAO.Tools;

import java.sql.*;

public class ClientsDAOImpl implements ClientsDAO{

    private String tableName;
    private Connection connection;

    public ClientsDAOImpl(Connection connection, String tableName) throws SQLException{
        this.connection=connection;
        this.tableName=tableName;
        initDB();
    }

    private void initDB() throws SQLException{
        try(Statement st = connection.createStatement()){
            st.execute("DROP TABLE IF EXISTS "+tableName);
            st.execute("CREATE TABLE "+tableName+" (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(40) NOT NULL, phone VARCHAR(10) NOT NULL)");
        }
    }

    @Override
    public void addClient(String name, String phone) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("INSERT INTO "+tableName+" (name, phone) VALUES (?,?)")) {
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();
        }
    }

    @Override
    public void viewClients() throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName)) {
            Tools.printSelected(ps);
        }
    }
}
