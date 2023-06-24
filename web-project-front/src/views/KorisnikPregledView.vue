<template>
  <div v-if="korisnik">
    <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
    <p><strong>Korisnicko ime:</strong> {{ korisnik.korisnickoIme }}</p>
    <p><strong>Email:</strong> {{ korisnik.mail }}</p>
    <p><strong>Datum rodjenja:</strong> {{ korisnik.datumRodjenja }}</p>
    <p><strong>Profilna slika:</strong> {{ korisnik.profilnaSlika }}</p>
    <p><strong>Opis:</strong> {{ korisnik.opis }}</p>
    <p><strong>Uloga:</strong> {{ korisnik.uloga }}</p>

    <h2>Police</h2>
    <ul>
      <li v-for="polica in korisnik.policas" :key="polica.id">
        <select>
          <option value="">{{polica.naziv}}</option>
          <option v-for="stavkaPolice in polica.stavkePolica" :key="stavkaPolice.id" :value="stavkaPolice.knjiga.naslov">{{ stavkaPolice.knjiga.naslov }}</option>
        </select>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'KorisnikView',
  data() {
    return {
      korisnik: null
    };
  },
  created() {
    this.getKorisnikDetails();
  },
  methods: {
    getKorisnikDetails() {
      const korisnikId = this.$route.params.id;

      axios
        .get(`http://localhost:9090/api/korisnici/${korisnikId}`)
        .then((response) => {
          this.korisnik = response.data;
          this.getPolicasOfKorisnik(); // Fetch policas after getting korisnik details
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch user details');
        });
    },
    getPolicasOfKorisnik() {
      const korisnikId = this.korisnik.id; // Use this.korisnik instead of this.knjiga.korisnik

      axios
        .get(`http://localhost:9090/api/korisnici/${korisnikId}/police`)
        .then((response) => {
          const police = response.data;
          this.korisnik.policas = police; // Assign police data to this.korisnik.policas
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch user policas');
        });
    },
  }
};
</script>
