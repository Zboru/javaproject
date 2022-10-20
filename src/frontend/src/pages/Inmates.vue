<template>
  <div>
    <div class="row q-mb-lg">
      <div class="col">
        <h5 class="q-ma-none">Lista więźniów</h5>
      </div>
      <div class="col flex">
        <q-space/>
        <q-btn color="primary" @click="addModalOpen = true" label="Dodaj więźnia"/>
      </div>
    </div>
    <q-table
        title="Więźniowie"
        :rows="inmatesRef"
        :columns="columns"
        row-key="name"
    >
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn @click="openDetailsModal(props)" flat round size="sm" icon="zoom_in" color="black"/>
          <q-btn @click="openEditModal(props)" flat round size="sm" icon="edit" color="primary"/>
          <q-btn @click="openDeleteModal(props)" flat round size="sm" icon="delete" color="red"/>
        </q-td>
      </template>
    </q-table>
    <EditInmateModal v-model="editModalOpen" :prisonCells="cellsRef" :inmate="selectedInmate" @updated="refetchModel"/>
    <ShowInmateModal v-model="showModalOpen" :inmate="selectedInmate"/>
    <DeleteInmateDialog v-model="deleteModalOpen" :inmate="selectedInmate" @deleted="refetchModel"/>
    <AddInmateModal v-model="addModalOpen" :prisonCells="cellsRef" @created="refetchModel"/>
  </div>
</template>

<script setup>
import axios from 'axios'
import {ref} from "vue";
import AddInmateModal from "@/components/Inmates/AddInmateModal";
import ShowInmateModal from "@/components/Inmates/ShowInmateModal";
import EditInmateModal from "@/components/Inmates/EditInmateModal";
import DeleteInmateDialog from "@/components/Inmates/DeleteInmateDialog";

const {data: inmates} = await axios.get('/api/inmates');
const {data: availableCells} = await axios.get('/api/prisonCells/free')

const inmatesRef = ref(inmates);
const cellsRef = ref(availableCells)

const editModalOpen = ref(false);
const addModalOpen = ref(false);
const deleteModalOpen = ref(false);
const showModalOpen = ref(false);

const selectedInmate = ref();

function openDetailsModal(props) {
  selectedInmate.value = props.row;
  showModalOpen.value = true;
}

function openEditModal(props) {
  selectedInmate.value = props.row;
  editModalOpen.value = true;
}

function openDeleteModal(props) {
  selectedInmate.value = props.row;
  deleteModalOpen.value = true;
}

async function refetchModel() {
  const {data: inmates} = await axios.get('/api/inmates');
  const {data: availableCells} = await axios.get('/api/prisonCells/free')
  inmatesRef.value = inmates
  cellsRef.value = availableCells
}

const columns = [
  {
    name: 'id',
    label: 'ID',
    align: 'left',
    field: row => row.id,
    sortable: true
  },
  {
    name: 'firstname',
    label: 'Imię',
    align: 'left',
    field: row => row.firstname,
    sortable: true
  },
  {
    name: 'lastname',
    label: 'Nazwisko',
    align: 'left',
    field: row => row.lastname,
    sortable: true
  },
  {
    name: 'prisonCell',
    label: 'Przypisana cela',
    align: 'left',
    field: row => row.prisonCell.cellName,
    sortable: true
  },
  {
    name: 'actions',
    label: 'Akcje',
    align: 'left',
    sortable: false
  }
]

</script>
