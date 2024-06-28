//File: StudentUser.h

#ifndef STUDENT_USER_H
#define STUDENT_USER_H

#include <memory>
#include <vector>
#include <string>
#include <stdexcept>
#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

class StudentUser
{
private:
	shared_ptr<vector<string>> resources{ nullptr };
	string name;
	bool IsInValidRequest(int position);

public:
	//constructor and destructor
	StudentUser() : resources(make_shared<vector<string>>()) {}
	~StudentUser();

	//Size operations
	int Size();
	bool IsEmpty();

	//Add and remove elements
	void PushBack(const string& str);
	void PopBack(); //to remove student user

	//Element Access 
	string& GetFirstResource();
	string& GetLastResource();

	//Position is User Number
	string& GetResourceAt(int position);

	//Reference count
	int GetReferenceCount();

	//Setters and Getters
	void SetSharedPointer(shared_ptr<vector<string>> res);
	shared_ptr<vector<string>> GetSharedPointer();

	void SetName(string newName) { name = newName; }
	string GetName() const { return name; }
};

#endif