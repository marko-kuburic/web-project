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
                        <li id="pocetna"><a href="/homeAdministrator">Početna</a></li>
                        <li>
                            <Logout />
                        </li>
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
                    <th>Ažuriraj knjigu</th>
                    <th>Obriši knjigu</th>
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
                    <td>{{ knjiga.autor.ime }}</td>
                    <td>{{ knjiga.zanr?.naziv }}</td>
                    <td>
                        <button @click="azurirajKnjigu(knjiga.id)">Ažuriraj knjigu</button>
                    </td>
                    <td>
                    <button @click="obrisiKnjigu(knjiga.id)">Obriši knjigu</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="add-book">
            <div class="container3">
                <section class="add-book">
                    <form>
                        <div class="row5">
                            <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight:bold;">
                                Dodaj novu knjigu</h4>
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite naslov" v-model="knjigaNaslov">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite ISBN" v-model="knjigaIsbn">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite broj strana" v-model="knjigaBrojStrana">
                        </div>
                        <div class="row6">
                            <input type="date" placeholder="Unesite datum" v-model="knjigaDatum">
                        </div>
                        <div class="row6">
                            <input type="text" placeholder="Unesite opis" v-model="knjigaOpis">
                        </div>
                        <div class="row6">
                            <div class="select-wrapper">
                                <select class="custom-select" v-model="knjigaAutor">
                                    <option value="" disabled>Odaberite autora</option>
                                    <option v-for="autor in availableAutors" :value="autor.id" :key="autor.id">{{ autor.ime }} {{ autor.prezime }}</option>
                                </select>
                            </div>
                        </div>




                        <div class="row6">
                            <div class="select-wrapper">
                                <select class="custom-select" v-model="knjigaZanr">
                                    <option value="" disabled>Odaberite žanr</option>
                                    <option v-for="zanr in zanrovi" :value="zanr.id" :key="zanr.id">{{ zanr.naziv }}</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
        <div class="dodaj_knjigu">
            <button @click="dodajKnjigu">Dodaj knjigu</button>
        </div>
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
                    <th>Ažuriraj autora</th>
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
                    <td v-if="korisnik.uloga === 'AUTOR'">
                        <button @click="azurirajAutora(korisnik.id)">Ažuriraj autora</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="container4">
        <section class="add-autor">
            <form>
                <div class="row5">
                    <h4 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight:bold;">
                        Dodaj novog autora</h4>
                </div>
                <div class="row6">
                    <input type="text" placeholder="Unesite Ime" v-model="autorIme">
                </div>
                <div class="row7">
                    <input type="text" placeholder="Unesite prezime" v-model="autorPrezime">
                </div>
                <div class="row8">
                    <input type="text" placeholder="Unesite korisnicko ime" v-model="autorKorisnickoIme">
                </div>
                <div class="row9">
                    <input type="text" placeholder="Unesite mail" v-model="autorMail">
                </div>
                <div class="row10">
                    <input type="text" placeholder="Unesite lozinku" v-model="autorLozinka">
                </div>
            </form>
        </section>
        <div class="dodaj_autora">
            <button @click="dodajAutora">Dodaj autora</button>
        </div>
    </div>

    <div>
        <h3 style="text-align: center; padding-top: 20px; padding-bottom: 10px; font-weight: bold;">
            Zahtevi za aktivaciju
        </h3>
        <div class="zahtevi-table">
        <table class="center">
            <thead>
                <tr>
                    <th>Ime</th>
                    <th>E-mail</th>
                    <th>Telefon</th>
                    <th>Poruka</th>
                    <th>Datum</th>
                    <th>Status</th>
                    <th>Accept</th>
                    <th>Decline</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="zahtev in zahtevi" :key="zahtev.id">
                    <td>{{ zahtev.korisnik.ime }}</td>
                    <td>{{ zahtev.email }}</td>
                    <td>{{ zahtev.telefon }}</td>
                    <td>{{ zahtev.poruka }}</td>
                    <td>{{ zahtev.datum }}</td>
                    <td>{{ zahtev.status }}</td>
                    <td>
                        <button @click="acceptZahtev(zahtev.id)">Accept</button>
                    </td>
                    <td>
                        <button @click="declineZahtev(zahtev.id)">Decline</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
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
        <div class="dodaj_zanr">
            <input type="text" v-model="noviZanrNaziv" placeholder="Unesite naziv zanra">
            <button @click="dodajZanr">Dodaj zanr</button>
        </div>
    </div>

    <footer>
        <p>&copy; 2023 Nikaread. All rigths reserved.</p>
    </footer>
