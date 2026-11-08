# Palindrome Linked List

## 1. Problem in simple words

Given the head of a singly linked list, determine whether the linked list is a palindrome.

A palindrome is a sequence that reads the same forward and backward.

**Examples:**

```text
1 → 2 → 2 → 1
Output: true
```

```text
1 → 2 → 3 → 2 → 1
Output: true
```

```text
1 → 2 → 3 → 4
Output: false
```

## 2. Input and Output

**Input:**
- Head of a singly linked list.

**Output:**
- `true` if the linked list is a palindrome.
- `false` otherwise.

**Constraints:**
- The number of nodes can be zero or more.
- Node values can be positive, negative, or zero.

## 3. Brute Force Approach

**Idea:** Store all linked list elements in an ArrayList and compare them from both ends.

**Algorithm:**

1. Create an ArrayList.
2. Traverse the linked list and store every node's data.
3. Initialize `left = 0` and `right = size - 1`.
4. Compare elements at both positions.
5. If they differ, return `false`.
6. Move `left` forward and `right` backward.
7. If all elements match, return `true`.

**Pseudocode:**

```text
function isPalindrome(head):
    create an empty list

    current = head

    while current != null:
        add current.data to list
        current = current.next

    left = 0
    right = list.size - 1

    while left < right:
        if list[left] != list[right]:
            return false

        left++
        right--

    return true
```

**Complexity:**
- Time: O(n)
- Space: O(n)

## 4. Find the Bottleneck

The brute force approach stores all node values in an additional ArrayList.

This requires O(n) extra space.

Can we check whether a linked list is a palindrome without using extra storage?

Yes! We can use:
- Slow and Fast Pointers
- Reverse Linked List
- Two-pointer Comparison

## 5. Identify the Pattern

**Pattern: Fast and Slow Pointers + Reversal**

We combine three concepts:

1. Find the middle node using slow and fast pointers.
2. Reverse the second half of the linked list.
3. Compare the first half with the reversed second half.

This allows us to solve the problem using O(1) auxiliary space.

## 6. Build Intuition

Consider:

```text
1 → 2 → 3 → 2 → 1
```

**Step 1: Find the middle**

```text
1 → 2 → 3 → 2 → 1
          ↑
         slow
```

**Step 2: Reverse the second half**

```text
First half:   1 → 2

Second half:  1 → 2
```

The middle node `3` does not need to be compared.

**Step 3: Compare both halves**

```text
1 == 1
2 == 2
```

All values match.

Therefore, the linked list is a palindrome.

## 7. Optimized Approach

**Algorithm:**

1. If the list is empty or contains one node, return `true`.
2. Initialize `slow` and `fast` at `head`.
3. Move `slow` one step and `fast` two steps until the middle is reached.
4. Reverse the second half of the linked list.
5. Compare nodes from the first half and reversed second half.
6. If any values differ, the list is not a palindrome.
7. If all compared values match, it is a palindrome.

**Pseudocode:**

```text
function isPalindrome(head):

    if head == null or head.next == null:
        return true

    slow = head
    fast = head

    while fast.next != null and fast.next.next != null:
        slow = slow.next
        fast = fast.next.next

    secondHalf = reverse(slow.next)

    first = head
    second = secondHalf

    while second != null:
        if first.data != second.data:
            return false

        first = first.next
        second = second.next

    return true
```

**Important:** In production-quality code, restore the original second half after comparison by reversing it again and reconnecting it. This prevents the palindrome check from permanently modifying the input list.

**Complexity:**
- Time: O(n)
- Auxiliary Space: O(1)

## 8. Common Mistakes

- Incorrectly finding the middle node.
- Forgetting to reverse the second half.
- Comparing the wrong nodes.
- Not handling empty and single-node lists.
- Using `==` to compare node values instead of comparing their integer data.
- Forgetting that even and odd length lists have different middle positions.
- Not restoring the original linked list after checking when the input must remain unchanged.

## 9. Interview Explanation

"I will solve this problem using slow and fast pointers and linked list reversal.

First, I find the middle of the linked list using two pointers. Then I reverse the second half.

Next, I compare the first half with the reversed second half node by node. If all corresponding values match, the linked list is a palindrome.

Otherwise, it is not a palindrome.

The algorithm takes O(n) time and O(1) auxiliary space."