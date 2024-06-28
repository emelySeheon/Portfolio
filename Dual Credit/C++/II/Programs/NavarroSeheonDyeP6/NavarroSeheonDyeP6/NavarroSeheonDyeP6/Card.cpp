#include "Card.h"
#include <sstream>

Card::Card()
{

}

Card::Card(int n)
{
	//firgure out the suit and ivalue and value from a number 1 - 52
	int iSuit = int(n / 13);
	iValue = (n % 13) + 1;
	if (iValue > 10)
	{
		iValue = 10;
	}
	switch (iSuit)
	{
	case 0: 
		suit = "Spades";
		break;
	case 1: 
		suit = "Hearts";
		break;
	case 2: 
		suit = "Clubs";
		break;
	case 3: 
		suit = "Diamonds";
		break;
	}
	switch (iValue)
	{
	case 1: 
		value = "Ace";
		break;
	case 11:
		value = "Jack";
		break;
	case 12: 
		value = "Queen";
		break;
	case 13:
		value = "King";
		break;
	default: 
		stringstream ss; 
		ss << iValue;
		value = ss.str();
	}


}
