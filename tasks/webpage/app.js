import React from "react";
import { createRoot } from "react-dom/client";

//function (arrow function) or comonent in the react
const HeadingComponent = () =>{
    return (
        <div>
            <h1>heading from react component</h1>
        </div>
    );
};
var root= document.getElementById("mainRoot");
var rootReact=createRoot(root);
rootReact.render(<HeadingComponent/>);