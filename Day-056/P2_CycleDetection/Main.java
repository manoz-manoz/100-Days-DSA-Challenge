
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

    // Detect cycle using Floyd's algorithm
    public static boolean hasCycle(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            // Slow moves one step
            slow = slow.next;

            // Fast moves two steps
            fast = fast.next.next;

            // Check whether both pointers meet
            if (slow == fast) {
                return true;
            }
        }

        return false;
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

        // Create a cycle for testing
        if (n > 2) {
            Node current = head;
            Node cycleNode = head.next;

            while (current.next != null) {
                current = current.next;
            }

            // Last node points to second node
            current.next = cycleNode;
        }

        // Detect cycle
        if (hasCycle(head)) {
            System.out.println("Cycle detected: true");
        } else {
            System.out.println("Cycle detected: false");
        }

        sc.close();
    }
}
