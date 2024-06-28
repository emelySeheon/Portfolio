//
// Darian Rackham -- drackham@cnm.edu
// C++ III Project 8 -- Circular Queue
//
// CirQueue.cpp -- Implementation of circular queue class for 10 Persons
//

#include "CirQueue.h"
#include <sstream>
#include <iostream>
#include <iomanip>

using namespace std;


void CirQueue::Show() const
{
	cout << ShowString() << '\n';
}


const string CirQueue::ShowString() const
{
	stringstream ss;
	ss << "\n Persons enqueued: " << setw(2) << left << count << " = ";
	if (count == 0) return ss.str();

	const int PERSONS_PER_LINE{ 3 };

	for (int i{ 0 }; i < count; ++i ) {
		if (i > 0 && (i % PERSONS_PER_LINE) == 0)
			ss << '\n' << setw(24) << ' ';
		ss << i + 1 << "-> " << left << setw(21) << Q[(i + front) % QSIZE]->GetName();
	}

	return ss.str();
}


bool CirQueue::Push(unique_ptr<Person> p)
{
	if (Full()) return false;

	if (Empty()) 
		front = rear = 0;
	else
		rear = (rear + 1) % QSIZE;

	Q[rear] = move(p);
	++count;
	return true;
}


bool CirQueue::Pop()
{
	if (Empty()) return false;
	Q[front].reset();
	front = (front + 1) % QSIZE;
	--count;
	return true;
}
