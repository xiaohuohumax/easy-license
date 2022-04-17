import index from '@/api/index'

export default (app) => {
    app.config.globalProperties.$http = index
}