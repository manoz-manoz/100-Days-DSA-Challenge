
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

    // Swap nodes in pairs
    public static Node swapPairs(Node head) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node prev = dummy;

        while (prev.next != null && prev.next.next != null) {

            Node first = prev.next;
            Node second = first.next;

            // Swap the nodes
            first.next = second.next;
            second.next = first;
            prev.next = second;

            // Move to the next pair
            prev = first;
        }

        return dummy.next;
    }

    // Display linked list
    public static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data);

            if (current.next != null) {
                System.out.print(" -> ");
            }

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        Node head = null;

        System.out.println("Enter node values:");

        for (int i = 0; i < n; i++) {
            head = insert(head, sc.nextInt());
        }

        System.out.print("Original Linked List: ");
        display(head);

        head = swapPairs(head);

        System.out.print("After Swapping Pairs: ");
        display(head);

        sc.close();
    }
}
