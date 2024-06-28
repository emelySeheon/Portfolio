/*
Program: Functions.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/26/20
Purpose: To provide functions for the driver.
*/

#include <iostream>;
#include <iomanip>;
#include <fstream>;
#include "Functions.h";
#include <string>;
using namespace std;

//Course Header
void WriteHeader() {
	cout << "Name: Emely Seheon" << endl
		<< "Program: SeheonP6" << endl
		<< "Objective: To swap colors with phrases in sentences." << endl;
}

//Display rules
void ShowRules()
{
	cout << "\nWelcome! This program will read a sentence, detect the name of a color, and replace\nthe color with a corresponding phrase." << endl;
}

//Reads files
bool Read(string someArray[], string filename)
{
	ifstream inputFile;
	string contents;
	inputFile.open(filename);
	int i = 0;

	if (inputFile.is_open()) {
		while (getline(inputFile, contents)) {
			someArray[i] = contents;
			++i;
		}
		return true;
	}
	else {
		return false;
	}

	return false;
}

//Swaps the colors with the phrases
int SwapColors(int numSwapped[], string sentences[], string colors[], string phrases[])
{
	int totalChanges = 0;

	//Getting array sizes
	int sentenceSize = *(&sentences + 1) - sentences;
	
	int c = sizeof(colors);
	int d = sizeof(colors[0]);
	int colorSize = (d / c);

	//Looping through the sentence array
	for (int i=0; i < sentenceSize-1; i++) {
		int changes = 0;

		int sentenceChars = sentences[i].length();

		//Couting how many words in the sentence
		int spaceCount = 0;
		for (int l = 0; l < sentenceChars; ++l) {
			if (sentences[i][l] == ' ') {
				spaceCount++;
			}
		}
		int wordCount = spaceCount + 1;

		//Adding each word into a new array
		string* words = new string[wordCount];

		int index = 0;

		for (int k = 0; k < sentenceChars-1; ++k) {
			if (sentences[i][k] == ' ') {
				index++;
			}
			else if (sentences[i][k] == ',') {
				continue;
			}
			else {
				words[index] = words[index]+sentences[i][k];
			}
		}

		//Replacing the colors with phrases
		for (int j=0; j < colorSize; ++j) {
			for (int m = 0; m < wordCount;++m) {
				if (colors[j] == words[m]) {
					words[m] = phrases[j];
					changes++;
				}
			}
		}

		//Reconstructing the sentence with phrases
		string reconstruct = "";
		for (int n = 0; n < wordCount; ++n) {
			if (n == wordCount - 1) {
				reconstruct = reconstruct + words[n];
			}
			else {
				reconstruct = reconstruct + words[n] + " ";
			}
		}
		reconstruct = reconstruct + ".";

		delete[] words;
		//Putting the reconstructed sentence back into the sentence array
		sentences[i] = reconstruct;

		//putting the number of changes to the sentence in the numSwapped array
		numSwapped[i] = changes;

		totalChanges = totalChanges + changes;

	}
	cout << endl;

	return totalChanges;
}

//Writes the results
bool WriteResults(string sentences[], int totalSentModified, int numSwapped[], ofstream& output, string& results)
{
	output << "\nResults: " << endl
		<< "Number of modified sentences: " << totalSentModified << endl;
	for (int i = 0; i < 10; ++i) {
		output << "Number of modifications for sentence " << i << ": " << numSwapped[i] << endl;
	}

	output << "Sentences: " << endl;
	for (int j = 0; j < 10; ++j) {
		output << sentences[j] << endl;
	}

	output << results;

	return false;
}

//Writes the originals
bool WriteOriginals(string sentences[], string colors[], string phrases[], ofstream& output)
{
	output << "Originals: " << endl;
	output << "Sentences: " << endl;
	for (int i = 0; i < 10; ++i) {
		output << sentences[i] << endl;
	}
	output << "Colors: " << endl;
	for (int j = 0; j < 7; ++j) {
		output << sentences[j] << endl;
	}
	output << "Phrases: " << endl;
	for (int k = 0; k < 7; ++k) {
		output << sentences[k] << endl;
	}

	return false;
}

//Say GoodBye
void SayGoodbye()
{
	cout << "\nGoodBye!" << endl;
}