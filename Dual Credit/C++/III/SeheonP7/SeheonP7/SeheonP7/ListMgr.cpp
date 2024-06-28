//File: ListMgr.cpp

#include "ListMgr.h"
#include <sstream>
using namespace std;

ListMgr::ListMgr() {
	count = 0;
	first = nullptr;
	last = nullptr;
}

void ListMgr::push_front(Person* p)
{
	PersonNode* np;
	try {
		np = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	np->SetPerson(p);

	//Check if list is empty
	if (count == 0) { //np is the only item
		first = np;
		last = np;
	}
	else {
		np->SetNext(first);
		first->SetPrev(np);
		np->SetPrev(nullptr);
		first = np;
	}

	++count;
}

void ListMgr::push_back(Person* p)
{
	PersonNode* np;
	try {
		np = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	np->SetPerson(p);

	//Check if list is empty
	//cout << count;
	if (count == 0) { //np is the only item
		first = np;
		last = np;
	}
	else {
		np->SetNext(nullptr);
		last->SetNext(np);
		np->SetPrev(last);
		last = np;
	}

	++count;
}

void ListMgr::pop_front()
{
	if (count == 0) {
		cout << "\nList is empty.";
		return;
	}
	
	PersonNode* np;
	try {
		np = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	np = first->GetNext();
	delete first;

	if (count == 1) {
		count = 0;
		return;
	}

	first = np;
	first->SetPrev(nullptr);

	--count;
}

void ListMgr::pop_back()
{
	if (count <= 0) {
		cout << "\nList is empty.";
		return;
	}

	PersonNode* np;
	try {
		np = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	np = last->GetPrev();
	delete last;

	if (count == 1) {
		count = 0;
		return;
	}

	last = np;
	last->SetNext(nullptr);

	--count;
}

Person* ListMgr::front()
{
	//If empty
	if (count == 0) {
		cout << "\nList is empty.";
		return nullptr;
	}

	//Returning the first person
	Person* p;
	p = first->GetPerson();
	return p;
}

Person* ListMgr::back()
{
	//If empty
	if (count == 0) {
		cout << "\nList is empty.";
		return nullptr;
	}

	//Returning the last person
	Person* p;
	p = last->GetPerson();
	return p;
}

bool ListMgr::empty()
{
	if (count == 0) {
		return true;
	}
	return false;
}

void ListMgr::remove(string n)
{
	//if empty
	if (count == 0) {
		cout << "\nList is empty.";
		return;
	}

	//making objects
	PersonNode* checker, * prevHolder, * nextHolder;
	checker = first;
	try {
		prevHolder = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}try {
		nextHolder = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	while (checker != nullptr) {
		prevHolder = checker->GetPrev();
		nextHolder = checker->GetNext();
		if (checker->GetPerson()->GetName().find(n) != string::npos) {
			//erasing
			if (checker == first) {
				pop_front();
			}
			else if (checker == last) {
				pop_back();
			}
			else {
				prevHolder->SetNext(nextHolder);
				nextHolder->SetPrev(prevHolder);
				delete checker;
				count--;
			}
		}
		checker = nextHolder;
	}
}

void ListMgr::erase(int pos)
{
	//if empty
	if (count == 0) {
		cout << "\nList is empty.";
		return;
	}

	if (pos > count || pos < 1) {
		return;
	}

	//making objects
	PersonNode* pn, * prevHolder, * nextHolder;
	pn = first;
	try {
		prevHolder = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}try {
		nextHolder = new PersonNode();
	}
	catch (bad_alloc ba) {
		return;
	}

	//finding the location of erasure
	for (int i = 1; i < pos; ++i) {
		pn = pn->GetNext();
	}

	//erasing
	if (pn == first) {
		pop_front();
	}
	else if (pn == last) {
		pop_back();
	}
	else {
		prevHolder = pn->GetPrev();
		nextHolder = pn->GetNext();
		prevHolder->SetNext(nextHolder);
		nextHolder->SetPrev(prevHolder);
		delete pn;
		count--;
	}
}

void ListMgr::clear()
{
	if (count == 0) {
		cout << "\nList is empty.";
		return;
	}

	PersonNode* eraser, * nextHolder;
	eraser = first;
	for (int i = 0; i < count; ++i) {
		nextHolder = eraser->GetNext();
		if (i != count - 1) {
			nextHolder->SetPrev(nullptr);
		}
		//if (eraser->GetNext() != nullptr) {
			eraser->SetNext(nullptr);
		//}
		delete eraser;
		eraser = nextHolder;
	}
	count = 0;
	first = nullptr;
	last = nullptr;
}

void ListMgr::show()
{
	cout << endl << count << " items" << endl;
	PersonNode* display;
	display = first;

	for (int i = 0; i < count; ++i) {
		for (int j = 0; j < 5; ++j) {
			if (display != nullptr) {
				cout << display->GetPerson()->GetName() << ":: ";
				display = display->GetNext();
				++i;
			}
		}
	}
}

string ListMgr::showString()
{
	stringstream ss;
	ss << endl << count << " items" << endl;
	PersonNode* display;
	display = first;

	for (int i = 0; i < count; ++i) {
		for (int j = 0; j < 5; ++j) {
			if (display != nullptr) {
				ss << display->GetPerson()->GetName() << ":: ";
				display = display->GetNext();
				++i;
			}
		}
	}
	return ss.str();
}

ListMgr::~ListMgr()
{
	if (first != nullptr) {
		delete first;
	}
	if (last != nullptr) {
		delete last;
	}
	count = 0;
}