</template>

<script>
import axios from "axios";
import Logout from "@/components/Logout.vue";

export default {
    name: "HomeAdministratorView",
    components: {
        Logout,
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
        this.getZahtevi();
        this.getAutori();
    },
    methods: {
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
        dodajKnjigu() {
        const novaKnjiga = {
            naslov: this.knjigaNaslov,
            naslovnaFotografija: this.naslovnaFotografija,
            isbn: this.knjigaIsbn,
            brojStrana: this.knjigaBrojStrana,
            datumObjavljivanja: this.knjigaDatum,
            opis: this.knjigaOpis,
            autorId: this.knjigaAutor,
            zanrId: this.knjigaZanr
        };
        

            axios
                .post("http://localhost:9090/api/admin/knjiga-add", novaKnjiga, { withCredentials: true })
                .then((response) => {
                    this.getKnjige();
                    this.knjigaNaslov = "";
                    this.naslovnaFotografija = "";
                    this.knjigaIsbn = "";
                    this.knjigaBrojStrana = "";
                    this.knjigaDatum = "";
                    this.knjigaOpis = "";
                    this.knjigaAutor = "";
                    this.knjigaZanr = "";
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to add knjiga");
                });
            },
        obrisiKnjigu(knjigaId) {
            axios
            .delete(`http://localhost:9090/api/admin/${knjigaId}`, { withCredentials: true })
            .then((response) => {
                // Handle the success case, such as displaying a success message or refreshing the list of books
                console.log("Book deleted successfully");
                this.getKnjige(); // Refresh the list of books after deletion
            })
            .catch((error) => {
                // Handle the error case, such as displaying an error message
                console.log(error);
                alert("Failed to delete the book");
            });
        },
        azurirajKnjigu(knjigaId) {
        // Redirect to the book page for updating
        this.$router.push(`/knjigaEdit/${knjigaId}`);
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
        dodajAutora() {
            const noviAutor = {
                ime: this.autorIme,
                prezime: this.autorPrezime,
                korisnickoIme: this.autorKorisnickoIme,
                mail: this.autorMail,
                lozinka: this.autorLozinka,
            };
            
            axios
                .post("http://localhost:9090/api/autor-register", noviAutor, { withCredentials: true })
                .then((response) => {
                    
                    this.getKorisnici();
                    this.autorIme = "";
                    this.autorPrezime = "";
                    this.autorKorisnickoIme = "";
                    this.autorMail = "";
                    this.autorLozinka = "";
                })
                .catch((error) => {
                    console.log(error);
                    alert("Neuspešno dodavanje autora");
                });
 
        },
        azurirajAutora(autorId) {
            this.$router.push(`/autor/${autorId}`);
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
        dodajZanr() {
            if (this.noviZanrNaziv.trim() !== "") {
                const noviZanr = { naziv: this.noviZanrNaziv };

                axios
                    .post("http://localhost:9090/api/admin/zanr-add", noviZanr, { withCredentials: true })
                    .then((response) => {
                        
                        this.getZanrovi();
                        this.noviZanrNaziv = "";
                    })
                    .catch((error) => {
                        console.log(error);
                        alert("Neuspešno dodavanje žanra");
                    });
            } else {
                alert("Unesite naziv žanra");
            }
        },
        getZahtevi() {
            axios
                .get("http://localhost:9090/api/zahtev-getAll", { withCredentials: true })
                .then((response) => {
                    this.zahtevi = response.data;
                })
                .catch((error) => {
                    console.log(error);
                    alert("Failed to fetch zahtevi");
                });
        },
        acceptZahtev(zahtevId) {
            axios
                .post(`http://localhost:9090/api/admin/zahtev/${zahtevId}/accept`, null, { withCredentials: true })
                .then((response) => {
                    alert('Zahtev je prihvaćen.');
                    this.getZahtevi();
                })
                .catch((error) => {
                    console.log(error);
                    alert('Failed to accept the activation request');
                });
            },

        declineZahtev(zahtevId) {
            axios
                .post(`http://localhost:9090/api/admin/zahtev/${zahtevId}/decline`, null, { withCredentials: true })
                .then((response) => {
                    alert('Zahtev je odbijen.');
                    this.getZahtevi();
                })
                .catch((error) => {
                    console.log(error);
                    alert('Failed to decline the activation request');
                });
            },
        },
};
</script>

