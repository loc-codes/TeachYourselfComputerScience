(define (duplicate lst)
    (if 
        (eq? lst nil)
        lst
        (cons (car lst) (cons (car lst) (duplicate (cdr lst))))
    )
)

(expect (duplicate '(1 2 3)) (1 1 2 2 3 3))
(expect (duplicate '(1 1)) (1 1 1 1))
