# Minimum Days to Make Bouquets — Binary Search on Answer

## 1. Problem in simple words

You are given an array `bloomDay`, where `bloomDay[i]` represents the day the `i-th` flower blooms.

You need to make `m` bouquets, and each bouquet requires `k` **adjacent bloomed flowers**.

Find the minimum number of days needed to make all `m` bouquets.

**Example:**

```text
bloomDay = [1, 10, 3, 10, 2]
m = 3
k = 1

Output: 3
```

By day 3, flowers at indices `0`, `2`, and `4` have bloomed. Since each bouquet needs one flower, we can make three bouquets.

**Important:** Flowers used in one bouquet cannot be reused in another.

## 2. Input and Output

**Input:**
- `bloomDay[]`: Blooming day of each flower.
- `m`: Required number of bouquets.
- `k`: Adjacent flowers required per bouquet.

**Output:** Minimum days required, or `-1` if making the bouquets is impossible.

Example:

```text
bloomDay = [1, 10, 3, 10, 2]
m = 3
k = 2

Output: -1
```

There are only five flowers, but six are required (`m * k = 6`). Therefore, making three bouquets is impossible.

## 3. Brute Force Approach

Try every day from the earliest blooming day to the latest blooming day.

For each day:
1. Count consecutive bloomed flowers.
2. Every time the count reaches `k`, make a bouquet and reset the count.
3. If at least `m` bouquets can be made, return that day.

**Time Complexity:** `O(n × D)`, where `D` is the range of candidate days.

**Space Complexity:** `O(1)`.

## 4. Find the Bottleneck

Checking every possible day is inefficient.

However, bouquet feasibility is monotonic:

- If we can make the bouquets by day `d`, we can also make them on any later day.
- If we cannot make them by day `d`, we cannot make them on an earlier day.

Therefore, we can search for the **first feasible day** instead of checking every day.

## 5. Identify the Pattern

**Pattern: Binary Search on Answer**

Search space:

```text
left  = minimum bloomDay
right = maximum bloomDay
```

For each candidate day `mid`, check whether at least `m` bouquets can be made using adjacent bloomed flowers.

- If feasible, save `mid` and search earlier days.
- If not feasible, search later days.

Before searching, if `(long) m * k > bloomDay.length`, return `-1`.

## 6. Build Intuition

Imagine waiting for flowers to bloom.

At first, there may not be enough adjacent bloomed flowers. As days pass, more flowers bloom, and making bouquets becomes possible.

We do not need to wait day by day. We can jump to the middle day and check feasibility.

For example:

```text
Days:       1  2  3  4  5  6  7
Feasible:   N  N  N  Y  Y  Y  Y
```

The answer is the first `Y`.

This is the same first-valid-answer pattern used in Koko Eating Bananas.

## 7. Optimized Approach

### Algorithm

1. Check whether `m * k > n`. If so, return `-1`.
2. Set `left` to the minimum blooming day.
3. Set `right` to the maximum blooming day.
4. Binary-search the day range.
5. For each `mid`, count consecutive bloomed flowers (`bloomDay[i] <= mid`).
6. Whenever the count reaches `k`, increment the bouquet count and reset the flower count to zero.
7. If at least `m` bouquets can be made, search earlier days; otherwise, search later days.
8. Return the minimum feasible day.

### Feasibility condition

```text
Can we make at least m bouquets by day mid?
```

A flower is available if:

```text
bloomDay[i] <= mid
```

Reset the consecutive-flower count after making each bouquet to prevent reusing flowers.

**Time Complexity:** `O(n log D)`, where `D = max(bloomDay) - min(bloomDay) + 1`.

**Space Complexity:** `O(1)`.

## 8. Common Mistakes

- Forgetting that flowers in a bouquet must be adjacent.
- Reusing flowers across multiple bouquets.
- Not resetting the consecutive-flower count after making a bouquet.
- Searching days from `1` to an unnecessarily large fixed value instead of using the minimum and maximum bloom days.
- Forgetting the impossible case: `(long) m * k > n`.
- Searching right when a candidate day is feasible. The goal is the **minimum** feasible day, so search left.

## 9. Interview Explanation

"I use binary search on the number of days because feasibility is monotonic: once enough bouquets can be made, they remain possible on every later day.

For a candidate day, I scan the array and count consecutive flowers that have bloomed. Whenever I collect `k` adjacent flowers, I form a bouquet and reset the count.

If I can make at least `m` bouquets, I record the day and search earlier. Otherwise, I search later.

This gives `O(n log D)` time and `O(1)` extra space, where `D` is the range of blooming days."
