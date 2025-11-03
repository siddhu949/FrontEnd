import { createContext, useState ,useContext} from "react";
const GiftContext = createContext();


export const GiftProvider =({children})=>{
    const [surprise,setSurprise]=useState("apple");//game=cricket,captain=dhoni-- destruct is surprise.game and surprise.captain

    return(
        <GiftContext.Provider value={{surprise,setSurprise}}>
        {children}
        </GiftContext.Provider>
    )
}
export const useData =()=>useContext(GiftContext)