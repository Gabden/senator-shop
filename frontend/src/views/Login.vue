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
        value => !!value || 'Необходимо ввести адрес электронной почты',
        value => value.indexOf('@') !== 0 || 'Email должен содержать имя',
        value => value.includes('@') || 'Email должен включать @ символ',
        value =>
          value.indexOf('.') - value.indexOf('@') > 1 ||
          'Email должен содержать имя домена',
        value =>
          value.includes('.') || 'Email должен содержать точку после домена',
        value =>
          value.indexOf('.') <= value.length - 3 ||
          'Email должен содержать доменную зону'
      ],
      passwordRules: [value => !!value || 'Необходимо ввести пароль']
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
