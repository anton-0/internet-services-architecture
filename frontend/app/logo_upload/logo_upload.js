import {getFileDBUrl} from "../js/configuration.js";

window.addEventListener('load', () => {
    document.getElementById("upload-file-button").onclick = uploadFile;
});

function uploadFile() {

    const formData = new FormData();
    formData.append('title', document.getElementById("title").value);
    formData.append('author', document.getElementById("author").value);
    formData.append('content', document.getElementById("file").value);

    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getFileDBUrl() + '/api/files/', true);
    xhttp.setRequestHeader("Content-type", "multipart/form-data");
    xhttp.send(formData);
    // location.assign("../team_view/team_view.html?team=" + team);
}
