// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getMessaging, getToken } from "firebase/messaging";

// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCV-uVrN2M5EAIoZqBiBrXWw-6zHq8TrVE",
  authDomain: "coursewebonline-f529a.firebaseapp.com",
  databaseURL: "https://coursewebonline-f529a-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "coursewebonline-f529a",
  storageBucket: "coursewebonline-f529a.appspot.com",
  messagingSenderId: "958597756638",
  appId: "1:958597756638:web:5ed58883bd18831eb0344f",
  measurementId: "G-NX37WXHX7D"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const messaging = getMessaging(app);



export const generateToken = async() => {
  const permission = await Notification.requestPermission();
  console.log(permission);
  if(permission === 'granted'){
    const token = await getToken(messaging,{
      vapidKey:"BLGuPr-bC2SQVrtngxE5EmYXR4K7nVw-EB8Zw7LFGZWr2S1H8FOsTrUVYzGCOgK8iqGwrphSbD6XZYmRGvkqns8"
    });
    console.log(token);
  }

}

