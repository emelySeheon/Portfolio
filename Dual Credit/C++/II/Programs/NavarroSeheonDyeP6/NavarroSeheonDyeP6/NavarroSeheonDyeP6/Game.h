#ifndef _GAME_H
#define _GAME_H

#include <string>
#include"Deck.h"
#include"Hand.h"
#include"Logger.h"
using namespace std;

class Game
{
	private:
		int bet{ 0 };	 
		double money{ 1000.0 };	
		int wins, losses, ties, numberOfBets; 
		Deck deck;	
		Hand playersHand;	
		Hand dealersHand;	
		Logger log;		

	public:
		Game();
		bool SetBet(int b);	
		void InitialDeal();
		string ShowPlayerHand();
		string ShowDealersHand(bool hide);
		bool IsBlackJack();
		bool PlayerBusted(); 
		bool PlayerContinues(); 
		void PlayerHits(); 
		string PlayerWins();  
		bool DealerContinues(); 
		string DealerWins(); 
		string Tie(); 
		string NoResults(); 
		string ShowResults(); 		
		void ClearHands();
		bool  IsLogOpened() { return log.IsLogOpen(); }
		void EndGame();  

};

#endif