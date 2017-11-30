package ordersHW;

import ordersHW.DAO.clientsDAO.ClientsDAOImpl;
import ordersHW.DAO.goodsDAO.GoodsDAOImpl;
import ordersHW.DAO.ordersDAO.OrdersDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "realrunner14"; // todo: change

    private static final String GOODS_NAME = "Goods";
    private static final String CLIENTS_NAME = "Clients";
    private static final String ORDERS_NAME = "Orders";

    public static void main( String[] args ){
        try(Connection connection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD)){

            GoodsDAOImpl goods = new GoodsDAOImpl(connection, GOODS_NAME);
            ClientsDAOImpl clients = new ClientsDAOImpl(connection, CLIENTS_NAME);
            OrdersDAOImpl orders = new OrdersDAOImpl(connection, ORDERS_NAME);

            goods.addGoods("Soap",20);
            goods.addGoods("Cheese",55);
            goods.addGoods("Cookies",30);
            goods.viewGoods();
            System.out.println();

            clients.addClient("Jeff","403-32-43");
            clients.addClient("Ash","460-22-91");
            clients.viewClients();
            System.out.println();

            orders.addOrder(1,1);
            orders.addOrder(2,3);
            orders.viewOrders();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    private static void addClients(){
//
//    }
//    private static void addGoods(){
//
//    }
//    private static void addOrders(){
//
//    }
}
