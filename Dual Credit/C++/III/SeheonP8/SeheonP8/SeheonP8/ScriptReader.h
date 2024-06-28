/*Barbara Johnston
 *for CIS 2277 Fall 2013
 *File: ScriptReader.h

 The getNextInt() reads and ignores
 any lines that begin with #, or a blank line. It will return 
 the next valid int. 

 The getNextString() reads and ignores
 any lines that begin with #, or a blank line. It will return 
 the next valid string. 

 Note: the programmer is responsible for knowing when to call
 for an int and when to call for a string. 
 */

#ifndef _SCRPT_RDR_H
#define _SCRPT_RDR_H


#include <fstream>
#include <string>
#include <iostream>
using namespace std;

class ScriptReader
{
private:
	ifstream in;


public:

	//only constructor, is passed the name of the file
	ScriptReader(string file);

	//returns the next integer value it finds 
	int GetNextInt();
	
	//returns the next valid string 
	string GetNextString();
};


#endif
