import React from "react";
import { FaBars, FaUserCircle } from "react-icons/fa";
import "./index.css";

const HeaderComponent = () => {
  return (
    <header className="header">
      <div className="header-left">
        <FaBars className="header-icon" />
      </div>

      <div className="header-center">
        <input
          type="text"
          className="search-bar"
          placeholder="Search..."
        />
      </div>

      <div className="header-right">
        <FaUserCircle className="user-icon" />
      </div>
    </header>
  );
};

export default HeaderComponent;
