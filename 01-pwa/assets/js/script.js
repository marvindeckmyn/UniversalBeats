"use strict";

const BASE = "https://pacific-ridge-96972.herokuapp.com";
const BASE_API = "https://pacific-ridge-96972.herokuapp.com/api";
const LOGINLISTITEM = document.querySelector("nav li[data-id='login']");
const SEARCHBEAT = "form input[name='search']";
const MAIN = document.querySelector("main");
let MAINSECTION = document.querySelector("main section");

let user;

let request = window.indexedDB.open("CartDatabase", 1);
let db = null;

request.addEventListener("error", function (event) {
    console.error("A problem occured. " + event.target.errorCode)
});

request.addEventListener("upgradeneeded", function (event) {
    db = event.target.result;
    let cart = db.createObjectStore("cart", { keyPath: "id" });
});

request.onsuccess = function (e) {
    db = e.target.result;
}

document.addEventListener("DOMContentLoaded", init);

function init() {
    registerServiceWorker();
    enableNotifications();

    if (localStorage.getItem("token") != "null") {
        if (localStorage.getItem("producer") !== "undefined") {
            loadUser(localStorage.getItem("token"));
        }
    }

    document.querySelector(SEARCHBEAT).addEventListener('click', searchBeats);

    if (!navigator.onLine)
        handleOffline();

    window.addEventListener("online", handleOnline);
    window.addEventListener("offline", handleOffline);

}

function enableNotifications() {
    if ('Notification' in window) {
        if (Notification.permission === "default") {
            Notification.requestPermission();
        }
    }
}

function searchBeats(e) {
    e.preventDefault();
    const searchvalue = document.querySelector("form input[type='text']").value;
    MAIN.innerHTML = `<section>
                            <h1>Beats</h1>
                        </section>`;

    MAINSECTION = document.querySelector("main section");

    fetch(`${BASE_API}/beats`).then(response => response.json())
        .then(function (result) {
            const beats = result.reverse();

            fetch(`${BASE_API}/producers`).then(response => response.json())
                .then(function (result) {
                    const producers = result;

                    for (let i = 0; i < beats.length; i++) {
                        if (beats[i].name.toLowerCase().includes(searchvalue.toLowerCase())) {
                            addBeatsHtml(beats, i, producers);
                        }
                    }

                    document.querySelectorAll("section article h1").forEach(beat => {
                        beat.addEventListener('click', goToBeatPage);
                    });

                    document.querySelectorAll("section article #cart p").forEach(cartBeat => {
                        cartBeat.addEventListener('click', addToCart);
                    });
                })
        })
}

function addBeatsHtml(beats, i, producers) {
    MAINSECTION.innerHTML += `<article class='beats' data-id="${beats[i].id}">
                                    <div id="firstpart">
                                        <img src="${BASE}/${beats[i].cover}" alt="${beats[i].name}" title="${beats[i].name}">

                                        <div>
                                            <h1>${beats[i].name}</h1>
                                            <div id="details">
                                                <p><span>${producers[beats[i].producer_id - 1].username}</span> -</p>
                                                <p id="price"> €${beats[i].price}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="cart">
                                        <p>Add to cart</p>
                                    </div>
                                </article>`;
}

function goToBeatPage(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.parentNode.parentNode.getAttribute('data-id');

    localStorage.setItem('beat', beatId);
    window.location.href = "beat.html";
}

function addToCart(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.parentNode.getAttribute('data-id');
    const beatName = e.target.parentNode.parentNode.querySelector("#firstpart div h1").innerHTML;
    const beatPrice = e.target.parentNode.parentNode.querySelector("#firstpart div #details #price").innerHTML.substring(2);
    const beatCover = e.target.parentNode.parentNode.querySelector("#firstpart img").src;

    const beatCart = {
        id: beatId, name: beatName, price: beatPrice, cover: beatCover
    };

    const trx = db.transaction("cart", "readwrite");
    const cartStore = trx.objectStore("cart");

    cartStore.add(beatCart);
    localStorage.setItem("cart", "true");
    window.location.href = "cart.html";
}

function registerServiceWorker() {
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.register("./sw.js").catch(function (err) {
            console.log("Error registering service worker:", err);
        });
    }
}

function loadUser(token) {
    fetch(`${BASE_API}/user`, {
        method: "GET",
        headers: {
            Authorization: "Bearer" + token,
            Accept: "application/json",
        },
    })
        .then((response) => response.json())
        .then(function (result) {
            user = result;

            LOGINLISTITEM.innerHTML = `<span data-id="${user.id}">${user.username}</span>`;
            LOGINLISTITEM.addEventListener("click", goToProfile);
        })
}

function goToProfile(e) {
    e.preventDefault();

    const producerId = e.target.getAttribute('data-id');

    localStorage.setItem('producer', producerId);
    window.location.href = "producer.html";
}

function handleOnline() {
    const header = document.querySelector("header");

    addHtmlWhenOnline(header);
    header.style.background = "#7e5aa1";
    header.style.textAlign = "initial";
    header.style.padding = "initial";
}

function addHtmlWhenOnline(header) {
    header.innerHTML = `<div id="navigation">
                            <a href="index.html">
                                <img id="logo" src="assets/images/logo.png" alt="Logo Universal BEATS." title="Logo Universal BEATS.">
                            </a>
                            <nav>
                                <ul>
                                    <li><a href="index.html">Home</a></li>
                                    <li><a href="beats.html">Beats</a></li>
                                    <li><a href="startselling.html">Start Selling</a></li>
                                    <li data-id="login"><a href="login.html">Login</a></li>
                                    <li><a href="cart.html"><img src="assets/media/shopping-cart.svg" alt="Cart" title="Cart"></a></li>
                                </ul>
                            </nav>
                        </div>
                        <div id="search">
                            <form>
                                <label for="searchBeat">Search beat</label>
                                <input type="text" name="searchBeat" id="searchBeat" required>
                                <input type="button" name="search" value="search">
                            </form>
                        </div>`;
}

function handleOffline() {
    const header = document.querySelector("header");

    header.innerHTML = "<p>You are offline</p>";
    header.style.background = "rgb(236, 52, 52)";
    header.style.textAlign = "center";
    header.style.padding = "15px";
}
