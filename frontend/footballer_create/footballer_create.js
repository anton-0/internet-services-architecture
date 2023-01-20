import {getBackendUrl} from "../js/configuration.js";
import {validateForm} from "../js/common_functions.js";
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    document.getElementById("create-team-button").onclick = createFootballer;
    document.getElementById("teamname").innerText = getParameterByName("team");
});

/**
 * create footballer and add him to team defined in query parameter
 */
function createFootballer() {
    if (!validateForm()) {return;}

    const team = getParameterByName("team");
    let footballer = {
        "name": document.getElementById("name").value,
        "team": team,
        "role": document.getElementById("role").value
    }

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/teams/' + team + '/footballers', true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(footballer));
    location.assign("../team_view/team_view.html?team=" + team);
}
