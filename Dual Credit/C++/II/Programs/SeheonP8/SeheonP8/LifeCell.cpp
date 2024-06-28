#include "LifeCell.h"
#include <iostream>
#include <iostream>
#include <fstream>
#include <sstream>
using namespace std;


void LifeCell::InitializeBoard()
{
	ifstream readFile(pattern);
	if (readFile.is_open())
	{
		bOpen = true;

		int rows;
		int cols;
		readFile >> rows >> cols;
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				readFile >> cell[i][j];
			}
		}

		readFile.close();
	}

	else {
		bOpen = false;
	}

}

void LifeCell::SetNextState()
{
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {
			cell[i][j] = nextCellState[i][j];
		}
	}
}

LifeCell::LifeCell()
{
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {
			cell[i][j] = '.';
			nextCellState[i][j] = '.';
		}
	}
}

void LifeCell::SetPattern(string pat)
{
	pattern = pat;
	InitializeBoard();
}

void LifeCell::UpdateBoard()
{
	UpdateCells();
	SetNextState();
}

string LifeCell::PrintBoard()
{
	stringstream ss;
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {
			if (cell[i][j] == '.') {
				ss << " ";
			}
			else {
				//Decided that the * looked cooler than the X
				ss << '*';
			}
		}
		ss << "\n";
	}
	return ss.str();
}

void LifeCell::Clear()
{
	count = 0;
	rows = 0;
	cols = 0;
	pattern = " ";
	bOpen = false;
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {
			cell[i][j] = '.';
			nextCellState[i][j] = '.';
		}
	}
}