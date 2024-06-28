using namespace std;

#ifndef _DIVIDE_BY_ZERO_H
#define _DIVIDE_BY_ZERO_H

class DivideByZeroException : public runtime_error
{
public:
	DivideByZeroException() : runtime_error("Attempted to divide by zero") {}
};


#endif