/*
Program: SeheonP2
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/11/20
Purpose: To draw a picture
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
#include <windows.h>;
using namespace std;

int main()
{
	
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP2" << endl
		<< "Objective: To draw a picture." << endl;

	//Program Explanation
	cout << "\nHello! welcome to the C++ Picture App!" << endl
		<< "This program will draw any shape in any color you want!" << endl
		<< "Have Fun!!!" << endl;

	//Declaring variables and constants
	int userColor;
	int userShape;
	
	const int QUIT = 6;
	const int MAX_COLORS = 6;
	const int MAX_PIX = 3;

	//Creating handle to the console
	HANDLE screen = GetStdHandle(STD_OUTPUT_HANDLE);

	//Open the do while loop
	do
	{

		//Showing the user the color options
		cout << "\n1. Blue" << endl
			<< "2. Green" << endl
			<< "3. Cyan" << endl
			<< "4. Red" << endl
			<< "5. Purple" << endl
			<< "6. Quit" << endl;

		//Getting user input
		cout << "\nChoose a color (1-6): ";
		cin>>(userColor);

		//Open the if statement
		if (userColor >= 1 && userColor <= 5)
		{
			
			//Showing the user the color options
			cout << "\n1. Smiling Face" << endl
				<< "2. Pyramid" << endl
				<< "3. Square" << endl;

			//Getting user input
			cout << "\nChoose a Shape (1-3): ";
			cin >> (userShape);

			//Changing the color to the bright version
			int colorChoice = userColor + 8;

			//Opening the switch statement
			switch (userShape)
			{
				//If the user wants a smiley face
			case 1:
				SetConsoleTextAttribute(screen, colorChoice);
				cout << "\n |  ^  ^  |" << endl
					<< " |________|" << endl;
				break;

				//If the user wants a pyramid
			case 2:
				SetConsoleTextAttribute(screen, colorChoice);
				cout << "    _" << endl
					<< "   | |" << endl
					<< "   ---" << endl
					<< "  |   |" << endl
					<< "  -----" << endl
					<< " |_____|" << endl;
				break;

				//If the user wants a Square
			case 3:
				SetConsoleTextAttribute(screen, colorChoice);
				cout << "--------" << endl
					<< "|      |" << endl
					<< "|      |" << endl
					<< "--------" << endl;
				break;
			}
		}
		else
		{
			//Leaving the loop if the user wants to quit
			break;
		}

	} while (userColor >= 1 && userColor <= 6);

	//Goodbye Message
	cout << "\nThank you for using the App!" << endl
		<< "Come again soon!" << endl
		<< "GoodBye!" << endl;

	return 0;
}