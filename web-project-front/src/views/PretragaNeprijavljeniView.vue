<template>
    <header>
        <div class="pre-header">
            <div class="container1">
                <div class="row1">
                    <h1>NIKAREAD</h1>
                </div>
            </div>
        </div>
        <div class="container2">
            <div class="row2">
                <div>
                    <ul class="menu">
                        <li id="pocetna"><a href="/">Prijavi se</a></li>
                        <li id="pocetna"><a href="/pregledNeprijavljeni">Pregled</a></li>
                        <li id="pretraga"><a href="/pretragaNeprijavljeni">Pretraga</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
    
    <section class="search-section">
        <h3>Pretraga</h3>
            <form @submit.prevent="searchKnjige">
                <input type="text" placeholder="Pretraga knjiga" v-model="searchQuery">
                <button @click="searchKnjige">Pretraži</button>
            </form>
    </section>

    <div v-if="searched && knjige.length > 0">
    <div class="knjige-table">
      <table class="center">
        <thead>
          <tr>
            <th>ID</th>
            <th>Naslov</th>
            <th>ISBN</th>
            <th>Broj Strana</th>
            <th>Datum Objavljivanja</th>
            <th>Opis</th>
            <th>Ocena</th>
            <th>Zanr</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="knjiga in knjige" :key="knjiga.id">
            <td>{{ knjiga.id }}</td>
            <td>{{ knjiga.naslov }}</td>
            <td>{{ knjiga.isbn }}</td>
            <td>{{ knjiga.brojStrana }}</td>
            <td>{{ knjiga.datumObjavljivanja }}</td>
            <td>{{ knjiga.opis }}</td>
            <td>{{ knjiga.ocena }}</td>
            <td>{{ knjiga.zanr?.naziv }}</td>
            <td>
                <button @click="seeMore(knjiga.id)">Vidi još</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
    
    <footer>
        <p>&copy; 2023 Nikaread. All rigths reserved.</p>
    </footer>
</template>

<script>
import axios from 'axios';

export default {
  name: 'PretragaNeprijavljeniView',
  
  data() {
    return {
      searchQuery: '',
      knjige: [],
      searched: false
    };
  },
  methods: {
    searchKnjige() {
      axios
        .get(`http://localhost:9090/api/search-knjige/${this.searchQuery}`)
        .then((response) => {
            if (response.data.length == 0) {
                alert('Ne postoji knjiga sa tim imenom');
                this.searched = false; // Set searched to false if knjige array is empty
            } else {
                this.knjige = response.data;
                this.searched = true;
            }
        })
        .catch((error) => {
            console.log(error);
            alert('Ne postoji ta knjiga');
        });
    },
    seeMore(knjigaId) {
        // Redirect to the book page for updating
        this.$router.push(`/knjigaPregled/${knjigaId}`);
        },
  }
};
</script>

<style></style>