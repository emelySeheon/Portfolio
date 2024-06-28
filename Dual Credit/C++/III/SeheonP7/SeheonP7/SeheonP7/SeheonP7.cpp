//Name: Emely Seheon
//Date: 4/5/21
//Program: SeheonP7
//Email: eseheon1@cnm.edu

#include <iostream>
#include <iomanip>
#include <stdio.h>
#include <fstream>
#include "HashTable.h"
#include "PersonGen.h"
#include "Person.h"
using namespace std;

int main()
{
    cout << "Name: Emely Seheon" << endl;
    cout << "Email: eseheon1@cnm.edu" << endl;
    cout << "Program: SeheonP7" << endl;
    cout << "Objective: To search Person objects using a hash table" << endl;

    //create HashTable pointer
    HashTable* ht;
    try {
        ht = new HashTable;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //create PersonGen pointer
    PersonGen* pg;
    try {
        pg = new PersonGen;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //Reading the file for the 2000 names
    pg->UseFile("BJ_Persons2000.txt");

    //create a Person*
    Person* pers;
    try {
        pers = new Person;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //putting the persons in the hash table
    for (int i = 0; i < 2000; ++i) {
        pers = pg->GetNewPerson();

        ht->HashFunction(pers);
    }

    //open log file
    ofstream writer;
    bool open = false;
    writer.open("SeheonP7Log.txt");
    if (!writer.is_open()) {
        cout << "\nUnable to open log file.";
    }
    else {
        open = true;
    }
    //write an intro
    cout << "Log File: SeheonP7Log.txt" << endl;
    cout << "Person File: BJ_Persons2000.txt" << endl;
    cout << "Searching File: BJ_42PersonsToFind.txt" << endl << endl;
    cout << "Results:" << endl;
    if (open) {
        writer << "Log File: SeheonP7Log.txt" << endl;
        writer << "Person File: BJ_Persons2000.txt" << endl;
        writer << "Searching File: BJ_42PersonsToFind.txt" << endl << endl;
        writer << "Results:" << endl;
    }

    //making the distribution array
    int distribution[100];
    ht->AnalyzeTable(distribution, 100);

    //Calculating all the fun facts
    int lowest = 2001;
    int highest = 0;
    double average = 0;
    for (int i = 0; i < 100; i++) {
        if (distribution[i] < lowest) {
            lowest = distribution[i];
        }
        if (distribution[i] > highest) {
            highest = distribution[i];
        }
        average = average + distribution[i];
    }
    average = average / 100;

    //Writing the results
    cout << "Minimum List Length: " << lowest;
    cout << "\nMaximum List Length: " << highest;
    cout << "\nAverage List Length: " << average << endl;
    if (open) {
        writer << "Minimum List Length: " << lowest;
        writer << "\nMaximum List Length: " << highest;
        writer << "\nAverage List Length: " << average << endl;
    }

    //Writing the distribution
    double perc = 0;
    for (int i = 0; i < 100; ++i) {
        for (int j = 0; j < 4; ++j) {
            if (i < 10) {
                cout << "[ ";
                if (open) {
                    writer << "[ ";
                }
            }
            else {
                cout << "[";
                if (open) {
                    writer << "[";
                }
            }
            perc = distribution[i] / static_cast<double>(2000);
            perc = perc * 100;
            if (distribution[i] < 10) {
                cout << i << "]  " << distribution[i] << ", " << perc << setprecision(2) << fixed << "%   ";
                if (open) {
                    writer << i << "]  " << distribution[i] << ", " << perc << setprecision(2) << fixed << "%   ";
                }
            }
            else {
                cout << i << "] " << distribution[i] << ", " << perc << setprecision(2) << fixed << "%   ";
                if (open) {
                    writer << i << "] " << distribution[i] << ", " << perc << setprecision(2) << fixed << "%   ";
                }
            }

            i++;
        }
        i--;
        cout << endl;
        if (open) {
            writer << endl;
        }
    }

    //Create a new PersonGen object
    PersonGen* pgg;
    try {
        pgg = new PersonGen;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //Using the 42 person file
    pgg->UseFile("BJ_42PersonsToFind.txt");

    //create a Person* to be searched for
    Person* perss;
    try {
        perss = new Person;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //create a Person* object that is what was found
    Person* persf;
    try {
        persf = new Person;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    string name;
    int m;
    int d;
    int y;
    int index;

    //Searching for the person
    for (int i = 0; i < 42; ++i) {
        //Getting and Setting
        perss = pgg->GetNewPerson();
        persf = perss;

        name = persf->GetName();
        m = persf->GetBirthDay().GetMonth();
        d = persf->GetBirthDay().GetDay();
        y = persf->GetBirthDay().GetYear();
        
        //Calling GetValue
        cout << "\n\nSearching for: " << perss->GetFullDesc();
        if (open) {
            writer << "\n\nSearching for: " << perss->GetFullDesc();
        }
        index = ht->GetValue(name, m, d, y, &persf);

        //Reporting results
        if (index == -1) {
            cout << "\nPerson named " << perss->GetName() << " not found.";
            if (open) {
                writer << "\nPerson named " << perss->GetName() << " not found.";
            }
        }
        else {
            cout << "\nPerson found: " << persf->GetFullDesc();
            if (open) {
                writer << "\nPerson found: " << persf->GetFullDesc();
            }
        }
    }

    //Closing and deleting
    ht->~HashTable();
    pg = nullptr;
    pgg = nullptr;
    pers = nullptr;
    perss = nullptr;
    persf = nullptr;
    delete pg;
    delete pgg;
    delete pers;
    delete perss;
    delete persf;

    if (open) {
        writer.close();
    }

    //Goodbyes
    cout << "\n\nGoodbye!";

    return 0;
}