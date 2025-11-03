import React from "react";
import { createRoot } from "react-dom/client";
import { GiftProvider } from "./context/ExampleContext";
import Delhi from "./context/Delhi";
import Bangalore from "./context/Bangalore";

function App() {
  return (
    <div>
      <GiftProvider>
        <div>
        <h1>welcome to context demo</h1>
        <Delhi gift="Laptop"/>
        <Bangalore/>
        </div>
      </GiftProvider>
    </div>
  );
}

const root = createRoot(document.getElementById("root"));
root.render(<App />);
