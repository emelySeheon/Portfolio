// Name: Emely Seheon
// Email: eseheon1@cnm.edu
// File: SeheonP4

#include <iostream>
#include <fstream>
#include <chrono>
#include "Sort.h"
using namespace std::chrono;
using namespace std;

const int Size = 200000;
const string FILENAME = "BJTest200K.txt";

int main()
{
	cout << "Name: Emely Seheon" << endl;
	cout << "Program: SeheonP4" << endl;
	cout << "Objective: to sort numbers" << endl << endl;

	string outputName;
	ofstream writing;
	ifstream reading;

	//Getting the file names
	cout << "What is the output file name? ";
	cin >> outputName;

	//Making the arrays
	int* arr;
	arr = new int[Size];
	int* sorArr;
	sorArr = new int[Size];

	//Reading the file
	reading.open(FILENAME);
	if (!reading.is_open()) {
		cout << "\nUnable to open input file.";
	}
	else {
		for (int i = 0; i < Size;++i) {
			reading >> arr[i];
			sorArr[i] = arr[i];
		}
	}
	reading.close();

	//Sorting
	Sort sorter;

	//Bubble
	cout << "\nStarting bubble sort.";
	auto bubStart{ high_resolution_clock::now() };
	sorter.BubbleSort(sorArr, Size);
	auto bubStop{ high_resolution_clock::now() };
	auto bubMs{ duration_cast<milliseconds>(bubStop - bubStart).count() };
	cout << "\nFinished bubble sort with an elapsed time of " << bubMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Insertion
	cout << "\nStarting insertion sort.";
	auto insStart{ high_resolution_clock::now() };
	sorter.InsertionSort(sorArr, Size);
	auto insStop{ high_resolution_clock::now() };
	auto insMs{ duration_cast<milliseconds>(insStop - insStart).count() };
	cout << "\nFinished insertion sort with an elapsed time of " << insMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Selection
	cout << "\nStarting selection sort.";
	auto selStart{ high_resolution_clock::now() };
	sorter.SelectionSort(sorArr, Size);
	auto selStop{ high_resolution_clock::now() };
	auto selMs{ duration_cast<milliseconds>(selStop - selStart).count() };
	cout << "\nFinished selection sort with an elapsed time of " << selMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Comb 11
	cout << "\nStarting comb 11 sort.";
	auto comStart{ high_resolution_clock::now() };
	sorter.Comb11Sort(sorArr, Size);
	auto comStop{ high_resolution_clock::now() };
	auto comMs{ duration_cast<milliseconds>(comStop - comStart).count() };
	cout << "\nFinished comb 11 sort with an elapsed time of " << comMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Shaker
	cout << "\nStarting shaker sort.";
	auto shaStart{ high_resolution_clock::now() };
	sorter.ShakerSort(sorArr, Size);
	auto shaStop{ high_resolution_clock::now() };
	auto shaMs{ duration_cast<milliseconds>(shaStop - shaStart).count() };
	cout << "\nFinished shaker sort with an elapsed time of " << shaMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Shell
	cout << "\nStarting shell sort.";
	auto sheStart{ high_resolution_clock::now() };
	sorter.ShellSort(sorArr, Size);
	auto sheStop{ high_resolution_clock::now() };
	auto sheMs{ duration_cast<milliseconds>(sheStop - sheStart).count() };
	cout << "\nFinished shell sort with an elapsed time of " << sheMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//In-Place Merge
	cout << "\nStarting recursive in-place merge sort.";
	auto merStart{ high_resolution_clock::now() };
	sorter.InPlaceMergeSort(sorArr, Size);
	auto merStop{ high_resolution_clock::now() };
	auto merMs{ duration_cast<milliseconds>(merStop - merStart).count() };
	cout << "\nFinished recursive in-place merge sort with an elapsed time of " << merMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Quick
	cout << "\nStarting recursive quick sort.";
	auto quiStart{ high_resolution_clock::now() };
	sorter.QuickSort(sorArr, Size);
	auto quiStop{ high_resolution_clock::now() };
	auto quiMs{ duration_cast<milliseconds>(quiStop - quiStart).count() };
	cout << "\nFinished recursive quick sort with an elapsed time of " << quiMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}
	
	//Goofy
	cout << "\nStarting goofy sort.";
	auto gooStart{ high_resolution_clock::now() };
	sorter.GoofySort(sorArr, Size);
	auto gooStop{ high_resolution_clock::now() };
	auto gooMs{ duration_cast<milliseconds>(gooStop - gooStart).count() };
	cout << "\nFinished goofy sort with an elapsed time of " << gooMs << " milliseconds.";

	for (int i = 0; i < Size; ++i) {
		sorArr[i] = arr[i];
	}

	//Outputting information
	writing.open(outputName);
	if (!writing.is_open()) {
		cout << "\nUnable to open output file.";
	}
	else {
		writing << "Name: Emely Seheon" << "\nNumber of integer values sorted by each routine: " << Size;
		writing << "\nName of sort function:        Elapsed time in milliseconds:";
		writing << "\nBubble Sort                   " << bubMs;
		writing << "\nComb Sort                     " << comMs;
		writing << "\nGoofy Sort                    " << gooMs;
		writing << "\nIn-Place Merge Sort           " << merMs;
		writing << "\nInsertion Sort                " << insMs;
		writing << "\nRecursive Quick Sort          " << quiMs;
		writing << "\nSelection Sort                " << selMs;
		writing << "\nShaker Sort                   " << shaMs;
		writing << "\nShell Sort                    " << sheMs;
	}
}