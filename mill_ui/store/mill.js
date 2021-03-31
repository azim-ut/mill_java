import axios from "axios";

export const state = () => ({
  data: {
    water: 0,
    millet: 0,
    flour: 0,
    power: 0,
    engine: false,
    machine: false
  }
})

export const getters = {
  getState(state) {
    return state.data;
  }
}

export const actions = {
  updateState(context, data) {
    context.commit("setState", data)
  },
  fetchData(context) {
    axios.get("/rest/mill").then((res) => {
      context.commit("setState", res.data)
    })
  },
  addWater(context, capacity) {
    axios.post("/rest/mill/water/" + capacity).then((res) => {
      window.console.log("Water +" + capacity)
    })
  },
  addMillet(context, capacity) {
    axios.post("/rest/mill/millet/" + capacity).then((res) => {
      window.console.log("Millet +" + capacity)
    })
  },
  dropFlour(context) {
    axios.post("/rest/mill/flour/drop").then((res) => {
      window.console.log("Flour is sold")
    })
  }

}

export const mutations = {
  setState(state, obj) {
    state.data = obj
  }
}
