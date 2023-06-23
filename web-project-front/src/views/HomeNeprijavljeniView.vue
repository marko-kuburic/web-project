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

<LoginSection />
<RegisterSection />

<footer>
    <p>&copy; 2023 Nikaread. All rights reserved.</p>
</footer>
</template>

<script>
import LoginSection from '@/components/LoginSection.vue';
import RegisterSection from '@/components/RegisterSection.vue';

export default {
  components: {
    LoginSection,
    RegisterSection
  }
};
</script>

<style>
* {
    margin: 10px;
}
</style>
