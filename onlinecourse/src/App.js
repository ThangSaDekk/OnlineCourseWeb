import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Course from './components/Course';
import Footer from './layout/Footer';
import Header from './layout/Header';
import CourseDetail from './components/CourseDetail';
// import Services from './components/Services';
// import About from './components/About';
// import Contact from './components/Contact';
import Cart from './components/Cart';
import Profile from './components/Profile';
import { createContext, useReducer } from 'react';
import MyCartReducer from './reducers/MyCartReducer';
import MyUserReducer from './reducers/MyUserReducer';
import Login from './components/Login';
import Payment from './components/Payment';
import Success from './components/Success';
import Failure from './components/Failure';
import CourseContent from './components/CourseContent';


export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);
  const [cartCounter, cartDispatch] = useReducer(MyCartReducer, 0);


  return (

    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>
        <MyCartContext.Provider value={[cartCounter, cartDispatch]}>
          <BrowserRouter>
            <Header />
            <Routes>
              <Route path='/login' element={<Login />} />
              <Route path='/' element={<Course />} />
              <Route path='/courses/:id' element={<CourseDetail />} />
              {/* <Route path='/about' element={<About />} />
              <Route path='/services' element={<Services />} />
              <Route path='/contact' element={<Contact />} /> */}
              <Route path='/cart' element={<Cart />} /> {/* Route cho trang Giỏ Hàng */}
              <Route path='/profile' element={<Profile />} /> {/* Route cho trang Hồ Sơ */}
              <Route path='/payment' element={<Payment />} /> {/* Route cho trang Hồ Sơ */}
              <Route path="/success" element={<Success/>} />
              <Route path="/failure" element={<Failure />} />
              <Route path="/courses/:courseId/content/:contentId" element={<CourseContent />} />
            </Routes>
            <Footer />
          </BrowserRouter>
        </MyCartContext.Provider>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>

  );
}

export default App;
