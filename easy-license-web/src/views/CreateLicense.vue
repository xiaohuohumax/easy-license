<template>
  <n-space>
    <n-button type="success" ghost @click="createLicense">创建许可证</n-button>
    <n-button type="info" ghost>保存许可证</n-button>
    <n-button type="warning" ghost @click="downloadLicense">
      导出许可证
    </n-button>
    <n-button type="primary" ghost @click="downloadKeys">导出密钥</n-button>
  </n-space>
  <n-grid x-gap="12" :cols="2" class="mt-4">
    <n-gi>
      <n-form ref="formRef" :model="license" :rules="rules">
        <n-form-item path="licenseData.licenseVisible.name" label="许可证名称">
          <n-input
            v-model:value="license.licenseData.licenseVisible.name"
            @keydown.enter.prevent
            placeholder="许可证名称"
          />
        </n-form-item>
        <n-form-item
          path="licenseData.licenseVisible.version"
          label="许可证版本"
        >
          <n-input
            v-model:value="license.licenseData.licenseVisible.version"
            @keydown.enter.prevent
            placeholder="许可证版本"
          />
        </n-form-item>

        <n-form-item
          path="licenseData.licenseVisible.describe"
          label="许可证描述"
        >
          <n-input
            type="textarea"
            autosize
            v-model:value="license.licenseData.licenseVisible.describe"
            placeholder="许可证描述"
          />
        </n-form-item>
        <n-form-item
          path="licenseData.authRegistrationCodeList"
          label="许可证注册码"
        >
          <n-dynamic-tags
            v-model:value="license.licenseData.authRegistrationCodeList"
            :max="10"
          />
        </n-form-item>

        <n-form-item path="licenseData.expiredTime" label="许可证过期时间">
          <n-date-picker
            v-model:formatted-value="license.licenseData.expiredTime"
            type="datetime"
            clearable
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="许可证过期时间"
            class="w-full"
          />
        </n-form-item>
        <n-form-item path="keys.publicKey" label="许可证公钥">
          <n-input
            type="textarea"
            autosize
            v-model:value="license.keys.publicKey"
            placeholder="许可证公钥"
          />
        </n-form-item>
        <n-form-item path="keys.privateKey" label="许可证私钥">
          <n-input
            type="textarea"
            autosize
            v-model:value="license.keys.privateKey"
            placeholder="许可证私钥"
          />
        </n-form-item>
        <n-form-item label="自定义参数">
          <LicenseAttrs v-model:value="license.licenseData.attrMap" />
        </n-form-item>
      </n-form>
    </n-gi>
    <n-gi>
      <n-form-item label="许可证">
        <n-input
          type="textarea"
          autosize
          v-model:value="licenseStr"
          disabled
          placeholder="许可证"
        />
      </n-form-item>
    </n-gi>
  </n-grid>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useLicenseStore } from "@/store/license";
import { useRoute } from "vue-router";
import { ref, getCurrentInstance } from "vue";
import { useMessage } from "naive-ui";
import moment from "moment";
import util from "@/sctipt/util";

const { proxy } = getCurrentInstance();

const licenseStore = useLicenseStore();

const message = useMessage();

const { query } = useRoute();

const { license } = storeToRefs(licenseStore);

const licenseStr = ref("");

license.value.keys.privateKey = !!query.privateKey ? query.privateKey : "";
license.value.keys.publicKey = !!query.publicKey ? query.publicKey : "";

const rules = {
  licenseData: {
    licenseVisible: {
      name: [
        {
          required: true,
          message: "请输入许可证名称",
        },
      ],
      version: [
        {
          required: true,
          message: "请输入许可证版本",
        },
      ],
    },
    authRegistrationCodeList: [
      {
        required: true,
        message: "请输入许可证注册码",
      },
    ],
    expiredTime: [
      {
        required: true,
        message: "请输入许可证过期时间",
      },
    ],
  },
  keys: {
    publicKey: [
      {
        required: true,
        message: "请输入许可证公钥",
      },
    ],
    privateKey: [
      {
        required: true,
        message: "请输入许可证私钥",
      },
    ],
  },
};
const formRef = ref(null);
const createLicense = () => {
  formRef.value?.validate((errors) => {
    if (!!errors) {
      message.error("许可证格式错误!");
      return;
    }

    proxy.$http.license
      .createLicense(license.value)
      .then(({ code, data, msg }) => {
        if (code == 0) {
          licenseStr.value = data.licenseEncryptData;
        }
      });
  });
};

const downloadLicense = () => {
  const name = license.value.licenseData.licenseVisible.name;
  if (!licenseStr.value) {
    message.error("许可证不能为空!");
    return;
  }
  util.saveFile(
    licenseStr.value,
    `${name}-${moment().format("YYYY-MM-DD HH-mm-ss")}.json`
  );
  message.info("下载成功!");
};

const downloadKeys = () => {
  const keys = license.value.keys;
  const name = license.value.licenseData.licenseVisible.name;
  if (!keys.privateKey || !keys.privateKey) {
    message.error("密钥不能为空!");
    return;
  }
  util.saveFile(
    keys.privateKey,
    `${name}-${moment().format("YYYY-MM-DD HH-mm-ss")}.privateKey`
  );
  util.saveFile(
    keys.publicKey,
    `${name}-${moment().format("YYYY-MM-DD HH-mm-ss")}.publicKey`
  );
  message.info("下载成功!");
};
</script>