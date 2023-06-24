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
                      <li id="pocetna"><a href="/homeAutor">Početna</a></li>
                      <li id="pretraga"><a href="/pretragaAutor">Pretraga</a></li>
                      <li><Logout/></li>
                  </ul>
              </div>
          </div>
      </div>
  </header>
  
  <footer>
      <p>&copy; 2023 Nikaread. All rigths reserved.</p>
  </footer>
  </template>
  
  <script>
  import Logout from '@/components/Logout.vue';
  
  export default {
    name: 'HomeAutorView',
    components: {
      Logout
    },
  };
  </script>
  
  <style>
  </style>
  