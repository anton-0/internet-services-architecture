import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName} from "../js/dom_utils.js";
import {validateForm} from "../js/common_functions.js";

window.addEventListener('load', () => {
    populateForm();
    document.getElementById("edit-team-button").onclick = editFootballer;
});

function editFootballer() {
    if (!validateForm()) {return;}
    const team = getParameterByName("team");

    const footballer = {
        "name": document.getElementById("name").value,
        "team": team,
        "role": document.getElementById("role").value
    };
    console.log(JSON.stringify(footballer));
    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + "/api/teams/" + team
        + "/footballers/" + getParameterByName("footballer"), true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(footballer));
    location.assign("../team_view/team_view.html?team=" + team);
}

function populateForm() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            setInputValues(JSON.parse(this.responseText));
        }
    }
    xhttp.open("GET", getBackendUrl() + "/api/footballers/" + getParameterByName("footballer"), true);
    xhttp.send();
}

/**
 * populates input boxes with team attributes
 * @param {{name: string, team: string, role: string}} footballer
 */
function setInputValues(footballer) {
    document.getElementById("name").value = footballer.name;
    document.getElementById("role").value = footballer.role;
    document.getElementById("teamname").innerText = footballer.team;
}
