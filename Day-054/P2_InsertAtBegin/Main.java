
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Insert a node at the beginning
    static Node insertAtBeginning(Node head, int value) {

        // Step 1: Create a new node
        Node newNode = new Node(value);

        // Step 2: Connect new node to the current head
        newNode.next = head;

        // Step 3: Update head
        head = newNode;

        return head;
    }

    // Display the linked list
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

        System.out.println("Before insertion:");
        display(head1);

        head1 = insertAtBeginning(head1, 5);

        System.out.println("After insertion:");
        display(head1);


        // Test Case 2: Empty linked list
        Node head2 = null;

        System.out.println("\nEmpty linked list:");

        head2 = insertAtBeginning(head2, 100);

        display(head2);


        // Test Case 3: Single-node linked list
        Node head3 = new Node(50);

        System.out.println("\nSingle-node linked list:");
        display(head3);

        head3 = insertAtBeginning(head3, 25);

        System.out.println("After insertion:");
        display(head3);
    }
}
