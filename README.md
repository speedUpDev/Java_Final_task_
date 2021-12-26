# Итоговая работа по Java
## Вариант №4
## Белобородов Алексей
Для обработки CSV-файла, я создал класс CsvRow, в котором выделил все поля значений из файла.
Для создания заполнения SQL-файла я реализовал класс CsvParser, в котором поочередно преобразую строки из CSV-файла в класс CsvRow.
![csvRowCreate](https://github.com/speedUpDev/Java_Final_task_/blob/main/screenshots/createCsvRow.png)
С помощью метода createSqlTables() создаю все колонки в наборе таблиц.
![createTables](https://github.com/speedUpDev/Java_Final_task_/blob/main/screenshots/createTables.png)
Для заполнения таблиц данными использую метод InsertData.
![InsertData](https://github.com/speedUpDev/Java_Final_task_/blob/main/screenshots/insertData.png)
Итоговая база в формате sql находится в файле transfers.sqlite.
