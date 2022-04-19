<template>
  <n-config-provider
    :theme-overrides="theme"
    :locale="zhCN"
    :date-locale="dateZhCN"
  >
    <n-message-provider>
      <n-card :bordered="false" class="rounded-none">
        <template #cover>
          <router-view />
        </template>
      </n-card>
    </n-message-provider>
  </n-config-provider>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useDefineStore } from "@/store/define";
import { zhCN, dateZhCN } from "naive-ui";
const { theme } = storeToRefs(useDefineStore());
</script>