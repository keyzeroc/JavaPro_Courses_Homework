package ordersHW.DAO.ordersDAO;

import java.sql.SQLException;

interface OrdersDAO {
    public void addOrder(int clientId, int goodsId) throws SQLException;
    public void viewOrders() throws SQLException;
}
