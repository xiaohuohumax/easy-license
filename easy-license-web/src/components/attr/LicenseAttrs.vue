<template>
  <n-space vertical class="w-full">
    <n-card class="text-center cursor-pointer" @click="addAttrItem">
      <n-icon size="140">
        <AddCircleOutline />
      </n-icon>
    </n-card>
    {{licenseAttrs}}
    <LicenseAttrItem
      v-for="(item, index) in licenseAttrs"
      :key="index"
      :index="index"
    />
  </n-space>
</template>

<script setup>
import { provide, ref } from "vue";
import { AddCircleOutline } from "@vicons/ionicons5";
import { useLicenseStore } from "@/store/license";
import { storeToRefs } from "pinia";
const licenseStore = useLicenseStore();

const { attrModel, attrComponentMap } = storeToRefs(licenseStore);

const licenseAttrs = ref([]);
provide("licenseAttrs", licenseAttrs);
const defineAttr = attrComponentMap.value[0];

const addAttrItem = () => {
  licenseAttrs.value.push({
    ...attrModel.value,
    key: `key-${new Date().getTime()}`,
    attrType: !!defineAttr ? defineAttr.value : "",
  });
};
</script>

<style>
</style>