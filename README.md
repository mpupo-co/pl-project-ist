# 🦀 Rusty Interpreters

A pair of interpreters for **Rusty**, a functional-imperative programming language developed as part of the Programming Languages course at Instituto Superior Técnico.

The project was implemented in **Java** using **JavaCC** and follows a **big-step environment-based operational semantics**. It was developed in two stages:

* **Rusty1** — dynamically typed interpreter
* **Rusty2** — statically typed interpreter

---

## Features

### Rusty1

Rusty1 implements a functional-imperative language with:

* Integer, Boolean, String and Unit values
* Arithmetic and logical expressions
* First-class functions and closures
* Lexical scoping (`let`)
* Mutable references (`cell`, dereference and assignment)
* Conditional expressions
* While loops
* Sequential execution
* Printing primitives
* Struct tuples inspired by Rust
* Pattern matching over struct tuples
* Runtime type checking with descriptive error messages

Example:

```rust
#Person("Alice", 25)

match person {
    #Person(name, age) => println! name
    else => ()
}
```

### Rusty2

Rusty2 extends Rusty1 with:

* Static type checking
* Type aliases
* Function types
* Reference types
* Algebraic data types (`enum`)
* Pattern matching over tagged unions
* Width and depth subtyping for enum types
* Optional type annotations
* REPL and file execution support

Example:

```rust
type Person =
    enum {
        #person:(string, int)
    };

let p : Person = #person("Alice", 25);

match p {
    #person(name, age) => println name
}
```

---

## Architecture

The interpreters are organized into the following components:

* **Parser** (JavaCC)
* **Abstract Syntax Tree (AST)**
* **Runtime Value System**
* **Environment & Store**
* **Big-Step Evaluator**
* **Static Type Checker** (Rusty2)

---

## Language Overview

### Rusty1

```text
EE -> id | num | true | false | string | ()
    | EE + EE | EE - EE
    | EE * EE | EE / EE | -EE
    | EE == EE | EE > EE | EE >= EE | ...
    | EE && EE | EE || EE | ~EE
    | tag(EE,EE,...)
    | match EE { tag(id,id,...) => EE, else => EE }
    | let (id = EE)+ in EE
    | cell(EE) | EE := EE | !EE
    | if EE then EE else EE
    | while EE do EE
    | print! EE | println! EE
    | EE ; EE
    | abs(id, EE)
    | app(EE, EE)
```

### Rusty2

```text
EE -> id | num | true | false | string | ()
    | EE + EE | EE - EE
    | EE * EE | EE / EE | -EE
    | EE == EE | EE > EE | EE >= EE | ...
    | EE && EE | EE || EE | ~EE
    | tag(EE,EE,...)
    | match EE { tag(id,id,...) => EE, ... }
    | let id (: A)? = EE ; EE
    | type id = A ; EE
    | cell EE | EE := EE | !EE
    | if EE then EE else EE end
    | while EE do EE end
    | print EE | println EE
    | EE ; EE
    | fn(id, A, EE)
    | app(EE, EE)

A -> int
   | bool
   | string
   | ()
   | A -> B
   | ref(A)
   | enum { tag:(A,B,...) }
```

# 🧑🏻‍💻 Try it  

## Build and Clean
To compile the project, run:

```bash
./makeit
```

To remove all compiled files and generated JavaCC artifacts, use:
```bash
./clean
```

Both files can be found [here (Rusty1)](Phase1) | [here (Rusty2)](Phase2).

## Usage
```bash 
java Rusty      /* Rusty1 */
./rs2           /* Rusty2 */
```

## Examples
 
All example programs are [here (Rusty1)](Phase1/myRustyTests.md) | [here (Rusty2)](Phase2/myRusty2Tests.md).

## What I Learned

Through this project I gained hands-on experience with:

* Parser generation using JavaCC
* Abstract syntax tree design
* Interpreter implementation
* Environment and store semantics
* Runtime type checking
* Static type systems
* Algebraic data types and pattern matching
* Subtyping and type inference concepts

---

## Limitations

The following optional extensions proposed in the course specification were **not implemented**:

### Rusty1

* Safe recursion analysis

### Rusty2

* Recursive types
* Stack-allocated mutable variables (`let mut`)
* Explicit aliases (`&T`)

---

## Credits

This project was developed for the **Programming Languages** course at **Instituto Superior Técnico (IST)** during the 2025/2026 academic year.

The objective was to progressively build interpreters for a functional-imperative language, moving from dynamic type checking (Rusty1) to a statically typed language featuring algebraic data types and subtyping (Rusty2).
