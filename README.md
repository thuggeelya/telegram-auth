# Test Telegram Authentication

Вывод пользовательских данных с помощью Telegram Mini App.

---

## Инструкция к запуску

### 1. Установить зависимости

- Docker ([Docker Desktop](https://www.docker.com/products/docker-desktop/) для Windows и MacOS) и перезагрузиться

### 2. Зарегистрироваться на [ngrok.com](https://dashboard.ngrok.com/signup)

- найдите [authtoken](https://dashboard.ngrok.com/get-started/your-authtoken) в личном кабинете

### 3. Клонировать проект

```bash
git clone https://github.com/thuggeelya/telegram-auth.git
```

### 4. Определить глобальные переменные

- перейти в директорию `.ci`

- создать файл `.env`:

```text
POSTGRES_USERNAME=postgres
POSTGRES_PASSWORD=postgres
TELEGRAM_BOT_TOKEN=<токен бота Telegram (например, из BotFather)>
NGROK_AUTHTOKEN=<authtoken из профиля ngrok>
```

### 5. Запустить проект

```bash
# MacOS
docker-compose up --build
```

```bash
# Windows/Linux-like
docker compose up --build
```

- в случае ошибок ngrok контейнера попробуйте включить VPN и пересобрать контейнеры

### 6. Указать webapp URL для бота

- скопировать [адрес ngrok тоннеля](https://dashboard.ngrok.com/endpoints) (будет доступен после запуска ngrok
  контейнера)

- перейти в [BotFather](https://t.me/BotFather)

- выполнить команду `/setdomain` для нужного бота

- указать скопированный адрес ngrok тоннеля

---

## Пример использования

- найдите ID своего Telegram-аккаунта в [боте](https://t.me/userinfobot)

- если с этого аккаунта в Telegram у вас нет активного диалога с вашим ботом, перейдите в него и выполните
  команду `/start`

- используйте его, URL тоннеля и токен бота для отправки тестового сообщения (замените в запросе):

```bash
curl --location 'https://api.telegram.org/bot<токен вашего бота>/sendMessage' \
--header 'Content-Type: application/json' \
--data '{
    "chat_id": "ваш ID",
    "text": "Открыть WebApp",
    "reply_markup": {
        "inline_keyboard": [
            [
                {
                    "text": "Открыть WebApp",
                    "web_app": {
                        "url": "ваш тоннель"
                    }
                }
            ]
        ]
    }
}'
```

- пример запроса:

```bash
curl --location 'https://api.telegram.org/bot7974944985:AAGYvDfeAjOE7fV5kCAEzghjvtObFh2HXfc/sendMessage' \
--header 'Content-Type: application/json' \
--data '{
    "chat_id": "772927285",
    "text": "Открыть WebApp",
    "reply_markup": {
        "inline_keyboard": [
            [
                {
                    "text": "Открыть WebApp",
                    "web_app": {
                        "url": "https://048c-45-12-145-84.ngrok-free.app"
                    }
                }
            ]
        ]
    }
}'
```

- откройте мини-приложение по ссылке из вашего бота
