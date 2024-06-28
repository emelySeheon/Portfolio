////Template Sort Example
////refer to Schlidt's book too for more info
//
//#ifndef _SORT_H
//#define _SORT_H
//
//template <class X > class Sort
//{
//public:
//	Sort();
//	void BubbleSort(X a[], int total);
//};
//
//template <class X>
//Sort<X>::Sort()
//{
//
//}
//
//
//template <class X>
//void Sort <X>::BubbleSort(X a[], int total)
//{
//	X T;
//	int i,j;
//	for(i = 0; i < total - 1; ++i ) 
//	{
//		for(j = 1; j < total; ++j) 
//		{
//			if (a[j-1] > a[j])
//			{
//				T = a[j];
//				a[j] = a[j-1];
//				a[j-1] = T;
//			}
//		}
//	} 
//}
//
//#endif
//
//
////Here is how you'd make the template Sort object
//
//#include "Sort.h"  
//#include <string>
//using namespace std;
//
//int main()
//{
//	Sort <int>   intSorter;      //sorter for integers
//	Sort <string>   strSorter;   //sorter for strings