"use strict";

const mysql = require("mysql");

let config = {
    host:       "us-cdbr-east-03.cleardb.com",
    port:       3306,
    database:   "heroku_4d5cf3f565482d3",
    user:       "b4c0797768ddd6",
    password:   "51a3b7d2",
};

function row2play(row) {
    return {
        beat_id: row.beat_id
    }
}

function row2beat(row) {
    return {
        id: row.id,
        name: row.name,
        producer_id: row.producer_id,
        cover: row.cover,
        genre: row.genre,
        tag: row.tag,
        bpm: row.bpm,
        mood: row.mood,
        price: row.price,
        audio: row.audio
    }
}

function row2producer(row) {
    return {
        id: row.id,
        username: row.username,
        name: row.name,
        avatar: row.avatar,
        background: row.background,
        aboutme: row.aboutme,
        genre: row.genre,
        twitter: row.twitter,
        instagram: row.instagram,
        youtube: row.youtube,
        email: row.email
    }
}

function row2user(row) {
    return {
        id: row.id,
        username: row.username,
        password: row.password
    }
}

function row2genre(row) {
    return {
        id: row.id,
        name: row.name
    }
}

function row2mood(row) {
    return {
        id: row.id,
        name: row.name
    }
}

function row2soldbeat(row) {
    return {
        data: row.data,
        created_at: row.created_at + ""
    }
}

function getMoods(cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = "SELECT * from moods";
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2mood));
                }
            });
        }
    });
}

function getGenres(cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = "SELECT * from genres";
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2genre));
                }
            });
        }
    });
}

function getBeats(cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = "SELECT * from beats";
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2beat));
                }
            });
        }
    });
}

function getBeat(id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from beats where id=${id}`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2beat));
                }
            });
        }
    });
}

function getBeatsByProducerId(producer_id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from beats where producer_id=${producer_id}`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2beat));
                }
            })
        }
    })
}

function getSoldBeats(id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from notifications where notifiable_id=${id}`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2soldbeat));
                }
            });
        }
    });
}

function getPlays(beat_id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from plays where beat_id=${beat_id}`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2play));
                }
            });
        }
    });
}

function getProducers(cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = "SELECT * from users";
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2producer));
                }
            });
        }
    });
}

function loginUser(username, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from users where username='${username}'`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2user));
                }
            });
        }
    });
}

function getProducer(id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from users where id=${id}`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2producer));
                }
            });
        }
    });
}

function getUserById(id, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from users where id='${id}'`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2user));
                }
            })
        }
    })
}

function getProducerByUsername(username, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if(err) {
            cb(err);
        } else {
            let sql = `SELECT * from users where username='${username}'`;
            connection.query(sql, (err, rows) => {
                connection.end();
                if (err) {
                    return cb(err);
                } else {
                    return cb(err, rows.map(row2producer));
                }
            })
        }
    })
}

function createBeat(id, name, producerId, cover, genre, tag, bpm, mood, price, audio, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            const producerType = "App\Models\User";
            let sql = "INSERT INTO `beats`(`id`, `name`, `producer_id`, `cover`, `genre`, `tag`, `bpm`, `mood`, `price`, `audio`, `producer_type`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            connection.query(sql, [id, name, producerId, cover, genre, tag, bpm, mood, price, audio, producerType], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    });
}

function registerPlay(beat_id, created_at, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "INSERT INTO `plays`(`beat_id`, `created_at`, `updated_at`) VALUES(?, ?, ?);";
            connection.query(sql, [beat_id, created_at, updated_at], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    });
}

function buyBeat(id, type, notifiable_type, notifiable_id, data, read_at, created_at, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "INSERT INTO `notifications`(`id`, `type`, `notifiable_type`, `notifiable_id`, `data`, `read_at`, `created_at`, `updated_at`) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
            connection.query(sql, [id, type, notifiable_type, notifiable_id, data, read_at, created_at, updated_at], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    });
}

function registerUser(id, username, name, email, password, avatar, background, aboutme, genre, twitter, instagram, youtube, created_at, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "INSERT INTO `users`(`id`, `username`, `name`, `email`, `password`, `avatar`, `background`, `aboutme`, `genre`, `twitter`, `instagram`, `youtube`, `created_at`, `updated_at`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            connection.query(sql, [id, username, name, email, password, avatar, background, aboutme, genre, twitter, instagram, youtube, created_at, updated_at], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    });
}

function updateUserInfo(id, username, name, email, aboutme, genre, twitter, instagram, youtube, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "UPDATE `users` SET `username` = ?, `name` = ?, `email` = ?, `aboutme` = ?, `genre` = ?, `twitter` = ?, `instagram` = ?, `youtube` = ?, `updated_at` = ? WHERE `id`= ?";
            connection.query(sql, [username, name, email, aboutme, genre, twitter, instagram, youtube, updated_at, id], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    })
}

function changeAvatar(id, avatar, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "UPDATE `users` SET `avatar` = ?, `updated_at` = ? WHERE `id` = ?";
            connection.query(sql, [avatar, updated_at, id], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    })
}

function changePassword(id, password, updated_at, cb) {
    let connection = mysql.createConnection(config);

    connection.connect((err) => {
        if (err) {
            cb(err);
        } else {
            let sql = "UPDATE `users` SET `password` = ?, `updated_at` = ? WHERE `id` = ?";
            connection.query(sql, [password, updated_at, id], (err, result) => {
                connection.end();
                if (err) cb(err);
                else cb(err, true);
            });
        }
    })
}

module.exports = {
    getBeats,
    getBeat,
    getBeatsByProducerId,
    getProducers,
    getProducer,
    getProducerByUsername,
    getUserById,
    getGenres,
    getMoods,
    getPlays,
    getSoldBeats,
    createBeat,
    buyBeat,
    registerUser,
    registerPlay,
    loginUser,
    updateUserInfo,
    changeAvatar,
    changePassword
}
