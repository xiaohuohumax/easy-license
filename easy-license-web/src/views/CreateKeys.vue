<template>
  <n-button type="success" ghost @click="requestTest"> 测试请求 </n-button>
</template>

<script setup>
import { getCurrentInstance } from "vue";
import { useMessage } from "naive-ui";
const { proxy } = getCurrentInstance();

const message = useMessage();

const requestTest = () => {
  proxy.$http.test.testHttp().then((data) => {
    message.info(data);
  });
};
</script>