# Демонстрационный проект
### Прототип web-сервера "advertisementBoard"<br>
### Электронная доска объявлений
#### backend + frontend

Запуск тестов: `mvn clean test`<br>
**Если тесты запускаются на Windows** проверить platform encoding командой:
`mvn --version`. <br>
Если platform encoding мавена != UTF-8, выполнить команду: `set MAVEN_OPTS=-Dfile.encoding="UTF-8"`


Запуск web сервера с развернутым приложением: `mvn jetty:run`<br>
**Если приложение запускается на Windows**, то выполните команду: `set MAVEN_OPTS=-Dfile.encoding="UTF-8"`

По умолчанию Jetty - сервер настроен на порт 8090.<br>

### API

`advert/list` - возвращает полный список объявлений;<br>
`advert/{id}/details` - возвращает полное описание объявления с зададнным id;<br>
`advert/new` - открывается форма добавления новогоо объяления;<br>
`advert/{advertId}/comment/new` - открывается форма добавления нового комментария;<br>

