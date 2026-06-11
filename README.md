# 🦀 Rusty1 Interpreter

Rusty1 Interpreter — Functional-Imperative Language Implementation (dynamic type checking)

## Description

This project implements an interpreter for **Rusty1**, a functional-imperative programming language, built with Java + JavaCC using big-step environment-based semantics.

## Abstract Syntax
```
EE -> id | num | true | false | string | ()
    | EE + EE | EE - EE
    | EE * EE | EE / EE | -EE | ( EE )
    | EE == EE | EE > EE | EE >= EE | …
    | EE && EE | EE || EE | ~ EE
    | tag(EE,EE,…)
    | match EE { tag(id,id,…) => EE, else => EE }
    | let (id = EE)+ in EE
    | cell (EE) | EE := EE | !EE
    | if EE then EE else EE
    | while EE do EE
    | print! EE | println! EE | EE ; EE
    | abs ( id, EE )
    | app ( EE, EE)
```


## Safe Recursion (Challenge)
- Not implemented

# 🦀 Rusty2 Interpreter

Rusty2 Interpreter — Functional-Imperative Language Implementation (static type checking)

## Description

Using Rusty1 as starting point, **Rusty2** implements static type-checking over it with some changes on language syntax.

## Abstract Syntax
```
EE -> id | num | true | false | string | ()         
    | EE + EE | EE - EE                             
    | EE * EE | EE / EE | -EE | ( EE )                   
    | EE == EE | EE > EE | EE >= EE | …                     
    | EE && EE | EE || EE | ~ EE                            
    | tag(EE,EE,…)                                          
    | match EE { tag(id,id,…) => EE, … }       
    | let id (: A)? = EE+ ; EE
    | type id = A ; EE
    | cell EE | EE := EE | ! EE
    | if EE then EE else EE end
    | while EE do EE end
    | print EE | | println EE | EE ; EE
    | fn ( id, A, EE )
    | app ( EE, EE)

A,B -> id
    | ()
    | int
    | bool
    | string
    | A -> B
    | enum { tag: ( A,B,C, … ), … }
    | ref ( A )
```
## Recursive Types, Stack Allocated Mutable Cells, Explicit Aliases (Challenges)
- Not implemented
---

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