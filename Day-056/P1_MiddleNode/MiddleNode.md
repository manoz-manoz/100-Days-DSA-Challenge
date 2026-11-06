# Find Middle Node of a Linked List

## 1. Problem in simple words

Given the head of a singly linked list, find and return the middle node.

- If the list has an odd number of nodes, return the exact middle node.
- If the list has an even number of nodes, return the second middle node (standard LeetCode approach).

**Example 1:**

```text
Input:  10 -> 20 -> 30 -> 40 -> 50 -> null
Output: 30
```

Explanation: `30` is the middle node.

**Example 2:**

```text
Input:  10 -> 20 -> 30 -> 40 -> null
Output: 30
```

Explanation: The two middle nodes are `20` and `30`. We return the second middle node.

**Example 3:**

```text
Input:  10 -> null
Output: 10
```

**Example 4:**

```text
Input: null
Output: null
```

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of a singly linked list.

**Output:**
- Reference to the middle node.

**Important conditions:**
- Empty list: Return `null`.
- Single node: Return that node.
- Odd length: Return the exact middle.
- Even length: Return the second middle.

## 3. Brute Force Approach

### Intuition

First, count the total number of nodes. Then traverse again to reach the middle position.

If there are `n` nodes, the middle position (using zero-based indexing for the second middle) is:

**Middle index = n / 2**

### Algorithm

1. Initialize `count = 0`.
2. Traverse the list and count all nodes.
3. Calculate `middle = count / 2`.
4. Start again from `head`.
5. Move `middle` steps.
6. Return the current node.

### Pseudocode

```text
count = 0
current = head

WHILE current != null:
    count++
    current = current.next

middle = count / 2
current = head

FOR i = 0 TO middle - 1:
    current = current.next

RETURN current
```

### Dry Run

Input:

```text
10 -> 20 -> 30 -> 40 -> 50 -> null
```

- Total nodes = 5
- Middle index = 5 / 2 = 2
- Start at index 0.
- Move two positions.

Result: `30`

### Complexity

- **Time:** O(n) — two traversals, which is still linear.
- **Auxiliary Space:** O(1) — only a few variables.

## 4. Find the Bottleneck

The brute force approach traverses the linked list twice:

1. First traversal counts nodes.
2. Second traversal finds the middle.

We can avoid counting the nodes.

**Observation:** If one pointer moves one step and another moves two steps, the slower pointer reaches the middle when the faster pointer reaches the end.

This leads to the Slow and Fast Pointer technique.

## 5. Identify the Pattern

**Pattern: Two Pointers – Slow and Fast Pointers (Tortoise and Hare).**

- `slow` moves one node at a time.
- `fast` moves two nodes at a time.

**Why this pattern works:**

The fast pointer travels twice as quickly as the slow pointer. When the fast pointer reaches the end, the slow pointer has travelled approximately half the list.

**Related problems:**
- Detect Cycle in Linked List.
- Find Starting Point of Cycle.
- Find Length of Cycle.
- Check Palindrome Linked List.
- Reorder List.
- Find Middle of Linked List.

**Recognition:** Use slow and fast pointers when a linked list problem involves finding the middle, detecting cycles, or determining positions relative to the end without knowing the list length.

## 6. Build Intuition

Consider:

```text
10 -> 20 -> 30 -> 40 -> 50 -> null
```

Initially:

```java
Node slow = head;
Node fast = head;
```

Both pointers start at node `10`.

**Iteration 1:**

- Slow moves from `10` to `20`.
- Fast moves from `10` to `30`.

**Iteration 2:**

- Slow moves from `20` to `30`.
- Fast moves from `30` to `50`.

Now `fast.next == null`, so the loop stops.

The slow pointer is at `30`, which is the middle node.

**Important loop condition:**

```java
while (fast != null && fast.next != null)
```

Why?

- `fast != null` ensures the fast pointer exists.
- `fast.next != null` ensures it can safely move two steps.
- This condition also makes the slow pointer return the second middle node for even-length lists.

## 7. Optimized Approach

### Intuition

Use two pointers moving at different speeds. When the fast pointer reaches the end, the slow pointer points to the middle.

### Algorithm

1. Initialize `slow = head`.
2. Initialize `fast = head`.
3. While `fast != null` and `fast.next != null`:
   - Move slow by one node.
   - Move fast by two nodes.
4. Return slow.

### Pseudocode

```text
FUNCTION findMiddle(head):

    slow = head
    fast = head

    WHILE fast != null AND fast.next != null:

        slow = slow.next
        fast = fast.next.next

    RETURN slow
```

### Java Code

```java
public static Node findMiddle(Node head) {

    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {

        slow = slow.next;
        fast = fast.next.next;
    }

    return slow;
}
```

### Dry Run

Input:

```text
10 -> 20 -> 30 -> 40 -> 50 -> null
```

Initially:
- `slow = 10`
- `fast = 10`

**Iteration 1:**
- `slow = 20`
- `fast = 30`

**Iteration 2:**
- `slow = 30`
- `fast = 50`

**Next iteration:**

`fast.next == null`, so the loop stops.

Final answer: `30`

### Why Does It Work?

For every one step moved by the slow pointer, the fast pointer moves two steps.

Therefore:

- Fast travels twice the distance of slow.
- When fast reaches the end, slow has travelled approximately half the distance.
- Thus, slow points to the middle node.

For an even number of nodes, this loop condition makes slow reach the second middle node.

### Complexity Analysis

- **Time Complexity: O(n)** — the fast pointer moves two nodes per iteration, so the list is traversed in linear time.
- **Auxiliary Space: O(1)** — only two pointer variables are used.

**Comparison:**
- Brute Force: O(n) time, O(1) space, two traversals.
- Optimized: O(n) time, O(1) space, one traversal.

## 8. Common Mistakes

1. **Wrong loop condition:** Use `fast != null && fast.next != null` to prevent `NullPointerException`.
2. **Moving fast by one step:** Fast must move two steps using `fast = fast.next.next`.
3. **Moving slow by two steps:** Slow must move exactly one step.
4. **Returning fast:** Return `slow`, because it points to the middle.
5. **Not handling an empty list:** Both pointers start as null, and the method correctly returns null.
6. **Wrong even-length expectation:** Starting both pointers at head with this loop condition returns the second middle.
7. **Accessing `fast.next.next` without checking:** Always verify `fast.next != null` first.
8. **Confusing node position with array index:** For the second middle, the zero-based index is `n / 2`.

## 9. Interview Explanation

"I need to find the middle node of a singly linked list.

A brute force approach is to traverse the list once to count the nodes and traverse again to reach the middle position.

We can optimize this using the slow and fast pointer technique.

I initialize both pointers at the head. In each iteration, the slow pointer moves one step, while the fast pointer moves two steps.

I continue until the fast pointer reaches the end or its next node becomes null.

At that point, the slow pointer points to the middle node.

For an even-length list, this implementation returns the second middle node.

The time complexity is O(n), and the auxiliary space complexity is O(1)."