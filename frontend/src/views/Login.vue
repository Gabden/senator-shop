<template>
  <v-card width="400" class="mx-auto mt-5">
    <v-card-title>
      <h1 class="display-1">Login</h1>
    </v-card-title>
    <p class="red--text text-center mt-3" v-if="error">{{ errorMsg }}</p>
    <v-card-text>
      <v-form>
        <v-text-field
          label="Username"
          prepend-icon="mdi-account-circle"
          v-model="username"
        ></v-text-field>
        <v-text-field
          label="Password"
          :type="showPassword ? 'text' : 'password'"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          v-model="password"
          @click:append="showPassword = !showPassword"
        ></v-text-field>
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions>
      <v-btn color="success" @click="login">Login</v-btn>
      <v-spacer></v-spacer>
      <v-btn color="info">Register</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      showPassword: false,
      username: '',
      password: '',
      error: false,
      errorMsg: 'Неправильный адрес электронной почты или пароль'
    }
  },
  methods: {
    login() {
      this.$store
        .dispatch('login', {
          username: this.username,
          password: this.password
        })
        .then(() => {
          this.$router.push({ name: 'secured' })
        })
        .catch(err => {
          this.error = true
        })
    }
  }
}
</script>

<style></style>
