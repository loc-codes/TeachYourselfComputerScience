(define (distance city-a city-b)
    sqrt((+
        (expt 
            (- (get-lat city-a) 
            (get-lat city-b)) 2) 
        (expt 
            (- (get-lon city-a) 
            (get-lon city-b)) 2)
        )
    )
)