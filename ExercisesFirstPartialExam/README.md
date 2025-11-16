# 1. [Shapes Application](ShapesTest.java)

Да се дефинира класа ShapesApplication во која се чуваат податоци за повеќе прозорци на кои се исцртуваат геометриски
слики во форма на квадрат.

За класата да се дефинира:

- ShapesApplication() - конструктор
- int readCanvases (InputStream inputStream) - метод којшто од влезен поток на податоци ќе прочита информации за повеќе
  прозорци на кои се исцртуваат квадрати. Во секој ред од потокот е дадена информација за еден прозорец во формат:
  canvas_id size_1 size_2 size_3 …. size_n, каде што canvas_id е ИД-то на прозорецот, а после него следуваат големините
  на страните на квадратите што се исцртуваат во прозорецот. Методот треба да врати цел број што означува колку квадрати
  за сите прозорци се успешно прочитани.
- void printLargestCanvasTo (OutputStream outputStream) - метод којшто на излезен поток ќе го испечати прозорецот чии
  квадрати имаат најголем периметар. Печатењето да се изврши во форматот canvas_id squares_count
  total_squares_perimeter.

Input:

```angular2html
364fbe94 24 30 22 33 32 30 37 18 29 27 33 21 27 26
0469e20f 26 14 14 28 37 14 36 30
33f2c7c0 18 12 14 38 28 26 17 22 33 36 28 33 36 38
```

Output:

```angular2html
===READING SQUARES FROM INPUT STREAM===
36
===PRINTING LARGEST CANVAS TO OUTPUT STREAM===
364fbe94 14 1556
```

# 2. [Shapes Application 2](ShapesApplicationTest.java)

Да се дефинира класа ShapesApplication чување на податоци за повеќе прозорци на кои и се сцртуваат геометриски слики во
различна форма (квадрати и кругови)..

За класата да се дефинира:

- ShapesApplication(double maxArea) - конструктор, каде maxArea е најголемата дозволена плоштина на секоја форма
  поединечно, која може да биде исцртана на прозорците.
- void readCanvases (InputStream inputStream) - метод којшто од влезен поток на податоци ќе прочита информации за повеќе
  прозорци на кои се исцртуваат различните геометриски слики. Во секој ред се наоѓа информација за еден прозорец во
  формат: canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n каде што canvas_id е ИД-то на прозорецот,
  a после него следуваат информации за секоја форма во прозорецот. Секоја форма е означена со карактер што го означува
  типот на геометриската слика (S = square, C = circle) и со големината на страната на квадратот, односно радиусот на
  кругот.
