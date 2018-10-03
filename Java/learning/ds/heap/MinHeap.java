package learning.ds.heap;

public class MinHeap {

	public void minHeapify(int arr[], int i, int n) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int smallest = i;
		if (left < n && arr[left] < arr[smallest]) {
			smallest = left;
		}
		if (right < n && arr[right] < arr[smallest]) {
			smallest = right;
		}
		if (smallest != i) {
			swap(arr, i, smallest);
			minHeapify(arr, smallest, n);
		}
	}

	public void buildHeap(int arr[]) {
		for (int i = arr.length / 2; i >= 1; i--) {
			minHeapify(arr, i, arr.length);
		}
	}

	public void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public void heapSort(int arr[]) {
		int heapSize = arr.length - 1;
		buildHeap(arr);
		for (int i = heapSize; i >= 1; i--) {
			swap(arr, 1, i);
			heapSize = heapSize - 1;
			minHeapify(arr, 1, heapSize);
		}
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 0, 10, 8, 9, 7, 6, 5, 4 };
		new MinHeap().buildHeap(arr);
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
		new MinHeap().heapSort(arr);
		for (int i : arr)
			System.out.print(i + " ");
		System.out.println();
	}

}

//Output:
// 0 4 6 5 7 8 10 9 
// 0 10 9 8 7 6 5 4 
// 
