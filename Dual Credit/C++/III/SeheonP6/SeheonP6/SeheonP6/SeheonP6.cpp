//Name: Emely Seheon
//Date: 3/20/21
//Email: eseheon1@cnm.edu
//Program: SeheonP6
//Objective: To sort ints, strings, and Person objects

#include <iostream>
#include <fstream>
#include <chrono>
#include ".\PersonGen\Date.h"
#include ".\PersonGen\Person.h"
#include ".\PersonGen\PersonGen.h"
#include "TemplateSorter.h"
using namespace std::chrono;
using namespace std;

//Creating the Person array and the temp globally
Person persArr[10000];
Person tempPersArr[10000];

int main()
{
	//Header
	cout << "Name: Emely Seheon\n";
	cout << "Date: 3/20/21\n";
	cout << "Email: eseheon1@cnm.edu\n";
	cout << "Program: SeheonP6\n";
	cout << "Objective: To sort ints, strings, and Person objects\n";

	//Getting the output file
	string filename;
	cout << "\nPlease enter the name of the output file. ";
	cin >> filename;

	ofstream writer;
	writer.open(filename);
	if (!writer.is_open()) {
		cout << "\nUnable to open output file.";
	}

	//Creating the int array
	int* intArr = new int[200000];
	ifstream reader;
	reader.open("BJTest200K.txt");
	if (!reader.is_open()) {
		cout << "\nUnable to open int input file.";
	}
	else {
		string intReader;
		for (int i = 0; i < 200000; ++i) {
			getline(reader, intReader);
			intArr[i] = stoi(intReader);
		}
		reader.close();
	}

	//Creating the string array
	string* strArr = new string[25000];
	reader.open("BJName25000.txt");
	if (!reader.is_open()) {
		cout << "\nUnable to open string input file.";
	}
	else {
		for (int i = 0; i < 25000; ++i) {
			getline(reader, strArr[i]);
		}
		reader.close();
	}

	//Filling the Person array
	PersonGen generator;
	for (int i = 0; i < 10000; ++i) {
		persArr[i] = generator.GetPerson();
	}

	//Temp arrays
	int* tempIntArr = new int[200000];
	string* tempStrArr = new string[25000];

	for (int i = 0; i < 200000; ++i) {
		tempIntArr[i] = intArr[i];
	}
	for (int i = 0; i < 25000; ++i) {
		tempStrArr[i] = strArr[i];
	}
	for (int i = 0; i < 10000; ++i) {
		tempPersArr[i] = persArr[i];
	}

//Int sorts
	Sort <int> intSorter;

	//Bubble
	cout << "\nFirst 10 values of unsorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of unsorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	cout << "\nStarting int bubble sort.";
	auto intBubStart{ high_resolution_clock::now() };
	intSorter.BubbleSort(intArr, 200000);
	auto intBubStop{ high_resolution_clock::now() };
	auto intBubMs{ duration_cast<milliseconds>(intBubStop - intBubStart).count() };
	cout << "\nFinished int bubble sort with an elapsed time of " << intBubMs << " milliseconds.";
	
	cout << "\nFirst 10 values of sorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of sorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	for (int i = 0; i < 200000; ++i) {
		intArr[i] = tempIntArr[i];
	}

	//Comb 11
	cout << "\n\nFirst 10 values of unsorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of unsorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	cout << "\nStarting int comb 11 sort.";
	auto intComStart{ high_resolution_clock::now() };
	intSorter.Comb11(intArr, 200000);
	auto intComStop{ high_resolution_clock::now() };
	auto intComMs{ duration_cast<milliseconds>(intComStop - intComStart).count() };
	cout << "\nFinished int comb 11 sort with an elapsed time of " << intComMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of sorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	for (int i = 0; i < 200000; ++i) {
		intArr[i] = tempIntArr[i];
	}

	//Insertion
	cout << "\n\nFirst 10 values of unsorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of unsorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	cout << "\nStarting int insertion sort.";
	auto intInsStart{ high_resolution_clock::now() };
	intSorter.Insertion(intArr, 200000);
	auto intInsStop{ high_resolution_clock::now() };
	auto intInsMs{ duration_cast<milliseconds>(intInsStop - intInsStart).count() };
	cout << "\nFinished int insertion sort with an elapsed time of " << intInsMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of sorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	for (int i = 0; i < 200000; ++i) {
		intArr[i] = tempIntArr[i];
	}

	//Selection
	cout << "\n\nFirst 10 values of unsorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of unsorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	cout << "\nStarting int selection sort.";
	auto intSelStart{ high_resolution_clock::now() };
	intSorter.Selection(intArr, 200000);
	auto intSelStop{ high_resolution_clock::now() };
	auto intSelMs{ duration_cast<milliseconds>(intSelStop - intSelStart).count() };
	cout << "\nFinished int selection sort with an elapsed time of " << intSelMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of sorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	for (int i = 0; i < 200000; ++i) {
		intArr[i] = tempIntArr[i];
	}

	//Shaker
	cout << "\n\nFirst 10 values of unsorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of unsorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	cout << "\nStarting int shaker sort.";
	auto intShaStart{ high_resolution_clock::now() };
	intSorter.Shaker(intArr, 200000);
	auto intShaStop{ high_resolution_clock::now() };
	auto intShaMs{ duration_cast<milliseconds>(intShaStop - intShaStart).count() };
	cout << "\nFinished int shaker sort with an elapsed time of " << intShaMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted int array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << intArr[i];
	}
	cout << "\nLast 10 values of sorted int array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << intArr[200000 - i];
	}

	for (int i = 0; i < 200000; ++i) {
		intArr[i] = tempIntArr[i];
	}

