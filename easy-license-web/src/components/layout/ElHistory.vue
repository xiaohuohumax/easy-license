<template>
  <div class="p-2" v-show="false">
    <n-tag
      :closable="history.length > 1"
      @close="historyStore.closeHistory(item.id)"
      v-for="(item, index) in history"
      :key="index"
      class="mr-1 mb-1"
      size="small"
    >
      {{ item.name }}
    </n-tag>
  </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useHistoryStore } from "@/store/history";

const historyStore = useHistoryStore();

const { history } = storeToRefs(historyStore);
</script>