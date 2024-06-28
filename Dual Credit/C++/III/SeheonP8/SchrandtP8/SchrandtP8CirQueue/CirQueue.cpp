//Ben Schrandt  bschrandt@cnm.edu
//Circular Queue with Unique Pointers

//CirQueue.cpp

#include "CirQueue.h"
#include <iostream>
#include <sstream>

bool CirQueue::empty()
{
    if (first == -1)
        return true;
    else
        return false;
}

bool CirQueue::full()
{
    if ((first == 0 && last == SIZE - 1) || (last == (first - 1) % (SIZE - 1))) //check to see if first and last are next to each other
        return true;
    else
        return false;
}

void CirQueue::show()
{

    int newLine{ 0 };
    if (empty())
    {
        cout << "\nList is empty.";
        return;
    }
    if (last >= first) //if last is larger just print normally
    {
        for (int i = first; i <= last; i++)
        {
            cout << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                cout << "\n";
        }
    }
    else //if first is larger then print from first to the end, then from 0 to last
    {
        for (int i = first; i < SIZE; i++) 
        {
            cout << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                cout << "\n";
        }
        for (int i = 0; i <= last; i++)
        {
            cout << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                cout << "\n";
        }
    }
   
}

void CirQueue::push(unique_ptr<Person> p)
{
    
    if (full())
    {
        cout << "\nLine is full.";
        return;

    }
    else if (first == -1) //if list is empty set first and last to first index
    {
        first = last = 0;
        pArray[last] = move(p);
        count++;
    }
    else if (last == SIZE - 1 && first != 0) //if last is at the end, wrap it around to 0
    {
        last = 0;
        pArray[last] = move(p);
        count++;
    }
    else
    {
        last++;
        pArray[last] = move(p);
        count++;
    }

}

void CirQueue::pop()
{
    if (empty())
    {
        cout << "\nLine is empty.";
        return;
    }
    if (first == last) //if first and last are same, means theres only 1 person, so popping will make the list empty
    {
        first = -1;
        last = -1;
        count--;
    }
    else if (first == SIZE - 1) //if first is at the end, wrap it back around to 0
    {
        first = 0;
        count--;
    }
    else
    {
        first++;
        count--;
    }
        
}

string CirQueue::front()
{
    if (empty())
    {
        return "\nList is empty\n";
    }
    return pArray[first]->GetName();
}

string CirQueue::back()
{
    if (empty())
    {
        return "\nList is empty\n";
    }
    return pArray[last]->GetName();
}

string CirQueue::showString()
{
    stringstream ss;
    int newLine{ 0 };
    ss << "Number of people in line: " << count << "\n";
    if (empty())
    {
        
        return "Line is empty.\n";
    }
   
    if (last >= first) //if last is larger just print normally
    {
        for (int i = first; i <= last; i++)
        {
            ss << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                ss << "\n";
        }
    }
    else //if first is larger then print from first to the end, then from 0 to last
    {
        for (int i = first; i < SIZE; i++)
        {
            ss << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                ss << "\n";
        }
        for (int i = 0; i <= last; i++)
        {
            ss << " <- " << pArray[i]->GetName();
            newLine++;
            if (newLine % 5 == 0)
                ss << "\n";
        }
    }
    if (full())
    {
        ss << "Line is full.\n";
    }
    string result = ss.str();
    return result;
}

int CirQueue::size()
{
    return count;
}
