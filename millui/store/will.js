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
    return state.data
  }
}

export const actions = {
  updateState(context, data) {
    context.commit("setState", data)
  },
  async addWater(context, capacity) {
    axios.post('/rest/mill/water/' + capacity).then((res) => {
      window.console.log("water +" + capacity)
    })
  },
  async addGrain(context, capacity) {
    axios.post('/rest/mill/millet/' + capacity).then((res) => {
      window.console.log("millet +" + capacity)
    })
  },
  async dropFlour(context) {
    axios.post('/rest/mill/flour/drop').then((res) => {
      window.console.log("flour is sold")
    })
  }
}
export const mutations = {
  setState(state, obj) {
    state.data = obj
  }
}
