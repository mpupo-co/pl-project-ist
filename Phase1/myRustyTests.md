# Rusty v1 Testing Programs

## OK Programs

### 1. Recursive Fibonacci 

```rust
let calls = cell (0);
let fib = |n| {
    calls := !calls + 1;
    if n <= 1 {
        n
    } else {
        fib(n - 1) + fib(n - 2)
    }
};
let result = fib(10);
println! result;
println! !calls;;
```

---

### 2. Pattern Matching

```rust
let area = |shape|
    match shape {
        #Circle(r) =>
            314 * r * r / 100
        else => match shape {
            #Rect(w, h) =>
                w * h
            else => match shape {
                #Triangle(b, h) =>
                    b * h / 2
                else => -1
            }
        }
    };
let c = #Circle(5);
let r = #Rect(4, 7);
let tri = #Triangle(6, 9);
println! (area(c));
println! (area(r));
println! (area(tri));;
```

---

### 3. Recursive List

```rust
let sum = |l|
    match l {
        #Cons(h, t) => h + sum(t)
        else => 0
    };
let length = |l|
    match l {
        #Cons(h, t) => 1 + length(t)
        else => 0
    };
let l0 = #Cons(1, #Cons(2,#Cons(3,#Cons(4,#Cons(5,#Nil())))));
println! (sum(l0));
println! (length(l0));;
```

---

### 4. Prime Number Detection with Cells and While

```rust
let isprime =
    |n| {
        let d = cell(2);
        let ok = cell(true);
        while (!d < n) {
            if n == (!d) * (n / (!d)) {
                ok := false
            } else {
                ()
            };
            d := !d + 1
        };
        !ok
    };
let count = cell(0);
let i = cell(2);
while (!i < 20) {
    if isprime(!i) {
        {
            print!("prime:");
            println!(!i);
            count := !count + 1
        }
    } else {
        ()
    };
    i := !i + 1
};
print!("total:");
println!(!count);;
```

---

### 5. Recursive Tree Depth Computation

```rust
let depth =
    |t| {
        match t {
            #Leaf(x) => 1
            else => match t {
                #Node(l,r) =>
                    1 + depth(l) + depth(r)
                else => 0
            }
        }
    };
let tree = #Node(#Node(#Leaf(1),#Leaf(2)),#Leaf(3));
depth(tree);;
```

---

### 6. Recursive String Construction

```rust
let countdown =
    |n| {
        if n == 0 {
            "done"
        } else {
            {
                println!(n);
                "tick-" + countdown(n-1)
            }
        }
    };
println!(countdown(3));;
```

---

## KO Programs

### 7. Invalid Arithmetic on Struct Tuple

```rust
let p = #Point(3, 4);
p + 1;;
```

---

### 8. Forbidden due not unbound id

```rust
let hero =
    #Person(
        "Luis",
        sidekick(3)
    );

let sidekick =
    |n| {
        if n == 0 {
            #Done()
        } else {
            #Helper(
                "bot-" + n,
                sidekick(n-1)
            )
        }
    };

hero;;
```

---

### 9. Invalid String Arithmetic

```rust
"abc" - "def";;
```
