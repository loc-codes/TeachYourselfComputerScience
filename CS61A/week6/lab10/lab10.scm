(define (make-adder num) (lambda (x) (+ num x)))

(define (composed f g) (lambda (x) (f (g x))))

(define (my-filter pred s) 
    (cond 
    ((eq? s ()) ())
    ((pred (car s)) (cons (car s) (my-filter pred (cdr s))))
    (else (my-filter pred (cdr s)))
    )
)

(define (exp b n)
  (define (helper b n so-far)
    (if (= n 0) 
    so-far
    (helper b (- n 1) (* so-far b))
    )
  
  )
  (helper b n 1))

(define (interleave lst1 lst2) 
  (cond ((and (eq? lst1 nil) (eq? lst2 nil)) nil)
        ((eq? lst2 nil) lst1)
        ((eq? lst1 nil) lst2)
        (else (cons (car lst1) (cons (car lst2) (interleave (cdr lst1) (cdr lst2)))))
  )
)

(define (square n) (* n n))

(define (pow base exp) 
  (cond ((= exp 1) base)
        ((even? exp) (square (pow base (/ exp 2))))
        ((odd? exp) (* base (pow base (- exp 1))))
  )
)
