from math import sqrt
def is_prime(n):
    def helper(x):
        if x > n ** 0.5:
            return True
        elif n % x == 0:
            return False
        else:
            return helper(x+1)
    return helper(2)