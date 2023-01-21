/**
 * remove all children nodes from a node
 * @param {HTMLElement} element parent element
 */
export function clearElementChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

/**
 * create a cell with a button
 * @param {string} text text to be displayed on button
 * @param {function} action function to be executed on button click
 * @returns {HTMLTableDataCellElement} table cell with action button
 */
export function createButtonCell(text, action) {
    const td = document.createElement('td');
    const button = document.createElement('button');
    button.appendChild(document.createTextNode(text));
    button.classList.add('ui-control', 'ui-button');
    td.appendChild(button);
    button.addEventListener('click', action);
    return td;
}

/**
 * create a link
 * @param {string} text text to be displayed on link
 * @param {string} url link url
 */
export function createLinkCell(text, url) {
    const td = document.createElement('td');
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    td.appendChild(a);
    return td;
}

export function createLink(text, url) {
    const a = document.createElement('a');
    a.appendChild(document.createTextNode(text));
    a.href = url;
    return a;
}

/**
 * create a simple cell with text
 * @param {string} text text to be displayed
 */
export function createTextCell(text) {
    const td = document.createElement('td');
    td.appendChild(document.createTextNode(text));
    return td;
}


// get parameter from url by its name
export function getParameterByName(name) {
    return new URLSearchParams(window.location.search).get(name);
}


/**
 * alter error message
 * @param {string} msg
 * @param {string} visibility
 */
export function alterError(msg, visibility) {
    const errorbox = document.getElementById("error-text");
    errorbox.style.visibility = visibility;
    errorbox.innerText = msg;
}
