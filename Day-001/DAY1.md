# Day 1 — Time Complexity & Constraint Reading

📚 What I Learned

Today I learned the basics of Time Complexity and how to read
constraints before choosing an algorithm.

# 1. What is n?

n represents the size of the input.

Example:

arr = \[10, 20, 30, 40, 50\]

n = 5

# 2. Why do constraints matter?

Constraints tell us how large the input can be.

Example:

n ≤ 10\^5

This means n can be as large as 100,000.

Large input → need a more efficient algorithm.

# TIME COMPLEXITY
 O(1)       → Constant
 O(log n)   → Logarithmic
 O(n)       → Linear
 O(n log n) → Linearithmic
 O(n²)      → Quadratic
 O(n³)      → Cubic
 O(2ⁿ)      → Exponential
 O(n!)      → Factorial                     

# CONSTRAINT → COMPLEXITY
 n ≤ 10        → O(n!), O(2ⁿ)
 n ≤ 20        → O(2ⁿ)
 n ≤ 10²       → O(n³)
 n ≤ 10³       → O(n²)
 n ≤ 10⁴       → O(n log n)
 n ≤ 10⁵       → O(n), O(n log n)
 n ≤ 10⁶       → O(n)
 n ≥ 10⁷       → O(n) or better 

# 3. Operation Counting

We estimate how many times the main work is performed.

Example:

for (int i = 0; i \< n; i++) {

// work

}

The loop runs n times.

Time Complexity:

O(n)

# 4. Sequential Loops

Two separate O(n) loops:

O(n) + O(n)

= O(2n)

= O(n)

So, sequential loops are added.

# 5. Nested Loops

Two nested O(n) loops:

O(n) × O(n)

= O(n²)

So, nested loops are multiplied.

# 6. Correctness vs Efficiency

Correctness:

Does the algorithm give the correct answer?

Efficiency:

Does the algorithm solve the problem fast enough?

A solution should be:

Correct + Efficient

# 7. Why O(n²) can be slow

As n increases, n² grows very quickly.

Example:

n = 1,000

n² = 1,000,000

n = 100,000

n² = 10,000,000,000

So O(n²) can become too slow for large inputs.

8\. Growth Rate

Growth rate tells us how quickly the work increases when n increases.

Common complexities:

O(1)

O(log n)

O(n)

O(n log n)

O(n²)

O(2ⁿ)

O(n!)

🧠 Key Rules I Learned

Sequential → ADD

Nested → MULTIPLY

Constants → IGNORE

Keep the dominant term

Examples:

O(n) + O(n) = O(n)

O(n) × O(n) = O(n²)

O(10n) = O(n)

O(n + n²) = O(n²)

🎯 Today\'s Takeaway

Before writing code:

Read the constraints

↓

Understand input size

↓

Think about required efficiency

↓

Choose the algorithm

↓

Check correctness
