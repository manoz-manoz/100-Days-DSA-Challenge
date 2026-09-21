# DAY 10 — Hashing: Collisions & Collision Handling

# Date: 21-09-2026, Monday

🎯 Goal

Today we go one level deeper into hashing:

Collisions
Chaining
Load factor
Open addressing
Linear probing
Quadratic probing
Double hashing
Clustering
Resizing / rehashing
Java HashMap connection

No problems today. Focus on understanding the internal models.

# 1. What is a Collision?

A collision happens when two different keys produce the same bucket/index.

Example:

Table size = 7

10 % 7 = 3
17 % 7 = 3

Both want bucket 3:

10 ──┐
     ├──→ bucket 3
17 ──┘

That's a collision.

Important

A collision is not an error.

Collisions are normal in hash tables because there can be many more possible keys than available buckets.

# 2. Why Are Collisions Unavoidable?

Suppose:

Possible keys = millions
Table slots   = 10

Multiple keys must eventually map to the same slot.

This is related to the pigeonhole principle.

So a good hash table doesn't try to completely eliminate collisions.

Instead:

It handles collisions efficiently.

# 3. Collision Handling

There are two major approaches:

             Collision
                 |
        +--------+--------+
        |                 |
    Chaining       Open Addressing
        |                 |
    Store multiple      Find another
    entries in bucket   empty position
# 4. Separate Chaining

In separate chaining, multiple entries can belong to the same bucket.

Example:

Bucket 3

10 → 17 → 24

The complete table might look like:

0 → empty
1 → empty
2 → empty
3 → 10 → 17 → 24
4 → empty
5 → empty
6 → empty

The bucket points to a chain of entries.

Mental model
table[index]
     |
     ↓
   Node
     |
     ↓
   Node
     |
     ↓
   Node
# 5. Chaining Search

Suppose we want:

17

First:

hash(17)
   ↓
bucket 3

Then search only that bucket's chain:

10 ❌
17 ✅

We don't need to scan the entire table.

# 6. Advantages of Chaining
Simple collision handling
Multiple entries can share a bucket
Deletion is relatively straightforward
Load factor can be greater than 1
Can handle many collisions
# 7. Disadvantages of Chaining

If many keys go into the same bucket:

bucket
   ↓
A → B → C → D → E → F

The chain becomes long.

Then searching takes more time.

Worst case:

O(n)

Therefore, good key distribution is important.

# 8. What is Load Factor?

Load factor tells us how full the hash table is.

Formula:

Load Factor = Number of Entries / Number of Buckets

Usually:

α = n / m

Where:

n = number of entries
m = number of buckets
# 9. Load Factor Example

Suppose:

Entries = 6
Buckets = 10

Then:

α = 6 / 10
  = 0.6

So:

Load Factor = 0.6

Another example:

Entries = 8
Buckets = 10

α = 0.8

The table is becoming more crowded.

# 10. Why Does Load Factor Matter?

Think:

More entries
     ↓
More crowded table
     ↓
Higher collision probability
     ↓
More work during lookup

Therefore, hash tables monitor their load factor.

When the table becomes too crowded, it can be resized.

# 11. Capacity vs Load Factor

Don't confuse these two.

Capacity

Number of available buckets/slots.

Capacity = 16
Load Factor

How full the table is allowed to become before resizing.

Load Factor = 0.75
Threshold

The point at which resizing is triggered.

Conceptually:

Threshold = Capacity × Load Factor

Example:

Capacity = 16
Load Factor = 0.75

Threshold ≈ 16 × 0.75
           = 12
# 12. Resizing / Rehashing

When the table becomes too crowded:

Old table
    ↓
Resize
    ↓
Larger table
    ↓
Redistribute entries

Example:

8 buckets
   ↓
16 buckets

Because the table size changes, entries may need to be placed into different buckets.

This redistribution is commonly referred to as rehashing or redistribution in hash-table discussions.

# 13. Open Addressing

The second major collision-handling technique is:

Open addressing

Here, entries are stored inside the hash table itself.

If the calculated position is occupied:

Find another empty position.

Example:

0   1   2   3   4   5   6
            10

Now:

17 % 7 = 3

But:

3 → occupied

So we search for another position.

# 14. Probing

The process of searching for another position is called:

Probing

General model:

Hash
 ↓
Initial position
 ↓
Occupied?
 ↓
Yes
 ↓
Probe another position
 ↓
Empty?
 ↓
Store

There are three major probing techniques.

Open Addressing
      |
      +-- Linear Probing
      |
      +-- Quadratic Probing
      |
      +-- Double Hashing
# 15. Linear Probing

Linear probing checks positions one after another.

Formula:

index = (h(key) + i) % tableSize

where:

i = 0, 1, 2, 3...

Example:

Original = 3

3 → 4 → 5 → 6 → 0 → 1 ...
# 16. Linear Probing Example

Suppose:

Table size = 7

Insert:

10
10 % 7 = 3

Now insert:

17
17 % 7 = 3

Collision.

Try:

4

If 4 is empty:

0  1  2  3  4  5  6
         10 17
# 17. Primary Clustering

Linear probing can create groups of consecutive occupied slots.

Example:

[10][17][24][31][38]

A large continuous cluster develops.

This is called:

Primary clustering

As the cluster grows:

Larger cluster
     ↓
More probing
     ↓
Slower operations
# 18. Quadratic Probing

Instead of checking:

+1
+2
+3
+4

quadratic probing uses increasing jumps.

Common formula:

index = (h(key) + i²) % tableSize

The offsets are:

0
1
4
9
16
...

So the probing sequence spreads out more than linear probing.

# 19. Quadratic Probing Example

Suppose:

h(key) = 3

Try:

i = 0

3 + 0² = 3

Then:

i = 1

3 + 1² = 4

Then:

i = 2

3 + 2² = 7

Then apply modulo table size.

