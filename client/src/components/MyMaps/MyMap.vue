<template>
  <div>
    <div>{{ visitedData }}</div>
    <ol-map :loadTilesWhileAnimating="true" :loadTilesWhileInteracting="true" style="width: 375px; height:200px">

        <ol-view ref="view" :center="center" :rotation="rotation" :zoom="zoom" 
        :projection="projection" />

        <ol-tile-layer>
            <ol-source-osm />
        </ol-tile-layer>


        <div v-for="visitedData in visitedDatas" :key="visitedData">
            <ol-overlay :position="visitedData">
                <template v-slot="">
                    <div class="overlay-content">
                        *<i class="fab fa-forumbee"></i>*<br>
                        <!-- Position: {{ slotProps.position }} -->
                    </div>
                </template>
            </ol-overlay>
        </div>
        
        
    </ol-map>
    <!-- <div>center : {{ center }}, zoom : {{ zoom }} </div> -->

  </div>
</template>

<script>
import {ref} from 'vue'

export default {
  name: 'MyMap',
  data() {
    const center = ref([127, 37])
    const projection = ref('EPSG:4326')
    const zoom = ref(6)
    const rotation = ref(0)
    return {
        center,
        projection,
        zoom,
        rotation
    }
  },
  props: {
    visitedDatas: Array
  }
}
</script>

<style>

</style>