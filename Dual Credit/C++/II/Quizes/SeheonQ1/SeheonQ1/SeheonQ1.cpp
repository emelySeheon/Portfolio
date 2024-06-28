// Name: Emely Seheon
//Email: eseheon1@cnm.edu
//Program: SeheonQ1
//Purpose: To make a class that displays dog information

#include <iostream>
#include "Dog.h"
using namespace std;

int main()
{
    cout << "Name: Emely Seheon" << endl << "Email: eseheon1@cnm.edu" << endl << "Program: SeheonQ1" << endl << "Purpose: To make a class that displays dog information" << endl;
    Dog defaultDog;
    Dog constructorDog ("siberian husky", "Crystal", 2);
    Dog switchDog;

    switchDog.SetData("maltipoo", "Cuddles", 3);

    string defaultString = defaultDog.GetFormattedString();
    string constructorString = constructorDog.GetFormattedString();
    string switchString = switchDog.GetFormattedString();

    cout << endl << defaultString << constructorString << switchString << endl << "Good Bye!" << endl;
}
