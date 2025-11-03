import { createContext, useState ,useContext} from "react";
// craete context
const GiftContext = createContext();

//provider function
export const GiftProvider =({children})=>{
    const [surprise,setSurprise]=useState("apple");//game=cricket,captain=dhoni-- destruct is surprise.game and surprise.captain
//custom component
    return(
        <GiftContext.Provider value={{surprise,setSurprise}}>
        {children}
        </GiftContext.Provider>
    )
}
//custom hook
export const useData =()=>useContext(GiftContext)