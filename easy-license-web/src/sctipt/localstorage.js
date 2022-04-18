export default {
    set(name, data) {
        localStorage.setItem(name, JSON.stringify(data))
    },
    get(name) {
        return JSON.parse(localStorage.getItem(name));
    }
}