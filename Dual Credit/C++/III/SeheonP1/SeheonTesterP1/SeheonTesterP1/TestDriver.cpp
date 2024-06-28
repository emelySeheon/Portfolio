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
    bool alpha;
    bool beta;

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

        //Default constructor
        Date d1(m, d, y, name);
        Person p;
        cout << endl << "************** Default Constructor **************";
        cout << endl << p.GetName();
        cout << p.GetNameAge();
        cout << p.GetFullDesc();
        cout << endl << p.GetBirthDay().GetFormattedDate();

        p.SetName("Fred");
        p.SetBirthday(d1);

        cout << endl << endl << p.GetName();
        cout << p.GetNameAge();
        cout << p.GetFullDesc();
        cout << endl << p.GetBirthDay().GetFormattedDate();

        p.SetBirthday(2, 2, 2002);

        cout << endl << p.GetFullDesc();
        cout << endl << p.GetBirthDay().GetFormattedDate();

        //First overloaded constructor
        Person p1(name, d1);

        cout << endl << "************** First Overloaded Constructor **************";
        cout << endl << p1.GetName();
        cout << p1.GetNameAge();
        cout << p1.GetFullDesc();
        cout << endl << p1.GetBirthDay().GetFormattedDate();

        //second overloaded constructor
        Person p2(name, m, d, y);

        cout << endl << endl << "************** Second Overloaded Constructor **************";
        cout << endl << p2.GetName();
        cout << p2.GetNameAge();
        cout << p2.GetFullDesc();
        cout << endl << p2.GetBirthDay().GetFormattedDate();

        //Overloaded operators
        alpha = p < p1;
        beta = p > p1;
        cout << endl << alpha << endl << beta;

        //Go again
        cout << endl << "\nWould you like to go again? (y/n) ";
        cin >> goAgain;
    }
}

