console.log("hello!, redux");
import {createStore } from Redux;
const initialState{

}
const BUY_CAKE ="BUY_CAKE";

function cakeReducer(state = initialState,action){
    switch(action.type){
        case BUY_CAKE:
            return {
                ...state,
                numOfCakes: state.numOfCake -1,

            };
            default:
                return state
    }

}
const store = createStore(cakeReducer);