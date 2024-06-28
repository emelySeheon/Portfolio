//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Program: SeheonP5

#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include "ListMgr.h"
#include "ScriptReader.h"
#include ".\PersonGen\Date.h"
#include ".\PersonGen\Person.h"
#include ".\PersonGen\PersonGen.h"
using namespace std;

int main()
{
    //header
    cout << "Name: Emely Seheon\n";
    cout << "Program: SeheonP5 Linked Lists\n";
    cout << "Objective: To create a linked list of person objects.\n";

    //Create the menu
    vector<string> menu = {
        "1. Show list",
        "2. Add to front of list",
        "3. Add to back of list",
        "4. Remove from front",
        "5. Remove from back",
        "6. Remove selected item",
        "7. Front",
        "8. Back",
        "9. Erase",
        "10. Clear",
        "11. Size",
        "12. Quit the program"
    };

    //Create a new ListMgr object
    ListMgr* lm;
    try {
        lm = new ListMgr;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //create the personGen  object
    PersonGen* pg;
    try {
        pg = new PersonGen;
    }
    catch (bad_alloc ba) {
        return 1;
    }

    //Create the script reader
    string file = "P5TestScript.txt";
    ScriptReader sr(file);

    //open the log file
    pg->UseFile("P5LinkedListPeopleNov6.txt");

    bool isOpen = true;
    ofstream writer;
    writer.open("log.txt");
    if (!writer.is_open()) {
        cout << "\nUnable to open log file.\n";
        isOpen = false;
    }

    writer << "Name: Emely Seheon\n";
    writer << "Program: SeheonP5 Linked Lists\n";
    writer << "Objective: To create a linked list of person objects.\n";
    writer << "Date: 3/16/21\n";

    int choice;
    bool goAgain = true;
    int eraser;
    string toDelete;
    Person* persFront;
    Person* persBack;
    int size;
    int option;

    //for testing purposes, using a script or not
    cout << "\nWould you like to use a menu or a script? Enter 1 for script and 2 for menu. ";
    cin >> option;

    for (int i = 0; i < 12; ++i) {
        if (isOpen) {
            writer << endl << menu[i];
        }
    }

    while (goAgain == true) {
        cout << endl;
        if (isOpen) {
            writer << endl;
        }

        //displaying menu
        for (int i = 0; i < 12; ++i) {
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

        //switch cases
        switch (choice) {
        case 1:
            lm->show();
            if (isOpen) {
                writer << lm->showString();
            }
            break;

        case 2:
            lm->push_front(pg->GetNewPerson());
            cout << "\nPerson added to the front.";
            if (isOpen) {
                writer << "\nPerson added to the front.";
            }
            break;

        case 3:
            lm->push_back(pg->GetNewPerson());
            cout << "\nPerson added to the back.";
            if (isOpen) {
                writer << "\nPerson added to the back.";
            }
            break;

        case 4:
            lm->pop_front();
            cout << "\nPerson removed from the front.";
            if (isOpen) {
                writer << "\nPerson removed from the front.";
            }
            break;

        case 5:
            lm->pop_back();
            cout << "\nPerson removed from the back.";
            if (isOpen) {
                writer << "\nPerson removed from the back.";
            }
            break;

        case 6:
            if (option == 2) {
                cout << "\nEnter the item you would like to delete. ";
                cin >> toDelete;
            }
            else {
                toDelete = sr.GetNextString();
            }

            if (isOpen) {
                writer << "\nDeleting " << toDelete;
            }
            lm->remove(toDelete);
            cout << "\nPerson(s) deleted if they were in the list.";
            if (isOpen) {
                writer << "\nPerson(s) deleted if they were in the list: " << toDelete;
            }
            break;

        case 7:
            persFront = lm->front();
            if (persFront != nullptr) {
                cout << "\nPointer to " << persFront->GetName() << " saved.";
                if (isOpen) {
                    writer << "\nPointer to " << persFront->GetName() << " saved.";
                }
            }
            break;

        case 8:
            persBack = lm->back();
            if (persBack != nullptr) {
                cout << "\nPointer to " << persBack->GetName() << " saved.";
                if (isOpen) {
                    writer << "\nPointer to " << persBack->GetName() << " saved.";
                }
            }
            break;

        case 9:
            if (option == 2) {
                cout << "\nWhat position would you like to erase? ";
                cin >> eraser;
            }
            else {
                eraser = sr.GetNextInt();
            }
            if (isOpen) {
                writer << "\nErasing position " << eraser;
            }
            lm->erase(eraser);
            cout << "\nPerson at position " << eraser << " has been deleted.";
            if (isOpen) {
                writer << "\nPerson at position " << eraser << " has been deleted.";
            }
            break;

        case 10:
            lm->clear();
            cout << "\nList cleared.";
            if (isOpen) {
                writer << "\nList cleared.";
            }
            break;

        case 11:
            size = lm->size();
            cout << "\nThe size of the list is " << size << ".";
            if (isOpen) {
                writer << "\nThe size of the list is " << size << ".";
            }
            break;

        case 12:
            goAgain = false;
            break;

        default:
            cout << "\nInvalid choice.";
            if (isOpen) {
                writer << "\nInvalid choice.";
            }
        }
        if (choice != 1) {
            if (isOpen) {
                writer << lm->showString();
            }
        }

        cout << endl;
    }
    
    //deleting
    delete pg;
    lm->~ListMgr();

    //close writing file
    writer.close();

    cout << "\nGoodbye!";

    return 0;
}