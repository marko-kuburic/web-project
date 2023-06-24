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
                      <li id="pretraga"><a href="/pretragaCitalac">Pretraga 🔍</a></li>
                      <li><Logout/></li>
                  </ul>
              </div>
          </div>
      </div>
  </header>
  
  <footer>
      <p>&copy; 2023 Nikaread. All rights reserved.</p>
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