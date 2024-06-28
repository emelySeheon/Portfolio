/*
Program: Functions.h
Programmer: Emely Seheon (eseheon1@cnm.edu)
Date: 8/7/20
Purpose: To provide functions for the driver
*/

using namespace std;

#ifndef _FUNCTIONS_H
#define _FUNCTIONS_H

void read(int days[], double data[]);
string analyze(int days[], double data[]);
double percentMonth(double data[]);
double averageRain(double data[]);
int dayMost(int days[], double data[]);
void write(string results);

#endif