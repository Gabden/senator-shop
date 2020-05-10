import Vue from 'vue'
import VueRouter from 'vue-router'
import Secured from '@/views/Secured.vue'
import SecuredAdmin from '@/views/SecuredAdmin.vue'
import Login from '@/views/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: Login
  },
  {
    path: '/secured',
    name: 'secured',
    component: Secured,
    meta: { requiresAuth: true }
  },
  {
    path: '/admin',
    name: 'admin',
    component: SecuredAdmin,
    meta: { requiresAuth: true, requiresAuthAdmin: true }
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  const loggedIn = localStorage.getItem('user')
  let isAdmin = false
  if (loggedIn) {
    const userData = JSON.parse(loggedIn)
    isAdmin = userData.rolesList.includes('ADMIN')
  }
  if (to.matched.some(record => record.meta.requiresAuthAdmin) && !isAdmin) {
    next('/')
  }
  if (to.matched.some(record => record.meta.requiresAuth) && !loggedIn) {
    next('/')
  } else {
    next()
  }
})

export default router
