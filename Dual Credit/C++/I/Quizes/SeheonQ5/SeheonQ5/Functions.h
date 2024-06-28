/*
Program: Functions.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/4/20
Purpose: To fix the broken code shown below.
*/



//Ivonne Nelson CIS 1275 C++ I
//Paint Calculator - Functions
//in Files

//Functions.h  Put function prototypes here

#include <string>
using namespace std;

void WriteGreeting();
//Put all parameters on one line to make it easier to read for both functions below, for syntax
int HowManyGallons(double length, double width, double height, int coverage, int coats, string ceiling);
int HowManygallons(double radius, double height, int coverage, int coats, string ceiling);