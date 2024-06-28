//File: Grader.cpp

#include "Grader.h"

#include <fstream>
#include <iomanip>
#include <sstream>
using namespace std;

//ES: changed FILE to fileName since FILE can have multiple meanings, logic correction
//ES: changed "StudentGrade.txt" to "StudentGrades.txt", logic correction
const string  fileName = "StudentGrades.txt";

//ES: deleted the constructor body since it is set as default in the .h file, runtime error


void Grader::ComputeCourseStats()
{
	//loop through array of kids
	//obtaining the students' average
	//to compute course average
	//remember high and low ave too

	double sum = 0;
	//ES: parenthesis after GetAverage to show that it is a function, syntax error
	courseHigh = kids[0].GetAverage();
	courseLow = courseHigh;
	lowKid = highKid = 0;

	double score;

	for(int i = 0; i < total; ++ i)
	{
		score = kids[i].GetAverage();
		sum = sum + score;

		if(score < courseLow)
		{
			lowKid = i;
			courseLow = score;
		}

		if(score > courseHigh)
		{
			highKid = i;
			courseHigh = score;
		}
	}

	courseAve = sum/total;

	//Create a string with the course statistics
	stringstream ss;

	//set stringstream's precision
	ss.setf(ios::fixed);
	ss.precision(2);

	ss << "\n Grade Results for " << college
		<< "'s \n " << courseName
		<< "\n ID " << courseID << endl;

	string stName, stID;
	double stAve;

	ss << setw(20) << "Name" << setw(8)
		<< "ID" << setw(10) << "Ave" << endl;

	for (int i = 0; i < total; ++i)
	{
		stName = kids[i].GetName();
		stID = kids[i].GetID();
		stAve = kids[i].GetAverage();

		ss << setw(20) << stName << setw(8)
			<< stID << setw(10) << stAve << endl;
	}

	ss << "\n Course Ave " << courseAve
		<< "\n High Ave was " << courseHigh
		<< " by " << kids[highKid].GetName()
		<< "\n Low Ave was " << courseLow
		<< " by " << kids[lowKid].GetName();

	courseStats = ss.str();

}
	
bool Grader::WriteResultsFile(string fName, string* course_name)
{
	file = fName;
	ofstream output;
	bool bFileOK;

	//convert the string to a c-string
	output.open(file.c_str() );

	if(!output)
	{
		bFileOK = false;
	}
	else
	{
		output << courseStats;
		output.close();

		*course_name = courseName;
		bFileOK = true;
	}
	

	return bFileOK;

}
	
void Grader::ReadStudentFile()
{
	ifstream input;
	//ES: changed FILE to fileName because the file name is saved to the string fileName, syntax error
	input.open(fileName);
	if(!input)
	{
		bReadFile = false;
	}
	//ES: Put an else around the rest of the code because this part does not need to be done if the file is not open, logic error
	else {
		//ES: set bReadFile to true so the main program knows the file was opened, runtime error
		bReadFile = true;

		//Ok, open and ready to go
		//read first line, college
		getline(input, college);

		//next two lines are the course and course ID
		getline(input, courseName);
		getline(input, courseID);

		//rest of file are students, 3 lines per 
		//we'll read until the end of file
		//counting students as we go

		string name, id;
		int g[4];
		int count = 0;

		//each pass, read each student's data
		while (!input.eof())
		{
			getline(input, name);
			getline(input, id);

			//read each value using a delimiter and fill a char array,
			//then use atoi to convert to an int. 

			//read the first 3 to the comma, then last to the end of the line.
			char buf[10];
			input.getline(buf, 10, ',');
			g[0] = atoi(buf);
			input.getline(buf, 10, ',');
			g[1] = atoi(buf);
			input.getline(buf, 10, ',');
			g[2] = atoi(buf);
			input.getline(buf, 10);
			g[3] = atoi(buf);


			kids[count].SetNameID(name, id);
			kids[count].SetTestScores(g);
			++count;
		}

		//set the total number of students in file
		total = count;

		//close the file
		input.close();

	}
}
