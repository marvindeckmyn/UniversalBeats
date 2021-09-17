"use strict";

const INSIGHTSSECTION = document.querySelector("main>section");
const BEAT_ID = localStorage.getItem("beat");

const WIDTH = 960;
const HEIGHT = 500;

const MARGIN = 5;
const PADDING = 5;
const ADJ = 30;

const VIEW_BOX_STRING = "-" + ADJ + "-" + ADJ + " " + (WIDTH + ADJ * 3) + " " + (HEIGHT + ADJ * 3);

const SVG = d3.select("div#container").append("svg")
    .style("padding", PADDING)
    .style("margin", MARGIN)
    .classed("svg-content", true)
    .attr("viewBox", VIEW_BOX_STRING)
    .attr("preserveAspectRatio", "xMinYMin meet");

const TIME_CONV = d3.timeParse("%Y-%m-%d");

document.addEventListener("DOMContentLoaded", init);

function init() {
    loadBeat();
    showPlays();
}

function loadBeat() {
    fetch(`${BASE_API}/beats/${BEAT_ID}`).then(response => response.json())
        .then(function (result) {
            const beat = result;

            fetch(`${BASE_API}/plays`).then(response => response.json())
                .then(function (result) {
                    const plays = result;
                    let totalplays = 0;

                    for (let i = 0; i < plays.length; i++) {
                        if (BEAT_ID == plays[i].beat_id) {
                            totalplays++;
                        }
                    }

                    addInsightsHtml(beat, totalplays);
                })
        })
}

function addInsightsHtml(beat, totalplays) {
    INSIGHTSSECTION.innerHTML += `<article data-id="${beat.id}">
                                            <img src="${BASE}/${beat.cover}" alt="${beat.name}" title="${beat.name}">
                                            <h1>${beat.name}</h1>
                                            <p>Total plays: ${totalplays}</p>
                                        </article>
                                        
                                        <div id="chart">
                                            <h1>Plays per day</h1>
                                        </div>`;
}

function showPlays() {
    fetch(`${BASE_API}/plays`).then(response => response.json())
        .then(function (d) {
            const beatData = d.filter(({ beat_id }) => beat_id == BEAT_ID).map(function (data) {
                return {
                    date: TIME_CONV(data.created_at.slice(0, 10))
                };
            });

            const dayData = getDaysWithPlaysData(beatData);

            const finalBeatData = getAllDaysData(dayData);

            if (finalBeatData.length <= 1) {
                document.querySelector("#container").innerHTML = "<h2>There is no data enough to visualize your insights for this beat.</h2>";
            } else {
                const { lines, line } = visualizeInsights(finalBeatData);
                displayData(lines, line, finalBeatData);
            }
        });
}

function visualizeInsights(finalBeatData) {
    const yMaximum = d3.max(finalBeatData.values(), d => d.plays) + 4;

    const { xScale, yScale } = defineScales(yMaximum, finalBeatData);

    const line = defineLine(xScale, yScale);

    const { xaxis, yaxis } = defineAxises(xScale, yScale);

    const lines = SVG.append("g");

    beautifyAxises(xaxis, yaxis);
    return { lines, line };
}

function getAllDaysData(daydata) {
    const allDayData = d3.timeDay.range(new Date(daydata[0].date), new Date(d3.timeDay()));

    let allDayMap = {};

    fillEmptyIntervals(allDayData, allDayMap, daydata);

    const finalBeatData = returnDaydataAsObject(allDayMap);
    return finalBeatData;
}

function getDaysWithPlaysData(beatdata) {
    let map = {};

    putPlaysPerDay(beatdata, map);

    const dayData = returnDaydataAsObject(map);
    return dayData;
}

function fillEmptyIntervals(allDayData, allDayMap, daydata) {
    let i = 0;
    let j = 0;

    allDayData.forEach(function (val) {
        allDayMap[val] = allDayMap[val] || {};

        if (allDayData[j].getTime() === daydata[i].date.getTime()) {
            allDayMap[val].plays = daydata[i].plays;
            i++;
        } else {
            allDayMap[val].plays = 0;
        }
        j++;
    });
}

function beautifyAxises(xaxis, yaxis) {
    SVG.append("g")
        .attr("class", "axis")
        .attr("transform", `translate(0, ${HEIGHT})`)
        .call(xaxis);

    SVG.append("g")
        .attr("class", "axis")
        .call(yaxis)
        .append("text")
        .attr("fill", "black")
        .text("Plays")
        .attr("transform", "rotate(-90)")
        .attr("y", 6)
        .attr("dy", ".75em")
        .style("text-anchor", "end");
}

function displayData(lines, line, daydata) {
    lines.append("path")
        .attr("d", line(daydata.values()))
        .attr("class", d => "line");
}

function defineAxises(xScale, yScale) {
    const xaxis = d3.axisBottom()
        .ticks(d3.timeDay.every(1))
        .tickFormat(d3.timeFormat("%b %d"))
        .scale(xScale);

    const yaxis = d3.axisLeft()
        .scale(yScale);
    return { xaxis, yaxis };
}

function defineLine(xScale, yScale) {
    return d3.line()
        .x(d => xScale(d.date))
        .y(d => yScale(d.plays));
}

function defineScales(yMaximum, daydata) {
    const yScale = d3.scaleLinear()
        .domain([0, yMaximum])
        .range([HEIGHT, 0]);

    const xScale = d3.scaleTime()
        .domain(d3.extent(daydata.values(), d => d.date))
        .range([0, WIDTH]);
    return { xScale, yScale };
}

function returnDaydataAsObject(map) {
    return Object.keys(map).map(function (key) {
        return {
            date: new Date(key),
            plays: map[key].plays
        };
    });
}

function putPlaysPerDay(beatdata, map) {
    beatdata.forEach(function (val) {
        let key = "plays";
        map[val.date] = map[val.date] || {};
        map[val.date].plays = map[val.date].plays || 0;
        map[val.date][key]++;
    });
}

