package apartmentHW;

import java.sql.SQLException;

public interface ApartmentDAO {
    public void selectByAddress(String qAddress) throws SQLException;
    public void selectByDistrict(String qDistrict) throws SQLException;
    public void selectByArea(int qArea, String operator) throws SQLException;
    public void selectByRooms(int qRooms, String operator) throws SQLException;
    public void selectByPrice(int qPrice, String operator) throws SQLException;
    public void selectAll() throws SQLException;
}
