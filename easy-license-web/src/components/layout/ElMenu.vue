<template>
  <n-menu :options="menuOptions" accordion />
</template>

<script setup>
import { useMenuStore } from "@/store/menu";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();

const { menuOptions } = storeToRefs(menuStore);
</script>