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

    // Merge two sorted linked lists
    public static Node mergeTwoLists(Node list1, Node list2) {

        Node dummy = new Node(0);
        Node tail = dummy;

        while (list1 != null && list2 != null) {

            if (list1.data <= list2.data) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }

            tail = tail.next;
        }

        // Attach remaining nodes
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
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

        Node list1 = null;
        Node list2 = null;

        System.out.print("Enter number of nodes in List 1: ");
        int n1 = sc.nextInt();

        System.out.println("Enter sorted values for List 1:");

        for (int i = 0; i < n1; i++) {
            list1 = insert(list1, sc.nextInt());
        }

        System.out.print("Enter number of nodes in List 2: ");
        int n2 = sc.nextInt();

        System.out.println("Enter sorted values for List 2:");

        for (int i = 0; i < n2; i++) {
            list2 = insert(list2, sc.nextInt());
        }

        System.out.print("List 1: ");
        display(list1);

        System.out.print("List 2: ");
        display(list2);

        Node mergedList = mergeTwoLists(list1, list2);

        System.out.print("Merged List: ");
        display(mergedList);

        sc.close();
    }
}