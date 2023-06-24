<template>
    <div>
      <h1>Edit Autor</h1>
      <form @submit.prevent="updateAutor">
        <div>
          <label for="ime">Ime:</label>
          <input type="text" id="ime" v-model="updateDto.ime" required>
        </div>
        <div>
          <label for="prezime">Prezime:</label>
          <input type="text" id="prezime" v-model="updateDto.prezime" required>
        </div>
        <div>
          <label for="naslovnaFotografija">Naslovna Fotografija:</label>
          <input type="text" id="naslovnaFotografija" v-model="updateDto.naslovnaFotografija">
        </div>
        <div>
          <label for="opis">Opis:</label>
          <input type="text" id="opis" v-model="updateDto.opis">
        </div>
        <div>
          <label for="datumRodjenja">Datum Rodjenja:</label>
          <input type="date" id="datumRodjenja" v-model="updateDto.datumRodjenja" required>
        </div>
        <div>
          <label for="mail">Mail:</label>
          <input type="email" id="mail" v-model="updateDto.mail" required>
        </div>
        <div>
          <label for="lozinka">Lozinka:</label>
          <input type="password" id="lozinka" v-model="updateDto.lozinka" required>
        </div>
        <div>
          <label for="newlozinka">Nova Lozinka:</label>
          <input type="password" id="newlozinka" v-model="updateDto.newlozinka">
        </div>
        <div>
          <button type="submit">Update Autor</button>
        </div>
      </form>
      <p>{{ message }}</p>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        updateDto: {
          ime: '',
          prezime: '',
          naslovnaFotografija: '',
          opis: '',
          datumRodjenja: '',
          mail: '',
          lozinka: '',
          newlozinka: ''
        },
        message: ''
      };
    },
    created() {
      this.getAutorDetails();
    },
    methods: {
      getAutorDetails() {
        const autorId = this.$route.params.id;
  
        axios.get(`http://localhost:9090/api/korisnici/${autorId}`)
          .then(response => {
            const autor = response.data;
            this.updateDto.ime = autor.ime;
            this.updateDto.prezime = autor.prezime;
            this.updateDto.naslovnaFotografija = autor.naslovnaFotografija;
            this.updateDto.opis = autor.opis;
            this.updateDto.datumRodjenja = autor.datumRodjenja;
            this.updateDto.mail = autor.mail;
            // Exclude lozinka and newlozinka properties since they should not be pre-filled for security reasons
          })
          .catch(error => {
            console.error('Failed to fetch author details:', error);
            alert('Failed to fetch author details');
          });
      },
      getAutori() {
        axios
            .get('http://localhost:9090/api/korisnici', { withCredentials: true })
            .then((response) => {
            this.korisnici = response.data;
            this.availableAutors = response.data.filter((korisnik) => korisnik.uloga === 'AUTOR');

            if (this.knjiga && this.knjiga.autor) {
                this.updatedAutor = this.knjiga.autor.id;
            }
            })
            .catch((error) => {
            console.log(error);
            alert('Failed to fetch korisnici');
            });
        },

      updateAutor() {
        const autorId = this.$route.params.id;
  
        axios.put(`http://localhost:9090/api/admin/update_autor/${autorId}`, this.updateDto)
          .then(response => {
            console.log('Author updated successfully');
            // Redirect to the updated author page or any other desired route
            this.$router.push(`/homeAdministrator`);
          })
          .catch(error => {
            console.error('Failed to update author details:', error);
            alert('Failed to update author details');
          });
      }
    }
  };
  </script>
  