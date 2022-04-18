<template>
  <n-space vertical>
    <n-card class="text-center" v-show="keys.length == 0">这里啥也没有~</n-card>
    <n-card v-for="(item, index) in keys" :key="index">
      <n-space vertical class="mt-2">
        <n-input
          v-model:value="item.describe"
          type="text"
          placeholder="密钥描述"
          disabled
        />
        <n-input
          v-model:value="item.privateKey"
          type="textarea"
          placeholder="私钥"
          autosize
          disabled
        />
        <n-input
          v-model:value="item.publicKey"
          type="textarea"
          placeholder="公钥"
          autosize
          disabled
        />
        <n-space justify="space-between" align="center">
          <span>创建时间: {{ item.time }}</span>
          <n-space>
            <n-button type="success" ghost>创建证书</n-button>
            <n-button type="warning" ghost>下载密钥</n-button>
            <n-button
              type="error"
              ghost
              @click="keysStore.removeKeyById(item.id)"
            >
              删除
            </n-button>
          </n-space>
        </n-space>
      </n-space>
    </n-card>
  </n-space>
</template>

<script setup>
import { useKeysStore } from "@/store/keys";
import { storeToRefs } from "pinia";
const keysStore = useKeysStore();

keysStore.loadKeys();
const { keys } = storeToRefs(keysStore);
</script>