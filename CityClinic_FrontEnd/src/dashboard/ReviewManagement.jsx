// src/dashboard/ReviewManagement.jsx
import React, { useEffect, useState } from 'react';

const ReviewManagement = () => {
    const [reviews, setReviews] = useState([]);

    useEffect(() => {
        const fetchReviews = async () => {
            const response = await fetch('http://localhost:8080/reviews');
            const data = await response.json();
            setReviews(data);
        };
        fetchReviews();
    }, []);

    return (
        <div>
            <h2>Review Management</h2>
            <ul>
                {reviews.map(review => (
                    <li key={review.id}>
                        <strong>{review.user}</strong>: {review.comment}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ReviewManagement;
