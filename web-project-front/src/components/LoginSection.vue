<template>
  <div class="pre-log">
    <div class="container3">
      <section class="login-section">
        <form>
          <div class="row3">
            <h1>Log In</h1>
          </div>
          <div class="row4">
            <input type="text" placeholder="username" v-model="username">
          </div>
          <div class="row5">
            <input type="password" placeholder="password" v-model="password">
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
      username: '',
      password: ''
    };
  },
  methods: {
    submit() {
      const payload = {
        username: this.username,
        password: this.password
      };

      fetch('http://localhost:9092/api/login', {
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
          if (data.role === 'READER') {
            this.$router.push('/homeCitalac');
          } else if (data.role === 'AUTHOR') {
            this.$router.push('/homeAutor');
          } else if (data.role === 'ADMIN') {
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
  <style>
 </style>