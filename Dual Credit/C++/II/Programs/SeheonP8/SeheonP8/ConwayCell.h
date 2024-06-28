#ifndef CONWAYCELL_H
#define CONWAYCELL_H


#include "LifeCell.h"
#include <iostream>
using namespace std;

/*
1.	Any live cell with fewer than two live neighbors dies, as if caused by under-population.
2.	Any live cell with two or three live neighbors lives on to the next generation.
3.	Any live cell with more than three live neighbors dies, as if by overcrowding.
4.	Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
*/

class ConwayCell: public LifeCell
{
protected:
	virtual void GetLivingNeighbors(int r, int c);
	virtual void UpdateCells();

public:
	ConwayCell();
};

#endif