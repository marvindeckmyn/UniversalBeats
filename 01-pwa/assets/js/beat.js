"use strict";

const BEATDETAILS = document.querySelector("section");
const BEATID = localStorage.getItem("beat");

document.addEventListener("DOMContentLoaded", init);

function init() {
    loadBeat();
    registerPlay();
}

function registerPlay() {
    fetch(`${BASE_API}/plays/${BEATID}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json());
}

function loadBeat() {
    fetch(`${BASE_API}/beats/${BEATID}`).then(response => response.json())
        .then(function (result) {
            const beat = result;
            const producer = beat.producer;

            if (localStorage.getItem("lang") === "nl") {
                loadDetails(beat, producer, "nl");
            } else {
                loadDetails(beat, producer, "en");
            }
        })
}

function loadOtherBeats(beat, producer, lang) {
    fetch(`${BASE_API}/beats`).then(response => response.json())
        .then(function (result) {
            const beats = result.reverse();
            const main = document.querySelector("main");

            addHtmlOtherBeats(lang, main, beats, producer, beat);

            addLangSection(main);

            document.querySelectorAll("section#otherbeats img").forEach(beat => {
                beat.addEventListener('click', goToBeat);
            });
        });
}

function addHtmlOtherBeats(lang, main, beats, producer, beat) {
    if (lang === "nl") {
        main.innerHTML += `<section id="otherbeats">
                                <h1>Andere beats van deze producer</h1>
                            </section>`;
    } else {
        main.innerHTML += `<section id="otherbeats">
                                    <h1>Other beats from this producer</h1>
                                </section>`;
    }
    const section = document.querySelector("section#otherbeats");

    for (let i = 0; i < beats.length; i++) {
        if (beats[i].producer_id === producer.id && beats[i].id !== beat.id) {
            section.innerHTML += `<article data-id="${beats[i].id}">
                                        <img src="${BASE}/${beats[i].cover}" alt="${beats[i].name}" title="${beats.name}">
                                    </article>`;
        }
    }
}

function addLangSection(main) {
    main.innerHTML += `<section id="lang">
                            <ul>
                                <li>EN</li>
                                <li>NL</li>
                            </ul>
                            </section>`;

    document.querySelectorAll("#lang ul li").forEach(lang => {
        lang.addEventListener('click', setLang);
    });
}

function setLang(e) {
    e.preventDefault();

    const lang = e.target.innerHTML.toLowerCase();
    localStorage.setItem("lang", lang);

    location.reload();
}

function goToBeat(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.getAttribute('data-id');

    localStorage.setItem('beat', beatId);
    window.location.href = "beat.html";
}

function loadAudio(beat) {
    const main = document.querySelector("main");

    main.innerHTML += `<section id="waveform">
                            <audio src="${BASE}/${beat.audio}"></audio>
                        </section>`;
}

function loadDetails(beat, producer, lang) {
    if (lang === "nl") {
        document.title = `${beat.name} door ${producer.username} - UNIVERSAL BEATS.`;
    } else {
        document.title = `${beat.name} by ${producer.username} - UNIVERSAL BEATS.`;

    }

    fetch(`${BASE_API}/plays`).then(response => response.json())
        .then(function (result) {
            const plays = result;
            let playsBeat = 0;

            playsBeat = countPlays(plays, beat, playsBeat);

            fetch(`${BASE_API}/${lang}/moods/${beat.mood_id}`).then(response => response.json())
                .then(function (result) {
                    const mood = result;

                    addHtmlDetails(beat, lang, producer, mood, playsBeat);

                    setTimeout(function () {
                        document.querySelector("article span").addEventListener('click', goToProducerPage);
                        document.querySelector("article #cart p").addEventListener('click', addBeatToCart);
                    }, 1000);

                    loadAudio(beat);
                    loadOtherBeats(beat, producer, lang);
                });
        });
}

function addHtmlDetails(beat, lang, producer, mood, playsBeat) {
    BEATDETAILS.innerHTML = `<article data-id="${beat.id}"></article>`;
    const beatArticle = document.querySelector("article");

    beatArticle.innerHTML += `<figure>
                                        <img src="${BASE}/${beat.cover}" alt="${beat.name}" title="${beat.name}">
                                    </figure>`;

    if (lang === "nl") {
        beatArticle.innerHTML += `<div id="title">
                                                <img src="assets/media/play.svg" alt="play" title="Play">
                                                <img src="assets/media/pause.svg" alt="pause" title="Pause">
                                                <h1>${beat.name} door <span data-id="${producer.id}">${producer.username}</span></h1>
                                            </div>

                                            <div id="details">
                                                <p>${beat.genre}</p>
                                                <p>${beat.bpm} bpm</p>
                                                <p>${mood.name}</p>
                                            </div>

                                            <div id="cartandplays">
                                                <div id="cart">
                                                    <p>Koop €${beat.price}</p>
                                                </div>
                                                <div id="plays">
                                                    <p>${playsBeat} keer afgespeeld</p>
                                                </div>
                                            </div>`;

    } else {
        beatArticle.innerHTML += `<div id="title">
                                                <img src="assets/media/play.svg" alt="play" title="Play">
                                                <img src="assets/media/pause.svg" alt="pause" title="Pause">
                                                <h1>${beat.name} by <span data-id="${producer.id}">${producer.username}</span></h1>
                                            </div>

                                            <div id="details">
                                                <p>${beat.genre}</p>
                                                <p>${beat.bpm} bpm</p>
                                                <p>${mood.name}</p>
                                            </div>

                                            <div id="cartandplays">
                                                <div id="cart">
                                                    <p>Buy €${beat.price}</p>
                                                </div>
                                                <div id="plays">
                                                    <p>${playsBeat} plays</p>
                                                </div>
                                            </div>`;
    }

    const details = document.querySelector("#details");

    if (beat.tag) {
        details.innerHTML += `<p>#${beat.tag}</p>`;
    }
}

function countPlays(plays, beat, playsBeat) {
    for (let i = 0; i < plays.length; i++) {
        if (plays[i].beat_id === beat.id) {
            playsBeat++;
        }
    }
    return playsBeat;
}

function addBeatToCart(e) {
    e.preventDefault();

    const beatId = e.target.parentNode.parentNode.parentNode.getAttribute('data-id');
    const beatName = e.target.parentNode.parentNode.parentNode.querySelector("figure img").title;
    const beatPrice = e.target.parentNode.parentNode.parentNode.querySelector("#cartandplays #cart p").innerHTML.substring(5);
    const beatCover = e.target.parentNode.parentNode.parentNode.querySelector("figure img").src;

    const beatCart = {
        id: beatId, name: beatName, price: beatPrice, cover: beatCover
    };

    const trx = db.transaction("cart", "readwrite");
    const cartStore = trx.objectStore("cart");

    cartStore.add(beatCart);
    localStorage.setItem("cart", "true");
    window.location.href = "cart.html";
}

function goToProducerPage(e) {
    e.preventDefault();

    const producerId = e.target.getAttribute('data-id');

    localStorage.setItem('producer', producerId);
    window.location.href = "producer.html";
}
