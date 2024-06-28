// Name: Emely Seheon eseheon1@cnm.edu
// Date: 2/4/21
// File: SeheonP2

#include "Person.h"
#include "PersonGen.h";
#include <iostream>

using namespace std;

int main()
{
    cout << "Name: Emely Seheon (eseheon1@cnm.edu)\n";
    cout << "Program: SeheonP2 Person Generator\n";
    cout << "Program Objective: To generate random people objects.\n\n";


    PersonGen* pg = new PersonGen;
    pg->CreateFile("Test15.txt", 15);

    //1. Array of Persons
    cout << "\nArray One\n";
    Person arrOne[10];
    for (int i = 0; i < 10; ++i) {
        arrOne[i] = pg->GetPerson();
        cout << i << ": " << arrOne[i].GetFullDesc() << "\n";
    }

    //2. New Array of Persons
    cout << "\nArray Two\n";
  
    Person* arrTwo = nullptr;
    try {
        arrTwo = new Person[10];
        for (int i = 0; i < 10; ++i) {
            arrTwo[i] = pg->GetPerson();
            cout << i << ": " << arrTwo[i].GetFullDesc() << "\n";
        }
    }
    catch (exception const& ex) {
        cout << "Error";
    }

    //3. Array of New Persons
    cout << "\nArray Three\n";
    Person* arrThree[10];
    for (int i = 0; i < 10; ++i) {
        arrThree[i] = pg->GetNewPerson();
        cout << i << ": " << arrThree[i]->GetFullDesc() << "\n";
    }

    //4. New Array of New Persons
    cout << "\nArray Four\n";
    Person** arrFour = nullptr;
    try {
        arrFour = new Person * [10];
        for (int i = 0; i < 10; ++i) {
            arrFour[i] = pg->GetNewPerson();
            cout << i << ": " << arrFour[i]->GetFullDesc() << "\n";
        }
    }
    catch (exception const& ex) {
        cout << "error";
    }

    //Deleting Arrays
    if (arrTwo != nullptr) {
        delete[] arrTwo;
        arrTwo = nullptr;
    }
    if (arrFour != nullptr) {
        for (int i = 0; i < 10;++i) {
            delete arrFour[i];
        }
        delete[] arrFour;
        arrFour = nullptr;
    }
    for (int i = 0; i < 10;++i) {
        delete arrThree[i];
    }

    //5. Using GetPerson to create 5 Person Objects
    cout << "\nUsing GetPerson\n";
    for (int i = 0; i < 5; ++i) {
        cout << i << ": " << pg->GetPerson().GetFullDesc() << "\n";
    }

    //7. Grading Spec
    pg->CreateFile("Test10.txt", 10);
    pg->UseFile("PersonGenTest15.txt");
    pg->CreateFile("Test20.txt", 20);
    pg->UseFile("Test10.txt");
    cout << "\nUsing Test10.txt\n";
    for (int i = 0; i < 15; ++i) {
        cout << i << ": " << pg->GetPerson().GetFullDesc() << "\n";
    }
    cout << "\nUsing GetPerson and GetNewPerson\n";
    for (int i = 0; i < 5; ++i) {
        cout << i << ": " << pg->GetPerson().GetFullDesc() << "\n";
    }
    for (int i = 0; i < 5; ++i) {
        cout << i << ": " << pg->GetNewPerson()->GetFullDesc() << "\n";
    }
    for (int i = 0; i < 5; ++i) {
        cout << i << ": " << pg->GetPerson().GetFullDesc() << "\n";
    }
    for (int i = 0; i < 5; ++i) {
        cout << i << ": " << pg->GetNewPerson()->GetFullDesc() << "\n";
    }
    pg->UseFile("DNE.txt");
    pg->CreateFile("DNETest.txt", 10);
}