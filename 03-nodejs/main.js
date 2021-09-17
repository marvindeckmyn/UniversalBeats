"use strict";
const http = require("http");
const express = require("express");
const data = require("./data");
const path = require("path");
const upload = require("express-fileupload");
const fs = require("fs");
const bcrypt = require("bcryptjs");
const moment = require("moment");

const app = express();

app.use(express.json({limit: '1000mb', extended: true}));
app.use(express.urlencoded({ limit: '1000mb', extended: true }));

app.get("/api/moods", (req, res) => {
    data.getMoods((err, moods) => {
        res.json(moods);
    });
});

app.get("/api/genres", (req, res) => {
    data.getGenres((err, genres) => {
        res.json(genres);
    });
});

app.get("/api/beats", (req, res) => {
    data.getBeats((err, beats) => {
        res.json(beats);
    });
});

app.get("/api/beats/:id", (req, res) => {
    const id = req.params.id;
    data.getBeat(id, (err, beat) => {
        res.json(beat);
    });
});

app.get("/api/beats/producer/:producer_id", (req, res) => {
    const producer_id = req.params.producer_id;
    data.getBeatsByProducerId(producer_id, (err, beats) => {
        res.json(beats);
    });
});

app.get("/api/producers", (req, res) => {
    data.getProducers((err, producers) => {
        res.json(producers);
    });
});

app.get("/api/producers/:id", (req, res) => {
    const id = req.params.id;
    data.getProducer(id, (err, producer) => {
        res.json(producer);
    });
});

app.get("/api/producers/user/:username", (req, res) => {
    const username = req.params.username;
    data.getProducerByUsername(username, (err, producer) => {
        res.json(producer);
    });
});

app.get("/api/soldbeats/:id", (req, res) => {
    const id = req.params.id;
    data.getSoldBeats(id, (err, soldbeats) => {
        res.json(soldbeats);
    });
});

app.get("/api/plays/:beat_id", (req, res) => {
    const beat_id = req.params.beat_id;
    data.getPlays(beat_id, (err, plays) => {
        res.json(plays);
    });
});

app.post("/api/login", (req, res) => {
    const username = req.body.username;
    const password = req.body.password;

    data.loginUser(username, (err, user) => {
        bcrypt.compare(password, user[0].password, function(err, result) {
            if (result === true) {
                res.end("k");
            } else {
                res.end("nok");
            }
        });
    });
});

app.post("/api/beats", (req, res) => {
    const cover = req.body.cover;
    const base64DataCover = cover.replace(/^data:image\/png;base64,/, "");
    const coverName = req.body.id + req.body.name + '.jpeg';
    const coverSqlName = "cover/" + coverName;

    fs.writeFile('www/cover/' + coverName, base64DataCover, 'base64', function(err) {
        console.log(err);
    });

    const audio = req.body.audio;
    const base64DataAudio = audio.replace(/^data:image\/png;base64,/, "");
    const audioName = req.body.id + req.body.name + '.mp3';
    const audioSqlName = "audio/" + audioName;

    fs.writeFile('www/audio/' + audioName, base64DataAudio, 'base64', function(err) {
        console.log(err);
    });

    data.createBeat(req.body.id, req.body.name, req.body.producer_id, coverSqlName, req.body.genre, req.body.tag, req.body.bpm, req.body.mood, req.body.price, audioSqlName, (err, success) => {
        res.end(success?"ok":"nok");
    })
});

app.post("/api/register", (req, res) => {
    data.getProducerByUsername(req.body.username, (err, producer) => {
        if (passwordIsConfirmed(req) && producer.length === 0) {
            bcrypt.hash(req.body.password, 10, function(err, hash) {
                const avatar = "avatar/logo.png";
                const background = "avatar/background.png";
                const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
                data.registerUser(req.body.id, req.body.username, req.body.name, req.body.email, hash, avatar, background, req.body.aboutme, req.body.genre, req.body.twitter, req.body.instagram, req.body.youtube, date, date, (err, success) => {
                    res.end(success?"k":"nok");
                })
            })
        } else {
            res.end("nok");
        }
    })
});

app.post("/api/plays", (req, res) => {
    const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
    data.registerPlay(req.body.beat_id, date, date, (err, success) => {
        res.end(success?"k":"nok");
    })
});

app.post("/api/buybeat", (req, res) => {
    const id = makeid(35);
    const type = "App\Notifications\SoldBeat";
    const notifiable_type = "App\Models\User";
    const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
    data.buyBeat(id, type, notifiable_type, req.body.notifiable_id, req.body.data, date, date, date, (err, success) => {
        res.end(success?"k":"nok");
    })
});

app.put("/api/changepassword", (req, res) => {
    const oldpassword = req.body.oldpassword;
    const newpassword = req.body.newpassword;
    const confirmpassword = req.body.confirmpassword;

    if (newpassword === confirmpassword) {
        data.getUserById(req.body.id, (err, producer) => {
            bcrypt.compare(oldpassword, producer[0].password, function(err, result) {
                if (result === true) {
                    bcrypt.hash(newpassword, 10, function(err, hash) {
                        const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
                        data.changePassword(req.body.id, hash, date, (err, success) => {
                            res.end(success?"k":"nok");
                        })
                    })
                } else {
                    res.end("nok");
                }
            });
        })
    } else {
        res.end("nok");
    }
});

app.put("/api/updateuserinfo", (req, res) => {
    data.getProducerByUsername(req.body.username, (err, producer) => {
        if (producer.length === 0 || producer[0].id == req.body.id) {
            const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
            data.updateUserInfo(req.body.id, req.body.username, req.body.name, req.body.email, req.body.aboutme, req.body.genre, req.body.twitter, req.body.instagram, req.body.youtube, date, (err, success) => {
                res.end(success?"k":"nok");
            })
        } else {
            res.end("nok");
        }
    })
});

app.put("/api/changeavatar", (req, res) => {
    data.getProducer(req.body.id, (err, producer) => {
        if (producer[0].avatar != "avatar/logo.png") {
            fs.unlink("www/" + producer[0].avatar, function(err) {
                console.log(err);
            });
        }

        const avatar = req.body.avatar;
        const base64DataAvatar = avatar.replace(/^data:image\/png;base64,/, "");
        const avatarName = req.body.id + '.jpeg';
        const avatarSqlName = "avatar/" + avatarName;
    
        fs.writeFile('www/avatar/' + avatarName, base64DataAvatar, 'base64', function(err) {
            console.log(err);
        });

        const date = moment(Date.now()).format('YYYY-MM-DD HH:mm:ss');
        data.changeAvatar(req.body.id, avatarSqlName, date, (err, success) => {
            res.end(success?"k":"nok");
        })
    })
});

function makeid(length) {
    var result           = [];
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result.push(characters.charAt(Math.floor(Math.random() * 
 charactersLength)));
   }
   return result.join('');
}

app.use(express.static(path.join(__dirname, 'www')));

const server = http.createServer(app);

server.listen(process.env.PORT);
function passwordIsConfirmed(req) {
    return req.body.password === req.body.confirmpassword;
}
