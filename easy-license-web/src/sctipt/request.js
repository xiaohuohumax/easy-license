import axios from 'axios'
const requests = axios.create({
    ...window.$config.axios,
})

const err = (error) => {
    window.$message.error(error.message)
    return Promise.reject(error.message)
}

requests.interceptors.request.use(config => {
    return config
}, err)

requests.interceptors.response.use((response) => {
    const res = response.data

    if (!!res?.code && res.code < 0) {
        message.error(res?.msg)
    }
    return res;
}, err)

export default requests