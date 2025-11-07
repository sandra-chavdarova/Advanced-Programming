# [Shapes Application](ShapesTest.java)
Да се дефинира класа ShapesApplication во која се чуваат податоци за повеќе прозорци на кои се исцртуваат геометриски слики во форма на квадрат.

За класата да се дефинира:
- ShapesApplication() - конструктор
- int readCanvases (InputStream inputStream) - метод којшто од влезен поток на податоци ќе прочита информации за повеќе прозорци на кои се исцртуваат квадрати. Во секој ред од потокот е дадена информација за еден прозорец во формат: canvas_id size_1 size_2 size_3 …. size_n, каде што canvas_id е ИД-то на прозорецот, а после него следуваат големините на страните на квадратите што се исцртуваат во прозорецот. Методот треба да врати цел број што означува колку квадрати за сите прозорци се успешно прочитани.
- void printLargestCanvasTo (OutputStream outputStream) - метод којшто на излезен поток ќе го испечати прозорецот чии квадрати имаат најголем периметар. Печатењето да се изврши во форматот canvas_id squares_count total_squares_perimeter.

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

# [Shapes Application 2](ShapesApplicationTest.java)
Да се дефинира класа ShapesApplication чување на податоци за повеќе прозорци на кои и се сцртуваат геометриски слики во различна форма (квадрати и кругови)..

