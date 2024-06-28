/*File: SearchableListMgr.h */

#ifndef _SRC_LIST_MGR_H
#define _SRC_LIST_MGR_H

#include "ListMgr.h"
#include "Person.h"
#include "string.h"
using namespace std;

class SearchableListMgr : public ListMgr
{
private:

public:
	SearchableListMgr() = default;

	//Search for a Person based on name and birthday
	//Returns node number if the person is present in the list, -1 if not.
	//It also returns a Person* if it finds the Person. 
	//nullptr if the person is not found. 

	int IsPresent(string name, int mm, int dd, int yy, Person** pPer);

};

#endif