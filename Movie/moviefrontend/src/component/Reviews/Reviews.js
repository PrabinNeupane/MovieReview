import React, { useEffect, useRef, useState } from 'react';
import api from '../../api/AxiosConfig';
import { useParams } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import ReviewForm from '../reviewForm/ReviewForm';

const Reviews = ({ movie, reviews, setReviews }) => {
  const [reviewText, setReviewText] = useState('');
  const params = useParams();
  const movieId = params.movieId;
  const revText = useRef();

  useEffect(() => {
    getMovieData(movieId);
  }, []);

  const getMovieData = async (id) => {
    try {
      const response = await api.get(`/api/v1/movies/${id}`);
      const singleMovie = response.data;
      setMovie(singleMovie);
      setReviews(singleMovie.reviews);
    } catch (error) {
      console.error(error);
    }
  };

  const addReview = async (e) => {
    e.preventDefault();

    try {
      const response = await api.post('/api/v1/reviews', {
        reviewBody: reviewText,
        imdbId: movieId,
      });

      const updatedReviews = [...reviews, { body: reviewText }];

      setReviewText('');
      setReviews(updatedReviews);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <h3>Reviews</h3>
        </Col>
      </Row>
      <Row className="mt-2">
        <Col>
          <img src={movie?.poster} alt="" />
        </Col>
        <Col>
          <>
            <Row>
              <Col>
                <ReviewForm
                  handleSubmit={addReview}
                  revText={revText}
                  setRevText={setReviewText}
                  labelText="Write a Review?"
                />
              </Col>
            </Row>
            <Row>
              <Col>
                <hr />
              </Col>
            </Row>
          </>
          {reviews?.map((r, index) => (
            <React.Fragment key={index}>
              <Row>
                <Col>{r.body}</Col>
              </Row>
              <Row>
                <Col>
                  <hr />
                </Col>
              </Row>
            </React.Fragment>
          ))}
        </Col>
      </Row>
      <Row>
        <Col>
          <hr />
        </Col>
      </Row>
    </Container>
  );
};

export default Reviews;
