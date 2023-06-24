<template>
    <header>
        <div class="pre-header">
            <div class="container1">
                <div class="row1">
                    NIKAREAD
                </div>
            </div>
        </div>
        <div class="container2">
            <div class="row2">
                <div>
                    <ul class="menu">
                        <li id="pocetna"><a href="/homeCitalac">Početna</a></li>
                        <li id="pretraga"><a href="/pretragaCitalac">Pretraga</a></li>
                        <li>
                            <Logout />
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div v-if="user">
            <h1>{{ user.name }} {{ user.surname }}</h1>
            <p><strong>Korisnicko ime:</strong> {{ user.username }}</p>
            <p><strong>Email:</strong> {{ user.mail }}</p>
            <p><strong>Datum rodjenja:</strong> {{ user.birthDate }}</p>
            <p><strong>Opis:</strong> {{ user.aboutMe }}</p>
            <p><strong>Uloga:</strong> {{ user.role }}</p>

            <h2>Police</h2>
            <ul>
                <li v-for="shelf in user.shelves" :key="shelf.id">
                    <select>
                        <option value="">{{ shelf.name }}</option>
                        <option v-for="shelfItem in shelf.shelfItems" :key="shelfItem.id"
                            :value="shelfItem.book.title">{{ shelfItem.book.title }}</option>
                    </select>
                </li>
            </ul>
        </div>
    </header>

    <footer>
        <p>&copy; 2023 BookBuddy. Sva prava zadržana.</p>
    </footer>
</template>
  
<script>
import Logout from '@/components/Logout.vue';
import axios from 'axios';

export default {
    name: 'HomeCitalacView',
    data() {
        return {
            user: null
        };
    },
    created() {
    this.getUserDetails();
    },
    components: {
        Logout
    },
    methods: {
    getUserDetails() {
      var loggedUser = JSON.parse(localStorage.getItem("user"));
      console.log(loggedUser);
      const id = loggedUser.userId;

      axios
        .get(`http://localhost:9092/api/users/${id}`)
        .then((response) => {
          this.user = response.data;
          console.log(response.data);
        })
        .catch((error) => {
          console.log(error);
          alert('Failed to fetch user details');
        });
    },
  }
};
</script>
  
<style></style>
  