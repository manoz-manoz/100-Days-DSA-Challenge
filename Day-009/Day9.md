# DAY 9 — HASHING & HASHMAP

**Date:** 20-09-2026, Sunday

**Focus:** Hashing fundamentals, HashMap, Java HashMap, hierarchy, methods, and applications

**Today:** We will understand how HashMap provides fast key-based lookup.

> **Collision handling is NOT covered today.**
> It will be studied separately after the basic HashMap model is clear.

---

# 🎯 DAY 9 GOAL

By the end of today, you should be able to explain:

- What hashing is
- Why hashing is needed
- What a HashMap is
- What Java `HashMap` is
- Key and value
- HashMap hierarchy
- Important HashMap methods
- When to use HashMap
- Common DSA applications of HashMap
- Why HashMap lookup is usually fast

---

# 1. WHY DO WE NEED HASHING?

Suppose we have:

```text
[10, 25, 7, 40, 15]
```

We want to find:

```text
40
```

With an array, we may need to scan:

```text
10 ❌
25 ❌
7  ❌
40 ✅
```

This is:

```text
O(n)
```

If we have a very large collection, repeated scanning can become expensive.

We want a way to find information faster.

That is where **hashing** becomes useful.

---

# 2. WHAT IS HASHING?

Hashing is a technique used to convert a key into information that helps determine where the key should be stored or searched.

Basic model:

```text
Key
 ↓
Hashing
 ↓
Location / Bucket
 ↓
Lookup
```

The goal is:

> **Make lookup fast without scanning all stored elements.**

---

# 3. WHAT IS A HASH FUNCTION?

A hash function takes a key and produces a hash value.

Conceptually:

```text
hash(key)
```

Example:

```text
key = 42

42
 ↓
hash function
 ↓
hash value
```

The hash value is used by the hash table to determine where the entry belongs.

For today, remember:

```text
KEY
 ↓
HASH FUNCTION
 ↓
HASH VALUE
 ↓
LOOKUP LOCATION
```

**Collision handling is not part of today's study.**

---

# 4. WHAT IS A HASHMAP?

A `HashMap` is a data structure that stores data as:

```text
KEY → VALUE
```

Example:

```text
101 → "Manoj"
102 → "Rahul"
103 → "Priya"
```

Here:

```text
101 = key
"Manoj" = value
```

Think of it like a dictionary:

```text
Word       → Meaning
Student ID → Student
Product ID → Product
Username   → User
```

---

# 5. HASHMAP vs ARRAY

### Array

```text
Index → Value

0 → 10
1 → 20
2 → 30
3 → 40
```

### HashMap

```text
Key → Value

101 → Manoj
205 → Rahul
309 → Priya
```

The key identifies the value.

The key does not have to be an integer.

Example:

```java
HashMap<String, Integer> map = new HashMap<>();
```

```text
"Manoj" → 95
"Rahul" → 88
"Priya" → 91
```

---

# 6. JAVA HASHMAP

Java provides:

```java
HashMap<K, V>
```

where:

```text
K = Key
V = Value
```

Example:

```java
HashMap<Integer, String> map = new HashMap<>();
```

This means:

```text
Key   → Integer
Value → String
```

Example:

```java
map.put(101, "Manoj");
map.put(102, "Rahul");
map.put(103, "Priya");
```

---

# 7. IMPORT HASHMAP

```java
import java.util.HashMap;
```

Or:

```java
import java.util.*;
```

Then:

```java
HashMap<Integer, String> map = new HashMap<>();
```

---

# 8. HASHMAP HIERARCHY

Important:

> **Map is NOT a child of Collection.**

`Map` is a separate interface hierarchy.

Simplified:

```text
              Map<K,V>
                 ↑
              HashMap
```

HashMap's class inheritance:

```text
Object
  ↓
AbstractMap<K,V>
  ↓
HashMap<K,V>
```

HashMap also implements:

```text
Map
Cloneable
Serializable
```

Useful overview:

```text
Collection
   ├── List
   ├── Set
   └── Queue

Map
   ├── HashMap
   ├── LinkedHashMap
   └── TreeMap
```

Remember:

> `Map` and `Collection` are separate concepts.

---

# 9. KEY AND VALUE

Every HashMap entry looks like:

```text
KEY → VALUE
```

Example:

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(101, "Java");
```

Here:

```text
Key   = 101
Value = Java
```

Another example:

```java
HashMap<String, Integer> marks = new HashMap<>();

marks.put("Java", 90);
marks.put("DSA", 85);
```

```text
Java → 90
DSA  → 85
```

---

# 10. HASHMAP KEYS ARE UNIQUE

Example:

```java
map.put(101, "Manoj");
map.put(101, "Rahul");
```

The second `put()` does not create another key `101`.

Instead:

```text
Before:

101 → Manoj

After:

101 → Rahul
```

So:

> **A key identifies one mapping at a time.**

Values can be duplicated.

```text
101 → Java
102 → Java
103 → DSA
```

---

# 11. CREATING A HASHMAP

```java
HashMap<Integer, String> map = new HashMap<>();
```

Empty:

```text
{}
```

Add entries:

```java
map.put(101, "Manoj");
map.put(102, "Rahul");
```

---

# 12. IMPORTANT HASHMAP METHODS

## 12.1 `put()`

Adds or updates a key-value pair.

```java
map.put(101, "Manoj");
```

If the key does not exist, it is added.

If the key already exists, its value is replaced.

---

## 12.2 `get()`

Gets the value associated with a key.

```java
String name = map.get(101);
```

Conceptually:

```text
101 → Manoj

