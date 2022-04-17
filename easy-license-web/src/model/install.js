import installPinia from "./installPinia";
import installNaive from "./installNaive";
import installRouter from "./installRouter";
import installTailwind from "./installTailwind";
import installXicons from "./installXicons";
import installComponent from "./installComponent";
import installHttp from "./installHttp";

export default (app) => {
    installPinia(app)
    installRouter(app)
    installNaive(app)
    installTailwind(app)
    installXicons(app)
    installComponent(app)
    installHttp(app)
}