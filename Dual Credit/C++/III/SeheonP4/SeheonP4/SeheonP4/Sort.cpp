//File: Sort.cpp

#include "Sort.h"
using namespace std;

//a
void Sort::BubbleSort(int arr[], int size)
{
	for (int i = size; --i >= 0; ) {
		bool flipped = false;
		for (int j = 0; j < i; j++) {
			if (arr[j] > arr[j + 1]) {
				int T = arr[j];
				arr[j] = arr[j + 1];
				arr[j + 1] = T;
				flipped = true;
			}
		}
		if (!flipped) {
			return;
		}
	}
}

//b
void Sort::InsertionSort(int arr[], int size)
{
	for (int i = 1; i < size; i++) {
		int j = i;
		int B = arr[i];
		while ((j > 0) && (arr[j - 1] > B)) {
			arr[j] = arr[j - 1];
			j--;
		}
		arr[j] = B;
	}
}

//g
void Sort::SelectionSort(int arr[], int size)
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
		int T = arr[min];
		arr[min] = arr[i];
		arr[i] = T;
	}
}

//i
void Sort::Comb11Sort(int arr[], int size)
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
				int T = arr[i];
				arr[i] = arr[j];
				arr[j] = T;
				flipped = true;
			}
		}
	} while (flipped || (gap > 1));
	/* like the bubble and shell sorts we check for a clean pass */
}

//h
void Sort::ShakerSort(int arr[], int size)
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
		int T = arr[min];
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

//d
void Sort::ShellSort(int arr[], int size)
{
	int h = 1;
	/*
	 * find the largest h value possible
	 */
	while ((h * 3 + 1) < size) {
		h = 3 * h + 1;
	}

	/*
	 * while h remains larger than 0
	 */
	while (h > 0) {
		/*
		 * for each set of elements (there are h sets)
		 */
		for (int i = h - 1; i < size; i++) {
			/*
			 * pick the last element in the set
			 */
			int B = arr[i];
			int j = i;
			/*
			 * compare the element at B to the one before it in the set
			 * if they are out of order continue this loop, moving
			 * elements "back" to make room for B to be inserted.
			 */
			for (j = i; (j >= h) && (arr[j - h] > B); j -= h) {
				arr[j] = arr[j - h];
			}
			/*
			 *  insert B into the correct place
			 */
			arr[j] = B;
		}
		/*
		 * all sets h-sorted, now decrease set size
		 */
		h = h / 3;
	}
}

//f
void Sort::InPlaceMergeSort(int arr[], int size)
{
	RecursiveInPlaceMergeSort(arr, 0, size - 1);
}

//f
void Sort::RecursiveInPlaceMergeSort(int arr[], int lo0, int hi0)
{
	int lo = lo0;
	int hi = hi0;
	if (lo >= hi) {
		return;
	}
	int mid = (lo + hi) / 2;

	/*
	 *  Partition the list into two lists and sort them recursively
	 */
	RecursiveInPlaceMergeSort(arr, lo, mid);
	RecursiveInPlaceMergeSort(arr, mid + 1, hi);

	/*
	 *  Merge the two sorted lists
	 */
	int end_lo = mid;
	int start_hi = mid + 1;
	while ((lo <= end_lo) && (start_hi <= hi)) {
		if (arr[lo] < arr[start_hi]) {
			lo++;
		}
		else {
			/*
			 *  a[lo] >= a[start_hi]
			 *  The next element comes from the second list,
			 *  move the a[start_hi] element into the next
			 *  position and shuffle all the other elements up.
			 */
			int T = arr[start_hi];
			for (int k = start_hi - 1; k >= lo; k--) {
				arr[k + 1] = arr[k];
			}
			arr[lo] = T;
			lo++;
			end_lo++;
			start_hi++;
		}
	}
}

//e
void Sort::QuickSort(int arr[], int size)
{
	RecursiveQuickSort(arr, 0, size - 1);
}

//e
void Sort::RecursiveQuickSort(int arr[], int lo0, int hi0)
{
	int lo = lo0;
	int hi = hi0;
	if (lo >= hi) {
		return;
	}
	else if (lo == hi - 1) {
		/*
		 *  sort a two element list by swapping if necessary
		 */
		if (arr[lo] > arr[hi]) {
			int T = arr[lo];
			arr[lo] = arr[hi];
			arr[hi] = T;
		}
		return;
	}


	/*
	 *  Pick a pivot and move it out of the way
	 */
	int pivot = arr[(lo + hi) / 2];
	arr[(lo + hi) / 2] = arr[hi];
	arr[hi] = pivot;

	while (lo < hi) {
		/*
		 *  Search forward from a[lo] until an element is found that
		 *  is greater than the pivot or lo >= hi
		 */
		while (arr[lo] <= pivot && lo < hi) {
			lo++;
		}

		/*
		 *  Search backward from a[hi] until element is found that
		 *  is less than the pivot, or lo >= hi
		 */
		while (pivot <= arr[hi] && lo < hi) {
			hi--;
		}

		/*
		 *  Swap elements a[lo] and a[hi]
		 */
		if (lo < hi) {
			int T = arr[lo];
			arr[lo] = arr[hi];
			arr[hi] = T;
		}
	}

	/*
	 *  Put the median in the "center" of the list
	 */
	arr[hi0] = arr[hi];
	arr[hi] = pivot;

	/*
	 *  Recursive calls, elements a[lo0] to a[lo-1] are less than or
	 *  equal to pivot, elements a[hi+1] to a[hi0] are greater than
	 *  pivot.
	 */
	RecursiveQuickSort(arr, lo0, lo - 1);
	RecursiveQuickSort(arr, hi + 1, hi0);
}

//j
void Sort::GoofySort(int arr[], int size)
{
	int a;
	bool switched;
	int counter;
	for (int i = 0; i < size - 1; ++i) {
		switched = false;
		counter = 0;
		if (arr[i + 1] < arr[i]) {
			a = arr[i + 1];
			arr[i + 1] = arr[i];
			arr[i] = a;
			switched = true;
		}
		if (i != 0) {
			while (switched) {
				if (arr[i - counter] < arr[i - counter - 1]) {
					a = arr[i - counter];
					arr[i - counter] = arr[i - counter - 1];
					arr[i - counter - 1] = a;
					counter++;
				}
				else {
					switched = false;
				}
			}
		}
	}
}