get(101)
   ↓
Manoj
```

If the key is absent, `get()` returns `null` for a map whose value type permits `null`.

---

## 12.3 `getOrDefault()`

Returns the value if the key exists.

Otherwise returns the supplied default.

```java
int count = map.getOrDefault("Java", 0);
```

Very useful for frequency counting.

---

## 12.4 `containsKey()`

Checks whether a key exists.

```java
map.containsKey(101);
```

Result:

```text
true / false
```

---

## 12.5 `containsValue()`

Checks whether a value exists.

```java
map.containsValue("Manoj");
```

Result:

```text
true / false
```

---

## 12.6 `remove()`

Removes a mapping using its key.

```java
map.remove(101);
```

---

## 12.7 `size()`

Returns the number of key-value mappings.

```java
map.size();
```

---

## 12.8 `isEmpty()`

Checks whether the map contains no entries.

```java
map.isEmpty();
```

---

## 12.9 `clear()`

Removes all mappings.

```java
map.clear();
```

---

# 13. USEFUL VIEW METHODS

## `keySet()`

Returns the keys.

```java
map.keySet();
```

Conceptually:

```text
[101, 102, 103]
```

---

## `values()`

Returns the values.

```java
map.values();
```

Conceptually:

```text
[Manoj, Rahul, Priya]
```

---

## `entrySet()`

Returns key-value entries.

```java
map.entrySet();
```

Conceptually:

```text
101=Manoj
102=Rahul
103=Priya
```

---

# 14. TRAVERSING A HASHMAP

### Keys

```java
for (Integer key : map.keySet()) {
    System.out.println(key);
}
```

### Values

```java
for (String value : map.values()) {
    System.out.println(value);
}
```

### Key + Value

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey());
    System.out.println(entry.getValue());
}
```

Think:

```text
entry
 ↓
key + value
```

---

# 15. NULLS IN HASHMAP

Java `HashMap` allows:

- One `null` key
- Multiple `null` values

Example:

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(null, "Unknown");
map.put(101, null);
map.put(102, null);
```

Conceptually:

```text
null → Unknown
101  → null
102  → null
```

---

# 16. ORDER IN HASHMAP

Do not depend on normal `HashMap` iteration order.

If you need insertion order, consider:

```text
LinkedHashMap
```

If you need sorted key order:

```text
TreeMap
```

This is one reason different `Map` implementations exist.

---

# 17. HASHMAP vs LINKEDHASHMAP vs TREEMAP

| Map | Main idea |
|---|---|
| HashMap | General-purpose hash-based map |
| LinkedHashMap | Maintains a defined encounter order |
| TreeMap | Keeps keys sorted |

For today, focus mainly on:

```text
HashMap → hashing + fast average lookup
```

---

# 18. HASHMAP TIME COMPLEXITY

For normal use, we commonly describe:

| Operation | Average |
|---|---:|
| `put()` | O(1) |
| `get()` | O(1) |
| `containsKey()` | O(1) |
| `remove()` | O(1) |
| `size()` | O(1) |

Important:

> These are average/expected complexities. They do not mean every operation always takes exactly one constant-time step.

Collision behavior is **not covered today**.

---

# 19. HASHMAP APPLICATIONS

## 1. Frequency counting

```text
number → frequency
```

Example:

```text
[2, 3, 2, 2, 5]

2 → 3
3 → 1
5 → 1
```

---

## 2. Duplicate detection

Question:

> Have I seen this value before?

Conceptually:

```text
value → seen
```

---

## 3. Two Sum

Given:

```text
[2, 7, 11, 15]
```

Target:

```text
9
```

HashMap can remember previously seen numbers.

---

## 4. Character frequency

String:

```text
"banana"
```

Frequency:

```text
b → 1
a → 3
n → 2
```

---

## 5. First non-repeating element

Use:

```text
value → count
```

Then find the first value with:

```text
count == 1
```

---

## 6. Mapping relationships

Examples:

```text
studentId → student
productId → product
username → user
country → capital
```

---

## 7. Memoization

HashMap can store:

```text
input → answer
```

Then repeated calculations can be avoided.

Memoization will be studied more deeply later.

---

# 20. HASHING vs HASHMAP

These are related, but not identical.

### Hashing

A **technique**:

```text
Key
 ↓
Hash function
 ↓
Hash value
```

### HashMap

A **Java data structure/class** that uses hashing to store and retrieve key-value mappings.

Think:

```text
HASHING
   ↓
Technique / concept

HASHMAP
   ↓
Key-value data structure
```

---

# 21. HASHMAP vs HASHSET

Basic difference:

```text
HashMap

KEY → VALUE
```

```text
HashSet

VALUE
```

Example:

```text
HashMap:
101 → Manoj
102 → Rahul
```

```text
HashSet:
101
102
103
```

Use `HashMap` when you need:

```text
key → value
```

Use `HashSet` when you mainly need:

```text
unique values
```

---
