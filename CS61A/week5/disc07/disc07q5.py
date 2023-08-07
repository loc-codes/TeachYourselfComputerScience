from LinkedList import Link
def convert_link_recursive(link): #Recursive
    """Takes a linked list and returns a Python list with the same elements.

    >>> link = Link(1, Link(2, Link(3, Link(4))))
    >>> convert_link_recursive(link)
    [1, 2, 3, 4]
    >>> convert_link_recursive(Link.empty)
    []
    """
    if link == Link.empty:
        return []
    else:
        return [link.first] + convert_link_recursive(link.rest)
    
def convert_link_iterative(link):
    """Takes a linked list and returns a Python list with the same elements.

    >>> link = Link(1, Link(2, Link(3, Link(4))))
    >>> convert_link_iterative(link)
    [1, 2, 3, 4]
    >>> convert_link_iterative(Link.empty)
    []
    """
    answer = []
    while link != Link.empty:
        answer.append(link.first)
        link = link.rest
    return answer
        

import doctest
doctest.testmod()