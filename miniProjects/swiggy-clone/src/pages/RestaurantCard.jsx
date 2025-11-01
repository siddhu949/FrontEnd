import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/RestaurantCard.css';

const RestaurantCard = ({ restaurant }) => {
  const { id, name, cuisines, avgRating, sla, cloudinaryImageId, costForTwo } = restaurant?.info || {};

  const IMG_CDN = "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/";

  return (
    <Link to={`/restaurant/${id}`} className="restaurant-card-link">
      <div className="restaurant-card">
        <div className="restaurant-image">
          <img 
            src={cloudinaryImageId ? `${IMG_CDN}${cloudinaryImageId}` : 'https://via.placeholder.com/300x200?text=No+Image'}
            alt={name}
          />
        </div>
        <div className="restaurant-info">
          <h3>{name}</h3>
          <p className="cuisines">{cuisines?.join(', ')}</p>
          <div className="restaurant-details">
            <span className="rating">⭐ {avgRating || 'N/A'}</span>
            <span className="dot">•</span>
            <span className="delivery-time">{sla?.deliveryTime || 'N/A'} mins</span>
            <span className="dot">•</span>
            <span className="cost">{costForTwo || 'N/A'}</span>
          </div>
        </div>
      </div>
    </Link>
  );
};

export default RestaurantCard;