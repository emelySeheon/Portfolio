package Server;

import javax.sql.rowset.CachedRowSet;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static String URL = "jdbc:derby:rentalProperties";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    private static Connection connection; // manages connection
    private static PreparedStatement selectAllTenants;
    private static PreparedStatement selectTenantsByLastName;
    private static PreparedStatement insertNewTenant;
    private static PreparedStatement removeTenant;
    private static PreparedStatement selectAllSingleHouse;
    private static PreparedStatement insertNewSingleHouse;
    private static PreparedStatement removeSingleHouse;
    private static PreparedStatement selectAllApartment;
    private static PreparedStatement insertNewApartment;
    private static PreparedStatement removeApartment;
    private static PreparedStatement selectAllVacationRental;
    private static PreparedStatement insertNewVacationRental;
    private static PreparedStatement removeVacationRental;
    private static String command;

    static {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    // INSTANTIATE QUERIES
    static {
        try{
            selectAllTenants = connection.prepareStatement("SELECT * FROM tenant ORDER BY lastName, firstName");
            selectTenantsByLastName = connection.prepareStatement("SELECT * FROM tenant WHERE lastName LIKE ? ORDER BY lastName, firstName");
            insertNewTenant = connection.prepareStatement("INSERT INTO tenant (firstName, lastName, phoneNumber, email) VALUES (?, ?, ?, ?)");
            removeTenant = connection.prepareStatement("DELETE FROM tenant WHERE tenantID = ?");
            selectAllSingleHouse = connection.prepareStatement("SELECT * FROM singleHouse");
            insertNewSingleHouse = connection.prepareStatement("INSERT INTO singleHouse (propertyType, numBeds,"+
                                                                "numBaths, garage, frontYard, backYard, leaseLength,"+
                                                                "monthlyRent, maxTenants, tenantID, allTenantID,"+
                                                                "leaseStartDate, leaseEndDate, rentDueDate)"+
                                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            removeSingleHouse = connection.prepareStatement("DELETE FROM singleHouse WHERE houseID = ?");
            selectAllApartment = connection.prepareStatement("SELECT * FROM apartmentComplex ORDER BY apartmentID");
            insertNewApartment = connection.prepareStatement("INSERT INTO apartmentComplex(propertyType, unitNumber,"+
                "numBeds, numBaths, commonArea, utilityRoom, fitnessCenter, reservedParking, leaseLength, monthlyRent,"+
                "maxTenants, tenantID, allTenantID, leaseStartDate, leaseEndDate, rentDueDate)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            removeApartment = connection.prepareStatement("DELETE FROM apartmentComplex WHERE apartmentID = ?");
            selectAllVacationRental = connection.prepareStatement("SELECT * FROM vacationRental ORDER BY unitID");
            insertNewVacationRental = connection.prepareStatement("INSERT INTO vacationRental(propertyType, unitDescription,"+
                "leaseLength, leaseUnit, dailyRent, weeklyRent, monthlyRent, maxTenants,"+
                "tenantID, allTenantID, leaseStartDate, leaseEndDate, rentDueDate)"+
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            removeVacationRental = connection.prepareStatement("DELETE FROM vacationRental WHERE unitID = ?");

        } catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public Server(){}

    public static void main(String[] args) {
        try{
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Waiting For Client..");
            Socket conn = server.accept();
            System.out.println("Connected");
            ObjectOutputStream output = new ObjectOutputStream(conn.getOutputStream());
            output.flush();
            DataInputStream input = new DataInputStream(new BufferedInputStream(conn.getInputStream()));
            while (true){
                command = input.readUTF();
                output.flush();
                switch(command){
                    case "1": // GET ALL TENANTS
                        List<Tenant> getAllTenants = getAllTenants();
                        output.writeObject(getAllTenants);
                        output.flush();
                        break;
                    case "2": //FIND BY LAST NAME
                        String lastName = input.readUTF();
                        List<Tenant> lastNameList = getTenantByLastName(lastName);
                        output.writeObject(lastNameList);
                        output.flush();
                        break;
                    case "3": // INSERT TENANT
                        String insertFirstName = input.readUTF();
                        String insertLastName = input.readUTF();
                        String insertPhonenumber = input.readUTF();
                        String insertEmail = input.readUTF();
                        int rowsAddedToTenant = addTenant(insertFirstName, insertLastName, insertPhonenumber, insertEmail);
                        output.writeObject(rowsAddedToTenant);
                        break;
                    case "4": // REMOVE TENANT
                        String removeTenantID = input.readUTF();
                        int rowsRemovedFromTenant = removeTenant(removeTenantID);
                        output.writeObject(rowsRemovedFromTenant);
                        break;
                    case "5": // GET ALL SINGLEHOUSE
                        List<SingleHouse> getAllSingleHouse = getAllSingleHouse();
                        output.writeObject(getAllSingleHouse);
                        output.flush();
                        break;
                    case "6": // INSERT SINGLEHOUSE
                        String insertPropertyType = input.readUTF();
                        String insertNumBeds = input.readUTF();
                        String insertNumBaths = input.readUTF();
                        String insertGarage = input.readUTF();
                        String insertFrontYard = input.readUTF();
                        String insertBackYard = input.readUTF();
                        String insertLeaseLength = input.readUTF();
                        String insertMonthlyRent = input.readUTF();
                        String insertMaxTenants = input.readUTF();
                        String insertTenantID = input.readUTF();
                        String insertAllTenantID = input.readUTF();
                        String insertLeaseStartDate = input.readUTF();
                        String insertLeaseEndDate = input.readUTF();
                        String insertRentDueDate = input.readUTF();
                        int rowsAddedToSingleHouse = addSingleHouse(insertPropertyType, insertNumBeds, insertNumBaths, insertGarage, insertFrontYard, insertBackYard, insertLeaseLength, insertMonthlyRent, insertMaxTenants, insertTenantID, insertAllTenantID, insertLeaseStartDate, insertLeaseEndDate, insertRentDueDate);
                        output.writeObject(rowsAddedToSingleHouse);
                        break;
                    case "7": //REMOVE SINGLEHOUSE
                        String removeSingleHouseID = input.readUTF();
                        int rowsRemovedSingleHouse = removeSingleHouse(removeSingleHouseID);
                        output.writeObject(rowsRemovedSingleHouse);
                        break;
                    case "8": // GET ALL APARTMENT
                        List<Apartment> getAllApartment = getAllApartment();
                        output.writeObject(getAllApartment);
                        output.flush();
                        break;
                    case "9": // INSERT APARTMENT
                        String insertApropertyType = input.readUTF();
                        String insertAunitNumber = input.readUTF();
                        String insertAnumBeds = input.readUTF();
                        String insertAnumBaths = input.readUTF();
                        String insertAcommonArea = input.readUTF();
                        String insertAutilityRoom = input.readUTF();
                        String insertAfitnessCenter = input.readUTF();
                        String insertAreservedParking = input.readUTF();
                        String insertAleaseLength = input.readUTF();
                        String insertAmonthlyRent = input.readUTF();
                        String insertAmaxTenants = input.readUTF();
                        String insertAtenantID = input.readUTF();
                        String insertAallTenantID = input.readUTF();
                        String insertAleaseStartDate = input.readUTF();
                        String insertAleaseEndDate = input.readUTF();
                        String insertArentDueDate = input.readUTF();
                        int rowsAddedToApartment = addApartment(insertApropertyType,insertAunitNumber,insertAnumBeds,insertAnumBaths,insertAcommonArea,insertAutilityRoom,insertAfitnessCenter,insertAreservedParking,insertAleaseLength,insertAmonthlyRent,insertAmaxTenants,insertAtenantID,insertAallTenantID,insertAleaseStartDate,insertAleaseEndDate,insertArentDueDate);
                        output.writeObject(rowsAddedToApartment);
                        break;
                    case "10": // REMOVE APARTMENT
                        String removeApartmentID = input.readUTF();
                        int rowsRemovedApartment = removeApartment(removeApartmentID);
                        output.writeObject(rowsRemovedApartment);
                        break;
                    case "11": // GET ALL VACATION RENTAL
                        List<VacationRental> getAllVacationRental = getAllVacationRental();
                        output.writeObject(getAllVacationRental);
                        output.flush();
                        break;
                    case "12": // INSERT VACATION RENTAL insertV
                        String insertVpropertyType = input.readUTF();
                        String insertVunitDescription = input.readUTF();
                        String insertVleaseLength = input.readUTF();
                        String insertVleaseUnit = input.readUTF();
                        String insertVdailyRent = input.readUTF();
                        String insertVweeklyRent = input.readUTF();
                        String insertVmonthlyRent = input.readUTF();
                        String insertVmaxTenants = input.readUTF();
                        String insertVtenantID = input.readUTF();
                        String insertVallTenantID = input.readUTF();
                        String insertVleaseStartDate = input.readUTF();
                        String insertVleaseEndDate = input.readUTF();
                        String insertVrentDueDate = input.readUTF();
                        int rowsAddedToVacationRental = addVacationRental(insertVpropertyType,insertVunitDescription,insertVleaseLength,insertVleaseUnit,insertVdailyRent,insertVweeklyRent,insertVmonthlyRent,insertVmaxTenants,insertVtenantID,insertVallTenantID,insertVleaseStartDate,insertVleaseEndDate,insertVrentDueDate);
                        output.writeObject(rowsAddedToVacationRental);
                        break;
                    case "13": // REMOVE VACATION RENTAL
                        String removeUnitID = input.readUTF();
                        int rowsRemovedVacationRental = removeVacationRental(removeUnitID);
                        output.writeObject(rowsRemovedVacationRental);
                        break;
                    default:
                        System.out.println("Unsuccessful");
                        break;
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Tenant> getAllTenants() throws SQLException {
        try{
            ResultSet resultSet = selectAllTenants.executeQuery();
            try {
                List<Tenant> results = new ArrayList<Tenant>();
                while (resultSet.next()){
                    results.add(new Tenant(
                            resultSet.getInt("tenantID"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("email")));
                }
                return results;
            }
            catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Tenant> getTenantByLastName(String lastName) throws SQLException {
        selectTenantsByLastName.setString(1, lastName);
        try (ResultSet resultSet = selectTenantsByLastName.executeQuery()) {
            List<Tenant> results = new ArrayList<Tenant>();
            while (resultSet.next()) {
                results.add(new Tenant(
                        resultSet.getInt("tenantID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getString("email")));
            }
            return results;
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    public static int addTenant(String firstName, String lastName, String phoneNumber, String email) {
        try {
            // set parameters
            insertNewTenant.setString(1, firstName);
            insertNewTenant.setString(2, lastName);
            insertNewTenant.setString(4, phoneNumber);
            insertNewTenant.setString(3, email);
            // returns # of rows updated
            return insertNewTenant.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static int removeTenant(String tenantID){
        try {
            // set parameters
            removeTenant.setString(1, tenantID);
            // returns # of rows updated
            return removeTenant.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static List<SingleHouse> getAllSingleHouse() throws SQLException {
        try{
            ResultSet resultSet = selectAllSingleHouse.executeQuery();
            try {
                List<SingleHouse> results = new ArrayList<SingleHouse>();
                while (resultSet.next()){
                results.add(new SingleHouse(
                        resultSet.getInt("houseID"),
                        resultSet.getString("propertyType"),
                        resultSet.getString("numBeds"),
                        resultSet.getString("numBaths"),
                        resultSet.getString("garage"),
                        resultSet.getString("frontYard"),
                        resultSet.getString("backYard"),
                        resultSet.getString("leaseLength"),
                        resultSet.getString("monthlyRent"),
                        resultSet.getString("maxTenants"),
                        resultSet.getInt("tenantID"),
                        resultSet.getString("allTenantID"),
                        resultSet.getString("leaseStartDate"),
                        resultSet.getString("leaseEndDate"),
                        resultSet.getString("rentDueDate")));
                }
                return results;
            }
            catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int addSingleHouse(String propertyType, String numBeds, String numBaths, 
                                     String garage, String frontYard, String backYard, 
                                     String leaseLength, String monthlyRent, String maxTenants, 
                                     String tenantID, String allTenantID, String leaseStartDate, 
                                     String leaseEndDate, String rentDueDate) {
        try {
            // set parameters
            insertNewSingleHouse.setString(1, propertyType);
            insertNewSingleHouse.setString(2, numBeds);
            insertNewSingleHouse.setString(3, numBaths);
            insertNewSingleHouse.setString(4, garage);
            insertNewSingleHouse.setString(5, frontYard);
            insertNewSingleHouse.setString(6, backYard);
            insertNewSingleHouse.setString(7, leaseLength);
            insertNewSingleHouse.setString(8, monthlyRent);
            insertNewSingleHouse.setString(9, maxTenants);
            insertNewSingleHouse.setString(10, tenantID);
            insertNewSingleHouse.setString(11, allTenantID);
            insertNewSingleHouse.setString(12, leaseStartDate);
            insertNewSingleHouse.setString(13, leaseEndDate);
            insertNewSingleHouse.setString(14, rentDueDate);
            // returns # of rows updated
            return insertNewSingleHouse.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static int removeSingleHouse(String houseID){
        try {
            // set parameters
            removeSingleHouse.setString(1, houseID);
            // returns # of rows updated
            return removeSingleHouse.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static List<Apartment> getAllApartment() throws SQLException {
        try{
            ResultSet resultSet = selectAllApartment.executeQuery();
            try {
                List<Apartment> results = new ArrayList<Apartment>();
                while (resultSet.next()){
                results.add(new Apartment(
                        resultSet.getInt("apartmentID"),
                        resultSet.getString("propertyType"),
                        resultSet.getString("unitNumber"),
                        resultSet.getString("numBeds"),
                        resultSet.getString("numBaths"),
                        resultSet.getString("commonArea"),
                        resultSet.getString("utilityRoom"),
                        resultSet.getString("fitnessCenter"),
                        resultSet.getString("reservedParking"),
                        resultSet.getString("leaseLength"),
                        resultSet.getString("monthlyRent"),
                        resultSet.getString("maxTenants"),
                        resultSet.getInt("tenantID"),
                        resultSet.getString("allTenantID"),
                        resultSet.getString("leaseStartDate"),
                        resultSet.getString("leaseEndDate"),
                        resultSet.getString("rentDueDate")));
                }
                return results;
            }
            catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int addApartment(String propertyType, String unitNumber, String numBeds, 
                                   String numBaths, String commonArea, String utilityRoom, 
                                   String fitnessCenter, String reservedParking, String leaseLength, 
                                   String monthlyRent, String maxTenants, String tenantID, 
                                   String allTenantID, String leaseStartDate, String leaseEndDate, 
                                   String rentDueDate) {
        try {
            // set parameters
            insertNewApartment.setString(1,propertyType);
            insertNewApartment.setString(2,unitNumber);
            insertNewApartment.setString(3,numBeds);
            insertNewApartment.setString(4,numBaths);
            insertNewApartment.setString(5,commonArea);
            insertNewApartment.setString(6,utilityRoom);
            insertNewApartment.setString(7,fitnessCenter);
            insertNewApartment.setString(8,reservedParking);
            insertNewApartment.setString(9,leaseLength);
            insertNewApartment.setString(10,monthlyRent);
            insertNewApartment.setString(11,maxTenants);
            insertNewApartment.setString(12,tenantID);
            insertNewApartment.setString(13,allTenantID);
            insertNewApartment.setString(14,leaseStartDate);
            insertNewApartment.setString(15,leaseEndDate);
            insertNewApartment.setString(16,rentDueDate);
            // returns # of rows updated
            return insertNewApartment.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static int removeApartment(String apartmentID){
        try {
            // set parameters
            removeApartment.setString(1, apartmentID);
            // returns # of rows updated
            return removeApartment.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static List<VacationRental> getAllVacationRental() throws SQLException {
        try{
            ResultSet resultSet = selectAllVacationRental.executeQuery();
            try {
                List<VacationRental> results = new ArrayList<VacationRental>();
                while (resultSet.next()){
                results.add(new VacationRental(
                    resultSet.getInt("unitID"),
                    resultSet.getString("propertyType"),
                    resultSet.getString("unitDescription"),
                    resultSet.getString("leaseLength"),
                    resultSet.getString("leaseUnit"),
                    resultSet.getString("dailyRent"),
                    resultSet.getString("weeklyRent"),
                    resultSet.getString("monthlyRent"),
                    resultSet.getString("maxTenants"),
                    resultSet.getInt("tenantID"),
                    resultSet.getString("allTenantID"),
                    resultSet.getString("leaseStartDate"),
                    resultSet.getString("leaseEndDate"),
                    resultSet.getString("rentDueDate")));
                }
                return results;
            }
            catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int addVacationRental(String propertyType, String unitDescription, String leaseLength, 
                                        String leaseUnit, String dailyRent, String weeklyRent, 
                                        String monthlyRent, String maxTenants, String tenantID, 
                                        String allTenantID, String leaseStartDate, String leaseEndDate, 
                                        String rentDueDate) {
        try {
            // set parameters
            insertNewVacationRental.setString(1, propertyType);
            insertNewVacationRental.setString(2, unitDescription);
            insertNewVacationRental.setString(3, leaseLength);
            insertNewVacationRental.setString(4, leaseUnit);
            insertNewVacationRental.setString(5, dailyRent);
            insertNewVacationRental.setString(6, weeklyRent);
            insertNewVacationRental.setString(7, monthlyRent);
            insertNewVacationRental.setString(8, maxTenants);
            insertNewVacationRental.setString(9, tenantID);
            insertNewVacationRental.setString(10, allTenantID);
            insertNewVacationRental.setString(11, leaseStartDate);
            insertNewVacationRental.setString(12, leaseEndDate);
            insertNewVacationRental.setString(13, rentDueDate);
            // returns # of rows updated
            return insertNewVacationRental.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public static int removeVacationRental(String unitID){
        try {
            // set parameters
            removeVacationRental.setString(1, unitID);
            // returns # of rows updated
            return removeVacationRental.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }
}
