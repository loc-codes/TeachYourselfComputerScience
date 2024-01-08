import re
def match_url(text):
    """
    >>> match_url("https://cs61a.org/resources/#regular-expressions")
    True
    >>> match_url("https://pythontutor.com/composingprograms.html")
    True
    >>> match_url("https://pythontutor.com/should/not.match.this")
    False
    >>> match_url("https://link.com/nor.this/")
    False
    >>> match_url("http://insecure.net")
    True
    >>> match_url("htp://domain.org")
    False
    """
    beginning = r"^"
    scheme = r"(https?:\/\/)?"
    domain = r"\w+\.\w{3}"
    path = r"(\/\w+)*(\.\w+)?"
    anchor = r"(\/#[\w-]+)?$"
    end = r"$"
    full_string = beginning + scheme + domain + path + anchor + end
    return bool(re.match(full_string, text))
