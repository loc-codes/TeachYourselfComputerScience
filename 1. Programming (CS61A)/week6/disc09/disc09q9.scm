(define (sum-list lst)
    (if (null? lst)
        0
        (+ (car lst) (sum-list (cdr lst)))
    )
)

(define (sum-nodes t)
    (define branch-sums (map sum-nodes (branches t)))
    (+ (label t) (sum-list branch-sums))
)