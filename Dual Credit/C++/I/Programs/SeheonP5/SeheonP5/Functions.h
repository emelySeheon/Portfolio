/*
Program: Functions.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/7/20
Purpose: To provide functions for the driver
*/

using namespace std;

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

struct GameData
{
	int tieCount = 0;
	int numRounds = 0;
	int userWins = 0;
	int compWins = 0;
	int numGames = 0;
};

void WriteHeader();
void WriteGameRules();
void AskUserChoice(int* uChoice);
bool ValidateUserChoice(int uChoice, string* result);
void ObtainCompChoice(int uChoice, int* cChoice, GameData& data);
string DetermineWinner(int uChoice, int cChoice, GameData& data);
void DetermineObject(int choice, string& object);
void WriteResults(string result, GameData data);
void WriteResults(string result);

#endif