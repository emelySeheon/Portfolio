#ifndef _HAND_H_
#define _HAND_H_
#include "Card.h"
#include <array>

using namespace std;

const int MAX_CARDS = 12;

class Hand
{
private: 
	int numCards{ 0 };
	string ShowHand;
	array<Card, MAX_CARDS> cards;

public: 
	Hand() {}
	void AddCard(Card c);
	string Show(bool isdeal, bool hideFirstCard);
	bool BlackJack(); 
	bool Under(int n);
	int BestScore();
	bool MustHit();
	bool Busted(); 
	void ClearHand();
};

#endif