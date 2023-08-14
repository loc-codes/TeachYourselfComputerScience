import re

def cs_classes(post):
    """
    Returns strings that look like a Berkeley CS or EE class,
    starting with "CS" or "EE", followed by a number, optionally ending with A, B, or C
    and potentially with a space between "CS" or "EE" and the number.
    Case insensitive.

    >>> cs_classes("Is it unreasonable to take CS61A, CS61B, CS70, and EE16A in the summer?")
    True
    >>> cs_classes("how do I become a TA for cs61a? that job sounds so fun")
    True
    >>> cs_classes("Can I take ECON101 as a CS major?")
    False
    >>> cs_classes("Should I do the lab lites or regular labs in EE16A?")
    True
    >>> cs_classes("thoughts on ee127?")
    True
    >>> cs_classes("Is 70 considered an EECS class?")
    False
    >>> cs_classes("What are some good CS upper division courses? I was thinking about CS 161 or CS 169a")
    True
    """
    return bool(re.search('(?i)(?:CS|EE) ?\d+[ABCabc]?', post))

