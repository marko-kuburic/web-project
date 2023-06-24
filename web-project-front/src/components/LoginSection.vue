<template>
  <div class="pre-log">
    <div class="container3">
      <section class="login-section">
        <form>
          <div class="row3">
            <h1>Log In</h1>
          </div>
          <div class="row4">
            <input type="email" placeholder="Unesite Vas e-mail" v-model="mail">
          </div>
          <div class="row5">
            <input type="password" placeholder="Unesite Vasu lozinku" v-model="lozinka">
          </div>
          <div class="row6">
            <button @click.prevent="submit">Log in</button>
          </div>
        </form>
      </section>
    </div>
  </div>
</template>
<script>
export default {
  name: 'LoginSection',
  data() {
    return {
      mail: '',
      lozinka: ''
    };
  },
  methods: {
    submit() {
      const payload = {
        mail: this.mail,
        lozinka: this.lozinka
      };

      fetch('http://localhost:9090/api/login', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      })
        .then((res) => {
          console.log(res);
          if (res.ok) {
            return res.json();
          } else {
            throw new Error('Login failed');
          }
        })
        .then((data) => {
          console.log(data);
          localStorage.setItem('user', JSON.stringify(data));
          if (data.uloga === 'CITALAC') {
            this.$router.push('/homeCitalac');
          } else if (data.uloga === 'AUTOR') {
            this.$router.push('/homeAutor');
          } else if (data.uloga === 'ADMINISTRATOR') {
            this.$router.push('/homeAdministrator');
          } else {
            this.$router.push('/');
          }
        })
        .catch((err) => {
          console.log(err);
          alert('Something went wrong!');
        });
    }
  }
};

</script>