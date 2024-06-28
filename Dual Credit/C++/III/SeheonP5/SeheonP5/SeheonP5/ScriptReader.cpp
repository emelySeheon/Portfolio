#include "ScriptReader.h"

ScriptReader::ScriptReader(string file)
{
	in.open(file);
}

int ScriptReader::GetNextInt()
{
	string junk;
	string keep;
	int nextInt;
	bool intFound = false;
	
	while (!intFound) {
		if (!in.eof()) {
			char c = in.peek();
			if (c == '#' || c == ' ' || c == '\n') {
				getline(in, junk);
			}
			else {
				getline(in, keep);
				nextInt = stoi(keep);
				intFound = true;
			}
		}
	}
	return nextInt;
}

string ScriptReader::GetNextString()
{
	string junk;
	string keep;
	bool strFound = false;

	while (!strFound) {
		if (!in.eof()) {
			char c = in.peek();
			if (c == '#' || c == ' ' || c == '\n') {
				getline(in, junk);
			}
			else {
				getline(in, keep);
				strFound = true;
			}
		}
	}
	return keep;
}
