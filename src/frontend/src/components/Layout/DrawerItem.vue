<template>
  <q-item @click="emit('click')" clickable tag="router-link" :to="props.link" :active="props.label === 'Outbox'" v-ripple>
    <q-item-section avatar>
      <q-icon :name="props.icon" />
    </q-item-section>
      <q-item-section>
        {{ props.label }}
      </q-item-section>
  </q-item>
  <q-separator :key="'sep' + index"  v-if="props.separator" />
</template>

<script setup>
import {defineProps, defineEmits} from 'vue'
const emit = defineEmits(['click'])
const props = defineProps({
  label: String,
  icon: String,
  separator: Boolean,
  link: String
})
const index = Math.floor(Math.random() * 1234)
</script>