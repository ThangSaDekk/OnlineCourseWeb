import axios from "axios"
import cookie from "react-cookies"

const BASE_URL = 'http://localhost:8081'

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
    'instructorCourses' : 'api/instructor/courses'
}

export const authAPIs = () => {
    return axios.create({
        baseURL: BASE_URL,
        headers: {
            'Authorization': cookie.load("access-token")
        }
    })
}

export default axios.create({
    baseURL: BASE_URL
});