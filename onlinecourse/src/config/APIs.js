import axios from "axios"
import cookie from "react-cookies"

const BASE_URL = 'http://localhost:8081'
// const BASE_URL = 'https://onlinecourse-3j53.onrender.com'

export const endpoints = {
    'register':'api/users',
    'categories': 'api/categories',
    'courses':'api/courses',
    'course-details': (id) => `api/courses/${id}`,
    'content':(id) => `api/courses/${id}/content`, 
    'login': 'api/login',
    'current-user': 'api/current-user',
    'payment': '/payments/pay',
    'enrollment': (id) =>`api/courses/${id}/enrollments`,
    'course-enrollments':(id) => `api/enrollments/${id}`,
    'content-details':(courseId, contentId) => `api/courses/${courseId}/content/${contentId}`,
    'instructorCourses' : 'api/instructor/courses',
    'google-access-token' : 'api/access-token',
    'save-fcm-token': 'api/cfirestore'
}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load("access-token")
        }
    })
}

export const authFCMTOkens = () => {
    return axios.create({
        baseURL: "https://fcm.googleapis.com//v1/projects/coursewebonline-f529a/messages:send",
        headers: {
            'Authorization': `Bearer ${cookie.load("google-access-token")}`
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});