<template>
    <div class="pre-reg">
      <div class="container4">
        <section class="register-section">
          <form>
            <div class="row7">
              <h2>Register</h2>
            </div>
            <div class="row8">
              <input type="text" placeholder="name" v-model="name">
            </div>
            <div class="row9">
              <input type="text" placeholder="surname" v-model="surname">
            </div>
            <div class="row10">
              <input type="text" placeholder="username" v-model="username">
            </div>
            <div class="row11">
              <input type="email" placeholder="email" v-model="email">
            </div>
            <div class="row12">
              <input type="password" placeholder="password" v-model="password">
            </div>
            <div class="row13">
              <input type="password" placeholder="repeat password" v-model="password2">
            </div>
            <div class="row14">
            <button @click="register">Register</button>
          </div>
        </form>
      </section>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RegisterSection',
  data() {
    return {
      name: '',
      surname: '',
      username: '',
      email: '',
      password: '',
      password2: ''
    };
  },
  methods: {
    register() {
      const payload = {
        name: this.name,
        surname: this.surname,
        username: this.username,
        email: this.email,
        password: this.password,
        password2: this.password2
      };

      axios
        .post("http://localhost:9092/api/register", payload)
        .then((res) => {
          console.log(res);
          // Emit the 'register' event upon successful registration
          this.$emit('register');
        })
        .catch((err) => {
          console.log(err);
          alert("Something went wrong!");
        });
    }
  }
}
</script>
  
  <style>  </style>