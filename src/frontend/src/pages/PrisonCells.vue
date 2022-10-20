<template>
  <div>
    <div class="row q-mb-lg">
      <div class="col">
        <h5 class="q-ma-none">Lista cel więziennych</h5>
      </div>
      <div class="col flex">
        <q-space/>
        <q-btn color="primary" @click="addModalOpen = true" label="Dodaj celę"/>
      </div>
    </div>
    <q-table
        title="Cele"
        :rows="cellsRef"
        :columns="columns"
        row-key="name"
    >
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn @click="openEditModal(props)" flat round size="sm" icon="edit" color="primary"/>
          <q-btn @click="openDeleteModal(props)" flat round size="sm" icon="delete" color="red"/>
        </q-td>
      </template>
    </q-table>
    <AddPrisonCellModal v-model="addModalOpen" @created="refetchModel"/>
    <EditPrisonCellModal :prison-cell="selectedPrisonCell" v-model="editModalOpen" @updated="refetchModel"/>
    <DeletePrisonCellModal :prison-cell="selectedPrisonCell" v-model="deleteModalOpen" @deleted="refetchModel"/>
  </div>
</template>

<script setup>
import axios from 'axios'
import AddPrisonCellModal from "@/components/PrisonCells/AddPrisonCellModal";
import {ref} from "vue";
import EditPrisonCellModal from "@/components/PrisonCells/EditPrisonCellModal";
import DeletePrisonCellModal from "@/components/PrisonCells/DeletePrisonCellModal";

const {data: prisonCells} = await axios.get('/api/prisonCells');

const columns = [
  {
    name: 'id',
    label: 'ID',
    align: 'left',
    field: row => row.id,
    sortable: true
  },
  {
    name: 'cellName',
    label: 'Nazwa celi',
    align: 'left',
    field: row => row.cellName,
    sortable: true
  },
  {
    name: 'capacity',
    label: 'Pojemność',
    align: 'left',
    field: row => row.maxCapacity,
    sortable: true
  },
  {
    name: 'residents_number',
    label: 'Liczba więźniów',
    align: 'left',
    field: row => row.residentsNumber,
    sortable: true
  },
  {
    name: 'actions',
    label: 'Akcje',
    align: 'left',
    sortable: false
  }
]

const cellsRef = ref(prisonCells)

const editModalOpen = ref(false);
const addModalOpen = ref(false);
const deleteModalOpen = ref(false);

const selectedPrisonCell = ref();

function openEditModal(props) {
  selectedPrisonCell.value = props.row;
  editModalOpen.value = true;
}

function openDeleteModal(props) {
  selectedPrisonCell.value = props.row;
  deleteModalOpen.value = true;
}

async function refetchModel() {
  const {data: prisonCells} = await axios.get('/api/prisonCells')
  cellsRef.value = prisonCells
}
</script>
