import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.*;

public class ClientGUI extends JFrame{
    private final TenantQueries tenantQueries = new TenantQueries();

    private JPanel panel1;
    private JButton btnUpdate;
    private JTextArea txtboxDisplay;
    private JButton addTenantButton;
    private JTextField firstNameTextField;
    private JTextField emailTextField;
    private JTextField txtRentalIDUpdate;
    private JTextField txtNameUpdate;
    private JTextField txtContactUpdate;
    private JTextField txtRentalIDRent;
    private JButton btnPay;
    private JButton btnCheckInfo;
    private JButton btnOptions;
    private JComboBox choiceLocation;
    private JButton btnDelete;
    private JComboBox SHchoiceFromMonth;
    private JComboBox SHchoiceToDay;
    private JRadioButton singleHouseRadioButton;
    private JRadioButton RBtnApartment;
    private JRadioButton RBtnVacation;
    private JComboBox SHchoiceToMonth;
    private JComboBox SHchoiceFromYear;
    private JComboBox choiceFromMonthOptions;
    private JComboBox SHchoiceFromDay;
    private JComboBox choiceFromYearOptions;
    private JComboBox choiceToMonthOptions;
    private JComboBox choiceToDayOptions;
    private JComboBox choiceToYearOptions;
    private JTextField txtAmountPaid;
    private JButton btnCheckRentDue;
    private JTextField tenantIDTextField;
    private JTextField lastNameTextField;
    private JTextField phoneTextField;
    private JButton removeTenantButton;
    private JButton findTenantButton;
    private JButton browseAllTenantButton;
    private JTextField removeTenantIDTextField;
    private JTextField finfByLastNameTextField;
    private JRadioButton garageRadioButton;
    private JRadioButton frontYardRadioButton;
    private JRadioButton backYardRadioButton;
    private JTextField SHnumBedsTextField;
    private JButton addSingleHouseButton;
    private JTextField SHpropertyTypeTextField;
    private JTextField SHnumBathsTextField;
    private JTextField SHleaseLengthTextField;
    private JTextField SHmonthlyRentTextField;
    private JTextField SHmaxTenantsTextField;
    private JTextField SHtenantIDTextField;
    private JTextField SHallTenantIDTextField;
    private JComboBox SHchoiceToYear;
    private JComboBox SHrentDueMonth;
    private JComboBox SHrentDueDay;
    private JComboBox SHrentDueYear;
    private JRadioButton commonAreaRadioButton;
    private JRadioButton utilityRoomRadioButton;
    private JRadioButton fitnessCenterRadioButton;
    private JRadioButton reservedParkingRadioButton;
    private JTextArea vUnitDescriptionTextArea;
    private JButton addApartmentButton;
    private JTextField apartmentUnitNumberTextField;
    private JTextField apartmentNumBedsTextField;
    private JTextField apartmentNumBathsTextField;
    private JTextField apartmentLeaseLengthTextField;
    private JTextField apartmentMonthlyRentTextField;
    private JTextField apartmentMaxTenantsTextField;
    private JTextField apartmentPrimaryTenantTextField;
    private JTextField apartmentAllTenantsTextField;
    private JComboBox AfromMonthStart;
    private JComboBox AfromDayStart;
    private JComboBox AfromYearStart;
    private JComboBox AtoMonthEnd;
    private JComboBox AtoDayEnd;
    private JComboBox AtoYearEnd;
    private JComboBox ArentDueMonth;
    private JComboBox ArentDueDay;
    private JComboBox ArentDueYear;
    private JTextField apartmentPropertyTypeTextField;
    private JTextField vPropertyTypeTextField;
    private JTextField VleaseLengthTextField;
    private JTextField VleaseUnitTextField;
    private JTextField VdailyRentTextField;
    private JTextField VweeklyRentTextField;
    private JTextField VmonthlyRentTextField;
    private JTextField VmaxTenantsTextField;
    private JTextField VprimaryTenantTextField;
    private JTextField VallTenantsTextField;
    private JComboBox VfromMonthStart;
    private JComboBox VfromDayStart;
    private JComboBox VfromYearStart;
    private JComboBox VtoMonthEnd;
    private JComboBox VtoDayEnd;
    private JComboBox VtoYearEnd;
    private JComboBox VrentDueMonth;
    private JComboBox VrentDueDay;
    private JComboBox VrentDueYear;
    private JButton addVacationRentalButton;

