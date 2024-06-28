//Name: Emely seheon
//Email: eseheon1@gmail.com
//Project: CompositionDemo

#include <iostream>
#include "Carpet.h"

using namespace std;

int main() {
	cout << "\n Composition Demo - the Area of a rectangular carpet.";

	int lengthFt{ 1 };
	int lengthIn{ 1 };
	int widthFt{ 1 };
	int widthIn{ 1 };

	cout << "\n Please enter the length of your carpet in feet, then a space , and then inches."
		<< "\n please use whole numbers:  ";
	cin >> lengthFt >> lengthIn;

	cout << "\n Please enter the width of your carpet in feet, then a space , and then inches."
		<< "\n please use whole numbers:  ";
	cin >> widthFt >> widthIn;

	Carpet carpet{ lengthFt, lengthIn, widthFt, widthIn };

	cout << carpet.GetCarpetStr();

	cout << "\n Thanks for doing our composition demo";
}