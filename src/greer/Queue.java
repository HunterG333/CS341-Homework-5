package greer;

import java.util.NoSuchElementException;

/**
 * A class which stores the queue.
 *
 */
public class Queue {
	
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * Constructor for the queue
	 */
	public Queue() {
		head = null;
		tail = null;
	}
	
	/**
	 * 
	 * @return true if the queue is empty, false if it is not
	 */
	public Boolean isEmpty() {
		return head == null;
	}
	
	/**
	 * add an element to the queue
	 * @param i an integer that will be added to the queue
	 */
	public void enqueue(int i) {
		Node newNode = new Node(i);
		size++;
		
		if(head == null) {
			head = newNode;
			tail = newNode;
			return;
		}
		tail.next = newNode;
		tail = newNode;
		
	}
	
	/**
	 * Dequeues the first available item in the queue
	 * @return the integer getting dequeued
	 */
	public int dequeue() {
		if(head == null) {
			throw new NoSuchElementException("Can not dequeue with an empty queue");
		} else if(head == tail) {
			int frontItem = head.n;
			head = null;
			tail = null;
			size--;
			return frontItem;
		} else {
			int frontItem = head.n;
			head = head.next;
			size--;
			return frontItem;
		}
	}
	
	/**
	 * 
	 * @return a node which is the head of the queue
	 */
	public Node getHead() {
		return head;
	}
	
	/**
	 * 
	 * @return The size of the queue
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Represents the queue as a string
	 */
	public String toString() {
		String queue = "[";
		
		Node node = head;
		while(node != null) {
			if(node != head) {
				queue += ", ";
			}
			queue += node.n;
			node = node.next;
		}
		queue += "]";
		return queue;
	}
}
