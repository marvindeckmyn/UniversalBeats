"use strict";

const CARTSECTION = document.querySelector("section");

request.onsuccess = function (e) {
    db = e.target.result;
    loadCart();
}

document.addEventListener("DOMContentLoaded", init);

function init() {
    if (!navigator.onLine)
        handleOffline();

    window.addEventListener("online", handleOnlineCart);
    window.addEventListener("offline", handleOfflineCart);
}

function buy(e) {
    e.preventDefault();

    const trx = db.transaction("cart", "readwrite");
    const cartStore = trx.objectStore("cart");

    cartStore.getAll().onsuccess = function (e) {
        const cart = putBeatsInArray(e);

        fetch(`${BASE_API}/beats/buy`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(cart),
        })
            .then((response) => response.json());

        clearCartAndRedirect(cartStore);
    }
}

function putBeatsInArray(e) {
    const beatsInCart = e.target.result;

    const cartArray = [];

    for (let i = 0; i < beatsInCart.length; i++) {
        cartArray.push(parseInt(beatsInCart[i].id));
    }

    const cart = {
        cart: cartArray
    };
    return cart;
}

function clearCartAndRedirect(cartStore) {
    cartStore.clear();
    localStorage.setItem("cart", "false");
    window.location.href = "thank-you.html";
}

function emptyCart(e) {
    e.preventDefault();
    const trx = db.transaction("cart", "readwrite");
    const cartStore = trx.objectStore("cart");

    clearCartAndReload(cartStore);
}

function clearCartAndReload(cartStore) {
    cartStore.clear();
    localStorage.setItem("cart", "false");
    location.reload();
}

function loadCart() {
    if (localStorage.getItem("cart") === "true") {
        const trx = db.transaction("cart");
        const cartStore = trx.objectStore("cart");

        cartStore.getAll().onsuccess = function (e) {
            const beatsInCart = e.target.result;
            AddHtmlCartIndications();

            AddHtmlCartBeats(beatsInCart);
        }
    } else {
        CARTSECTION.innerHTML += "<p>Your cart is empty</p>";
    }
}
function linkForms() {
    const EMPTYCART = "form input[name='empty']";
    const BUY = "form input[name='buy']";

    document.querySelector(EMPTYCART).addEventListener('click', emptyCart);
    document.querySelector(BUY).addEventListener('click', buy);
}

function AddHtmlCartBeats(beatsInCart) {
    let totalPrice = 0;

    for (let i = 0; i < beatsInCart.length; i++) {
        document.querySelector("section ul").innerHTML += `
        <li>
                <img src="${beatsInCart[i].cover}" alt="${beatsInCart[i].name}" title="${beatsInCart[i].name}">
                <p>${beatsInCart[i].name}</p>
                <p class="individual-price">€<span>${beatsInCart[i].price}</span></p>
        </li> 
                                                        `;

        totalPrice += parseInt(beatsInCart[i].price);
    }


    CARTSECTION.innerHTML += `<p id="total">Total: €<strong>${totalPrice}</strong></p>
                                    <div id="forms">
                                        <form>
                                            <input type="button" name="empty" value="Empty cart">
                                        </form>

                                        <form>
                                            <input type="button" name="buy" value="Buy">
                                        </form>
                                    </div>`;
    linkForms();
}

function AddHtmlCartIndications() {
    CARTSECTION.innerHTML += `
                                <ul>
                                    <li id="cart-indications">
                                        <p>Cover art</p>
                                        <p>Track title</p>
                                        <p class="individual-price">Price</p>
                                    </li>
                                </ul>
                                `;
}

function handleOfflineCart() {
    const forms = document.querySelector("#forms");

    forms.innerHTML = "<p>You can buy beats when you are online</p>";
    forms.style.background = "rgb(236, 52, 52)";
    forms.style.color = "white";
    forms.style.textAlign = "center";
    forms.style.padding = "15px";
}

function handleOnlineCart() {
    const forms = document.querySelector("#forms");

    forms.innerHTML = `<div id="forms">
                            <form>
                                <input type="button" name="empty" value="Empty cart">
                            </form>

                            <form>
                                <input type="button" name="buy" value="Buy">
                            </form>
                        </div>`;
    linkForms();
    forms.style.background = "#e9eaed";
    forms.style.color = "initial";
    forms.style.textAlign = "initial";
    forms.style.padding = "initial";
}
