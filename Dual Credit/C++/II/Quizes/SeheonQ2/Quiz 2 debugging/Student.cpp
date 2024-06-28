//File: Student.cpp

#include "Student.h"

//ES: deleted the constructor body since it is set as default in the .h file, runtime error

void Student::SetNameID(string n, string i)
{
	name = n;
	ID = i;
}

void Student::SetTestScores(int sc[])
{
	int i;
	
	//copy the test scores into the array
	//ES: Put a for loop around the code to put each value of sc into testScore, otherwise it the program wouldn't know what it had to do, syntax error
	for (int j = 0; j < 4; ++j) {
		testScore[j] = sc[j];
	}
	
	
	int sum = 0;
	
	
	//sum and determine average
	//ES: put curly brackets around the addition of the test scores to show that it is in the for loop, syntax error
	for (i = 0; i < 4; ++i) {
		sum += testScore[i];
	}

	ave = sum/4;
}

