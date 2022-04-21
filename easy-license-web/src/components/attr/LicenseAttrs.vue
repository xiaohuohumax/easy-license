<template>
  <n-space vertical class="w-full">
    <n-card class="text-center cursor-pointer" @click="addAttrItem">
      <n-icon size="140">
        <AddCircleOutline />
      </n-icon>
    </n-card>
    <LicenseAttrItem
      v-for="(item, index) in licenseAttrs"
      :key="index"
      :index="index"
    />
  </n-space>
</template>

<script setup>
import { provide, ref, watch, defineEmits, defineProps } from "vue";
import { AddCircleOutline } from "@/sctipt/icon";
import { useLicenseStore } from "@/store/license";
import { storeToRefs } from "pinia";
const licenseStore = useLicenseStore();
const { attrModel, attrComponentMap } = storeToRefs(licenseStore);

const emit = defineEmits();

const props = defineProps({
  value: {
    tyep: Object,
    default: () => ({}),
  },
});

const licenseAttrs = ref([]);

for (const name in props.value) {
  const item = props.value[name];
  licenseAttrs.value.push({
    key: name,
    describe: item.describe,
    attrStr: item.attrStr,
    attrType: item.attrType,
  });
}

provide("licenseAttrs", licenseAttrs);
const defineAttr = attrComponentMap.value[0];

const addAttrItem = () => {
  licenseAttrs.value.unshift({
    ...attrModel.value,
    key: `key-${new Date().getTime()}`,
    attrType: !!defineAttr ? defineAttr.value : "",
  });
};
watch(
  () => licenseAttrs.value,
  () => {
    const atts = {};
    licenseAttrs.value.forEach((item) => {
      atts[item.key] = {
        describe: item.describe,
        attrStr: item.attrStr,
        attrType: item.attrType,
      };
    });
    emit("update:value", atts);
  },
  { deep: true }
);
</script>

<style>
</style>