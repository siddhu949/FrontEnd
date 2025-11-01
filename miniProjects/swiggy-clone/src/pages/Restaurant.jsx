import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { mockMenuData } from '../utils/mockData';
import '../styles/Restaurant.css';

const Restaurant = () => {
  const { id } = useParams();
  const [restaurant, setRestaurant] = useState(null);
  const [loading, setLoading] = useState(true);
  const [cart, setCart] = useState([]);

  useEffect(() => {
    // Simulate API call
    setTimeout(() => {
      const restaurantData = mockMenuData[id];
      setRestaurant(restaurantData);
      setLoading(false);
    }, 500);
  }, [id]);

  const addToCart = (item) => {
    setCart([...cart, item]);
    alert(`${item.name} added to cart!`);
  };

  if (loading) {
    return (
      <div className="restaurant-page">
        <h2>Loading menu...</h2>
      </div>
    );
  }

  if (!restaurant) {
    return (
      <div className="restaurant-page">
        <h2>Restaurant not found</h2>
        <p>Menu data is only available for selected restaurants.</p>
      </div>
    );
  }

  // Group items by category
  const categories = {};
  restaurant.menuItems.forEach(item => {
    if (!categories[item.category]) {
      categories[item.category] = [];
    }
    categories[item.category].push(item);
  });

  return (
    <div className="restaurant-page">
      <div className="restaurant-header">
        <div className="restaurant-details">
          <h1>{restaurant.name}</h1>
          <p className="cuisines">{restaurant.cuisines.join(', ')}</p>
          <div className="restaurant-meta">
            <span className="rating">⭐ {restaurant.avgRating}</span>
            <span className="dot">•</span>
            <span>{restaurant.deliveryTime} mins</span>
            <span className="dot">•</span>
            <span>{restaurant.costForTwo}</span>
          </div>
        </div>
      </div>

      <div className="menu-section">
        <h2>Menu</h2>
        {Object.keys(categories).map(category => (
          <div key={category} className="category-section">
            <h3 className="category-title">{category}</h3>
            <div className="menu-items">
              {categories[category].map(item => (
                <div key={item.id} className="menu-item">
                  <div className="item-details">
                    <div className="item-type">
                      {item.isVeg ? '🟢' : '🔴'}
                    </div>
                    <div className="item-info">
                      <h4>{item.name}</h4>
                      <p className="item-price">₹{item.price}</p>
                      <p className="item-description">{item.description}</p>
                    </div>
                  </div>
                  <div className="item-action">
                    <button 
                      className="add-btn"
                      onClick={() => addToCart(item)}
                    >
                      ADD
                    </button>
                  </div>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>

      {cart.length > 0 && (
        <div className="cart-footer">
          <p>{cart.length} item(s) in cart</p>
          <button className="view-cart-btn">View Cart</button>
        </div>
      )}
    </div>
  );
};

export default Restaurant;