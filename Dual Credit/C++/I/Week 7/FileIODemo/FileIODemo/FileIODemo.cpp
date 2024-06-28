/*
Program: FileIODemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/30/20
Purpose: To learn file io.
*/

#include <iostream>;
#include <fstream>;
#include <ctime>;
#include <sstream>;
using namespace std;

string CurrentTime();
bool SaveFile(string fileName);
string ReadFile(string fileName);

int main()
{
	
	if (SaveFile("example3.txt")) {
		cout << "File saved!" << endl;
	}
	else
	{
		cout << "Error! Could not open file." << endl;
	}

	//Open and display file
	string fileText = ReadFile("example3.txt");
	cout << "Information from file: " << endl;
	cout << fileText;

	return 0;
}

bool SaveFile(string fileName) {

	bool fileOk = true; //Return true if file ok

	//Save to file
	ofstream outputFile;
	outputFile.open(fileName);
	if (outputFile.is_open()) {
		outputFile << "Sample text to write into file" << endl;
		outputFile << "This will be on another line!" << endl;
		outputFile << "Time stamp: " << CurrentTime() << endl;
		outputFile.close();
		return true;
	}
	else {
		return false;
	}

}

//Current Time returns the current date and time
string CurrentTime() {

	//Declare a string stream object to format our string
	stringstream timeStream;

	//Get time using char functions
	char timeNow[20];
	char dateNow[20];

	errno_t err;

	err = _strtime_s(timeNow, 20);
	err = _strdate_s(dateNow, 20);

	//Put time into timeStream
	timeStream << dateNow << " " << timeNow;

	return timeStream.str();
}

string ReadFile(string fileName) {
	ifstream inputFile;
	string line;
	string fullText = "";

	inputFile.open(fileName);
	if (inputFile.is_open()) {
		while (getline(inputFile, line)) {
			fullText += line + "\n";
		}
	}
	else {
		fullText = "Error! Could not open file.";
	}
	return fullText;
}