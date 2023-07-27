import doctest
def skip_mul(n):
    """Return the product of n * (n - 2) * (n - 4) * ...

    >>> skip_mul(5) # 5 * 3 * 1
    15
    >>> skip_mul(8) # 8 * 6 * 4 * 2
    384
    """
    if n <= 1: #ADDED THIS LINE TO FIX bug
        return 1 #ADDED THIS LINE TO FIX bug
    if n == 2:
        return 2
    else:
        return n * skip_mul(n - 2)
    
doctest.testmod()