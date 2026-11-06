import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {

    // Insert node at the end
    public static Node insertAtEnd(Node head, int data) {

        Node newNode = new Node(data);

        if (head == null) {
            return newNode;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;

        return head;
    }

    // Find middle node using slow and fast pointers
    public static Node findMiddle(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            // Slow moves one step
            slow = slow.next;

            // Fast moves two steps
            fast = fast.next.next;
        }

        return slow;
    }

    // Display linked list
    public static void display(Node head) {

        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Node head = null;

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        System.out.println("Enter node values:");

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            head = insertAtEnd(head, value);
        }

        System.out.println("Linked List:");
        display(head);

        Node middle = findMiddle(head);

        if (middle != null) {
            System.out.println("Middle Node: " + middle.data);
        } else {
            System.out.println("Linked List is empty.");
        }

        sc.close();
    }
}
