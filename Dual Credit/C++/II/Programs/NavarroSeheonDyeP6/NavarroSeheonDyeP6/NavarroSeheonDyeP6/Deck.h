#ifndef _DECK_H
#define _DECK_H

#include "Card.h"
#include <array>
#include <random>

using namespace std;

class Deck
{
private: 
	array<int, 52> cards;
	int topCard{ 0 };
	default_random_engine engine;
public: 
	Deck(); 
	void Shuffle(); 
	int RandomCard(); 
	void Deal(Card& c);

};

#endif

