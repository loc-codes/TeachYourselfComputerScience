def square(x):
    print("here!")
    return x * x

def so_slow(num):
    x = num
    while x > 0:
        x = x + 1
    return x / 0

square(so_slow(5))

#Q. What is the result of evaluating the following code?
#A. This code causes an infinite loop, so_slow(5) is called first and x > 0 never
#   evaluates to false
