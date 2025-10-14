import React, { useState } from "react";

export default function App() {
  const nowShowing = [
    { title: "Spider-Man: No Way Home", img: "https://tse2.mm.bing.net/th/id/OIP.jFWga73Vye9w2gQvqWJX6gHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3", desc: "Action, 2h 28m" },
    { title: "Avatar: The Way of Water", img: "https://tse4.mm.bing.net/th/id/OIP.73SG9-j2EMH3k-ekDwfqzwHaLH?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3", desc: "Adventure, 3h 12m" },
    { title: "Black Panther: Wakanda Forever", img: "https://th.bing.com/th/id/OIP.rnMklcBwV1xZl5ndF5sTSAHaK-?w=205&h=304&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Action, 2h 41m" },
    { title: "The Batman", img: "https://th.bing.com/th/id/OIP.ORrLW2ur-82K0mQ2QeH1jgHaK-?w=117&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Crime, 2h 56m" },
    { title: "Doctor Strange: Multiverse of Madness", img: "https://th.bing.com/th/id/OIP.n7o8B0-d6P4-r3150OWN5AHaK9?w=205&h=304&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Fantasy, 2h 6m" }
  ];

  const comingSoon = [
    { title: "Jurassic World: Dominion", img: "https://th.bing.com/th/id/OIP.NsVJ8Tg22sXq3mjtsaTowgHaKe?w=117&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Adventure, 2h 27m" },
    { title: "Guardians of the Galaxy Vol.3", img: "https://th.bing.com/th/id/OIP.4E32Anj4RLBR4T3KZGh9cgHaLH?w=204&h=306&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Action, 2h 30m" },
    { title: "Mission Impossible: Dead Reckoning", img: "https://th.bing.com/th/id/OIP.qrICVBnT6qb3GAyU2HdDkwHaJv?w=128&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Action, 2h 25m" },
    { title: "The Flash", img: "https://th.bing.com/th/id/OIP.lXzCvrnInawFeoJW9JUIBAHaLH?w=115&h=180&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Action, 2h 20m" },
    { title: "Ant-Man and the Wasp: Quantumania", img: "https://th.bing.com/th/id/OIP.0LOcwcHglyOwFYhBgBwoFQHaK-?w=125&h=185&c=7&r=0&o=7&cb=12&dpr=1.3&pid=1.7&rm=3", desc: "Adventure, 2h 5m" }
  ];

  const [city, setCity] = useState("");
  const [query, setQuery] = useState("");

  const handleSearch = () => {
    alert(`Searching for "${query}" in ${city || "All Cities"}...`);
  };

  const renderMovies = (movies) =>
    movies.map((movie, index) => (
      <div className="movie-card" key={index}>
        <img src={movie.img} alt={movie.title} />
        <div className="movie-info">
          <h3>{movie.title}</h3>
          <p>{movie.desc}</p>
        </div>
      </div>
    ));

  return (
    <div>
      <nav className="navbar">
        <h1>MovieBook</h1>
        <ul>
          <li><a href="#now-showing">Now Showing</a></li>
          <li><a href="#coming-soon">Coming Soon</a></li>
          <li><a href="#">Profile</a></li>
        </ul>
      </nav>

      <div className="banner">
        <h2>Find Movies, Theaters & More</h2>
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

      <div className="section" id="now-showing">
        <h2>Now Showing</h2>
        <div className="movies">{renderMovies(nowShowing)}</div>
      </div>

      <div className="section" id="coming-soon">
        <h2>Coming Soon</h2>
        <div className="movies">{renderMovies(comingSoon)}</div>
      </div>
    </div>
  );
}
