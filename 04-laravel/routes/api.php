<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\BeatsApiController;
use App\Http\Controllers\UsersApiController;
use App\Http\Controllers\JwtAuthController;
use App\Http\Controllers\PushController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::group(["prefix" => "beats"], function() {
    Route::get("/", [BeatsApiController::class, "beats"]);
    Route::get("/{id}", [BeatsApiController::class, "beat"]);
    Route::post("/buy", [BeatsApiController::class, "buyBeat"]);
});

Route::group(["prefix" => "producers"], function() {
    Route::get("/", [UsersApiController::class, "producers"]);
    Route::get("/{id}", [UsersApiController::class, "producer"]);
});

Route::group(["prefix" => "plays"], function() {
    Route::get("/", [BeatsApiController::class, "plays"]);
    Route::post("/{id}", [BeatsApiController::class, "play"]);
});

Route::group(["prefix" => "/{lang}"], function() {
    Route::get("/moods/{id}", [BeatsApiController::class, "mood"]);
});

Route::group(["prefix" => "auth"], function() {
    Route::post("/register", [JwtAuthController::class, "register"]);
    Route::post("/login", [JwtAuthController::class, "login"]);

    Route::middleware(["auth.jwt"])->group(function() {
        Route::post("/token/refresh", [JwtAuthController::class, "token.refresh"]);
        Route::post("/logout", [JwtAuthController::class, "logout"]);
    });
});

Route::middleware(["auth.jwt"])->group(
    function() {
        Route::get("/user", [JwtAuthController::class, "user"]);
        Route::get("/soldbeats", [UsersApiController::class, "soldBeats"]);
        Route::put("/updateaccount", [UsersApiController::class, "updateAccount"]);
        Route::put("/changepassword", [UsersApiController::class, "changePassword"]);
        Route::post("/push", [PushController::class, "store"]);
    }
);
