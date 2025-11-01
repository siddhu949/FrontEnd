const BASE_URL = 'https://www.swiggy.com/dapi';

// Try this CORS proxy instead
const CORS_PROXY = 'https://api.allorigins.win/raw?url=';

// Default coordinates (Bangalore)
const DEFAULT_LAT = 12.9715987;
const DEFAULT_LNG = 77.5945627;

export const fetchRestaurants = async (lat = DEFAULT_LAT, lng = DEFAULT_LNG) => {
  try {
    const url = `${BASE_URL}/restaurants/list/v5?lat=${lat}&lng=${lng}&is-seo-homepage-enabled=true&page_type=DESKTOP_WEB_LISTING`;
    
    const response = await fetch(`${CORS_PROXY}${encodeURIComponent(url)}`);
    
    if (!response.ok) {
      throw new Error('Failed to fetch restaurants');
    }
    
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching restaurants:', error);
    throw error;
  }
};

export const fetchRestaurantMenu = async (restaurantId, lat = DEFAULT_LAT, lng = DEFAULT_LNG) => {
  try {
    const url = `${BASE_URL}/menu/pl?page-type=REGULAR_MENU&complete-menu=true&lat=${lat}&lng=${lng}&restaurantId=${restaurantId}`;
    
    const response = await fetch(`${CORS_PROXY}${encodeURIComponent(url)}`);
    
    if (!response.ok) {
      throw new Error('Failed to fetch menu');
    }
    
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching menu:', error);
    throw error;
  }
};