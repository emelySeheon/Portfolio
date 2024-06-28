//File: Search.cpp

#include "Search.h"
#include <sstream>;
#include <cstring>
#include <algorithm>

bool Search::RecursiveBinarySearch(Person folks[], string target, int first, int last, int* position, int* pNumCompares)
{
    int middle = ((last - first) / 2) + first;
    //If target found
//TODO:  Here, you are incrementing the pointer, not the value. -5 
//TODO:  In precendence of operations, ++ is higher than *dereferencing.  To
//TODO:  make this work, need to (*pNumCompares)++; 
    *pNumCompares++;
    if (!folks[middle].GetName().compare(target)) {
        *position = middle + 1;
        return true;
    }
    //If there are no more options
    else if ((folks[middle + 1].GetName() > target && folks[middle - 1].GetName() < target) || target < folks[first].GetName() || target > folks[last].GetName()) {
        *position = -1;
        return false;
    } //General Case
    else {
        //if middle is too large
        if (folks[middle].GetName() > target) {
        last = middle - 1;
        }
        //if middle is too small
        else if (folks[middle].GetName() < target) {
            first = middle + 1;
        }
        return RecursiveBinarySearch(folks, target, first, last, position, pNumCompares);
    }
}

bool Search::SequentialSearch(Person folks[], int total, string target, int* position, int* pNumCompares)
{
    for (int i = 0; i < total; ++i) {
        //if target found
        if (!folks[i].GetName().compare(target)) {
            *position = i + 1;
            *pNumCompares = i + 1;
            return true;
        }
        //If target can not exist
        else if (folks[i].GetName() > target || folks[i].GetName() < target) {
            *pNumCompares = i + 1;
            *position = -1;
            return false;
        }
    }
    //In case target or the name in the array is somehow blank
    *pNumCompares = -1;
    *position = -1;
    return false;
}

bool Search::BinarySearch(Person folks[], int total, string target, int* position, int* pNumCompares)
{
    int first = 0;
    int last = total-1;
    int middle = ((last - first) / 2) + first;
    int counter = 0;

    while (true) {
        counter++;
        //If target found
        if (!folks[middle].GetName().compare(target)) {
            *position = middle + 1;
            *pNumCompares = counter;
            return true;
        }
        //If there are no more options
        else if ((folks[middle + 1].GetName() > target && folks[middle - 1].GetName() < target) || target < folks[first].GetName() || target > folks[last].GetName()) {
            *position = -1;
            *pNumCompares = counter;
            return false;
        }
        //if middle is too large
        else if (folks[middle].GetName() > target) {
            last = middle - 1;
            middle = ((last - first) / 2) + first;
        }
        //if middle is too small
        else if (folks[middle].GetName() < target) {
            first = middle + 1;
            middle = ((last - first) / 2) + first;
        }
    }
    //In case target or the name in the array is somehow blank
    *pNumCompares = -1;
    *position = -1;
    return false;
}

bool Search::RunRecursiveSearch(Person folks[], int total, string target, int* position, int* pNumCompares)
{
    int first = 0;
    int last = total - 1;
    return RecursiveBinarySearch(folks, target, first, last, position, pNumCompares);
}

string Search::FormatName(string first, string last)
{
    stringstream ss;
    ss << last << ", " << first;
    return ss.str();
}
