# Find the Element That Appears Once in a Sorted Array

## 1. Problem in simple words

You are given a **sorted array** in which every element appears exactly twice, except for one element that appears only once.

Your task is to find the element that appears only once.

**Example:**

```text
Input:
[1, 1, 2, 2, 3, 4, 4, 5, 5]

Output:
3
```

Here, every element appears twice except `3`, which appears only once.

**Important assumptions:**
- The array is sorted in ascending order.
- Every element appears exactly twice, except one element.
- The single element is guaranteed to exist.
- The array contains an odd number of elements.

## 2. Input and Output

**Input:**
- An integer `n`, representing the array length.
- A sorted integer array of size `n`.

**Output:**
- The element that appears exactly once.

**Example 1:**

```text
Input:
9
1 1 2 2 3 4 4 5 5

Output:
3
```

**Example 2:**

```text
Input:
7
1 1 2 3 3 4 4

Output:
2
```

**Example 3:**

```text
Input:
5
1 1 2 2 3

Output:
3
```

## 3. Brute Force Approach

### Intuition

We can examine each element and check whether it matches either of its neighbors.

Because every repeated element appears twice and the array is sorted, duplicate elements normally appear next to each other.

The single element will not match either neighbor.

### Algorithm

1. Traverse the array from left to right.
2. For each index `i`, check whether the current element matches the previous element or the next element.
3. If neither neighbor matches, return the current element.
4. Handle the first and last elements separately to avoid going outside the array.

### Java code

```java
public static int singleNonDuplicateBruteForce(int[] arr) {
    int n = arr.length;

    if (n == 1) {
        return arr[0];
    }

    for (int i = 0; i < n; i++) {
        boolean sameAsPrevious =
                i > 0 && arr[i] == arr[i - 1];

        boolean sameAsNext =
                i < n - 1 && arr[i] == arr[i + 1];

        if (!sameAsPrevious && !sameAsNext) {
            return arr[i];
        }
    }

    return -1;
}
```

### Complexity

- **Time complexity:** `O(n)` — in the worst case, we inspect every element.
- **Auxiliary space:** `O(1)` — we use only a few variables.

## 4. Find the Bottleneck

The brute-force approach checks elements one by one.

However, the array is sorted, and each duplicate pair occupies two adjacent positions. This creates a predictable index pattern.

Before the single element, pairs begin at even indices:

```text
Index:   0  1  2  3  4  5  6
Array:  [1, 1, 2, 2, 3, 4, 4]
         └─┘  └─┘  ↑  └─┘
```

Before the single element, pairs begin at indices `0, 2, 4, ...`.

After the single element, the pair alignment shifts:

```text
Index:   0  1  2  3  4  5  6
Array:  [1, 1, 2, 3, 3, 4, 4]
         └─┘  ↑  └─┘  └─┘
```

The single element changes the alignment of all the pairs that follow it.

**The bottleneck:** We are scanning the entire array even though the pair alignment lets us determine which half contains the single element.

We can use binary search to reduce the time complexity from `O(n)` to `O(log n)`.

## 5. Identify the Pattern

**Pattern: Binary Search on Pair Alignment**

Instead of searching for a particular target value, we search for the position where the normal pairing pattern breaks.

At every iteration:

1. Calculate `mid`.
2. If `mid` is odd, move it one position left to make it even.
3. Compare `arr[mid]` with `arr[mid + 1]`.
4. Use the comparison to eliminate half of the search space.

Why make `mid` even?

In the normal pairing pattern, the first element of each pair is at an even index, and the second is at the next odd index.

For example:

```text
Index:   0  1  2  3  4  5  6
Array:  [1, 1, 2, 2, 3, 4, 4]
         └─┘  └─┘  ↑  └─┘
```

The pairs are:

- `arr[0]` and `arr[1]`
- `arr[2]` and `arr[3]`
- `arr[5]` and `arr[6]` — after the single element, the alignment has shifted.

Before the single element, the pair starts at an even index. After it, the pairing pattern is shifted.

## 6. Build Intuition

Consider:

```text
arr = [1, 1, 2, 2, 3, 4, 4, 5, 5]
```

The answer is `3`.

We use two pointers:

```java
int left = 0;
int right = arr.length - 1;
```

Continue while `left < right`.

### Case 1: `arr[mid] == arr[mid + 1]`

If `mid` is even and the current element matches the next element, the pair is correctly aligned.

That means the single element must be to the right.

```java
left = mid + 2;
```

We skip both elements of the confirmed pair.

### Case 2: `arr[mid] != arr[mid + 1]`

