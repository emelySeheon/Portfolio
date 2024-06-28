#include "Deck.h"
#include <chrono>

Deck::Deck()
{
    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
    engine.seed(seed);

    for (int i = 0; i < 52; i++)
    {
        cards[i] = i;
    }
    Shuffle();
}

void Deck::Shuffle()
{
    for (int i = 0; i < 52; i++)
    {
        int switchWith = RandomCard();
        int temp = cards[switchWith];
        cards[switchWith] = cards[i];
        cards[i] = temp;
    }

}

int Deck::RandomCard()
{
    uniform_int_distribution<int> dist(0, 51);
    return dist(engine);
}

void Deck::Deal(Card& c)
{
    Card card(cards[topCard]);
    c = card; 
    topCard++;
    if (topCard == 35)
    {
        Shuffle();
        topCard = 0;
    }
}
