import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createLink
} from '../js/dom_utils.js';
import {getFileDBUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayFileDetails();
});

function fetchAndDisplayFileDetails() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayFile(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getFileDBUrl() + '/api/files/' + getParameterByName('file') + '/details', true);
    xhttp.send();
}

function displayFile(file) {
    document.getElementById('title').innerText = file.title;
    document.getElementById('author').innerText = file.author;
    const imageCell = document.getElementById('img');
    let img = document.createElement("img");
    img.src = getFileDBUrl() + '/api/files/' + getParameterByName('file');
    imageCell.appendChild(img);
}