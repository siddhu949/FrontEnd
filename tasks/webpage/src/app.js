import React, { useState } from "react";

// Navbar Component
const Navbar = () => (
  <nav className="navbar">
    <h1 style={{ display: 'flex', alignItems: 'center', gap: '8px', color: '#ff3d00' }}>


  MovieBook
</h1>
    <ul>
      <li><a href="#now-showing">Now Showing</a></li>
      <li><a href="#coming-soon">Coming Soon</a></li>
      <li><a href="#">Profile</a></li>
    </ul>
  </nav>
);

// Banner Component with Featured Movie
const Banner = ({ featuredMovie, city, setCity, query, setQuery, handleSearch }) => (
  <div className="banner">
    <div className="banner-content">
      <div className="banner-left">
        <h1>{featuredMovie.title}</h1>
        <p>{featuredMovie.genre}, {featuredMovie.language}</p>
        <p>{featuredMovie.cast}</p>
        <button className="book-now">Book Now</button>
        <div className="search-box">
          <select value={city} onChange={(e) => setCity(e.target.value)}>
            <option value="">Select City</option>
            <option>Visakhapatnam</option>
            <option>Hyderabad</option>
            <option>Chennai</option>
            <option>Bangalore</option>
          </select>
          <input
            type="text"
            placeholder="Search for movies or theaters..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
          <button onClick={handleSearch}>🔍</button>
        </div>
      </div>
      <div className="banner-right">
        <img src={featuredMovie.img} alt={featuredMovie.title} />
      </div>
    </div>
  </div>
);

// MovieCard Component
const MovieCard = ({ title, img, genre, language, cast, rating }) => (
  <div className="movie-card">
    <img src={img} alt={title} />
    <div className="overlay">
      <h3>{title}</h3>
      <p>Genre: {genre}</p>
      <p>Language: {language}</p>
      <p>Cast: {cast}</p>
      <p>Rating: {rating}</p>
    </div>
  </div>
);

// MovieSection Component
const MovieSection = ({ sectionTitle, movies }) => (
  <div className="section" id={sectionTitle.toLowerCase().replace(" ", "-")}>
    <h2>{sectionTitle}</h2>
    <div className="movies">
      {movies.map((movie, index) => (
        <MovieCard key={index} {...movie} />
      ))}
    </div>
  </div>
);

// App Component
export default function App() {
  const [city, setCity] = useState("");
  const [query, setQuery] = useState("");

  const handleSearch = () => {
    alert(`Searching for "${query}" in ${city || "All Cities"}...`);
  };

  const nowShowing = [
    {
      title: "Spider-Man: No Way Home",
      img: "https://tse2.mm.bing.net/th/id/OIP.jFWga73Vye9w2gQvqWJX6gHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3",
      genre: "Action",
      language: "English",
      cast: "Tom Holland, Zendaya",
      rating: "8.5/10"
    },
    {
      title: "Avatar: The Way of Water",
      img: "https://tse4.mm.bing.net/th/id/OIP.73SG9-j2EMH3k-ekDwfqzwHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3",
      genre: "Adventure",
      language: "English",
      cast: "Sam Worthington, Zoe Saldana",
      rating: "8.3/10"
    },
    {
      title: "Black Panther: Wakanda Forever",
      img: "https://th.bing.com/th/id/OIP.rnMklcBwV1xZl5ndF5sTSAHaK-?w=205&h=304&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3",
      genre: "Action",
      language: "English",
      cast: "Letitia Wright, Lupita Nyong'o",
      rating: "8.0/10"
    }
  ];

  const comingSoon = [
    {
      title: "Jurassic World: Dominion",
      img: "https://th.bing.com/th/id/OIP.NsVJ8Tg22sXq3mjtsaTowgHaKe?w=117&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3",
      genre: "Adventure",
      language: "English",
      cast: "Chris Pratt, Bryce Dallas Howard",
      rating: "7.5/10"
    },
    {
      title: "Guardians of the Galaxy Vol.3",
      img: "https://th.bing.com/th/id/OIP.4E32Anj4RLBR4T3KZGh9cgHaLH?w=204&h=306&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3",
      genre: "Action",
      language: "English",
      cast: "Chris Pratt, Zoe Saldana",
      rating: "8.0/10"
    }
  ];

  // Featured movie (first movie from Now Showing)
  const featuredMovie = nowShowing[0];

  return (
    <>
      <Navbar />
      <Banner 
        featuredMovie={featuredMovie} 
        city={city} 
        setCity={setCity} 
        query={query} 
        setQuery={setQuery} 
        handleSearch={handleSearch} 
      />
      <MovieSection sectionTitle="Now Showing" movies={nowShowing} />
      <MovieSection sectionTitle="Coming Soon" movies={comingSoon} />
    </>
  );
}
