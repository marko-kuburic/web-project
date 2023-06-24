<template>
    <button @click="logout">Logout</button>
  </template>
  
  <script>
export default {
  name: 'LogoutButton',
  methods: {
    logout() {
      fetch('http://localhost:9090/api/logout', {
        method: 'POST',
        credentials: 'include'
      })
        .then((res) => {
          if (res.ok) {
            // Clear user information from local storage or session storage
            localStorage.removeItem('user');
            // Redirect to HomeNeprijavljeniView
            this.$router.push('/');
          } else {
            throw new Error('Logout failed');
          }
        })
        .catch((err) => {
          console.log(err);
          alert('Logout failed!');
        });
    }
  }
};
</script>
  