- При додавањето на геометриските слики на прозорецот треба да се спречи креирање и додавање на прозорец во кој има
  форма што има плоштина поголема од максимално дозволената. Како механизам за спречување треба да се користи исклучок
  од тип IrregularCanvasException (фрлањето на исклучокот не треба да го попречи вчитувањето на останатите прозорци и
  геометриски слики. Да се испечати порака Canvas [canvas_id] has a shape with area larger than [max_area].
- void printCanvases (OutputStream os) - метод којшто на излезен поток ќе ги испечати информациите за сите прозорци во
  апликацијата. Прозорците да се сортирани во опаѓачки редослед според сумата на плоштините на геометриските слики во
  нив. Секој прозорец да е испечатен во следниот формат: ID total_shapes total_circles total_squares min_area max_area
  average_area.

За вредноста на PI користете ja константата Math.PI. За постигнување на точност со тест примерите користете double за
сите децимални променливи.

Input:

```angular2html
0cc31e47 C 27 C 13 C 29 C 15 C 22
5960017f C 30 S 15 S 588 C 25 C 14 S 14 S 17 C 19
8ed50a65 C 29 S 12 C 13 S 30 C 25 S 11
201c295e C 27 C 13 C 14 C 11 S 18 C 12
184ef1d4 S 28 S 26 S 2001 S 28 C 30 C 16 S 18
c4b48d9f S 26 C 18 C 18 S 16 S 12 C 29 S 19
5e28f402 C 24 C 28 C 14 C 25 S 11 S 22 S 10 S 19 S 20 S 11 C 29
91a5b09b C 30 S 10 S 28 S 10 S 18 C 28 S 14 S 10 S 30 C 21 C 24
36e77dad C 29 S 11 S 25 S 30 C 21 C 17 S 400 S 30 S 23
13343cb0 S 21 C 29 C 14 C 30 C 12
```

Output:

```angular2html
===READING CANVASES AND SHAPES FROM INPUT STREAM===
Canvas 5960017f has a shape with area larger than 10000.00
Canvas 184ef1d4 has a shape with area larger than 10000.00
Canvas 36e77dad has a shape with area larger than 10000.00
===PRINTING SORTED CANVASES TO OUTPUT STREAM===
5e28f402 11 5 6 100.00 2642.08 1007.35
91a5b09b 11 4 7 100.00 2827.43 999.04
0cc31e47 5 5 0 530.93 2642.08 1538.12
13343cb0 5 4 1 441.00 2827.43 1395.73
8ed50a65 6 3 3 121.00 2642.08 1050.25
c4b48d9f 7 3 4 144.00 2642.08 873.55
201c295e 6 5 1 324.00 2290.22 765.57
```

# 3. [File System](FileSystemTest.java)

Потребно е да се дефинира апликација за едноставен датотечен систем во којшто ќе се чуваат објекти коишто репрезентираат
фајлови/датотеки (објекти коишто го имплементираат интерфејсот IFile).

Да се декларира интерфејсот IFile со соодветни методи, така што секој фајл/датотека ќе ги има следните карактеристики:

- може да се пристапи до неговото име (String getFileName())
- може да се добие неговата големина во long (long getFileSize())
- може да се добие String репрезентација на фајлот (String getFileInfo(???))
- може да се сортира датотеката доколку е колекција од датотеки според големините на датотеките кои ги содржи (void
  sortBySize())
- може да се пресмета големината на најголемата обична датотека во датотеката (findLargestFile ())

Постојат два типа на фајлови: File (обична датотека) и Folder (директориум/фолдер). Потребно е овие две класи да го
имплементираат интерфејсот IFile.

За еден File се чуваат информации за неговото име и големина (во long).

Во класата Folder се чуваат исти информации како и за File, a дополнително се чува и листа од фајлови (и обични и
директориуми). За оваа класа да се имплементираат методите:

- void addFile (IFile file) - метод за додавање на било каква датотека во листата од датотеки.
- Доколку веќе постои датотека со исто име како името на датотеката што се додава како аргумент на методот, да се фрли
  исклучок од тип FileNameExistsException во којшто се проследува името кое веќе постои.

И во двете класи да се имплементираат методите коишто се декларирани во интерфејсот IFile. Да се запази на следните
фактори:

- големината на еден Folder е сума од големините на сите датотеки (обични или директориуми) коишто се наоѓаат во него.
- при генерирање на String репрезентација на директориумите, датотеките и поддиректориумите во тој директориум да се
  вовлечени со таб ("\t").
- String репрезентацијата на една обична датотека е File name [името на фајлот со 10 места порамнето на десно] File
  size: [големината на фајлот со 10 места пораменета на десно ]
- String репрезентацијата на еден директориум е Folder name [името на директориумот со 10 места порамнето на десно]
  Folder size: [големината на директориумот со 10 места пораменета на десно ]
- возможно е сортирање само во рамки на директориум, каде што сите датотеки во тој директориум потребно е да се
  сортираат според големина во растечки редослед.
- методот getLargestFile() треба да ја врати големината на најголемата обична датотека во рамки на датотеката каде што е
  повикан.
- кога се повикува методот sortBySize() кај директориум истиот треба да се повика и за сите негови подиректориуми

Да се дефинира класа FileSystem во која што ќе се чува само еден директориум (rootDirectory). За класата да се
имплементираат:

- default конструктор FileSystem()
- void addFile (IFile file) - метод за додавање на било каква датотека во root директориумот.
- long findLargestFile () - метод којшто ја враќа големината на најголемата (обична) датотека во root директориумот.
- void sortBySize() - метод којшто ги сортира датотеките во root директориумот ( и обични и директориуми) според нивната
  големина во root директориумот во растечки редослед.

Input:

```angular2html
test
3
0
test 12000
0
test 123
0
test_1 1070
```

Output:

```angular2html
===READING FILES FROM INPUT===
There is already a file named test in the folder test
===PRINTING FILE SYSTEM INFO===
Folder name:       root Folder size:      13070
Folder name:       test Folder size:      13070
File name:       test File size:      12000
File name:     test_1 File size:       1070

===PRINTING FILE SYSTEM INFO AFTER SORTING===
Folder name:       root Folder size:      13070
Folder name:       test Folder size:      13070
File name:     test_1 File size:       1070
File name:       test File size:      12000

===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===
12000
```

# 4. [Log System](LoggerTest.java)

Потребно е да се развие систем за процесирање на логови. За секој лог треба да се чува пораката од логот (String), типот
на логот (String) и временски печат (long). За таа цел комплетирајте го интерфејсот ILog.

За да се процесираат логовите ќе се користи генеричкиот интерфејс LogProcessor. Овој интерфејс има само еден метод со
потпис: ArrayList processLogs (ArrayList logs). Методот добива влезен аргумент логови коишто треба да ги процесира, а
враќа резултат процесирани логови. Интерфејсот ви е даден и за истиот треба да ги пополните само генеричките параметри.

Дадена ви е класата LogSystem во којашто се чува листа на логови. За класата да се дефинираат соодветните генерички
параметри, да се имплементира конструктор LogSystem(ArrayList elements), како и да се комплетира методот printResults().

Во овој метод потребно е да креирате три конкретни процесори на логови (со помош на ламбда изрази):

1. Процесор којшто ќе ги врати само логовите коишто се од тип INFO
2. Процесор којшто ќе ги врати само логовите чиишто пораки се пократки од 100 карактери
3. Процесор којшто ќе ги врати логовите сортирани според временскиот печат во растечки редослед (од најстар кон најнов
   лог).

Input:

```angular2html
3
2020-11-18 02:20:20 [dag-scheduler-event-loop] INFO FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
2020-11-18 02:20:20 [dispatcher-event-loop-3] INFO TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
3
ERROR Some_short_message-8 8878539449911250943
ERROR Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64 7026192678309265809
INFO Some_short_message-13 2051624829434088144
```

Output:

```angular2html
===REAL LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020101 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020102 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
RESULTS FROM THE SECOND LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
RESULTS FROM THE THIRD LOG PROCESSOR
1605666020100 [INFO] FairSchedulableBuilder: Added task set TaskSet_550.0 tasks to pool 8528611190673175966
1605666020101 [INFO] TaskSetManager: Starting task 0.0 in stage 550.0 (TID 4051, 10.162.242.30, executor 1, partition 0, PROCESS_LOCAL)
1605666020102 [INFO] TaskSetManager: Starting task 1.0 in stage 550.0 (TID 4052, 10.162.235.85, executor 0, partition 1, PROCESS_LOCAL)
===DUMMY LOGS SYSTEM RESULTS===
RESULTS FROM THE FIRST LOG PROCESSOR
DummyLog&#123;type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
RESULTS FROM THE SECOND LOG PROCESSOR
DummyLog{type='ERROR', message='Some_short_message-8', timestamp=8878539449911250943}
DummyLog{type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
RESULTS FROM THE THIRD LOG PROCESSOR
DummyLog{type='INFO', message='Some_short_message-13', timestamp=2051624829434088144}
DummyLog{type='ERROR', message='Some_loooooooooooooooooooooooooong_dummy_message_that_should_be_more_than_100_charsszzzzzzzzzzzzzzzz-64', timestamp=7026192678309265809}
DummyLog{type='ERROR', message='Some_short_message-8', timestamp=8878539449911250943}
```

# 5. [Min Max](MinMax.java)

Да се имплемнтира генеричка класа MinMax од два споредливи објекти (минимум/максимум). За класата да се имплементираат:

- MinMax()-default конструктор
- void update(T element) - метод за ажурирање на тековните минимум/максимум.
- T max() - го враќа најголемиот елемент
- T min() - го враќа најмалиот елемент
- да се преоптовари методот toString() кој враќа стринг составен од минималниот и максималниот елемент и бројот на
  елементи обработени во методот update кои се различни од тековниот минимум/максимум, разделени со празно место.
  Во класата не смеат да се чуваат елементите кои се обработуваат во методот update, освен тековниот минимум/максимум.

Input:

```angular2html
6
abc
xyz
abc
xyz
zzz
blatr
12
8
8
5
55
13
55
```

Output:

```angular2html
abc zzz 3

5 55 4
```

# 6. [Canvas](Shapes.java)

Да се имплементира класа Canvas на која ќе чуваат различни форми. За секоја форма се чува:

- id:String
- color:Color (enum дадена)

Притоа сите форми треба да имплментираат два интерфејси:

- Scalable - дефиниран со еден метод void scale(float scaleFactor) за соодветно зголемување/намалување на формата за
  дадениот фактор
- Stackable - дефиниран со еден метод float weight() кој враќа тежината на формата (се пресметува како плоштина на
  соодветната форма)

Во класата Canvas да се имплементираат следните методи:

- void add(String id, Color color, float radius) за додавање круг
- void add(String id, Color color, float width, float height) за додавање правоаголник

При додавањето на нова форма, во листата со форми таа треба да се смести на соодветното место според нејзината тежина.
Елементите постојано се подредени според тежината во опаѓачки редослед.

- void scale(String id, float scaleFactor) - метод кој ја скалира формата со даденото id за соодветниот scaleFactor.
  Притоа ако има потреба, треба да се изврши преместување на соодветните форми, за да се задржи подреденоста на
  елементите.
  Не смее да се користи сортирање на листата.
- toString() - враќа стринг составен од сите фигури во нов ред. За секоја фигура се додава:

```
C: [id:5 места од лево] [color:10 места од десно] [weight:10.2 места од десно] ако е круг

R: [id:5 места од лево] [color:10 места од десно] [weight:10.2 места од десно] ако е правоаголник
```

Користење на instanceof ќе се смета за неточно решение!

Input:

```angular2html
1 c1 RED 15
1 c2 GREEN 7
1 c3 RED 9
2 r1 BLUE 4 7
2 r2 GREEN 10 8
3 c2 2.4
3 r1 5.1
```

Output:

```angular2html
ORIGNAL:
C: c1   RED           706.86
C: c3   RED           254.47
C: c2   GREEN         153.94
R: r2   GREEN          80.00
R: r1   BLUE           28.00
AFTER SCALING: c2 2.40
C: c2   GREEN         886.68
C: c1   RED           706.86
C: c3   RED           254.47
R: r2   GREEN          80.00
R: r1   BLUE           28.00
ORIGNAL:
C: c2   GREEN         886.68
C: c1   RED           706.86
C: c3   RED           254.47
R: r2   GREEN          80.00
R: r1   BLUE           28.00
AFTER SCALING: r1 5.10
C: c2   GREEN         886.68
R: r1   BLUE          728.28
C: c1   RED           706.86
C: c3   RED           254.47
R: r2   GREEN          80.00
```

# 7. [Timetable](TimesTest.java)

Да се имплементира класа TimeTable која ќе чита од влезен тек (стандарден влез, датотека, ...) податоци за времиња во
24-часовен формат. Сите времиња се разделени со едно празно место, а во самото време часот и минутите може да бидат
разделени со ```:``` или ```.```. Пример за форматот на податоците:

11:15 0.45 23:12 15:29 18.46

Ваша задача е да ги имплементирате методите:

- TimeTable() - default конструктор
- void readTimes(InputStream inputStream) - метод за читање на податоците
- void writeTimes(OutputStream outputStream, TimeFormat format) - метод кој ги печати сите времиња сортирани во растечки
  редослед во зададениот формат (24 часовен или AM/PM).
- Методот за читање readTimes фрла исклучоци од тип UnsupportedFormatException ако времињата се разделени со нешто друго
  што не е : или . и InvalidTimeException ако времето (часот или минутите) е надвор од дозволениот опсег (0-23, 0-59). И
  двата исклучоци во пораката getMessage() треба да го вратат влезниот податок кој го предизвикал исклучокот. Сите
  времиња до моментот кога ќе се фрли некој од овие два исклучоци треба да си останат вчитани.

Правила за конверзија од 24-часовен формат во AM/PM:

- за првиот час од денот (0:00 - 0:59), додадете 12 и направете го "AM"
- од 1:00 до 11:59, само направето го "AM"
- од 12:00 до 12:59, само направето го "PM"
- од 13:00 до 23:59 одземете 12 и направете го "PM"

Input:

```angular2html
10.10
11.11
12.12
0.15
1:10
20:20
13:15
17:20
23:30
19.19
17:21
19.59
12:00
11:59
```

Output:

```angular2html
24 HOUR FORMAT
0:15
1:10
10:10
11:11
11:59
12:00
12:12
13:15
17:20
17:21
19:19
19:59
20:20
23:30
AM/PM FORMAT
12:15 AM
1:10 AM
10:10 AM
11:11 AM
11:59 AM
12:00 PM
12:12 PM
1:15 PM
5:20 PM
5:21 PM
7:19 PM
7:59 PM
8:20 PM
11:30 PM
```

# 8. [Archive Store (with formatter)](ArchiveStoreTest.java)

# 9. [Archive Store (without formatter)](ArchiveStoreTest2.java)

Да се имплементира класа ArchiveStore во која се чува листа на архиви (елементи за архивирање).

Секој елемент за архивирање Archive има:

- id - цел број
- dateArchived - датум на архивирање.

Постојат два видови на елементи за архивирање, LockedArchive за кој дополнително се чува датум до кој не смее да се
отвори dateToOpen и SpecialArchive за кој се чуваат максимален број на дозволени отварања maxOpen. За елементите за
архивирање треба да се обезбедат следните методи:

- LockedArchive(int id, Date dateToOpen) - конструктор за заклучена архива
- SpecialArchive(int id, int maxOpen) - конструктор за специјална архива

За класата ArchiveStore да се обезбедат следните методи:

- ArchiveStore() - default конструктор
- void archiveItem(Archive item, Date date) - метод за архивирање елемент item на одреден датум date
- void openItem(int id, Date date) - метод за отварање елемент од архивата со зададен id и одреден датум date. Ако не
  постои елемент со даденото id треба да се фрли исклучок од тип NonExistingItemException со порака Item with id [id]
  doesn't exist.
- String getLog() - враќа стринг со сите пораки запишани при архивирањето и отварањето архиви во посебен ред.

За секоја акција на архивирање во текст треба да се додаде следната порака Item [id] archived at [date], додека за
секоја акција на отварање архива треба да се додаде Item [id] opened at [date]. При отварање ако се работи за
LockedArhive и датумот на отварање е пред датумот кога може да се отвори, да се додаде порака Item [id] cannot be opened
before [date]. Ако се работи за SpecialArhive и се обидиеме да ја отвориме повеќе пати од дозволениот број (maxOpen) да
се додаде порака Item [id] cannot be opened more than [maxOpen] times.

### With formatter

Input:

```angular2html
Locked Archive Count
2
Id Date (Days in future)
1 50
2 -25
Special Archive Count
2
Id MaxOpen
3 5
4 2
Opening
1 2 3 7 4 4 4 3 3 3 3 3
```

Output:

```angular2html
Item with id 7 doesn't exist
Item 1 archived at Thu Nov 07 00:00:00 UTC 2013
Item 2 archived at Thu Nov 07 00:00:00 UTC 2013
Item 3 archived at Thu Nov 07 00:00:00 UTC 2013
Item 4 archived at Thu Nov 07 00:00:00 UTC 2013
Item 1 cannot be opened before Fri Dec 27 00:00:00 UTC 2013
Item 2 opened at Thu Nov 07 00:00:00 UTC 2013
Item 3 opened at Thu Nov 07 00:00:00 UTC 2013
Item 4 opened at Thu Nov 07 00:00:00 UTC 2013
Item 4 opened at Thu Nov 07 00:00:00 UTC 2013
Item 4 cannot be opened more than 2 times
Item 3 opened at Thu Nov 07 00:00:00 UTC 2013
Item 3 opened at Thu Nov 07 00:00:00 UTC 2013
Item 3 opened at Thu Nov 07 00:00:00 UTC 2013
Item 3 opened at Thu Nov 07 00:00:00 UTC 2013
Item 3 cannot be opened more than 5 times
```

### Without formatter

Input:

```angular2html
Locked Archive Count
2
Id Date (Days in future)
1 50
2 -25
Special Archive Count
2
Id MaxOpen
3 5
4 2
Opening
1 2 3 7 4 4 4 3 3 3 3 3
```

Output:

```angular2html
Item with id 8 doesn't exist
Item with id 9 doesn't exist
Item 1 archived at 2013-10-07
Item 2 archived at 2013-10-07
Item 3 archived at 2013-10-07
Item 4 archived at 2013-10-07
Item 5 archived at 2013-10-07
Item 2 opened at 2013-10-07
Item 3 cannot be opened before 2013-11-21
Item 1 cannot be opened before 2013-11-26
Item 4 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 4 opened at 2013-10-07
Item 4 cannot be opened more than 2 times
Item 5 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 5 cannot be opened more than 3 times
```

# 10. [Triple](TripleTest.java)

Да се имплемнтира генеричка класа Triple (тројка) од нумерички вредности (три броја). За класата да се имплементираат:

- конструктор со 3 аргументи,
- double max() - го враќа најголемиот од трите броја
- double average() - кој враќа просек на трите броја
- void sort() - кој ги сортира елементите во растечки редослед
- да се преоптовари методот toString() кој враќа форматиран стринг со две децимални места за секој елемент и празно
  место помеѓу нив.

Input:

```angular2html
3 7 5
8 8 9
2 1 4
```

Output:

```angular2html
7.00
5.00
3.00 5.00 7.00
9.00
8.33
8.00 8.00 9.00
4.00
2.33
1.00 2.00 4.00
```

# 11. [Evaluator](EvaluatorTest.java)

Да се дефинира генерички интерфејс IEvaluator којшто ќе има само еден метод:

- boolean evaluate (Т a, Т b) - метод што ќе враќа true/false за некој тип на споредба помеѓу два објекти од иста класа
  што се споредливи.

Да се креира класа EvaluatorBuilder која што ќе има само еден генерички статички метод:

- static IEvaluator build (String operator) - метод којшто ќе враќа објект што го имплементира интерфејсот IEvaluator.
  Имплементацијата на овие објекти треба да се базира врз основа на операторот којшто е даден како аргумент во
  функцијата. Истиот може да биде:
    - \>
    - ==
    - !=
    - <
      имплементациите на интерфејсот да бидат зададени со **ламбда** израз!

Да се дефинира класа Evaluator која што ќе има само еден генерички статички метод:

- static boolean evaluateExpression (T left, T right, String operator) - метод којшто прима три аргументи: првите два се
  вредностите за евалуација, додека пак третиот е операторот со којшто се врши евалуацијата. Во овој метод да се креира
  соодветниот евалуатор на операторот, и да се евалуираат двете вредностleft и right.

Input:

```angular2html
1 2 > 3
1 2 == 3
1 2 < 3
```

Output:

```angular2html
false
false
true
```

# 12. [Fractions](GenericFractionTest.java)

Треба да се развие генеричка класа за работа со дропки. Класата GenericFraction има два генерички параметри T и U кои
мора да бидат од некоја класа која наследува од класата Number. GenericFraction има две променливи:

- numerator - броител
- denominator - именител.

Треба да се имплементираат следните методи:

- GenericFraction(T numerator, U denominator) - конструктор кој ги иницијализира броителот и именителот на дропката. Ако
  се обидиме да иницијализираме дропка со 0 вредност за именителот треба да се фрли исклучок од тип
  ZeroDenominatorException
- GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) - собирање на две дропки
- double toDouble() - враќа вредност на дропката како реален број
- toString():String - ја печати дропката во следниот формат [numerator] / [denominator], скратена (нормализирана) и
  секој со две децимални места.

Input:

```angular2html
2 5
1 7
4 9
```

Output:

```angular2html
0.40
19.00 / 35.00
37.00 / 63.00
38.00 / 45.00
Denominator cannot be zero
```

# 15. [Weather Station](WeatherStationTest.java)
Во една метеролошка станица на секои 5 минути пристигнуваат податоци за временските услови (температура, влажност на воздухот, ветар, видливост, време). Пример за вакви податоци:
- температура: 13 степени
- влажност: 98%
- ветар: 11.2 km/h
- видливост: 14 km
- време: 28.12.2013 14:37:55 (dd.MM.yyyy HH:mm:ss).

Потребно е да се имплементира класа WeatherStation која ќе ги чува податоците за временските услови за последните x денови (при додавање на податоци за ново мерење, сите мерења чие што време е постаро за x денови од новото се бришат ). Исто така ако времето на новото мерење кое се додава се разликува за помалку од 2.5 минути од времето на некое претходно додадено мерење, тоа треба да се игнорира (не се додава).
Да се имплементираат следните методи на класата WeatherStation:
- WeatherStation(int days) - конструктор со аргумент бројот на денови за кои се чуваат мерења
- public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date) - додавање на податоци за ново мерење
- public int total() - го враќа вкупниот број на мерења кои се чуваат
- public void status(Date from, Date to) - ги печати сите мерења во периодот од from до to подредени според датумот во растечки редослед и на крај ја печати просечната температура во овој период. Ако не постојат мерења во овој период се фрла исклучок од тип RuntimeException (вграден во Јава).

Пример за форматот на излезот:
```angular2html
24.6 80.2 km/h 28.7% 51.7 km Tue Dec 17 23:40:15 CET 2013
23.5 32.2 km/h 16.5% 187.2 km Tue Dec 17 23:45:15 CET 2013
13.2 67.1 km/h 18.9% 135.4 km Tue Dec 17 23:50:15 CET 2013
Avarage temperature: 20.43
```

