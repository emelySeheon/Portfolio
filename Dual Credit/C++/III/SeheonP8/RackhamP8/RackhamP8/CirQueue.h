//
// Darian Rackham -- drackham@cnm.edu
// C++ III Project 8 -- Circular Queue
//
//  CirQueue.h -- Prototypes for circular queue class for 10 Persons
//

#ifndef CIRQUEUE_H
#define CIRQUEUE_H

#include "Person.h"
#include <ostream>
#include <memory>

class CirQueue
{
private:


	// Maximum number of Persons in the queue
	static const int QSIZE{ 10 };


	// The queue
	std::unique_ptr<Person> Q[QSIZE]{};


	// Number of Persons currently enqueued
	int count{ 0 };


	// Index of front Person
	int front{ 0 };
	

	// Index of rear Person
	int rear{ 0 };

public:

	CirQueue() = default;


	// Don't allow instances to be copied because we can't copy unique_ptr members
	CirQueue(const CirQueue&) = delete;
	CirQueue& operator=(const CirQueue&) = delete;


	// Does queue contain 0 Persons?
	bool Empty() const { return count == 0; }


	// Does queue contain the maximum number of Persons?
	bool Full() const { return count == QSIZE; }


	// How many Persons does it contain?
	int Size() const { return count; }


	// Pretty print the queue contents
	void Show() const;
	const std::string ShowString() const;


	// Add a Person to the rear of the queue
	bool Push(std::unique_ptr<Person> p);


	// Pop the Person at the front of the queue
	bool Pop();


	// Name of Person in front or "empty" in queue is empty
	const std::string Front() const
	{ 
		return (!Empty() ? Q[front]->GetName() : "empty");
	};


	// Name of Person in rear or "empty" if queue is empty
	const std::string Rear() const
	{ 
		return (!Empty() ? Q[rear]->GetName() : "empty");
	};

};

#endif // !CIRQUEUE_H