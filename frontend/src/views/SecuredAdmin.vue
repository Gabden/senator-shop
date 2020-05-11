<template>
  <div>
    <h1>This is secured admin section</h1>
    <p>{{ msg }}</p>
  </div>
</template>

<script>
import requestsService from '../services/requestsService'
export default {
  data() {
    return {
      msg: ''
    }
  },
  created() {
    requestsService.getAdminData().then(response => {
      this.msg = response.data
      console.log(response.data)
    })
  }
}
</script>

<style></style>
