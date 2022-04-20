<template>
  <n-card>
    <n-icon
      size="30"
      class="absolute top-2 right-2 cursor-pointer z-20"
      @click="onAttrRemove"
    >
      <CloseCircleOutline />
    </n-icon>
    <n-space vertical class="w-full">
      <n-input
        type="text"
        placeholder="参数KEY"
        v-model:value="licenseAttrs[props.index].key"
      />
      <n-select
        v-model:value="licenseAttrs[props.index].attrType"
        :options="attrComponentMap"
        @update:value="onSelectUpdate"
      />
      <n-input
        type="text"
        placeholder="参数描述"
        v-model:value="licenseAttrs[props.index].describe"
      />
      <component
        :is="componentItem.name"
        v-if="componentItem.isExist"
        v-model:value="licenseAttrs[props.index].attrStr"
      />
      <n-input
        v-else
        type="text"
        :placeholder="`组件[${componentItem.label}]不存在`"
        disabled
      />
    </n-space>
  </n-card>
</template>

<script setup>
import { useLicenseStore } from "@/store/license";
import { storeToRefs } from "pinia";
import { ref, inject, defineProps, getCurrentInstance } from "vue";

const props = defineProps({
  index: {
    type: Number,
  },
});

const ctx = getCurrentInstance();

const licenseAttrs = inject("licenseAttrs");

const licenseStore = useLicenseStore();

const { attrComponentMap } = storeToRefs(licenseStore);

const componentItem = ref({
  name: "",
  isExist: false,
  label: "",
});

const onSelectUpdate = (value) => {
  const attrItem = licenseStore.getcomponentNameByValue(value);

  if (!!attrItem) {
    componentItem.value.name = attrItem.componentName;
    componentItem.value.label = attrItem.label;
    componentItem.value.isExist =
      !!ctx.appContext.components[componentItem.value.name];
  }
};
onSelectUpdate(licenseAttrs.value[props.index].attrType);

const onAttrRemove = () => {
  licenseAttrs.value.splice(props.index, 1);
};
</script>

<style>
</style>