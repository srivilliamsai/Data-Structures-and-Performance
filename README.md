## 📘 Data Structures and Performance

**Java implementations and performance analysis of core data structures and algorithms**, developed as part of the **UC San Diego “Data Structures and Performance”** Coursera course.

This repository contains multiple mini-projects and assignments focusing on fundamental data structures, algorithmic efficiency, benchmarking, and unit testing — implemented in **Java**.

---

### 🧩 Course Overview

The **Data Structures and Performance** course (offered by *University of California, San Diego* on Coursera) teaches how to:

* Analyze algorithmic complexity using Big-O notation
* Implement core data structures such as Linked Lists, Trees, and Tries
* Measure and optimize code performance
* Build text processing, spelling suggestion, and autocomplete systems
* Develop benchmark tools and unit tests to compare efficiency

---

### 📂 Repository Structure

```
Data-Structures-and-Performance/
│
├── flesch-eff/                  # Efficient Document implementation
│   ├── BasicDocument.java
│   ├── EfficientDocument.java
│   ├── DocumentBenchmarking.java
│   └── ...
│
├── linkedlist-assignment/       # Custom Linked List implementation
│   ├── MyLinkedList.java
│   ├── MyLinkedListGrader.java
│   └── ...
│
├── readability-project/         # Flesch Readability Analysis project
│   ├── Document.java
│   ├── BasicDocument.java
│   ├── LaunchClass.java
│   └── MainApp.java
│
├── spellcheck-autocomplete/     # Autocomplete Dictionary Trie implementation
│   ├── AutoCompleteDictionaryTrie.java
│   ├── DictionaryHashSet.java
│   ├── DictionaryLoader.java
│   └── ...
│
├── spelling-suggestions/        # NearbyWords and Word Path Tree implementations
│   ├── NearbyWords.java
│   ├── SpellingSuggest.java
│   ├── WPTree.java
│   ├── WPTreeNode.java
│   └── ...
│
└── .gitignore
```

---

### 🚀 Projects Summary

#### 1. **Linked List Implementation**

* Custom doubly linked list (`MyLinkedList`)
* Manual node management without Java’s built-in collections
* Supports add, remove, get, and set operations
* Includes boundary and exception handling tests

#### 2. **Document Readability Analysis**

* Implements Flesch readability formula to evaluate text complexity
* Counts words, sentences, and syllables manually
* Two versions:

  * `BasicDocument.java`: straightforward regex-based counting
  * `EfficientDocument.java`: optimized text traversal for performance
* Compared using `DocumentBenchmarking.java`

#### 3. **Spelling Suggestions**

* Implements a spelling correction algorithm using `NearbyWords`
* Generates possible words via insertions, deletions, and substitutions
* Uses a breadth-first search (BFS) over word transformations
* Integrated with `WPTree` to find shortest valid word paths

#### 4. **Autocomplete Dictionary Trie**

* Efficient prefix search using a **Trie (Prefix Tree)**
* Supports:

  * Word addition
  * Prefix completion
  * Performance benchmarking
* Includes dictionary loaders and test scaffolding

#### 5. **Flesch Efficiency (Benchmarking)**

* Compares execution speed of `BasicDocument` vs `EfficientDocument`
* Plots runtime vs input size using benchmarking utilities
* Demonstrates the impact of algorithmic efficiency in text processing

---

### 🧪 Testing & Benchmarking

Each project includes:

* **Unit Tests** (e.g., `BasicDocumentGrader.java`, `MyLinkedListGrader.java`)
* **Benchmark Programs** (e.g., `DocumentBenchmarking.java`)
* **JUnit integration** for automated validation

---

### ⚙️ How to Run

```bash
# Compile all Java files
javac -d bin $(find src -name "*.java")

# Run individual projects
java -cp bin application.MainApp

# Example: Run Benchmark
java -cp bin document.DocumentBenchmarking
```

> 💡 Ensure you are inside the correct project folder before compiling/running.

---

### 📊 Key Learnings

* How to analyze **time and space complexity**
* The trade-off between **clarity and performance**
* Building **efficient algorithms** for real-world text operations
* Understanding **linked structures, tries, and BFS traversal**

---

### 🏫 Course Reference

This repository is based on:

> **UC San Diego – Data Structures and Performance**
> *(Part of the “Java Programming: Arrays, Lists, and Structured Data” specialization on Coursera)*
> [🔗 Course Link](https://www.coursera.org/learn/data-structures-optimizing-performance)

---

### 👨‍💻 Author

**Sri Villiam Sai**
💼 Software Developer | Building full-stack apps with Java, Spring Boot, Node.js, and Flutter 🚀
📧 [srivilliamsai@gmail.com](mailto:srivilliamsai@gmail.com)
🌐 [GitHub: srivilliamsai](https://github.com/srivilliamsai)

---
