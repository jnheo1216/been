<template>
  <div class="background">
    <div class="logo">
        <img alt="BEEN LOGO" src="@/assets/image-logo.png">
    </div>

    <ol-map style="width:370px; height:200px;">
        <ol-view
            ref="view"
            :center="center"
            :rotation="rotation"
            :zoom="zoom"
            :projection="projection"
            @zoomChanged="zoomChanged"
            @centerChanged="centerChanged"
            @resolutionChanged="resolutionChanged"
            @rotationChanged="rotationChanged"
            />

        <ol-tile-layer>
        <ol-source-osm />
        </ol-tile-layer>
    </ol-map>

    <div>
        center : {{currentCenter}} zoom : {{currentZoom}} resolution :
        {{currentResolution}} rotation : {{currentRotation}}
    </div>
    <div>
        <el-input placeholder="당신이 원하는 곳" v-model="area"></el-input>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: 'MyPositionAdd',
  data() {
    const center = ref([120, 37]);
    const projection = ref("EPSG:4326");
    const zoom = ref(8);
    const rotation = ref(0);
    return {
      center,
      projection,
      zoom,
      rotation,
      currentCenter: this.center,
      currentZoom: this.zoom,
      currentResolution: this.resolution,
      currentRotation: this.rotation,
      wido: '',
      kyungdo: '',
      area: '',
    };
  },
  methods: {
    zoomChanged(currentZoom) {
      this.currentZoom = currentZoom;
    },
    resolutionChanged(resolution) {
      this.currentResolution = resolution;
    },
    centerChanged(center) {
      this.currentCenter = center;
    },
    rotationChanged(rotation) {
      this.currentRotation = rotation;
    },
  },
}
</script>

<style>

  .background {
    width: 375px;
    height: 812px;
    text-align: center;
    background-color: #FFFAF4;
    margin:0 auto;
  }
</style>