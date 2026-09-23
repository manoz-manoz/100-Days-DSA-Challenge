# DAY 12 — Java HashMap Internals

## 🎯 Goal

Understand what happens **inside Java `HashMap`** when we use:

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("Java", 100);
```

> Focus on the internal model, not memorizing source code.

---

## 1. What Happens Inside HashMap?

Think:

```text
"Java"
   ↓
hashCode()
   ↓
hash processing
   ↓
bucket index
   ↓
table[index]
   ↓
Node<K,V>
   ↓
store key + value
```

---

## 2. HashMap Has a Table

Conceptually:

```text
HashMap
   ↓
table[]
```

Example:

```text
0 → null
1 → Node
2 → null
3 → Node
4 → null
5 → Node
```

Each position is called a **bucket**.

---

## 3. What Is a Node?

A simplified `HashMap` node contains:

```text
Node<K,V>
 ├── hash
 ├── key
 ├── value
 └── next
```

The `next` reference allows multiple nodes to exist in the same bucket.

---

## 4. Collision in HashMap

Suppose:

```text
Key A → bucket 3
Key B → bucket 3
```

Both want the same bucket.

That's a **collision**.

Conceptually:

```text
bucket[3]
   |
   ↓
Node A → Node B → Node C
```

Java `HashMap` does **not** use open addressing.

---

## 5. `hashCode()`

When we write:

```java
map.put(key, value);
```

Java needs to determine where the key belongs.

Think:

```text
key
 ↓
hashCode()
 ↓
hash value
 ↓
bucket index
```

`hashCode()` returns an `int`.

---

## 6. Hash Code ≠ Bucket Index

Suppose:

```text
hashCode = 123456
```

This does **not** mean:

```text
bucket = 123456
```

The hash must be converted into a valid table index.

```text
hashCode
   ↓
hash processing
   ↓
bucket index
```

---

## 7. Why Hash Processing?

A hash code is an `int`, but the table may contain only a small number of buckets.

```text
Huge range of hash values
          ↓
       16 buckets
```

Java processes the hash to help distribute keys across buckets.

```text
Raw hash
   ↓
Hash spreading
   ↓
Bucket calculation
```

---

## 8. Bucket Index

A simple conceptual model is:

```text
index = hash % capacity
```

But Java `HashMap` actually uses a **power-of-two table size** and a bitwise calculation.

A commonly seen form is:

```text
index = (n - 1) & hash
```

where:

```text
n = table length
```

For now, remember:

> **Hash → valid array index.**

---

## 9. Power-of-Two Capacity

`HashMap` uses table sizes that are powers of two.

Examples:

```text
16
32
64
128
256
...
```

This works well with its bitwise bucket-index calculation.

Don't memorize the formula yet.

Understand the purpose:

```text
hash
 ↓
valid array index
```

---

## 10. `put()` Mental Model

When we execute:

```java
map.put("Java", 100);
```

Think:

```text
"Java"
   ↓
hashCode()
   ↓
hash processing
   ↓
bucket index
   ↓
table[index]
   ↓
Is bucket empty?
   /           YES           NO
  ↓             ↓
Store       Collision
              ↓
         Compare keys
```

---

## 11. Empty Bucket

If:

```text
table[5] = null
```

Java can place the node there:

```text
table[5]
   |
   ↓
Node("Java", 100)
```

---

## 12. Occupied Bucket

Suppose:

```text
table[5]
   |
   ↓
Node("Python", 200)
```

Now:

```java
map.put("Java", 100);
```

If `"Java"` also maps to bucket `5`, we have a collision.

Java must determine whether `"Java"` is:

1. The same key already present, or
2. A different key that collided.

---

## 13. `hashCode()` + `equals()`

These two work together.

### `hashCode()`

Helps answer:

> **Which bucket should I check?**

```text
hashCode()
   ↓
bucket
```

### `equals()`

Helps answer:

> **Is this actually the same key?**

```text
equals()
   ↓
