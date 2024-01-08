Repr-esentation > Suite 1 > Case 1
(cases remaining: 1)

What would Python display? If you get stuck, try it out in the Python
interpreter!

>>> class A:
...   def __init__(self, x):
...         self.x = x
...   def __repr__(self):
...         return self.x
...   def __str__(self):
...         return self.x * 2
>>> class B:
...   def __init__(self):
...         print('boo!')
...         self.a = []
...   def add_a(self, a):
...         self.a.append(a)
...   def __repr__(self):
...         print(len(self.a))
...         ret = ''
...         for a in self.a:
...               ret += str(a)
...         return ret
>>> A('one')
one
>>> print(A('one'))
oneone
>>> repr(A('two'))
'two'
>>> b = B()
boo!
>>> b.add_a(A('a'))
>>> b.add_a(A('b'))
>>> b
(line 1)? 2
(line 2)? aabb