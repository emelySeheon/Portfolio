/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: To provide functions for the driver
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <sstream>;
#include "Functions.h"
using namespace std;

//Course Header
void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP5" << endl
		<< "Objective: To play rock paper scissors." << endl;
}

//Display the rules
void WriteGameRules()
{
	cout << endl << "\nWelcome to Rock Paper Scissors." << endl
		<< "Today you will be competing against the computer." << endl
		<< "The rules of the game are simple." << endl
		<< "You have three options: rock, paper, and scissors." << endl
		<< "Rock beats scissors, scissors beat paper, and paper beats rock." << endl
		<< "Whoever gets two points first wins" << endl
		<< "May the odds be ever in your favor." << endl;
}

//Display options and gets user's choice
void AskUserChoice(int* uChoice)
{
	cout << "\n1. Rock" << endl
		<< "2. Paper" << endl
		<< "3. Scissors" << endl
		<< "Choose your weapon (enter the number): ";
	cin >> *uChoice;
}

//Verify that the user's choice is an option
bool ValidateUserChoice(int uChoice, string* result)
{
	stringstream results;
	results << "Error, the number you entered (" << uChoice << ") is not valid";
	if (uChoice != 1 && uChoice != 2 && uChoice != 3) {
		*result = results.str();
		return false;
	}
	return true;
}

//Getting the computer's choice
void ObtainCompChoice(int uChoice, int* cChoice, GameData& data)
{
	int u = data.tieCount;
	bool tie = false;
	do {
		srand((unsigned)time(NULL));
		*cChoice = rand() % 3 + 1;
		if (*cChoice == uChoice) {
			data.tieCount = data.tieCount + 1;
			cout << "d";
			tie = true;
		}
		else {
			tie = false;
		}

		if (data.tieCount > 0) {
			data.tieCount = 0;
			data.tieCount = u + 1;
		}
	} while (tie == true);
}

//Determines the winner
string DetermineWinner(int uChoice, int cChoice, GameData& data)
{
	string winner;
	if ((uChoice == 1 && cChoice == 2)
		|| (uChoice == 2 && cChoice == 3)
		|| (uChoice == 3 && cChoice == 1)) {

		winner = "Darn! The computer won!";
		data.compWins = data.compWins + 1;
	}
	else if ((uChoice == 1 && cChoice == 3)
		|| (uChoice == 2 && cChoice == 1)
		|| (uChoice == 3 && cChoice == 2)) {

		winner = "Yay! You won!";
		data.userWins = data.userWins + 1;
	}
	return winner;
}

//Translate the players' choice to the object name
void DetermineObject(int choice, string& object)
{
	if (choice == 1) {
		object = "Rock";
	} else if (choice == 2) {
		object = "Paper";
	}
	else if (choice == 3) {
		object = "Scissors";
	}
}

//Display the game data
void WriteResults(string result, GameData data)
{
	if (data.compWins > data.userWins) {
		result = "\nThe computer won! :(";
	}
	else if (data.compWins < data.userWins) {
		result = "\nYou won!!! :)";
	}

	cout << result << endl
		<< "\nTies: " << data.tieCount
		<< "\nNumber of rounds: " << data.numRounds
		<< "\nUser wins: " << data.userWins
		<< "\nComputer wins: " << data.compWins
		<< "\nNumber of Games: " << data.numGames;
}

//Display the error if the user's choice is not valid
void WriteResults(string result)
{
	cout << endl << result;
}