import java.io.Serializable;

public class SingleHouse implements Serializable{
    private int houseID;
    private String propertyType;
    private String numBeds;
    private String numBaths;
    private String garage;
    private String frontYard;
    private String backYard;
    private String leaseLength;
    private String monthlyRent;
    private String maxTenants;
    private int tenantID;
    private String allTenantID;
    private String leaseStartDate;
    private String leaseEndDate;
    private String rentDueDate;

    // constructor
    public SingleHouse(){}

    // constructor
    public SingleHouse(int houseID, String propertyType, String numBeds, String numBaths, String garage,
                       String frontYard, String backYard, String leaseLength, String monthlyRent, String maxTenants,
                       int tenantID, String allTenantID, String leaseStartDate, String leaseEndDate, String rentDueDate){
        this.houseID = houseID;
        this.propertyType = propertyType;
        this.numBeds = numBeds;
        this.numBaths = numBaths;
        this.garage = garage;
        this.frontYard = frontYard;
        this.backYard = backYard;
        this.leaseLength = leaseLength;
        this.monthlyRent = monthlyRent;
        this.maxTenants = maxTenants;
        this.tenantID = tenantID;
        this.allTenantID = allTenantID;
        this.leaseStartDate = leaseStartDate;
        this.leaseEndDate = leaseEndDate;
        this.rentDueDate = rentDueDate;
    }

    // sets the houseID
    public void setHouseID(int houseID){
        this.houseID = houseID;
    }
    // gets the houseID
    public String getHouseID(){
        String houseIDString = String.valueOf(houseID);
        return houseIDString;
    }

    // sets the propertyType
    public void setPropertyType(String propertyType){
        this.propertyType = propertyType;
    }
    // gets the propertyType
    public String getPropertyType(){
        return propertyType;
    }

    // sets the numBeds
    public void setNumBeds(String numBeds){
        this.numBeds = numBeds;
    }
    // gets the numBeds
    public String getNumBeds(){
        return numBeds;
    }

    // sets the numBaths
    public void setNumBaths(String numBaths){
        this.numBaths = numBaths;
    }
    // gets the numBaths
    public String getNumBaths(){
        return numBaths;
    }

    // sets the garage
    public void setGarage(String garage){
        this.garage = garage;
    }
    // gets the garage
    public String getGarage(){
        return garage;
    }

    // sets the frontYard
    public void setFrontYard(String frontYard){
        this.frontYard = frontYard;
    }
    // gets the frontYard
    public String getFrontYard(){
        return frontYard;
    }

    // sets the backYard
    public void setBackYard(String backYard){
        this.backYard = backYard;
    }
    // gets the backYard
    public String getBackYard(){
        return backYard;
    }

    // sets the leaseLength
    public void setLeaseLength(String leaseLength){
        this.leaseLength = leaseLength;
    }
    // gets the leaseLength
    public String getLeaseLength(){
        return leaseLength;
    }

    // sets the monthlyRent
    public void setMonthlyRent(String monthlyRent){
        this.monthlyRent = monthlyRent;
    }
    // gets the monthlyRent
    public String getMonthlyRent(){
        return monthlyRent;
    }

    // sets the maxTenants
    public void setMaxTenants(String maxTenants){
        this.maxTenants = maxTenants;
    }
    // gets the maxTenants
    public String getMaxTenants(){
        return maxTenants;
    }

    // sets the tenantID
    public void setTenantID(int tenantID){
        this.tenantID = tenantID;
    }
    // gets the tenantID
    public String getTenantID(){
        String tenantIDString = String.valueOf(tenantID);
        return tenantIDString;
    }

    //sets the allTenantID
    public void setAllTenantID(String allTenantID){
        this.allTenantID = allTenantID;
    }
    // gets the allTenantID
    public String getAllTenantID(){
        return allTenantID;
    }

    // sets the leaseStartDate
    public void setLeaseStartDate(String leaseStartDate){
        this.leaseStartDate = leaseStartDate;
    }
    // gets the leaseStartDate
    public String getLeaseStartDate(){
        return leaseStartDate;
    }

    // sets the leaseEndDate
    public void setLeaseEndDate(String leaseEndDate){
        this.leaseEndDate = leaseEndDate;
    }
    // gets the leaseEndDate
    public String getLeaseEndDate(){
        return leaseEndDate;
    }

    // sets the rentDueDate
    public void setRentDueDate(String rentDueDate){
        this.rentDueDate = rentDueDate;
    }
    // gets the rentDueDate
    public String getRentDueDate(){
        return rentDueDate;
    }

    // returns the string representation of the tenants name
    @Override
    public String toString(){
        return getPropertyType() +"ABQ"+ getHouseID();
    }
}