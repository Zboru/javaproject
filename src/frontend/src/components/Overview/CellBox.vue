<template>
  <div>
    <span>{{cell.cellName}}</span>
    <div class="cell-wrapper">
      <div
          class="box"
          :class="{active: index <= cell.residentsNumber}"
          v-for="index in cell.maxCapacity"
          :key="index"
      />
    </div>
  </div>
</template>
<script setup>
import {defineProps} from "vue";

defineProps({
  inmates: Array,
  cell: Object
})

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

.box.active {
  background: #006600;
}
</style>