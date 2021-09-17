"use strict";

const PLAYBUTTON = "#title img[alt='play']";
const PAUSEBUTTON = "#title img[alt='pause']";

var wavesurfer;

document.addEventListener("DOMContentLoaded", init);

function init() {
    setTimeout(function () {
        createWavesurferInstance();
        document.querySelector(PLAYBUTTON).addEventListener("click", playAudio);
        document.querySelector(PAUSEBUTTON).addEventListener("click", pauseAudio);

        wavesurfer.on("finish", switchToPlayButton);
    }, 2000);
}

function createWavesurferInstance() {
    const audioFile = document.querySelector("#waveform audio").src;
    wavesurfer = WaveSurfer.create({
        container: '#waveform',
        waveColor: 'rgba(126, 90, 161, 0.6)',
        progressColor: 'rgb(126, 90, 161)',
        backend: 'MediaElement',
        barWidth: 3,
        barRadius: 3,
        cursorColor: 'rgb(126, 90, 161)',
        hideScrollbar: 'true',
        mediaType: "audio",
        responsive: true
    });

    wavesurfer.load(audioFile);
}

function playAudio(e) {
    e.preventDefault();

    wavesurfer.play();
    switchToPauseButton();
}

function pauseAudio(e) {
    e.preventDefault();

    wavesurfer.pause();
    switchToPlayButton();
}

function switchToPlayButton() {
    document.querySelector(PLAYBUTTON).style.display = "initial";
    document.querySelector(PAUSEBUTTON).style.display = "none";
}

function switchToPauseButton() {
    document.querySelector(PLAYBUTTON).style.display = "none";
    document.querySelector(PAUSEBUTTON).style.display = "initial";
}
