#include "Hand.h"
#include <sstream>

void Hand::AddCard(Card c)
{
    cards[numCards] = c;
    numCards++;
    //assign c to the array using the index of numCards
    //increment numCards
}

string Hand::Show(bool isdeal, bool hideFirstCard)
{
    stringstream ss; 
    int i = 0;
    if (isdeal)
    {
        ss << "Dealer ";
    }
    else
    {
        ss << "Player ";
    }
    if (hideFirstCard) {
        ss << "Hidden ";
        i++;
    }
    for (i; i < numCards; i++)
    {
        ss << cards[i].GetValue() << " of " << cards[i].GetSuit() << ", ";
    }
    //put either dealer or player at the beginning of the string
    //add the cards held to the string. if hideFirstcard is true add Hidden. 
    return ss.str();
}

bool Hand::BlackJack()
{
    //check if had == 21 and has only 2 cards.

    if (BestScore() == 21 && numCards == 2)
        return true;
    return false;
}

bool Hand::Under(int n)
{
    //add up  the points in the hand and see if they are under.
    int best = 0;
    for (int i = 0; i < numCards; i++)
    {
        best += cards[i].GetIValue();
    }
    if (best < n) return true;

    return false;
}

int Hand::BestScore()
{
    //add up the score check if there is an ace. use loop if more than one ace
    //add up point with ace == 11 and ace == 1s
    bool ace = false; 
    int best = 0; 
    for (int i = 0; i < numCards; i++)
    {
        if (cards[i].GetIValue() == 1)
        {
            ace = true; 
        }
        else {
            best += cards[i].GetIValue();
        }
    }
    if (ace) 
    {
        int bestLow = best + 1; 
        int bestHigh = best + 11; 
        if (bestHigh < 22)
        {
            return bestHigh;
        }
        else
        {
            return bestLow;
        }
    }

    return best;
}

bool Hand::MustHit()
{
    //check is best score is < 17 if so hit
    if (BestScore() < 17)
        return true;
    return false;
}

bool Hand::Busted()
{
    //if under 22 is not true busted = false
    int bestLow = 0;
    for (int i = 0; i < numCards; i++)
    {
        bestLow += cards[i].GetIValue();
    }
    if (bestLow > 21)
        return true;
    return false;
}

void Hand::ClearHand()
{
    numCards = 0;
    ShowHand = "";
    //numCards = 0; 
    //showHad = "";
}
