#include <sstream>;
#include <chrono>
#include <iomanip>
#include <sstream>
#include "Person.h";

void Person::CalcAge()
{
	//Declaring/Initializing variables
	string currentTime;
	int currentMonth;
	int currentDay;
	int currentYear;
	int birthMonth = bday.GetMonth();
	int birthDay = bday.GetDay();
	int birthYear = bday.GetYear();
	stringstream year;
	stringstream month;
	stringstream day;
	int y;

	//Getting current date
	//Method from https://gist.github.com/niranjanmalviya/671bb0ca9dcc16093538f0cdf8cbc873
	tm now_tm;
	errno_t error;
	chrono::time_point<chrono::system_clock> timepoint = chrono::system_clock::now();
	time_t time_now_t = chrono::system_clock::to_time_t(timepoint);
	error = localtime_s(&now_tm, &time_now_t);
	if (error != 0) {
		age = 0;
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
		year << currentTime[i];
	}
	for (int i = 0; i < 2; ++i) {
		month << currentTime[i + 5];
	}
	for (int i = 0; i < 2; ++i) {
		day << currentTime[i + 8];
	}

	//Turning the stringstreams into ints
	currentYear = stoi(year.str());
	currentMonth = stoi(month.str());
	currentDay = stoi(day.str());

	//Setting the age
	y = currentYear - birthYear;
	if (currentMonth < birthMonth)
	{
		--y;
	}
	else if (currentMonth == birthMonth && currentDay < birthDay) {
		--y;
	}
	age = y;

	//If birthday is in the future
	if (currentYear < birthYear) {
		age = 0;
	}
	else if (currentYear == birthYear && currentMonth < birthMonth) {
		age = 0;
	}
	else if (currentYear == birthYear && currentMonth == birthMonth && currentDay < birthDay) {
		age = 0;
	}
}

Person::Person(string p, Date d) : name{ p }, bday{ d }
{
	bday.SetDesc(name);
	CalcAge();
}

Person::Person(string p, int m, int d, int y) : name{ p }, bday{ m, d, y, p }
{
	CalcAge();
}

void Person::SetBirthday(Date d)
{
	bday = d;
	CalcAge();
}

void Person::SetBirthday(int m, int d, int y)
{
	bday.SetDate(m, d, y, name);
	CalcAge();
}

string Person::GetNameAge()
{
	stringstream ss;
	ss << endl << name << " is " << age << " years old.";
	return ss.str();
}

string Person::GetFullDesc()
{
	stringstream ss;
	ss << endl << bday.GetFormattedDate() << "." << " They are " << age << " years old.";
	return ss.str();
}

Date Person::GetBirthDay()
{
	return bday;
}

bool Person::operator<(Person p)
{
	if (name < p.name)
		return true;
	else {
		return false;
	}
}

bool Person::operator>(Person p)
{
	return name > p.name;
}
