
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
    public static Node insert(Node head, int data) {
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

    // Find length of cycle
    public static int findCycleLength(Node head) {
        Node slow = head;
        Node fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {

                // Step 2: Calculate cycle length
                int count = 1;
                Node current = slow.next;

                while (current != slow) {
                    count++;
                    current = current.next;
                }

                return count;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        Node head = null;

        System.out.println("Enter node values:");

        for (int i = 0; i < n; i++) {
            int data = sc.nextInt();
            head = insert(head, data);
        }

        // Enter 0-based position where cycle starts.
        // Enter -1 for no cycle.
        System.out.print("Enter cycle starting position (-1 for no cycle): ");
        int pos = sc.nextInt();

        if (pos >= 0 && pos < n) {
            Node current = head;
            Node cycleNode = null;
            Node last = null;

            int index = 0;

            while (current != null) {
                if (index == pos) {
                    cycleNode = current;
                }

                last = current;
                current = current.next;
                index++;
            }

            last.next = cycleNode;
        } else if (pos != -1) {
            System.out.println("Invalid cycle position.");
            sc.close();
            return;
        }

        int length = findCycleLength(head);

        System.out.println("Length of cycle: " + length);

        sc.close();
    }
}
