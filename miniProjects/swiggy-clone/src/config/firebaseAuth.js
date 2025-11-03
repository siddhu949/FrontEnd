import { initializeApp } from "firebase/app";

import { getAuth, GoogleAuthProvider } from "firebase/auth";


const firebaseConfig = {
  apiKey: "AIzaSyA1MZXFZxA-xwTTaIc4Uw6jeGKf4Jl9Iw8",
  authDomain: "swiggy-project-8b502.firebaseapp.com",
  projectId: "swiggy-project-8b502",
  storageBucket: "swiggy-project-8b502.firebasestorage.app",
  messagingSenderId: "989475263112",
  appId: "1:989475263112:web:333637e2be94469708c53d"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

const auth = getAuth(app);
    
const provider = new GoogleAuthProvider();

export { auth, provider };

