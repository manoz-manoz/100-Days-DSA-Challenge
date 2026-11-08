# Merge Two Sorted Lists

## 1. Problem in simple words

Given the heads of two sorted singly linked lists, merge them into one sorted linked list.

The merged list must also be sorted in ascending order.

**Example 1:**

```text
List 1: 1 → 3 → 5
List 2: 2 → 4 → 6

Output: 1 → 2 → 3 → 4 → 5 → 6
```

**Example 2:**

```text
List 1: 1 → 2 → 4
List 2: 1 → 3 → 4

Output: 1 → 1 → 2 → 3 → 4 → 4
```

## 2. Input and Output

**Input:**
- Head of the first sorted linked list.
- Head of the second sorted linked list.

**Output:**
- Head of the merged sorted linked list.

**Important:** Both input linked lists are already sorted.

## 3. Brute Force Approach

**Idea:** Store all elements from both linked lists in an ArrayList, sort them, and create a new linked list.

**Algorithm:**

1. Traverse the first linked list and store its values.
2. Traverse the second linked list and store its values.
3. Sort the ArrayList.
4. Create a new linked list using the sorted values.
5. Return the new head.

**Pseudocode:**

```text
function mergeTwoLists(list1, list2):

    create empty ArrayList

    traverse list1:
        add each value to ArrayList

    traverse list2:
        add each value to ArrayList

    sort ArrayList

    create new linked list from sorted values

    return new head
```

**Complexity:**
- Time: O((n + m) log(n + m))
- Space: O(n + m)

## 4. Find the Bottleneck

Both linked lists are already sorted.

Sorting all elements again is unnecessary.

We can take advantage of their existing sorted order and compare the current nodes directly.

## 5. Identify the Pattern

**Pattern: Two Pointers + Dummy Node**

We use:
- Two pointers to traverse both linked lists.
- A dummy node to simplify building the merged list.
- A tail pointer to attach the smaller node.

## 6. Build Intuition

Consider two sorted linked lists:

```text
List 1: 1 → 3 → 5
List 2: 2 → 4 → 6
```

Compare the first available elements.

**Step 1:** Compare 1 and 2.

Choose 1.

```text
Merged: 1
```

**Step 2:** Compare 3 and 2.

Choose 2.

```text
Merged: 1 → 2
```

**Step 3:** Compare 3 and 4.

Choose 3.

```text
Merged: 1 → 2 → 3
```

Continue until one list becomes empty.

**Step 4:** Attach the remaining nodes.

```text
Merged: 1 → 2 → 3 → 4 → 5 → 6
```

The important observation is that we never need to compare every node with every other node.

## 7. Optimized Approach

**Algorithm:**

1. Create a dummy node.
2. Initialize `tail = dummy`.
3. Traverse both lists while neither is empty.
4. Compare `list1.data` and `list2.data`.
5. Attach the smaller node to `tail.next`.
6. Move the pointer of the selected list forward.
7. Move `tail` forward.
8. When one list becomes empty, attach the remaining list.
9. Return `dummy.next`.

**Pseudocode:**

```text
function mergeTwoLists(list1, list2):

    dummy = new Node(0)
    tail = dummy

    while list1 != null AND list2 != null:

        if list1.data <= list2.data:
            tail.next = list1
            list1 = list1.next

        else:
            tail.next = list2
            list2 = list2.next

        tail = tail.next

    if list1 != null:
        tail.next = list1

    else:
        tail.next = list2

    return dummy.next
```

**Complexity:**
- Time: O(n + m)
- Auxiliary Space: O(1)

Here, `n` is the number of nodes in the first list and `m` is the number of nodes in the second list.

## 8. Common Mistakes

- Forgetting that both input lists are already sorted.
- Moving the selected list pointer before attaching its node.
- Forgetting to move the tail pointer.
- Forgetting to attach the remaining nodes.
- Returning `dummy` instead of `dummy.next`.
- Not handling an empty first or second list.
- Creating unnecessary new nodes when existing nodes can be reused.

## 9. Interview Explanation

"I will solve this problem using two pointers and a dummy node.

First, I create a dummy node and maintain a tail pointer.

I compare the current nodes of both sorted linked lists. I attach the smaller node to the merged list and move the corresponding pointer forward.

I repeat this until one list becomes empty.

Finally, I attach the remaining nodes and return dummy.next.

The time complexity is O(n + m), and the auxiliary space complexity is O(1), because I reuse the existing nodes."