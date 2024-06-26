package com.movieapi.Movie.movie;


import com.movieapi.Movie.Review.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
@Id
private ObjectId id;
private String imdbId;
private String title;
private String releaseDate;
private String trailerLink;
private String poster;
private List<String> backdrop;
private List<String> genres;
@DocumentReference
private List<Review> reviews;



public Movie (String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> backdrop, List<String> genres, List<Review> reviews){
    this.imdbId = imdbId;
    this.title = title;
    this.releaseDate = releaseDate;
    this.trailerLink = trailerLink;
    this.poster = poster;
    this.backdrop = backdrop;
    this.genres = genres;
}

}
