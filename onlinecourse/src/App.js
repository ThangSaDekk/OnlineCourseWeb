import 'bootstrap/dist/css/bootstrap.min.css';
import Course from './components/Course';
import Footer from './layout/Footer';
import Header from './layout/Header';
import Cart from './components/Cart';
import Profile from './components/Profile';
import { createContext, useEffect, useReducer } from 'react';
import MyCartReducer from './reducers/MyCartReducer';
import MyUserReducer from './reducers/MyUserReducer';
import Payment from './components/Payment';
import Success from './components/Success';
import Failure from './components/Failure';
import CourseContent from './components/CourseContent';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import CourseDetail from './components/CourseDetail';
import Login from './components/Login';
import Register from './components/Register';
import InstructorDrashboard from './components/InstructorDrashboard';
import InstructorCourseEnrollment from './components/InstructorCourseEnrollment';
import TestCourse from './components/TestCourse';
import { generateToken, messaging } from './config/firebaseConfig';
import { onMessage } from 'firebase/messaging';
import toast, { Toaster } from 'react-hot-toast';
import APIs, { endpoints } from './config/APIs';
import cookie from "react-cookies";
import About from './components/About';
export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();


const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [cartCounter, cartDispatch] = useReducer(MyCartReducer, 0);

  const getGoogleAccessToken = async() => {
    try {
      const response = await APIs.get(endpoints['google-access-token']);
      cookie.save("google-access-token", response.data);
    } catch (error) {
      console.error('Error fetching google-access-token:', error);
    }
  }

  useEffect(() => {
    cookie.remove("google-access-token");
    cookie.remove("FCM-token");
    getGoogleAccessToken();
    generateToken();
    onMessage(messaging, (payload) => {
      toast(payload.notification.title + ":\n" + payload.notification.body);
    })
  }, []);

  return (
    <BrowserRouter>
      <MyUserContext.Provider value={user}>
        <MyDispatchContext.Provider value={dispatch}>
          <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
            <Toaster position="top-right" />
            <Header />

            <Container>
              <Routes>
                <Route path='/' element={<Course />} />
                <Route path='/login' element={<Login />} />
                <Route path='/register' element={<Register />} />
                <Route path='/cart' element={<Cart />} />
                <Route path='/profile' element={<Profile />} />
                <Route path='/payment' element={<Payment />} />
                <Route path='/about' element={<About />} />
                <Route path="/success" element={<Success />} />
                <Route path="/failure" element={<Failure />} />
                <Route path="/courses/:courseId/content/:contentId" element={<CourseContent />} />
                <Route path="/courses/:id" element={<CourseDetail />} />
                <Route path="/instructor-dashboard" element={<InstructorDrashboard />} />
                <Route path="/instructor-dashboard/courses/:id" element={<InstructorCourseEnrollment />} />
                <Route path="/test-course/:id" element={<TestCourse />} />
              </Routes>
            </Container>
            <Footer />
          </MyCartContext.Provider>
        </MyDispatchContext.Provider>
      </MyUserContext.Provider>
    </BrowserRouter>
  );
}

export default App;
