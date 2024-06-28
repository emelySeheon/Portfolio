import Server.Apartment;
import Server.SingleHouse;
import Server.Tenant;
import Server.VacationRental;

import javax.sql.rowset.CachedRowSet;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class TenantQueries {
    private static Socket socket;
    static {
        try{
            socket = new Socket("localHost", 5000);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static DataOutputStream output;
    static {
        try{
            output = new DataOutputStream(socket.getOutputStream());
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static ObjectInputStream input;
    static {
        try{
            input = new ObjectInputStream(socket.getInputStream());
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // select all of the tenants in the db
    public List<Tenant> getAllTenants() throws IOException, ClassNotFoundException {
        // executeQuery returns ResultSet containing matching entries
        String command = "1";
        output.writeUTF(command);
        List<Tenant> results = (List<Tenant>) input.readObject();
        return results;
    }

    // select all of the tenants ordered by last name
    public List<Tenant> getTenantsByLastName(String lastName) throws IOException, ClassNotFoundException {
        String command = "2";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(lastName);
        output.flush();
        List<Tenant> results = (List<Tenant>) input.readObject();
        System.out.println(results);
        // executeQuery returns ResultSet containing matching entries
        return results;
    }

    // add a tenant
    public int addTenant(String firstName, String lastName, String phoneNumber, String email) throws IOException, ClassNotFoundException {
        // insert the new entry
        String command = "3";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(firstName);
        output.flush();
        output.writeUTF(lastName);
        output.flush();
        output.writeUTF(phoneNumber);
        output.flush();
        output.writeUTF(email);
        output.flush();
        return (int) input.readObject();
    }

    // remove a tenant
    public int removeTenant(String tenantID) throws IOException, ClassNotFoundException{
        // remove the entry
        String command = "4";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(tenantID);
        output.flush();
        return (int) input.readObject();
    }

    // select all of the singleHouse in the db
    public List<SingleHouse> getAllSingleHouse() throws IOException, ClassNotFoundException {
        // executeQuery returns ResultSet containing matching entries
        String command = "5";
        output.writeUTF(command);
        List<SingleHouse> results = (List<SingleHouse>) input.readObject();
        return results;
    }

    public int addSingleHouse(String propertyType, String numBeds, String numBaths,
                              String garage, String frontYard, String backYard,
                              String leaseLength, String monthlyRent, String maxTenants,
                              String tenantID, String allTenantID, String leaseStartDate,
                              String leaseEndDate, String rentDueDate) throws IOException, ClassNotFoundException {
        // insert the new entry
        String command = "6";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(propertyType);
        output.flush();
        output.writeUTF(numBeds);
        output.flush();
        output.writeUTF(numBaths);
        output.flush();
        output.writeUTF(garage);
        output.flush();
        output.writeUTF(frontYard);
        output.flush();
        output.writeUTF(backYard);
        output.flush();
        output.writeUTF(leaseLength);
        output.flush();
        output.writeUTF(monthlyRent);
        output.flush();
        output.writeUTF(maxTenants);
        output.flush();
        output.writeUTF(String.valueOf(tenantID));
        output.flush();
        output.writeUTF(allTenantID);
        output.flush();
        output.writeUTF(leaseStartDate);
        output.flush();
        output.writeUTF(leaseEndDate);
        output.flush();
        output.writeUTF(rentDueDate);
        output.flush();
        return (int) input.readObject();
    }

    public int removeSingleHouse(String houseID) throws IOException, ClassNotFoundException{
        // remove the entry
        String command = "7";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(houseID);
        output.flush();
        return (int) input.readObject();
    }

    public List<Apartment> getAllApartment() throws IOException, ClassNotFoundException {
        // executeQuery returns ResultSet containing matching entries
        String command = "8";
        output.writeUTF(command);
        List<Apartment> results = (List<Apartment>) input.readObject();
        return results;
    }

    public int addApartment(String propertyType, String unitNumber, String numBeds,
                            String numBaths, String commonArea, String utilityRoom,
                            String fitnessCenter, String reservedParking, String leaseLength,
                            String monthlyRent, String maxTenants, String tenantID,
                            String allTenantID, String leaseStartDate, String leaseEndDate,
                            String rentDueDate) throws IOException, ClassNotFoundException {
        // insert the new entry
        String command = "9";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(propertyType);
        output.flush();
        output.writeUTF(unitNumber);
        output.flush();
        output.writeUTF(numBeds);
        output.flush();
        output.writeUTF(numBaths);
        output.flush();
        output.writeUTF(commonArea);
        output.flush();
        output.writeUTF(utilityRoom);
        output.flush();
        output.writeUTF(fitnessCenter);
        output.flush();
        output.writeUTF(reservedParking);
        output.flush();
        output.writeUTF(leaseLength);
        output.flush();
        output.writeUTF(monthlyRent);
        output.flush();
        output.writeUTF(maxTenants);
        output.flush();
        output.writeUTF(tenantID);
        output.flush();
        output.writeUTF(allTenantID);
        output.flush();
        output.writeUTF(leaseStartDate);
        output.flush();
        output.writeUTF(leaseEndDate);
        output.flush();
        output.writeUTF(rentDueDate);
        output.flush();
        return (int) input.readObject();
    }

    public int removeApartment(String apartmentID) throws IOException, ClassNotFoundException{
        // remove the entry
        String command = "10";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(apartmentID);
        output.flush();
        return (int) input.readObject();
    }

    public List<VacationRental> getAllVacationRental() throws IOException, ClassNotFoundException {
        // executeQuery returns ResultSet containing matching entries
        String command = "11";
        output.writeUTF(command);
        List<VacationRental> results = (List<VacationRental>) input.readObject();
        return results;
    }

    public int addVacationRental(String propertyType, String unitDescription, String leaseLength,
                                 String leaseUnit, String dailyRent, String weeklyRent,
                                 String monthlyRent, String maxTenants, String tenantID,
                                 String allTenantID, String leaseStartDate, String leaseEndDate,
                                 String rentDueDate) throws IOException, ClassNotFoundException {
        // insert the new entry
        String command = "12";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(propertyType);
        output.flush();
        output.writeUTF(unitDescription);
        output.flush();
        output.writeUTF(leaseLength);
        output.flush();
        output.writeUTF(leaseUnit);
        output.flush();
        output.writeUTF(dailyRent);
        output.flush();
        output.writeUTF(weeklyRent);
        output.flush();
        output.writeUTF(monthlyRent);
        output.flush();
        output.writeUTF(maxTenants);
        output.flush();
        output.writeUTF(tenantID);
        output.flush();
        output.writeUTF(allTenantID);
        output.flush();
        output.writeUTF(leaseStartDate);
        output.flush();
        output.writeUTF(leaseEndDate);
        output.flush();
        output.writeUTF(rentDueDate);
        output.flush();
        return (int) input.readObject();
    }

    public int removeVacationRental(String unitID) throws IOException, ClassNotFoundException{
        // remove the entry
        String command = "13";
        output.writeUTF(command);
        output.flush();
        output.writeUTF(unitID);
        output.flush();
        return (int) input.readObject();
    }
}