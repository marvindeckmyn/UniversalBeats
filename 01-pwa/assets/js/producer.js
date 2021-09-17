"use strict";

const PRODUCERDETAILS = document.querySelector("section");
const PRODUCERID = localStorage.getItem("producer");

document.addEventListener("DOMContentLoaded", init)

function init() {
    loadProducer();

    setTimeout(function () {
        if (user) {
            if (localStorage.getItem('producer') == user.id) {
                loadPersonalFeatures();
                loadBeats("insights", "Insights", goToInsightsPage);

            } else {
                loadBeats("cart", "Add to cart", addToCart);
            }
        } else {
            loadBeats("cart", "Add to cart", addToCart);
        }
    }, 1000);
}

function goToInsightsPage(e) {
    e.preventDefault();
    const beatId = e.target.parentNode.parentNode.getAttribute('data-id');

    fetch(`${BASE_API}/plays/${beatId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json())
        .then(function (result) {
            localStorage.setItem('beat', beatId);

            window.location.href = "insights.html";
        });
}

function loadBeats(id, text, goToPage) {
    fetch(`${BASE_API}/beats`).then(response => response.json())
        .then(function (result) {
            const beats = result.reverse();

            for (let i = 0; i < beats.length; i++) {
                if (beats[i].producer_id == localStorage.getItem('producer')) {
                    addHtmlBeats(beats, i, id, text);
                }
            }

            document.querySelectorAll("section article h1").forEach(beat => {
                beat.addEventListener('click', goToBeatPage);
            });

            document.querySelectorAll(`section article .lastdiv p`).forEach(cartBeat => {
                cartBeat.addEventListener('click', goToPage);
            });
        })
}

function addHtmlBeats(beats, i, id, text) {
    PRODUCERDETAILS.innerHTML += `<article class='beats' data-id="${beats[i].id}">
                    <div id="firstpart">
                        <img src="${BASE}/${beats[i].cover}" alt="${beats[i].name}" title="${beats[i].name}">

                        <div>
                            <h1>${beats[i].name}</h1>
                            <div id="details">
                                <p id="price"> €${beats[i].price}</p>
                            </div>
                        </div>
                    </div>

                    <div class="lastdiv" id="${id}">
                        <p>${text}</p>
                    </div>
                </article>`;
}

function loadProducer() {
    fetch(`${BASE_API}/producers/${PRODUCERID}`).then(response => response.json())
        .then(function (result) {
            const producer = result;

            document.title = `${producer.username} - UNIVERSAL BEATS.`;

            addHtmlProducerInfo(producer);

            addToProfileHeader(producer);

            addToSocialMedia(producer);
        })
}

function addHtmlProducerInfo(producer) {
    PRODUCERDETAILS.innerHTML = `<div id="profileheader">
                                        <img src="${BASE}/${producer.avatar}" alt="${producer.username}" title="${producer.username}">
                                        <h1>${producer.username}</h1>
                                     </div>`;
}

function addToSocialMedia(producer) {
    const SOCIALMEDIA = document.querySelector("#socialmedia");
    if (producer.twitter !== null) {
        SOCIALMEDIA.innerHTML += `<p><a target="_blank" href="https://twitter.com/${producer.twitter}">Twitter</a></p>`;
    }

    if (producer.instagram !== null) {
        SOCIALMEDIA.innerHTML += `<p><a target="_blank" href="https://www.instagram.com/${producer.instagram}">Instagram</a></p>`;
    }

    if (producer.youtube !== null) {
        SOCIALMEDIA.innerHTML += `<p><a target="_blank" href="https://www.youtube.com/${producer.youtube}">YouTube</a></p>`;
    }
}

function addToProfileHeader(producer) {
    const PROFILEHEADER = document.querySelector("#profileheader");
    if (producer.name !== null) {
        PROFILEHEADER.innerHTML += `<p>${producer.name}</p>`;
    }

    if (producer.genre !== null) {
        PROFILEHEADER.innerHTML += `<p>${producer.genre}</p>`;
    }

    if (producer.aboutme !== null) {
        PROFILEHEADER.innerHTML += `<p>${producer.aboutme}</p>`;
    }

    PROFILEHEADER.innerHTML += `<div id="socialmedia"></div>`;
}

function loadPersonalFeatures() {
    PRODUCERDETAILS.innerHTML += `<div id="personalfeatures">
                                    <p><a href="soldbeats.html">Sold beats</a></p>
                                    <p><a href="settingsprofile.html">Settings</a></p>
                                    <p id="logout">Logout</p>
                                  </div>`;

    setTimeout(function () {
        document.querySelector("#logout").addEventListener('click', logout);
    }, 1000);
}

function logout(e) {
    e.preventDefault();

    const token = localStorage.getItem("token");

    fetch(`${BASE_API}/auth/logout`, {
        method: "POST",
        headers: {
            Authorization: "Bearer" + token,
            Accept: "application/json",
        },
    })
        .then((response) => response.json())
        .then(function (result) {
            localStorage.setItem('token', null);
            window.location.href = "index.html";
        })
}
