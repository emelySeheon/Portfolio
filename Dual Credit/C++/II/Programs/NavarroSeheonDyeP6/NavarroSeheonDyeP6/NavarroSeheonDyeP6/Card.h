#ifndef _CARD_H_
#define _CARD_H_

#include <string>
using namespace std;
class Card
{
private:
	int iValue{ 0 };
	string value; 
	string suit; 

public: 
	Card(); 
	Card(int n);
	void SetIValue(int val) { iValue = val; }
	string GetValue() const { return value; }
	int GetIValue()const { return iValue; }
	string GetSuit()const { return suit; }

};

#endif

