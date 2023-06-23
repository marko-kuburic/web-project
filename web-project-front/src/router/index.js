import { createRouter, createWebHistory } from 'vue-router';
import HomeNeprijavljeniView from '../views/HomeNeprijavljeniView.vue';

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
    path: '/register',
    name: 'register',
    component: RegisterSection,
    meta: {
      title: 'Pretraga'
    }
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

router.beforeEach((to, from, next) => {
  const pageTitle = to.meta.title || 'Nikaread'; 
  document.title = pageTitle;
  next();
});

export default router;
