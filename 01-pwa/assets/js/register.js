"use strict";

const REGISTER = "form input[name='register']";
const LOGIN = "input[name='login']";
const NAME = document.querySelector("form input[id='name']");
const EMAIL = document.querySelector("form input[id='email']");
const USERNAME = document.querySelector("form input[id='username']");
const PASSWORD = document.querySelector("form input[id='password']");
const PASSWORDCONFIRMATION = document.querySelector("form input[id='password-confirm']");
const ABOUTME = document.querySelector("form input[id='aboutme']");
const GENRE = document.querySelector("form select[id='genre']");
const TWITTER = document.querySelector("form input[id='twitter']");
const INSTAGRAM = document.querySelector("form input[id='instagram']");
const YOUTUBE = document.querySelector("form input[id='youtube']");

document.addEventListener("DOMContentLoaded", init);

function init() {
    document.querySelector(REGISTER).addEventListener('click', register);
    document.querySelector(LOGIN).addEventListener('click', goToLoginPage);
}


function register(e) {
    e.preventDefault();

    const user = {
        name: NAME.value,
        email: EMAIL.value,
        username: USERNAME.value,
        password: PASSWORD.value,
        password_confirmation: PASSWORDCONFIRMATION.value,
        aboutme: ABOUTME.value,
        genre: GENRE.value,
        twitter: TWITTER.value,
        instagram: INSTAGRAM.value,
        youtube: YOUTUBE.value,
    };

    fetch(`${BASE_API}/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    })
        .then((response) => response.json())
        .then(function (result) {
            if (result.message === "User signed up") {
                window.location.href = "login.html";
            }
        });
}

function goToLoginPage(e) {
    e.preventDefault();

    window.location.href = "login.html";
}
