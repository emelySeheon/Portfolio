/*Name: Emely Seheon
* Email: eseheon1@cnm.edu
* Program: SeheonP1
*/

#include <iostream>
#include "Person.h";
#include "Date.h";
using namespace std;

int main()
{
    //Header
    cout << "Name: Emely Seheon" << endl
        << "Program: SeheonP1" << endl
        << "Objective: to save a person in the Person class." << endl;

    //test info
    int d;
    int m;
    int y;
    string name;
    string goAgain = "y";

    while (goAgain == "y") {
        
        //Getting info from the user
        cout << endl << "Enter year of birth: ";
        cin >> y;
        cout << "Enter month of birth: ";
        cin >> m;
        cout << "Enter day of birth: ";
        cin >> d;
        cout << "Enter name: ";
        cin >> name;

        //First overloded constructer
        Date d1(m, d, y, name);
        Person p1(name, d1);

        cout << endl << "************** First Overloaded Constructor **************";
        cout << endl << p1.GetName();
        cout << p1.GetNameAge();
        cout << p1.GetFullDesc();
        cout << endl << p1.GetBirthDay().GetFormattedDate();

        //secnd overloaded constructor
        Person p2(name, m, d, y);

        cout << endl << endl << "************** Second Overloaded Constructor **************";
        cout << endl << p2.GetName();
        cout << p2.GetNameAge();
        cout << p2.GetFullDesc();
        cout << endl << p2.GetBirthDay().GetFormattedDate();

        //Go again
        cout << endl << "\nWould you like to go again? (y/n) ";
        cin >> goAgain;
    }
}

