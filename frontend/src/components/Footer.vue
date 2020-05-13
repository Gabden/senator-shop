<template>
  <v-footer color="blue-grey darken-4" padless>
    <v-container>
      <v-row>
        <v-col cols="12" md="6">
          <p class="white--text text-center">
            ООО "Сенатор"
          </p>
          <p class="white--text text-center">
            Компания работающая в сегменте <br />
            алкоголя премиум класса с 2005 года
          </p>
          <p class="white--text text-center">
            Мы в социальных сетях
          </p>
          <p class="white--text text-center">
            <a href="https://www.facebook.com/senator24/">
              <v-icon class="blue--text social">mdi-facebook</v-icon>
            </a>
            <a
              class="mx-5"
              href="https://www.instagram.com/senatorwine24/?igshid=1vk88vyuoxkq"
            >
              <v-icon class="pink--text social">mdi-instagram</v-icon>
            </a>
            <a href="https://wa.me/79209551894">
              <v-icon class="green--text social">mdi-whatsapp</v-icon>
            </a>
          </p>
        </v-col>
        <v-col cols="12" md="3">
          <h3 class="text-center grey--text">Быстрые ссылки</h3>
          <router-link
            v-for="link in aboutUs"
            :key="`${link.label}-footer-link`"
            color="white"
            text
            rounded
            class="white--text d-block my-3 text-center"
            :to="link.url"
          >
            {{ link.label }}
          </router-link>
        </v-col>
        <v-col cols="12" md="3">
          <h3 class="text-center grey--text">Свяжитесь с нами</h3>
          <a
            class="blue-grey darken-4 white--text d-block my-3 text-center"
            text
            rounded
            :href="`tel:+79209551894`"
          >
            <v-icon class="white--text mr-1" style="font-size:20px"
              >mdi-phone</v-icon
            >
            <span>+7(920)-955-18-94</span>
          </a>
          <a
            class="blue-grey darken-4 white--text d-block text-center"
            text
            rounded
            :href="`tel:+74912281937`"
          >
            <v-icon class="white--text mr-1" style="font-size:20px"
              >mdi-phone</v-icon
            >
            <span>+7(4912)-28-19-37</span>
          </a>
          <a
            class="blue-grey darken-4 white--text d-block my-3 text-center"
            text
            rounded
            :href="`mailto:senator24a@gmail.com`"
          >
            <v-icon class="white--text mr-1" style="font-size:20px"
              >mdi-email</v-icon
            >
            <span>senator24a@gmail.com</span>
          </a>
          <p class="blue-grey darken-4 white--text text-center">
            <v-icon class="white--text mr-1" style="font-size:20px"
              >mdi-mailbox</v-icon
            >
            <span>390006, г.Рязань, ул.Свободы д.24а</span>
          </p>
        </v-col>
      </v-row>

      <v-row justify="center" no-gutters>
        <v-col cols="12" class="text-center white--text">
          <router-link
            color="white"
            text
            rounded
            class="white--text d-block text-center"
            to="/confidential"
          >
            Пользовательское соглашение
          </router-link>
        </v-col>
        <v-col cols="12" class="text-center white--text">
          <router-link
            color="white"
            text
            rounded
            class="white--text d-block my-3 text-center"
            to="/confidential"
          >
            Политика конфиденциальности
          </router-link>
        </v-col>
        <v-col cols="12" class="text-center grey--text">
          Ограничение по возрасту 18+
        </v-col>
        <v-col cols="12" class="text-center grey--text">
          Все материалы на сайте носят информационный характер и не являются
          рекламой
        </v-col>
        <v-col class="py-4 text-center grey--text" cols="12">
          {{ new Date().getFullYear() }} — <strong>ООО "Сенатор"</strong>
        </v-col>
      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
export default {
  data: () => ({
    aboutUs: [
      {
        label: 'Начало',
        url: '/'
      },
      {
        label: 'Акции',
        url: '/'
      },
      {
        label: 'Корзина',
        url: '/'
      },
      {
        label: 'Корпоративным клиентам',
        url: '/'
      },
      {
        label: 'Контакты',
        url: '/'
      },
      {
        label: 'О нас',
        url: '/'
      }
    ]
  })
}
</script>
<style scoped>
a {
  text-decoration: none;
}
a span {
  text-decoration: underline;
}
.social {
  font-size: 2.5rem;
}
</style>
