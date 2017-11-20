package apartmentHW;

import java.sql.SQLException;

public class Main {

    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb1";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root"; // todo: change
    static final String DB_TABLE_NAME = "Apartments";

    public static void main( String[] args ){
        try {
            ApartmentDAO apartDAO = new ApartmentDAOJDBC(DB_CONNECTION, DB_USER, DB_PASSWORD, DB_TABLE_NAME);

            int tP = 26000; // price
            int tR = 2; // rooms
            int tA = 70; // area
            String tD="District2";
            String tAdr = "Address1";

            System.out.println("\nALL:");
            apartDAO.selectAll();

            System.out.println("\nBy address: "+ tAdr);
            apartDAO.selectByAddress(tAdr);

            System.out.println("\nBy district: "+tD);
            apartDAO.selectByDistrict(tD);

            System.out.println("\nBy price: "+ tP);
            apartDAO.selectByPrice(tP,"=");

            System.out.println("\nBy area: "+tA);
            apartDAO.selectByArea(tA,"<=");

            System.out.println("\nBy rooms: "+tR);
            apartDAO.selectByRooms(tR,">=");

        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
            System.exit(1);
        }

    }
}