//String sorts
	Sort <string> strSorter;

	//Bubble
	cout << "\nFirst 10 values of unsorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of unsorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	cout << "\nStarting string bubble sort.";
	auto strBubStart{ high_resolution_clock::now() };
	strSorter.BubbleSort(strArr, 25000);
	auto strBubStop{ high_resolution_clock::now() };
	auto strBubMs{ duration_cast<milliseconds>(strBubStop - strBubStart).count() };
	cout << "\nFinished string bubble sort with an elapsed time of " << strBubMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of sorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	for (int i = 0; i < 25000; ++i) {
		strArr[i] = tempStrArr[i];
	}

	//Comb 11
	cout << "\nFirst 10 values of unsorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of unsorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	cout << "\nStarting string comb 11 sort.";
	auto strComStart{ high_resolution_clock::now() };
	strSorter.Comb11(strArr, 25000);
	auto strComStop{ high_resolution_clock::now() };
	auto strComMs{ duration_cast<milliseconds>(strComStop - strComStart).count() };
	cout << "\nFinished string comb 11 sort with an elapsed time of " << strComMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of sorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	for (int i = 0; i < 25000; ++i) {
		strArr[i] = tempStrArr[i];
	}

	//Insertion
	cout << "\nFirst 10 values of unsorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of unsorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	cout << "\nStarting string insertion sort.";
	auto strInsStart{ high_resolution_clock::now() };
	strSorter.Insertion(strArr, 25000);
	auto strInsStop{ high_resolution_clock::now() };
	auto strInsMs{ duration_cast<milliseconds>(strInsStop - strInsStart).count() };
	cout << "\nFinished string insertion sort with an elapsed time of " << strInsMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of sorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	for (int i = 0; i < 25000; ++i) {
		strArr[i] = tempStrArr[i];
	}

	//Selection
	cout << "\nFirst 10 values of unsorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of unsorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	cout << "\nStarting string selection sort.";
	auto strSelStart{ high_resolution_clock::now() };
	strSorter.Selection(strArr, 25000);
	auto strSelStop{ high_resolution_clock::now() };
	auto strSelMs{ duration_cast<milliseconds>(strSelStop - strSelStart).count() };
	cout << "\nFinished string selection sort with an elapsed time of " << strSelMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of sorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	for (int i = 0; i < 25000; ++i) {
		strArr[i] = tempStrArr[i];
	}

	//Shaker
	cout << "\nFirst 10 values of unsorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of unsorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	cout << "\nStarting string shaker sort.";
	auto strShaStart{ high_resolution_clock::now() };
	strSorter.Shaker(strArr, 25000);
	auto strShaStop{ high_resolution_clock::now() };
	auto strShaMs{ duration_cast<milliseconds>(strShaStop - strShaStart).count() };
	cout << "\nFinished string shaker sort with an elapsed time of " << strShaMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted string array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << strArr[i];
	}
	cout << "\nLast 10 values of sorted string array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << strArr[25000 - i];
	}

	for (int i = 0; i < 25000; ++i) {
		strArr[i] = tempStrArr[i];
	}

