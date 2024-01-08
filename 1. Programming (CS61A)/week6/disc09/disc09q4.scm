(define (sum lst)
    (define (sum_so_far lst total)
        (if (null? lst)
            total
            (sum_so_far (cdr lst) (+ total (car lst)))
        )
    )
    (sum_so_far lst 0)
)

(expect (sum '(1 2 3)) 6)
(expect (sum '(10 -3 4)) 11)
