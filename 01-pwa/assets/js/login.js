"use strict";

const VAPID_PUBLIC_KEY = "BLLx2QKxJmMjp3z0AMNrtVv5rF0HlL0f66XYLibWoqRSusUw6ZAfXgJmq9TS40ExR517_Y2_MBckEetZcvMNeSQ";
const LOGIN = "form input[name='login']";
const REGISTER = "input[name='register']";
const USERNAME = document.querySelector("form input[id='username']");
const PASSWORD = document.querySelector("form input[id='password']");

document.addEventListener("DOMContentLoaded", init);

function init() {
    document.querySelector(LOGIN).addEventListener('click', login);
    document.querySelector(REGISTER).addEventListener('click', goToRegisterPage);
}

function login(e) {
    e.preventDefault();

    const user = {
        username: USERNAME.value,
        password: PASSWORD.value,
    };

    fetch(`${BASE_API}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user),
    })
        .then((response) => response.json())
        .then(function (result) {
            localStorage.setItem('token', result.access_token);
            localStorage.setItem('producer', result.user.id);
            subscribeUserToPush(result.access_token);
        })
}

function subscribeUserToPush(token) {
    if ('PushManager' in window) {
        navigator.serviceWorker.ready.then(registration => {
            const subscribeOptions = {
                userVisibleOnly: true,
                applicationServerKey: urlBase64ToUint8Array(VAPID_PUBLIC_KEY)
            };
            return registration.pushManager.subscribe(subscribeOptions);
        }).then(pushSubscription => {
            storePushSubscription(pushSubscription, token);
        });
    }
}

function storePushSubscription(pushSubscription, token) {
    fetch(`${BASE_API}/push`, {
        method: "POST",
        headers: {
            Authorization: "Bearer" + token,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(pushSubscription),
    }).then((response) => response.json())
        .then(function (result) {
            window.location.href = "index.html";
        });
}

function urlBase64ToUint8Array(base64String) {
    var padding = '='.repeat((4 - base64String.length % 4) % 4);
    var base64 = (base64String + padding)
        .replace(/\-/g, '+')
        .replace(/_/g, '/');

    var rawData = window.atob(base64);
    var outputArray = new Uint8Array(rawData.length);

    for (var i = 0; i < rawData.length; ++i) {
        outputArray[i] = rawData.charCodeAt(i);
    }
    return outputArray;
}

function goToRegisterPage(e) {
    e.preventDefault();

    window.location.href = "register.html";
}
