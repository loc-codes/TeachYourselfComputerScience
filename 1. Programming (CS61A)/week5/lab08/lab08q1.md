---------------------------------------------------------------------
Inheritance ABCs > Suite 1 > Case 1
(cases remaining: 1)

What would Python display? If you get stuck, try it out in the Python
interpreter!

>>> class A:
...   x, y = 0, 0
...   def __init__(self):
...         return
>>> class B(A):
...   def __init__(self):
...         return
>>> class C(A):
...   def __init__(self):
...         return
>>> print(A.x, B.x, C.x)
? 0 0 0
-- OK! --

>>> B.x = 2
>>> print(A.x, B.x, C.x)
? 0 2 0
-- OK! --

>>> A.x += 1
>>> print(A.x, B.x, C.x)
? 1 3 1
-- Not quite. Try again! --

? 1 2 0
-- Not quite. Try again! --

? 1 2 1
-- OK! --

>>> obj = C()
>>> obj.y = 1
>>> C.y == obj.y
? False
-- OK! --

>>> A.y = obj.y
>>> print(A.y, B.y, C.y, obj.y)
? 1, 1, 1
-- Not quite. Try again! --

? 1 1 1
-- Not quite. Try again! --

? 1 0 1
-- Not quite. Try again! --

? 1 0 0
-- Not quite. Try again! --

? 1 1 1 1
-- OK! --