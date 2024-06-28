#include "StudentUser.h"

bool StudentUser::IsInValidRequest(int position)
{
    if (IsEmpty()) {
        return true;
    }
    else if (position < resources->size() && position > -1) {
        return false;
    }
    return true;
}

StudentUser::~StudentUser()
{
    cout << "\nStudent named " << name << " is in the destructor.";
}

int StudentUser::Size()
{
    return resources->size();
}

bool StudentUser::IsEmpty()
{
    return resources->empty();
}

void StudentUser::PushBack(const string& str)
{
    resources->push_back(str);
}

void StudentUser::PopBack()
{
    if (!IsEmpty()) {
        resources->pop_back();
        cout << "\nPop back successful.";
    }
    else {
        cout << "\nUnable to pop back.";
    }
}

string& StudentUser::GetFirstResource()
{
    if (!IsEmpty()) {
        return resources->at(0);
    }
    string i = "Vector is empty.";
    string& re = i;
    return re;
}

string& StudentUser::GetLastResource()
{
    if (!IsEmpty()) {
        return resources->at(resources->size() - 1);
    }
    string i = "Vector is empty.";
    string& re = i;
    return re;
}

string& StudentUser::GetResourceAt(int position)
{
    if (!IsInValidRequest(position)) {
        return (resources->at(position));
    }
    string i = "i";
    string& re = i;
    return re;
}

int StudentUser::GetReferenceCount()
{
    return resources.use_count();
}

void StudentUser::SetSharedPointer(shared_ptr<vector<string>> res)
{
    if (res != nullptr) {
        resources = res;
    }
    else {
        cout << "\nUnable to set null pointer.";
    }
}

shared_ptr<vector<string>> StudentUser::GetSharedPointer()
{
    return resources;
}
