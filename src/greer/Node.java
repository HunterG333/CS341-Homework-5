package greer;

/**
 * 
 * A class which stores the attributes of a Node stored in a queue
 *
 */
public class Node {
	
	public int n;
	public Node next;
	
	public Node(int n) {
		next = null;
		this.n = n;
	}
	
}