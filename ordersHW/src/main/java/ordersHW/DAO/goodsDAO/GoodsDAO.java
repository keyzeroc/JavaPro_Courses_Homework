package ordersHW.DAO.goodsDAO;

import java.sql.SQLException;

interface GoodsDAO {
    public void addGoods(String name, int price) throws SQLException;
    public void viewGoods() throws SQLException;
}
