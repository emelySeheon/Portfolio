import java.util.Calendar;

public class DateCheck {
    private int givenDay;
    private int givenMonth;
    private int givenYear;
    private int dueDay;
    private int dueMonth;
    private int dueYear;
    private boolean leapYear;
    private String months[] = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //Constructor
    public DateCheck (int day, int month, int year){
        givenDay = day;
        givenMonth = month;
        givenYear = year;
    }

    //Check If leap year
    private boolean isLeapYear (){
        if(givenYear%4 == 0){
            return true;
        }

        return false;
    }

    //Make sure the dates exist
    public boolean isValidDate (){
        leapYear = isLeapYear();

        if (givenMonth == 2){
            if (leapYear){
                if (givenDay > 29){
                    return false;
                }
            } else{
                if (givenDay > 28){
                    return false;
                }
            }
        } else if (givenMonth == 4 || givenMonth == 6 || givenMonth == 9 || givenMonth == 11){
            if (givenDay > 30){
                return false;
            }
        }

        return true;
    }

    //Make sure the end date is not before the begin date
    public boolean areDatesInOrder (int secondDay, int secondMonth, int secondYear){
        if (secondYear<givenYear){
            return false;
        } else if (secondYear>givenYear){
            return true;
        } else if (secondMonth < givenMonth){
            return false;
        } else if (secondMonth>givenMonth){
            return true;
        } else if (secondDay < givenDay){
            return false;
        }

        return true;
    }

    //Get formatted Date String.
    public String getFormattedDate (){
        return months[givenMonth-1] + " " + givenDay + ", " + givenYear;
    }

    //Figure out when the rent is due
    public void rentDue (){
        leapYear = isLeapYear();

         if (givenMonth == 12){
             dueYear = givenYear+1;
         } else {
             dueYear = givenYear;
        }

         if (givenMonth == 1){
             if (leapYear){
                 if (givenDay>29){
                     dueMonth = 3;
                     dueDay = givenDay - 29;
                 }
             } else {
                 if (givenDay>28){
                     dueMonth = 3;
                     dueDay = givenDay - 28;
                 }
             }
         } else if (givenMonth == 3 || givenMonth == 5 || givenMonth == 7 || givenMonth == 8 || givenMonth == 10 || givenMonth == 12){
             if (givenDay == 31){
                 dueMonth = givenMonth + 2;
                 dueDay = 1;
             }
         } else {
             dueMonth = givenMonth + 1;
             dueDay = givenDay;
         }
    }

    public int getDueDay (){return dueDay;}
    public int getDueMonth (){return dueMonth;}
    public int getDueYear (){return dueYear;}

    //check if rent is past due
    public boolean isRentDue (){
        int systemMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        int systemYear = Calendar.getInstance().get(Calendar.YEAR);
        int systemDay = Calendar.getInstance().get(Calendar.DATE);

        if (systemYear > givenYear){
            return true;
        } else if (systemMonth > givenMonth){
            return true;
        } else if (systemDay >= givenDay){
            return true;
        }

        return false;
    }
}
