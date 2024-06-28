#include "CirQueue.h"

CirQueue::~CirQueue()
{
	first = 0;
	last = 0;
	count = 0;
	for (int i = 0; i < SIZE; ++i) {
		if (pArray[i] != NULL) {
			pArray[i] = NULL;
		}
	}
}

bool CirQueue::Empty()
{
	if (count > 0) {
		return false;
	}
	else {
		return true;
	}
}

bool CirQueue::Full()
{
	if (count < SIZE) {
		return false;
	}
	else {
		return true;
	}
}

void CirQueue::Show()
{
	if (Empty()) {
		cout << "Queue is empty.";
	}
	else {
		for (int i = 0; i < SIZE; ++i) {
			if (first + i < SIZE) {
				if (pArray[first + i] != NULL) {
					cout << pArray[first + i].get()->GetName() << ":: ";
				}
			}
			else {
				if (pArray[first + i - SIZE] != NULL) {
					cout << pArray[first + i - SIZE].get()->GetName() << ":: ";
				}
			}
		}
	}
}

string CirQueue::ShowString()
{
	stringstream ss;
	if (Empty()) {
		ss << "Queue is empty.";
	}
	else {
		for (int i = 0; i < SIZE; ++i) {
			if (pArray[i] != NULL) {
				ss << pArray[i].get()->GetName() << ":: ";
			}
		}
	}
	return ss.str();
}

bool CirQueue::Push(unique_ptr<Person> p)
{
	if (Full()) {
		return false;
	}
	else {
		if (Empty()) {
			pArray[0].reset(p.release());
			first = 0;
			last = 0;
		}
		else {
			if (last == SIZE - 1) {
				pArray[0].reset(p.release());
				last = 0;
			}
			else {
				pArray[last + 1].reset(p.release());
				last = last + 1;
			}
		}
		count++;
	}
}

bool CirQueue::Pop()
{
	if (Empty()) {
		return false;
	}
	else {
		pArray[first].reset(NULL);
		if (first != SIZE - 1) {
			first = first + 1;
		}
		else if (first == SIZE - 1) {
			first = 0;
		}
		count--;
	}
}

string CirQueue::Front()
{
	if (pArray[first] == NULL) {
		return "Front pointer is null.";
	}
	else {
		return pArray[first].get()->GetName();
	}
}

string CirQueue::Rear()
{
	if (pArray[last] == NULL) {
		return "Rear pointer is null.";
	}
	else {
		return pArray[last].get()->GetName();
	}
}

int CirQueue::Size()
{
	return count;
}
