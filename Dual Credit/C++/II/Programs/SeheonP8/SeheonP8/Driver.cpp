/*
* Name: Emely Seheon
* Email: eseheon1@cnm.edu
* Date: 12/8/20
* Program: SeheonP8
* Objective: To run the game of life.
* File: Driver.cpp
*/

#include <Windows.h>
#include "LifeCell.h"
#include "ConwayCell.h"
#include "Seeds.h"
#include "HighLife.h"
#include "FredkinCell.h"
#include "ModifiedFredkinCell.h"
#include <iostream>
using namespace std;

int main()
{
	//Header
	ShowWindow(GetConsoleWindow(), SW_MAXIMIZE);
	cout << " Name: Emely Seheon\n Email: eseheon1@cnm.edu\n Date: 12 / 10 / 20\n Program: SeheonP8\n Objective: To run the game of life.\n" << endl << endl << endl;

	LifeCell* pLife[5];

	ConwayCell con;
	FredkinCell fred;
	ModifiedFredkinCell modFred;
	Seeds seed;
	HighLife high;

	pLife[0] = &con;
	pLife[1] = &fred;
	pLife[2] = &modFred;
	pLife[3] = &seed;
	pLife[4] = &high;

	string playAgain = "n";
	int variation;
	int patternChoice;
	string fileNames[5] = { "butterfly.txt", "face.txt", "fish.txt", "icecream.txt", "smile.txt" };

	//Intro
	cout << " Welcome to the game of life!";
	cout << "\n This is a cellular automation where living cells become dead and dead cells become living";
	cout << "\n depending on the state of the cells around them.";
	cout << "\n In this program there are five different variations of this game, each with a defferent set of rules.";

	do {
		//Variation Selection
		cout << "\n Please type the number of the variation you would like to play: ";
		cout << "\n 1. Conway Cell";
		cout << "\n 2. Seeds";
		cout << "\n 3. Fredkin Cell";
		cout << "\n 4. Modified Fredkin Cell";
		cout << "\n 5. High Life\n ";

		cin >> variation;
		
		//Pattern Selection
		cout << "\n Next, choose a pattern: ";
		cout << "\n 1. Butterfly";
		cout << "\n 2. Face";
		cout << "\n 3. Fish";
		cout << "\n 4. Ice Cream";
		cout << "\n 5. Smile\n ";

		cin >> patternChoice;

		variation = variation - 1;
		patternChoice = patternChoice - 1;

		//Running the game
		int lifeCount = 0;
		pLife[variation]->SetPattern(fileNames[patternChoice]);

		do
		{
			cout << pLife[variation]->PrintBoard();
			pLife[variation]->UpdateBoard();
			Sleep(150);
			system("cls");
			lifeCount++;
		} while (lifeCount < 50);

		pLife[variation]->Clear();

		//Go Again
		cout << "\n Would you like to go again? (y/n)   ";
		cin >> playAgain;

	} while (playAgain == "y");
}