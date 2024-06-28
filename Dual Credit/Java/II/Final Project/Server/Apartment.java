import java.io.Serializable;

public class Apartment implements Serializable{
	private int apartmentID;
	private String propertyType;
	private String unitNumber;
	private String numBeds;
	private String numBaths;
	private String commonArea;
	private String utilityRoom;
	private String fitnessCenter;
	private String reservedParking;
	private String leaseLength;
	private String monthlyRent;
	private String maxTenants;
	private int tenantID;
	private String allTenantID;
	private String leaseStartDate;
	private String leaseEndDate;
	private String rentDueDate;

	public Apartment(){}

	// contructor
	public Apartment(int apartmentID, String propertyType, String unitNumber, String numBeds, String numBaths, String commonArea,
					 String utilityRoom, String fitnessCenter, String reservedParking, String leaseLength, String monthlyRent,
					 String maxTenants, int tenantID, String allTenantID, String leaseStartDate, String leaseEndDate, String rentDueDate){
		this.apartmentID = apartmentID;
		this.propertyType = propertyType;
		this.unitNumber = unitNumber;
		this.numBeds = numBeds;
		this.numBaths = numBaths;
		this.commonArea = commonArea;
		this.utilityRoom = utilityRoom;
		this.fitnessCenter = fitnessCenter;
		this.reservedParking = reservedParking;
		this.leaseLength = leaseLength;
		this.monthlyRent = monthlyRent;
		this.maxTenants = maxTenants;
		this.tenantID = tenantID;
		this.allTenantID = allTenantID;
		this.leaseStartDate = leaseStartDate;
		this.leaseEndDate = leaseEndDate;
		this.rentDueDate = rentDueDate;
	}

	// sets the tenantID
	public void setApartmentID(int apartmentID){
		this.apartmentID = apartmentID;
	}
	// gets the tenantID
	public String getApartmentID(){
		String apartmentIDString = String.valueOf(apartmentID);
		return apartmentIDString;
	}

	// sets the propertyType
	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
	}
	// gets the propertyType
	public String getPropertyType(){
		return propertyType;
	}

	// sets the unitNumber
	public void setUnitNumber(String unitNumber){
		this.unitNumber = unitNumber;
	}
	// gets the unitNumber
	public String  getUnitNumber(){
		return unitNumber;
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

	// sets the commonArea
	public void setCommonArea(String commonArea){
		this.commonArea = commonArea;
	}
	// gets the commonArea
	public String getCommonArea(){
		return commonArea;
	}

	// sets the utilityRoom
	public void setUtilityRoom(String utilityRoom){
		this.utilityRoom = utilityRoom;
	}
	// gets the utilityRoom
	public String getUtilityRoom(){
		return utilityRoom;
	}

	// sets the fitnessCenter
	public void setFitnessCenter(String fitnessCenter){
		this.fitnessCenter = fitnessCenter;
	}
	// gets the fitnessCenter
	public String getFitnessCenter(){
		return fitnessCenter;
	}

	// sets the reservedParking
	public void setReservedParking(String reservedParking){
		this.reservedParking = reservedParking;
	}
	// gets the reservedParking
	public String getReservedParking(){
		return reservedParking;
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

	// sets the allTenantID
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

	@Override
	public String toString(){
		return getPropertyType() +"ABQ"+ getApartmentID() + "-" + getUnitNumber();
	}
}