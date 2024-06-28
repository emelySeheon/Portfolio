#ifndef HIGHLIFE_H
#define HIGHLIFE_H


#include "LifeCell.h"
#include <iostream>
using namespace std;

/*
1.	Cells survive from one generation to the next if they have 2 or 3 neighbors.
2.  Cells are born if they have 3 or 6 neighbors.
*/

class HighLife: public LifeCell
{
protected:
	virtual void GetLivingNeighbors(int r, int c);
	virtual void UpdateCells();

public:
	HighLife();
};

#endif