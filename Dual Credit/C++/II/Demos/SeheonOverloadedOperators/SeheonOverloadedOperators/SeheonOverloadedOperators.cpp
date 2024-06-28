//Emely Seheon 
//eseheon1@cnm.edu
//11/9/20
//SeheonOverloadedOperators
//SeheonOverloadedOperators is a program to demonstrate overloaded operators

#include <iostream> 
#include "Time.h"
using namespace std;

int main() {
	cout << "\n Let's say you are scheduling your day";
	cout << "\n On Tuesday, you get up at 7:45 am. You have a class at 9:30 am.";
	cout << "\nHow long do you have to get up and to class?";
	Time awake{ 7, 45 };
	Time getToClass{ 9, 30 };
	int timeToGetThere = getToClass - awake;
	cout << "\n The time to get ready for class is  " << timeToGetThere
		<< " minutes.";

	Time getUpEarly = awake-15;
	cout << "\n The time to get up and be there 15 minutes early for class is  " << getUpEarly.GetFormattedString();
	cout << "\n I need to organize my appointments for Friday.  There are four. ";

	Time walk{ 8, 0 };
	Time meeting{ 15, 0 };
	Time lunch{ 12, 0 };
	Time dinner{ 18, 30 };
	Time appts[] = { meeting, lunch, dinner, walk };

	Time temp;
	int total{ 4 };
	for (int i = 0; i < total - 1; ++i)
	{
		for (int j = 1; j < total; ++j)
			{
			if (appts[j - 1] > appts[j])
				{
				temp = appts[j];
				appts[j] = appts[j - 1];
				appts[j - 1] = temp;
				}
			}
		}

	cout << "\n My Friday schedule:\n";
	for (int x = 0; x < total; ++x)
		{
		cout << appts[x].GetFormattedString() << endl;
		}
	cout << "\n But I have to walk today.  My car is not running!";
	cout << "\n That will take me twice as long to get to school.";
	cout << "\n I hope I can get there in time if I get up at 6:30am";

	Time getUp{ 6, 30 };
	Time itWillTake { getUp + timeToGetThere * 2 };
	if (itWillTake < getToClass)
	{
		cout << "\n I made it!";
			}
	else
	{
		cout << "\n Whoops!  tardy again.";
	}
	cout << "\n I will be there at"  << itWillTake.GetFormattedString();

}