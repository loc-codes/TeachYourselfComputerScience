(define (make-adder num) (lambda (x) (+ num x)))

(define (composed f g) (lambda (x) (f (g x))))

(define (my-filter pred s) 
    (cond 
    ((eq? s ()) ())
    ((pred (car s)) (cons (car s) (filter pred (cdr s))))
    (else (filter pred (cdr s)))
    )
)

(define (exp b n)
  (define (helper b n so-far) "YOUR CODE HERE")
  (helper b n 1))

(define (interleave lst1 lst2) 'YOUR-CODE-HERE)

(define (square n) (* n n))

(define (pow base exp) 'YOUR-CODE-HERE)
