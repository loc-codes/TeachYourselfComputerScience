from treeADT import tree, branches, is_leaf, label
def height(t):
    """Return the height of a tree.
    >>> t = tree(3, [tree(5, [tree(1)]), tree(2)])
    >>> height(t)
    2
    >>> t = tree(3, [tree(1), tree(2, [tree(5, [tree(6)]), tree(1)])])
    >>> height(t)
    3
    """
    max_depth = 0
    for branch in branches(t):
        new_depth = 1 + height(branch)
        if new_depth > max_depth:
            max_depth = new_depth
    return max_depth  

import doctest
doctest.testmod()  