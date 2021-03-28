<template>
  <b-container>
    <b-row>
      <b-col style="text-align: center;justify-content: center;">
        <ul class="flours">
          <li class="water" @click="addWater()">
            <b-badge variant="primary">{{ state.water }}</b-badge>
          </li>
          <li class="txt">
            +
          </li>
          <li class="grain" @click="addGrain()">
            <b-badge variant="warning">{{ state.millet }}</b-badge>
          </li>
          <li class="txt">
            =
          </li>
          <li class="flour" @click="dropFlour()">
            <b-badge variant="primary">{{ state.flour }}</b-badge>
          </li>
        </ul>

        <hr/>

        <div>
          <h3>Water: {{ state.water }}</h3>
          <b-progress :value="state.water" :max="10" animated variant="primary"></b-progress>
          <h3>Energy: {{ state.power }}</h3>

          <b-progress :value="state.power" :max="10" animated variant="warning"></b-progress>
          <h3>Millet: {{ state.millet }}</h3>
          <b-progress :value="state.millet" :max="10" animated :variant="state.flour<10?'info':'danger'"></b-progress>
        </div>
        <div style="display: contents; width: 520px; height: 400px; overflow: hidden; z-index: 10;">
          <b-img :src="millImg()"></b-img>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import {mapGetters, mapActions} from "vuex";

export default {
  computed: mapGetters({
    state: "will/getState"
  }),
  components: {},
  data() {
    return {}
  },
  mounted() {
    this.socket = new WebSocket('ws://localhost:8080/ws')
    this.socket.onopen = () => {
      window.console.log("Socket opened")
    }
    this.socket.onmessage = (msg, ctx) => {
      this.updateData(JSON.parse(msg.data))
    }
    setInterval(this.fetchData, 300)
  },
  fetch(ctx) {
  },
  methods: {
    updateData(data) {
      this.$store.dispatch("will/updateState", data);
    },
    fetchData() {
      this.socket.send(new Date())
    },
    addWater() {
      this.$store.dispatch("will/addWater", 10)
    },
    addGrain() {
      this.$store.dispatch("will/addGrain", 10)
    },
    dropFlour() {
      this.$store.dispatch("will/dropFlour")
    },
    millImg() {
      if (this.state.engine) {
        return "anim.gif";
      } else {
        return "stat.gif";
      }
    }
  }
}
</script>

<style>
body {
  background: #2abd5b;
}

.flours {
  list-style: none;
  display: inline-flex;
}

.flours li {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: #35495e 3px solid;
  margin: 10px;
}

.flours li.txt {
  width: 40px;
  height: 80px;
  line-height: 80px;
  border: none;
  font-size: 200%;
  color: #fff;
}

.flours li.water {
  background: #fff url(/water.png) no-repeat center center/contain;
}

.flours li.grain {
  background: #fff url(/grain.png) no-repeat center center/cover;
}

.flours li.flour {
  background: #fff url(/flour_PNG20.png) no-repeat center center/contain;
}

</style>
