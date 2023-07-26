import doctest
def is_prime(n):
    """Returns True if n is a prime number and False otherwise.
    >>> is_prime(2)
    True
    >>> is_prime(16)
    False
    >>> is_prime(521)
    True
    """
    def helper(i):
        if i > (n ** 0.5): # Could replace with i == n
            return True
        elif n % i == 0:
            return False
        return helper(i + 1)
    return helper(2)

def primes_gen(n):
    """Generates primes in decreasing order.
    >>> pg = primes_gen(7)
    >>> list(pg)
    [7, 5, 3, 2]
    """
    "*** YOUR CODE HERE ***"
    for num in iter(range(n,1,-1)):
        if is_prime(num):
            yield num

def primes_gen_yield_from(n):
    """Generates primes in decreasing order.
    >>> pg = primes_gen_yield_from(7)
    >>> list(pg)
    [7, 5, 3, 2]
    """
    if n <= 1:
        return
    if is_prime(n):
        yield n
    yield from primes_gen_yield_from(n-1)

def primes_gen_ascending(n):
    """Generates primes in ascending order.
    >>> pg = primes_gen_ascending(7)
    >>> list(pg)
    [2, 3, 5, 7]
    """
    for num in iter(range(2,n+1)):
        if is_prime(num):
            yield num

def primes_gen_ascending_yield_from(n):
    """Generates primes in ascending order.
    >>> pg = primes_gen_ascending(7)
    >>> list(pg)
    [2, 3, 5, 7]
    """
    def helper(n,i):
        if is_prime(i):
            yield i
        if i > n:
            return
        else:
            yield from helper(n,i+1)
    return helper(n,2)
    
doctest.testmod()