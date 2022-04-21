<template>
  <n-space>
    <n-button type="success" ghost @click="createKeys"> 创建密钥 </n-button>
    <n-button type="info" ghost @click="saveKeys"> 保存密钥 </n-button>
    <n-button type="warning" ghost @click="downloadKeys">下载密钥</n-button>
  </n-space>
  <n-space vertical class="mt-4">
    <n-input v-model:value="keys.describe" type="text" placeholder="密钥描述" />
    <n-input
      v-model:value="keys.privateKey"
      type="textarea"
      placeholder="私钥"
      autosize
      disabled
    />
    <n-input
      v-model:value="keys.publicKey"
      type="textarea"
      placeholder="公钥"
      autosize
      disabled
    />
  </n-space>
</template>

<script setup>
import moment from "moment";
import { getCurrentInstance, ref } from "vue";
import { useMessage } from "naive-ui";
import { useKeysStore } from "@/store/keys";
import util from "@/sctipt/util";

const { proxy } = getCurrentInstance();

const message = useMessage();

const keysStore = useKeysStore();

const keys = ref({
  privateKey: "",
  publicKey: "",
  describe: "",
  id: 0,
  time: "",
});

const createKeys = () => {
  proxy.$http.license.createKeys().then(({ code, data, msg }) => {
    if (code == 0) {
      keys.value.privateKey = data.privateKey;
      keys.value.publicKey = data.publicKey;
      keys.value.describe = `密钥${moment().format("YYYY-MM-DD HH-mm-ss")}`;
      keys.value.id = new Date().getTime();
      keys.value.time = moment().format("YYYY-MM-DD HH-mm-ss");
      message.info("创建密钥成功!");
    }
  });
};

const saveKeys = () => {
  if (!keys.value.privateKey || !keys.value.publicKey) {
    message.error("密钥不能为空!");
    return;
  }

  keysStore.addKeys(keys.value);
  keys.value.privateKey = "";
  keys.value.publicKey = "";
  keys.value.describe = "";
  message.info("保存密钥成功!");
};

const downloadKeys = () => {
  if (!keys.value.privateKey || !keys.value.publicKey) {
    message.error("密钥不能为空!");
    return;
  }
  util.saveFile(keys.value.privateKey, `license.privateKey`);
  util.saveFile(keys.value.publicKey, `license.publicKey`);
  message.info("下载成功!");
};
</script>