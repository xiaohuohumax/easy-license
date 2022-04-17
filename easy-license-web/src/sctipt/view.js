export default {
    status404: () => import('@/views/statusView/status404.vue'),
    Home: () => import('@/views/Home.vue'),
    CreateKeys: () => import('@/views/CreateKeys.vue'),

    CreateLicense: () => import('@/views/CreateLicense.vue'),
    DecryptLicense: () => import('@/views/DecryptLicense.vue'),
    LocalKeys: () => import('@/views/LocalKeys.vue'),
    LocalLicenses: () => import('@/views/LocalLicenses.vue'),
}