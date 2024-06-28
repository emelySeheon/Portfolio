//File: Search.h

#ifndef _SORT_H
#define _SORT_H

#include "Person.h";
#include <iostream>

using namespace std;

class Search
{
private:
	bool RecursiveBinarySearch(Person folks[], string target, int first, int last, int* position, int* pNumCompares);

public:
	Search() = default;
	bool SequentialSearch(Person folks[], int total, string target, int* position, int* pNumCompares);
	bool BinarySearch(Person folks[], int total, string target, int* position, int* pNumCompares);
	bool RunRecursiveSearch(Person folks[], int total, string target, int* position, int* pNumCompares);
	string FormatName(string first, string last);
};

#endif