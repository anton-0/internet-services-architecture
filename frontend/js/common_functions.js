import {alterError} from "./dom_utils.js";

export function validateForm() {
    if (!document.getElementById("name").value) {
        alterError("you must provide footballer's name!", "visible");
        document.getElementById("name").focus();
        return false;
    }
    else {return true;}
}