same key?
```

---

## 14. Why Do We Need `equals()`?

Two different keys can have the same hash.

Example:

```text
Key A → hash 100
Key B → hash 100
```

Same hash does **not** mean:

```text
A.equals(B) == true
```

They may simply have collided.

So Java compares keys using `equals()`.

---

## 15. Important `hashCode()` Rule

If:

```text
a.equals(b) == true
```

then:

```text
a.hashCode() == b.hashCode()
```

must also be true.

But:

```text
same hashCode
```

does **not** guarantee:

```text
equals() == true
```

because different keys can collide.

---

## 16. Collision Example

Imagine:

```text
Key A → hash 50
Key B → hash 50
```

Both go to the same bucket.

But:

```text
A.equals(B) → false
```

Therefore:

```text
Different keys
      ↓
Same bucket
      ↓
Collision
```

---

## 17. Bucket Chain

A bucket can contain multiple nodes:

```text
table[5]
   |
   ↓
Node A → Node B → Node C
```

Each node conceptually contains:

```text
hash
key
value
next
```

A search may need to check multiple nodes in that bucket.

---

## 18. `get()` Mental Model

When we write:

```java
map.get("Java");
```

Think:

```text
"Java"
   ↓
hashCode()
   ↓
hash processing
   ↓
bucket index
   ↓
table[index]
   ↓
compare keys
   ↓
equals()
   ↓
return value
```

---

## 19. `get()` Without Collision

Suppose:

```text
table[5]
   |
   ↓
Node("Java", 100)
```

Search:

```java
map.get("Java");
```

Java finds bucket `5`.

Then:

```text
"Java".equals(node.key)
```

Result:

```text
true
```

Return:

```text
100
```

---

## 20. `get()` With Collision

Suppose:

```text
table[5]

Node("Python", 200)
       ↓
Node("Java", 100)
       ↓
Node("C++", 300)
```

Search:

```java
map.get("Java");
```

Conceptually:

```text
Python ❌
Java   ✅
```

Return:

```text
100
```

---

# 🧠 Core Mental Model

```text
                 KEY
                  ↓
              hashCode()
                  ↓
            hash processing
                  ↓
              BUCKET
                  ↓
             table[index]
                  ↓
              Collision?
               /                   NO        YES
             ↓          ↓
           STORE    compare keys
                        ↓
                     equals()
                        ↓
                  same / different
```

---

# 🧠 Mastery Task

Without looking at your notes, explain:

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("Java", 100);
int value = map.get("Java");
```

### `put()`

```text
"Java"
 ↓
?
 ↓
?
 ↓
?
```

### `get()`

```text
"Java"
 ↓
?
 ↓
?
 ↓
?
```

---

# 🎤 Interview Questions

1. What is the internal structure of `HashMap`?
2. What is a bucket?
3. What is a `Node<K,V>`?
4. Why does `HashMap` use `hashCode()`?
5. Why does `HashMap` use `equals()`?
6. Can two different keys have the same hash?
7. What happens during a collision?
8. Does Java `HashMap` use open addressing?
9. What is the purpose of `next`?
10. What happens during `get()`?
11. What happens during `put()`?
12. What is the relationship between `hashCode()` and `equals()`?
13. Is `hashCode()` the same as the bucket index?
14. Why does `HashMap` use power-of-two capacities?

---

# ✅ DAY 12 CHECKLIST

- [ ] Understand `HashMap`
- [ ] Understand `table[]`
- [ ] Understand buckets
- [ ] Understand `Node<K,V>`
- [ ] Understand `hashCode()`
- [ ] Understand hash processing
- [ ] Understand bucket index
- [ ] Understand collisions
- [ ] Understand `equals()`
- [ ] Understand `hashCode()` + `equals()`
- [ ] Understand bucket chains
- [ ] Understand `put()`
- [ ] Understand `get()`
- [ ] Complete the mastery task

---

# ⭐ DAY 12 KEY TAKEAWAY

Don't think of `HashMap` as magic.

Think:

```text
Key
 ↓
hashCode()
 ↓
hash processing
 ↓
bucket
 ↓
Node
 ↓
collision?
 ↓
hashCode + equals
 ↓
find / insert / update
```

**DAY 10:** How do we handle collisions?

**DAY 12:** How does Java `HashMap` use hashing and collision handling internally?
