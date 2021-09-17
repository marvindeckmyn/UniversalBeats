"use strict";

const CHANGE = "form input[name='change']";

document.addEventListener("DOMContentLoaded", init);

function init() {
    setTimeout(function () {
        if (localStorage.getItem('producer') == user.id) {
            loadSettings();
            document.querySelector(CHANGE).addEventListener("click", changePassword);
        }
    }, 1000);
}

function loadSettings() {
    const SETTINGSTITLE = document.querySelector("section h1");
    SETTINGSTITLE.innerHTML = user.username;
}

function changePassword(e) {
    e.preventDefault();

    const OLDPASSWORD = document.querySelector("input#oldpassword");
    const PASSWORD = document.querySelector("input#password");
    const CONFIRMPASSWORD = document.querySelector("input#password-confirm");

    if (PASSWORD.value === CONFIRMPASSWORD.value) {
        const token = localStorage.getItem("token");

        const password = {
            oldpassword: OLDPASSWORD.value,
            password: PASSWORD.value,
            password_confirmation: CONFIRMPASSWORD.value
        };

        fetch(`${BASE_API}/changepassword`, {
            method: "PUT",
            headers: {
                Authorization: "Bearer" + token,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(password),
        })
            .then((response) => response.json());
    } else {
        console.log("Confirmation of password not succeeded");
    }
}
