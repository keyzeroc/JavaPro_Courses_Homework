package apartmentHW;

import java.sql.*;

public class ApartmentDAOJDBC implements ApartmentDAO {

    private final String tableName;
    private Connection connection;

    public ApartmentDAOJDBC(String dbConnection, String dbUser, String dbPassword, String tableName) throws SQLException{
        connection = DriverManager.getConnection(dbConnection, dbUser, dbPassword);
        System.out.println("**Connection set!**");
        this.tableName=tableName;
    }

    @Override
    public void selectByAddress(String qAddress) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE address = (?)")) { // SQL query here
            ps.setString(1,qAddress);
            viewSQLResult(ps);
        }
    }

    @Override
    public void selectByDistrict(String qDistrict) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE district = (?)")) { // SQL query here
            ps.setString(1,qDistrict);
            viewSQLResult(ps);
        }
    }

    @Override
    public void selectByArea(int qArea, String operator) throws SQLException{
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE area "+operator+" (?)")) { // SQL query here
            ps.setInt(1,qArea);
            viewSQLResult(ps);
        }
    }

    @Override
    public void selectByRooms(int qRooms, String operator) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE rooms "+operator+" (?)")) { // SQL query here
            ps.setInt(1,qRooms);
            viewSQLResult(ps);
        }
    }

    @Override
    public void selectByPrice(int qPrice, String operator) throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName+" WHERE price "+operator+" (?)")) { // SQL query here
            ps.setInt(1,qPrice);
            viewSQLResult(ps);
        }
    }

    @Override
    public void selectAll() throws SQLException {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+tableName)) { // SQL query here
            viewSQLResult(ps);
        }
    }

    private void viewSQLResult(PreparedStatement ps) throws SQLException{
        try (ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        }
    }

}