Input:
```angular2html
4
22.1 18.9 1.3 24.6
10.12.2013 21:30:15
22.1 18.9 1.3 24.6
11.12.2013 22:30:15
22.1 18.9 1.3 24.6
17.12.2013 23:30:15
41.8 9.4 40.8 20.7
17.12.2013 23:35:15
24.6 28.7 80.2 51.7
17.12.2013 23:40:15
24.6 28.7 80.2 51.7
17.12.2013 23:40:25
24.6 28.7 80.2 51.7
17.12.2013 23:39:36
23.5 16.5 32.2 187.2
17.12.2013 23:45:15
13.2 18.9 67.1 135.4
17.12.2013 23:50:15
13.2 18.9 67.1 135.4
17.12.2013 23:51:12
31.6 48.3 6.0 86.6
17.12.2013 23:55:15
18.0 37.6 15.9 37.7
18.12.2013 00:00:15
24.6 28.7 80.2 51.7
19.12.2013 23:39:36
23.5 16.5 32.2 187.2
19.12.2013 23:45:15
13.2 18.9 67.1 135.4
19.12.2013 23:50:15
13.2 18.9 67.1 135.4
19.12.2013 23:51:12
31.6 48.3 6.0 86.6
19.12.2013 23:55:15
18.0 37.6 15.9 37.7
20.12.2013 00:00:15
18.0 37.6 15.9 37.7
20.12.2013 00:05:15
18.0 37.6 15.9 37.7
20.12.2013 00:05:35
=====
11.12.2013 22:30:15
19.12.2013 23:39:36
```

Output:
```angular2html
13
22.1 18.9 km/h 1.3% 24.6 km Tue Dec 17 23:30:15 GMT 2013
41.8 9.4 km/h 40.8% 20.7 km Tue Dec 17 23:35:15 GMT 2013
24.6 28.7 km/h 80.2% 51.7 km Tue Dec 17 23:40:15 GMT 2013
23.5 16.5 km/h 32.2% 187.2 km Tue Dec 17 23:45:15 GMT 2013
13.2 18.9 km/h 67.1% 135.4 km Tue Dec 17 23:50:15 GMT 2013
31.6 48.3 km/h 6.0% 86.6 km Tue Dec 17 23:55:15 GMT 2013
18.0 37.6 km/h 15.9% 37.7 km Wed Dec 18 00:00:15 GMT 2013
24.6 28.7 km/h 80.2% 51.7 km Thu Dec 19 23:39:36 GMT 2013
Average temperature: 24.93
```

# 16. [MojDDV](MojDDVTest.java)
Да се имплементира класа MojDDV која што од влезен тек ќе чита информации за скенирани фискални сметки од страна на еден корисник на истоимената апликација. Податоците за фискалните сметки се во следниот формат:
ID item_price1 item_tax_type1 item_price2 item_tax_type2 … item_price-n item_tax_type-n
На пример: 12334 1789 А 1238 B 1222 V 111 V

Постојат три типа на данок на додадена вредност и тоа:
- А (18% од вредноста)
- B (5% од вредноста)
- V (0% од вредноста)
Повратокот на ДДВ изнесува 15% од данокот на додадената вредност за артикалот.

Да се имплементираат методите:
- void readRecords (InputStream inputStream)- метод којшто ги чита од влезен тек податоците за фискалните сметки. Доколку е скенирана фискална сметка со износ поголем од 30000 денари потребно е да се фрли исклучок од тип AmountNotAllowedException. Дефинирајте каде ќе се фрла исклучокот, и каде ќе биде фатен, на начин што оваа функција, ќе може да ги прочита сите фискални коишто се скенирани. Исклучокот треба да испечати порака “Receipt with amount [сума на сите артикли] is not allowed to be scanned”.
- void printTaxReturns (OutputStream outputStream) - метод којшто на излезен тек ги печати сите скенирани фискални сметки во формат “ID SUM_OF_AMOUNTS TAX_RETURN”, каде што SUM_OF_AMOUNTS e збирот на сите артикли во фискалната сметка, а TAX_RETURN е пресметаниот повраток на ДДВ за таа фискална сметка.

Input:
```angular2html
70876 1585 B 1585 V 247 V 1391 B 1391 V 1649 B 1649 V 548 B 548 V 640 B 640 V 1309 B 1309 V 1486 V 2093 V 106 V 2001 V 361 V 
198710 1306 A 1306 B 1306 V 432 V 1222 V 1851 V 390 V 111 A 111 B 111 V 991 V 1611 A 1611 B 1611 V 
140819 709 A 709 B 709 V 1628 A 1628 B 1628 V 680 V 
584886 411 A 411 B 411 V 699 B 699 V 1571 V 1307 B 1307 V 
588253 1528 B 1528 V 1744 B 1744 V 1033 V 412 B 412 V 1221 A 1221 B 1221 V 328 A 328 B 328 V 1301 A 1301 B 1301 V 1778 A 1778 B 1778 V 1651 A 1651 B 1651 V 1937 V 1521 V 2068 B 2068 V 
970452 1703 V 1796 B 1796 V 1221 V 885 A 885 B 885 V 183 V 788 B 788 V 1753 B 1753 V 456 V 926 V 1898 V 410 B 410 V 824 B 824 V 
51307 2002 B 2002 V 1776 V 2097 B 2097 V 1128 A 1128 B 1128 V 151 A 151 B 151 V 
570505 1090 A 1090 B 1090 V 1121 B 1121 V 971 B 971 V 1260 A 1260 B 1260 V 443 V 
987793 1436 V 1648 B 1648 V 1197 V 992 B 992 V 528 A 528 B 528 V 739 A 739 B 739 V 750 B 750 V 
752690 1626 B 1626 V 1785 A 1785 B 1785 V 1938 V 1733 V 1137 B 1137 V 1832 A 1832 B 1832 V
```

Output:
```angular2html
===READING RECORDS FROM INPUT STREAM===
Receipt with amount 34832 is not allowed to be scanned
===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===
70876 20538 53.42
198710 13970 104.47
140819 7691 80.63
584886 6816 29.22
970452 20184 72.32
51307 13811 74.87
570505 11677 96.76
987793 13214 69.14
752690 20048 145.51
```

# 19. [Loaded Coin](LoadedCoinTest.java)
Дадена е класа Coin која се користи за симулирање на вртење монета. Методот flip во оваа класа со помош на класата Random (која користи рамномерна распределба) враќа HEAD (глава) или TAIL (петка) со еднаква веројатност од 0.5 (50%).

Да се напише класа LoadedCoin која наследува од класата Coin и го препокрива методот flip така што ќе враќа HEAD со некоја веројатност P (0 - 100%). Веројатноста P е класна променлива и се иницијализира преку конструкторот.

Input:
```angular2html
76
```

Output:
```angular2html
YES
YES
```

# 21. [Task Scheduler](TaskSchedulerTest.java)
Потребно е да се дефинираат два распоредувачи на задачи кои го имплеметираат интерфејсот TaskScheduler. TaskScheduler е генерички интерфејс за распоредување на задачи (Task) со еден метод schedule кој прима низа од задачи а како резултат се очекува да врати листа од истите тие задачи распоредени согласно критериумите за распоредување.

Задача (Task) е интерфејс кој имплемeнтира метод кој го дава редниот број на задачата за извршување. Постојат да вида на задачи (TimedTask и PriorityTask). Редниот број на извршување на задачата кај TimedTask е дефиниран преку времето на извршување (time), додека кај PriorityTask тој е дефиниран преку приоритетот на извршување (priority).

Првиот распоредувач ги распоредува задачите на тој начин што истите ги сортира според нивниот реден број. Неговата имплементација потребно е да биде дадена со анонимна класа (во рамки на методот getOrdered од класата Schedulers).

Вториот распоредувач го задржува редоследот на добиените задачи, но, ги филтрира (отфрла) сите задачи со поголем реден број од даден праг (order). Неговата имплементација потребно е да биде дадена со ламбда израз (во рамки на методот getFiltered од класата Schedulers).

Распоредените задачи се стартуваат со помош на генеричката класа TaskRunner. За оваа класа потребно е да го дефинирате само параметарскиот тип.

Input:
```angular2html
28
283 975 946 738 882 175 55 814 47 28 552 689 462 967 12 523 644 973 439 978 973 193 653 757 655 441 514 234
9
65 351 130 252 278 281 899 763 141
500
```

Output:
```angular2html
PT -> 65
PT -> 351
PT -> 130
PT -> 252
PT -> 278
PT -> 281
PT -> 899
PT -> 763
PT -> 141
=== Ordered tasks ===
Timed tasks
TT -> 12
TT -> 28
TT -> 47
TT -> 55
TT -> 175
TT -> 193
TT -> 234
TT -> 283
TT -> 439
TT -> 441
TT -> 462
TT -> 514
TT -> 523
TT -> 552
TT -> 644
TT -> 653
TT -> 655
TT -> 689
TT -> 738
TT -> 757
TT -> 814
TT -> 882
TT -> 946
TT -> 967
TT -> 973
TT -> 973
TT -> 975
TT -> 978
Priority tasks
PT -> 65
PT -> 130
PT -> 141
PT -> 252
PT -> 278
PT -> 281
PT -> 351
PT -> 763
PT -> 899
=== Filtered time tasks with order less then 500 ===
TT -> 283
TT -> 175
TT -> 55
TT -> 47
TT -> 28
TT -> 462
TT -> 12
TT -> 439
TT -> 193
TT -> 441
TT -> 234
=== Filtered priority tasks with order less then 500 ===
PT -> 65
PT -> 351
PT -> 130
PT -> 252
PT -> 278
PT -> 281
PT -> 141
```

# 22. [F1 Race](F1Test.java)
Да се имплементира класа F1Race која ќе чита од влезен тек (стандарден влез, датотека, ...) податоци за времињата од последните 3 круга на неколку пилоти на Ф1 трка. Податоците се во следниот формат:

Driver_name lap1 lap2 lap3, притоа lap е во формат mm:ss:nnn каде mm се минути ss се секунди nnn се милисекунди (илјадити делови од секундата). Пример:

Vetel 1:55:523 1:54:987 1:56:134.

Ваша задача е да ги имплементирате методите:
- F1Race() - default конструктор
- void readResults(InputStream inputStream) - метод за читање на податоците
- void printSorted(OutputStream outputStream) - метод кој ги печати сите пилоти сортирани според нивното најдобро време (најкраткото време од нивните 3 последни круга) во формат Driver_name best_lap со 10 места за името на возачот (порамнето од лево) и 10 места за времето на најдобриот круг порамнето од десно. Притоа времето е во истиот формат со времињата кои се читаат.

Input:
```angular2html
Webber 2:32:103 2:49:182 2:18:132 
Vettel 2:55:023 3:14:987 2:56:134
Massa 2:57:503 2:05:270 3:05:134
Alonso 3:52:563 3:56:187 2:54:139
Hamilton 1:56:074 2:04:371 3:06:009
Raikonen 2:03:300 4:12:187 3:14:139
```

Output:
```angular2html
1. Hamilton    1:56:074
2. Raikonen    2:03:300
3. Massa       2:05:270
4. Webber      2:18:132
5. Alonso      2:54:139
6. Vettel      2:55:023
```

# 24. [Books](BooksTest.java)
Да се напише класа за книга Book во која се чува:
- наслов
- категорија
- цена.
Да се имплементира конструктор со следните аргументи Book(String title, String category, float price).

Потоа да се напише класа BookCollection во која се чува колекција од книги. Во оваа класа треба да се имплментираат следните методи:
- public void addBook(Book book) - додавање книга во колекцијата
- public void printByCategory(String category) - ги печати сите книги од проследената категорија (се споредува стрингот без разлика на мали и големи букви), сортирани според насловот на книгата (ако насловот е ист, се сортираат според цената).
- public List<Book> getCheapestN(int n) - враќа листа на најевтините N книги (ако има помалку од N книги во колекцијата, ги враќа сите).

Input:
```angular2html
50
Book Z:A:43.11
Book L:C:16.17
Book Q:D:32.51
Book O:D:46.15
Book C:C:40.02
Book W:D:16.20
Book G:D:41.55
Book P:E:31.54
Book X:C:27.09
Book I:D:23.25
Book Q:E:19.69
Book D:D:9.49
Book G:D:6.94
Book J:E:11.16
Book O:C:31.67
Book G:E:6.17
Book O:C:6.53
Book D:C:27.77
Book E:B:35.25
Book X:E:0.81
Book F:E:16.55
Book G:D:38.97
Book V:D:33.62
Book T:D:8.07
Book M:D:26.04
Book W:A:49.79
Book K:E:48.91
Book M:A:0.90
Book S:B:28.95
Book M:C:42.30
Book J:E:31.29
Book M:E:9.83
Book V:E:19.67
Book W:C:49.28
Book U:C:49.39
Book E:D:22.38
Book K:E:37.06
Book P:B:47.52
Book X:D:34.77
Book C:B:48.91
Book C:E:22.85
Book V:B:45.90
Book V:D:7.45
Book L:C:35.15
Book P:C:35.49
Book F:C:49.07
Book V:A:22.40
Book V:E:17.20
Book U:E:37.56
Book N:D:31.67
```

