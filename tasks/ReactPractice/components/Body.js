import { ResCard } from "./ResCard";
import { resObj } from "../util";
import {  useState } from "react";
const Body = () => {
    const [searchText,setSearchText]=useState("");
    const [listOfResturants,setListOfResturants]=useState([]);
    const [allResturants,setAllResturants]=useState([]);
    //boolean operator
    const [dataFetched,setDataFetched]=useState(false);
    const api ="https://www.swiggy.com/dapi/restaurants/list/v5?lat=17.385044&lng=78.486671&is-seo-homepage-enabled=true&page_type=DESKTOP_WEB_LISTING";
    const fetchData =async()=>{
      try{
      const data = await fetch(api);
      const response = await data.json();
      console.log(response);
      const resturants=response?.data?.cards[1]?.card?.card?.gridElements?.infoWithStyle
          ?.restaurants || [];
      // response.data.cards[1].card.card.gridElements.infoWithStyle.restaurants;
      setAllResturants(resturants);
      setListOfResturants(resturants);
      setDataFetched(true);
      }
      catch(err){
        console.error("error fetching resturants"+err);
      }
    };
    if(!dataFetched){
    fetchData();
    }
    const handleSearch =()=>{
      if(searchText.trim()===""){
        setListOfResturants(allResturants);
        return;
      }
        const filitered =allResturants.filter((res)=>
        res.info.name.toLowerCase().includes(searchText.toLowerCase()));
           setListOfResturants(filitered);
         console.log("filitered"+filitered);
    }
     // Reset Functionality
  const handleReset = () => {
    setSearchText("");
    setFilteredList(listOfRestaurants);
  };
      // Top Rated Filter
  const handleTopRated = () => {
    const topRated = listOfResturants.filter((res) => res.rating >= 4.0);
    setFilteredList(topRated);
  };
  
  return (
    <>
    <div className="searchingElement">
        <input type ="text" id="searchText1" value={searchText}
        onChange={(e)=>{//setSearchText(e.target.value)}
            // var temp =document.getElementById("searchText1").value;
            // setSearchText(searchText+temp);
            setSearchText(e.target.value)
            {handleSearch()}
        }}/>
        <button onClick={(e)=>{//HandleSearch
            //setListOfResturants(resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants);
          {handleSearch()};
         
        }
        
        }>
            Search
        </button>
         <button onClick={handleTopRated}>Top Rated</button>
    </div>
   <div className="movies-grid">
        {dataFetched ? (
          listOfResturants.length > 0 ? (
            listOfResturants.map((res) => (
              <ResCard key={res.info.id} resObj={res} />
            ))
          ) : (
            <h3>No restaurants found 😔</h3>
          )
        ) : (
          // fallback to predefined local data until fetch completes
          resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants.map(
            (res) => <ResCard key={res.info.id} resObj={res} />
          )
        )}
      </div>
    {/* // <div className="movies-grid">
    //   {moviesArr.map((movie) => {
    //     return <MovieCard key={movie.id} movieObj={movie} />;
    //   })}
    // </div> */}
    </>
  );
};
export default Body;