<template>
  <v-app>
    <Navbar />
    <v-content>
      <Login />
    </v-content>
    <Footer />
  </v-app>
</template>

<script>
import Login from '@/views/Login.vue'
import Navbar from '@/components/Navbar.vue'
import Footer from '@/components/Footer.vue'
export default {
  name: 'App',
  components: {
    Login,
    Navbar,
    Footer
  }
}
</script>
