<template>
  <v-card width="400" class="mx-auto mt-5">
    <v-card-title>
      <h1 class="display-1">Login</h1>
    </v-card-title>
    <p class="red--text text-center mt-3" v-if="error">{{ errorMsg }}</p>
    <v-card-text>
      <v-form @submit.prevent="login" v-model="formValidity">
        <v-text-field
          label="Username"
          prepend-icon="mdi-account-circle"
          v-model="username"
          :rules="usernameRules"
          required
        ></v-text-field>
        <v-text-field
          label="Password"
          :type="showPassword ? 'text' : 'password'"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          v-model="password"
          :rules="passwordRules"
          @click:append="showPassword = !showPassword"
          required
        ></v-text-field>
        <v-card-actions>
          <v-btn color="success" type="submit" :disabled="!formValidity"
            >Login</v-btn
          >
          <v-spacer></v-spacer>
          <v-btn color="info">Register</v-btn>
        </v-card-actions>
      </v-form>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  data() {
    return {
      formValidity: false,
      showPassword: false,
      username: '',
      password: '',
      error: false,
      errorMsg: 'Неправильный адрес электронной почты или пароль',
      usernameRules: [
        value => !!value || 'Email is required.',
        value => value.indexOf('@') !== 0 || 'Email should have a username.',
        value => value.includes('@') || 'Email should include an @ symbol.',
        value =>
          value.indexOf('.') - value.indexOf('@') > 1 ||
          'Email should contain a valid domain.',
        value => value.includes('.') || 'Email should include a period symbol.',
        value =>
          value.indexOf('.') <= value.length - 3 ||
          'Email should contain a valid domain extension.'
      ],
      passwordRules: [value => !!value || 'Email is required.']
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
