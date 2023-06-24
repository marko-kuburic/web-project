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
                        <li id="pocetna"><a href="/homeCitalac">Početna</a></li>
                        <li id="pretraga"><a href="/pretragaCitalac">Pretraga</a></li>
                        <li><Logout/></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>
    
    <section class="search-section">
        <h2>Pretraga</h2>
        <form>
          <input type="text" placeholder="Pretraga recenzija">
          <input type="text" placeholder="Pretraga knjiga">
          <input type="text" placeholder="Pretraga korisnika">
          <input type="text" placeholder="Pretraga polica">
          <button type="submit">Pretraži</button>
        </form>
    </section>
    
    <footer>
        <p>&copy; 2023 Nikaread. All rigths reserved.</p>
    </footer>
</template>

<script>
  import Logout from '@/components/Logout.vue';
  
  export default {
    name: 'HomeCitalacView',
    components: {
      Logout
    },
  };
  </script>

<style>
    </style>