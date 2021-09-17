"use strict";

const SECTIONLIST = document.querySelector("section ul");

document.addEventListener("DOMContentLoaded", init);

function init() {
    showSoldBeats();
}

function showSoldBeats() {
    const token = localStorage.getItem("token");

    fetch(`${BASE_API}/soldbeats`, {
        method: "GET",
        headers: {
            Authorization: "Bearer" + token,
            "Content-Type": "application/json",
        },
    })
        .then((response) => response.json())
        .then(function (result) {
            const soldbeats = result.reverse();

            for (let i = 0; i < soldbeats.length; i++) {
                if (soldbeats[i].notifiable_id == localStorage.getItem("producer") && soldbeats[i].read_at === null) {
                    const beat = JSON.parse(soldbeats[i].data);
                    addSoldBeatsHtml(beat, soldbeats, i);
                }
            }
        });
}
function addSoldBeatsHtml(beat, soldbeats, i) {
    SECTIONLIST.innerHTML += `<li>
                                    <p>Your beat <span>${beat.beat_name}</span> has been sold for €${beat.beat_price}.</p>
                                    <p>${(soldbeats[i].created_at).substring(0, 10)}</p>
                                </li>`;
}

