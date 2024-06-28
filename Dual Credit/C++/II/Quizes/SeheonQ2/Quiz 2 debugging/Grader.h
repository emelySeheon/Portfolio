//File: Grader.h

#ifndef _GRADER_H
#define _GRADER_H

#include "Student.h"
#include <string>
using namespace std;

class Grader
{
private:

	//ES: initialized kids so that it would have a default value, runtime error
	Student kids[10] = {};
	int total{ 0 };
	//ES: initialized courseName, courseID, and college so that they would have default values, runtime error
	string courseName = "", courseID = "", college = "";
	double courseAve{ 0.0 }, courseHigh{ 0.0 }, courseLow{ 0.0 };
	int highKid{ 0 }, lowKid{ 0 };
	//ES: initialized file and courseStats so that it would have a default value, runtime error
	string file = "";
	string courseStats = "";
	bool bReadFile{ false };

public:
	Grader() = default;
	void ComputeCourseStats();
	string GetCourseStats() { return courseStats; }
	bool WriteResultsFile(string file, string* course_name);
	//ES: Put parentheses before the return to show that there are no parameters taken by the function, syntax error
	bool IsFileRead() { return bReadFile; }
	int GetTotal() { return total; }
	//ES: made ReadStudentFile public because it needs to be called by the main program, logic fix
	void ReadStudentFile();
//ES: added a semicolon after the curly bracket to indicate that this is a class, syntax error
};

#endif
