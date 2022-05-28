<template>
  <div>
    <icon name="ni ni-building" type="success" rounded class="mb-4"> </icon>
    <h6
      class="text-success text-uppercase"
      style="display: inline-block; margin: 10px"
    >
      Apt in Map
    </h6>
    <div class="float-right">
      <base-button
        tag="a"
        href="#/aptstore"
        type="button"
        size="sm"
        class="btn btn-1 btn-outline-info float-right"
        style="display: inline-block; margin: 10px"
        >More Info : store</base-button
      >
    </div>
    <div id="map"></div>
    <div class="map-center">
      <div class="button-group">
        <button
          v-on:click="[geofind()]"
          type="button"
          class="btn btn-1 btn-outline-primary"
          style="margin: 10px"
        >
          My Location
        </button>
        <button
          v-on:click="[displayMarker([[house.lat, house.lng]])]"
          type="button"
          class="btn btn-1 btn-outline-success"
          style="margin: 10px"
        >
          Mark Apt
        </button>

        <button
          v-on:click="displayMarker([])"
          type="button"
          class="btn btn-1 btn-outline-warning"
          style="margin: 10px"
        >
          Mark clear
        </button>
        <!-- <button
          v-on:click="displayInfoWindow()"
          type="button"
          class="btn btn-1 btn-outline-danger"
        >
          Apt info
        </button> -->
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import vue from "vue";
import VueGeolocationApi from "vue-geolocation-api";

vue.use(VueGeolocationApi);

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  // props: {
  //   lat: String,
  //   lng: String,
  // },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  created() {
    // this.$store.dispatch("getSido");
    this.CLEAR_HOUSE_DETAIL();

    // if (!("geolocation" in navigator)) {
    //   return;
    // }
    // navigator.geolocation.getCurrentPosition(
    //   (pos) => {
    //     this.geolat = pos.coords.latitude;
    //     this.geolng = pos.coords.longitude;
    //   },
    //   (err) => {
    //     alert(err.message);
    //   },
    // );
  },
  data() {
    return {
      geolat: "",
      getlng: "",

      markerPositions1: [[33.452278, 126.567803]],
      markerPositions3: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markerPositions2: [],
      markers: [],
      infowindow: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE_DETAIL"]),
    toHouseLocation() {
      this.$router.push({
        name: "aptstore",
        params: { lat: this.house.lat, lng: this.house.lng },
      });
    },
    geofind() {
      if (!("geolocation" in navigator)) {
        this.textContent = "Geolocation is not available.";
        return;
      }
      this.textContent = "Locating...";

      // get position
      navigator.geolocation.getCurrentPosition((pos) => {
        this.geolat = pos.coords.latitude;
        this.geolng = pos.coords.longitude;
        console.log(
          "Your location data is " + this.geolat + ", " + this.geolng,
        );
        this.displayMarker([[this.geolat, this.geolng]]);
      });
    },

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position),
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            }),
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds(),
        );

        console.log("markerPos", markerPositions);
        this.map.setBounds(bounds);
      }
    },

    // displayInfoWindow() {
    //   if (this.infowindow && this.infowindow.getMap()) {
    //     //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
    //     this.map.setCenter(this.infowindow.getPosition());
    //     return;
    //   }

    //   var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    //   this.infowindow = new kakao.maps.InfoWindow({
    //     map: this.map, // 인포윈도우가 표시될 지도
    //     position: iwPosition,
    //     content: iwContent,
    //     removable: iwRemoveable,
    //   });

    //   this.map.setCenter(iwPosition);
    // },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 800px flex;
  height: 400px;
  justify-content: center;
  margin-left: 40px;
  margin-right: 40px;
}

.button-group-top {
  margin-left: 40px;
  margin-right: 40px;
  margin-bottom: 20px;
}

.button-group {
  margin-left: 30px;
  margin-right: 30px;
  margin-top: 20px;
}

button {
  margin: 0 10px;
}
</style>
