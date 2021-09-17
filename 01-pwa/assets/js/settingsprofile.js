"use strict";

const UPDATE = "form input[name='save']";
const SETTINGSNAME = document.querySelector("input#name");
const SETTINGSUSERNAME = document.querySelector("input#username");
const SETTINGSABOUTME = document.querySelector("input#aboutme");
const SETTINGSGENRE = document.querySelector("select#genre");
const SETTINGSTWITTER = document.querySelector("input#twitter");
const SETTINGSINSTAGRAM = document.querySelector("input#instagram");
const SETTINGSYOUTUBE = document.querySelector("input#youtube");
const SETTINGSEMAIL = document.querySelector("input#email");

document.addEventListener("DOMContentLoaded", init);

function init() {
    setTimeout(function () {
        if (localStorage.getItem('producer') == user.id) {
            loadSettings();
            document.querySelector(UPDATE).addEventListener("click", updateAccount);
        }
    }, 1000);
}

function loadSettings() {
    const SETTINGSTITLE = document.querySelector("section h1");
    const SETTINGSAVATAR = document.querySelector("figure#avatar");

    SETTINGSTITLE.innerHTML = user.username;
    SETTINGSAVATAR.innerHTML = `<img src="${BASE}/${user.avatar}" alt="Avatar ${user.username}" title="Avatar ${user.username}">`;
    SETTINGSNAME.value = user.name;
    SETTINGSUSERNAME.value = user.username;
    SETTINGSABOUTME.value = user.aboutme;
    SETTINGSGENRE.innerHTML += `<option selected value=${user.genre}>${user.genre}</option>`;
    SETTINGSTWITTER.value = user.twitter;
    SETTINGSINSTAGRAM.value = user.instagram;
    SETTINGSYOUTUBE.value = user.youtube;
    SETTINGSEMAIL.value = user.email;
}

function updateAccount(e) {
    e.preventDefault();

    const token = localStorage.getItem("token");

    const user = {
        name: SETTINGSNAME.value,
        email: SETTINGSEMAIL.value,
        username: SETTINGSUSERNAME.value,
        aboutme: SETTINGSABOUTME.value,
        genre: SETTINGSGENRE.value,
        twitter: SETTINGSTWITTER.value,
        instagram: SETTINGSINSTAGRAM.value,
        youtube: SETTINGSYOUTUBE.value,
    };

    fetch(`${BASE_API}/updateaccount`, {
        method: "PUT",
        headers: {
            Authorization: "Bearer" + token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    })
        .then((response) => response.json())
        .then(function (result) {
            window.location.href = "producer.html";
        });
}
