import React from 'react'

import { useData } from './ExampleContext'
const Mumbai=({gift}) =>{
    const{surprise}=useData()
  return (
    <div>
        <h2>my name is {surprise}</h2>
        
        
    </div>
  )
}

export default Mumbai