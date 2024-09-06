import 'bootstrap/dist/css/bootstrap.min.css';
import { createContext, useReducer } from 'react';

import { Container } from 'react-bootstrap';
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

import Header from './layout/Header';
import Footer from './layout/Footer';


import MyUserReducer from './reducers/MyUserReducer';
import Login from './components/Home/Login';
import Register from './components/Home/Register';
import ProtectedRoute from './components/Home/ProtectedRoute';
import InstructorDashboard from './components/Home/InstructorDashboard';
import UserDashboard from './components/Home/UserDashboard';


export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();

const App = () => {
  const [user, dispatch] = useReducer(MyUserReducer, null);

  return (
    <MyUserContext.Provider value={user}>
      <MyDispatchContext.Provider value={dispatch}>

        <BrowserRouter>
          <Header />
          <Container>
            <Routes>
              <Route path='/login' element={<Login />} />
              <Route path='/register' element={<Register />} />
              {/* Route đăng nhập không cần phân quyền */}
              {/* Route cho Instructor, chỉ truy cập được nếu là ROLE_INSTRUCTOR */}
              <Route
                path="/instructor-dashboard"
                element={
                  <ProtectedRoute roles={['ROLE_INSTRUCTOR']}>
                    <InstructorDashboard />
                  </ProtectedRoute>
                }
              />
              {/* Route cho User, chỉ truy cập được nếu là ROLE_USER */}
              <Route
                path="/user-dashboard"
                element={
                  <ProtectedRoute roles={['ROLE_USER']}>
                    <UserDashboard />
                  </ProtectedRoute>
                }
              />
              {/* Điều hướng về trang đăng nhập nếu không có quyền */}
              <Route path="/" element={<Navigate to="/login" />} />
            </Routes>
          </Container>
          <Footer />
        </BrowserRouter>
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>

  );
}

export default App