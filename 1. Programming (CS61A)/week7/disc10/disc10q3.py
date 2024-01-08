import re
def phone_number(string):
    """
    >>> phone_number("Song by Logic: 1-800-273-8255")
    True
    >>> phone_number("123 456 7890")
    True
    >>> phone_number("1" * 11) and phone_number("1" * 10) and phone_number("1" * 7)
    True
    >>> phone_number("The secret numbers are 4, 8, 15, 16, 23 and 42 (from the TV show Lost)")
    False
    >>> phone_number("Belphegor's Prime is 1000000000000066600000000000001")
    False
    >>> phone_number(" 1122334455 ")
    True
    >>> phone_number(" 11 22 33 44 55 ")
    False
    >>> phone_number("Tommy Tutone's '80s hit 867-5309 /Jenny")
    True
    >>> phone_number("11111111") # 8 digits isn't valid, has to be 11, 10, or 7
    False
    """
    return bool(re.search(r"\b(\d[ -]?(\d{3})|(\d{3})?)[ -]?\d{3}[ -]?\d{4}\b", string))
    return bool(re.search(r"\b(((\d[ -]?)(\d{3}[ -]?))?|(\d{3}[ -]?))?(\d{3}[ -]?)(\d{4})\b", string))    
    return bool(re.search(r"\b((\d[- ]?)?\d{3}[- ]?)?\d{3}[- ]?\d{4}\b", string))