If `mid` is even and the current element does not match the next element, the single element is at `mid` or somewhere to the left.

```java
right = mid;
```

We keep `mid` because it could itself be the single element.

### Why adjust `mid` to an even index?

If `mid` is odd, move it one position left:

```java
if (mid % 2 == 1) {
    mid--;
}
```

Now `mid` is even, so comparing `arr[mid]` and `arr[mid + 1]` consistently checks the expected first and second positions of a pair.

### Dry run

Array:

```text
[1, 1, 2, 2, 3, 4, 4, 5, 5]
```

| Step | left | right | mid before adjustment | mid after adjustment | Comparison | Action |
|---|---:|---:|---:|---:|---|---|
| 1 | 0 | 8 | 4 | 4 | `arr[4] != arr[5]` | `right = 4` |
| 2 | 0 | 4 | 2 | 2 | `arr[2] == arr[3]` | `left = 4` |
| End | 4 | 4 | — | — | — | Return `arr[4] = 3` |

The loop stops when `left == right`. That index contains the single element.

## 7. Optimized Approach

### Algorithm

1. Initialize `left = 0` and `right = arr.length - 1`.
2. While `left < right`:
   - Calculate `mid`.
   - If `mid` is odd, decrement it by one to make it even.
   - If `arr[mid] == arr[mid + 1]`, the pair is correctly aligned, so set `left = mid + 2`.
   - Otherwise, set `right = mid`.
3. Return `arr[left]`.

### Java code

```java
import java.util.Scanner;

public class Main {

    public static int singleNonDuplicate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Make mid even so it points to
            // the expected first element of a pair.
            if (mid % 2 == 1) {
                mid--;
            }

            if (arr[mid] == arr[mid + 1]) {
                // The pair is correctly aligned.
                // The single element is to the right.
                left = mid + 2;
            } else {
                // The single element is at mid
                // or somewhere to the left.
                right = mid;
            }
        }

        return arr[left];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if (n <= 0 || n % 2 == 0) {
            System.out.println(
                "Array length must be a positive odd number"
            );
            sc.close();
            return;
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(singleNonDuplicate(arr));

        sc.close();
    }
}
```

### Complexity analysis

- **Time complexity:** `O(log n)` — each iteration eliminates approximately half of the remaining search space.
- **Auxiliary space:** `O(1)` — the algorithm uses a constant number of variables.

## 8. Common Mistakes

1. **Forgetting to make `mid` even.**  
   If `mid` is odd, the comparison with `mid + 1` may not check the intended pair alignment.

2. **Using `left = mid + 1` when a pair matches.**  
   When `arr[mid] == arr[mid + 1]`, both elements belong to a confirmed pair. Use `left = mid + 2`.

3. **Using `right = mid - 1` when the pair does not match.**  
   The single element could be at `mid`, so use `right = mid`.

4. **Using `while (left <= right)`.**  
   This approach narrows the range until one candidate remains. Use `while (left < right)`.

5. **Accessing `arr[mid + 1]` without considering the loop condition.**  
   With `left < right` and the adjustment to an even `mid`, `mid + 1` remains within the current array bounds.

6. **Assuming the array can contain multiple single elements.**  
   This algorithm assumes exactly one element appears once and every other element appears twice.

7. **Forgetting the sorted-array requirement.**  
   The algorithm depends on duplicate elements being adjacent.

## 9. Interview Explanation

“I will use binary search because the array is sorted and every element except one appears exactly twice.

The key observation is that pairs normally begin at even indices before the single element. Once the single element appears, the pair alignment shifts.

I initialize `left` and `right` to the array boundaries. In every iteration, I calculate `mid` and adjust it to an even index if necessary.

If `arr[mid]` equals `arr[mid + 1]`, the pair is correctly aligned, so the single element must be to the right. I move `left` to `mid + 2`.

Otherwise, the single element is at `mid` or to the left, so I move `right` to `mid`.

When `left` and `right` meet, I return `arr[left]`.

The time complexity is `O(log n)`, and the auxiliary space complexity is `O(1)`.”

### Reusable pattern

```java
int left = 0;
int right = arr.length - 1;

while (left < right) {
    int mid = left + (right - left) / 2;

    if (mid % 2 == 1) {
        mid--;
    }

    if (arr[mid] == arr[mid + 1]) {
        left = mid + 2;
    } else {
        right = mid;
    }
}

return arr[left];
```

**Remember:** Make `mid` even, compare the pair at `mid` and `mid + 1`, and use the pair alignment to decide which half to keep.
