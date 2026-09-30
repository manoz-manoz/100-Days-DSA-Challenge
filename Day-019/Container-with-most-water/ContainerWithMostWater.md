# Container With Most Water — LeetCode 11

## 1. Problem

Given an array where each element represents the height of a vertical line.

Choose **two lines** that can hold the maximum amount of water.

### Example

```text
Input:
[1,8,6,2,5,4,8,3,7]

Output:
49
```

---

## 2. How Is Area Calculated?

The area of water is:

```text
Area = width × height
```

Where:

```text
width = right - left

height = min(height[left], height[right])
```

Therefore:

```text
Area = (right - left) × min(height[left], height[right])
```

The **shorter line always limits the water**.

---

## 3. Brute Force

Try every possible pair of lines.

```text
for every i
    for every j
        calculate area
```

### Complexity

```text
Time  : O(n²)
Space : O(1)
```

The problem is that we check too many pairs.

---

## 4. Two Pointer Approach

Use two pointers:

```text
left  = 0
right = n - 1
```

Start from both ends:

```text
L →                 ← R
[1, 8, 6, 2, 5, 4, 8, 3, 7]
```

This gives us the **maximum possible width** initially.

---

## 5. Algorithm

1. Set `left = 0`
2. Set `right = n - 1`
3. Calculate the current area.
4. Update `maxArea`.
5. Move the pointer with the **smaller height**.
6. Repeat until `left >= right`.

### Logic

```text
if height[left] < height[right]

    left++

else

    right--
```

---

## 6. Why Move the Smaller Side?

Suppose:

```text
left height  = 4
right height = 9
```

The container is limited by `4`.

```text
Area = width × 4
```

If we move the `9` side:

```text
width decreases
```

but the limiting height is still potentially `4`.

So that move cannot improve the current container using this shorter boundary.

Therefore:

```text
Shorter side → move it
```

We are looking for a taller boundary while the width decreases.

---

## 7. Dry Run

```text
height = [1,8,6,2,5,4,8,3,7]
```

Initially:

```text
left = 0
right = 8
```

Heights:

```text
1 and 7
```

Area:

```text
width = 8
height = min(1,7) = 1

area = 8
```

Left is smaller:

```text
left++
```

Now:

```text
left = 1
right = 8
```

Heights:

```text
8 and 7
```

Area:

```text
width = 7
height = 7

area = 49
```

```text
maxArea = 49
```

Continue until the pointers meet.

Final answer:

```text
49
```

---

## 8. Complexity

Each pointer moves only toward the other pointer.

```text
Time  : O(n)
Space : O(1)
```

---

## 9. Interview Explanation

> The problem asks us to choose two lines that form a container with maximum water.
>
> The area is the distance between the two lines multiplied by the shorter height.
>
> A brute-force solution checks every pair and takes O(n²) time.
>
> To optimize it, I use two pointers starting from both ends. This gives the maximum possible width initially.
>
> After calculating the area, I move the pointer with the smaller height because the shorter line limits the amount of water.
>
> Moving the taller line would only reduce the width while the shorter line remains the limiting factor.
>
> Therefore, I always move the shorter side.
>
> The two pointers move toward each other only once, giving O(n) time and O(1) extra space.

---

## 10. Pattern Recognition

When you see:

```text
Choose two positions
+
Distance between positions matters
+
One side limits the result
+
Find maximum
```

Think:

```text
L →             ← R
```

Then:

```text
Calculate area
      ↓
Find shorter side
      ↓
Move shorter pointer
```

---

## 11. Key Takeaway

```text
Area = width × shorter height

Maximum width
     ↓
Start from both ends
     ↓
Calculate area
     ↓
Move shorter side
     ↓
Repeat
```

### One-Line Rule

**Container With Most Water → Start at both ends and always move the shorter height.**
