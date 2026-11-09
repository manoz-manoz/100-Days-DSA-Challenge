# Swap Nodes in Pairs

## 1. Problem in simple words

Given the head of a singly linked list, swap every two adjacent nodes and return the modified list.

**Important:** Swap the actual nodes, not just their data values.

**Example 1:**

```text
Input:  1 → 2 → 3 → 4
Output: 2 → 1 → 4 → 3
```

**Example 2:**

```text
Input:  1 → 2 → 3
Output: 2 → 1 → 3
```

**Example 3:**

```text
Input:  1
Output: 1
```

## 2. Input and Output

**Input:**
- Head of a singly linked list.

**Output:**
- Head of the linked list after swapping adjacent pairs.

**Constraints:**
- The number of nodes can be zero or more.
- Node values can be repeated.
- If the number of nodes is odd, the last node remains unchanged.

## 3. Brute Force Approach

**Idea:** Swap the data values of adjacent nodes.

**Algorithm:**

1. Start from the head.
2. Check whether the current node and its next node exist.
3. Swap their data values.
4. Move two nodes forward.
5. Repeat until fewer than two nodes remain.
6. Return the head.

**Pseudocode:**

```text
function swapPairs(head):

    current = head

    while current != null AND current.next != null:

        temp = current.data
        current.data = current.next.data
        current.next.data = temp

        current = current.next.next

    return head
```

**Complexity:**
- Time: O(n)
- Auxiliary Space: O(1)

**Limitation:** This swaps values instead of actual nodes. Some interview problems specifically require node manipulation.

## 4. Find the Bottleneck

Swapping data does not change the actual node connections.

The desired operation is to change the `next` references.

We need to carefully reconnect three parts:
- Previous node.
- First node of the pair.
- Second node of the pair.

## 5. Identify the Pattern

**Pattern: Linked List Pointer Manipulation + Dummy Node**

We use:
- Dummy node to handle head changes.
- Previous pointer to connect the swapped pair to the preceding list.
- First and second pointers to identify the pair.

## 6. Build Intuition

Consider:

```text
1 → 2 → 3 → 4
```

We want:

```text
2 → 1 → 4 → 3
```

For the first pair:

```text
prev → 1 → 2 → 3
```

Perform three pointer changes:

**Step 1:** Connect `prev` to node 2.

**Step 2:** Connect node 1 to node 3.

**Step 3:** Connect node 2 to node 1.

Result:

```text
prev → 2 → 1 → 3
```

Repeat for the next pair.

## 7. Optimized Approach

**Algorithm:**

1. Create a dummy node.
2. Set `dummy.next = head`.
3. Initialize `prev = dummy`.
4. While two nodes are available:
   - Set `first = prev.next`.
   - Set `second = first.next`.
5. Change the pointers:
   - `first.next = second.next`
   - `second.next = first`
   - `prev.next = second`
6. Move `prev = first`.
7. Repeat.
8. Return `dummy.next`.

**Java-style pseudocode:**

```text
function swapPairs(head):

    dummy = new Node(0)
    dummy.next = head

    prev = dummy

    while prev.next != null AND prev.next.next != null:

        first = prev.next
        second = first.next

        first.next = second.next
        second.next = first
        prev.next = second

        prev = first

    return dummy.next
```

**Complexity:**
- Time: O(n)
- Auxiliary Space: O(1)

## 8. Common Mistakes

- Forgetting to update `prev.next`.
- Changing pointers in the wrong order.
- Losing access to the remaining linked list.
- Forgetting to move `prev` to the original first node.
- Not handling an empty list.
- Not handling an odd number of nodes.
- Swapping values when actual node swapping is required.

## 9. Interview Explanation

"I will solve this problem using pointer manipulation and a dummy node.

First, I create a dummy node to simplify handling changes to the head.

For every pair, I identify the first and second nodes. Then I reconnect their next references to reverse their order.

After swapping, I move the previous pointer to the original first node and continue with the next pair.

If one node remains without a pair, it stays unchanged.

The solution takes O(n) time and O(1) auxiliary space."