Output:
```angular2html
=== PRINT BY CATEGORY ===
CATEGORY: A
Book M (A) 0.90
Book V (A) 22.40
Book W (A) 49.79
Book Z (A) 43.11
CATEGORY: B
Book C (B) 48.91
Book E (B) 35.25
Book P (B) 47.52
Book S (B) 28.95
Book V (B) 45.90
CATEGORY: C
Book C (C) 40.02
Book D (C) 27.77
Book F (C) 49.07
Book L (C) 16.17
Book L (C) 35.15
Book M (C) 42.30
Book O (C) 6.53
Book O (C) 31.67
Book P (C) 35.49
Book U (C) 49.39
Book W (C) 49.28
Book X (C) 27.09
CATEGORY: D
Book D (D) 9.49
Book E (D) 22.38
Book G (D) 6.94
Book G (D) 38.97
Book G (D) 41.55
Book I (D) 23.25
Book M (D) 26.04
Book N (D) 31.67
Book O (D) 46.15
Book Q (D) 32.51
Book T (D) 8.07
Book V (D) 7.45
Book V (D) 33.62
Book W (D) 16.20
Book X (D) 34.77
CATEGORY: E
Book C (E) 22.85
Book F (E) 16.55
Book G (E) 6.17
Book J (E) 11.16
Book J (E) 31.29
Book K (E) 37.06
Book K (E) 48.91
Book M (E) 9.83
Book P (E) 31.54
Book Q (E) 19.69
Book U (E) 37.56
Book V (E) 17.20
Book V (E) 19.67
Book X (E) 0.81
=== TOP N BY PRICE ===
Book X (E) 0.81
Book M (A) 0.90
Book G (E) 6.17
Book O (C) 6.53
Book G (D) 6.94
Book V (D) 7.45
Book T (D) 8.07
Book D (D) 9.49
Book M (E) 9.83
Book J (E) 11.16
Book L (C) 16.17
Book W (D) 16.20
Book F (E) 16.55
Book V (E) 17.20
Book V (E) 19.67
Book Q (E) 19.69
Book E (D) 22.38
Book V (A) 22.40
Book C (E) 22.85
Book I (D) 23.25
Book M (D) 26.04
Book X (C) 27.09
Book D (C) 27.77
Book S (B) 28.95
Book J (E) 31.29
Book P (E) 31.54
Book N (D) 31.67
Book O (C) 31.67
Book Q (D) 32.51
Book V (D) 33.62
Book X (D) 34.77
Book L (C) 35.15
Book E (B) 35.25
Book P (C) 35.49
Book K (E) 37.06
Book U (E) 37.56
Book G (D) 38.97
Book C (C) 40.02
Book G (D) 41.55
Book M (C) 42.30
Book Z (A) 43.11
Book V (B) 45.90
Book O (D) 46.15
Book P (B) 47.52
Book C (B) 48.91
Book K (E) 48.91
Book F (C) 49.07
Book W (C) 49.28
Book U (C) 49.39
Book W (A) 49.79
```

# 25. [Discounts](DiscountsTest.java)
Да се имплементира класа Discounts за обработка на информации за цени и цени на попуст на одредени производи во неколку продавници (објекти од класа Store). Потребно е да се имплементираат следните методи:
- public int readStores(InputStream inputStream) - метод за вчитување на податоците за продавниците и цените на производите. Податоците за секоја продавница се во посебен ред во формат [ime] [cena_na_popust1:cena1] [cena_na_popust2:cena2] ... (погледнете пример). Методот враќа колку продавници се вчитани.
- public List<Store> byAverageDiscount() - метод кој враќа листа од 3-те продавници со најголем просечен попуст (просечна вредност на попустот за секој производ од таа продавница). Попустот (намалувањето на цената) е изразен во цел број (проценти) и треба да се пресмета од намалената цена и оригиналната цена. Ако две продавници имаат ист попуст, се подредуваат според името лексикографски.
- public List<Store> byTotalDiscount() - метод кој враќа листа од 3-те продавници со намал вкупен попуст (сума на апсолутен попуст од сите производи). Апсолутен попуст е разликата од цената и цената на попуст. Ако две продавници имаат ист попуст, се подредуваат според името лексикографски.

Дополнително за класата Store да се имплементира стринг репрезентација, односно методот:
- public String toString() кој ќе враќа репрезентација во следниот формат:
[Store_name]
Average discount: [заокружена вредност со едно децимално место]%
Total discount: [вкупен апсолутен попуст]
[процент во две места]% [цена на попуст]/[цена]
...
при што продуктите се подредени според процентот на попуст (ако е ист, според апсолутниот попуст) во опаѓачки редослед.
Погледнете од примерот.

Input:
```angular2html
GAP 501:593  6135:7868  1668:2582  3369:4330  9702:15999  8252:13674  3944:5707  2896:4392  9169:17391  
Desigual 5967:9115  5519:9378  3978:5563  7319:13092  8558:10541  
Stradivarius 7833:15538  7407:10477  9768:13688  9797:15637  2466:2812  7606:13665  580:1024  4792:7212  1864:2090  623:1241  1116:2056  1839:3465  4520:5846  8724:14790  9636:16158  6885:8021  3014:5378  1442:2096  7939:7975  4084:5292  4301:7688  1389:2447  1541:1874  7436:8836  8520:10214  3495:5263  4953:9306  1578:2970  3798:6904  1592:2620  5872:6308  4085:5857  8321:12453  3866:5965  5549:10073  8198:12214  
ZARA 9518:12033  6790:12201  5220:8389  6822:7886  6625:8053  2330:3538  7579:14655  7586:12274  6358:11155  7687:7803  9470:18652  
Levis 6385:9497  9988:19165  7121:11287  1501:2316  2579:4985  6853:8314  
MassimoDutti 5875:7320  4789:9414  1538:2216  5045:8925  4974:8513  2461:3961  2360:4041  4193:8238  3863:5754  4590:6654  1403:1585  4999:9384  1737:2439  3214:4750  5302:7477  5793:10278  3983:6372  6102:12201  6796:11675  9310:15946  5396:9341  5958:9112  1486:2824  1147:1382  2418:3739  3349:6643  6206:8961  6268:6524  8613:12536  9916:13140  6772:10423  5283:5840  6444:10106  1470:2586  3490:6018  2218:2808  5303:9398  7462:13680  496:716  8105:10148  2749:5244  7236:8028  4949:8370  4180:4589  7204:12798  5380:10760  4566:8941  3251:4626  9114:11094  1618:1896  
Diesel 6911:11246  6373:8142  5469:10746  8925:10579  3159:5944  9439:16987  6076:7702  5513:10041  436:717  9956:11360  7310:7786  2368:2513  8589:11212  1215:1938  8441:8880  3897:7328  3332:6124  9273:10409  7164:12025  1426:1566  1181:1678  6429:10230  2932:4400  1264:1659  4014:7472  2072:3575  2269:2446  498:888  5156:6306  7401:8986  8217:15414  8210:10862  2932:3266  2413:4194  7455:13196  4279:4450  4103:7840  8477:12871  6413:10470  9100:9598  6449:8291  7370:12116  1054:1500  4831:5558  4438:5086  3167:5873  8518:10872  2390:2893  
H&M 3848:7374  8797:12477  1240:1719  577:935  2620:4112  716:781  2896:4685  824:1565  8119:8676  8965:17503  7769:11291  8432:13984  6532:10276  2304:4408  9596:16915  993:1808  8503:16782  2273:3323  7109:9664  5660:11049  708:1070  3602:4133  8152:8359  1641:1677  4562:5488  5219:8159  7752:9557  8558:12196  1064:1963  4010:5531  6506:7745  1557:2661  9926:17100  4396:5788  6640:12243  7297:9141  7547:13233  9488:12316  5830:8733  8727:8799  9618:14282  
P&B 7577:10157  474:555  7234:14022  9951:17736  7774:13741  2816:4429  384:735  5824:8043  6022:8681  3764:6387  8647:16231  3262:5741  4714:8433  5490:8897  3737:5052  4932:8355  7429:10196  1522:1558  8488:16514  5599:7254  5557:6347  9916:17501  8913:16125  775:1267  7189:11996  7044:11387  3238:3852  7780:13183  1686:3278  7523:14707  3422:5782  8974:16318  8050:10189  4350:5821  8460:13622  9121:16086  6025:6678  7960:10383  8805:16415  9435:10086  3160:3550  8310:8523  6551:10056  7142:8746  9948:13982  3881:4845  2318:4400  2352:4228  9883:17025  
Bershka 5076:6917  709:1120  5664:10973  2174:3899  8816:14510  558:887  9752:10401  7107:9222  
Holister 1471:1908  4358:5850  3544:3619  3463:3774  3799:3973  3307:4279  4641:8691  4025:6667  5798:8432  2072:2882  6308:9647  1367:1967  1474:2113  7640:15070  8772:9704  2388:3629  6226:7222  5115:9375  5125:6084  3240:5626  5703:10270  1011:1495  4072:5462  9933:15013  4559:7457  1858:2892  4572:7414
```

Output:
```angular2html
Stores read: 11
=== By average discount ===
Levis
Average discount: 35.8%
Total discount: 21137
48% 2579/4985
47% 9988/19165
36% 7121/11287
35% 1501/2316
32% 6385/9497
17% 6853/8314
MassimoDutti
Average discount: 33.4%
Total discount: 133040
50% 5380/10760
49% 6102/12201
49% 4789/9414
49% 4193/8238
49% 3349/6643
48% 4566/8941
47% 2749/5244
47% 1486/2824
46% 4999/9384
45% 7462/13680
43% 7204/12798
43% 5793/10278
43% 5303/9398
43% 5045/8925
43% 1470/2586
42% 5396/9341
42% 3490/6018
41% 9310/15946
41% 6796/11675
41% 4974/8513
41% 2360/4041
40% 4949/8370
37% 3983/6372
37% 2461/3961
36% 6444/10106
35% 6772/10423
35% 2418/3739
34% 5958/9112
32% 3863/5754
32% 3214/4750
31% 8613/12536
31% 4590/6654
30% 6206/8961
30% 1538/2216
30% 496/716
29% 5302/7477
29% 3251/4626
28% 1737/2439
24% 9916/13140
21% 2218/2808
20% 8105/10148
19% 5875/7320
17% 9114/11094
17% 1147/1382
14% 1618/1896
11% 1403/1585
 9% 7236/8028
 9% 5283/5840
 8% 4180/4589
 3% 6268/6524
Desigual
Average discount: 33.0%
Total discount: 16348
44% 7319/13092
41% 5519/9378
34% 5967/9115
28% 3978/5563
18% 8558/10541
=== By total discount ===
Desigual
Average discount: 33.0%
Total discount: 16348
44% 7319/13092
41% 5519/9378
34% 5967/9115
28% 3978/5563
18% 8558/10541
Bershka
Average discount: 32.3%
Total discount: 18073
48% 5664/10973
44% 2174/3899
39% 8816/14510
37% 558/887
36% 709/1120
26% 5076/6917
22% 7107/9222
 6% 9752/10401
Levis
Average discount: 35.8%
Total discount: 21137
48% 2579/4985
47% 9988/19165
36% 7121/11287
35% 1501/2316
32% 6385/9497
17% 6853/8314
```

# 26. [File System 2](FileSystemTest2.java)
Да се имплементира класа FileSystem за едноставен податочен систем. За вашиот податочен систем треба да имплементирате сопствена класа за датотека File со податоци за име (String), големина (Integer) и време на креирање (LocalDateTime) Класата треба да ги овозможува следните функционалности:
- public void addFile(char folder, String name, int size, LocalDateTime createdAt) - метод за додавање нова датотека File во фолдер со даденото име (името на фолдерот е еден знак, може да биде . или голема буква)
- public List<File> findAllHiddenFilesWithSizeLessThen(int size) - враќа листа на сите скриени датотеки (тоа се датотеки чие што име започнува со знакот за точка .) со големина помала од size.
- public int totalSizeOfFilesFromFolders(List<Character> folders) - враќа вкупна големина на сите датотеки кои се наоѓаат во фолдерите кои се зададени во листата folders
- public Map<Integer, Set<File>> byYear() - враќа мапа Map во која за датотеките се групирани според годината на креирање.
- public Map<String, Long> sizeByMonthAndDay() - враќа мапа Map во која за секој месец и ден (независно од годината) се пресметува вкупната големина на сите датотеки креирани во тој месец и тој ден. Месецот се добива со повик на методот getMonth(), а денот getDayOfMonth().
Датотеките во секој фолдер се подредени според датумот на креирање во растечки редослед, потоа според името лексикографски и на крај според големината. Да се имплементира ваков компаратор во самата класа File. Исто така да се имплементира и toString репрезентација во следниот формат:
%-10[name] %5[size]B %[createdAt]

Input:
```angular2html
26
T:cvl:326:2688
V:.brf:2968:3466
J:tiz:1354:13630
S:.fat:1705:11453
V:rgo:1206:14728
I:rtec:72:16816
F:kfs:2505:2999
F:upxv:4995:15353
M:ziufl:3804:9461
B:qpbay:4814:7169
T:vgp:4847:2672
L:.jwtk:586:2782
W:.clpu:421:16045
C:riyk:4707:16690
W:nwddi:3313:6279
X:nbzc:761:10555
D:soo:4715:19413
F:xmft:2834:18113
U:.ukdfz:536:15917
J:.pna:218:6777
O:.lwqq:208:5236
O:jdvx:3531:6424
.:iegda:671:17305
C:wdqo:3877:16563
G:.rvke:183:13165
Y:gpt:2306:5372
3
```

