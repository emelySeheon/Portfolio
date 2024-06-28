/*Barbara Johnston
 *for CIS 2277 Fall 2013
 *File: ListMgr.h          */

#ifndef _LIST_MGR_H
#define _LIST_MGR_H

#include "PersonNode.h"
#include <string>
using namespace std;

class ListMgr
{
private:
   int count{0};
   PersonNode *first, *last;

public:
    ListMgr();

    //Person* input, added to the front of the list
   void push_front(Person *p);

   //Person* input, added to the end of the list
   void push_back(Person *p);

   //Removes from the front of the list
   void pop_front();

   //Removes from the end of the list
   void pop_back();

   //Returns the number of PersonNodes in the list
   int size(){return count;}

   //Returns a pointer to the first Person
   Person* front();

   //Returns a pointer to the last Person
   Person* back();

   //Returns true if the list is empty, false if not
   bool empty();

   //Removes any (and all) PersonNode from the list that contains "n" in name
   //n could be full or partial name
   void remove(string n);

   //Removes the PersonNode at position pos.
   //Note: first string is #1
   void erase( int pos);

   //Clears all PersonNodes from the list
   void clear();

   //Shows the list, names only. First line states number in list
   //Second, 3rd, + lines shows names of people in the list. 
   //Place several names on one line. 
   //Separate names using obvious symbol, such as --> or :: 
   void show();

   //This show function returns a string that has the same features
   //of the show(), except it has been stringstreamed into a string
   string showString();

   //Destructor, clears the list
   ~ListMgr();

};

#endif