<template>
  <div>
    <span>{{ cellRef.cellName }}</span>
    <div class="cell-wrapper">
      <div
          class="box"
          :class="{[getBoxClass(index)]: true}"
          v-for="(inmate, index) in cellRef.maxCapacity"
          :key="inmate.id"
      />
    </div>
  </div>
</template>
<script setup>
import {defineProps, ref} from "vue";
import axios from "axios";

const props = defineProps({
  cell: Object
})
const cellRef = ref(props.cell);

const {data: inmates} = await axios.get('/api/inmates/byPrisonCell/' + cellRef.value.id);
cellRef.value.inmates = inmates;

function getBoxClass(index) {
  if (cellRef.value.inmates[index]) {
    return 'dangerState-' + cellRef.value.inmates[index].dangerState;
  } else {
    return 'dangerState-none'
  }
}
</script>
<style scoped>
.cell-wrapper {
  width: 250px;
  height: 250px;
  border: 1px solid #333;
  border-radius: 10px;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  grid-column-gap: 15px;
  grid-row-gap: 15px;
  padding: 5px;
}

.box {
  width: 100%;
  height: 100%;
  border-radius: 10px;
  background: #9c9c9c;
}

.box.dangerState-1 {
  background: #006600;
}
.box.dangerState-2 {
  background: #c07728;
}
.box.dangerState-3 {
  background: #b22d1f;
}
</style>