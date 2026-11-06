# Detect Cycle in a Linked List

## 1. Problem in simple words

Given the head of a singly linked list, determine whether the linked list contains a cycle.

A cycle exists when a node's `next` reference points back to a previously visited node instead of pointing to `null`.

**Example 1:**

```text
1 -> 2 -> 3 -> 4
          ^    |
          |____|
Output: true
```

Explanation: Node `4` points back to node `3`, creating a cycle.

**Example 2:**

```text
1 -> 2 -> 3 -> 4 -> null

Output: false
```

Explanation: The last node points to `null`, so there is no cycle.

**Example 3:**

```text
1 -> null

Output: false
```

**Example 4:**

```text
1 -> 1 (points to itself)

Output: true
```

## 2. Input and Output

**Input:**
- `head`: Reference to the first node of a singly linked list.

**Output:**
- `true` if a cycle exists.
- `false` if no cycle exists.

**Important conditions:**
- Empty list: Return `false`.
- Single node pointing to `null`: Return `false`.
- Single node pointing to itself: Return `true`.
- A cycle can begin at any node.

## 3. Brute Force Approach

### Intuition

Maintain a collection of visited nodes.

While traversing the linked list:
- If the current node was already visited, a cycle exists.
- Otherwise, mark it as visited and move forward.
- If we reach `null`, no cycle exists.

### Algorithm

1. Create a `HashSet` to store visited nodes.
2. Initialize `current = head`.
3. Traverse while `current != null`.
4. If the current node already exists in the set, return `true`.
5. Otherwise, add it to the set.
6. Move to `current.next`.
7. If traversal reaches `null`, return `false`.

### Pseudocode

```text
FUNCTION hasCycle(head):

    CREATE visited set
    current = head

    WHILE current != null:

        IF current exists in visited:
            RETURN true

        ADD current to visited

        current = current.next

    RETURN false
```

### Dry Run

Input:

```text
10 -> 20 -> 30 -> 40
       ^         |
       |_________|
```

Traversal:

- Visit 10: Not visited, add it.
- Visit 20: Not visited, add it.
- Visit 30: Not visited, add it.
- Visit 40: Not visited, add it.
- Next node is 20: Already visited.

Result: `true`

### Complexity

- **Time:** O(n) expected — each node is visited once, assuming average constant-time HashSet operations.
- **Auxiliary Space:** O(n) — storing visited nodes.

## 4. Find the Bottleneck

The brute force approach uses a HashSet to remember every visited node.

This requires additional memory proportional to the number of nodes.

**Observation:**

Instead of remembering every node, use two pointers moving at different speeds.

- Slow pointer moves one step.
- Fast pointer moves two steps.

If a cycle exists, the fast pointer will eventually meet the slow pointer.

This eliminates the need for extra memory.

## 5. Identify the Pattern

**Pattern: Two Pointers – Floyd's Cycle Detection Algorithm (Tortoise and Hare).**

**Why this pattern?**

- Slow moves one step at a time.
- Fast moves two steps at a time.
- If a cycle exists, both pointers eventually meet inside the cycle.
- If no cycle exists, fast eventually reaches `null`.

**Related problems:**
- Find Middle of Linked List.
- Find Starting Point of Cycle.
- Find Length of Cycle.
- Remove Cycle in Linked List.
- Happy Number.

**Recognition:** Use Floyd's algorithm when you need to detect a cycle without using extra memory.

## 6. Build Intuition

Consider:

```text
10 -> 20 -> 30 -> 40
       ^         |
       |_________|
```

Initially:

```java
Node slow = head;
Node fast = head;
```

Both pointers start at node `10`.

**Iteration 1:**

- Slow moves: 10 → 20
- Fast moves: 10 → 30

**Iteration 2:**

- Slow moves: 20 → 30
- Fast moves: 30 → 20

**Iteration 3:**

- Slow moves: 30 → 40
- Fast moves: 20 → 40

Both pointers meet at node `40`.

Therefore, a cycle exists.

**Core logic:**

```java
while (fast != null && fast.next != null) {

    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        return true;
    }
}
```

**Important:** Compare nodes using `==`, not their data values.

Two different nodes can contain the same data but have different memory references.

## 7. Optimized Approach

### Intuition

Use two pointers moving at different speeds.

If they meet, a cycle exists. If the fast pointer reaches `null`, no cycle exists.

### Algorithm

1. Initialize `slow = head`.
2. Initialize `fast = head`.
3. While `fast != null` and `fast.next != null`:
   - Move slow one step.
   - Move fast two steps.
   - If `slow == fast`, return `true`.
4. If the loop finishes, return `false`.

### Pseudocode

```text
FUNCTION hasCycle(head):

    slow = head
    fast = head

    WHILE fast != null AND fast.next != null:

        slow = slow.next
        fast = fast.next.next

        IF slow == fast:
            RETURN true

    RETURN false
```

### Java Code

```java
public static boolean hasCycle(Node head) {

    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {

        slow = slow.next;
        fast = fast.next.next;

        if (slow == fast) {
            return true;
        }
    }

    return false;
}
```

### Dry Run

Consider:

```text
10 -> 20 -> 30 -> 40
       ^         |
       |_________|
```

Initially:
- `slow = 10`
- `fast = 10`

**Iteration 1:**
- `slow = 20`
- `fast = 30`
- Not equal.

**Iteration 2:**
- `slow = 30`
- `fast = 20`
- Not equal.

**Iteration 3:**
- `slow = 40`
- `fast = 40`
- Both references are equal.

Result: `true`

### Why Does It Work?

Inside a cycle, the fast pointer gains one node on the slow pointer during every iteration.

Because the cycle has a finite number of nodes, this distance eventually becomes zero modulo the cycle length.

Therefore, the two pointers must eventually meet.

If there is no cycle, the fast pointer eventually reaches `null`, ending the loop.

### Complexity Analysis

- **Time Complexity: O(n)** — each pointer moves at most a linear number of steps before meeting or reaching the end.
- **Auxiliary Space: O(1)** — only two pointers are used.

**Comparison:**
- Brute Force: O(n) time, O(n) space.
- Optimized: O(n) time, O(1) space.

## 8. Common Mistakes

1. **Using `fast.next.next` without checking:** Check both `fast != null` and `fast.next != null` first.
2. **Moving fast only one step:** Fast must move two steps.
3. **Comparing node values:** Use `slow == fast`, not `slow.data == fast.data`.
4. **Checking equality before moving pointers:** Both initially point to head, which would incorrectly indicate a cycle.
5. **Forgetting empty-list handling:** The loop naturally skips and returns `false`.
6. **Returning false immediately when pointers differ:** They may meet in a later iteration.
7. **Using HashSet unnecessarily:** Floyd's algorithm achieves O(1) auxiliary space.
8. **Confusing cycle detection with cycle starting point:** Detecting a cycle only tells us whether one exists; finding its entry requires an additional step.

## 9. Interview Explanation

"I need to determine whether a singly linked list contains a cycle.

A brute force approach is to maintain a HashSet of visited nodes. Whenever I encounter an already visited node, I know that a cycle exists. However, this requires O(n) extra space.

I can optimize this using Floyd's Cycle Detection Algorithm, also known as the Tortoise and Hare algorithm.

I initialize two pointers, slow and fast, at the head.

The slow pointer moves one step, while the fast pointer moves two steps.

If both pointers meet, a cycle exists, so I return true.

If the fast pointer reaches null or its next reference becomes null, there is no cycle, so I return false.

The optimized solution takes O(n) time and O(1) auxiliary space."