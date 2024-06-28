//File: HashTable.cpp

#include "HashTable.h"

int HashTable::CalculateHashKey(Person* person)
{
	//Do the hashing calculation and make sure it fits the table size
	//return the index
	string name = person->GetName();
	int birth = 0;
	birth =	person->GetBirthDay().GetDay() * person->GetBirthDay().GetMonth() * person->GetBirthDay().GetYear();
	int  value = 0;
	for (int i = 0; i < name.size(); ++i) {
		value = (value + name.at(i));
	}
	value = value * birth;
	int holder = value;
	value = value % 101;
	if (value == 100) {
		value = holder % 97;
	}
	return value;
}

HashTable::HashTable()
{
	table = new SearchableListMgr[100];
}

void HashTable::HashFunction(Person* p)
{
	int index;
	index = CalculateHashKey(p);
	if (index > -1 && index < 100) {
		table[index].push_back(p);
	}
	else {
		cout << endl << index;
	}
}

int HashTable::GetValue(string name, int mm, int dd, int yy, Person** value)
{
	int hashKey;
	hashKey = CalculateHashKey(*value);
	int index;
	index = table[hashKey].IsPresent(name, mm, dd, yy, value);
	return index;
}

void HashTable::AnalyzeTable(int distribution[], int total)
{
	for (int i = 0; i < total; i++) {
		distribution[i] = table[i].size();
	}
}

HashTable::~HashTable()
{
}
