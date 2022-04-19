<template>
  <n-space>
    <n-button type="success" ghost>创建许可证</n-button>
    <n-button type="warning" ghost>导出许可证</n-button>
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
            ~~~
        </n-form-item>
      </n-form>
    </n-gi>
    <n-gi>
      <n-form-item label="许可证">
        {{ license }}
      </n-form-item>
    </n-gi>
  </n-grid>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useLicenseStore } from "@/store/license";
import { useRoute } from "vue-router";

const licenseStore = useLicenseStore();

const { query } = useRoute();

const { license } = storeToRefs(licenseStore);

license.value.keys.privateKey = !!query.privateKey ? query.privateKey : "";
license.value.keys.publicKey = !!query.publicKey ? query.publicKey : "";

const rules = [];
</script>

<style>
</style>