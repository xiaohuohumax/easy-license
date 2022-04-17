import { createRouter, createWebHashHistory } from 'vue-router'
import component from '@/sctipt/component'
import view from '@/sctipt/view'
import { useHistoryStore } from '@/store/history'

const routes = [
    {
        path: '/',
        component: component.ElBody,
        children: [
            {
                path: '/',
                name: "Home",
                component: view.Home,
                meta: {
                    title: "首页"
                }
            },
            {
                path: 'CreateKeys',
                name: "CreateKeys",
                component: view.CreateKeys,
                meta: {
                    title: "创建密钥对"
                }
            },
            {
                path: 'CreateLicense',
                name: "CreateLicense",
                component: view.CreateLicense,
                meta: {
                    title: "生成许可证"
                }
            },
            {
                path: 'DecryptLicense',
                name: "DecryptLicense",
                component: view.DecryptLicense,
                meta: {
                    title: "解析许可证"
                }
            },
            {
                path: 'LocalKeys',
                name: "LocalKeys",
                component: view.LocalKeys,
                meta: {
                    title: "管理密钥对"
                }
            },
            {
                path: 'LocalLicenses',
                name: "LocalLicenses",
                component: view.LocalLicenses,
                meta: {
                    title: "管理许可证"
                }
            },
            {
                path: '/:path(.*)',
                name: "404 Not Found",
                component: view.status404,
                meta: {
                    title: "404 未找到"
                }
            },
        ]
    },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

router.beforeEach((to, from, next) => {
    const title = !!to?.meta?.title ? to.meta.title : to.name;

    // 添加历史记录
    const historyStore = useHistoryStore()
    historyStore.addHistory({
        path: to.path,
        name: title
    })

    // 更新 title
    window.document.title = title
    next()
})


export default router
// 