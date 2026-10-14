# Problem 2: Last Occurrence of a Target in a Sorted Array
# 1. Problem explanation in simple words
You are given a sorted integer array that may contain duplicate elements. Your task is to find the index of the last occurrence of the given target.
- If the target exists multiple times, return its largest index.
- If the target does not exist, return -1.
- Array indexing starts from 0.

# Example:
Array  = [1, 2, 2, 2, 2, 5, 7]
Target = 2

Output = 4

# 2. Input and output
Component	Description
Input	    Sorted integer array arr and integer target
Output	    Index of the last occurrence, or -1
Condition	Array is sorted in ascending order

# 3. Brute-force algorithm and pseudocode
Approach
The simplest approach is to check every element from right to left.
1. Start at the last index, arr.length - 1.
2. Compare each element with the target.
3. If they match, immediately return the current index.
4. If the loop finishes without finding the target, return -1.

# Pseudocode
FUNCTION lastOccurrenceBruteForce(arr, target):

    FOR i = arr.length - 1 DOWNTO 0:

        IF arr[i] == target:
            RETURN i

    RETURN -1

# 4. Optimized algorithm and pseudocode
Approach: Binary Search
Because the array is sorted, we can use Binary Search to reduce the search time.
Normal Binary Search may return any matching index. We need the last occurrence.
Therefore, when arr[mid] is less than or equal to the target, we continue searching the right half.

# Pseudocode
FUNCTION lastOccurrence(arr, target):

    left = 0
    right = arr.length - 1
    answer = -1

    WHILE left <= right:

        mid = left + floor((right - left) / 2)

        IF arr[mid] <= target:
            IF arr[mid] == target:
                answer = mid

            left = mid + 1

        ELSE:
            right = mid - 1

    RETURN answer

#6. Interview explanation: Brute force to optimized
First, I would solve the problem using brute force by traversing the sorted array from right to left. Whenever an element matches the target, I return its index immediately. Since I start from the right, the first match is the last occurrence. This takes O(n) worst-case time and O(1) auxiliary space.
To optimize the solution, I use Binary Search because the array is sorted.
I initialize left = 0, right = arr.length - 1, and answer = -1.
At each iteration, I calculate the middle index. If arr[mid] is smaller than or equal to the target, I move left to mid + 1. If the middle element equals the target, I first store its index in answer, because it may be the last occurrence found so far.
If arr[mid] is greater than the target, I move right to mid - 1.
I continue until the search space becomes empty. The answer variable contains the last occurrence's index, or -1 if the target was not found.
The optimized solution takes O(log n) time and O(1) auxiliary space.
