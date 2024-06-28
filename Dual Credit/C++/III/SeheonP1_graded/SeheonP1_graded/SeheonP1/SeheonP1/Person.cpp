#include <sstream>;
#include <chrono>
#include <iomanip>
#include <sstream>
#include "Person.h";

//TODO: This is a lot of code, but I am impressed. It sure works. Good research.
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
		month << currentTime[i+5];
	}
	for (int i = 0; i < 2; ++i) {
		day << currentTime[i+8];
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
//TODO: Use the constructor initializer, that gives initialization instead of -5
//TODO: assignment and you don't take the time to call two methods
Person::Person(string p, Date d): name{p}, bday{d}
//Person::Person(string p, Date d)
{
	//TODO:  now bday has the d information, just need to assign name to desc
	//TODO:  of the bday
	bday.SetDesc(name);
	//SetName(p);
	//SetBirthday(d);
	CalcAge();
}
//TODO:  Same here:
Person::Person(string p, int m, int d, int y) : name{ p }, bday { m, d, y, p }

//TODO:  Or you can delegate constructors (not required in spec) and do this:
//Person::Person(string p, int m, int d, int y) : Person( p, Date(m, d, y, p) )

//Person::Person(string p, int m, int d, int y)
{
	//SetName(p);
	//SetBirthday(m, d, y);
	CalcAge();
}

void Person::SetBirthday(Date d)
{
	bday = d;
	//TODO:  Now call CalcAge(); "Once a person’s information is passed  -2
	//TODO: into the object, the class automatically determines and 
	//TODO:  assigns the person’s age. "
}

void Person::SetBirthday(int m, int d, int y)
{
	//TODO:  This will give you the "old" name, but that is 
	//TODOL  probably no worse than ""
	bday.SetDate(m, d, y, name);
	//TODO:  Now call CalcAge();
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
//TODO:  I see what you did here.  This is probably not the best approach.  -5
//TODO:  In effect, you are returning after testing only one character
//TODO:  and your objects are backwards. 
//TODO:  If you want to do this yourself, it is better to use the compare method. 
//TODO:  However, someone else has overloaded the string class already to do this
//TODO:  comparison, so we can take advantage of that:
bool Person::operator<(Person p)
{
	if (name < p.name)
		return true;
	else
	//for (int i = 0; i < name.length(); ++i) {
	//	//If name A < name B
	//	if (p.GetName()[i] < name[i]) {
	//		return true;
	//	}
	//	//If name A > name B
	//	else if (p.GetName()[i] > name[i]) {
	//		return false;
	//	}
	//}
	return false;
}

bool Person::operator>(Person p)
{

	//TODO:  even shorter:
	return name > p.name;

	//for (int i = 0; i < name.length(); ++i) {
	//	//If name A > name B
	//	if (p.GetName()[i] > name[i]) {
	//		return true;
	//	}
	//	//If name A < name B
	//	else if (p.GetName()[i] < name[i]) {
	//		return false;
	//	}
	//}
	//return false;
}