Output:
```angular2html
== Size by month and day
APRIL-15 -> 2306
APRIL-20 -> 4707
AUGUST-13 -> 671
AUGUST-20 -> 326
AUGUST-21 -> 1705
AUGUST-25 -> 3877
AUGUST-29 -> 208
DECEMBER-13 -> 183
DECEMBER-15 -> 72
DECEMBER-17 -> 4995
FEBRUARY-3 -> 3804
FEBRUARY-5 -> 761
JANUARY-24 -> 421
JULY-4 -> 2968
JUNE-1 -> 536
JUNE-10 -> 218
MAY-14 -> 4814
MAY-18 -> 586
MAY-28 -> 2834
MAY-29 -> 3531
NOVEMBER-5 -> 4715
OCTOBER-13 -> 2505
OCTOBER-21 -> 3313
SEPTEMBER-2 -> 1206
SEPTEMBER-5 -> 6201
```

# 28. [Payroll System](PayrollSystemTest.java)
Да се имплементира апликација за евиденција на работниот ангажман на вработени во една ИТ компанија. За таа цел да се имплементира класата PayrollSystem во која што ќе се чуваат информации за вработени во компанијата. Постојат два типа на вработени HourlyEmployee и FreelanceEmployee. HourlyEmployee добиваат плата базирана на вкупниот број на изработени часови, додека пак FreelanceEmployee добиваат плата базирана на поените на тикетите што ги решиле. За класата PayrollSystem да се имплементираат:
- PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel) - конструктор со два аргументи - мапи. Првата мапа означува колку е саатницата за соодветно ниво за вработените што земаат плата по час работа, а втората мапа означува колку е платата по поен од тикет за соодветното ниво за фриленсерите.
- void readEmployeesData (InputStream is) - метод за вчитување на податоците за вработените во компанијата, при што за секој вработен податоците се дадени во нов ред.
Податоците за вработените се во следниот формат:
  - Доколку вработениот е HourlyEmployee: H;ID;level;hours;
  - Доколку вработениот е FreelanceEmployee: F;ID;level;ticketPoints1;ticketPoints2;...;ticketPointsN;
- Map<String, Collection<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels) - метод којшто нa излезен поток ќе врати мапа од вработeните во нивоата levels групирани по нивоа. Вработените да бидат сортирани според плата во опаѓачки редослед во рамките на нивото. Доколку платата е иста, да се споредуваат според нивото.

Дополнителни информации:
- Платата на HourlyEmployee се пресметува така што сите часови работа до 40 часа се множат со саатницата определена за нивото, а сите часови работа над 40 часа, се множат со саатницата на нивото зголемена за коефициент 1.5.
- Платата на FreelanceEmployee се пресметува така што сумата на поените на тикетите коишто програмерот ги решил се множат со плата по тикет (ticket rate) за нивото.

Input:
```angular2html
F;72926a;level7;5;6;8;1
F;c8b1bc;level5;3;8;3;4;6;7;1;3;7;4;7
F;e911a6;level6;3;4;5;8;3;8;7;5;5
H;157f3d;level10;63.14
F;b4ba13;level6;8;3;3;2;2
F;363bba;level4;3;1;4;2
F;1b6f52;level7;8;5;3;4;5;7;6
F;38a77b;level3;6;7;1;7
F;b0b200;level7;8;5;4;8;5;5;5;1;3;8;1;2
F;596ed2;level10;1;6;4;7;6;4;8;5;2
F;ef8875;level8;7;3;7;1;5;5;5
H;862dd9;level4;41.16
H;44fb60;level3;48.74
F;7bda21;level10;2;2;5;8;5;6;7
F;3c4d1a;level7;2;7;1;4;7;2;6;1
F;0e7a7f;level4;1;4;8;7
H;881177;level5;63.19
H;19b391;level8;41.42
F;9ffa75;level8;4;7;2;3;6;5;1;3;4;4;3
H;2aba0f;level6;40.47
```

Output:
```angular2html
READING OF THE EMPLOYEES DATA
PRINTING EMPLOYEES BY LEVEL
LEVEL: level10
Employees: 
Employee ID: 157f3d Level: level10 Salary: 2390.72 Regular hours: 40.00 Overtime hours: 23.14
Employee ID: 596ed2 Level: level10 Salary: 1290.00 Tickets count: 9 Tickets points: 43
Employee ID: 7bda21 Level: level10 Salary: 1050.00 Tickets count: 7 Tickets points: 35
------------
LEVEL: level5
Employees: 
Employee ID: 881177 Level: level5 Salary: 1570.49 Regular hours: 40.00 Overtime hours: 23.19
Employee ID: c8b1bc Level: level5 Salary: 927.50 Tickets count: 11 Tickets points: 53
------------
LEVEL: level6
Employees: 
Employee ID: e911a6 Level: level6 Salary: 960.00 Tickets count: 9 Tickets points: 48
Employee ID: 2aba0f Level: level6 Salary: 944.36 Regular hours: 40.00 Overtime hours: 0.47
Employee ID: b4ba13 Level: level6 Salary: 360.00 Tickets count: 5 Tickets points: 18
------------
LEVEL: level7
Employees: 
Employee ID: b0b200 Level: level7 Salary: 1237.50 Tickets count: 12 Tickets points: 55
Employee ID: 1b6f52 Level: level7 Salary: 855.00 Tickets count: 7 Tickets points: 38
Employee ID: 3c4d1a Level: level7 Salary: 675.00 Tickets count: 8 Tickets points: 30
Employee ID: 72926a Level: level7 Salary: 450.00 Tickets count: 4 Tickets points: 20
------------
LEVEL: level8
Employees: 
Employee ID: 19b391 Level: level8 Salary: 1162.79 Regular hours: 40.00 Overtime hours: 1.42
Employee ID: 9ffa75 Level: level8 Salary: 1050.00 Tickets count: 11 Tickets points: 42
Employee ID: ef8875 Level: level8 Salary: 825.00 Tickets count: 7 Tickets points: 33
------------
```

# 31. [Stadium](StadiumTest.java)
Да се имплементира систем за билети за стадион. За таа цел треба да се имплементираат класите:
- Sector во која се чуват информации за:
  - кодот на секторот String
  - бројот на места за седење int
  - информации за зафатеност на местата за седење ?
- Stadium во која се чуваат информации за:
  - името на стадионот String
  - и сите сектори во стадионот ?

Во класата Stadium треба да се имплементираат следните методи:
- Stadium(String name) конструктор со аргумент име на стадионот
- void createSectors(String[] sectorNames, int[] sizes) креирање на сектори со имиња String[] sectorNames и број на места int[] sizes (двете низи се со иста големина)
- void buyTicket(String sectorName, int seat, int type) за купување билет од проследениот тип (type, 0 - неутрален, 1 - домашен, 2 - гостински), во секторот sectorName со број на место seat (местото секогаш е со вредност во опсег 1 - size). Ако местото е зафатено (претходно е купен билет на ова место) се фрла исклучок од вид SeatTakenException. Исто така ако се обидеме да купиме билет од тип 1, во сектор во кој веќе има купено билет од тип 2 (и обратно) се фрла исклучок од вид SeatNotAllowedException.
- void showSectors() ги печати сите сектори сортирани според бројот на слободни места во опаѓачки редослед (ако повеќе сектори имаат ист број на слободни места, се подредуваат според името).

Input:
```angular2html
8
Old Trafford
A;100
B;100
C;100
D;100
E;100
F;100
G;100
H;100
50
E;72;1
H;25;1
B;67;1
B;56;2
G;62;2
E;69;2
G;40;1
A;16;0
C;78;1
A;67;1
A;13;0
A;88;1
G;42;0
B;70;0
C;8;2
G;85;0
D;54;1
C;3;2
D;94;2
A;98;1
G;37;0
A;86;1
G;44;2
A;71;1
H;66;2
B;98;0
D;90;2
E;61;2
G;34;0
D;96;2
B;4;0
H;58;0
F;3;2
G;73;2
H;42;0
G;6;2
B;74;0
A;14;2
A;93;0
D;51;0
C;10;2
B;88;0
C;71;1
H;13;1
H;1;2
G;37;1
B;43;0
A;44;2
D;29;0
B;22;1
```

Output:
```angular2html
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatNotAllowedException
SeatTakenException
SeatNotAllowedException
E	99/100	1.0%
F	99/100	1.0%
C	98/100	2.0%
D	97/100	3.0%
H	96/100	4.0%
A	92/100	8.0%
B	92/100	8.0%
G	92/100	8.0%
```

# 36. [Cars](CarTest.java)
Да се напише класа за автомобил Car во која се чува:
- производител
- модел
- цена
- моќност.

Да се имплементира конструктор со следните аргументи Car(String manufacturer, String model, int price, float power).
Потоа да се напише класа CarCollection во која се чува колекција од автомобили. Во оваа класа треба да се имплментираат следните методи:
- public void addCar(Car car) - додавање автомобил во колекцијата
- public void sortByPrice(boolean ascending) - подредување на колекцијата по цената на автомобилот (во растечки редослед ако аргументот ascending е true, во спротивно, во опаѓачки редослед). Ако цената на автомобилите е иста, сортирањето да се направи според нивната моќноста.
- public List<Car> filterByManufacturer(String manufacturer) - враќа листа со автомобили од одреден производител (споредбата е според името на производителот без да се води сметка за големи и мали букви во името). Автомобилите во оваа листата треба да бидат подредени според моделот во растечки редослед.
- public List<Car> getList() - ја враќа листата со автомобили од колекцијата.

Input:
```angular2html
Fiat Punto 13500 65
Ford Fiesta 14200 75
VW Polo 13800 77
Renault Clio 12100 96
Mazda 2 14200 74
Citroen C2 12900 64
Ford Focus 16200 88
VW Golf 17100 94
Mazda 3 16500 91
Renault Megan 16900 96
Fiat Stilo 17100 92
mAzda
```

Output:
```angular2html
=== Sorted By Price ASC ===
Renault Clio (96KW) 12100
Citroen C2 (64KW) 12900
Fiat Punto (65KW) 13500
VW Polo (77KW) 13800
Mazda 2 (74KW) 14200
Ford Fiesta (75KW) 14200
Ford Focus (88KW) 16200
Mazda 3 (91KW) 16500
Renault Megan (96KW) 16900
Fiat Stilo (92KW) 17100
VW Golf (94KW) 17100
=== Sorted By Price DESC ===
VW Golf (94KW) 17100
Fiat Stilo (92KW) 17100
Renault Megan (96KW) 16900
Mazda 3 (91KW) 16500
Ford Focus (88KW) 16200
Ford Fiesta (75KW) 14200
Mazda 2 (74KW) 14200
VW Polo (77KW) 13800
Fiat Punto (65KW) 13500
Citroen C2 (64KW) 12900
Renault Clio (96KW) 12100
=== Filtered By Manufacturer: mAzda ===
Mazda 2 (74KW) 14200
Mazda 3 (91KW) 16500
```

# 37. [Airports](AirportsTest.java)
Да се имплементира класа Airports со следните методи:
- public void addAirport(String name, String country, String code, int passengers) - метод за додавање нов аеродром (име, држава, код и број на патници кои ги превезува годишно)
- public void addFlights(String from, String to, int time, int duration) - метод за додавање летови (код на аеродром за полетување, код на аеродром за слетување, време на тргнување во минути поминати од 0:00 часот, времетраење на летот во минути). Од аеродром А до аеродром Б може да има повеќе летови.
- public void showFlightsFromAirport(String code) - метод кои ги прикажува сите летови од аеродромот со код code. Прво се печати името на аеродромот (формат во пример излезот), потоа се печатат сите летови (формат во пример излезот) подредени најпрво лексикографски според кодот на аеродромот дестинација, а потоа летовите кон тој аеродром според времето на полетување (целосно точна имплементација се смета без повикување на sort методи).
- public void showDirectFlightsFromTo(String from, String to) - метод кој ги прикажува сите директни летови од аеродромот со код from до аеродромот со код to.
- public void showDirectFlightsTo(String to) - метод кои ги прикажува сите директни летови до аеродромот со код to.

Сите летови треба да бидат сортирани според времето на полетување (целосно точна имплементација се смета без повикување на sort методи).

