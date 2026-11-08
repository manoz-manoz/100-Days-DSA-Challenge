
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

    // Reverse a linked list
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    // Check whether linked list is palindrome
    public static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node secondHalf = reverse(slow.next);

        // Step 3: Compare both halves
        Node first = head;
        Node second = secondHalf;

        boolean palindrome = true;

        while (second != null) {
            if (first.data != second.data) {
                palindrome = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Step 4: Restore original linked list
        slow.next = reverse(secondHalf);

        return palindrome;
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
            int data = sc.nextInt();
            head = insert(head, data);
        }

        System.out.print("Linked List: ");
        display(head);

        if (isPalindrome(head)) {
            System.out.println("Palindrome Linked List");
        } else {
            System.out.println("Not a Palindrome Linked List");
        }

        System.out.print("Original Linked List: ");
        display(head);

        sc.close();
    }
}
