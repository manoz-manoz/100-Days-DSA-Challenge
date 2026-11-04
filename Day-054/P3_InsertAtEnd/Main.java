
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Insert a node at the end
    static Node insertAtEnd(Node head, int value) {

        // Step 1: Create a new node
        Node newNode = new Node(value);

        // Step 2: Handle an empty linked list
        if (head == null) {
            return newNode;
        }

        // Step 3: Traverse to the last node
        Node curr = head;

        while (curr.next != null) {
            curr = curr.next;
        }

        // Step 4: Connect the last node to newNode
        curr.next = newNode;

        // Step 5: Return the original head
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

        System.out.println("Test Case 1:");
        System.out.print("Before insertion: ");
        display(head1);

        head1 = insertAtEnd(head1, 40);

        System.out.print("After insertion:  ");
        display(head1);


        // Test Case 2: Empty linked list
        Node head2 = null;

        System.out.println("\nTest Case 2:");
        System.out.print("Before insertion: ");
        display(head2);

        head2 = insertAtEnd(head2, 100);

        System.out.print("After insertion:  ");
        display(head2);


        // Test Case 3: Single-node linked list
        Node head3 = new Node(50);

        System.out.println("\nTest Case 3:");
        System.out.print("Before insertion: ");
        display(head3);

        head3 = insertAtEnd(head3, 60);

        System.out.print("After insertion:  ");
        display(head3);


        // Test Case 4: Insert multiple nodes
        Node head4 = new Node(1);

        head4 = insertAtEnd(head4, 2);
        head4 = insertAtEnd(head4, 3);
        head4 = insertAtEnd(head4, 4);

        System.out.println("\nTest Case 4:");
        display(head4);
    }
}
