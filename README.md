# Итоговая работа по Java
## Вариант №4
## Белобородов Алексей
Для обработки CSV-файла, я создал класс CsvRow, в котором выделил все поля значений из файла.
Для создания заполнения SQL-файла я реализовал класс CsvParser, в котором поочередно преобразую строки из CSV-файла в класс CsvRow.

![csvRowCreate](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

С помощью метода createSqlTables() создаю все колонки в наборе таблиц.

![createTables](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

Для заполнения таблиц данными использую метод InsertData.

![InsertData](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

Итоговая база в формате sql находится в файле transfers.sqlite.

### Задание №1
Выведите сумму всех переводов(где это возможно) в Долларах за 2020 год, сгрупированные по месяцам в виде графика.
Выполнил задание с выводом в консоль(пока без графика).Код:

![FirstTask](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

Вывод в консоль:

![FirstTaskOutPut](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

### Задание №2
Выведите в консоль средний размер перевода в долларах, а так же их количество, за каждый уникальный период.
Код:

![SecondTask](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

Часть вывода(он слишком большой):

![SecondTaskOut](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

### Задание №3
Выведите в консоль максимальный и минимальный перевод за период с 2014, 2016 и 2020 год.
Не до конца понял задание, выполнил как максимальный и минимальный перевод за год. Если имелось ввиду с 2014-2021, 2016-2021, 2020-2021, достаточно поменять знак в коде с '=', на '>='.
Код:

![ThirdTask](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

Вывод в консоль:

![ThirdTaskOut](https://github.com/speedUpDev/PyCharm_task/blob/main/screenshots/profiler_filter.png)

