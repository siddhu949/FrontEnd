import MultiState from "./multiState";
import React from "react";
import { createRoot } from "react-dom/client";
var reactElement = document.getElementById("root");
var reactRoot = createRoot(reactElement);


const App = () => {
  return (
    <>
    <MultiState />
    </>
  );
};


reactRoot.render(<App />);