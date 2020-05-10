import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import requestsService from '../services/requestsService'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: null
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      state.user = userData
      localStorage.setItem('user', JSON.stringify(userData))
      axios.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${userData.token}`
    },
    CLEAR_USER_DATA() {
      localStorage.removeItem('user')
      location.reload()
    }
  },
  actions: {
    register({ commit }, credentials) {
      return axios.post('/register', credentials).then(({ data }) => {
        commit('SET_USER_DATA', data)
      })
    },
    login({ commit }, credentials) {
      return requestsService.login(credentials).then(response => {
        commit('SET_USER_DATA', response.data)
      })
    },
    logout({ commit }) {
      commit('CLEAR_USER_DATA')
    }
  },
  getters: {
    loggedIn(state) {
      return !!state.user
    },
    isAdmin(state, getters) {
      let isAdmin = false
      if (getters.loggedIn) {
        isAdmin = state.user.rolesList.includes('ADMIN')
      }
      return isAdmin
    }
  }
})
