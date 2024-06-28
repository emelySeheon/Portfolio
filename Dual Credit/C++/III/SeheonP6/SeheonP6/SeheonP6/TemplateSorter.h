//File: TemplateSorter.h

#ifndef _SORT_H
#define	_SORT_H

#include ".\PersonGen\Person.h"

template <class X > class Sort
{
public:
	Sort();
	void BubbleSort(X arr[], int size);
	void Comb11(X arr[], int size);
	void Insertion(X arr[], int size);
	void Selection(X arr[], int size);
	void Shaker(X arr[], int size);
};

#endif


template <class X>
Sort<X>::Sort()
{
}

template <class X>
void Sort <X>::BubbleSort(X arr[], int size)
{
	X T;
	int i, j;
	for (i = 0; i < size - 1; ++i)
	{
		for (j = 1; j < size; ++j)
		{
			if (arr[j - 1] > arr[j])
			{
				T = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = T;
			}
		}
	}
}

template<class X>
inline void Sort<X>::Comb11(X arr[], int size)
{
	const float SHRINKFACTOR = (float)1.3;
	bool flipped = false;
	int gap, top;
	int i, j;

	gap = size;
	do {
		gap = (int)((float)gap / SHRINKFACTOR);
		switch (gap) {
		case 0: /* the smallest gap is 1 - bubble sort */
			gap = 1;
			break;
		case 9: /* this is what makes this Combsort11 */
		case 10:
			gap = 11;
			break;
		default: break;
		}
		flipped = false;
		top = size - gap;
		for (i = 0; i < top; i++) {
			j = i + gap;
			if (arr[i] > arr[j]) {
				X T = arr[i];
				arr[i] = arr[j];
				arr[j] = T;
				flipped = true;
			}
		}
	} while (flipped || (gap > 1));
	/* like the bubble and shell sorts we check for a clean pass */
}

template<class X>
inline void Sort<X>::Insertion(X arr[], int size)
{
	for (int i = 1; i < size; i++) {
		int j = i;
		X B = arr[i];
		while ((j > 0) && (arr[j - 1] > B)) {
			arr[j] = arr[j - 1];
			j--;
		}
		arr[j] = B;
	}
}

template<class X>
inline void Sort<X>::Selection(X arr[], int size)
{
	for (int i = 0; i < size; i++) {
		int min = i;
		int j;
		/*
		 *  Find the smallest element in the unsorted list
		 */
		for (j = i + 1; j < size; j++) {
			if (arr[j] < arr[min]) {
				min = j;
			}
		}
		/*
		 *  Swap the smallest unsorted element into the end of the
		 *  sorted list.
		 */
		X T = arr[min];
		arr[min] = arr[i];
		arr[i] = T;
	}
}

template<class X>
inline void Sort<X>::Shaker(X arr[], int size)
{
	int i = 0;
	int k = size - 1;
	while (i < k) {
		int min = i;
		int max = i;
		int j;
		for (j = i + 1; j <= k; j++) {
			if (arr[j] < arr[min]) {
				min = j;
			}
			if (arr[j] > arr[max]) {
				max = j;
			}
		}
		X T = arr[min];
		arr[min] = arr[i];
		arr[i] = T;
		if (max == i) {
			T = arr[min];
			arr[min] = arr[k];
			arr[k] = T;
		}
		else {
			T = arr[max];
			arr[max] = arr[k];
			arr[k] = T;
		}
		i++;
		k--;
	}
}
