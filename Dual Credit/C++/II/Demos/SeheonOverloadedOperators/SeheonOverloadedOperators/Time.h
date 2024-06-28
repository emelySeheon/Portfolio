#ifndef TIME_H
#define TIME_H

#include <string>
using namespace std;

class Time
{
private:
	int hours{ 0 };
	int minutes{ 0 };
public:
	Time();
	Time(int h, int m) : hours{ h }, minutes{ m }{};
	int GetHours()const { return hours; }
	int GetMinutes()const { return minutes; }
	int operator-(const Time& other) const;
	int operator+(const Time& other) const;	
	int operator+(int mins);
	int operator-(int mins);
	bool operator==(const Time& other) const;
	bool operator!=(const Time& other) const;
	bool operator>(const Time& other) const;
	bool operator<(const Time& other)const;
	Time operator++();
	Time operator++(int dummy);
	string GetFormattedString() const;
};

#endif