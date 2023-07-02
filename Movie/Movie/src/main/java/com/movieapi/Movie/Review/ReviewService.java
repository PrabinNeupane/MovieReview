package com.movieapi.Movie.Review;


import com.movieapi.Movie.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Review createReview(String reviewBody, String movieId){
       Review  review = reviewRepository.insert(new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

         mongoTemplate.update(Movie.class)
                 .matching(Criteria.where("imdbId").is(movieId))
                .apply(new Update().push("reviews", review))
                .first();

            return review;
    }
}
