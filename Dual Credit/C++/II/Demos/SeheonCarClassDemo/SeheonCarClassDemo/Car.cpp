/*
Program: Car.cpp
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 9/10/20
Purpose: To provide functions for the driver.
*/

#include <iostream>;
#include <sstream>;
#include "CarClass.h";
using namespace std;

Car::Car()
{}

//Overloaded constructor
Car::Car(string make, string model, int year, string color) :
    make(make), model(model), year(year), color(color)
{}
Car::~Car()  //the destructor, no action in this class
{}
//Set method
void Car::SetCarInfo(string make, string model, int year, string color)
{
    this->make = make;
    this->model = model;
    this->year = year;
    this->color = color;
}
//Accessor to get a formatted string
string Car::GetCarInfo()
{
    stringstream ss;
    ss << "\n The car: " << "\n Make: " << make
        << "\n Model: " << model
        << "\n Year: " << year
        << "\n Color: " << color;
    return ss.str();
}
