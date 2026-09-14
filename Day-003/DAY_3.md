# 🚀 DAY 3 --- Space Complexity, Recursion & Amortized Analysis

## 🎯 Today's Goal

Understand:

-   Space Complexity
-   Input Space vs Auxiliary Space
-   Recursion Stack Space
-   Recurrence Basics
-   Amortized Analysis
-   Time-Space Tradeoff

------------------------------------------------------------------------

# 1. What is Space Complexity?

**Space Complexity = How much memory an algorithm uses as input grows.**

Think:

``` text
Time  → How much work?
Space → How much extra memory?
```

Example:

``` java
int sum = 0;

for (int i = 0; i < n; i++) {
    sum += arr[i];
}
```

We only use a few variables:

``` text
sum
i
```

The number of variables does not grow with `n`.

Therefore:

``` text
Time  → O(n)
Space → O(1)
```

------------------------------------------------------------------------

# 2. Input Space vs Auxiliary Space

### Input Space

Memory already occupied by the input.

Example:

``` java
int[] arr = new int[n];
```

The array uses:

``` text
O(n)
```

### Auxiliary Space

Extra memory created by our algorithm.

Example:

``` java
int max = arr[0];

for (int i = 1; i < arr.length; i++) {
    max = Math.max(max, arr[i]);
}
```

We only use a few variables.

``` text
Auxiliary Space = O(1)
```

### Remember

``` text
Input Space
     ↓
Memory for input

Auxiliary Space
     ↓
Extra memory used by algorithm
```

------------------------------------------------------------------------

# 3. O(1) Auxiliary Space

Example:

``` java
int sum = 0;

for (int i = 0; i < n; i++) {
    sum += arr[i];
}
```

No new array, map, list, etc. is created.

Therefore:

``` text
Space = O(1)
```

Even if:

``` text
n = 10
n = 1,000
n = 1,000,000
```

the extra memory stays constant.

------------------------------------------------------------------------

# 4. O(n) Auxiliary Space

Example:

``` java
int[] temp = new int[n];

for (int i = 0; i < n; i++) {
    temp[i] = arr[i];
}
```

We create another array containing `n` elements.

Therefore:

``` text
Time  → O(n)
Space → O(n)
```

### Rule

``` text
Extra array of size n → O(n)
HashMap with n items  → O(n)
HashSet with n items  → O(n)
```

------------------------------------------------------------------------

# 5. Time-Space Tradeoff

Sometimes we use more memory to reduce computation.

Example:

``` text
More memory
     ↓
Store information
     ↓
Faster lookup
```

Hashing is a common example.

``` text
More Space → Faster Lookup
Less Space → More Computation
```

This is called a:

> **Time-Space Tradeoff**

------------------------------------------------------------------------

# 6. Recursion Uses Stack Memory

Consider:

``` java
void fun(int n) {
    if (n == 0)
        return;

    fun(n - 1);
}
```

For:

``` text
fun(3)
```

the calls become:

``` text
fun(3)
  ↓
fun(2)
  ↓
fun(1)
  ↓
fun(0)
```

While `fun(0)` is running, the previous calls are still active.

So the stack contains multiple frames.

Maximum depth is approximately:

``` text
n
```

Therefore:

``` text
Time  → O(n)
Space → O(n)
```

### Key idea

> **Recursive depth determines stack space.**

------------------------------------------------------------------------

# 7. Recursion: Total Calls vs Stack Depth

Consider:

``` java
void fun(int n) {
    if (n == 0)
        return;

    fun(n - 1);
    fun(n - 1);
}
```

There are two recursive calls.

The recursion tree grows exponentially:

``` text
        fun(n)
       /      \
   fun(n-1)  fun(n-1)
    /  \       /  \
   ... ...    ... ...
```

Therefore:

``` text
Time = O(2^n)
```

But all calls are not active at the same time.

One path can reach:

``` text
fun(n)
  ↓
fun(n-1)
  ↓
fun(n-2)
  ↓
...
  ↓
fun(0)
```

Maximum depth:

``` text
n
```

Therefore:

``` text
Space = O(n)
```

### ⭐ Important

``` text
Total recursive calls → affects TIME

Maximum active depth → affects SPACE
```

Do not confuse them.

------------------------------------------------------------------------

# 8. What is a Recurrence?

A recurrence describes the work of a recursive algorithm using a smaller
input.

Example:

``` java
void fun(int n) {
    if (n == 0)
        return;

    fun(n - 1);
}
```

