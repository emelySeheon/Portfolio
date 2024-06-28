/*
Program: SeheonP5
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: To play rock paper scissors
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include "Functions.h"
using namespace std;

int main()
{
	string goAgain;
	string uObject;
	string& ptr_uObject = uObject;
	string cObject;
	string& ptr_cObject = cObject;
	string result;
	string* ptr_result = &result;
	bool valid;
	int uChoice;
	int* ptr_uChoice = &uChoice;
	int cChoice;
	int* ptr_cChoice = &cChoice;
	GameData data;
	GameData& ptr_data = data;

	WriteHeader();
	WriteGameRules();

	do {
		data.userWins = 0;
		data.compWins = 0;
		do {
			AskUserChoice(ptr_uChoice);
			valid = ValidateUserChoice(uChoice, ptr_result);
			if (valid == false) {
				WriteResults(result);
				cout << endl << "\nWould you like to go again? (y/n) ";
				cin.ignore();
				getline(cin, goAgain);

				if (goAgain == "y") {
					continue;
				}
				else {
					break;
				}
			}

			ObtainCompChoice(uChoice, ptr_cChoice, ptr_data);
			string winner = DetermineWinner(uChoice, cChoice, ptr_data);
			DetermineObject(uChoice, ptr_uObject);
			DetermineObject(cChoice, ptr_cObject);
			cout << "\nUser Choice: " << uObject << endl
				<< "Computer Choice: " << cObject << endl
				<< winner << endl;

			data.numRounds = data.numRounds + 1;
		} while (data.userWins < 2 && data.compWins < 2);

		if (valid == false) {
			break;
		}

		data.numGames = data.numGames + 1;
		WriteResults(result, data);

		cout << endl << "\nWould you like to go again? (y/n) ";
		cin.ignore();
		getline(cin, goAgain);

	} while (goAgain == "y");
}