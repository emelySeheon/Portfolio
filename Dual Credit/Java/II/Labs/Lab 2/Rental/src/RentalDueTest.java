//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 10/8/20
//Course Title: CSCI 2251
//Program Description: This program is a program to calculate and display the old and current rents of different properties.

public class RentalDueTest {

    public static void main(String[] args) {
        RentalProperty properties;
        properties = new RentalProperty();
        properties.PrintOldInfo();
        for (int i = 0; i<6;++i) {
            boolean isSingleFam = properties.UpdateRent(i);
            if (isSingleFam) {
                properties = new SingleFamilyRental();
                properties.UpdateRent(i);
            } else {
                properties = new ApartmentRental();
                properties.UpdateRent(i);
            }
            properties = new RentalProperty();
        }
        properties.PrintNewInfo();
        properties.Print();
    }
}

interface Payment{
    public void PrintOldInfo();
    public boolean UpdateRent(int i);
    public void PrintNewInfo();
    public void Print();
}

class RentalProperty implements Payment{

    int[] propertyNum = {1,2,3,4,5,6};
    char[] AOrS = {'S','A','S','S','A','A'};
    String[] rentalID = {"SABQ138","AABQ205","SABQ127","SABQ126","AABQ302","AABQ201",};
    int[] bedrooms = {3,2,1,2,2,1};
    double[] oldPrice = {1400.00,900.00,900.00,1200.00,850.00,600.00};
    double[] newPrice = {1400.00,900.00,900.00,1200.00,850.00,600.00};

    public void PrintOldInfo() {
        System.out.println("\nOld:");
        for (int i = 0; i<6;++i) {
            System.out.println(+propertyNum[i] + "     " + AOrS[i] + "     " + rentalID[i] + "     " + bedrooms[i] + "     " + oldPrice[i]);
        }
    }

    public void PrintNewInfo() {
        System.out.println("\nNew:");
        for (int i = 0; i<6;++i) {
            System.out.println(+propertyNum[i] + "     " + AOrS[i] + "     " + rentalID[i] + "     " + bedrooms[i] + "     " + newPrice[i]);
        }
    }

    public boolean UpdateRent(int i) {
        if (AOrS[i] == 'S'){
            return true;
        }else{
            return false;
        }
    }

    public void Print() {
        System.out.println("\n\nSingle-Family Rental Summary: ");
        System.out.println("House ID Number            Rental Due");
        System.out.println("===============            ==========");
        System.out.println("   "+rentalID[3]+"                  $"+newPrice[3]);
        System.out.println("   "+rentalID[2]+"                  $"+newPrice[2]);
        System.out.println("   "+rentalID[0]+"                  $"+newPrice[0]);

        System.out.println("\n\nApartment Rental Summary: ");
        System.out.println("Apartment ID No.           Rental Due");
        System.out.println("================           ===========");
        System.out.println("   "+rentalID[5]+"                  $"+newPrice[5]);
        System.out.println("   "+rentalID[1]+"                  $"+newPrice[1]);
        System.out.println("   "+rentalID[4]+"                  $"+newPrice[4]);
    }

}

class SingleFamilyRental extends RentalProperty{

    public boolean UpdateRent(int i) {
            newPrice[i]=(oldPrice[i]*0.04)+oldPrice[i];
        return false;
    }
}

class ApartmentRental extends RentalProperty{

    public boolean UpdateRent(int i) {
            newPrice[i]=(oldPrice[i]*0.08)+oldPrice[i];
        return false;
    }
}