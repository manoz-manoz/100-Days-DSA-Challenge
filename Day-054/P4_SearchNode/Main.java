
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Search for an element in the linked list
    static int search(Node head, int target) {

        Node curr = head;
        int index = 0;

        while (curr != null) {

            // Check whether current node contains target
            if (curr.data == target) {
                return index;
            }

            // Move to the next node
            curr = curr.next;
            index++;
        }

        // Target not found
        return -1;
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

        // Create linked list
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(20);

        System.out.println("Linked List:");
        display(head);

        // Test Case 1: Target exists
        int target1 = 30;
        int result1 = search(head, target1);

        System.out.println("\nSearching for: " + target1);

        if (result1 != -1) {
            System.out.println("Element found at index: " + result1);
        } else {
            System.out.println("Element not found");
        }

        // Test Case 2: Target does not exist
        int target2 = 100;
        int result2 = search(head, target2);

        System.out.println("\nSearching for: " + target2);

        if (result2 != -1) {
            System.out.println("Element found at index: " + result2);
        } else {
            System.out.println("Element not found");
        }

        // Test Case 3: Duplicate value
        int target3 = 20;
        int result3 = search(head, target3);

        System.out.println("\nSearching for: " + target3);

        if (result3 != -1) {
            System.out.println("First occurrence found at index: " + result3);
        } else {
            System.out.println("Element not found");
        }

        // Test Case 4: Search in an empty linked list
        Node emptyHead = null;
        int result4 = search(emptyHead, 50);

        System.out.println("\nSearching in empty linked list:");

        if (result4 != -1) {
            System.out.println("Element found at index: " + result4);
        } else {
            System.out.println("Element not found");
        }
    }
}
