import { createRouter, createWebHistory } from 'vue-router';
import HomeNeprijavljeniView from '../views/HomeNeprijavljeniView.vue';
import HomeCitalacView from '../views/HomeCitalacView.vue';
import HomeAutorView from '../views/HomeAutorView.vue';
import HomeAdministratorView from '../views/HomeAdministratorView.vue';
import KnjigaEditView from '../views/KnjigaEditView.vue';
import AutorEditView from '../views/AutorEditView.vue';
import KnjigaPregledView from '../views/KnjigaPregledView.vue';
import KorisnikPregledView from '../views/KorisnikPregledView.vue';
import ZahtevView from '../views/ZahtevView.vue';

import PretragaNeprijavljeniView from '../views/PretragaNeprijavljeniView.vue';
import PretragaCitalacView from '../views/PretragaCitalacView.vue';
import PretragaAutorView from '../views/PretragaAutorView.vue';
import PretragaAdministratorView from '../views/PretragaAdministratorView.vue';

import PregledNeprijavljeniView from '../views/PregledNeprijavljeniView.vue';

import LoginSection from '../components/LoginSection.vue';
import RegisterSection from '../components/RegisterSection.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeNeprijavljeniView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeCitalac',
    name: 'homeCitalac',
    component: HomeCitalacView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeAutor',
    name: 'homeAutor',
    component: HomeAutorView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/homeAdministrator',
    name: 'homeAdministrator',
    component: HomeAdministratorView,
    meta: {
      title: 'Home'
    }
  },
  {
    path: '/pretragaNeprijavljeni',
    name: 'pretragaNeprijavljeni',
    component: PretragaNeprijavljeniView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaCitalac',
    name: 'pretragaCitalac',
    component: PretragaCitalacView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaAutor',
    name: 'pretragaAutor',
    component: PretragaAutorView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/pretragaAdministrator',
    name: 'pretragaAdministrator',
    component: PretragaAdministratorView,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/login',
    name: 'login',
    component: LoginSection,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterSection,
    meta: {
      title: 'Pretraga'
    }
  },
  {
    path: '/knjigaEdit/:id',
    name: 'knjigaEdit',
    component: KnjigaEditView,
    meta: {
      title: 'Knjiga'
    }
  },
  {
    path: '/knjigaPregled/:id',
    name: 'knjigaPregled',
    component: KnjigaPregledView,
    meta: {
      title: 'Knjiga'
    }
  },
  {
    path: '/autor/:id',
    name: 'autor',
    component: AutorEditView,
    meta: {
      title: 'Autor'
    }
  },
  {
    path: '/pregledNeprijavljeni',
    name: 'pregledNeprijavljeni',
    component: PregledNeprijavljeniView,
    meta: {
      title: 'Pregled'
    }
  },
  {
    path: '/korisnikPregled/:id',
    name: 'korisnikPregled',
    component: KorisnikPregledView,
    meta: {
      title: 'Korisnik'
    }
  },
  {
    path: '/zahtev',
    name: 'zahtev',
    component: ZahtevView,
    meta: {
      title: 'Zahtev'
    }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  const pageTitle = to.meta.title || 'Book Buddy'; 
  document.title = pageTitle;
  next();
});

export default router;
