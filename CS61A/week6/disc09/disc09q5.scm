(define (reverse lst)
  (define (helper so_far rest)
    (if (null? rest)
        so_far
        (helper (cons (car rest) so_far) (cdr rest))
    )   
  )
  (helper nil lst)
)


(expect (reverse '(1 2 3)) (3 2 1))
(expect (reverse '(0 9 1 2)) (2 1 9 0))