We write:

``` text
T(n) = T(n - 1) + O(1)
```

Meaning:

``` text
Work for n
 =
Work for n-1
 +
Current work
```

Expand:

``` text
T(n)
= T(n-1) + 1
= T(n-2) + 1 + 1
= T(n-3) + 1 + 1 + 1
...
```

Therefore:

``` text
T(n) = O(n)
```

------------------------------------------------------------------------

# 9. Recurrence with Two Calls

For:

``` java
void fun(int n) {
    if (n == 0)
        return;

    fun(n - 1);
    fun(n - 1);
}
```

There are two recursive calls.

So:

``` text
T(n) = 2T(n-1) + O(1)
```

Each level roughly doubles.

Therefore:

``` text
Time = O(2^n)
```

------------------------------------------------------------------------

# 10. What is Amortized Analysis?

Amortized analysis looks at the cost of **many operations together**.

It asks:

> "What is the cost per operation over a long sequence?"

It is useful for dynamic arrays such as Java's `ArrayList`.

------------------------------------------------------------------------

# 11. ArrayList Example

Suppose capacity is 4:

``` text
[10][20][30][40]
```

Now we add:

``` java
list.add(50);
```

There is no free space.

Conceptually, the array must:

``` text
1. Create a larger array
2. Copy old elements
3. Insert the new element
```

If there are `n` elements:

``` text
Resize = O(n)
```

But every `add()` does not resize.

Usually:

``` text
add → O(1)
add → O(1)
add → O(1)
add → O(1)
resize → O(n)
add → O(1)
add → O(1)
...
```

Resizing happens only occasionally.

Therefore:

``` text
ArrayList.add()
→ O(1) amortized
```

### Important distinction

``` text
One expensive resize
        ↓
O(n)

Long sequence of additions
        ↓
O(1) amortized per add
```

------------------------------------------------------------------------

# 12. Worst Case vs Amortized

### Worst Case

Maximum cost of one operation.

Example:

``` text
ArrayList resize → O(n)
```

### Amortized

Cost spread across many operations.

``` text
ArrayList add → O(1) amortized
```

They answer different questions.

------------------------------------------------------------------------

# 🧠 DAY 3 MASTER MINDSET

When analyzing space:

``` text
1. What memory is already the input?
2. What extra memory am I creating?
3. Does that memory grow with n?
4. Am I using recursion?
5. What is the maximum recursion depth?
```

When analyzing recursion:

``` text
Number of calls → TIME

Maximum active calls → SPACE
```

When analyzing repeated operations:

``` text
One expensive operation
        ↓
Look at the whole sequence
        ↓
Amortized analysis
```

------------------------------------------------------------------------

# 📌 Complexity Cheat Sheet

  Situation                             Complexity
  ------------------------------ -----------------
  Few variables                         O(1) space
  Extra array of size n                 O(n) space
  HashMap with n items                  O(n) space
  2D array n × n                       O(n²) space
  Recursion depth n                     O(n) space
  Recursion depth log n             O(log n) space
  One recursive call: n → n-1            O(n) time
  Two recursive calls: n → n-1        O(2\^n) time
  ArrayList append                  O(1) amortized
  ArrayList resize                 O(n) worst case

------------------------------------------------------------------------

# 🎯 DAY 3 MASTERY CHECK

Before moving to Day 4, you should be able to answer:

1.  What is space complexity?
2.  What is auxiliary space?
3.  Why can an algorithm have O(n) time but O(1) space?
4.  Why does recursion use stack memory?
5.  Why is `fun(n-1)` recursion O(n) space?
6.  Why can two recursive calls give O(2\^n) time but O(n) space?
7.  What is a recurrence?
8.  What does `T(n) = 2T(n-1) + O(1)` mean?
9.  What is amortized analysis?
10. Why is `ArrayList.add()` O(1) amortized even though resize is O(n)?

------------------------------------------------------------------------

# 🔥 DAY 3 KEY TAKEAWAYS

``` text
Space Complexity
→ How much extra memory?

Auxiliary Space
→ Extra memory used by the algorithm

Recursion
→ Uses call stack

Recursive Time
→ Count total work/calls

Recursive Space
→ Count maximum active depth

Recurrence
→ Describes recursive work

Amortized Analysis
→ Cost across a sequence of operations

ArrayList.add()
→ O(1) amortized
```

> **Don't memorize complexity. Learn where it comes from.**

**Day 3 = Space + Recursion Cost + Amortized Thinking**
