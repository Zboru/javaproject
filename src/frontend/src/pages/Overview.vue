<template>
  <div class="row q-mb-lg">
    <div class="col">
      <h5 class="q-ma-none">Przegląd zajętości cel</h5>
    </div>
  </div>
  <div class="cells-wrapper">
    <CellBox
        v-for="cell in prisonCells"
        :key="cell.id"
        :cell="cell"
    />
  </div>
</template>

<script setup>

import axios from "axios";
import CellBox from "@/components/Overview/CellBox";

const {data: prisonCells} = await axios.get('/api/prisonCells')

</script>

<style scoped>
.cells-wrapper {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(3, 1fr);
  grid-column-gap: 15px;
}
</style>