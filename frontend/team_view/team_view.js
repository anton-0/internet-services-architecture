import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createLink
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';


window.addEventListener('load', () => {
    fetchAndDisplayTeam();
    fetchAndDisplayFootballers();
    updateLinks();
});


function updateLinks() {
    const div = document.getElementById("create-link-div")
    div.appendChild(createLink("add member", "../footballer_create/footballer_create.html?team="
        + getParameterByName("team")));
}


function fetchAndDisplayFootballers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFootballers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/teams/' + getParameterByName('team') + '/footballers', true);
    xhttp.send();
}


function displayFootballers(footballers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    footballers.footballers.forEach(footballer => {
        tableBody.appendChild(createTableRow(footballer));
    })
}

/**
 * creates a row for a footballer entity
 * @param {{id: number, name: string}} footballer
 * @returns {HTMLTableRowElement}
 */
function createTableRow(footballer) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(footballer.name));
    tr.appendChild(createLinkCell('view', '../footballer_view/footballer_view.html?team='
        + getParameterByName('team') + '&footballer=' + footballer.id));
    tr.appendChild(createLinkCell('edit', '../footballer_edit/footballer_edit.html?team='
        + getParameterByName('team') + '&footballer=' + footballer.id));
    tr.appendChild(createButtonCell('delete', () => deleteFootballer(footballer.id)));
    return tr;
}


// delete footballer from backend, by ID
function deleteFootballer(footballer) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayFootballers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/teams/' + getParameterByName('team')
        + '/footballers/' + footballer, true);
    xhttp.send();
}


// ready state 4 => DONE - the operation is complete
function fetchAndDisplayTeam() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTeam(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/teams/' + getParameterByName('team'), true);
    xhttp.send();
}

/**
 * assign team attributes to text nodes
 * @param {{name: string, city: string}} team
 */
function displayTeam(team) {
    document.getElementById('name').innerText = team.name;
    document.getElementById('city').innerText = team.city;
}
