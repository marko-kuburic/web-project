<template>
    <header>
        <div class="pre-header">
            <div class="container1">
                <div class="row1">
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
    <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight:bold;">
        Knjige
    </h3>
    <div class="knjige-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Naslov</th>
                    <th>ISBN</th>
                    <th>Broj Strana</th>
                    <th>Datum Objavljivanja</th>
                    <th>Opis</th>
                    <th>Ocena</th>
                    <th>Autor</th>
                    <th>Zanr</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="knjiga in knjige" :key="knjiga.id">
                    <td>{{ knjiga.naslov }}</td>
                    <td>{{ knjiga.isbn }}</td>
                    <td>{{ knjiga.brojStrana }}</td>
                    <td>{{ knjiga.datumObjavljivanja }}</td>
                    <td>{{ knjiga.opis }}</td>
                    <td>{{ knjiga.ocena }}</td>
                    <td>{{ knjiga.autor.prezime}}</td>
                    <td>{{ knjiga.zanr?.naziv }}</td>
                    <td>
                        <button @click="seeMoreKnjiga(knjiga.id)">Vidi još</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold;">
        Korisnici
    </h3>
    <div class="korisnici-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Ime</th>
                    <th>Prezime</th>
                    <th>Korisnicko Ime</th>
                    <th>Datum Rodjenja</th>
                    <th>Opis</th>
                    <th>Uloga</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="korisnik in korisnici" :key="korisnik.id">
                    <td>{{ korisnik.ime }}</td>
                    <td>{{ korisnik.prezime }}</td>
                    <td>{{ korisnik.korisnickoIme }}</td>
                    <td>{{ korisnik.datumRodjenja }}</td>
                    <td>{{ korisnik.opis }}</td>
                    <td>{{ korisnik.uloga }}</td>
                    <td>
                        <button @click="seeMoreKorisnik(korisnik.id)">Vidi još</button>
                    </td>
                    <td v-if="korisnik.uloga === 'AUTOR'">
                        <ZahtevView :id="korisnik.id"></ZahtevView>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold;">
        Zanrovi
    </h3>
    <div class="zanrovi-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Naziv</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="zanr in zanrovi" :key="zanr.id">
                    <td>{{ zanr.naziv }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <footer>
        <p>&copy; 2023 Nikaread. All rigths reserved.</p>
    </footer>
</template>

<script>
import axios from "axios";
import ZahtevView from './ZahtevView.vue';


export default {
    name: "PregledNeprijavljeniView",
    components: {
        ZahtevView,
    },
    data() {
        return {
            knjige: [],
            korisnici: [],
            zanrovi: [],
            zahtevi: [],
            availableAutors: [],
            autorIme: '',
            autorPrezime: '',
            autorMail: '',
            autorKorisnickoIme: '',
            autorLozinka: '',
        };
    },
    mounted() {
        this.getKnjige();
        this.getKorisnici();
        this.getZanrovi();
        this.getAutori();
    },
    methods: {
        podnesiZahtev(korisnikId) {
        this.$router.push({ path: '/zahtev', params: { id: korisnikId } });
        },
        seeMoreKnjiga(knjigaId) {
            this.$router.push(`/knjigaPregled/${knjigaId}`);
        },
        seeMoreKorisnik(korisnikId) {
            this.$router.push(`/korisnikPregled/${korisnikId}`);
        },
        getKnjige() {
            axios
                .get("http://localhost:9090/api/knjige", { withCredentials: true })
                .then((response) => {
                    this.knjige = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch knjige");
                });
        },
        getKorisnici() {
            axios
                .get("http://localhost:9090/api/korisnici", { withCredentials: true })
                .then((response) => {
                    this.korisnici = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch korisnici");
                });
        },
        getAutori() {
            axios
                .get("http://localhost:9090/api/korisnici", { withCredentials: true })
                .then((response) => {
                    this.korisnici = response.data;
                    this.availableAutors = response.data.filter((korisnik) => korisnik.uloga === "AUTOR");
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch korisnici");
                });
        },
        getZanrovi() {
            axios
                .get("http://localhost:9090/api/zanrovi", { withCredentials: true })
                .then((response) => {
                    this.zanrovi = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch zanrovi");
                });
        },
        
        },
};
</script>

<style>
</style>
