from LinkedList import Link
def remove_all(link, value):
    """Remove all the nodes containing value in link. Assume that the
    first element is never removed.

    >>> l1 = Link(0, Link(2, Link(2, Link(3, Link(1, Link(2, Link(3)))))))
    >>> print(l1)
    <0 2 2 3 1 2 3>
    >>> remove_all(l1, 2)
    >>> print(l1)
    <0 3 1 3>
    >>> remove_all(l1, 3)
    >>> print(l1)
    <0 1>
    >>> remove_all(l1, 3)
    >>> print(l1)
    <0 1>
    """
    "*** YOUR CODE HERE ***"
    if link.rest == Link.empty:
        return
    elif link.rest.first==value:
        link.rest = link.rest.rest
        remove_all(link, value)
    else:
        remove_all(link.rest, value)

import doctest
doctest.testmod()