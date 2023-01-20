import {getParameterByName} from "../js/dom_utils.js";
import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load', () => {
    populateForm();
    document.getElementById("edit-team-button").onclick = editTeam;
});

function populateForm() {
    const name = getParameterByName("team");
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            setInputValues(JSON.parse(this.responseText));
        }
    }
    xhttp.open("GET", getBackendUrl() + "/api/teams/" + name, true);
    xhttp.send();
}

/**
 * populates input boxes with team attributes
 * @param {{name: string, city: string}} team
 */
function setInputValues(team) {
    document.getElementById("name").innerText = team.name;
    document.getElementById("city").value = team.city;
}

/**
 * sends PUT request with data from the html form
 */
function editTeam() {
    const name = getParameterByName("team");
    const team = {
        "name": name,
        "city": document.getElementById("city").value
    };
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + "/api/teams/" + name);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(team));
    location.assign("../team_view/team_view.html?team=" + name);
}
