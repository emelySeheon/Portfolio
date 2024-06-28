#ifndef MEASUREMENT_H
#define MEASUREMENT_H

using namespace std;

class Measurement
{
private:
	int feet;        // To hold a number of feet
	int inches;      // To hold a number of inches
	Measurement Simplify(int simpInches);
public:
	Measurement();
	Measurement(int f, int i);
	// Mutator functions
	void SetFeet(const int f) { feet = f; }
	void SetInches(const int i) { inches = i; }
	// Accessor functions
	int GetFeet() const { return feet; }
	int GetInches() const { return inches; }
	int Multiply(Measurement obj);
	double ConvertToDouble();   //Convert feet and inches to decimal feet

};

#endif