//File: Student.h

#ifndef _STUDENT_H
#define _STUDENT_H

//ES: Needed to include iostream so that the program would know what a string is, syntax error
#include <iostream>
using namespace std;

class Student
{
private:
	//ES: initialized name and ID so that they would have default values, runtime error
	string name = "", ID = "";
	//ES: Put curly brackets around the zero so that the program recognizes it as an int array and not just an int. Syntax error
	int testScore[4] = { 0 };
	double ave{ 0.0 };

public:
	Student() = default;

	void SetNameID(string n, string i);
	void SetTestScores(int sc[]);
	
	double GetAverage(){ return ave; }
	string GetName(){ return name; }
	string GetID(){ return ID; }
};

#endif

