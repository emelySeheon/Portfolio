/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
#ifndef BANKACCOUNT_H
#define BANKACCOUNT_H
// The accounts program with the CheckingAccount class modified to limit transactions
// to a set number before hitting the user with a fee. Isnt that typical?

#include <iomanip>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

class BankAccount
{
public:
   /**
      Constructs a bank account with zero balance.
   */
   BankAccount();

   /**
      Makes a deposit into this account.
      @param amount the amount of the deposit.
   */
   virtual void Deposit(double amount);

   /**
      Makes a withdrawal from this account, or charges a penalty if
      sufficient funds are not available.
      @param amount the amount of the withdrawal.
   */
   void Withdraw(double amount);

   /**
      Carries out the end of month processing that is appropriate
      for this account.
   */
   virtual void MonthEnd() = 0;

   /**
      Gets the current balance of this bank account.
      @return the current balance
   */
   double GetBalance() const;
private:
   double balance;
};

#endif
