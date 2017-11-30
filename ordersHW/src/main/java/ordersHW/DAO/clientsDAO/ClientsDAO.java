package ordersHW.DAO.clientsDAO;

import java.sql.SQLException;

interface ClientsDAO {
    public void addClient(String name, String phone) throws SQLException;
    public void viewClients() throws SQLException;
}