    public int code = 0;
    public boolean isDone = false;
    public boolean isFinished = false;

    //Make New Button
    private int months[] = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
    private int days[] = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    private int years[] = new int[] {2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030};
    private int fromMonthIndex = 1;
    private int fromDayIndex = 1;
    private int fromYearIndex = 1;
    private int toMonthIndex = 1;
    private int toDayIndex = 1;
    private int toYearIndex = 1;
    public int fromMonth = 1;
    public int fromDay = 1;
    public int fromYear = 1;
    public int toMonth = 1;
    public int toDay = 1;
    public int toYear = 1;
    public String name = "default";
    public String contact = "default";
    public String propertyID = "default";
    public String rentalID = "default";

    //Delete Button
    public String deleteRentalID = "default";

    //Update Button
    public String updateRentalID = "default";

    //Check Options Button
    public boolean singleHouse = false;
    public boolean apartment = false;
    public boolean vacationRental = false;
    private int checkFromMonthIndex = 1;
    private int checkFromDayIndex = 1;
    private int checkFromYearIndex = 1;
    private int checkToMonthIndex = 1;
    private int checkToDayIndex = 1;
    private int checkToYearIndex = 1;
    public int checkFromMonth = 1;
    public int checkFromDay = 1;
    public int checkFromYear = 1;
    public int checkToMonth = 1;
    public int checkToDay = 1;
    public int checkToYear = 1;
    public int dueMonth = 1;
    public int dueDay = 1;
    public int dueYear = 1;
    public String location = "default";

    //Pay Button
    public double amountDue = 00.00;
    public double amountPaid = 00.00;

    //Check Renter Info Button
    public String rentalIDRentCheck = "default";

    //Check Rent Button
    int numberOfRenters = 0;
    public List<List> rentersInfo = new ArrayList<>();

