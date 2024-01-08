def merge(n1,n2):
    if n1 == 0:
        return n2
    elif n2 == 0:
        return n1 
    #at every step, look at the last digit of n1 and n2
    #keep the smaller digit on the "right"
    elif n1 % 10 > n2 % 10:
        return merge(n1, n2//10)*10+n2%10
    else:
        return merge(n1//10, n2)*10+n1%10
        