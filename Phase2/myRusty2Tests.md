# Rusty2 Testing Programs
## OK Programs
```rust
type Grade = enum { #fail:(int), #pass:(int), #merit:(int), #distinction:(int) };
let classify = |score:int| {
    if (score < 10) { #fail(score) }
    else {
        if (score < 14) { #pass(score) }
        else {
            if (score < 17) { #merit(score) }
            else { #distinction(score) }
        }
    }
};
let points = |g:Grade| {
    match g {
    #fail(n) => 0
    | #pass(n) => n
    | #merit(n) => n
    | #distinction(n) => n
    }
};
points(classify(18))
;;
```
```rust
type Shape  = enum { #circle:(int), #square:(int), #triangle:(int) };
type Circle = enum { #circle:(int) };
let area = |s:Shape| {
    match s {
    #circle(r) => r * r
    | #square(s) => s * s
    | #triangle(b) => b
    }
};
let c:Shape = #circle(5);
area(c)
;;
```
```rust
type Full = enum { #point:(int,int) };
type Partial = enum { #point:(int) };
let getX = |p:Partial| {
    match p { #point(x) => x }
};
let p:Full = #point(10, 20);
getX(p)
;;
```
```rust
type TRANSFORM = int->int;
let compose = |f:TRANSFORM, g:TRANSFORM| {
    |x:int| { f(g(x)) }
};
let double = |x:int| { x * 2 };
let inc = |x:int| { x + 1 };
let doubleThenInc:TRANSFORM = compose(inc, double);
doubleThenInc(4)
;;
```
```rust
type INTOP = int->int;
let power = |base:int| {
    |exp:int| {
        let result = cell(1);
        let e = cell(exp);
        while (!e > 0) {
            result := !result * base;
            e := !e - 1
        };
        !result
    }
};
let square:INTOP = power(2);
let cube:INTOP = power(3);
let apply = |f:INTOP, n:int| { f(n) };
(apply(square, 10)) + (apply(cube, 3))
;;
```
## KO Programs
```rust
/* fails to typecheck */
type Animal = enum { #dog:(int), #cat:(int) };
type Dog = enum { #dog:(int) };
let f = |d:Dog| {
    match d { #dog(n) => n }
};
let a:Animal = #cat(1);
f(a);;
```
