<template>
  <v-app>
    <Navbar />
    <v-content>
      <router-view :key="$route.fullPath"></router-view>
    </v-content>
    <Footer />
  </v-app>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import Footer from '@/components/Footer.vue'
export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  }
}
</script>
<style>
.vue-tel-input {
  font-size: 1rem;
  border-top: none !important;
  border-left: none !important;
  border-right: none !important;
  border-bottom: 1px solid #bbb !important;
}
</style>
