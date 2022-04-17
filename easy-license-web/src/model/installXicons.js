import * as vicons from '@vicons/ionicons5'

export default (app) => {
    for (const name in vicons) {
        app.component(name, vicons[name])
    }
}