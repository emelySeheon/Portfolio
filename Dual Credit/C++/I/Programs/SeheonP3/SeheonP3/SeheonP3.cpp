/*
Program: SeheonP3
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 6/24/20
Purpose: To calculate the surface area of segments of five planets
that you can see from Earth without aid and the moon.
*/

#include <iostream>;
#include <iomanip>;
#include <string>;
#include <vector>;
#include <sstream>;
#include <locale>;
using namespace std;

int main()
{
	locale comma("");
	cout.imbue(comma);

	cout << "Name: Emely Seheon" << endl
		<< " Program: SeheonP3" << endl
		<< "Objective: To calculate the surface area of segments of five planets"
		<< "\nthat you can see from Earth without aid and the moon." << endl;

	//Getting the User's name
	string name;
	cout << "\nWhat is your name? " << endl;
	getline(cin, name);

	//Creating the vector of planets and the moon
	vector <string> options;
	options.push_back("Mercury");
	options.push_back("Mars");
	options.push_back("Venus");
	options.push_back("Saturn");
	options.push_back("Jupiter");
	options.push_back("Moon");

	vector <int> radii;
	radii.push_back(2440);
	radii.push_back(3397);
	radii.push_back(6052);
	radii.push_back(60268);
	radii.push_back(71492);
	radii.push_back(1738);

	//Initializing / declaring variables
	string response;
	string selection;
	int illuminated;
	int radius;
	double surfaceArea;
	double theta;
	double pi = 3.14159;
	int selecIndex;
	bool validInput;

	//Opening do while loop
	do {
		//Displaying planets to user
		for (int i = 0; i <= options.size()-1; ++i) {
			cout <<"\n"<< options.at(i) << endl
				<< "----> " << radii.at(i) << " km";
		}
	
		//Asking user for planet and checking it's an option
		cout << "\nSelect a planet / moon: ";
		getline(cin, selection);

		for (int j = 0; j <= options.size() ; ++j)
		{
			if(j<=options.size()-1){
				if (selection == options.at(j)) {
					validInput = true;
					break;
				}
				else {
					continue;
				}
			}
			else {
				validInput = false;
				cout << "\nInput not valid." << endl;
				cout << "\nDo you want to calculate another question? (yes/no) ";
				getline(cin, response);
				break;
			}
		}
		if (validInput == true) {
			//Asking for precentage illuminated
			cout << "\nWhat percentage is illuminated? ";
			cin >> illuminated;

			//Finding index of selction
			if (selection == "Mercury") {
				selecIndex = 0;
			}
			else if (selection == "Mars") {
				selecIndex = 1;
			}
			else if (selection == "Venus") {
				selecIndex = 2;
			}
			else if (selection == "Saturn") {
				selecIndex = 3;
			}
			else if (selection == "Jupiter") {
				selecIndex = 4;
			}
			else if (selection == "Moon") {
				selecIndex = 5;
			}

			//Calculations
			theta = illuminated * 0.01 * pi;
			radius = radii.at(selecIndex);
			surfaceArea = 2 * radius * radius * theta;

			//Setting decimals to 0
			stringstream area;
			area.imbue(comma);
			area << "Surface area: ";
			area.setf(ios::fixed);
			area.precision(0);
			area<<surfaceArea << " square km" << endl;

			//Displaying results
			
			cout << "\nName: " << name << endl
				<< "Planet/moon: " << selection << endl
				<< "Percent illuminated: " << illuminated << "%" << endl
				<< "Radius: " << radius << " km" << endl
				<< area.str();
				 
			//Asking for another
			cin.ignore();
			cout << "\nDo you want to calculate another question? (yes/no) ";
			getline(cin, response);
		}
	} while (response == "yes");

	//Goodbye
	cout << "\nGoodbye!";

	return 0;
}