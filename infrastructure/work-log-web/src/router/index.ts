import { createRouter, createWebHistory } from "vue-router";

      
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "worker",
      component: () => import("../components/Worker.vue"),
    },
    {
      path: "/work-log",
      name: "worklog",
      component: () => import("../components/WorkLog.vue"),
    },
    {
      path: "/about",
      name: "about",
      component: () => import("../views/AboutView.vue"),
    },
  ],
});

export default router;
