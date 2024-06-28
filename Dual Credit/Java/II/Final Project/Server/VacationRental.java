import java.io.Serializable;

public class VacationRental implements Serializable{
	private int unitID;
	private String propertyType;
	private String unitDescription;
	private String leaseLength;
	private String leaseUnit;
	private String dailyRent;
	private String weeklyRent;
	private String monthlyRent;
	private String maxTenants;
	private int tenantID;
	private String allTenantID;
	private String leaseStartDate;
	private String leaseEndDate;
	private String rentDueDate;

	public VacationRental(){}

	public VacationRental(int unitID, String propertyType, String unitDescription, String leaseLength, String leaseUnit,
						  String dailyRent, String weeklyRent, String monthlyRent, String maxTenants, int tenantID,
						  String allTenantID, String leaseStartDate, String leaseEndDate, String rentDueDate){
		this.unitID = unitID;
		this.propertyType = propertyType;
		this.unitDescription = unitDescription;
		this.leaseLength = leaseLength;
		this.leaseUnit = leaseUnit;
		this.dailyRent= dailyRent;
		this.weeklyRent = weeklyRent;
		this.monthlyRent = monthlyRent;
		this.maxTenants = maxTenants;
		this.tenantID = tenantID;
		this.allTenantID = allTenantID;
		this.leaseStartDate = leaseStartDate;
		this.leaseEndDate = leaseEndDate;
		this.rentDueDate = rentDueDate;
	}

	// sets the unitID
	public void setUnitID(int unitID){
		this.unitID = unitID;
	}

	// gets the unitID
	public String getUnitID(){
		String unitIDString = String.valueOf(unitID);
		return unitIDString;
	}

	// sets the propertyType
	public void setPropertyType(String propertyType){
		this.propertyType = propertyType;
	}
	// gets the propertyType
	public String getPropertyType(){
		return propertyType;
	}

	// sets the unitDescription
	public void setUnitDescription(String unitDescription){
		this.unitDescription = unitDescription;
	}
	// gets the unitDescription
	public String getUnitDescription(){
		return unitDescription;
	}

	// sets the leaseLength
	public void setLeaseLength(String leaseLength){
		this.leaseLength = leaseLength;
	}
	// gets the leaseLength
	public String getLeaseLength(){
		return leaseLength;
	}

	// sets the leaseUnit
	public void setLeaseUnit(String leaseUnit){
		this.leaseUnit = leaseUnit;
	}
	// gets the leaseUnit
	public String getLeaseUnit(){
		return leaseUnit;
	}

	// sets the dailyRent
	public void setDailyRent(String dailyRent){
		this.dailyRent = dailyRent;
	}
	// gets the dailyRent
	public String getDailyRent(){
		return dailyRent;
	}

	// sets the weeklyRent
	public void setWeeklyRent(String weeklyRent){
		this.weeklyRent = weeklyRent;
	}
	// gets the weeklyRent
	public String getWeeklyRent(){
		return weeklyRent;
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
		return getPropertyType() +"ABQ"+ getUnitID();
	}
}