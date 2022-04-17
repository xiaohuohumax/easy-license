<template>
  <n-menu
    :options="menuOptions"
    accordion
  />
</template>

<script setup>
import { h, ref } from "vue";
import { RouterLink } from "vue-router";
import { NIcon } from "naive-ui";
import {
  PawOutline,
  HomeOutline,
  BookmarkOutline,
  CreateOutline,
  KeyOutline,
  FileTrayFullOutline,
  EyeOutline,
  FingerPrint,
} from "@vicons/ionicons5";

function renderIcon(icon) {
  return () => h(NIcon, null, { default: () => h(icon) });
}

function createLable(path, name) {
  return () => h(RouterLink, { to: { name: path } }, { default: () => name });
}

const menuOptions = ref([
  {
    label: createLable("Home", "首页"),
    key: "Home",
    icon: renderIcon(HomeOutline),
  },
  {
    label: "许可",
    key: "license",
    icon: renderIcon(CreateOutline),
    children: [
      {
        label: createLable("CreateKeys", "创建密钥对"),
        key: "CreateKeys",
        icon: renderIcon(KeyOutline),
      },
      {
        label: createLable("CreateLicense", "生成许可证"),
        key: "CreateLicense",
        icon: renderIcon(BookmarkOutline),
      },
      {
        label: createLable("DecryptLicense", "解析许可证"),
        key: "DecryptLicense",
        icon: renderIcon(EyeOutline),
      },
    ],
  },
  {
    label: "仓库",
    key: "local",
    icon: renderIcon(PawOutline),
    children: [
      {
        label: createLable("LocalKeys", "管理密钥对"),
        key: "LocalKeys",
        icon: renderIcon(FingerPrint),
      },
      {
        label: createLable("LocalLicenses", "管理许可证"),
        key: "LocalLicenses",
        icon: renderIcon(FileTrayFullOutline),
      },
    ],
  },
]);
</script>