<template>
  <v-container>
    <v-card width="600" class="mx-auto mt-5">
      <v-row>
        <v-col>
          <v-card-title cols="12">
            <h1 class="display-1 mx-auto">Регистрация</h1>
            <h3 class="title font-weight-light mx-auto text-justify">
              Необходимо заполнить поля с информацией о себе
            </h3>
          </v-card-title>
        </v-col>
      </v-row>

      <p class="red--text text-center mt-3" v-if="error">{{ errorMsg }}</p>
      <v-card-text>
        <v-form @submit.prevent="login" v-model="formValidity">
          <v-row>
            <v-col cols="12" md="5">
              <v-text-field
                label="Электронная почта "
                prepend-icon="mdi-email"
                v-model="username"
                :rules="usernameRules"
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
                required
              ></v-text-field>
              <v-text-field
                label="Подтвердите пароль"
                :type="showPassword ? 'text' : 'password'"
                prepend-icon="mdi-lock"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                v-model="secondPassword"
                :rules="passwordRules"
                @click:append="showPassword = !showPassword"
                required
              ></v-text-field>
              <vue-tel-input
                v-model="phone"
                placeholder="Номер телефона"
              ></vue-tel-input
            ></v-col>
            <v-spacer></v-spacer>
            <v-col cols="12" md="6">
              <v-text-field
                label="Фамилия"
                prepend-icon="mdi-account"
                :rules="passwordRules"
                required
              ></v-text-field>
              <v-text-field
                label="Имя"
                prepend-icon="mdi-account"
                :rules="passwordRules"
                required
              ></v-text-field>
              <v-text-field
                label="Отчество"
                prepend-icon="mdi-account"
                :rules="passwordRules"
                required
              ></v-text-field>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="12">
              <v-checkbox
                label="Принять 'Пользовательское соглашение' и 'Политику конфиденциальности'"
                v-model="agreeToTerms"
                :rules="agreeToTermsRules"
                required
              ></v-checkbox>
              <p class="caption">
                Ознакомиться с
                <router-link router to="/"
                  >Пользовательским соглашением</router-link
                >
                и
                <router-link router to="/"
                  >Политикой конфиденциальности</router-link
                >
              </p>
            </v-col>
          </v-row>
          <v-divider></v-divider>
          <v-row justify="center">
            <v-col cols="7" sm="5">
              <v-card-actions class="mt-3 mx-auto">
                <v-btn color="success" type="submit" :disabled="!formValidity"
                  >Зарегистрироваться</v-btn
                >
              </v-card-actions>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      phone: {},
      formValidity: false,
      showPassword: false,
      username: '',
      password: '',
      secondPassword: '',
      error: false,
      errorMsg: 'Ошибка регистрации, обратитесь в службу поддержки',
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
      passwordRules: [value => !!value || 'Необходимо заполнить поле'],
      agreeToTerms: false,
      agreeToTermsRules: [
        value =>
          !!value ||
          "Вам необходимо принять 'Пользовательское соглашение' и 'Политику конфиденциальности', чтобы создать аккаунт"
      ]
    }
  },
  methods: {
    login() {
      this.$store
        .dispatch('register', {
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

<style scoped></style>