    public ClientGUI() {
        super("Rental Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500, 1000);
        this.setVisible(true);
        this.setContentPane(panel1);

        //Make New Button
        addTenantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                try {
                    result = tenantQueries.addTenant(
                            firstNameTextField.getText(), lastNameTextField.getText(),
                            emailTextField.getText(),phoneTextField.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

                if (result == 1) {
                    txtboxDisplay.setText("Entry Added, New Tenant successfully added.");
                }
                else {
                    txtboxDisplay.setText("Entry Not Added, Unable to add entry.");
                }

                /* old code
                if(firstNameTextField.getText().equals("Name")
                        || firstNameTextField.getText().isEmpty()
                        || emailTextField.getText().equals("Email/Phone")
                        || emailTextField.getText().isEmpty()
                        || choiceFromMonth.getSelectedIndex()==0
                        || choiceFromMonth.getSelectedIndex()==-1
                        || choiceFromDay.getSelectedIndex()==0
                        || choiceFromDay.getSelectedIndex()==-1
                        || choiceFromYear.getSelectedIndex()==0
                        || choiceFromYear.getSelectedIndex()==-1
                        || choiceToMonth.getSelectedIndex()==0
                        || choiceToMonth.getSelectedIndex()==-1
                        || choiceToDay.getSelectedIndex()==0
                        || choiceToDay.getSelectedIndex()==-1
                        || choiceToYear.getSelectedIndex()==0
                        || choiceToYear.getSelectedIndex()==-1
                        || txtPropertyID.getText().equals("Property ID")
                        || txtPropertyID.getText().isEmpty()) {

                    //Make sure the property ID is valid
                    txtboxDisplay.setText("Error: Please fill out all applicable fields.");
                } else {
                    fromMonthIndex = choiceFromMonth.getSelectedIndex();
                    fromDayIndex = choiceFromDay.getSelectedIndex();
                    fromYearIndex = choiceFromYear.getSelectedIndex();
                    toMonthIndex = choiceToMonth.getSelectedIndex();
                    toDayIndex = choiceToDay.getSelectedIndex();
                    toYearIndex = choiceToYear.getSelectedIndex();

                    fromMonth = months [fromMonthIndex-1];
                    fromDay = days [fromDayIndex-1];
                    fromYear = years [fromYearIndex-1];
                    toMonth = months [toMonthIndex-1];
                    toDay = days [toDayIndex-1];
                    toYear = years [toYearIndex-1];

                    DateCheck fromDateCheck =new DateCheck (fromDay, fromMonth, fromYear);
                    DateCheck toDateCheck =new DateCheck (toDay, toMonth, toYear);
                    if (!fromDateCheck.isValidDate()){
                        txtboxDisplay.setText("Error: Invalid date.");
                    } else if (!toDateCheck.isValidDate()){
                        txtboxDisplay.setText("Error: Invalid date.");
                    } else if (!fromDateCheck.areDatesInOrder(toDay, toMonth, toYear)){
                        txtboxDisplay.setText("Error: To date cannot be before the from date.");
                    } else {

                        name = firstNameTextField.getText();
                        contact = emailTextField.getText();
                        propertyID = txtPropertyID.getText();

                        fromDateCheck.rentDue();
                        dueDay = fromDateCheck.getDueDay();
                        dueMonth = fromDateCheck.getDueMonth();
                        dueYear = fromDateCheck.getDueYear();

                        //Check if property is available
                        //If so put all the info in the database

                        rentalID = "default";
                        for (int i = 0; i < 3; ++i) {
                            rentalID = rentalID + propertyID.charAt(i + 4);
                        }
                        for (int i = 0; i < 3; ++i) {
                            rentalID = rentalID + name.charAt(i);
                        }

                        code = 1;
                    }
                }*/

            }
        }
        );
        //add single house button
        addSingleHouseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                try {
                    result = tenantQueries.addSingleHouse(
                            SHpropertyTypeTextField.getText(),
                            SHnumBedsTextField.getText(),
                            SHnumBathsTextField.getText(),
                            hasGarage(),
                            hasFrontYard(),
                            hasBackYard(),
                            SHleaseLengthTextField.getText(),
                            SHmonthlyRentTextField.getText(),
                            SHmaxTenantsTextField.getText(),
                            SHtenantIDTextField.getText(),
                            SHallTenantIDTextField.getText(),
                            SHleaseStartDate(),
                            SHleaseEndDate(),
                            SHrentDueDate());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                if (result == 1) {
                    txtboxDisplay.setText("SingleHouse Added, New SingleHouse successfully added.");
                }
                else {
                    txtboxDisplay.setText("SingleHouse Not Added, Unable to add SingleHouse.");
                }
            }
        });

        addApartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                try {
                    result = tenantQueries.addApartment(
                            apartmentPropertyTypeTextField.getText(),
                            apartmentUnitNumberTextField.getText(),
                            apartmentNumBedsTextField.getText(),
                            apartmentNumBathsTextField.getText(),
                            hasCommonArea(),
                            hasUtilityRoom(),
                            hasFitnessCenter(),
                            hasReservedParking(),
                            apartmentLeaseLengthTextField.getText(),
                            apartmentMonthlyRentTextField.getText(),
                            apartmentMaxTenantsTextField.getText(),
                            apartmentPrimaryTenantTextField.getText(),
                            apartmentAllTenantsTextField.getText(),
                            AleaseStartDate(),
                            AleaseEndDate(),
                            ArentDueDate());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                if (result == 1){
                    txtboxDisplay.setText("Apartment Added, New Apartment successfully added.");
                }
                else{
                    txtboxDisplay.setText("Apartment Not Added, Unable to add Apartment.");
                }
            }
        });

        //remove tenant button
        removeTenantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                try {
                    result = tenantQueries.removeTenant(removeTenantIDTextField.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                if (result == 1) {
                    txtboxDisplay.setText("Entry Removed, The entry successfully removed.");
                } else {
                    txtboxDisplay.setText("Entry Not Removed, Unable to remove entry.");
                }
            }
        });

        //Delete Button
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                try {
                    result = tenantQueries.removeTenant(removeTenantIDTextField.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                if (result == 1) {
                    txtboxDisplay.setText("Entry Removed, The entry successfully removed.");
                }
                else {
                    txtboxDisplay.setText("Entry Not Removed, Unable to remove entry.");
                }
            }
        }
        );

        //browse all button

        //Update Button
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtRentalIDUpdate.getText().equals("Renter ID")
                        || txtRentalIDUpdate.getText().isEmpty()){
                    txtboxDisplay.setText("Error: Please provide a renter ID.");
                } else if ((txtNameUpdate.getText().equals("Name")
                        || txtNameUpdate.getText().isEmpty()) &&
                        (txtContactUpdate.getText().equals("Email/Phone")
                                || txtContactUpdate.getText().isEmpty())){
                    txtboxDisplay.setText("Error: Please fill out what you would like to change.");
                } else {
                    txtboxDisplay.setText("");
                    updateRentalID = txtRentalIDUpdate.getText();
                    if(!txtNameUpdate.getText().equals("Name")
                            && !txtNameUpdate.getText().isEmpty()){
                        name = txtNameUpdate.getText();
                        //Make sure they're a valid renter
                        //Update the info in the database
                        code = 3;
                    }
                    if(!txtContactUpdate.getText().equals("Email/Phone")
                            &&!txtContactUpdate.getText().isEmpty()){
                        contact = txtContactUpdate.getText();

                        code = 4;
                    }
                }
            }
        }
        );

        //Check Options Button
        btnOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( (!singleHouseRadioButton.isSelected() && !RBtnApartment.isSelected() && !RBtnVacation.isSelected())
                        || choiceFromMonthOptions.getSelectedIndex()==0
                        || choiceFromMonthOptions.getSelectedIndex()==-1
                        || SHchoiceFromDay.getSelectedIndex()==0
                        || SHchoiceFromDay.getSelectedIndex()==-1
                        || choiceFromYearOptions.getSelectedIndex()==0
                        || choiceFromYearOptions.getSelectedIndex()==-1
                        || choiceToMonthOptions.getSelectedIndex()==0
                        || choiceToMonthOptions.getSelectedIndex()==-1
                        || choiceToDayOptions.getSelectedIndex()==0
                        || choiceToDayOptions.getSelectedIndex()==-1
                        || choiceToYearOptions.getSelectedIndex()==0
                        || choiceToYearOptions.getSelectedIndex()==-1){

                    txtboxDisplay.setText("Error: Please fill out all applicable fields.");
                } else {
                    DateCheck fromDateOptionsCheck =new DateCheck (checkFromDay, checkFromMonth, checkFromYear);
                    DateCheck toDateOptionsCheck =new DateCheck (checkToDay, checkToMonth, checkToYear);
                    if (!fromDateOptionsCheck.isValidDate()){
                        txtboxDisplay.setText("Error: Invalid date.");
                    } else if (!toDateOptionsCheck.isValidDate()){
                        txtboxDisplay.setText("Error: Invalid date.");
                    } else if (!fromDateOptionsCheck.areDatesInOrder(toDay, toMonth, toYear)){
                        txtboxDisplay.setText("Error: To date cannot be before the from date.");
                    } else {

                        if (singleHouseRadioButton.isSelected()) {
                            singleHouse = true;
                        }
                        if (RBtnApartment.isSelected()) {
                            apartment = true;
                        }
                        if (RBtnVacation.isSelected()) {
                            vacationRental = true;
                        }
                        checkFromMonthIndex = choiceFromMonthOptions.getSelectedIndex();
                        checkFromDayIndex = SHchoiceFromDay.getSelectedIndex();
                        checkFromYearIndex = choiceFromYearOptions.getSelectedIndex();
                        checkToMonthIndex = choiceToMonthOptions.getSelectedIndex();
                        checkToDayIndex = choiceToDayOptions.getSelectedIndex();
                        checkToYearIndex = choiceToYearOptions.getSelectedIndex();

                        checkFromMonth = months [checkFromMonthIndex-1];
                        checkFromDay = days [checkFromDayIndex-1];
                        checkFromYear = years [checkFromYearIndex-1];
                        checkToMonth = months [checkToMonthIndex-1];
                        checkToDay = days [checkToDayIndex-1];
                        checkToYear = years [checkToYearIndex-1];

                        location = (String) choiceLocation.getSelectedItem();

                        code = 5;

                        txtboxDisplay.setText("Available Locations: \n");
                        //Display available locations from database
                    }
                }
            }
        });

        //Pay Button
        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtRentalIDRent.getText().equals("Renter ID")
                        || txtRentalIDRent.getText().isEmpty()
                        || txtAmountPaid.getText().equals("Amount Paid")
                        || txtAmountPaid.getText().isEmpty()){

                    txtboxDisplay.setText("Error: Please fill out all applicable fields.");
                } else {
                    try{
                        //make sure the renter is valid and set the amountDue to the rent they owe
                        amountPaid = Double.parseDouble(txtAmountPaid.getText());
                        int currentDay = Calendar.getInstance().get(Calendar.DATE);
                        int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        DateCheck createDueDays = new DateCheck(currentDay, currentMonth, currentYear);
                        createDueDays.rentDue();
                        dueMonth = createDueDays.getDueMonth();
                        dueDay = createDueDays.getDueDay();
                        dueYear = createDueDays.getDueYear();

                        code = 6;
                        while (!isDone){ }
                        amountDue = amountDue-amountPaid;
                        isFinished = true;

                        txtboxDisplay.setText("Renter " + txtRentalIDRent.getText() + " has paid $" + amountPaid + "\nNew amount due is $" + amountDue);
                    } catch (Exception e1){
                        txtboxDisplay.setText("Error: Amount paid must be a number.");
                    }
                }
            }
        });

        //Check Renter Info Button
        btnCheckInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtRentalIDRent.getText().equals("Renter ID")
                        || txtRentalIDRent.getText().isEmpty()){
                    txtboxDisplay.setText("Error: Please provide a renter ID.");
                } else{
                    //make sure they're a valid renter
                    //If so set the name and all that jazz to the stuff in the database
                    rentalIDRentCheck = txtRentalIDRent.getText();
                    isDone = false;
                    code = 7;

                    while (!isDone){ }
                    DateCheck fromDateCheck =new DateCheck (fromDay, fromMonth, fromYear);
                    DateCheck toDateCheck =new DateCheck (toDay, toMonth, toYear);

                    txtboxDisplay.setText("Renter ID: " + rentalIDRentCheck +
                            "\nName: " + name +
                            "\nContact: " + contact +
                            "\nProperty: " + propertyID +
                            "\nAmount Due: $" + amountDue +
                            "\nRenting from " + fromDateCheck.getFormattedDate() +
                            " to " + toDateCheck.getFormattedDate());
                }
            }
        });

        // Check Rent Due Button
        btnCheckRentDue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtboxDisplay.setText("");

                DateCheck rentDue = new DateCheck(dueDay, dueMonth, dueYear);
                if (rentDue.isRentDue()) {
                    String billingStatement = rentDue.generateRentText();
                    billingStatement = rentalID + billingStatement + "They owe " + amountDue + ". Their contact is " + contact;

                    try {
                        String fileName = rentalID + "BillingStatement.txt";
                        File newFile = new File (fileName);
                        FileWriter out = new FileWriter(fileName);
                        out.write(billingStatement);
                        out.close();
                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        }
    }
});
}

    public int GetCode (){
        return code;
    }

    public void setText (String text){
        txtboxDisplay.setText(text);
    }

    public String getText (){
        return txtboxDisplay.getText();
    }

    public String hasGarage(){
        return String.valueOf(garageRadioButton.isSelected());
    }

    public String hasFrontYard(){
        return String.valueOf(frontYardRadioButton.isSelected());
    }

    public String hasBackYard(){
        return String.valueOf(backYardRadioButton.isSelected());
    }

    public String hasCommonArea(){
        return String.valueOf(commonAreaRadioButton.isSelected());
    }

    public String hasUtilityRoom(){
        return String.valueOf(utilityRoomRadioButton.isSelected());
    }

    public String hasFitnessCenter(){
        return String.valueOf(fitnessCenterRadioButton.isSelected());
    }

    public String hasReservedParking(){
        return String.valueOf(reservedParkingRadioButton.isSelected());
    }

    public String SHleaseStartDate() {
        fromMonthIndex = SHchoiceFromMonth.getSelectedIndex();
        fromDayIndex = SHchoiceFromDay.getSelectedIndex();
        fromYearIndex = SHchoiceFromYear.getSelectedIndex();

        fromMonth = months[fromMonthIndex - 1];
        fromDay = days[fromDayIndex - 1];
        fromYear = years[fromYearIndex - 1];

        DateCheck fromDateCheck = new DateCheck(fromDay, fromMonth, fromYear);
        if (!fromDateCheck.isValidDate()) {
            txtboxDisplay.setText("Error: Invalid date.");
        }

        return fromDateCheck.getFormattedDate();
    }

    public String SHleaseEndDate(){
        toMonthIndex = SHchoiceToMonth.getSelectedIndex();
        toDayIndex = SHchoiceToDay.getSelectedIndex();
        toYearIndex = SHchoiceToYear.getSelectedIndex();

        toMonth = months [toMonthIndex-1];
        toDay = days [toDayIndex-1];
        toYear = years [toYearIndex-1];

        DateCheck toDateCheck =new DateCheck (toDay, toMonth, toYear);
        if (!toDateCheck.isValidDate()) {
            txtboxDisplay.setText("Error: Invalid date.");
        }

        return toDateCheck.getFormattedDate();
    }

    public String SHrentDueDate(){
        String month = String.valueOf(SHrentDueMonth);
        String day = String.valueOf(SHrentDueDay);
        String year = String.valueOf(SHrentDueYear);
        return month+"/"+day+"/"+year;
    }

    public String AleaseStartDate(){
        String month = String.valueOf(AfromDayStart);
        String day = String.valueOf(AfromDayStart);
        String year = String.valueOf(AfromYearStart);
        return month+"/"+day+"/"+year;
    }

    public String AleaseEndDate(){
        String month = String.valueOf(AtoMonthEnd);
        String day = String.valueOf(AtoDayEnd);
        String year = String.valueOf(AtoYearEnd);
        return month+"/"+day+"/"+year;
    }

    public String ArentDueDate(){
        String month = String.valueOf(ArentDueMonth);
        String day = String.valueOf(ArentDueDay);
        String year = String.valueOf(ArentDueYear);
        return month+"/"+day+"/"+year;
    }

    public String VleaseStartDate(){
        String month = String.valueOf(VfromDayStart);
        String day = String.valueOf(VfromDayStart);
        String year = String.valueOf(VfromYearStart);
        return month+"/"+day+"/"+year;
    }

    public String VleaseEndDate(){
        String month = String.valueOf(VtoMonthEnd);
        String day = String.valueOf(VtoDayEnd);
        String year = String.valueOf(VtoYearEnd);
        return month+"/"+day+"/"+year;
    }

    public String VrentDueDate(){
        String month = String.valueOf(VrentDueMonth);
        String day = String.valueOf(VrentDueDay);
        String year = String.valueOf(VrentDueYear);
        return month+"/"+day+"/"+year;
    }


}