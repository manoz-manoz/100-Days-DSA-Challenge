# Problem1
# 1. Problem explanation in simple words
You are given a sorted integer array that may contain duplicate elements. Your task is to find the index of the first occurrence of the given target.
- If the target exists multiple times, return its smallest index.
- If the target does not exist, return -1.
- Array indexing starts from 0.

#2. Input and output

Component	Description
Input	    Sorted integer array arr and integer target
Output	    Index of the first occurrence, or -1
Condition	Array is sorted in ascending order

Input:
arr = [1, 2, 2, 2, 2, 5, 7]
target = 2

Output:
1

#3. Brute-force algorithm and pseudocode
Approach
The simplest approach is to check every element from left to right.
1. Start at index 0.
2. Compare each element with the target.
3. If they match, immediately return the current index.
4. If the loop finishes without finding the target, return -1.

#Pseudocode
FUNCTION firstOccurrence(arr, target):

    FOR i = 0 TO arr.length - 1:

        IF arr[i] == target:
            RETURN i

    RETURN -1

# 4. Optimized algorithm and pseudocode
Approach: Binary Search
Because the array is sorted, we can use Binary Search to reduce the search time.
However, normal Binary Search may return any matching index. We need the first occurrence.
Therefore, when arr[mid] is greater than or equal to the target, we continue searching the left half.

#FUNCTION firstOccurrence(arr, target):

    left = 0
    right = arr.length - 1

    WHILE left <= right:

        mid = left + (right - left) / 2

        IF arr[mid] < target:
            left = mid + 1
        ELSE:
            right = mid - 1

    IF left < arr.length AND arr[left] == target:
        RETURN left

    RETURN -1


# 5. Complexity analysis
Let n be the number of elements in the array.
Complexity	    Brute force	    OptimizedBinary Search
Best-case time	     O(1)	        O(log n)    
Average-case time	 O(n)	        O(log n)
Worst-case time	     O(n)	        O(log n)
Auxiliary space	     O(1)	        O(1)

# 6. Interview explanation

First, I would solve this problem using a linear scan, checking each element from left to right and returning the first index that matches the target. This takes O(n) time and O(1) auxiliary space.

To optimize it, I use Binary Search because the array is sorted.

I initialize two pointers, `left` and `right`, and calculate the middle index in each iteration. If the middle element is smaller than the target, I move `left` to `mid + 1`, because the target's first occurrence cannot be in the eliminated portion.

Otherwise, I move `right` to `mid - 1`. This includes the case where the middle element equals the target. I continue searching left because an earlier occurrence may exist.

After the loop terminates, `left` represents the lower-bound position, which is the first index where the element is greater than or equal to the target. I verify that this index is within the array and contains the target. If so, I return it; otherwise, I return -1.

The optimized solution takes O(log n) time and O(1) auxiliary space.