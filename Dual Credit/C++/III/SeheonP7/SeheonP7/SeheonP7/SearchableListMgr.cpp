//File: SearchableListMgr.cpp

#include "SearchableListMgr.h"

int SearchableListMgr::IsPresent(string name, int mm, int dd, int yy, Person** pPer)
{
    bool keepGoing = true;
    PersonNode* current = first;
    int counter = 0;
    while (keepGoing) {
        if (current == nullptr) {
            keepGoing = false;
        }
        else {
            if (current->GetPerson()->GetName() == name) {
                if (current->GetPerson()->GetBirthDay().GetDay() == dd && current->GetPerson()->GetBirthDay().GetMonth() == mm && current->GetPerson()->GetBirthDay().GetYear() == yy) {
                    keepGoing = false;
                    *pPer = current->GetPerson();
                }
                else if (current->GetNext() == nullptr) {
                    keepGoing = false;
                    counter = -1;
                }
                else {
                    current = current->GetNext();
                    counter++;
                }
            }
            else if (current->GetNext() == nullptr) {
                keepGoing = false;
                counter = -1;
            }
            else {
                current = current->GetNext();
                counter++;
            }
        }
    }

    return counter;
}
