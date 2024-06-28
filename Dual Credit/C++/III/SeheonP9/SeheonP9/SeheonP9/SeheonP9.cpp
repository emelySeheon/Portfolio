//Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Date: 4/21/21
//Program: SeheonP9

#include <iostream>
#include <fstream>
#include <vector>
#include <sstream>
#include "Functions.h"
#include "StudentUser.h"
using namespace std;

int main()
{
    //Opening log file
    ofstream writer;
    writer.open("SeheonP9Log.txt");
    bool isOpen = false;
    if (writer.is_open()) {
        isOpen = true;
    }

    //header
    cout << WriteHeader();
    if (isOpen) {
        writer << WriteHeader();
    }

    //Getting the users
    int number = HowManyUsers();
    vector<string> names;
    string inputName;

    //Getting the names
    for (int i = 1; i <= number; ++i) {
        cout << "\nWhat is student #" << i << "'s name? ";
        if (isOpen) {
            writer << "\nWhat is student #" << i << "'s name? ";
        }
        cin >> inputName;
        writer << endl << inputName;
        names.push_back(inputName);
    }

    vector<StudentUser> students;

    //Setting up the manager

    stringstream ss1;
    stringstream ss2;
    StudentUser manager;
    manager.SetName("Manager");

    //Getting the first display info
    ss1 << "\nUser name: " << manager.GetName();
    ss1 << "\nTotal users: " << manager.GetReferenceCount();
    ss1 << "\nResources: ";
    for (int i = 0; i < manager.Size(); ++i) {
        ss1 << endl << manager.GetResourceAt(i);
    }

    cout << ss1.str();
    writer << ss1.str();

    //adding the titles
    cout << "\nAdding titles to " << manager.GetName() << "'s resources.";

    string titleNum1 = "How to Math";
    string& title1 = titleNum1;
    manager.PushBack(title1);

    string titleNum2 = "How to English";
    string& title2 = titleNum2;
    manager.PushBack(title2);

    string titleNum3 = "How to Science";
    string& title3 = titleNum3;
    manager.PushBack(title3);

    string titleNum4 = "How to History";
    string& title4 = titleNum4;
    manager.PushBack(title4);

    //Getting the second display info
    ss2 << "\nUser name: " << manager.GetName();
    ss2 << "\nTotal users: " << manager.GetReferenceCount();
    ss2 << "\nResources: ";
    for (int i = 0; i < manager.Size(); ++i) {
        ss2 << endl << manager.GetResourceAt(i);
    }

    cout << ss2.str();
    writer << ss2.str();
    students.push_back(manager);

    //Setting up the students
    StudentUser user;
    for (int i = 1; i <= number; ++i) {
        students.push_back(user);
        students[i].SetName(names[i-1]);

       /* students[i - 1].PushBack(title1);
        students[i - 1].PushBack(title2);
        students[i - 1].PushBack(title3);
        students[i - 1].PushBack(title4);*/
    }

    int choice;
    int stuNum;
    bool keepGoing = true;

    //Printing the menu to the log
    if (isOpen) {
        writer << "\n\n1. Add title to the back.";
        writer << "\n2. Remove title from the back.";
        writer << "\n3. Get first title.";
        writer << "\n4. Get last title.";
        writer << "\n5. Check size.";
        writer << "\n6. Get resource at a position.";
        writer << "\n7. Get reference count.";
        writer << "\n8. Set name.";
        writer << "\n10. Display situation.";
        writer << "\n11. Quit.";
    }

    //Test options
    while (keepGoing == true) {

        cout << "\n\n1. Add title to the back.";
        cout << "\n2. Remove title from the back.";
        cout << "\n3. Get first title.";
        cout << "\n4. Get last title.";
        cout << "\n5. Check size.";
        cout << "\n6. Get resource at a position.";
        cout << "\n7. Get reference count.";
        cout << "\n8. Set name.";
        cout << "\n9. Get name.";
        cout << "\n10. Display situation.";
        cout << "\n11. Quit.";

        cout << "\nPick an option: ";
        if (isOpen) {
            writer << "\nPick an option: ";
        }
        cin >> choice;
        if (isOpen) {
            writer << endl << choice;
        }
        cout << "\nEnter student number (manager is student number 0): ";
        if (isOpen) {
            writer << "\nEnter student number (manager is student number 0): ";
        }
        cin >> stuNum;
        if (isOpen) {
            writer << endl << stuNum;
        }

        if (stuNum > students.size()) {
            choice = 12;
        }

        switch (choice) {
        case 1: {

            string titleName;
            cout << "\nEnter title name: ";
            cin >> titleName;
            if (isOpen) {
                writer << "\nEnter title name: " << endl << titleName;
            }
            string& tn = titleName;
            students[stuNum].PushBack(tn);

            break;
        }

        case 2: {

            students[stuNum].PopBack();

            break;
        }

        case 3: {

            if (!students[stuNum].IsEmpty()) {
                cout << endl << students[stuNum].GetFirstResource();
                if (isOpen) {
                    writer << endl << students[stuNum].GetFirstResource();
                }
            }
            else {
                cout << endl << "Vector is empty.";
                if (isOpen) {
                    writer << endl << "Vector is empty.";
                }
            }

            break;
        }

        case 4: {

            if (!students[stuNum].IsEmpty()) {
                cout << endl << students[stuNum].GetLastResource();
                if (isOpen) {
                    writer << endl << students[stuNum].GetLastResource();
                }
            }
            else {
                cout << endl << "Vector is empty.";
                if (isOpen) {
                    writer << endl << "Vector is empty.";
                }
            }

            break;
        }

        case 5: {

            cout << endl << students[stuNum].Size();
            if (isOpen) {
                writer << endl << students[stuNum].Size();
            }

            break;
        }

        case 6: {

            int pos;
            cout << "\nWhich position? ";
            if (isOpen) {
                writer << "\nWhich position? ";
            }
            cin >> pos;
            if (isOpen) {
                writer << endl << pos;
            }
            if (!students[stuNum].IsEmpty() && pos < students[stuNum].Size() && pos > -1) {
                cout << endl << students[stuNum].GetResourceAt(pos);
                    if (isOpen) {
                        writer << endl << students[stuNum].GetResourceAt(pos);
                    }
            }
            else {
                cout << endl << "Invalid position.";
                    if (isOpen) {
                        writer << endl << "Invalid position.";
                    }
            }

            break;
        }

        case 7: {

            cout << endl << students[stuNum].GetReferenceCount();
            if (isOpen) {
                writer << endl << students[stuNum].GetReferenceCount();
            }

            break;
        }

        case 8: {

            string newName;
            cout << "\nEnter the new name: ";
            if (isOpen) {
                writer << "\nEnter the new name: ";
            }
            cin >> newName;
            if (isOpen) {
                writer << endl << newName;
            }
            students[stuNum].SetName(newName);

            break;
        }

        case 9: {

            cout << endl << students[stuNum].GetName();
            if (isOpen) {
                writer << endl << students[stuNum].GetName();
            }

            break;
        }

        case 10: {
            stringstream ss4;
            ss4 << "\nUser name: " << students[stuNum].GetName();
            ss4 << "\nTotal users: " << students[stuNum].GetReferenceCount();
            ss4 << "\nResources: ";
            for (int i = 0; i < students[stuNum].Size(); ++i) {
                ss4 << endl << students[stuNum].GetResourceAt(i);
            }

            cout << ss4.str();
            if (isOpen) {
                writer << ss4.str();
            }

            break;
        }

        case 11: {
            keepGoing = false;
            cout << "\nGoodbye!";
            if (isOpen) {
                writer << "\nGoodbye!";
            }
            break;
        }

        default: {
            cout << "\nInvalid choice.";
            if (isOpen) {
                writer << "\nInvalid choice.";
            }
            break;
        }
        }

        if (choice != 10 && !choice > 11 && !choice < 1) {
            stringstream ss3;
            ss3 << "\nUser name: " << students[stuNum].GetName();
            ss3 << "\nTotal users: " << students[stuNum].GetReferenceCount();
            ss3 << "\nResources: ";
            for (int i = 0; i < students[stuNum].Size(); ++i) {
                ss3 << endl << students[stuNum].GetResourceAt(i);
            }

            if (isOpen) {
                writer << ss3.str();
            }
        }
    }

    //deleting
    for (int i = 0; i < number;++i) {
        students[stuNum].~StudentUser();
    }

    return 0;
}