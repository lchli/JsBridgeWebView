// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    component: () => import(/* webpackChunkName: "home" */ '@/views/Home.vue'),
    meta:{
      keepAlive:true
    }
   
  },
  {
    path: '/login',
    component: () => import('@/views/Login.vue'),
  
  },
  {
    path: '/filter',
    component: () => import('@/views/Filter.vue'),
  
   
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
