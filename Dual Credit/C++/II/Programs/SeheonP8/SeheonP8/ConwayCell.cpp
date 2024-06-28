#include "ConwayCell.h"
#include <iostream>
using namespace std;

void ConwayCell::GetLivingNeighbors(int r, int c)
{
	//NW = rows-1 cols-1
	//N = rows-1 cols
	//NE = rows-1 cols+1
	//E = rows cols+1
	//SE = rows+1 cols+1
	//S = rows+1 cols
	//SW = rows+1 cols-1
	//W = rows cols-1

	count = 0;

	//top right
	if (r != 0 && c != COLS - 1) {
		if (cell[r - 1][c + 1] == 'X') {
			count++;
		}
	}

	//right
	if (c != COLS - 1) {
		if (cell[r][c + 1] == 'X') {
			count++;
		}
	}


	//bottom right
	if (r != ROWS - 1 && c != COLS - 1) {
		if (cell[r + 1][c + 1] == 'X') {
			count++;
		}
	}

	//bottom
	if (r != ROWS - 1) {
		if (cell[r + 1][c] == 'X') {
			count++;
		}
	}

	//bottom left
	if (r != ROWS - 1 && c != 0) {
		if (cell[r + 1][c - 1] == 'X') {
			count++;
		}
	}

	//left
	if (c != 0) {
		if (cell[r][c - 1] == 'X') {
			count++;
		}
	}

	//top left
	if (r != 0 && c != 0) {
		if (cell[r - 1][c - 1] == 'X') {
			count++;
		}
	}

	//top
	if (r != 0) {
		if (cell[r - 1][c] == 'X') {
			count++;
		}
	}
}

void ConwayCell::UpdateCells()
{
	for (int i = 0; i < ROWS; ++i) {
		for (int j = 0; j < COLS; ++j) {

			GetLivingNeighbors(i, j);

			//If dead
			if (cell[i][j] == '.') {
				if (count == 3) {
					nextCellState[i][j] = 'X';
				}
				else {
					nextCellState[i][j] = '.';
				}
			} //If alive
			else if (cell[i][j] == 'X') {
				if (count < 2 || count >3) {
					nextCellState[i][j] = '.';
				}
				else {
					nextCellState[i][j] = 'X';
				}
			}
		}
	}
}

ConwayCell::ConwayCell()
{
}
