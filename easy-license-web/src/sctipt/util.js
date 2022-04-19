import { saveAs } from 'file-saver'

export default {
    saveFile(data, name) {
        saveAs(new Blob([data], { type: "blob" }), name);
    }
}