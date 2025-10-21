export const ResCard = (props) => {
 
  return (
    <div className="card">
      <img
        className="poster"
        src={
          "https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto,w_660/" +
          props.resObj.info.cloudinaryImageId
        }
        height="200"
        width="100"
      />
      <div className="meta">
        <strong>{props.resObj.info.name}</strong>
        <p>{props.resObj.info.locality}</p>
        <p>{props.resObj.info.costForTwo}</p>
        <p>{props.resObj.info.areaName}</p>
        <p>{props.resObj.info.cuisines.join(",")}</p>
      </div>
      <div className="rating">★ {props.resObj.info.avgRating}</div>
    </div>
  );
};