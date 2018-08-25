package learning.ds.queue;

public class CircularQueue {

	public static void main(String[] args) throws Exception {
		CircularQueueImpl c1 = new CircularQueueImpl();
		c1.enqueue(1);
		c1.enqueue(2);
		c1.enqueue(4);

		System.out.println(c1.dequeue());
		System.out.println(c1.dequeue());
		System.out.println(c1.dequeue());
		System.out.println(c1.dequeue());
		System.out.println(c1.size);
	}

}

class CircularQueueImpl {
	int front, rear, size;
	int arr[] = new int[10];

	int dequeue() throws Exception {
		if (isEmpty()) {
			throw new Exception("Q is empty");
		}
		int temp = arr[front];
		arr[front] = 0;
		front = (front + 1) % arr.length;
		size = size - 1;
		return temp;
	}

	void enqueue(int o) throws Exception {
		if (size == arr.length) {
			throw new Exception("Q is full");
		}
		arr[rear] = o;
		size = size + 1;
		rear = (rear + 1) % arr.length;
	}

	boolean isEmpty() {
		return size == 0;
	}

}
