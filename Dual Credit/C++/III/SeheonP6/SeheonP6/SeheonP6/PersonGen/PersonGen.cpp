#include "PersonGen.h"
#include <sstream>
#include <chrono>
#include <iomanip>
#include <fstream>
#include <iostream>
#include <string>
#include <random>

using namespace std;

void PersonGen::ReadFile()
{
	//Reading Female First Names into an array
	ifstream ff("FirstFemale.txt");
	string name;
	int i = 0;

	if (!ff.is_open()) {
		fileRead = false;
	}
	else {
		while (getline(ff, name)) {
			femaleFirst[i] = name;
			++i;
		}
		i = 0;
		ff.close();
	}

	//Reading Male First Names into an Array
	ifstream mf("FirstMale.txt");
	if (!mf.is_open()) {
		fileRead = false;
	}
	else {
		while (getline(mf, name)) {
			maleFirst[i] = name;
			++i;
		}
		i = 0;
		mf.close();
	}

	//Reading Last Names into an Array
	ifstream l("Last.txt");
	if (!l.is_open()) {
		fileRead = false;
	}
	else {
		while (getline(l, name)) {
			lastNames[i] = name;
			++i;
		}
		i = 0;
		l.close();
	}
}

void PersonGen::ReadUseFile()
{
	if (lineRead >= fileCount) {
		fileProvided = false;
		file.close();
		NameGen();
		BirthGen(yAge, oAge);
	}
	else {
		string lastN;
		string firstN;
		char c;
		file >> lastN >> firstN >> c >> month >> c >> day >> c >> year;
		stringstream ss;
		ss << lastN << " " << firstN;
		fullName = ss.str();
		lineRead++;
	}
}

void PersonGen::NameGen()
{
	stringstream ss;
	int gender;
	int randFirst;
	int randLast;

	uniform_int_distribution<int> gen(0, 1);
	uniform_int_distribution<int> randomFirst(0, 299);
	uniform_int_distribution<int> randomLast(0, 299);
	gender = gen(engine);
	randFirst = randomFirst(engine);
	randLast = randomLast(engine);

	//0=female 1=male
	if (gender == 0) {
		ss << lastNames[randLast] << ", " << femaleFirst[randFirst];
	}
	else {
		ss << lastNames[randLast] << ", " << maleFirst[randFirst];
	}
	fullName = ss.str();

}

void PersonGen::BirthGen(int young, int old)
{
	string currentTime;
	stringstream y;
	int currentYear;

	//Getting current date
	//Method from https://gist.github.com/niranjanmalviya/671bb0ca9dcc16093538f0cdf8cbc873
	tm now_tm;
	errno_t error;
	chrono::time_point<chrono::system_clock> timepoint = chrono::system_clock::now();
	time_t time_now_t = chrono::system_clock::to_time_t(timepoint);
	error = localtime_s(&now_tm, &time_now_t);
	if (error != 0) {
		exit;
	}
	else {
		ostringstream ss;
		ss << put_time(&now_tm, "%FT%T%z"); // ISO 8601 format
		currentTime = ss.str();
	}

	//putting the current year, day, and month in a stringstream
	//format: 2018-07-07T23:20:52+0530
	for (int i = 0; i < 4; ++i) {
		y << currentTime[i];
	}

	//Turning the stringstream into int
	currentYear = stoi(y.str());

	int gender;
	int randFirst;
	int randLast;

	//year
	uniform_int_distribution<int> yy(currentYear - old, currentYear - young);
	year = yy(engine);

	//month
	uniform_int_distribution<int> m(1, 12);
	month = m(engine);

	//day
	if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
		uniform_int_distribution<int> d(1, 31);
		day = d(engine);
	}
	else if (month == 4 || month == 6 || month == 9 || month == 11) {
		uniform_int_distribution<int> d(1, 30);
		day = d(engine);
	}
	else {
		//leap
		if (currentYear % 4 == 0 && currentYear % 100 != 0) {
			uniform_int_distribution<int> d(1, 29);
			day = d(engine);
		}
		//leap
		else if (currentYear % 400 == 0) {
			uniform_int_distribution<int> d(1, 29);
			day = d(engine);
		}
		//Not leap
		else {
			uniform_int_distribution<int> d(1, 28);
			day = d(engine);
		}
	}
}

PersonGen::PersonGen()
{
	ReadFile();
}

PersonGen::PersonGen(int youngAge, int oldAge) : yAge{ youngAge }, oAge{ oldAge }
{
	ReadFile();
}

Person PersonGen::GetPerson()
{
	Person generated;
	if (!fileProvided) {
		NameGen();
		BirthGen(yAge, oAge);
	}
	else {
		ReadUseFile();
	}

	generated.SetName(fullName);
	generated.SetBirthday(month, day, year);

	return generated;
}

Person* PersonGen::GetNewPerson()
{
	Person* newGenerated = new Person();
	if (!fileProvided) {
		NameGen();
		BirthGen(yAge, oAge);
	}
	else {
		ReadUseFile();
	}

	newGenerated->SetName(fullName);
	newGenerated->SetBirthday(month, day, year);

	return newGenerated;
}

bool PersonGen::IsTextFileRead()
{
	return fileRead;
}

bool PersonGen::CreateFile(string filename, int numOfPersons)
{
	bool success = false;
	ofstream output(filename.c_str());
	if (!output.is_open()) {
		success = false;
	}
	else {
		output << numOfPersons << "\n";
		for (int i = 0; i < numOfPersons;++i) {
			if (!fileProvided) {
				NameGen();
				BirthGen(yAge, oAge);
			}
			else {
				ReadUseFile();
			}
			output << fullName << " | " << month << " | " << day << " | " << year << "\n";
			success = true;
		}
	}
	return success;
}

bool PersonGen::UseFile(string filename)
{
	file.open(filename.c_str());
	if (file.is_open()) {
		fileProvided = true;
		file >> fileCount;
		lineRead = 0;
		return true;
	}
	else {
		fileProvided = false;
		return false;
	}
}
