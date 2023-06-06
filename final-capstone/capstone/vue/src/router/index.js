import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Collections from '../views/Collections.vue'
import CollectionDetail from '../views/CollectionDetail.vue';
import Register from '../views/Register.vue'
import store from '../store/index'
import Comic from '../views/Comic.vue'
import ExplorePage from '../views/ExplorePage.vue';
import BulkAddComics from '../components/BulkAdd.vue';
import ImportComics from '../components/ImportComics.vue';


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/collections',
      name: 'collections',
      component: Collections,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/collections/:id',
      name: 'collection-detail',
      component: CollectionDetail,
      meta: {
        requiresAuth: false,
      },
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    // Setting up a single page for each comic based on it's id?
    {
      path: '/comic/:id',
      name: 'comic',
      component: Comic,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/',
      name: 'Explore',
      component: ExplorePage,
    },
    {
      path: '/bulk-add-comics',
      name: 'BulkAddComics',
      component: BulkAddComics,
    },
    {
      path: '/import-comics',
      name: 'ImportComics',
      component: ImportComics,
      meta: {
        requiresAuth: false
      }
    },
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
