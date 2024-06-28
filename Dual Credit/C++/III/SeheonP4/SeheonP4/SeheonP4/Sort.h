//File: Sort.h

#ifndef  _PERSONGEN_H
#define  _PERSONGEN_H

class Sort
{
private:
	void RecursiveInPlaceMergeSort(int arr[], int lo0, int hi0);
	void RecursiveQuickSort(int arr[], int lo0, int hi0);

public:
	Sort() = default;

	//Sort A
	void BubbleSort(int arr[], int size);

	//Sort B
	void InsertionSort(int arr[], int size);

	//Sort D
	void ShellSort(int arr[], int size);

	//Sort E
	void QuickSort(int arr[], int size);

	//Sort F
	void InPlaceMergeSort(int arr[], int size);

	//Sort G
	void SelectionSort(int arr[], int size);

	//Sort H
	void ShakerSort(int arr[], int size);

	//Sort I
	void Comb11Sort(int arr[], int size);

	//Sort J (Goofy Sort)
	void GoofySort(int arr[], int size);
};

#endif