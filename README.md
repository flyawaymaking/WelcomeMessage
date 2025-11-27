# WelcomeMessage | Кастомные сообщения входа

[![Minecraft](https://img.shields.io/badge/Minecraft-1.21.8-green)](https://www.minecraft.net/)
[![Paper](https://img.shields.io/badge/Paper-Server-orange)](https://papermc.io/)

Плагин для серверов Minecraft Paper 1.21.8, который заменяет стандартные сообщения о входе и выходе игроков на кастомные с поддержкой современного форматирования, градиентов и совместимостью с LuckPerms.

## 📖 Описание

**WelcomeMessage** — это легковесный плагин, который позволяет полностью кастомизировать сообщения о входе и выходе игроков на сервере. Поддерживает MiniMessages форматирование.

**✨ Ключевые особенности:**
- **🎯 Кастомные сообщения:** Настройте уникальные сообщения для входа, первого входа и выхода
- **👑 Поддержка LuckPerms:** Автоматическое использование префиксов и суффиксов из LuckPerms
- **🎨 Современное форматирование:** MiniMessages форматирование
- **🔇 Совместимость с Essentials:** Поддержка silentjoin
- **⚙️ Простая настройка:** Все сообщения настраиваются через config.yml

## ⌨️ Команды

**Для администраторов (требуется право `welcomemessage.reload`):**
- `/welcomemessage reload` — Перезагружает конфигурацию плагина

## 🔐 Настройка прав (Permissions)

| Право | Описание | Кому выдавать |
|-------|-----------|----------------|
| `welcomemessage.reload` | Дает доступ к перезагрузке конфигурации | Администраторам |
| `essentials.silentjoin` | Скрывает сообщение о входе (из Essentials) | Администраторам |

## ⚙️ Конфигурация

### config.yml
```
# Сообщения при входе и выходе игроков
# Поддержка MiniMessage формата: https://docs.advntr.dev/minimessage/format.html
# Чтобы скрыть сообщение нужно указать "", например: quit-message: ""

# Формат отображения сообщений
# Доступные плейсхолдеры:
# %prefix% - префикс игрока из LuckPerms
# %player% - имя игрока
# %suffix% - суффикс игрока из LuckPerms
# %message% - основное сообщение (welcome-message, first-time-message, quit-message)
message-format: "%prefix%%player%%suffix% %message%"

# Основные сообщения
welcome-message: "<green>зашёл на сервер!"
first-time-message: "<green><bold>зашёл на сервер впервые!"
quit-message: "<red>вышел с сервера."

messages:
  no-permissions: "<red>У вас нет прав для этой команды!"
  config-reloaded: "<green>Конфиг WelcomeMessage перезагружен!"
  usage: "<yellow>Использование: /welcomemessage reload"
```

## 💡 Примеры использования message-format

### 🎯 Стандартный формат (по умолчанию):
```
message-format: "%prefix%%player%%suffix% %message%"
# Результат: [Admin] Tokega зашёл на сервер!
```

### 🎯 Формат без префикса/суффикса:
```
message-format: "<white>Игрок <yellow>%player%<white> %message%"
# Результат: Игрок Tokega зашёл на сервер!
```

### 🎯 Формат с кастомным текстом:
```
message-format: "<gray>[<green>+<gray>] <white>%prefix%%player%%suffix% <gray>→ %message%"
# Результат: [+] [Admin] Tokega → зашёл на сервер!
```

### 🎯 Формат для первого входа:
```
message-format: "<gold>✨ <yellow>%player%<gold> %message%"
# Результат: ✨ Tokega зашёл на сервер впервые!
```

## 🔗 Совместимость

- **🎮 Minecraft**: 1.21.8
- **🖥️ Server**: Paper
- **☕ Java**: 21
- **📦 Dependencies**: LuckPerms (опционально), Essentials (опционально)

## 📥 Установка

1. Скачайте **последний релиз** из раздела [Releases](../../releases)
2. Поместите файл `.jar` в папку `plugins` вашего сервера
3. Перезагрузите или запустите сервер (`/reload` или полная перезагрузка)
4. Настройте сообщения в `plugins/WelcomeMessage/config.yml`
5. Используйте `/welcomemessage reload` для применения изменений

## 🤝 Поддержка и Баги

Если вы нашли ошибку или у вас есть предложение по улучшению плагина, создайте **Issue** на странице проекта GitHub.

## 📜 Лицензия

Этот проект лицензирован под MIT License.
