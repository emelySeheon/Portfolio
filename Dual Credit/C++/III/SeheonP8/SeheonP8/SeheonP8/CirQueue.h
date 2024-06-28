//CirQueue.h

#ifndef _CIR_QUEUE_H
#define _CIR_QUEUE_H

#include "Person.h"
#include <fstream>
#include <memory>
#include <iostream>
#include <sstream>

using namespace std;

class CirQueue
{
private:
	static const int SIZE{ 10 };
	unique_ptr<Person> pArray[SIZE];
	int count{ 0 };
	int first{ 0 };
	int last{ 0 };

public:
	CirQueue() = default;
	~CirQueue();
	bool Empty();		//true is empty, false if not
	bool Full();		//true if full, false if not
	void Show();        	//displays the names in the queue
	string ShowString();	//Similar to LinkedList, returns string for Logger
	bool Push(unique_ptr<Person> p);   //put a Person pointer at the end of the line, bool = false if queue is full
	bool  Pop();		//removes the Person pointer from the front of the line, bool false if the queue is empty
	string Front();		//returns a string with the name of the Person pointer  from the front of the line
	string Rear();		// returns a string with the name of the Person pointer  from the rear of the line
	int Size();		//returns the number of elements currently in the queue
};

#endif _CIR_QUEUE_H