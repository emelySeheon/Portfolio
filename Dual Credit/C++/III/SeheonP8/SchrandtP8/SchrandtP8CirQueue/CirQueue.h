//Ben Schrandt  bschrandt@cnm.edu
//Circular Queue with Unique Pointers

//CirQueue.h

#ifndef CIR_QUEUE
#define CIR_QUEUE

#include<memory>
#include "Person.h"
#include<fstream>
using namespace std;

class CirQueue
{
private:
	static const int SIZE = 10;
	unique_ptr<Person> pArray[SIZE];
	int count{ 0 };
	int first{ -1 };
	int last{ -1 };
	
public:
	CirQueue() = default;
	bool empty();
	bool full();
	void show();
	void push(unique_ptr<Person> p);
	void pop();
	string front();
	string back();
	string showString();
	int size();
};

#endif

