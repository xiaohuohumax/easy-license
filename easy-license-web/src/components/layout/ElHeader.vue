<template>
  <div class="px-4 py-2 flex items-center">
    <div class="flex-shrink-0 text-2xl">Easy License</div>
    <div class="flex-grow mx-3"></div>
    <div class="flex-shrink-0">
      <n-switch size="medium" @update:value="changeThemea">
        <template #checked-icon> 🌟 </template>
        <template #unchecked-icon> ☀️ </template>
      </n-switch>
    </div>
  </div>
</template>

<script setup>
import { useDefineStore } from "@/store/define";
import { darkTheme, lightTheme } from "naive-ui";

const defineStore = useDefineStore();

const changeThemea = (value) => {
  defineStore.setTheme(!!value ? lightTheme : darkTheme);
};
</script>