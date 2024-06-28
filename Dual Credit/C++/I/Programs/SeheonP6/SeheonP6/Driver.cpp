/*
Program: SeheonP6
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 7/26/20
Purpose: To swap colors with phrases.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <array>
#include <fstream>;
#include "Functions.h";
using namespace std;

int main()
{
	WriteHeader();
	ShowRules();

	//Initializing Variables
	string colorArray[7];
	string colorFile = "colors.txt";

	string sentenceArray[10];
	string sentenceFile = "sentences.txt";

	string phraseArray[7];
	string phraseFile = "phrases.txt";

	int numSwapped[10];
	string outFile = "out.txt";

	string results = "Name: Emely Seheon\nProject: SeheonP6";

	//Reading files
	bool colorcorrect = Read(colorArray, colorFile);
	if (colorcorrect == false) {
		cout << "\nthe file was not able to be opened." << endl;
		exit(1);
	}

	bool sentenceCorrect = Read(sentenceArray, sentenceFile);
	if (sentenceCorrect == false) {
		cout << "\nThe file was not able to be opened." << endl;
		exit(1);
	}

	bool phraseCorrect = Read(phraseArray, phraseFile);
	if (phraseCorrect == false) {
		cout << "\nThe file was not able to be opened." << endl;
		exit(1);
	}

	//Getting the output file
	string outputFile;
	cout << "What is the name of the file you would like the results in? ";
	getline(cin, outputFile);

	ofstream output;
	output.open(outputFile);

	//Writing originals
	WriteOriginals(sentenceArray, colorArray, phraseArray, output);

	//Making and writing/displaying changes
	int totalChanges = SwapColors(numSwapped, sentenceArray, colorArray, phraseArray);

	WriteResults(sentenceArray, totalChanges, numSwapped, output, results);

	cout << "\nResults: " << endl
		<< "Number of modified sentences: " << totalChanges << endl;
	for (int i = 0; i < 10; ++i) {
		cout<< "Number of modifications for sentence " << i << ": " << numSwapped[i] << endl;
	}

	cout << "Sentences: " << endl;
	for (int j = 0; j < 10; ++j) {
		cout << sentenceArray[j] << endl;
	}

	//goodbye
	SayGoodbye();

	return 0;
}