Input:
```angular2html
30
Hartsfield Jackson Atlanta International;United States;ATL;95462867
Beijing Capital International;China;PEK;81929359
London Heathrow;United Kingdom;LHR;70037417
Tokyo International;Japan;HND;66795178
Chicago O’Hare International;United States;ORD;66633503
Los Angeles International;United States;LAX;63688121
Charles de Gaulle International;France;CDG;61611934
Dallas Fort Worth International;United States;DFW;58591842
Soekarno-Hatta International;Indonesia;CGK;57772762
Dubai International;United Arab Emirates;DXB;57684550
Frankfurt am Main International;Germany;FRA;57520001
Hong Kong International Kai Tak;Hong Kong;HKG;56057751
Denver International;United States;DEN;53156278
Suvarnabhumi;Thailand;BKK;53002328
Singapore Changi International;Singapore;SIN;51181804
Amsterdam Schiphol;Netherlands;AMS;51035590
John F Kennedy International;United States;JFK;49291765
Guangzhou Baiyun International;China;CAN;48548430
Madrid Barajas International;Spain;MAD;45176978
Atatürk International;Turkey;IST;45124831
Shanghai Pudong International;China;PVG;44880164
San Francisco International;United States;SFO;44399885
Charlotte Douglas International;United States;CLT;41228372
McCarran International;United States;LAS;40799830
Phoenix Sky Harbor International;United States;PHX;40421611
George Bush Intercontinental Houston;United States;IAH;39891444
Kuala Lumpur International;Malaysia;KUL;39887866
Miami International;United States;MIA;39467444
Incheon International;South Korea;ICN;39154375
Munich International;Germany;MUC;38360604
72
MIA;CDG;1140;66
PHX;HND;831;96
AMS;DFW;1030;159
FRA;JFK;367;226
SIN;CAN;925;47
ATL;LAS;1236;260
HND;PVG;1273;256
PEK;LAS;1237;297
JFK;HKG;377;108
SFO;DXB;48;47
ORD;CLT;826;97
CLT;IST;831;216
PHX;ATL;22;153
MUC;BKK;740;151
PVG;CDG;455;296
CDG;BKK;805;267
PHX;CAN;352;170
HKG;DEN;255;39
IAH;SIN;574;254
MAD;DXB;960;56
IST;AMS;750;205
IST;JFK;1187;64
PEK;DEN;1348;219
MUC;ICN;298;41
CGK;CDG;917;287
SIN;MAD;35;55
SIN;BKK;499;141
LAX;CAN;833;205
LAX;CAN;1316;274
ORD;PVG;744;117
CGK;DEN;1068;144
LAS;ORD;851;210
KUL;SIN;420;231
HND;AMS;884;272
MAD;IST;763;153
ATL;IAH;194;89
HND;PEK;299;110
MAD;DFW;1137;226
FRA;HKG;372;83
LHR;DXB;268;77
LAX;ORD;994;300
MAD;BKK;698;152
AMS;SFO;38;78
SFO;MAD;444;184
LAX;PVG;1355;211
PHX;CLT;338;239
BKK;CLT;1135;235
DFW;HKG;688;34
HND;DFW;1048;292
PHX;DXB;55;277
KUL;PVG;760;51
DEN;ATL;212;94
ORD;FRA;449;94
PEK;IAH;1208;233
FRA;HKG;862;278
ICN;SFO;1394;102
CAN;SIN;1194;109
IST;JFK;1392;237
JFK;HND;1373;145
HKG;DXB;847;39
ICN;HKG;390;286
CAN;CDG;842;182
SFO;PEK;59;88
CDG;DXB;417;104
MUC;JFK;1296;210
MAD;KUL;565;184
KUL;PEK;944;127
ORD;SIN;1194;186
MUC;JFK;299;169
IST;JFK;1061;87
KUL;DXB;1204;282
DFW;KUL;542;270
3 25
```

Output:
```angular2html
===== FLIGHTS FROM HND =====
Tokyo International (HND)
Japan
66795178
1. HND-AMS 14:44-19:16 4h32m
2. HND-DFW 17:28-22:20 4h52m
3. HND-PEK 04:59-06:49 1h50m
4. HND-PVG 21:13-01:29 +1d 4h16m
===== DIRECT FLIGHTS FROM HND TO IAH =====
No flights from HND to IAH
===== DIRECT FLIGHTS TO ATL =====
PHX-ATL 00:22-02:55 2h33m
DEN-ATL 03:32-05:06 1h34m
```

# 42. [Daily Temperatures](DailyTemperatureTest.java)
Да се имплементира класа DailyTemperatures во која се вчитуваат температури на воздухот (цели броеви) за различни денови од годината (број од 1 до 366). Температурите за еден ден се во еден ред во следниот формат (пример): 137 23C 15C 28C. Првиот број претставува денот во годината, а потоа следуваат непознат број на мерења на температури за тој ден во скала во Целзиусови степени (C) или Фаренхајтови степени (F).

Во оваа класа да се имплементираат методите:
- DailyTemperatures() - default конструктор
- void readTemperatures(InputStream inputStream) - метод за вчитување на податоците од влезен тек
- void writeDailyStats(OutputStream outputStream, char scale) - метод за печатање на дневна статистика (вкупно мерења, минимална температура, максимална температура, просечна температура) за секој ден, подредени во растечки редослед според денот. Вториот аргумент scale одредува во која скала се печатат температурите C - Целзиусова, F - Фаренхајтова. Форматот за печатање на статистиката за одреден ден е следниот:
[ден]: Count: [вк. мерења - 3 места] Min: [мин. температура] Max: [макс. температура] Avg: [просек ]

Минималната, максималната и просечната температура се печатат со 6 места, од кои 2 децимални, а по бројот се запишува во која скала е температурата (C/F).
Формула за конверзија од Целзиусуви во Фаренхајтови: $\frac{T * 9}{5} + 32$
Формула за конверзија од Фаренхајтови во Целзиусуви: $\frac{(T - 32) * 5}{9}$

Забелешка: да се постигне иста точност како во резултатите од решението, за пресметување на просекот и конверзијата во различна скала температурите се чуваат со тип Double.

Input:
```angular2html
299 5C 9C 6C 8C 5C 7C 7C 8C 7C 8C 6C 10C 10C 6C 10C 6C 8C
64 56F
270 74F 78F 75F 75F 76F 78F 75F 79F
227 57F 58F
28 94F 90F 91F 92F 92F 91F 92F 90F 90F 93F 95F 94F 94F 91F 92F 94F
345 29C 30C 26C 29C 29C 29C 27C 27C 27C
95 100F 104F 100F
54 35F
178 10C 12C 15C 10C 11C
335 9C 5C 6C 7C 9C 10C 6C 7C 5C 9C
296 21C 21C 21C 21C 23C 24C 22C
274 -12C -16C -12C -16C -16C -13C
72 97F 95F 97F 96F 96F 98F 94F 97F 97F 99F 94F 94F 98F 96F
100 96F 93F 91F 93F 94F 93F 91F 91F 95F 96F
41 32C 34C 37C
1 6C 5C 6C 9C 5C 7C 10C 9C 7C 6C 8C 7C 7C 7C
82 -16C -13C -14C -12C -15C -15C
133 60F 58F 60F 59F 61F 60F 57F 59F 59F 61F 58F 61F 57F 61F 60F 57F 57F 56F
4 36C 33C 34C 34C 35C 33C 33C 31C
68 83F 81F 81F 83F 81F
317 94F 95F 97F 95F 98F 97F 94F 97F 98F 96F 95F 97F 94F 99F 94F 98F 94F 95F
5 89F 91F 90F 93F 93F 92F 92F 90F 94F 92F 94F 93F 89F 93F 91F 93F 91F 92F
125 37C 37C 34C 37C 39C 35C 36C 37C 34C 35C 34C 35C 36C
332 36F 32F 37F 34F 36F 34F 37F 36F 35F 35F 33F
229 75F 79F 77F 76F 75F 76F 75F 76F 77F 76F 78F 77F 80F 76F 75F 78F 77F
142 18F 16F 21F 21F 20F 16F 17F 17F 20F 20F 20F 21F 16F 21F 20F
282 -18C -18C -15C -16C -18C -19C -18C -19C -17C -16C -18C -18C -17C -16C -20C -15C -18C -20C -16C
71 36C 40C
135 25C 27C 26C 29C 27C 27C 27C 28C 30C 25C 25C 28C 29C 25C 28C 27C 25C
16 35C 32C 32C 30C 34C 31C 34C 34C 31C 32C 33C 31C 34C 35C 31C
180 101F 102F 99F 103F 99F 101F 99F 102F 100F 103F 98F 98F 98F 99F 99F 102F 103F
311 41C 41C 37C 40C 37C 42C 41C 40C 39C 39C
```

Output:
```angular2html
=== Daily temperatures in Celsius (C) ===
  1: Count:  14 Min:   5.00C Max:  10.00C Avg:   7.07C
  4: Count:   8 Min:  31.00C Max:  36.00C Avg:  33.63C
  5: Count:  18 Min:  31.67C Max:  34.44C Avg:  33.21C
 16: Count:  15 Min:  30.00C Max:  35.00C Avg:  32.60C
 28: Count:  16 Min:  32.22C Max:  35.00C Avg:  33.44C
 41: Count:   3 Min:  32.00C Max:  37.00C Avg:  34.33C
 54: Count:   1 Min:   1.67C Max:   1.67C Avg:   1.67C
 64: Count:   1 Min:  13.33C Max:  13.33C Avg:  13.33C
 68: Count:   5 Min:  27.22C Max:  28.33C Avg:  27.67C
 71: Count:   2 Min:  36.00C Max:  40.00C Avg:  38.00C
 72: Count:  14 Min:  34.44C Max:  37.22C Avg:  35.71C
 82: Count:   6 Min: -16.00C Max: -12.00C Avg: -14.17C
 95: Count:   3 Min:  37.78C Max:  40.00C Avg:  38.52C
100: Count:  10 Min:  32.78C Max:  35.56C Avg:  34.06C
125: Count:  13 Min:  34.00C Max:  39.00C Avg:  35.85C
133: Count:  18 Min:  13.33C Max:  16.11C Avg:  14.97C
135: Count:  17 Min:  25.00C Max:  30.00C Avg:  26.94C
142: Count:  15 Min:  -8.89C Max:  -6.11C Avg:  -7.26C
178: Count:   5 Min:  10.00C Max:  15.00C Avg:  11.60C
180: Count:  17 Min:  36.67C Max:  39.44C Avg:  37.97C
227: Count:   2 Min:  13.89C Max:  14.44C Avg:  14.17C
229: Count:  17 Min:  23.89C Max:  26.67C Avg:  24.80C
270: Count:   8 Min:  23.33C Max:  26.11C Avg:  24.58C
274: Count:   6 Min: -16.00C Max: -12.00C Avg: -14.17C
282: Count:  19 Min: -20.00C Max: -15.00C Avg: -17.47C
296: Count:   7 Min:  21.00C Max:  24.00C Avg:  21.86C
299: Count:  17 Min:   5.00C Max:  10.00C Avg:   7.41C
311: Count:  10 Min:  37.00C Max:  42.00C Avg:  39.70C
317: Count:  18 Min:  34.44C Max:  37.22C Avg:  35.52C
332: Count:  11 Min:   0.00C Max:   2.78C Avg:   1.67C
335: Count:  10 Min:   5.00C Max:  10.00C Avg:   7.30C
345: Count:   9 Min:  26.00C Max:  30.00C Avg:  28.11C
=== Daily temperatures in Fahrenheit (F) ===
  1: Count:  14 Min:  41.00F Max:  50.00F Avg:  44.73F
  4: Count:   8 Min:  87.80F Max:  96.80F Avg:  92.53F
  5: Count:  18 Min:  89.00F Max:  94.00F Avg:  91.78F
 16: Count:  15 Min:  86.00F Max:  95.00F Avg:  90.68F
 28: Count:  16 Min:  90.00F Max:  95.00F Avg:  92.19F
 41: Count:   3 Min:  89.60F Max:  98.60F Avg:  93.80F
 54: Count:   1 Min:  35.00F Max:  35.00F Avg:  35.00F
 64: Count:   1 Min:  56.00F Max:  56.00F Avg:  56.00F
 68: Count:   5 Min:  81.00F Max:  83.00F Avg:  81.80F
 71: Count:   2 Min:  96.80F Max: 104.00F Avg: 100.40F
 72: Count:  14 Min:  94.00F Max:  99.00F Avg:  96.29F
 82: Count:   6 Min:   3.20F Max:  10.40F Avg:   6.50F
 95: Count:   3 Min: 100.00F Max: 104.00F Avg: 101.33F
100: Count:  10 Min:  91.00F Max:  96.00F Avg:  93.30F
125: Count:  13 Min:  93.20F Max: 102.20F Avg:  96.52F
133: Count:  18 Min:  56.00F Max:  61.00F Avg:  58.94F
135: Count:  17 Min:  77.00F Max:  86.00F Avg:  80.49F
142: Count:  15 Min:  16.00F Max:  21.00F Avg:  18.93F
178: Count:   5 Min:  50.00F Max:  59.00F Avg:  52.88F
180: Count:  17 Min:  98.00F Max: 103.00F Avg: 100.35F
227: Count:   2 Min:  57.00F Max:  58.00F Avg:  57.50F
229: Count:  17 Min:  75.00F Max:  80.00F Avg:  76.65F
270: Count:   8 Min:  74.00F Max:  79.00F Avg:  76.25F
274: Count:   6 Min:   3.20F Max:  10.40F Avg:   6.50F
282: Count:  19 Min:  -4.00F Max:   5.00F Avg:   0.55F
296: Count:   7 Min:  69.80F Max:  75.20F Avg:  71.34F
299: Count:  17 Min:  41.00F Max:  50.00F Avg:  45.34F
311: Count:  10 Min:  98.60F Max: 107.60F Avg: 103.46F
317: Count:  18 Min:  94.00F Max:  99.00F Avg:  95.94F
332: Count:  11 Min:  32.00F Max:  37.00F Avg:  35.00F
335: Count:  10 Min:  41.00F Max:  50.00F Avg:  45.14F
345: Count:   9 Min:  78.80F Max:  86.00F Avg:  82.60F
```

