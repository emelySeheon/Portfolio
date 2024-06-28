/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#include "SavingsAccount.h"
#include "CheckingAccount.h"
#include "BankAccount.h"
BankAccount::BankAccount()
{
   balance = 0;
}

void BankAccount::Deposit(double amount)
{
   balance = balance + amount;
}

void BankAccount::Withdraw(double amount)
{
	//ES changed the + to a - since withdrawing is taking money out, incorrect result error
   balance = balance - amount;
}

void BankAccount::MonthEnd()
{
}

double BankAccount::GetBalance() const
{
   return balance;
}


