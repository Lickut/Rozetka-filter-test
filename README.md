# Rozetka-filter-test
Фильтр при выборе ноутбуков
Задание:
<br>site: http://rozetka.com.ua/notebooks/c80004/filter/
<br>написать тесты для проверки работы фильтра при выборе ноутбуков
<br>использовать page object

Решение:
<br>Eclipse maven project.
<br>I'm using BDD with JBehave.
<br>FilterByParameters.story - сценарии с параметрами
<br>FilterPage.java,FilterPresetPage.java, Page.java - использование паттерна page object для страниц розетки
<br>NotebookFiltering.java - соединение шагов сценариев с кодом
<br>TestFilterScenarios.java - jbehave test runner
<br>FilterUtils.java - layer between pages with PO model and tests.



