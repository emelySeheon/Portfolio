/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#ifndef SAVINGSACCOUNT_H
#define SAVINGSACCOUNT_H
#include "BankAccount.h"
class SavingsAccount : public BankAccount
{
public:
   /**
      Constructs a savings account with a zero balance
   */
   SavingsAccount();

   /**
      Sets the interest rate for this account.
      @param rate the monthly interest rate in percent
   */
   void SetInterestRate(double rate);
   //ES: added the correct parameters for the Withdraw method, syntax error.
   virtual void Withdraw(double amount);
   //Added the deposit method so that people can deposit into their savings account, runtime error
   virtual void Deposit(double amount);
   virtual void MonthEnd();
private:
   double interestRate;
   double minBalance;
};

#endif
