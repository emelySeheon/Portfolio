#include "Game.h"
#include<fstream>
#include <sstream>
#include <vector>
#include <iostream>
#include <chrono>
#include <limits> 
#include <iomanip>
using namespace std;

Game::Game()
{
    log.StartLog(money);
}

bool Game::SetBet(int b)
{
    if (b > 0 && b < money)
    {
        bet = b;
        numberOfBets++;
        return true;
    }
    else
    {
        return false;
    }
}

void Game::InitialDeal()
{
    //deck.Shuffle();
    //dealersHand.AddCard(deck.Deal());
    //dealersHand.AddCard(deck.Deal());
    //playersHand.AddCard(deck.dealCard());
    //playersHand.AddCard(deck.dealCard());

    for (int i = 0; i < 2; i++)
    {
        Card card;
        deck.Deal(card);
        dealersHand.AddCard(card);
    }

    for (int i = 0; i < 2; i++)
    {
        Card card;
        deck.Deal(card);
        playersHand.AddCard(card);
    }
    //*need to check this with the group
    /*for (auto times{ 0 }; times < 2; times++)
    {      
        for (size_t p{ 0 }; p < Players::getPlayerCount(); p++)
        {
            playersHand[p].addCard(0, gameDeck.drawCard());
        }
        dealer.addCard(0, gameDeck.drawCard());
    }*/
}

string Game::ShowPlayerHand()
{
    return playersHand.Show(false, false);
}

string Game::ShowDealersHand(bool hide)
{
    return dealersHand.Show(true, hide);
}

bool Game::IsBlackJack()
{ 
    return playersHand.BlackJack(); 
}

bool Game::PlayerBusted()
{
    return playersHand.Busted();
}

bool Game::PlayerContinues()
{
    if ((!PlayerBusted()) && playersHand.Under(22))
    {
        return true; 
    }
    else
    {
        return false;
    }
}

void Game::PlayerHits()
{
    Card card;
    deck.Deal(card);
    playersHand.AddCard(card);
}

string Game::PlayerWins()
{    
    money += (bet * 1.5);
    stringstream ss;
    ss << playersHand.Show(false, false) << "\r\n" << dealersHand.Show(true, false) << "\r\n"
        << "Player wins: " << bet * 1.5 << "\r\n" << " Player remaining balance: $" << money << "\r\n \r\n";
    log.WriteLog(ss.str());
    wins++;
    return ss.str();
}

bool Game::DealerContinues()
{
    if (dealersHand.MustHit())
    {
        Card card;
        deck.Deal(card);
        dealersHand.AddCard(card);
        return true;
    }    
    return false;
}

string Game::DealerWins()
{
    stringstream ss; 
    money = money - bet; 
    if (money < 0) money = 0;
    ss << playersHand.Show(false, false) << "\r\n" << dealersHand.Show(true, false) << "\r\n"
        << "Player losses: $" << bet << "\r\n" << " Player remaining balance: $"<< money << "\r\n \r\n";
    log.WriteLog(ss.str());
    losses++;
    return ss.str();
}

string Game::Tie()
{
    stringstream ss;
    ss << playersHand.Show(false, false) << "\r\n" << dealersHand.Show(true, false) << "\r\n"
        << "Tie, bet: " << bet << "\r\n" << " Player remaining balance: $" << money << "\r\n \r\n";
    log.WriteLog(ss.str());
    ties++;
    return ss.str();
}

string Game::NoResults()
{
    return "" ;
}

string Game::ShowResults()
{
    if (playersHand.Busted() && dealersHand.Busted())
    {
        return Tie();
    }
    else if(playersHand.Busted())
    {
        return DealerWins();
    }
    else if (dealersHand.Busted())
    {
        return PlayerWins();
    }
    else if (dealersHand.BestScore() > playersHand.BestScore())
    {
        return DealerWins();
    }
    else if (playersHand.BestScore() >= dealersHand.BestScore())
    {
        return PlayerWins();
    }
    else
    {
        return NoResults();
    }
}

void Game::ClearHands()
{
    playersHand.ClearHand();
    dealersHand.ClearHand();
}


void Game::EndGame()
{
    stringstream ss; 
    ss << "Wins: " << wins
        << "\r\nLosses: " << losses
        << "\r\nTies: " << ties
        << "\r\nThanks for playing!";
    log.CloseLog(ss.str());
}
