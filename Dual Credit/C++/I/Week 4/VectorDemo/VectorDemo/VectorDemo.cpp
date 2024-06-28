/*
Program: VectorDemo
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/11/20
Purpose: To demonstrate vectors.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
using namespace std;

int main()
{
	
	//Variable
	string userText;
	vector<string> textLines;

	//Start a do while loop
	do
	{


		//Get user input
		cout << "Please enter a line of text: ";
		getline(cin, userText);

		//If input is not "" then add it to vector
		if (userText != "")
		{
			textLines.push_back(userText);
		}

	// Loop back if input is not ""
	} while (userText != "");

	//Loop through vector and display it
	cout << endl << "Lines entered:" << endl;
	for (int i = 0; i < textLines.size(); ++i)
	{
		cout << textLines.at(i) << endl;
	}

	return 0;
}