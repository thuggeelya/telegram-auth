/* global Telegram */

window.onload = function () {

    if (Telegram.WebApp.colorScheme === "dark") {
        document.body.classList.add("dark");
    }

    const url = new URL(window.location.href);

    if (url.searchParams.has("initData")) {
        return;
    }

    if (typeof Telegram === "undefined" || !Telegram.WebApp || !Telegram.WebApp.initData) {

        document.body.innerHTML =
            `<p><b>Error:</b> WebApp is not running or no initData</p>` +
            `<p><code>Telegram = ${Telegram}</code></p>` +
            `<p><code>Telegram.WebApp = ${Telegram.WebApp}</code></p>` +
            `<p><code>Telegram.WebApp.initData = ${Telegram.WebApp.initData}</code></p>`;
        return;
    }

    const initData = Telegram.WebApp.initData;
    window.location.href = "/?initData=" + encodeURIComponent(initData);
};