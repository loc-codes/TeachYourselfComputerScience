=====================================================================
Assignment: Lab 7
OK, version v1.18.1
=====================================================================

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Unlocking tests

At each "? ", type what you would expect the output to be.
Type exit() to quit

---------------------------------------------------------------------
Iterators > Suite 1 > Case 1
(cases remaining: 3)

What would Python display? If you get stuck, try it out in the Python
interpreter!

>>> # Enter StopIteration if StopIteration exception occurs, Error for other errors
>>> # Enter Iterator if the output is an iterator object.
>>> s = [1, 2, 3, 4]
>>> t = iter(s)
>>> next(s) 
? Error
-- OK! --

>>> next(t)
? 1
-- OK! --

>>> next(t)
? 2
-- OK! --

>>> iter(s)
? 
-- Not quite. Try again! --

? Iterator
-- OK! --

>>> next(iter(s))
? 1
-- OK! --

>>> next(iter(t))
? 3
-- OK! --

>>> next(iter(s))
? 1
-- OK! --

>>> next(iter(t))
? 3
-- Not quite. Try again! --

? 4
-- OK! --

>>> next(t)
? 3
-- Not quite. Try again! --

? StopIteration
-- OK! --

---------------------------------------------------------------------
Iterators > Suite 1 > Case 2
(cases remaining: 2)

What would Python display? If you get stuck, try it out in the Python
interpreter!

>>> r = range(6)
>>> r_iter = iter(r)
>>> next(r_iter)
? 0
-- OK! --

>>> [x + 1 for x in r]
? [1,2,3,4,5,6]
-- OK! --

>>> [x + 1 for x in r_iter]
? [2,3,4,5,6]
-- OK! --

>>> next(r_iter)
? 1
-- Not quite. Try again! --

? 2
-- Not quite. Try again! --

? StopIteration
-- OK! --

>>> list(range(-2, 4))   # Converts an iterable into a list
? [-2,-1,0,1,2,3]
-- OK! --

---------------------------------------------------------------------
Iterators > Suite 1 > Case 3
(cases remaining: 1)

What would Python display? If you get stuck, try it out in the Python
interpreter!

>>> map_iter = map(lambda x : x + 10, range(5))
>>> next(map_iter)
? 10
-- OK! --

>>> next(map_iter)
? 11
-- OK! --

>>> list(map_iter)
? [12,13,14]
-- OK! --

>>> for e in filter(lambda x : x % 2 == 0, range(1000, 1008)):
...     print(e)
(line 1)? 1000
(line 2)? 1002
(line 3)? 1004
(line 4)? 1006
-- OK! --

>>> [x + y for x, y in zip([1, 2, 3], [4, 5, 6])]
? [5,7,9]
-- OK! --

>>> for e in zip([10, 9, 8], range(3)):
...   print(tuple(map(lambda x: x + 2, e)))
(line 1)? (12,0)
-- Not quite. Try again! --

(line 1)? (12,2)
(line 2)? (11,3)
(line 3)? (10,4)
-- OK! --

---------------------------------------------------------------------
OK! All cases for Iterators unlocked.