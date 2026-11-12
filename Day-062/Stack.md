# Stack in Java

## 1. What is a Stack?

A Stack is a linear data structure that follows the **LIFO (Last In, First Out)** principle.

The element inserted last is removed first.

**Real-world example:**

Imagine a stack of plates.

- You place a new plate on top.
- You remove the top plate first.
- You cannot directly remove a plate from the middle without removing the plates above it.

**Stack representation:**

```text
        TOP
         |
       | 30 |
       | 20 |
       | 10 |
       ------
```

If we insert `40`:

```text
        TOP
         |
       | 40 |
       | 30 |
       | 20 |
       | 10 |
       ------
```

If we remove an element, `40` is removed first.

## 2. Basic Stack Operations

A Stack mainly supports these operations:

**1. Push**

Adds an element to the top of the stack.

```text
push(10)
push(20)
push(30)
```

Result:

```text
TOP → 30 → 20 → 10
```

**2. Pop**

Removes and returns the top element.

```text
pop()
```

Removed element: `30`

Remaining stack:

```text
TOP → 20 → 10
```

**3. Peek**

Returns the top element without removing it.

```text
peek()
```

Result: `20`

**4. isEmpty**

Checks whether the stack contains any elements.

```text
isEmpty()
```

Returns:
- `true` if empty.
- `false` if not empty.

**5. Search**

Searches for an element and returns its 1-based position from the top.

Example:

```text
TOP → 30 → 20 → 10
```

```java
stack.search(20); // 2
stack.search(10); // 3
stack.search(50); // -1
```

## 3. Stack in Java Collection Framework

Java provides a built-in `Stack` class inside the `java.util` package.

It extends the `Vector` class.

**Inheritance:**

```text
Collection
    |
   List
    |
   Vector
    |
   Stack
```

**Import:**

```java
import java.util.Stack;
```

**Declaration:**

```java
Stack<Integer> stack = new Stack<>();
```

Here:
- `Stack` is the class.
- `Integer` specifies the type of elements.
- `stack` is the reference variable.
- `new Stack<>()` creates the Stack object.

**Example:**

```java
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println(stack);
    }
}
```

Output:

```text
[10, 20, 30]
```

The last element (`30`) is the top of the stack.

## 4. Important Stack Methods

### 1. push(E item)

Adds an element to the top.

```java
stack.push(10);
stack.push(20);
stack.push(30);
```

### 2. pop()

Removes and returns the top element.

```java
int removed = stack.pop();

System.out.println(removed);
```

Output:

```text
30
```

**Important:** Calling `pop()` on an empty Stack throws `EmptyStackException`.

### 3. peek()

Returns the top element without removing it.

```java
int top = stack.peek();
```

### 4. empty()

Checks whether the Stack is empty.

```java
System.out.println(stack.empty());
```

### 5. search(Object o)

Returns the 1-based position of an element from the top.

```java
stack.search(20);
```

Returns `2` for `[10, 20, 30]`.

Returns `-1` if the element is not found.

### 6. size()

Returns the number of elements.

```java
stack.size();
```

### 7. contains(Object o)

Checks whether an element exists.

```java
stack.contains(20);
```

### 8. clear()

Removes all elements.

```java
stack.clear();
```

### 9. get(int index)

Returns an element at a specified index.

```java
stack.get(0);
```

This is inherited from `Vector`. It accesses by index, not by stack position.

### 10. set(int index, E element)

Replaces an element at a specified index.

```java
stack.set(0, 100);
```

This is also inherited from `Vector`.

## 5. Complete Stack Methods Example

```java
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        // Push
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Stack: " + stack);

        // Peek
        System.out.println("Top: " + stack.peek());

        // Pop
        System.out.println("Removed: " + stack.pop());

        // Size
        System.out.println("Size: " + stack.size());

        // Search
        System.out.println("Search 10: " + stack.search(10));

        // Contains
        System.out.println("Contains 20: " + stack.contains(20));

        // Empty
        System.out.println("Is Empty: " + stack.empty());

        // Clear
        stack.clear();

        System.out.println("After Clear: " + stack);
        System.out.println("Is Empty: " + stack.empty());
    }
}
```

## 6. How to Copy a Stack in Java

There are multiple ways to copy a Stack.

### Approach 1: Copy Constructor

The simplest way is to pass the existing Stack to another Stack's constructor.

```java
Stack<Integer> original = new Stack<>();

original.push(10);
original.push(20);
original.push(30);

Stack<Integer> copy = new Stack<>(original);
```

Both stacks contain the same elements.

### Approach 2: addAll()

Create a new Stack and copy all elements using `addAll()`.

```java
Stack<Integer> copy = new Stack<>();

copy.addAll(original);
```

### Approach 3: Using push()

Traverse the original Stack and push every element into the new Stack.

```java
Stack<Integer> copy = new Stack<>();

for (int element : original) {
    copy.push(element);
}
```

This preserves the original order.

### Approach 4: Using clone()

The Stack class inherits `clone()` from `Vector`.

```java
Stack<Integer> copy = (Stack<Integer>) original.clone();
```

This creates a shallow copy.

## 7. Understanding Stack Copying

Consider:

```text
Original Stack:

TOP → 30
       20
       10
```

After copying:

```text
Original Stack:       Copy Stack:

TOP → 30              TOP → 30
       20                    20
       10                    10
```

Now execute:

```java
copy.push(40);
```

Result:

```text
Original Stack:       Copy Stack:

TOP → 30              TOP → 40
       20                    30
       10                    20
                             10
```

The original Stack remains unchanged.

**Important concepts:**

- Copying creates a separate Stack object.
- Modifying the copied Stack does not modify the original Stack's structure.
- The standard copying methods create shallow copies.
- If Stack elements are mutable objects, both stacks can still refer to the same underlying objects.

## 8. Important Stack Properties and Complexity

- LIFO: Last In, First Out.
- `push()`: O(1) amortized.
- `pop()`: O(1).
- `peek()`: O(1).
- `empty()`: O(1).
- `search()`: O(n).
- `contains()`: O(n).
- `size()`: O(1).
- Copying: O(n).

## 9. Interview Explanation

"Stack is a linear data structure that follows the Last In, First Out principle.

In Java, Stack is available in the java.util package. It extends Vector and provides methods such as push, pop, peek, empty, and search.

Push inserts an element, pop removes the top element, and peek reads the top element without removing it.

We can copy a Stack using its copy constructor, addAll(), iteration with push(), or clone().

A copied Stack has a separate structure, although the standard copying operations are shallow copies."