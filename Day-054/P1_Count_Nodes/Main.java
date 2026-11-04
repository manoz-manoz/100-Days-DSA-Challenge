
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Count nodes in a linked list
    static int countNodes(Node head) {

        int count = 0;

        Node curr = head;

        while (curr != null) {

            count++;

            curr = curr.next;
        }

        return count;
    }

    // Display linked list
    static void display(Node head) {

        Node curr = head;

        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Test Case 1: Multiple nodes
        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);

        System.out.println("Test Case 1:");
        display(head1);
        System.out.println("Number of nodes: " + countNodes(head1));

        // Test Case 2: Single node
        Node head2 = new Node(100);

        System.out.println("\nTest Case 2:");
        display(head2);
        System.out.println("Number of nodes: " + countNodes(head2));

        // Test Case 3: Empty linked list
        Node head3 = null;

        System.out.println("\nTest Case 3:");
        display(head3);
        System.out.println("Number of nodes: " + countNodes(head3));
    }
}
