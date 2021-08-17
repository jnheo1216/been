import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'
import InfiniteLoading from 'vue-infinite-loading';

const app = createApp(App)
installElementPlus(app)
app.use(store).use(router).use(OpenLayersMap).use(InfiniteLoading, { /* options */ }).mount('#app')