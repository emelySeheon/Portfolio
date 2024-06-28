#ifndef MODIFIEDFREDKINCELL_H
#define MODIFIEDFREDKINCELL_H


#include "FredkinCell.h"
#include <iostream>
using namespace std;

/*
1.	A dead cell becomes a live cell if 1 or 3 neighbors are alive.
2.	A live cell becomes a dead cell if 0, 2, or 4 neighbors are alive.
3.	Otherwise, the cell stays the same.
4.  If a cell survives to the next generation twice, it becomes a live Conway cell
*/

class ModifiedFredkinCell: public FredkinCell
{
protected:
	virtual void GetLivingNeighbors(int r, int c);
	virtual void UpdateCells();

public:
	ModifiedFredkinCell();
};

#endif