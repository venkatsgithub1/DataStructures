package learning.ds.heap;

public class MaxHeap {

	// maxHeapify method has time complexity O(logn).
	// takes an element, and heapifies it till bottom.
	void maxHeapify(int arr[], int i, int n) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int largest = i;
		if (left < n && arr[left] > arr[largest]) {
			largest = left;
		}
		if (right < n && arr[right] > arr[largest]) {
			largest = right;
		}
		if (largest != i) {
			swap(arr, largest, i);
			maxHeapify(arr, largest, n);
		}
	}

	// swaps two elements of given array.
	void swap(int arr[], int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	// buildHeap heapifies entire array.
	// Time complexity O(nlogn).
	void buildHeap(int[] arr) {
		for (int i = arr.length / 2; i >= 1; i--) {
			maxHeapify(arr, i, arr.length);
		}
	}

	// heapsort is an inplace sorting algorithm,
	// time complexity: O(nlogn).
	void heapSort(int[] arr) {
		buildHeap(arr);
		int heapSize = arr.length - 1;

		for (int i = heapSize; i >= 1; i--) {
			swap(arr, 1, i);
			heapSize -= 1;
			maxHeapify(arr, 1, heapSize);
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 0, 1, 4, 3, 7, 8, 9, 10 };
		new MaxHeap().buildHeap(arr);
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
		new MaxHeap().heapSort(arr);
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}

}

// Output:
// 0 10 8 9 7 4 1 3 
// 0 1 3 4 7 8 9 10 
//
