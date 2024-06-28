// Darian Rackham -- drackham@cnm.edu
// 
// PersonGen.h -- Person Generator class definition.
//

#ifndef  _PERSONGEN_H
#define  _PERSONGEN_H

#include <string>
#include "Person.h"
#include <fstream>
#include <random>
#include <chrono>


constexpr int NAME_LIST_SIZE{ 300 };
constexpr int GIRL{ 1 };
constexpr int BOY{ 0 };

class PersonGen
{
	private:
		int minAge{ 4 }, maxAge{ 85 };

		bool bFilesOK{ false };
		string girlNames[NAME_LIST_SIZE];
		string boyNames[NAME_LIST_SIZE];
		string lastNames[NAME_LIST_SIZE];

		bool bUsePersonFile{ false };
		ifstream personFile{};
		int personsRemaining{ 0 };

		// Use the 64-bit form of the default random number engine.
		// Avoids a narrowing conversion error when seeding with 64-bit time. 
		mt19937_64 engine;
		uniform_int_distribution<> gender{ 0, 1 };
		uniform_int_distribution<> nameIndex{ 0, NAME_LIST_SIZE - 1 };

		// Private methods
		void ReadNameFiles();
		Date BirthdayGen();
		string NameGen();
		string GirlNameGen();
		string BoyNameGen();
		string LastNameGen();
		Person GetPersonFromFile();

	public:

		// Default generator makes persons between minAge and maxAge.
		// Constructors both assign a random binary gender.
		PersonGen();	
		PersonGen( int youngAge, int oldAge);	

		// returns a random person object on the stack
		Person GetPerson();		

		// returns pointer to a random person in the free store
		Person* GetNewPerson();	

		// return whether or not all name files were read correctly
		bool IsTextFileRead() { return bFilesOK; };

		// Creates a file with specified number of random people.
		// Returns true if file was opened and closed successfully.
		bool CreateFile(string filename, int numOfPersons); 

		// Specify file of persons to use.
		// Returns true if file opens and has a positive integer on the first line.
		bool UseFile(string filename);
}; 
#endif
