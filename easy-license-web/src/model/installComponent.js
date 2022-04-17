import component from "@/sctipt/component"
import { defineAsyncComponent } from "vue";

export default (app) => {
    for (const name in component) {
        app.component(name, defineAsyncComponent(component[name]));
    }
}