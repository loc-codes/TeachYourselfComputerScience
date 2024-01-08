from treeADT import tree, branches, is_leaf, label
def max_path_sum(t):
    """Return the maximum path sum of the tree.
    >>> t = tree(1, [tree(5, [tree(1), tree(3)]), tree(10)])
    >>> max_path_sum(t)
    11
    """
    "*** YOUR CODE HERE ***"
    max_path = 0
    #If leaf node reached, the maximum sum path would be the label of that node itself. 
    if is_leaf(t):
        #print(f'Hit Leaf {label(t)}')
        return label(t)
    #For each branch of the current tree t, find the maximum subtree sum and add to label(t)
    else:
        for branch in branches(t):
            current_path = label(t) + max_path_sum(branch)
            #print(f' Evaluating: Current Path: {current_path} > Max Path: {max_path} ?')
            if current_path > max_path:
                max_path = current_path
    return max_path

import doctest
doctest.testmod()