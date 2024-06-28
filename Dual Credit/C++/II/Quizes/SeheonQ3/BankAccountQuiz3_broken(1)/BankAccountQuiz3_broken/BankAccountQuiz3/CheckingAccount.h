/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#ifndef CHECKINGACCOUNT_H
#define CHECKINGACCOUNT_H
#include "BankAccount.h"
class CheckingAccount : public BankAccount
{
public:
   /**
      Constructs a checking account with a zero balance.
   */
   CheckingAccount();
   virtual void Withdraw(double amount);
   virtual void Deposit(double amount);
   virtual void MonthEnd();
private:
    //ES: added the parenthesis for the CheckForFree method, syntax error
   void CheckForFee();
   int transactions;
};

#endif
