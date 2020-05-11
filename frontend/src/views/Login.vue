<template>
  <v-card width="400" class="mx-auto mt-5">
    <v-card-title class="grey lighten-4">
      <h1 class="display-1 mx-auto">Личный кабинет</h1>
    </v-card-title>
    <p class="red--text text-center mt-3" v-if="error">{{ errorMsg }}</p>
    <v-card-text>
      <v-form @submit.prevent="login" v-model="formValidity">
        <v-text-field
          label="Email"
          prepend-icon="mdi-account-circle"
          v-model="username"
          :rules="usernameRules"
          class="mt-3"
          required
        ></v-text-field>
        <v-text-field
          label="Пароль"
          :type="showPassword ? 'text' : 'password'"
          prepend-icon="mdi-lock"
          :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
          v-model="password"
          :rules="passwordRules"
          @click:append="showPassword = !showPassword"
          class="mb-3"
          required
        ></v-text-field>
        <v-card-actions>
          <v-btn color="success" type="submit" :disabled="!formValidity"
            >Войти</v-btn
          >
          <v-spacer></v-spacer>
          <router-link router to="/">Забыли пароль?</router-link>
        </v-card-actions>
      </v-form>
    </v-card-text>
    <v-divider></v-divider>
    <v-card-actions class="caption grey lighten-4">
      <span>Еще нет аккаунта?</span>
      <v-spacer></v-spacer>
      <router-link router to="/">Зарегистрироваться</router-link>
    </v-card-actions>
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
