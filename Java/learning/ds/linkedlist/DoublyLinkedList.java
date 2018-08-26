package learning.ds.linkedlist;

public class DoublyLinkedList {

	// head and tail are sentinel nodes.
	private DLLNode head;
	private DLLNode tail;
	private int length;

	public DoublyLinkedList() {
		head = new DLLNode(Integer.MIN_VALUE, null, null);
		tail = new DLLNode(Integer.MIN_VALUE, head, null);
		head.setNext(tail);
	}

	public int getPosition(int data) {
		DLLNode temp = head;
		int position = 0;

		while (temp != null) {
			if (temp.getData() == data) {
				return position;
			}
			position += 1;
			temp = temp.getNext();
		}

		return -1;
	}

	public int length() {
		return this.length;
	}

	public void insert(int data) {
		DLLNode newNode = new DLLNode(data, head, head.getNext());

		// set newNode ref in previous of next node of new node.
		newNode.getNext().setPrev(newNode);

		// set newNode ref in next node of head.
		head.setNext(newNode);

		length++;

	}

	public void insert(int position, int data) {
		DLLNode newNode = new DLLNode(data, null, null);
		if (position < 0) {
			position = 0;
		}
		if (position > length) {
			position = length;
		}
		if (head == null) {
			new DoublyLinkedList();
		}
		if (position == 0) {
			insert(data);
		} else {
			DLLNode temp = head.getNext();
			for (int i = 1; i < position; i++)
				temp = temp.getNext();
			newNode.setNext(temp.getNext());
			newNode.setPrev(temp);
			newNode.getNext().setPrev(newNode);
			temp.setNext(newNode);
		}
		length++;
	}

	public void insertTail(int data) {
		DLLNode newNode = new DLLNode(data, tail.getPrev(), tail);
		tail.getPrev().setNext(newNode);
		tail.setPrev(newNode);
		length++;
	}

	public void remove(int position) {
		if (position < 0) {
			position = 0;
		}
		if (position > length) {
			position = length - 1;
		}
		if (head == null) {
			return;
		}
		if (position == 0) {
			head.setNext(head.getNext().getNext());
		} else {
			DLLNode currentNode = head.getNext();
			for (int i = 1; i < position; i++) {
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(currentNode.getNext().getNext());
			currentNode.getNext().setPrev(currentNode);
		}
		length--;
	}

	public synchronized void removeMatched(DLLNode node) {
		if (length == 0) {
			return;
		}

		if (head == null) {
			return;
		}

		if (node.equals(head.getNext())) {
			head.setNext(head.getNext().getNext());
			length--;
			return;
		}

		DLLNode currentNode = head.getNext();
		while (currentNode != null) {
			if (currentNode.equals(node)) {
				currentNode.getPrev().setNext(currentNode.getNext());
				currentNode.getNext().setPrev(currentNode);
				length--;
				return;
			}
			currentNode = currentNode.getNext();
		}
	}

	public int removeHead() {
		if (length == 0) {
			return -1;
		}
		DLLNode currentNode = head.getNext();
		head.setNext(currentNode.getNext());
		currentNode.getNext().setPrev(head);
		length--;
		return currentNode.getData();
	}

	public int removeTail() {
		if (length == 0) {
			return -1;
		}

		DLLNode currentNode = tail.getPrev();
		tail.setPrev(currentNode.getPrev());
		currentNode.getPrev().setNext(tail);
		length--;
		return currentNode.getData();
	}

	public String toString() {
		String data = "[";
		if (length == 0)
			return data + "]";
		DLLNode current = head.getNext();
		while (current != tail) {
			data += current.getData() + " ";
			current = current.getNext();
		}

		return data.trim() + "]";
	}

	public void clearList() {
		head.setNext(tail);
		tail.setPrev(head);
	}
}

class DLLNode {
	private int data;
	private DLLNode prev;
	private DLLNode next;

	public DLLNode(int data, DLLNode prev, DLLNode next) {
		this.data = data;
		this.prev = prev;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public DLLNode getPrev() {
		return prev;
	}

	public void setPrev(DLLNode prev) {
		this.prev = prev;
	}

	public DLLNode getNext() {
		return next;
	}

	public void setNext(DLLNode next) {
		this.next = next;
	}
}
