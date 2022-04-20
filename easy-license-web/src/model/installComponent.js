import component from "@/sctipt/component"

export default (app) => {
    for (const name in component) {
        app.component(name, component[name]);
    }
}