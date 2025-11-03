import React from 'react'
import { useData } from './ExampleContext'
const Bangalore = ({gift}) => {
    const{surprise}=useData()
  return (
    <div>
    <h2>I am bangolre ,<span style={{color:"green"}}>{surprise}recived from context</span></h2>
    </div>
  )
}

export default Bangalore