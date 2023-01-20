import {getBackendUrl} from "../js/configuration.js";
import {getParameterByName} from "../js/dom_utils.js";

window.addEventListener('load', () => {
    fetchAndDisplayFootballer();
});

function fetchAndDisplayFootballer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFootballer(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/teams/' + getParameterByName('team')
        + '/footballers/' + getParameterByName('footballer'), true);
    xhttp.send();
}

/**
 * @param {{name: string, team: string, role: string}} footballer
 */
function displayFootballer(footballer) {
    document.getElementById('name').innerText = footballer.name;
    document.getElementById('role').innerText = footballer.role;
    document.getElementById('team').innerText = footballer.team;

}
