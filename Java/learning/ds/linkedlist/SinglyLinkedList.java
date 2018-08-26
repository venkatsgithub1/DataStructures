package learning.ds.linkedlist;

public class SinglyLinkedList {

	public SinglyLinkedList() {
		length = 0;
	}

	ListNode head;
	private int length;

	public synchronized ListNode getHead() {
		return head;
	}

	public synchronized void insertAtBegin(ListNode node) {
		node.setNext(head);
		head = node;
		length++;
	}

	public synchronized void insertAtEnd(ListNode node) {
		if (head == null) {
			head = node;
		} else {
			ListNode current = head;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(node);
		}
		length++;
	}

	public void insert(int position, int data) {
		if (position < 0) {
			position = 0;
		}
		if (position > length) {
			position = length;
		}
		ListNode node = new ListNode(data);
		if (head == null) {
			head = node;
			length++;
		} else if (position == 0) {
			insertAtBegin(node);
		} else {
			ListNode currentNode = head;
			for (int i = 1; i < position; i++) {
				currentNode = currentNode.getNext();
			}
			node.setNext(currentNode.getNext());
			currentNode.setNext(node);
			length++;
		}
	}

	public synchronized void removeFromBegin() {
		if (head != null) {
			head = head.getNext();
		}
		length--;
	}

	public synchronized ListNode removeFromEnd() {
		if (head == null) {
			return null;
		}
		ListNode current = head.getNext();
		ListNode previous = head;

		if (current == null) {
			head = null;
			return head;
		}

		while (current.getNext() != null) {
			previous = current;
			current = current.getNext();
		}

		previous.setNext(null);
		length--;
		return current;
	}

	public synchronized void removeMatched(ListNode node) {
		if (head == null) {
			return;
		}

		if (node.equals(head)) {
			head = head.getNext();
			length--;
			return;
		}

		ListNode current = head;
		ListNode previous = null;
		while (current.getNext() != null) {
			current = current.getNext();
			previous = current;
			if (current.equals(node)) {
				previous.setNext(current.getNext());
				length--;
				return;
			}
		}
	}

	public String toString() {
		ListNode current = head;
		String data = "";
		while (current != null) {
			data += current.getData() + " ";
			current = current.getNext();
		}
		return "[" + data.trim() + "]";
	}

	public int getPosition(int data) {
		if (head == null) {
			return 0;
		}
		if (head.getData() == data) {
			return 0;
		}

		ListNode current = head;

		int counter = 0;

		while (current.getNext() != null) {
			current = current.getNext();
			counter++;
			if (current.getData() == data) {
				return counter;
			}
		}

		return -1;
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
		} else if (position == 0) {
			head = head.getNext();
		} else {
			ListNode current = head;
			for (int i = 1; i < position; i++) {
				current = current.getNext();
			}

			current.setNext(current.getNext().getNext());
		}
		length--;

	}

	public void clearList() {
		head = null;
		length = 0;
	}
}

class ListNode {
	private int data;
	private ListNode next;

	public ListNode(int data) {
		this.setData(data);
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	public int listLength(ListNode headNode) {
		int length = 0;
		ListNode currentNode = headNode;
		while (currentNode != null) {
			length++;
			currentNode = currentNode.getNext();
		}
		return length;
	}
}
