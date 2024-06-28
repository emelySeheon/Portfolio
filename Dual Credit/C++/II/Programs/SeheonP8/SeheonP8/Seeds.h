#ifndef SEEDSCELL_H
#define SEEDSCELL_H


#include "LifeCell.h"
#include <iostream>
using namespace std;

/*
1.	A dead cell becomes a live cell if exactly 2 neighbors are alive.
2.	All other cells become dead.
*/

class Seeds: public LifeCell
{
protected:
	virtual void GetLivingNeighbors(int r, int c);
	virtual void UpdateCells();

public:
	Seeds();
};

#endif;