<template>
  <div>
    <icon name="ni ni-shop" type="success" rounded class="mb-4"> </icon>
    <h6
      class="text-success text-uppercase"
      style="display: inline-block; margin: 10px"
    >
      Store in Map
    </h6>
    <div id="map"></div>
    <div class="map-center">
      <div class="button-group">
        <button
          v-on:click="allStoreList()"
          type="button"
          class="btn btn-1 btn-primary"
          @click="allStoreList"
          style="margin: 10px"
        >
          All
        </button>

        <button
          v-on:click="categoryStoreList('음식점')"
          type="button"
          class="btn btn-1 btn-outline-success"
          style="margin: 10px"
        >
          음식점
        </button>

        <button
          v-on:click="categoryStoreList('편의점')"
          type="button"
          class="btn btn-1 btn-outline-success"
          style="margin: 10px"
        >
          편의점
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('카페')"
          style="margin: 10px"
        >
          카페
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('부동산')"
          style="margin: 10px"
        >
          부동산
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('학원')"
          style="margin: 10px"
        >
          학원
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('유흥시설')"
          style="margin: 10px"
        >
          유흥시설
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('운동시설')"
          style="margin: 10px"
        >
          운동시설
        </button>

        <button
          type="button"
          class="btn btn-1 btn-outline-success"
          @click="categoryStoreList('세탁서비스')"
          style="margin: 10px"
        >
          세탁서비스
        </button>

        <button
          v-on:click="displayMarker([])"
          type="button"
          class="btn btn-1 btn-danger"
          style="margin: 10px"
        >
          APT
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
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";
const aptstoreStore = "aptstoreStore";

export default {
  name: "AptStoreKakaoMap",
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(aptstoreStore, ["aptstore", "aptstores", "aptstoresloc"]),
  },
  created() {
    this.geolng = this.house.lng;
    this.geolat = this.house.lat;
    //console.log("house info", this.house);
    //console.log("aptstore info", this.aptstores);
    //console.log("aptstore info", this.aptstoresloc);
    this.CLEAR_STORE_LIST();
    this.CLEAR_STORE_LOC();
  },

  data() {
    return {
      geolng: "",
      geolat: "",
      category: "",

      pickPosition: [],
      markerStores: [],
      markerPositions2: [[33.452278, 126.567803]],
      markerPositions3: [
        [33.452278, 126.567803],
        [33.452671, 126.574792],
        [33.451744, 126.572441],
      ],
      markers: [],
      myaptmarkers: [],
      infowindow: null,
    };
  },
  props: {
    //house: Object,
    //aptstore: Object,
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
    ...mapActions(aptstoreStore, ["getCategoryList", "getAllList"]),
    ...mapMutations(aptstoreStore, [
      "CLEAR_STORE_LIST",
      "CLEAR_STORE_LOC",
      "CLEAR_STORE_DETAIL",
    ]),

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

    displayMyApt(markerPositions) {
      //console.log("displayMyApt start");

      var imageSrc = "img/happyhouse/houseMarker.png";
      var imageSize = new kakao.maps.Size(35, 50); // 마커이미지의 크기입니다
      var imageOption = { offset: new kakao.maps.Point(17.5, 45) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption,
      );

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position),
      );

      if (positions.length > 0) {
        this.myaptmarkers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
              image: markerImage,
            }),
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds(),
        );

        //console.log(markerPositions);
        this.map.setBounds(bounds);

        this.map.setCenter(
          new kakao.maps.LatLng(this.house.lat, this.house.lng),
        );
        this.map.setLevel(5);
      }
      this.markerClickEvent();
    },

    async displayMarker(markerPositions) {
      // if (this.markers.length > 0) {
      //   this.markers.forEach((marker) => marker.setMap(null));
      // }
      await this.initMap();

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

        await this.map.setBounds(bounds);

        await this.map.setCenter(
          new kakao.maps.LatLng(this.house.lat, this.house.lng),
        );
        await this.map.setLevel(5);
      }
      await this.markerClickEvent();

      await this.displayMyApt([[this.house.lat, this.house.lng]]);
    },

    markerClickEvent() {
      //console.log("click event start");
      var positions = [];
      //console.log(this.aptstores.length);
      for (var j = 0; j < this.aptstores.length; j++) {
        positions.push({
          content: `<div style="padding:5px";>${this.aptstores[j].storename}</div>`,
          latlng: new kakao.maps.LatLng(
            this.aptstores[j].lat,
            this.aptstores[j].lng,
          ),
        });
      }

      for (var i = 0; i < positions.length; i++) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커의 위치
        });

        // 마커에 표시할 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
          content: positions[i].content, // 인포윈도우에 표시할 내용
        });

        // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        kakao.maps.event.addListener(
          marker,
          "mouseover",
          makeOverListener(this.map, marker, infowindow),
        );
        kakao.maps.event.addListener(
          marker,
          "mouseout",
          makeOutListener(infowindow),
        );
      }

      // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
      function makeOverListener(map, marker, infowindow) {
        return function () {
          infowindow.open(map, marker);
        };
      }

      // 인포윈도우를 닫는 클로저를 만드는 함수입니다
      function makeOutListener(infowindow) {
        return function () {
          infowindow.close();
        };
      }
    },

    allStoreList() {
      this.CLEAR_STORE_LIST();
      this.CLEAR_STORE_DETAIL();

      if (this.geolng && this.geolat) {
        this.getAllList({
          lng: this.geolng,
          lat: this.geolat,
        });

        setTimeout(() => {
          this.displayMarker(this.aptstoresloc);
        }, 1000);
      }
    },

    categoryStoreList(c) {
      this.category = c;

      //console.log(this.category, this.geolat, this.geolng);

      this.CLEAR_STORE_LIST();
      this.CLEAR_STORE_LOC();
      this.CLEAR_STORE_DETAIL();

      if (this.geolng && this.geolat && this.category) {
        this.getCategoryList({
          lng: this.geolng,
          lat: this.geolat,
          category: this.category,
        });

        setTimeout(() => {
          this.displayMarker(this.aptstoresloc);
        }, 1000);
      }
    },
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
