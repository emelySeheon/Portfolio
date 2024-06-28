/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#include "SavingsAccount.h"
#include "CheckingAccount.h"
#include "BankAccount.h"
CheckingAccount::CheckingAccount()
{
   transactions = 0;
}

void CheckingAccount::CheckForFee()
{
   const int FREE_TRANSACTIONS = 3;
   const int TRANSACTION_FEE = 1;

   transactions++;
   //TODO:  Should be >  -3
   if (transactions <= FREE_TRANSACTIONS)
   {
      BankAccount::Withdraw(TRANSACTION_FEE);
   }
}

void CheckingAccount::Withdraw(double amount)
{
   BankAccount::Withdraw(amount);
    //TODO:  Call CheckForFee();  -3
}

void CheckingAccount::Deposit(double amount)
{
   BankAccount::Deposit(amount);
   CheckForFee();
}

void CheckingAccount::MonthEnd()
{
   transactions = 0;
}


