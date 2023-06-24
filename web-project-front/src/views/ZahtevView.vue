<template>
    <div>
      <h3>Zahtev za aktivaciju autora</h3>
      <form @submit="submitZahtev">
        <div>
          <label>Email:</label>
          <input type="text" v-model="zahtev.mail" required />
        </div>
        <div>
          <label>Broj Telefona:</label>
          <input type="text" v-model="zahtev.brojTelefona" required />
        </div>
        <div>
          <label>Dodatna Poruka:</label>
          <textarea v-model="zahtev.dodatnaPoruka"></textarea>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </template>

<script>
import axios from 'axios';

export default {
  name: 'ZahtevView',
  name: 'ZahtevView',
  props: ['id'], // Add this line to receive the prop
  data() {
    return {
      zahtev: {
        mail: '',
        brojTelefona: '',
        dodatnaPoruka: '',
        korisnik_id: this.id // Use the prop value to set korisnik_id
      }
    };
  },
  methods: {
    submitZahtev(event) {
      event.preventDefault();

      axios
        .post('http://localhost:9090/api/zahtev-create', this.zahtev, { withCredentials: true })
        .then((response) => {
          console.log(response.data);
          // Handle successful response
        })
        .catch((error) => {
          console.error(error);
          // Handle error
        });
    }
  }
};

</script>
