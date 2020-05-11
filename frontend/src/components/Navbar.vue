<template>
  <v-app-bar app dark color="info">
    <v-toolbar-title>Senator</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn text rounded router to="/">Home</v-btn>
    <v-btn text rounded router to="/secured">Secured</v-btn>
    <v-btn text rounded router to="/admin">Only for admin</v-btn>
    <v-btn v-if="loggedIn" text rounded router to="/" @click="logout"
      >Logout</v-btn
    >
    <v-btn v-else text rounded to="/">Login</v-btn>
    <v-btn rounded to="/registration">Регистрация</v-btn>
  </v-app-bar>
</template>

<script>
import { authComputed } from '../store/helpers'
export default {
  methods: {
    logout() {
      this.$store.dispatch('logout')
    }
  },
  computed: {
    ...authComputed
  }
}
</script>

<style></style>
