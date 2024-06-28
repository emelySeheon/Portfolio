//PersonGen.h
//This is the Person Generator class definition.
//CIS2277, Program 2

#ifndef  _PERSONGEN_H
#define  _PERSONGEN_H

#include <string>
#include <iostream>
#include "Person.h"
#include <fstream>
#include <random>

class PersonGen
{
	private:
		string name;
		int bmonth{ 1 }, bday{ 1 }, byear{ 1900 };
		int ageMin{ 4 }, ageMax{ 85 };
		int lastNameIndex{ 0 }, firstMaleIndex{ 0 }, firstFemaleIndex{ 0 };
		int fileCount{ 0 };
		int lineRead{ 0 };
		bool bGenderFlag{ false };
		bool bReadFile[3] = {}; 
		bool bCreateFile = false;
		bool bUseFile = false;
		ifstream input;
		string lname[300], female[300], male[300];

		default_random_engine engine;

		bool ReadFile(string fileName, string vn[]);
		void NameGen();
		void ReadUseFile();
		void BirthdayGen(int ageMin, int ageMax);
	public:
	//default creation, alternate male, female, age range toddler to elderly
	PersonGen();	

	//overloaded constructor, specify age range of persons
	PersonGen( int youngAge, int oldAge);	

	//returns a Person object, created locally in class, not using new
	Person GetPerson();		

	//return a Person created via the new operator
	Person* GetNewPerson();	

	//return a bool indicating that the text files were correctly opened
	//and read.  Returns false if any file was not opened and read.
	bool IsTextFileRead();
	

	//createFile creates a file with number of persons, 
	//return true if file successfully written, false if not.
	bool   CreateFile(string filename, int numOfPersons); 

	//useFile directs the generator to use the person data that is 
	//stored in the file instead of random creation. 
	//Return true indicates it was able to locate and open the file. 
	//Returns false file not found. 
	//When this method is called, the generator will only read the file 
	//line-by-line to pull out person data AS NEEDED. It DOES NOT read 
	//the entire file and store the data. If the file runs out of names, 
	//begin generating the persons randomly. 
	bool  UseFile(string filename);	
}; 
#endif
