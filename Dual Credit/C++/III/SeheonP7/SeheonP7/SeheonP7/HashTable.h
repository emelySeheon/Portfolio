//File: HashTable.h

#ifndef _HASH_TABLE_H
#define _HASH_TABLE_H

#include "SearchableListMgr.h"
#include "Person.h"

class HashTable
{
private:
	SearchableListMgr* table;	//pointer for an array of ListMgrs (Linked Lists)
		//in Constructor do table = new SearchableListMgr[100];
	int CalculateHashKey(Person* person);

public:
	HashTable();					//constructor

	void HashFunction(Person* p);	//hash function for storing a Person
									//key into an index

	//finds and returns the Person pointer that matches exactly
	int GetValue(string name, int mm, int dd, int yy, Person** value);

	//analyze how well the table is balanced
	void AnalyzeTable(int distribution[], int total);

	//destructor
	~HashTable();
};

#endif

//Hash Function Plan:
//For my hash function, I multiply all of the digits of the birthday together (month*day*year).
//Then, I add all of the ASCII values of the name together (a+b+c etc..).
//I multiply the value of the name with the value of the birthday.
//Finally, I mod my outcome with 101 to get my key. 
//I use 101 because it is the cloest number to 100 that is prime, so numbers will be scattered more evenly.
//Because 101 is greater than 100, some of the keys will be equal to 100.
//If I find a key equal to 100, I will instead mod it with 97, the second closest prime number to 100.
//This ensures that all of my values are between and including 0-99.
//Example 1: 
//Name: SEHEON, EMELY
//Birthday: 04/26/2004
//1. Multiply the values of the birthday together: 
// 4*26*2004=208416
//2. Add the ASCII values of the name together: 
// SEHEON, EMELY = 83+69+72+69+79+78+44+32+69+77+69+76+89=906
//3. Multiply the name and the birthday:
// 208416*906=188824896
//4. Mod the result with 101:
// 188824896%101=43
//The hash key is 43

//Example 2:
//Name: BROWN, FELIX 
//Birthday: 04/12/1974
//1. Multiply the values of the birthday together: 
// 4*12*1974=94752
//2. Add the ASCII values of the name together: 
// BROWN, FELIX = 66+82+79+87+78+44+32+70+69+76+73+88=844
//3. Multiply the name and the birthday:
// 94752*844=79970688
//4. Mod the result with 101:
// 79970688%101=100
//5. 100 is out of range, so we mod it again with 97:
// 79970688%97=74
//The hash key is 74