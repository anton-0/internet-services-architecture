import {getBackendUrl} from '../js/configuration.js';
import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayTeams();
});

/**
 * fetch teams and display them
 */
function fetchAndDisplayTeams() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTeams(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/teams', true);
    xhttp.send();
}

/**
 * clear table and append loaded teams to it
 * @param {{teams: string[]}} teams
 */
function displayTeams(teams) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    teams.teams.forEach(team => {
        tableBody.appendChild(createTableRow(team));
    })
}

/**
 * create a row with text, link and button
 * @param {string} team
 * @returns {HTMLTableRowElement}
 */
function createTableRow(team) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(team));
    tr.appendChild(createLinkCell('view', '../team_view/team_view.html?team=' + team));
    tr.appendChild(createLinkCell('edit', '../team_edit/team_edit.html?team=' + team));
    tr.appendChild(createButtonCell('delete', () => deleteTeam(team)));
    return tr;
}

/**
 * delete a team from backend and reload the page
 * @param {string} team to be deleted
 */
function deleteTeam(team) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayTeams();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/teams/' + team, true);
    xhttp.send();
}