За класата да се дефинира:
- ShapesApplication(double maxArea) - конструктор, каде maxArea е најголемата дозволена плоштина на секоја форма поединечно, која може да биде исцртана на прозорците.
- void readCanvases (InputStream inputStream) - метод којшто од влезен поток на податоци ќе прочита информации за повеќе прозорци на кои се исцртуваат различните геометриски слики. Во секој ред се наоѓа информација за еден прозорец во формат: canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n каде што canvas_id е ИД-то на прозорецот, a после него следуваат информации за секоја форма во прозорецот. Секоја форма е означена со карактер што го означува типот на геометриската слика (S = square, C = circle) и со големината на страната на квадратот, односно радиусот на кругот.
- При додавањето на геометриските слики на прозорецот треба да се спречи креирање и додавање на прозорец во кој има форма што има плоштина поголема од максимално дозволената. Како механизам за спречување треба да се користи исклучок од тип IrregularCanvasException (фрлањето на исклучокот не треба да го попречи вчитувањето на останатите прозорци и геометриски слики. Да се испечати порака Canvas [canvas_id] has a shape with area larger than [max_area].
- void printCanvases (OutputStream os) - метод којшто на излезен поток ќе ги испечати информациите за сите прозорци во апликацијата. Прозорците да се сортирани во опаѓачки редослед според сумата на плоштините на геометриските слики во нив. Секој прозорец да е испечатен во следниот формат: ID total_shapes total_circles total_squares min_area max_area average_area.

За вредноста на PI користете ja константата Math.PI. За постигнување на точност со тест примерите користете double за сите децимални променливи.

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

# [File System](FileSystemTest.java)
Потребно е да се дефинира апликација за едноставен датотечен систем во којшто ќе се чуваат објекти коишто репрезентираат фајлови/датотеки (објекти коишто го имплементираат интерфејсот IFile).

Да се декларира интерфејсот IFile со соодветни методи, така што секој фајл/датотека ќе ги има следните карактеристики:
- може да се пристапи до неговото име (String getFileName())
- може да се добие неговата големина во long (long getFileSize())
- може да се добие String репрезентација на фајлот (String getFileInfo(???))
- може да се сортира датотеката доколку е колекција од датотеки според големините на датотеките кои ги содржи (void sortBySize())
- може да се пресмета големината на најголемата обична датотека во датотеката (findLargestFile ())

Постојат два типа на фајлови: File (обична датотека) и Folder (директориум/фолдер). Потребно е овие две класи да го имплементираат интерфејсот IFile.

За еден File се чуваат информации за неговото име и големина (во long).

Во класата Folder се чуваат исти информации како и за File, a дополнително се чува и листа од фајлови (и обични и директориуми). За оваа класа да се имплементираат методите:
- void addFile (IFile file) - метод за додавање на било каква датотека во листата од датотеки.
- Доколку веќе постои датотека со исто име како името на датотеката што се додава како аргумент на методот, да се фрли исклучок од тип FileNameExistsException во којшто се проследува името кое веќе постои.

И во двете класи да се имплементираат методите коишто се декларирани во интерфејсот IFile. Да се запази на следните фактори:
- големината на еден Folder е сума од големините на сите датотеки (обични или директориуми) коишто се наоѓаат во него.
- при генерирање на String репрезентација на директориумите, датотеките и поддиректориумите во тој директориум да се вовлечени со таб ("\t").
- String репрезентацијата на една обична датотека е File name [името на фајлот со 10 места порамнето на десно] File size: [големината на фајлот со 10 места пораменета на десно ]
- String репрезентацијата на еден директориум е Folder name [името на директориумот со 10 места порамнето на десно] Folder size: [големината на директориумот со 10 места пораменета на десно ]
- возможно е сортирање само во рамки на директориум, каде што сите датотеки во тој директориум потребно е да се сортираат според големина во растечки редослед.
- методот getLargestFile() треба да ја врати големината на најголемата обична датотека во рамки на датотеката каде што е повикан.
- кога се повикува методот sortBySize() кај директориум истиот треба да се повика и за сите негови подиректориуми

Да се дефинира класа FileSystem во која што ќе се чува само еден директориум (rootDirectory). За класата да се имплементираат:
- default конструктор FileSystem()
- void addFile (IFile file) - метод за додавање на било каква датотека во root директориумот.
- long findLargestFile () - метод којшто ја враќа големината на најголемата (обична) датотека во root директориумот.
- void sortBySize() - метод којшто ги сортира датотеките во root директориумот ( и обични и директориуми) според нивната големина во root директориумот во растечки редослед.

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

# [Log System](LoggerTest.java)
Потребно е да се развие систем за процесирање на логови. За секој лог треба да се чува пораката од логот (String), типот на логот (String) и временски печат (long). За таа цел комплетирајте го интерфејсот ILog.

За да се процесираат логовите ќе се користи генеричкиот интерфејс LogProcessor. Овој интерфејс има само еден метод со потпис: ArrayList processLogs (ArrayList logs). Методот добива влезен аргумент логови коишто треба да ги процесира, а враќа резултат процесирани логови. Интерфејсот ви е даден и за истиот треба да ги пополните само генеричките параметри.

Дадена ви е класата LogSystem во којашто се чува листа на логови. За класата да се дефинираат соодветните генерички параметри, да се имплементира конструктор LogSystem(ArrayList elements), како и да се комплетира методот printResults().

Во овој метод потребно е да креирате три конкретни процесори на логови (со помош на ламбда изрази):
1. Процесор којшто ќе ги врати само логовите коишто се од тип INFO
2. Процесор којшто ќе ги врати само логовите чиишто пораки се пократки од 100 карактери
3. Процесор којшто ќе ги врати логовите сортирани според временскиот печат во растечки редослед (од најстар кон најнов лог).

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

# [Min Max](MinMax.java)
Да се имплемнтира генеричка класа MinMax од два споредливи објекти (минимум/максимум). За класата да се имплементираат:
- MinMax()-default конструктор
- void update(T element) - метод за ажурирање на тековните минимум/максимум.
- T max() - го враќа најголемиот елемент
- T min() - го враќа најмалиот елемент
- да се преоптовари методот toString() кој враќа стринг составен од минималниот и максималниот елемент и бројот на елементи обработени во методот update кои се различни од тековниот минимум/максимум, разделени со празно место.
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

# [Canvas](Shapes.java)
Да се имплементира класа Canvas на која ќе чуваат различни форми. За секоја форма се чува:
- id:String
- color:Color (enum дадена)

Притоа сите форми треба да имплментираат два интерфејси:
- Scalable - дефиниран со еден метод void scale(float scaleFactor) за соодветно зголемување/намалување на формата за дадениот фактор
- Stackable - дефиниран со еден метод float weight() кој враќа тежината на формата (се пресметува како плоштина на соодветната форма)

Во класата Canvas да се имплементираат следните методи:
- void add(String id, Color color, float radius) за додавање круг
- void add(String id, Color color, float width, float height) за додавање правоаголник

При додавањето на нова форма, во листата со форми таа треба да се смести на соодветното место според нејзината тежина. Елементите постојано се подредени според тежината во опаѓачки редослед.
- void scale(String id, float scaleFactor) - метод кој ја скалира формата со даденото id за соодветниот scaleFactor. Притоа ако има потреба, треба да се изврши преместување на соодветните форми, за да се задржи подреденоста на елементите.
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

# [Timetable](TimesTest.java)
Да се имплементира класа TimeTable која ќе чита од влезен тек (стандарден влез, датотека, ...) податоци за времиња во 24-часовен формат. Сите времиња се разделени со едно празно место, а во самото време часот и минутите може да бидат разделени со ```:``` или ```.```. Пример за форматот на податоците:

11:15 0.45 23:12 15:29 18.46

Ваша задача е да ги имплементирате методите:
- TimeTable() - default конструктор
- void readTimes(InputStream inputStream) - метод за читање на податоците
- void writeTimes(OutputStream outputStream, TimeFormat format) - метод кој ги печати сите времиња сортирани во растечки редослед во зададениот формат (24 часовен или AM/PM).
- Методот за читање readTimes фрла исклучоци од тип UnsupportedFormatException ако времињата се разделени со нешто друго што не е : или . и InvalidTimeException ако времето (часот или минутите) е надвор од дозволениот опсег (0-23, 0-59). И двата исклучоци во пораката getMessage() треба да го вратат влезниот податок кој го предизвикал исклучокот. Сите времиња до моментот кога ќе се фрли некој од овие два исклучоци треба да си останат вчитани.

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

# [Archive Store (со formatter)](ArchiveStoreTest.java)
# [Archive Store (без formatter)](ArchiveStoreTest2.java)
Да се имплементира класа ArchiveStore во која се чува листа на архиви (елементи за архивирање).

Секој елемент за архивирање Archive има:
- id - цел број
- dateArchived - датум на архивирање.

Постојат два видови на елементи за архивирање, LockedArchive за кој дополнително се чува датум до кој не смее да се отвори dateToOpen и SpecialArchive за кој се чуваат максимален број на дозволени отварања maxOpen. За елементите за архивирање треба да се обезбедат следните методи:
- LockedArchive(int id, Date dateToOpen) - конструктор за заклучена архива
- SpecialArchive(int id, int maxOpen) - конструктор за специјална архива

За класата ArchiveStore да се обезбедат следните методи:
- ArchiveStore() - default конструктор
- void archiveItem(Archive item, Date date) - метод за архивирање елемент item на одреден датум date
- void openItem(int id, Date date) - метод за отварање елемент од архивата со зададен id и одреден датум date. Ако не постои елемент со даденото id треба да се фрли исклучок од тип NonExistingItemException со порака Item with id [id] doesn't exist.
- String getLog() - враќа стринг со сите пораки запишани при архивирањето и отварањето архиви во посебен ред.

За секоја акција на архивирање во текст треба да се додаде следната порака Item [id] archived at [date], додека за секоја акција на отварање архива треба да се додаде Item [id] opened at [date]. При отварање ако се работи за LockedArhive и датумот на отварање е пред датумот кога може да се отвори, да се додаде порака Item [id] cannot be opened before [date]. Ако се работи за SpecialArhive и се обидиеме да ја отвориме повеќе пати од дозволениот број (maxOpen) да се додаде порака Item [id] cannot be opened more than [maxOpen] times.

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
