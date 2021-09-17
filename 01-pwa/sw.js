"use strict";

const CACHE_NAME = "universalbeats-v1";

const CACHED_URLS = [
    "./cart.html",
    "./assets/css/reset.css",
    "./assets/css/screen.css",
    "./assets/css/cart.css",
    "./assets/js/script.js",
    "./assets/js/cart.js"
]

self.addEventListener("install", function(e) {
    e.waitUntil(
        caches.open(CACHE_NAME).then(cache => {
            return cache.addAll(CACHED_URLS);
        })
    )
});

self.addEventListener("fetch", function(e) {
    e.respondWith(fetch(e.request)
        .catch(() => {
            return caches.open(CACHE_NAME).then(cache => {
                return cache.match(e.request);
            })
        }));
});

self.addEventListener("push", e => {
    const msg = e.data.json();
    self.registration.showNotification(msg.title, {
        body: msg.body,
        icon: msg.icon,
        actions: msg.actions
    });
});