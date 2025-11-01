import { useState, useEffect } from 'react';
import RestaurantCard from '../components/RestaurantCard';
import { mockRestaurants } from '../utils/mockData';
import '../styles/Home.css';

const Home = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [filteredRestaurants, setFilteredRestaurants] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchText, setSearchText] = useState('');

  useEffect(() => {
    // Simulate API call with mock data
    setTimeout(() => {
      setRestaurants(mockRestaurants);
      setFilteredRestaurants(mockRestaurants);
      setLoading(false);
    }, 1000);
  }, []);

  const filterTopRated = () => {
    const filtered = restaurants.filter(res => res.info.avgRating >= 4.2);
    setFilteredRestaurants(filtered);
  };

  const showAllRestaurants = () => {
    setFilteredRestaurants(restaurants);
  };

  const handleSearch = () => {
    const filtered = restaurants.filter(res =>
      res.info.name.toLowerCase().includes(searchText.toLowerCase())
    );
    setFilteredRestaurants(filtered);
  };

  if (loading) {
    return (
      <div className="home">
        <div className="loading">
          <h2>Loading restaurants...</h2>
        </div>
      </div>
    );
  }

  return (
    <div className="home">
      <div className="search-container">
        <input
          type="text"
          placeholder="Search restaurants..."
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          className="search-input"
        />
        <button onClick={handleSearch} className="search-btn">Search</button>
      </div>

      <div className="filter-container">
        <button onClick={showAllRestaurants} className="filter-btn">
          All Restaurants ({restaurants.length})
        </button>
        <button onClick={filterTopRated} className="filter-btn">
          Top Rated (4.2+)
        </button>
      </div>

      <h2>Restaurants near you</h2>
      
      <div className="restaurant-grid">
        {filteredRestaurants.length > 0 ? (
          filteredRestaurants.map((restaurant) => (
            <RestaurantCard key={restaurant.info.id} restaurant={restaurant} />
          ))
        ) : (
          <p>No restaurants found</p>
        )}
      </div>
    </div>
  );
};

export default Home;