def is_prime(n):
    """Returns True if n is a prime number and False otherwise.
    >>> is_prime(2)
    True
    >>> is_prime(16)
    False
    >>> is_prime(521)
    True
    """
    "*** YOUR CODE HERE ***"
    def helper(x):
        if x > n ** 0.5:
            return True
        elif n % x == 0:
            return False
        else:
            return helper(x+1)
    return helper(2)