# 45. [Lab Exercises](LabExercisesTest.java)
Да се напише класата Student во која што ќе се чуваат информации за:
- индекс на студент ФИНКИ (стринг составен од шест бројки)
- листа на поени добиени на лабораториски вежби по некој предмет ФИНКИ. По предметот се изведуваат максимум 10 лабораториски вежби.

За класата да се напише конструктор Student(String index, List<Integer> points).

Да се напише класа LabExercises во која што се чува колекција од студенти. За класата да се напишат следните методи:
- public void addStudent (Student student)- метод за додавање на нов студент во колекцијата
- public void printByAveragePoints (boolean ascending, int n) - метод којшто ќе ги печати првите n студентите сортирани според сумарните поени, а доколку се исти сумарните поени, според индексот, во растечки редослед доколку ascending е true, a во спротивно во опаѓачки.
сумарните поени се пресметуваат како збирот на поените поделен со 10.
- public List<Student> failedStudents () - метод којшто враќа листа од студенти кои не добиле потпис (имаат повеќе од 2 отсуства), сортирани прво според индексот, а потоа според сумарните поени.
- public Map<Integer,Double> getStatisticsByYear() - метод којшто враќа мапа од просекот од сумарните поени на студентите според година на студирање. Да се игнорираат студентите кои не добиле потпис.

Input:
```angular2html
152040 0 1 2 3 0 5 4 10
173071 10 0 0 3 0 5 3 10
181502 10 10 10 10 10 8 10 10 10 10
181507 10 10 10 10 10 10 10 10 9 10
181508 5 6 5 5 6 5 5 5 6
181509 3 8 6 3 0 0 4 5 1
181510 3 3 8 9 6 1 9 8 8
181513 2 10 10 8 9 10 10 7 2 8
181515 3 10 10 3 9 9 5 10 6 8
181517 3 2 5 2 0 2 1 1 1
181519 3 9 6 8 9 1 5 6 8
181521 2 10 8 0 
181522 10 2 6 2 2 1 3 1
181523 8 10 10 8 10 10 10 10 10
181524 3 8 8 3 8 10 0 10 3 5
181526 2 2 2 2 1 3 2 1
181527 3 2 7 3 6 1 9 1
181530 2 6 4 7 1 5 0 5
181534 8 9 8 4 8 9 5 10 4 
181536 10 10 8 7 8 10 5 10 7 10
181538 9 9 8 9 9 8 8 8 7 10
181540 2 4 1 4 4 1 1 1 1
181542 10 6 7 8 8 9 6 0
181545 0 8 0 8 10 6 10 3 5
181546 1 3 4 2 5 5 3 1 1
181547 6 2 2 4 2 2 5 4 2 1
181550 3 3 3 1 0 3 1 0 
181552 6 2 0 2 2 3 1 1 1
181554 7 0 1 1 0 1 1
181555 10 3 4 2 2 2 3 3 2 
181561 10 10 5 3 2 8 10 5 1
181563 10 
181564 0 1 1 1 0 1 0 1
181567 0 1 1 0 2 1 1 8
181568 10 2 4 2 2 1 2 1 
181569 
181572 1 1 0 0 1 0 
181574 6 2 5 2 0 2 2 3 1
181578 10 10 9 5 10 10 10 10 10 
181580 10 10 10 10 10 10 9 10 9 9
181581 10 8 8 6 6 8 10 10 6 
135042 
161265 3 0 0 2 0 
171221 10 5 7 10 
171512 6 6 
171527 10 1 1 1 0 2 1 1
171553 10 10 5 7 10 0 7 5 5
181501 10 10 10 9 10 10 10 10 10 10
181503 10 10 8 7 9 7 5 6 6 6
181504 10 10 10 10 9 9 9 10 9 10
181505 10 10 10 10 9 10 10 10 10 10
181506 10 4 5 8 9 2 5 5 8
181511 9 10 10 10 10 10 10 10 10 10
181512 9 10 10 10 10 10 10 10 10 10
181514 3 4 0 5 0 1 1 1
181516 3 1 0 
181518 7 10 9 6 7 7 2 9 7 9
181520 7 9 7 7 6 10 5 8
181525 5 0 1 4 3 0 1 1
181529 3 10 9 6 9 10 10 10
181531 10 5 3 5 0 4 4 1
181533 6 5 6 5 5 4 1 6 5 6
181535 0 3 0 2 4 1
181537 3 0 5 7 4 5
```

Output:
```angular2html
===printByAveragePoints (ascending)===
135042 NO 0.00
181569 NO 0.00
181572 NO 0.30
181516 NO 0.40
161265 NO 0.50
181564 YES 0.50
181535 NO 1.00
181563 NO 1.00
181554 NO 1.10
171512 NO 1.20
181550 YES 1.40
181567 YES 1.40
181514 YES 1.50
181525 YES 1.50
181526 YES 1.50
171527 YES 1.70
181517 YES 1.70
181552 YES 1.80
181540 YES 1.90
181521 NO 2.00
181574 YES 2.30
181537 NO 2.40
181568 YES 2.40
152040 YES 2.50
181546 YES 2.50
181522 YES 2.70
181509 YES 3.00
181530 YES 3.00
181547 YES 3.00
173071 YES 3.10
181555 YES 3.10
171221 NO 3.20
181527 YES 3.20
181531 YES 3.20
181508 YES 4.80
181533 YES 4.90
181545 YES 5.00
181542 YES 5.40
181561 YES 5.40
181510 YES 5.50
181519 YES 5.50
181506 YES 5.60
181524 YES 5.80
171553 YES 5.90
181520 YES 5.90
181534 YES 6.50
181529 YES 6.70
181581 YES 7.20
181515 YES 7.30
181518 YES 7.30
181503 YES 7.40
181513 YES 7.60
181578 YES 8.40
181536 YES 8.50
181538 YES 8.50
181523 YES 8.60
181504 YES 9.60
181580 YES 9.70
181502 YES 9.80
181501 YES 9.90
181505 YES 9.90
181507 YES 9.90
181511 YES 9.90
181512 YES 9.90
===printByAveragePoints (descending)===
181512 YES 9.90
181511 YES 9.90
181507 YES 9.90
181505 YES 9.90
181501 YES 9.90
181502 YES 9.80
181580 YES 9.70
181504 YES 9.60
181523 YES 8.60
181538 YES 8.50
181536 YES 8.50
181578 YES 8.40
181513 YES 7.60
181503 YES 7.40
181518 YES 7.30
181515 YES 7.30
181581 YES 7.20
181529 YES 6.70
181534 YES 6.50
181520 YES 5.90
171553 YES 5.90
181524 YES 5.80
181506 YES 5.60
181519 YES 5.50
181510 YES 5.50
181561 YES 5.40
181542 YES 5.40
181545 YES 5.00
181533 YES 4.90
181508 YES 4.80
181531 YES 3.20
181527 YES 3.20
171221 NO 3.20
181555 YES 3.10
173071 YES 3.10
181547 YES 3.00
181530 YES 3.00
181509 YES 3.00
181522 YES 2.70
181546 YES 2.50
152040 YES 2.50
181568 YES 2.40
181537 NO 2.40
181574 YES 2.30
181521 NO 2.00
181540 YES 1.90
181552 YES 1.80
181517 YES 1.70
171527 YES 1.70
181526 YES 1.50
181525 YES 1.50
181514 YES 1.50
181567 YES 1.40
181550 YES 1.40
171512 NO 1.20
181554 NO 1.10
181563 NO 1.00
181535 NO 1.00
181564 YES 0.50
161265 NO 0.50
181516 NO 0.40
181572 NO 0.30
181569 NO 0.00
135042 NO 0.00
===failed students===
135042 NO 0.00
161265 NO 0.50
171221 NO 3.20
171512 NO 1.20
181516 NO 0.40
181521 NO 2.00
181535 NO 1.00
181537 NO 2.40
181554 NO 1.10
181563 NO 1.00
181569 NO 0.00
181572 NO 0.30
===statistics by year
2 : 5.38
3 : 3.57
5 : 2.50
```

