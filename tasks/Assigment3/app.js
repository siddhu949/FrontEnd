// import React from "react";
// import { createRoot } from "react-dom/client";
// var reactElement =document.getElementById("root");
// var reactRoot=createRoot(reactElement);
// const headerJSX=(
//     <div className="headers">
//         <p>from the jsx</p>
//         <h1>heading1</h1>
//         <h2>heading2</h2>
//         <h3>heading3</h3>
//     </div>
// );
   
// function Title(){
//     return(
//         <div className="Title">
//            <p>from the functional component</p>
//         <h1>heading1</h1>
//         <h2>heading2</h2>
//         <h3>heading3</h3> 
//         </div>
//     );
// };
// function Title1(props){
//     return(
//         <div className="title1">
//             <h1>{props.heading1}</h1>
//             <h2>{props.heading2}</h2>
           
//         </div>
//     );
// };
// //composition of components
// function Container(){
// return(
//     <div className="container">
//         <Title/>
//         <p>this is inside conatiner component</p>
//     </div>
// );
// };
// const App = () => {
//   return (
//     <>
//    {headerJSX}
//    <Title/>
//    <Title1
//     heading1="dynamic H1"
//     heading2="dynamic H2"
//     />
//     <Container/>
      
//       <HeaderComponent />
//       <main style={{ padding: "20px", color: "#fff" }}>
//         <h2>Welcome to the App!</h2>
//       </main>
    
//     </>
    
//   );
// };
//     // const root = ReactDOM.createRoot(document.getElementById("root"));
//     //   root.render(
//     //     <TitleComponent>
//     //       <div>
//     //         <p>hi this direct component</p>
//     //       </div>
//     //     </TitleComponent>
//     //   );
// reactRoot.render(<App />);
import React from "react";
import { createRoot } from "react-dom/client";
import HeaderComponent from "./HeaderComponent";
import "./index.css";

const headerJSX = (
  <div className="headers">
    <p>from the jsx</p>
    <h1>heading1</h1>
    <h2>heading2</h2>
    <h3>heading3</h3>
  </div>
);

function Title() {
  return (
    <div className="Title">
      <p>from the functional component</p>
      <h1>heading1</h1>
      <h2>heading2</h2>
      <h3>heading3</h3>
    </div>
  );
}

function Title1(props) {
  return (
    <div className="title1">
      <h1>{props.heading1}</h1>
      <h2>{props.heading2}</h2>
    </div>
  );
}

function Container() {
  return (
    <div className="container">
      <Title />
      <p>This is inside the container component</p>
    </div>
  );
}

const App = () => {
  return (
    <>
      <HeaderComponent />
      <main style={{ padding: "20px", color: "#fff" }}>
        <h2>Welcome to the App!</h2>
        {headerJSX}
        <Title />
        <Title1 heading1="dynamic H1" heading2="dynamic H2" />
        <Container />
      </main>
    </>
  );
};

const rootEl = document.getElementById("root");
const reactRoot = createRoot(rootEl);
reactRoot.render(<App />);
