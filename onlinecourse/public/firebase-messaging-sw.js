// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here. Other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.1/firebase-messaging.js');

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object
firebase.initializeApp({
    apiKey: "AIzaSyCV-uVrN2M5EAIoZqBiBrXWw-6zHq8TrVE",
    authDomain: "coursewebonline-f529a.firebaseapp.com",
    databaseURL: "https://coursewebonline-f529a-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "coursewebonline-f529a",
    storageBucket: "coursewebonline-f529a.appspot.com",
    messagingSenderId: "958597756638",
    appId: "1:958597756638:web:5ed58883bd18831eb0344f",
    measurementId: "G-NX37WXHX7D"
});

// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
const messaging = firebase.messaging();

messaging.onBackgroundMessage((payload) => {
    console.log(
        '[firebase-messaging-sw.js] Received background message ',
        payload
    );
    // Customize notification here
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: payload.notification.body,
        icon: payload.notification.image,
    };

    self.registration.showNotification(notificationTitle, notificationOptions);
});