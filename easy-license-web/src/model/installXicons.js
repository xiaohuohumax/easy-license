import * as vicons from '@/sctipt/icon'

export default (app) => {
    for (const name in vicons) {
        app.component(name, vicons[name])
    }
}