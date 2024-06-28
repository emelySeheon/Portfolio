/*Debugged by: Emely Seheon (eseheon1@cnm.edu)
* Date: 12/5/20
* CIS 2275
*/


//SOLUTION
/*
	This program checks to see that fees are assesed for
	too much activity on a checking account only.
*/
#include <iostream>
#include <string>
using namespace std;
#include "SavingsAccount.h"
#include "CheckingAccount.h"
#include "BankAccount.h"
int main()
{
   cout << fixed << setprecision(2);
   cout << "\n Hello, this is a simple polymorphic bank account program.  "
	   << "\n It demonstrates how to use inheritance when the base class member variables are kept private."
	   <<"\n Please make it work.\n";

   // Create accounts
   const int ACCOUNTS_SIZE = 10;
   BankAccount* accounts[ACCOUNTS_SIZE];  
   //ES Added the * so that accounts[0] can be set to equal checkAcct, syntax
   //ES initialized checkAcct, runtime error
   CheckingAccount* checkAcct = new CheckingAccount();
   accounts[0] = checkAcct;
   //ES Added the * so that accounts[1] can be set to equal checkAcct, syntax
   //ES initialized saveAcct, runtime error
   SavingsAccount* saveAcct = new SavingsAccount();
   //ES changed the . to ->, syntax error
   saveAcct->SetInterestRate(0.75);
   accounts[1] = saveAcct;
   
   // Execute commands
   bool more = true;
   int num;
   while (more)
   {
	   cout << "\n Access your banking accounts.";
	  cout << "\n Enter D)eposit W)ithdraw B)alance or Q)uit: ";
	  string input;
	  cin >> input;
	  if (input == "D" || input == "W")   // Deposit or withdrawal
	  {
		 cout << "Enter 1 for Checking and 2 for Savings and then the amount: $";
		 
		 double amount;
		 cin >> num >> amount;
		 //ES: Changed num to num-1 because 1 would be the checking account, but accounts[0] is the checking account, runtime error
		 num = num - 1;


		 if (input == "D")
		 {
			accounts[num]->Deposit(amount);
		 }
		 else
		 {
			accounts[num]->Withdraw(amount);
		 }

		 cout << "Balance: $" << accounts[num]->GetBalance()
			  << endl;
	  }
	  else if (input == "B")     // Month end processing
	  {
		  for (int i = 0; i < 2; i++)
		  {
			  switch (i)
			  {
			  case 0:
				  cout << "\n Your checking account balance:";
				  break;
			  case 1:
				  cout << "\n Your savings account balance:";
			  }
				  //ES changed the . to ->, syntax error
				  accounts[i]->MonthEnd();
				  //ES changed the . to ->, syntax error
				  cout << "\n  $" << accounts[i]->GetBalance() << endl;
			  
		  }
	  }
	  else if (input == "Q")
	  {
		 more = false;
	  }
   }

   cout << "\n Thanks for using our Bank Account app!.\n\n";
   return 0;
}


