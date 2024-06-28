/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#include "SavingsAccount.h"
#include "CheckingAccount.h"
#include "BankAccount.h"
SavingsAccount::SavingsAccount()
{
   interestRate = 0;
   minBalance = 0;
}

void SavingsAccount::SetInterestRate(double rate)
{
   rate = interestRate;
}

void SavingsAccount::Withdraw(double amount)
{
   BankAccount::Withdraw(amount);
   //ES: declared balance as a double so the program recognizes it, syntax error
   double balance = GetBalance();
   if (balance < minBalance)
   {
      minBalance = balance;
   }
}

//Added the deposit method so that people can deposit into their savings account, runtime error
void SavingsAccount::Deposit(double amount)
{
    BankAccount::Deposit(amount);
}

void SavingsAccount::MonthEnd()
{
   double interest = minBalance * interestRate / 10;
   Deposit(interest);
   minBalance = GetBalance();
}


