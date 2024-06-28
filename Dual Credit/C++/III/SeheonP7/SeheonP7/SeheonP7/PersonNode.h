/*Barbara Johnston
 *for CIS 2277 Fall 2013
 *File: PersonNode.h
 */

// This is the linked list node class used by the ListMgr class.

#ifndef _PERSON_NODE_H
#define _PERSON_NODE_H

#include "Person.h"
#include <iostream>
using namespace std;

class PersonNode
{
private:
    PersonNode* prev{ nullptr }, * next{ nullptr };
    Person* person{ nullptr };

public:
    PersonNode() = default;

    ~PersonNode()
    {
        if (person != nullptr) {
            cout << "\nIn PersonNode destructor.  " << person->GetName();
            delete person;
        }
    }

    void SetPrev(PersonNode *p) { prev = p; }
    void SetNext(PersonNode *n) { next = n; }
    void SetPerson(Person* p) { person = p; }

    PersonNode *GetPrev() { return prev; }
    PersonNode *GetNext() { return next; }
    Person* GetPerson() { return person; }
};

#endif
