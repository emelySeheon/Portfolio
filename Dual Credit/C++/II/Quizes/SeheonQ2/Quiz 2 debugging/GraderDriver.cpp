//File: GraderDriver.cpp
//This program uses the Grader class to
//help us determine grades for C++ students.



#include "Grader.h"
#include <iostream>
//ES: added semicolon to show that this line of code is over, syntax error
using namespace std;

int main()
{
	cout << "Debugged by: Emely Seheon :)" << endl << endl;
	cout << "\n The Teacher's Helper Program! \n";

	string file;
	bool bFileRead{ false }; 
	bool bFileWritten{ false };
	string courseName;
	int total{ 0 };

	Grader kelly;
	
	//ES: called ReadStudentFile so that the file can be read
	kelly.ReadStudentFile();
	//Check if the file was read
	//ES: set bFileRead equal to the result of IsFileRead, logic error
	bFileRead = kelly.IsFileRead();
	if (!bFileRead)
	{

		cout << "\n Can't find the "
			<< " StudentGrades.txt file."
			<< " \n Exiting program!";
	}
	else
	{
	
		cout << "\n We have read " << kelly.GetTotal()
			<< " student grades " << endl;

		//Calculate course statistics
		kelly.ComputeCourseStats();
	
		//Display the answer to the user
		cout << kelly.GetCourseStats();

		//Now write the summary file
		cout << "\n\n Please enter the output file name, such as summary.txt  ";
		getline(cin, file);

		bFileWritten = kelly.WriteResultsFile(file, &courseName);

		if (!bFileWritten)
		{
			cout << "\n Trouble opening output file."
				<< "\n Can't write the file. " << endl;
		}
		else
		{
			cout << "\n All Done! \n Check " << file
				<< " for " << courseName << " grade results!"
				<< endl;
		}
	}

	return 0;
}