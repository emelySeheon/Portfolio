//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 4/16/21
//Program: SeheonP8
//Objective: To make a circular queue with unique pointers

#include <iostream>
#include <string>
//#include <cstdlib>
#include <memory>
#include <fstream>
#include "Date.h"
#include "Person.h"
#include "PersonGen.h"
#include "CirQueue.h"
#include "ScriptReader.h"

using namespace std;

int main()
{
    cout << "Name: Emely Seheon";
    cout << "\nEmail: eseheon1@cnm.edu";
    cout << "\nDate: 4/16/21";
    cout << "\nProgram: SeheonP8";
    cout << "\nObjective: To make a circular queue with unique pointers\n";

    //Opening the log
    string logName;
    cout << "\nPlease enter the name of the log file. ";
    cin >> logName;

    bool isOpen = true;
    ofstream writer;
    writer.open(logName);
    if (!writer.is_open()) {
        cout << "\nUnable to open log file.\n";
        isOpen = false;
    }

    writer << "Name: Emely Seheon\n";
    writer << "Program: SeheonP8\n";
    writer << "Objective: To make a circular queue with unique pointers\n";
    writer << "Date: 4/16/21\n";

    //Declare/Initialize the variables
    PersonGen* pg;
    try {
        pg = new PersonGen;
    }
    catch (bad_alloc ba) {
        return 1;
    }
    int option;
    bool goAgain = true;
    CirQueue* cq;
    try {
        cq = new CirQueue;
    }
    catch (bad_alloc ba) {
        return 1;
    }
    int choice;
    string scriptName = "empty";

    //menu
    vector<string> menu = {
        "1. Show who is standing in line",
        "2. Add a person to the end of the line",
        "3. Show who is on the front of the line",
        "4. Show who is in the back of the line",
        "5. Serve(or Pop) the Person pointer at the front of the line",
        "6. Size",
        "7. Quit the Program"
    };

    //Set up the use file
    string useName = "empty";
    cout << "\nPlease enter the Person file you would like to use. ";
    cin >> useName;

    pg->UseFile(useName);

    //for testing purposes, using a script or not
    cout << "\nWould you like to use a menu or a script? Enter 1 for script and 2 for menu. ";
    cin >> option;

    if (option == 1) {
        cout << "\nPlease enter the name of the script file. ";
        cin >> scriptName;
    }
    ScriptReader sr(scriptName);

    //Displaying the menu to the log
    for (int i = 0; i < 7; ++i) {
        if (isOpen) {
            writer << endl << menu[i];
        }
    }

    //Opening the loop and Showing the menu
    while (goAgain == true) {
        cout << endl;
        if (isOpen) {
            writer << endl;
        }

        //displaying menu
        for (int i = 0; i < 7; ++i) {
            cout << menu[i] << endl;
        }
        //getting user input
        if (option == 2) {
            cout << "Choose a menu option (1, 2, 3, etc.) ";
            cin >> choice;
            if (isOpen) {
                writer << "\nOption " << choice << " selected.";
            }
        }
        else {
            choice = sr.GetNextInt();
            if (isOpen) {
                writer << "\nOption " << choice << " selected.";
            }
        }

        //Switch cases
        switch (choice) {
        case 1:
        {
            if (isOpen) {
                cout << endl;
                cq->Show();
                if (isOpen) {
                    writer << endl << cq->ShowString();
                }
            }
            break;
        }

        case 2:
        {
            bool worked;
            unique_ptr<Person> p = unique_ptr<Person>(new Person(pg->GetPerson()));
            worked = cq->Push(move(p));

            if (worked) {
                cout << "\nPerson added to the queue.";
                if (isOpen) {
                    writer << "\nPerson added to the queue.";
                }
            }
            else {
                cout << "\nQueue is too full to add Person.";
                if (isOpen) {
                    writer << "\nQueue is too full to add Person.";
                }
            }

            break;
        }

        case 3:
        {
            string front;
            front = cq->Front();
            cout << "\n" << front;
            if (isOpen) {
                writer << "\n" << front;
            }
            break;
        }

        case 4:
        {
            string back;
            back = cq->Rear();
            cout << "\n" << back;
            if (isOpen) {
                writer << "\n" << back;
            }
            break;
        }

        case 5:
        {
            bool worked = cq->Pop();
            if (worked) {
                cout << "\nPerson removed from the queue.";
                if (isOpen) {
                    writer << "\nPerson removed from the queue.";
                }
            }
            else {
                cout << "\nCan not remove from an empty queue.";
                if (isOpen) {
                    writer << "\nCan not remove from an empty queue.";
                }
            }
            break;
        };

        case 6:
        {
            int size = cq->Size();
            cout << endl << size;
            if (isOpen) {
                writer << endl << size;
            }
            break;
        };

        case 7:
        {
            goAgain = false;
            break;
        };

        default:
        {
            cout << "\nInvalid choice.";
            if (isOpen) {
                writer << "\nInvalid choice.";
            }
        }
        }
        if (choice != 1) {
            if (isOpen) {
                writer << endl << cq->ShowString();
            }
        }
        cout << endl;
    }

    //deleting
    delete pg;
    cq->~CirQueue();

    //close writing file
    writer.close();

    cout << "\nGoodbye!" << endl;

    return 0;
}

