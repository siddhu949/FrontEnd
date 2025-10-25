import { useState, useEffect } from "react";
import { ResCard } from "./ResCard";

const Body = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [allRestaurants, setAllRestaurants] = useState([]);
  const [searchText, setSearchText] = useState("");
  const [pageOffset, setPageOffset] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [loading, setLoading] = useState(false);

  const baseURL =
    "https://www.swiggy.com/dapi/restaurants/list/v5?lat=17.385044&lng=78.486671&is-seo-homepage-enabled=true&page_type=DESKTOP_WEB_LISTING";

  // ✅ Fetch Data Function
  const fetchData = async (offset = 0) => {
    try {
      setLoading(true);
      const res = await fetch(`${baseURL}&offset=${offset}`);
      const data = await res.json();

      const newRestaurants =
        data?.data?.cards[1]?.card?.card?.gridElements?.infoWithStyle
          ?.restaurants || [];

      setAllRestaurants((prev) => [...prev, ...newRestaurants]);
      setRestaurants((prev) => [...prev, ...newRestaurants]);

      // ✅ Detect if there’s more data
      const nextOffset = data?.data?.pageOffset?.nextOffset;
      if (nextOffset !== undefined) {
        setPageOffset(nextOffset);
      } else {
        setHasMore(false);
      }
    } catch (err) {
      console.error("Error fetching:", err);
    } finally {
      setLoading(false);
    }
  };

  // ✅ Initial fetch
  useEffect(() => {
    fetchData();
  }, []);

  // ✅ Scroll listener
  useEffect(() => {
    const handleScroll = () => {
      if (
        window.innerHeight + window.scrollY >=
          document.body.offsetHeight - 100 &&
        !loading &&
        hasMore
      ) {
        fetchData(pageOffset);
      }
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [pageOffset, loading, hasMore]);

  // ✅ Search Filter
  useEffect(() => {
    if (searchText.trim() === "") {
      setRestaurants(allRestaurants);
    } else {
      const filtered = allRestaurants.filter((res) =>
        res.info.name.toLowerCase().includes(searchText.toLowerCase())
      );
      setRestaurants(filtered);
    }
  }, [searchText, allRestaurants]);

  return (
    <>
      <div className="searchingElement">
        <input
          type="text"
          placeholder="Search restaurants..."
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
        />
      </div>

      <div className="movies-grid">
        {restaurants.map((res) => (
          <ResCard key={res.info.id} resObj={res} />
        ))}
      </div>

      {loading && <h4 style={{ textAlign: "center" }}>Loading more...</h4>}
      {!hasMore && (
        <h4 style={{ textAlign: "center" }}>No more restaurants 🍽️</h4>
      )}
    </>
  );
};

export default Body;
