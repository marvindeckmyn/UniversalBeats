"use strict";

const LATESTBEATS = document.querySelector("section#latestbeats div");
const NEWESTPRODUCERS = document.querySelector("section#newestproducers div");

let newestProducers;

document.addEventListener("DOMContentLoaded", init);

function init() {
    showNewestProducers();
}

function showLatestBeats() {
    fetch(`${BASE_API}/beats`).then(response => response.json())
        .then(function (result) {
            const latestBeats = result.slice(result.length - 4).reverse();

            for (let i = 0; i < latestBeats.length; i++) {
                addHtmlLatestBeats(latestBeats, i);
            }

            document.querySelectorAll("section#latestbeats article h2").forEach(beat => {
                beat.addEventListener('click', goToBeatPage);
            });

            document.querySelectorAll("section#latestbeats article img").forEach(beat => {
                beat.addEventListener('click', goToBeatPageViaImg);
            });

            document.querySelectorAll("section#latestbeats article h3").forEach(producer => {
                producer.addEventListener('click', goToProducerPage);
            });
        })
}

function addHtmlLatestBeats(latestBeats, i) {
    LATESTBEATS.innerHTML += `<article data-id="${latestBeats[i].id}">
                                            <figure class="cover">
                                                <img src="${BASE}/${latestBeats[i].cover}" alt="${latestBeats[i].producer_id}" title="${latestBeats[i].name}">
                                            </figure>

                                            <h2>${latestBeats[i].name}</h2>

                                            <div data-id="${latestBeats[i].producer.id}">
                                                <h3>${latestBeats[i].producer.username}</h3>
                                            </div>
                                        </article>`;
}

function showNewestProducers() {
    fetch(`${BASE_API}/producers`).then(response => response.json())
        .then(function (result) {
            newestProducers = result.slice(result.length - 4).reverse();

            showLatestBeats();

            for (let i = 0; i < newestProducers.length; i++) {
                addHtmlNewestProducers(i);
            }

            document.querySelectorAll("section#newestproducers article img").forEach(producer => {
                producer.addEventListener('click', goToProducerPage);
            });

            document.querySelectorAll("section#newestproducers article h2").forEach(producer => {
                producer.addEventListener('click', goToProducerPage);
            });
        })
}

function addHtmlNewestProducers(i) {
    NEWESTPRODUCERS.innerHTML += `<article data-id="${newestProducers[i].id}">
                                            <img src="${BASE}/${newestProducers[i].avatar}" alt="${newestProducers[i].username}" title="${newestProducers[i].username}">
                                            <h2>${newestProducers[i].username}</h2>
                                          </article>`;
}

function goToBeatPage(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.getAttribute('data-id');

    localStorage.setItem('beat', beatId);
    window.location.href = "beat.html";
}

function goToBeatPageViaImg(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.parentNode.getAttribute('data-id');

    localStorage.setItem('beat', beatId);
    window.location.href = "beat.html";
}

function goToProducerPage(e) {
    e.preventDefault();

    const producerId = e.target.parentNode.getAttribute('data-id');

    localStorage.setItem('producer', producerId);
    window.location.href = "producer.html";
}
