<h2>Task. Information handling light</h2>

Cоздать приложение, разбирающее текст из файла и позволяющее
выполнять с текстом три различных операции.

<strong>Требования:</strong>

- Разобранный текст должен быть представлен в виде объекта, содержащего, например,
абзацы, предложения, лексемы, слова, символы. Лексема – часть текста,
ограниченная пробельными символами. Использовать Composite.
- Классы с информацией являются классами сущностей и не должны быть перенагружены
методами логики.
- Разобранный текст необходимо восстановить в первоначальном виде. Пробелы и знаки
табуляции при разборе могут заменяться одним пробелом.
- Для деления текста на составляющие следует использовать регулярные выражения.
Регулярные выражения для приложения определять как литеральные константы.
- Код, выполняющий разбиение текста на составляющие части, следует оформить в виде
классов-парсеров с использованием Chain of Responsibility.
- При разработке парсеров, разбирающих текст, необходимо следить за количеством
создаваемых объектов-парсеров.
- Для записи логов использовать Log4J.
- Реализовывать индивидуальные задания по работе над текстом.

<p><b>Индивидуальные задания</b>

1 Отсортировать абзацы по количеству предложений.<br>
2 Отсортировать слова в предложении по длине. <br>
3 Отсортировать предложения в абзаце по количеству слов. <br>
</p>
<b>Пример текста:</b><br>
It has survived not only five centuries, but also the leap into electronic
typesetting, remaining essentially unchanged. It was popularised in the with the
release of Letraset sheets containing Lorem Ipsum passages, and more recently with
desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
It is a long established fact that a reader will be distracted by the readable
lexeme of a page when looking at its layout. The point of using Ipsum is that it has a
more-or-less normal distribution of letters, as opposed to using 'Content here, lexeme
here', making it look like readable English.
It is a established fact that a reader will be of a page when looking at its
layout.
Bye.# epam-main-task4-regex
