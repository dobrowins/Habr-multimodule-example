Gradle multi-module + git подмодули - в первый раз всегда больно [tutorial]
=================================================================

Привет, Хабр!
Много уже сказано про пользу разбивания частей приложения на модули — я хотел бы описать процесс интеграции гитовских субмодулей в мультимодульный проект. Это имеет смысл, если планируется использовать модуль как библиотеку — т.е., как отдельную логическую единицу, планируемую к переиспользованию в других проектах.

Сразу скажу, что использовать подмодули просто, если знать, что делать. Если не знаешь — можно читать дальше. 

В качестве корневого проекта создадим обычное Android-приложение, в которое в качестве зависимостей добавим всё, что нам нужно. Я добавил только Dagger([build.gradle](https://github.com/dobrowins/Habr-multimodule-example/blob/before-module-added/app/build.gradle)).

По корневому проекту пока всё — конечный результат можно посмотреть в ветке [before-module-added](https://github.com/dobrowins/Habr-multimodule-example/tree/before-module-added).

Также для успеха туториала создаем [библиотеку](https://github.com/dobrowins/RandomProviderLibrary), которую и будем позже добавлять в качестве подмодуля.

Подмодули в git позволяют сохранить один Git-репозиторий, как поддиректорию другого Git-репозитория. Все коммиты будут храниться отдельно, при этом изменения в коде подмодуля будут распространяться на все проекты, использующие подмодуль в качестве зависимости. 

Добавляем подмодуль в существующий репозиторий командой `git submodule add https://github.com/dobrowins/RandomProviderLibrary`. Git создал для библиотеки директорию и клонировал подмодуль туда. Также был создан файл .gitmodules, в котором было отмечено добавление подмодуля в репозиторий:   
`
[submodule "RandomProviderLibrary"]
 	path = RandomProviderLibrary
 	url = https://github.com/dobrowins/RandomProviderLibrary
`

Добавленный подмодуль необходимо активировать командой `git submodule init`.  
Android Studio может заругаться на Unregistered git root - эту проблему можно решить через **Settings -> Version control**  
![unregistered git root](https://user-images.githubusercontent.com/18611797/41596717-de42d550-73d3-11e8-82bb-7b1c1b8e90ef.jpg)
Теперь коммиты сделанных изменений будут пушится в соответствующие репозитории.
![submodule_push](https://user-images.githubusercontent.com/18611797/41597277-a84b534e-73d5-11e8-9590-5d7e52215e17.jpg)  
Для того, чтобы сделать клонированный в подмодуль код полноценным модулем нашего Android приложения требуется правка build.gradle файлов нашего корневого приложения:
* в settings.gradle добавляем строчки   
`include ':randomproviderlibrary`  
`project(':randomproviderlibrary').projectDir = file("/home/dobrovinskiy/StudioProjects/HabrMultimoduleExample/RandomProviderLibrary")`
* в dependencies build.gradle добавляем строчку `implementation project(path: ':randomproviderlibrary')`

Дальше можно собрать проект, добавить библиотечный класс в граф зависимостей и использовать по назначению.

И всё. Результат можно посмотреть в ветке [master](https://github.com/dobrowins/Habr-multimodule-example/tree/master).

Happy coding!
