import {alterError} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load', () => {
    document.getElementById("create-team-button").onclick = createTeam;
});

/**
 * checks if team of the given name exists
 * @param {string} name
 * @return {boolean}
 */
function teamExists(name) {
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", getBackendUrl() + '/api/teams/' + name, false);
    xhttp.send();
    if (xhttp.readyState === 4) {
        if (xhttp.status === 200) {
            alterError("team of name '" + name + "' already exists!", "visible");
            return true;
        } else if (xhttp.status === 404) {
            alterError("", "hidden");
            return false;
        }
    }
}


function createTeam() {
    const xhttp = new XMLHttpRequest();
    const name_element = document.getElementById("name");
    if (teamExists(name_element.value)) {
        name_element.focus();
        return;
    }
    let team = {
        "name": name_element.value,
        "city": document.getElementById("city").value
    }
    xhttp.open("POST", getBackendUrl() + '/api/teams', true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(team));
    location.assign("../team_list/team_list.html");
}