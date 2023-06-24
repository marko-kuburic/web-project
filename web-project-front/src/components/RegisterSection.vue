<template>
    <div class="pre-reg">
      <div class="container4">
        <section class="register-section">
          <form>
            <div class="row7">
              <h2>Register</h2>
            </div>
            <div class="row8">
              <input type="text" placeholder="Unesite Vase ime" v-model="ime">
            </div>
            <div class="row9">
              <input type="text" placeholder="Unesite Vase prezime" v-model="prezime">
            </div>
            <div class="row10">
              <input type="text" placeholder="Unesite Vas username" v-model="korisnickoIme">
            </div>
            <div class="row11">
              <input type="email" placeholder="Unesite Vas e-mail" v-model="mail">
            </div>
            <div class="row12">
              <input type="password" placeholder="Unesite Vasu lozinku" v-model="lozinka">
            </div>
            <div class="row13">
              <input type="password" placeholder="Ponovite lozinku">
            </div>
            <div class="row14">
            <button @click="register">REGISTER</button>
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
      ime: '',
      prezime: '',
      korisnickoIme: '',
      mail: '',
      lozinka: ''
    };
  },
  methods: {
    register() {
      const payload = {
        ime: this.ime,
        prezime: this.prezime,
        korisnickoIme: this.korisnickoIme,
        mail: this.mail,
        lozinka: this.lozinka
      };

      axios
        .post("http://localhost:9090/api/register", payload)
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
  