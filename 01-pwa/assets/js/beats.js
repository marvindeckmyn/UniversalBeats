"use strict";

const BEATS = document.querySelector("section");
document.addEventListener("DOMContentLoaded", init);

function init() {
    showBeats();
}

function showBeats() {
    fetch(`${BASE_API}/beats`).then(response => response.json())
        .then(function (result) {
            const beats = result.reverse();

            fetch(`${BASE_API}/producers`).then(response => response.json())
                .then(function (result) {
                    const producers = result;

                    for (let i = 0; i < beats.length; i++) {
                        addBeatsHtml(beats, i, producers);
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
