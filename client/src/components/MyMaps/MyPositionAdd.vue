<template>
  <suspense>
    <template #default>

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

                <ol-overlay :position="currentCenter">
                    <template v-slot="">
                        <div class="overlay-content">
                            <i class="far fa-hand-point-down"></i>Here?
                        </div>
                    </template>
                </ol-overlay>

            </ol-map>

            <div>
                center : {{currentCenter}} zoom : {{currentZoom}} resolution :
                {{currentResolution}} rotation : {{currentRotation}}
            </div>
            <div>
                <el-input placeholder="당신이 원하는 곳" v-model="area"></el-input>
            </div>
            <div>
                <button @click="marking">마크</button>
            </div>
        </div>

    </template>

    <template #fallback>
      <div class="background">Loading...</div>
    </template>
  </suspense>
</template>

<script>
import { ref } from "vue";
import {getArea} from "@/api/user.js"

export default {
  name: 'MyPositionAdd',
  data() {
    const center = ref([127, 37]);
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
    marking() {
        this.wido = this.currentCenter[0]
        this.kyungdo = this.currentCenter[1]
        const info = {
            area: this.area,
            latitude: this.wido,
            longitude: this.kyungdo,
            userId: this.$store.state.user.id
        }
        getArea(
            info,
            (res) => {console.log(res)},
            (err) => {console.error(err)}
        )        
        this.$router.push({
            name: "Feed"
        })
    }
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
  .overlay-content {
    background-color: deeppink;
    color: aliceblue;
  }
</style>