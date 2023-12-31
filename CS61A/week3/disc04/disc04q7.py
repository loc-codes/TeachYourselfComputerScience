from treeADT import tree, branches, is_leaf, label
def find_path(t, x):
    """
    >>> t = tree(2, [tree(7, [tree(3), tree(6, [tree(5), tree(11)])] ), tree(15)])
    >>> find_path(t, 5)
    [2, 7, 6, 5]
    >>> find_path(t, 10)  # returns None
    """
    if label(t) == x:
        return [x]
    for branch in branches(t):
        path = find_path(branch,x)
        if path is not None:
            return [label(t)] + path

import doctest
doctest.testmod()