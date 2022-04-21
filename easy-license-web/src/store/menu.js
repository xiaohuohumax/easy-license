import { defineStore } from 'pinia'
import { h } from "vue";
import { RouterLink } from "vue-router";
import { NIcon } from "naive-ui";
import icon from '@/sctipt/icon'

function renderIcon(icon) {
    return () => h(NIcon, null, { default: () => h(icon) });
}

function createLable(path, name) {
    return () => h(RouterLink, { to: { name: path } }, { default: () => name });
}

export const useMenuStore = defineStore("menu", {
    state: () => ({
        menuOptions: [
            {
                label: createLable("Home", "首页"),
                key: "Home",
                icon: renderIcon(icon.HomeOutline),
            },
            {
                label: "许可",
                key: "license",
                icon: renderIcon(icon.CreateOutline),
                children: [
                    {
                        label: createLable("CreateKeys", "创建密钥对"),
                        key: "CreateKeys",
                        icon: renderIcon(icon.KeyOutline),
                    },
                    {
                        label: createLable("CreateLicense", "生成许可证"),
                        key: "CreateLicense",
                        icon: renderIcon(icon.BookmarkOutline),
                    },
                    {
                        label: createLable("DecryptLicense", "解析许可证"),
                        key: "DecryptLicense",
                        icon: renderIcon(icon.EyeOutline),
                    },
                ],
            },
            {
                label: "仓库",
                key: "local",
                icon: renderIcon(icon.PawOutline),
                children: [
                    {
                        label: createLable("LocalKeys", "管理密钥对"),
                        key: "LocalKeys",
                        icon: renderIcon(icon.FingerPrint),
                    },
                    {
                        label: createLable("LocalLicenses", "管理许可证"),
                        key: "LocalLicenses",
                        icon: renderIcon(icon.FileTrayFullOutline),
                    },
                ],
            },
        ]
    })
})