//Person sorts
	Sort <Person> persSorter;

	//Bubble
	cout << "\nFirst 10 values of unsorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of unsorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	cout << "\nStarting Person bubble sort.";
	auto persBubStart{ high_resolution_clock::now() };
	persSorter.BubbleSort(persArr, 10000);
	auto persBubStop{ high_resolution_clock::now() };
	auto persBubMs{ duration_cast<milliseconds>(persBubStop - persBubStart).count() };
	cout << "\nFinished Person bubble sort with an elapsed time of " << persBubMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of sorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	for (int i = 0; i < 10000; ++i) {
		persArr[i] = tempPersArr[i];
	}

	//Comb 11
	cout << "\nFirst 10 values of unsorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of unsorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	cout << "\nStarting Person comb 11 sort.";
	auto persComStart{ high_resolution_clock::now() };
	persSorter.Comb11(persArr, 10000);
	auto persComStop{ high_resolution_clock::now() };
	auto persComMs{ duration_cast<milliseconds>(persComStop - persComStart).count() };
	cout << "\nFinished Person comb 11 sort with an elapsed time of " << persComMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of sorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	for (int i = 0; i < 10000; ++i) {
		persArr[i] = tempPersArr[i];
	}

	//Insertion
	cout << "\nFirst 10 values of unsorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of unsorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	cout << "\nStarting Person insertion sort.";
	auto persInsStart{ high_resolution_clock::now() };
	persSorter.Insertion(persArr, 10000);
	auto persInsStop{ high_resolution_clock::now() };
	auto persInsMs{ duration_cast<milliseconds>(persInsStop - persInsStart).count() };
	cout << "\nFinished Person insertion sort with an elapsed time of " << persInsMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of sorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	for (int i = 0; i < 10000; ++i) {
		persArr[i] = tempPersArr[i];
	}

	//Selection
	cout << "\nFirst 10 values of unsorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of unsorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	cout << "\nStarting Person selection sort.";
	auto persSelStart{ high_resolution_clock::now() };
	persSorter.Selection(persArr, 10000);
	auto persSelStop{ high_resolution_clock::now() };
	auto persSelMs{ duration_cast<milliseconds>(persSelStop - persSelStart).count() };
	cout << "\nFinished Person selection sort with an elapsed time of " << persSelMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of sorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	for (int i = 0; i < 10000; ++i) {
		persArr[i] = tempPersArr[i];
	}

	//Shaker
	cout << "\nFirst 10 values of unsorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of unsorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	cout << "\nStarting Person shaker sort.";
	auto persShaStart{ high_resolution_clock::now() };
	persSorter.Shaker(persArr, 10000);
	auto persShaStop{ high_resolution_clock::now() };
	auto persShaMs{ duration_cast<milliseconds>(persShaStop - persShaStart).count() };
	cout << "\nFinished Person shaker sort with an elapsed time of " << persShaMs << " milliseconds.";

	cout << "\nFirst 10 values of sorted Person array:";
	for (int i = 0; i < 10; ++i) {
		cout << endl << persArr[i].GetName();
	}
	cout << "\nLast 10 values of sorted Person array:";
	for (int i = 10; i > 0; --i) {
		cout << endl << persArr[10000 - i].GetName();
	}

	for (int i = 0; i < 10000; ++i) {
		persArr[i] = tempPersArr[i];
	}

	//Converting the ms to secs
	int intBubSec = intBubMs / 1000;
	int intComSec = intComMs / 1000;
	int intInsSec = intInsMs / 1000;
	int intSelSec = intSelMs / 1000;
	int intShaSec = intShaMs / 1000;

	int strBubSec = strBubMs / 1000;
	int strComSec = strComMs / 1000;
	int strInsSec = strInsMs / 1000;
	int strSelSec = strSelMs / 1000;
	int strShaSec = strShaMs / 1000;

	int persBubSec = persBubMs / 1000;
	int persComSec = persComMs / 1000;
	int persInsSec = persInsMs / 1000;
	int persSelSec = persSelMs / 1000;
	int persShaSec = persShaMs / 1000;

	//Getting the remainder
	int intBubRem = intBubMs % 1000;
	int intComRem = intComMs % 1000;
	int intInsRem = intInsMs % 1000;
	int intSelRem = intSelMs % 1000;
	int intShaRem = intShaMs % 1000;

	int strBubRem = strBubMs % 1000;
	int strComRem = strComMs % 1000;
	int strInsRem = strInsMs % 1000;
	int strSelRem = strSelMs % 1000;
	int strShaRem = strShaMs % 1000;

	int persBubRem = persBubMs % 1000;
	int persComRem = persComMs % 1000;
	int persInsRem = persInsMs % 1000;
	int persSelRem = persSelMs % 1000;
	int persShaRem = persShaMs % 1000;

	//Writing to the log file
	writer << "Name: Emely Seheon";
	writer << "\nNumber of integer values sorted by each routine: 200,000";
	writer << "\nNumber of string values sorted by each routine: 25,000";
	writer << "\nNumber of Person values sorted by each routine: 10,000";

	writer << "\nTemplate sort information for integers: ";
	writer << "\nName of sort function:        Elapsed time in sec.ms:";
	writer << "\nBubble Sort                   " << intBubSec << ":" << intBubRem;
	writer << "\nComb Sort                     " << intComSec << ":" << intComRem;
	writer << "\nInsertion Sort                " << intInsSec << ":" << intInsRem;
	writer << "\nSelection Sort                " << intSelSec << ":" << intSelRem;
	writer << "\nShaker Sort                   " << intShaSec << ":" << intShaRem;

	writer << "\nTemplate sort information for strings: ";
	writer << "\nName of sort function:        Elapsed time in sec.ms:";
	writer << "\nBubble Sort                   " << strBubSec << ":" << strBubRem;
	writer << "\nComb Sort                     " << strComSec << ":" << strComRem;
	writer << "\nInsertion Sort                " << strInsSec << ":" << strInsRem;
	writer << "\nSelection Sort                " << strSelSec << ":" << strSelRem;
	writer << "\nShaker Sort                   " << strShaSec << ":" << strShaRem;

	writer << "\nTemplate sort information for Persons: ";
	writer << "\nName of sort function:        Elapsed time in sec.ms:";
	writer << "\nBubble Sort                   " << persBubSec << ":" << persBubRem;
	writer << "\nComb Sort                     " << persComSec << ":" << persComRem;
	writer << "\nInsertion Sort                " << persInsSec << ":" << persInsRem;
	writer << "\nSelection Sort                " << persSelSec << ":" << persSelRem;
	writer << "\nShaker Sort                   " << persShaSec << ":" << persShaRem;

	writer.close();

	return 0;
}
