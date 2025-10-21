import { ResCard } from "./ResCard";
import { resObj } from "../util";
import {  useState } from "react";
const Body = () => {
    const [searchText,setSearchText]=useState("");
    const [listOfResturants,setListOfResturants]=useState([]);
  return (
    <>
    <div className="searchingElement">
        <input type ="text" id="searchText1" value={searchText}
        onChange={(e)=>{//setSearchText(e.target.value)}
            // var temp =document.getElementById("searchText1").value;
            // setSearchText(searchText+temp);
            setSearchText(e.target.value)
        }}/>
        <button onClick={(e)=>{//HandleSearch
            //setListOfResturants(resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants);
         
           const resturants=resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants;
           const filitered =resturants.filter((res)=>
        res.info.name.toLowerCase().includes(searchText.toLowerCase()));
           setListOfResturants(filitered);
         console.log("filitered"+filitered);
        }
        
        }>
            Search
        </button>
    </div>
    <div className="movies-grid">
    
      {/* {resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants.map(
        (res) => {
          return <ResCard key={res.info.id} resObj={res} />;
        }
      )} */}
     {(listOfResturants.length > 0
  ? listOfResturants
  : resObj.data.cards[1].card.card.gridElements.infoWithStyle.restaurants
).map((res) => (
  <ResCard key={res.info.id} resObj={res} />
))}

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