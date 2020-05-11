import axios from 'axios'

const apiClient = axios.create({
  baseURL: '/api',
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export default {
  setAuthToken(token) {
    apiClient.defaults.headers.common['Authorization'] = `Bearer ${token}`
  },
  login(credentials) {
    return apiClient.post(`/login`, credentials)
  },
  getOpenData() {
    return apiClient.get('/api/public/manager/hello')
  },
  getAdminData() {
    return apiClient.get('/api/public/admin/hello')
  }
}
