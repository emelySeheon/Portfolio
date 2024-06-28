#include "Time.h"

#include <iostream>
#include <iomanip>
#include <sstream>
#include <string>
using namespace std;

Time::Time()
{
}

int Time::operator-(const Time& other) const
{
	return hours * 60 + minutes - (other.hours * 60 + other.minutes);
}

int Time::operator+(const Time& other) const
{
	return hours * 60 + minutes + (other.hours * 60 + other.minutes);
}

int Time::operator+(int mins)
{
	int resultMinutes = hours * 60 + minutes + mins;
	return Time(resultMinutes / 60, resultMinutes % 60);

}

int Time::operator-(int mins)
{
	int resultMinutes = hours * 60 + minutes - mins;
	return Time(resultMinutes / 60, resultMinutes % 60);

}

bool Time::operator==(const Time& other) const
{
	return *this - other == 0;
}

bool Time::operator!=(const Time& other) const
{
	return *this - other != 0;
}

bool Time::operator>(const Time& other) const
{
	return(minutes + hours * 60 > other.minutes * other.hours * 60);
}

bool Time::operator<(const Time& other) const
{
	return(minutes + hours * 60 < other.minutes * other.hours * 60);
}

Time Time::operator++()
{
	return *this+1;
}

Time Time::operator++(int dummy)
{
	Time original = *this;
	*this = *this + 1;
	return original;
}

string Time::GetFormattedString() const
{
	stringstream ss;
	ss << hours << ":";
	if (minutes < 10) {
		ss << 0;
	}
	ss << minutes << endl;

	return ss.str();
}
