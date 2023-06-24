<template>
  <header>
    <div class="pre-header">
        <div class="container1">
        </div>
            <h1>
                NIKAREAD
            </h1>
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
    <p>&copy; 2023 Nikaread. All rigths reserved</p>
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
    margin: 10;
    padding: 5;
}

.pre-header {
    background-color: rgb(25, 0, 252);
    height: 190px;
}

.menu {
    display: flex;
    justify-content:space-around;
    list-style: none;
    padding: 0;
  }
  
a{
    font-weight: bold;
    font-style: italic;
    font-size: 15px;
    color: rgb(188, 32, 32);
}

.container2 {
    background-color: rgb(0, 102, 255);
    height: 40px;
    padding-top: 8px;
}

.container1 .row1 {
    display: flex;
    justify-content: center;
    align-items: center;
}
  
.container1 .col-md-4 {
    flex: 1;
}

.imagelogo img {
    height: 90px;
}

.container1 .col-md-4:nth-child(2) img {
    display: block;
    margin: 0 auto;
}

.imageheader1 img {
    opacity: 0.75;
    padding-top: 15px;
}

.imageheader2 {
    opacity: 0.75;
    padding-top: 15px;
}

.pre-log {
    display: flex;
    justify-content: center;
    align-items: center;
}

.pre-reg {
    display: flex;
    justify-content: center;
    align-items: center;
}

h1 {
    font-size: 60px;
    margin-left: 15px;
    font-weight: bold;
}

h2 {
    font-size: 60px;
    margin-left:15px;
    font-weight: bold;
}

.container2 button{
    font-weight: bold;
    font-style: 8px;
    font-size: 15px;
    color: rgb(188, 32, 32);
}

.row4 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
}

.row5 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    text-align: center;
}

.row6 button{
    background-color:rgb(0, 102, 255);
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    margin-bottom: 20px;
    margin-left: 43px;
    border-radius: 8px;
}

.row8 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row9 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row10 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row11 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row12 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row13 input {
    border: 2px solid rgb(188, 32, 32);
    border-radius: 8px;
    margin-bottom: 20px;
    margin-left: 25px;
    text-align: center;
}

.row14 button{
    background-color:rgb(0, 102, 255);
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    margin-bottom: 20px;
    margin-left: 28px;
    border-radius: 8px;
}

footer {
    text-align: center;
    margin-top: 40px;
    font-weight: bold;
}
</style>
