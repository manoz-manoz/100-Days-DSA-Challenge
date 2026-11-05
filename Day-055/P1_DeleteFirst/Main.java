class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Delete the first node
    public static Node deleteFirstNode(Node head) {

        // If the list is empty
        if (head == null) {
            return null;
        }

        // Move head to the second node
        head = head.next;

        return head;
    }

    // Display linked list
    public static void printList(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        // Create linked list
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("Before deletion:");
        printList(head);

        // Delete first node
        head = deleteFirstNode(head);

        System.out.println("After deletion:");
        printList(head);
    }
}