# 46. [Movies](MoviesTest.java)
Да се имплементира класа MoviesList во која се чува листа од филмови (класа Movie за секој филм се дадени неговиот наслов и листа од рејтинзи (цели броеви од 1 до 10) и ги има следните методи:
- public void addMovie(String title, int[] ratings) - метод за додавање нов филм во листата (наслов и низа од рејтинзи)
- public List<Movie> top10ByAvgRating() - метод кој враќа листа од 10-те филмови со најдобар просечен рејтинг, подредени во опаѓачки редослед според рејтингот (ако два филмови имаат ист просечен рејтинг, се подредуваат лексикографски според името)
- public List<Movie> top10ByRatingCoef() - метод кој враќа листа од 10-те филмови со најдобар рејтинг коефициент (се пресметува како просечен ретјтинг на филмот x вкупно број на рејтинзи на филмот / максимален број на рејтинзи (од сите филмови во листата)

За класата Movie да се препокрие toString() методот да враќа соодветна репрезентација (погледнете го пример излезот).

Input:
```angular2html
30
Moonstruck (1987)
26
6 3 10 9 5 4 4 3 1 2 2 8 8 9 9 10 4 6 6 10 2 6 10 4 5 5
Oklahoma! (1955)
98
3 3 5 9 9 4 7 2 8 6 10 10 4 9 6 8 3 5 3 2 10 7 2 3 7 7 3 4 4 3 1 6 6 7 10 7 2 7 3 3 3 8 8 1 7 3 1 4 4 3 10 9 9 10 7 5 4 10 6 2 8 10 3 2 1 8 10 7 4 8 7 2 4 9 7 2 1 7 7 3 9 10 6 1 9 10 10 8 6 6 3 9 8 6 6 9 6 8
The War Game (1966)
40
3 2 8 9 4 3 1 3 2 6 9 4 8 2 4 8 9 2 8 9 7 10 5 7 4 8 10 10 10 1 4 9 8 4 4 5 6 9 4 1
My Dinner With Andre (1981)
35
9 5 1 9 10 1 10 3 6 10 2 7 3 3 2 5 10 10 1 5 8 3 1 1 6 4 1 5 1 2 4 1 10 8 4
7 Up/28 Up (1985)
18
6 5 2 6 10 6 5 1 3 2 2 1 10 2 6 5 2 10
Singin' in the Rain (1952)
100
5 5 2 9 1 6 8 9 6 4 8 9 1 10 10 7 5 8 1 4 9 3 2 7 2 4 2 3 4 5 10 7 10 3 10 6 7 5 9 6 3 4 7 10 1 7 6 3 5 4 6 10 3 1 4 7 4 5 1 9 8 7 5 5 1 9 3 5 3 6 7 7 8 8 4 9 4 5 5 3 9 7 4 7 2 10 9 3 2 1 2 7 4 8 10 1 9 3 3 6
Donnie Brasco (1997)
35
8 2 4 8 6 7 1 3 2 2 9 6 5 3 5 5 2 9 8 1 6 1 3 5 4 5 4 4 3 6 4 8 5 3 2
Belle de Jour (1968)
79
1 2 3 6 6 5 4 2 10 5 1 10 6 9 8 5 2 8 2 5 9 2 9 8 8 8 8 8 1 10 2 5 4 5 7 1 4 1 8 8 2 3 1 10 2 7 7 9 4 2 9 2 3 2 8 3 8 7 10 5 9 10 10 8 8 10 9 7 8 2 10 2 9 5 4 5 7 4 3
Secret Honor (1985)
15
5 3 4 2 9 8 6 6 3 10 1 6 6 7 2
The Flamingo Kid (1984)
86
8 8 8 2 6 6 6 9 3 9 7 10 3 9 8 3 5 1 8 5 7 9 4 7 6 9 1 7 6 8 10 9 5 8 10 5 9 1 7 10 5 7 9 9 3 3 9 8 5 8 5 9 4 7 6 7 8 6 8 3 3 1 3 9 1 7 5 2 6 7 8 1 8 4 4 8 6 7 2 9 10 3 4 7 1 2
Atlantic City (1981)
78
6 2 2 9 2 4 1 10 8 4 6 10 10 4 7 5 4 10 3 1 3 8 2 2 1 6 2 1 8 9 6 7 8 2 6 1 6 1 2 2 2 2 9 2 2 1 2 4 8 10 8 10 1 7 10 9 6 7 8 8 1 10 8 5 5 2 5 9 1 7 5 6 2 9 6 6 7 4
The Fortune Cookie (1966)
56
8 3 9 5 5 10 8 10 6 9 8 4 8 6 8 4 8 5 3 10 10 8 1 5 5 1 9 5 8 7 2 10 1 10 9 2 7 5 7 8 4 5 1 5 1 7 4 9 10 10 6 3 6 2 6 3
These Three (1936)
98
5 3 1 7 9 2 3 7 4 6 4 2 2 6 4 3 10 4 4 9 8 10 1 9 1 3 8 9 2 4 10 9 6 1 4 6 2 7 1 7 4 5 7 8 1 6 7 2 4 10 5 4 7 7 9 3 8 2 1 10 10 6 6 4 5 5 6 2 7 5 1 4 3 8 9 10 4 3 6 2 2 10 10 7 4 2 9 3 2 4 1 8 10 10 1 3 5 4
Black Narcissus (1947)
19
10 2 1 5 6 2 4 8 3 7 5 2 2 5 8 10 8 4 10
Adaptation (2002)
23
8 9 8 9 1 2 10 6 7 6 2 6 2 8 2 7 7 4 3 10 4 5 9
Contempt (1964)
36
4 3 8 5 2 4 3 1 7 4 8 4 4 4 4 9 2 3 6 10 8 1 4 7 7 10 3 6 4 2 9 8 7 6 5 5
Chicken Run (2000)
44
6 10 9 4 4 6 7 3 3 2 3 9 1 8 5 2 6 10 5 3 9 5 7 8 3 1 2 6 8 4 1 3 4 1 9 4 5 5 1 5 8 8 7 1
Great Expectations (1947)
9
10 8 6 5 6 2 8 3 10
Shadow of a Doubt (1943)
52
4 2 3 6 3 8 8 8 3 6 2 10 5 7 7 10 2 2 3 2 2 10 10 4 9 10 8 3 7 5 10 8 2 5 4 1 2 7 6 10 7 9 6 5 7 8 9 2 1 2 1 7
Snow White and the Seven Dwarfs (1938)
12
5 4 3 7 10 3 6 4 4 6 5 8
Mr. and Mrs. Bridge (1990)
65
2 3 2 8 9 3 8 8 4 9 9 6 2 8 5 2 10 8 7 9 6 7 1 3 8 10 2 4 5 5 10 4 10 7 3 8 9 5 10 8 10 3 4 6 1 9 10 7 4 7 8 2 3 9 10 6 5 7 2 6 5 7 2 9 3
Stolen Kisses (1969)
76
9 6 8 5 2 7 2 3 5 9 9 6 3 6 6 1 3 7 3 3 10 9 4 2 5 2 4 2 8 7 9 4 1 3 7 7 2 8 2 3 3 4 7 8 6 3 8 2 2 4 9 4 6 3 2 2 4 4 5 4 3 6 9 8 1 10 9 8 10 9 6 8 7 6 5 5
La Dolce Vita (1961)
45
3 10 9 1 4 9 9 8 7 2 7 2 3 4 6 5 5 10 3 4 3 7 7 3 10 4 10 6 1 4 8 9 9 9 7 3 4 3 6 10 2 9 4 2 2
Re-Animator (1985)
13
4 6 9 4 7 4 6 1 7 10 8 10 4
Trouble in Paradise (1932)
83
9 7 9 10 7 2 10 1 5 10 5 6 4 2 6 1 3 8 1 7 6 8 8 8 7 7 9 8 1 2 4 8 8 9 10 6 4 4 7 10 1 5 3 7 9 6 10 1 3 2 5 1 3 8 10 3 5 10 8 8 9 10 6 10 5 3 9 2 7 9 4 9 8 9 6 5 10 10 7 2 5 3 4
The Year of Living Dangerously (1982)
67
10 10 5 4 3 5 5 1 9 3 9 6 3 8 6 10 3 8 10 3 2 9 2 6 1 4 10 3 4 6 8 1 7 4 9 5 10 9 9 9 3 8 6 3 9 9 10 2 3 5 3 10 9 8 4 10 5 7 9 9 3 7 7 6 6 9 7
Fast, Cheap & Out of Control (1997)
74
9 8 1 3 2 3 5 5 1 1 7 4 10 2 4 2 8 6 5 1 6 1 3 1 2 7 3 10 9 8 4 7 1 4 7 7 10 1 5 3 4 5 9 8 9 2 2 5 1 9 2 3 6 6 3 5 6 2 6 9 5 5 6 7 1 8 4 4 3 2 1 7 7 1
Pather Panchali (1958)
87
3 6 10 1 10 1 10 10 2 10 5 1 4 1 8 7 10 7 3 8 6 1 9 9 8 9 2 3 3 9 9 8 2 3 1 4 5 7 6 9 7 9 2 3 3 2 4 8 3 3 2 1 3 10 6 5 3 8 7 10 8 1 8 9 5 5 8 7 8 5 4 9 4 2 6 3 3 5 3 1 6 6 7 2 4 7 3
Man Hunt (1941)
71
7 1 5 7 7 6 6 1 9 3 6 5 5 9 8 8 6 8 8 4 4 6 7 9 7 7 9 2 9 2 3 10 7 2 7 9 10 3 3 3 1 6 5 7 4 3 2 7 6 9 10 5 5 3 8 10 8 8 1 6 10 10 8 3 6 7 6 8 8 5 10
Barry Lyndon (1975)
73
5 1 2 4 4 2 6 8 1 5 7 10 8 3 5 3 3 2 4 1 4 6 8 3 4 10 5 4 8 5 9 3 1 3 2 5 8 10 5 10 1 3 10 6 3 4 8 8 5 10 8 3 5 9 5 2 3 1 10 9 1 7 10 2 5 5 10 7 10 7 8 8 2
```

Output:
```angular2html
=== TOP 10 BY AVERAGE RATING ===
Great Expectations (1947) (6.44) of 9 ratings
The Year of Living Dangerously (1982) (6.21) of 67 ratings
Re-Animator (1985) (6.15) of 13 ratings
Trouble in Paradise (1932) (6.11) of 83 ratings
Man Hunt (1941) (6.10) of 71 ratings
The Fortune Cookie (1966) (6.05) of 56 ratings
The Flamingo Kid (1984) (6.03) of 86 ratings
Mr. and Mrs. Bridge (1990) (6.03) of 65 ratings
Adaptation (2002) (5.87) of 23 ratings
Oklahoma! (1955) (5.84) of 98 ratings
=== TOP 10 BY RATING COEFFICIENT ===
Oklahoma! (1955) (5.84) of 98 ratings
Singin' in the Rain (1952) (5.51) of 100 ratings
The Flamingo Kid (1984) (6.03) of 86 ratings
These Three (1936) (5.24) of 98 ratings
Trouble in Paradise (1932) (6.11) of 83 ratings
Pather Panchali (1958) (5.38) of 87 ratings
Belle de Jour (1968) (5.70) of 79 ratings
Man Hunt (1941) (6.10) of 71 ratings
The Year of Living Dangerously (1982) (6.21) of 67 ratings
Atlantic City (1981) (5.19) of 78 ratings
```

# 49. [Names](NamesTest.java)
Да се имплементира класа Names со следните методи:
- public void addName(String name) - додавање на име
- public void printN(int n) - ги печати сите имиња кои се појавуваат n или повеќе пати, подредени лексикографски според името, на крајот на зборот во загради се печати бројот на појавувања, а по него на крај бројот на уникатни букви во зборот (не се прави разлика на големи и мали)
- public String findName(int len, int x) - го враќа името кое со наоѓа на позиција x (почнува од 0) во листата од уникатни имиња подредени лексикографски, по бришење на сите имиња со големина поголема или еднаква на len. Позицијата x може да биде поголема од бројот на останати имиња, во тој случај се продожува со броење од почетокот на листата. Пример за листа со 3 имиња A, B, C, ако x = 7, се добива B. A0, B1, C2, A3, B4, C5, A6, B7.

Input:
```angular2html
440
Alyssa
Brianna
Sarah
Emma
William
Nathan
Mia
Michael
John
Ryan
James
David
Mia
Hannah
Chloe
Matthew
Nicholas
Jonathan
Mia
Anthony
Jacob
Emma
Ethan
Andrew
Joshua
Grace
Emma
Emma
Elizabeth
Daniel
Tyler
Natalie
Sophia
Tyler
Sarah
Ashley
Isabella
William
Nicholas
Joseph
Alyssa
Ashley
Samantha
Alexander
Jonathan
Nathan
Sarah
Sarah
Nicholas
Joseph
Joseph
James
Brianna
Mia
Mia
Andrew
Emily
Matthew
Sarah
Sarah
Tyler
Tyler
Tyler
Samantha
Samantha
Sarah
Olivia
Tyler
Olivia
Natalie
Joseph
Matthew
Christopher
David
Michael
Alexis
Sophia
Mia
Samantha
James
David
Mia
Tyler
Grace
Natalie
Brianna
James
Joshua
David
Chloe
Matthew
Nathan
Christopher
Ethan
Hannah
Sophia
John
Isabella
Chloe
Ethan
Christopher
Samantha
Jonathan
Matthew
James
Andrew
Tyler
Isabella
Matthew
Tyler
John
Elizabeth
Michael
Mia
Emily
Tyler
Anthony
Hannah
James
Grace
Ryan
Sarah
Natalie
Elizabeth
Christopher
Joshua
Brianna
Elizabeth
Christopher
Madison
Hannah
Grace
Ava
Emily
John
Matthew
Mia
Grace
Olivia
Alexis
Nicholas
Joseph
Alexis
Abigail
Daniel
Nicholas
Nathan
Abigail
Jonathan
Samantha
Ryan
Ava
Nathan
Alexis
Samantha
James
David
Alexander
Olivia
Chloe
Sarah
Grace
Alexander
Matthew
Christopher
Michael
Sophia
Emma
Joseph
Brianna
Andrew
Joseph
Emma
Sophia
Alyssa
Alexander
Madison
Samantha
Abigail
Matthew
Joshua
Emma
Emily
Nicholas
Nathan
Michael
Sophia
Mia
Sophia
Sarah
Emily
Brianna
Michael
Hannah
Elizabeth
Nicholas
Jonathan
Chloe
James
Ethan
David
Samantha
Grace
Natalie
Sophia
Jacob
Chloe
William
Ethan
William
Abigail
Michael
William
Christopher
Daniel
Alyssa
Jonathan
William
Emma
John
Alyssa
Natalie
Mia
Chloe
Brianna
Tyler
Natalie
Jonathan
Sarah
William
Ryan
Mia
Abigail
Andrew
Emma
Andrew
William
Olivia
Hannah
Alexander
Joshua
Joseph
Nicholas
William
Nathan
Matthew
Christopher
Isabella
Sophia
Hannah
Elizabeth
Hannah
Ryan
Tyler
Jonathan
Christopher
Jacob
Alexis
Chloe
Elizabeth
Grace
Natalie
Madison
Jacob
Elizabeth
Mia
Brianna
Nicholas
James
Michael
Emily
Tyler
John
Christopher
Alexander
Ava
Tyler
Tyler
Samantha
Olivia
Hannah
Christopher
Anthony
John
Emma
Joseph
Ryan
Grace
Grace
Nicholas
John
Tyler
Ryan
Andrew
Anthony
Jonathan
Jacob
Nicholas
Michael
Emma
Samantha
Nathan
Chloe
Natalie
Emily
Tyler
Daniel
Alyssa
Samantha
Ashley
Tyler
Joshua
Elizabeth
Andrew
James
Matthew
Michael
Ethan
Ryan
Alexander
Joshua
Emily
Emma
Jonathan
Abigail
Emily
Alyssa
Nicholas
John
Samantha
William
Jonathan
David
Chloe
Daniel
Ethan
Abigail
Abigail
Joseph
Emma
Anthony
Natalie
Michael
Nathan
James
Jacob
Daniel
Christopher
Joseph
Elizabeth
Christopher
Christopher
Emily
Joseph
Samantha
Jacob
Samantha
Alexander
Alyssa
Christopher
Matthew
Alyssa
Ava
Tyler
Nicholas
Matthew
Ashley
Elizabeth
Matthew
Grace
Andrew
Nathan
Andrew
Natalie
William
Ava
Jonathan
Emily
Alexis
Natalie
Emma
Samantha
Brianna
Daniel
Joshua
Daniel
Michael
Jonathan
Joshua
Michael
Natalie
Alyssa
Joseph
Chloe
Grace
Jonathan
Joshua
William
Joseph
James
Alexander
Sophia
Daniel
Jacob
Michael
Anthony
Daniel
Alexis
Emma
Mia
Nathan
Abigail
Michael
Tyler
Natalie
Daniel
William
Daniel
Brianna
Andrew
Ava
Michael
Grace
John
Olivia
Madison
Emma
Christopher
Tyler
Anthony
Jonathan
Hannah
Brianna
Elizabeth
Abigail
William
William
Michael
Chloe
Brianna
6
7
144
```

Output:
```angular2html
===== PRINT NAMES APPEARING AT LEAST 6 TIMES =====
Abigail (10) 5
Alexander (9) 7
Alexis (7) 6
Alyssa (10) 4
Andrew (11) 6
Anthony (7) 6
Ava (6) 2
Brianna (12) 5
Chloe (12) 5
Christopher (16) 9
Daniel (12) 6
David (7) 4
Elizabeth (12) 8
Emily (11) 5
Emma (16) 3
Ethan (7) 5
Grace (13) 5
Hannah (10) 3
Jacob (8) 5
James (12) 5
John (10) 4
Jonathan (15) 6
Joseph (14) 6
Joshua (10) 6
Matthew (14) 6
Mia (14) 3
Michael (17) 7
Natalie (14) 6
Nathan (11) 4
Nicholas (13) 8
Olivia (7) 5
Ryan (8) 4
Samantha (16) 6
Sarah (11) 4
Sophia (10) 6
Tyler (21) 5
William (15) 5
===== FIND NAME =====
Nathan
```