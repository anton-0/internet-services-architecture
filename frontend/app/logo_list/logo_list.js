import {getFileDBUrl} from '../js/configuration.js';
import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayFiles();
});

/**
 * fetch files and display them
 */
function fetchAndDisplayFiles() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFiles(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getFileDBUrl() + '/api/files', true);
    xhttp.send();
}

function displayFiles(files) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    files.files.forEach(file => {
        tableBody.appendChild(createTableRow(file));
    })
}

function createTableRow(file) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(file.title));
    tr.appendChild(createLinkCell('view', '../logo_view/logo_view.html?file=' + file.id));
    return tr;
}