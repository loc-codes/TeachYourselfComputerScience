import re

def greetings(message):
    """
    Returns whether a string is a greeting. Greetings begin with either Hi, Hello, or
    Hey (first letter either capitalized or lowercase), and/or end with Bye (first letter 
    either capitalized or lowercase) optionally followed by an exclamation point or period.

    >>> greetings("Hi! Let's talk about our favorite submissions to the Scheme Art Contest")
    True
    >>> greetings("Hey I love Taco Bell")
    True
    >>> greetings("I'm going to watch the sun set from the top of the Campanile! Bye!")
    True
    >>> greetings("Bye Bye Birdie is one of my favorite musicals.")
    False
    >>> greetings("High in the hills of Berkeley lived a legendary creature. His name was Oski")
    False
    >>> greetings('Hi!')
    True
    >>> greetings("bye")
    True
    """
    return bool(re.search('(?i)^(Hi|Hello|Hey)(?=[ \.!,?])|Bye[!.]?$', message))

