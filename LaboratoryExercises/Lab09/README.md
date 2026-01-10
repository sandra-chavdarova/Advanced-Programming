# 1. [Document Viewer](DocumentViewerTest.java)
Да се имплементира систем за преглед на документи. Потребно е да имплементирате класа DocumentViewer со следните методи:
- DocumentViewer() – конструктор
- addDocument(String id, String text) – метод за додавање на нов документ со ИД id и содржина text. Документите во себе содржат повеќе редови текст (одделени со \n)
- enableLineNumbers(String id) – метод за нумерирање на секоја линија во документот со реден број пред линијата (пр. 1. на почеток на првата линија, 2. на почеток на втората линија итн.)
- enableWordCount(String id) – методот за додавање на нов ред во документот со содржина "Words: W", каде што W е бројот на зборови во цел документ
- enableRedaction(String id, List<String> forbiddenWords) – методот кој ќе ги редактира (замени со *) сите зборови од листата forbiddenWords во документот
- display(String id) – метод кој ќе го испечати документот на стандарден излез

Input:
```angular2html
2
5
3
Hello world
This is a secret document
Do not share
7
4
Decorator pattern is powerful
It hides complexity
But improves design
Use wisely
enableLineNumbers 5
display 5
enableLineNumbers 7
display 7
exit
```

Output:
```
=== Document 5 ===
1: Hello world
2: This is a secret document
3: Do not share
=== Document 7 ===
1: Decorator pattern is powerful
2: It hides complexity
3: But improves design
4: Use wisely
```

# 2. [XML](XMLTest.java)
XML форматот е еден од најупотребуваните формати за полуструктурирани податоци. Потребно е со помош на Composite шаблонот за развој на софтвер да дефинирате класи што ќе овозможат претставување на едноставни и посложени XML елементи.

Еден XML елемент е претставен на следниот начин:
```angular2html
<tag attribute1="value1" attribute2="value2", ...> value </tag>
```
пример:
```angular2html
<student type="redoven" smer="KNI"> Trajce Trajkov </student>
```
Но XML може да има и посложена структура, односно наместо вредност (value) да содржи други XML елементи. Тие XML (под)елементи може да содржат други XML елементи итн. Пример:

```angular2html
<student type="redoven">
    <name>
        <first-name>Trajce</first-name>
        <last-name>Trajkov</last-name>
    </name>
</student>
```
За таа цел потребно е да дефинирате интерфејст XMLComponent и од истиот да креирате две класи XMLLeaf и XMLComposite.

Да се дополни главната класа според барањето во коментарите.

Input:
```
1
```

Output:
```angular2html
<student type="redoven" program="KNI">Trajce Trajkovski</student>
```

# 3. [Mailing List](MailingListTest.java)
Да се имплементира систем за мејлинг листи кој овозможува корисниците да се претплатуваат на одредена мејлинг листа и автоматски да добиваат e-mail пораки секогаш кога ќе биде објавен нов пост на таа листа.

Системот треба да овозможи централизирано управување со мејлинг листите, при што секоја листа одржува сопствена колекција на претплатени корисници и е одговорна за испраќање известувања при објавување на нови содржини. Корисниците не треба директно да проверуваат дали има нови постови, туку известувањето треба да се случува автоматски.

Потребно е да се имплементира интерфејс User кој содржи метод:
- void notify(String mailingListName, String text)

Овој метод се повикува секогаш кога ќе биде објавен нов пост на мејлинг листата на која корисникот е претплатен.

Може да има повеќе видови на корисници:
- MailingListUser - ги прима сите пораки од мејлинг листата
- FilteredMailingListUser - ги прима сите пораки кои содржат одреден keyword
- AdminUser - ги чита сите пораки но ги третира како администраторски логови

Дополнително, потребно е да дефинирате интерфејс MailingList кој ги содржи методите:
- void subscribe(User user) – додава корисник во листата на претплатници
- void unsubscribe(User user) – го отстранува корисникот од листата
- void publish(String text) – објавува нов текст и ги известува сите претплатени корисници

Input:
```
8
CREATE_LIST FINKI
ADD_USER FINKI NORMAL Ana ana@finki.mk
ADD_USER FINKI FILTERED Bojan bojan@finki.mk exam
ADD_USER FINKI ADMIN Admin admin@finki.mk
PUBLISH FINKI New lab exercises are published
PUBLISH FINKI Exam schedule is available
REMOVE_USER FINKI ana@finki.mk
PUBLISH FINKI Final exam info
```

Output:
```
[USER] Ana received email from FINKI: New lab exercises are published
[ADMIN LOG] MailingList=FINKI | Message=New lab exercises are published
[USER] Ana received email from FINKI: Exam schedule is available
[FILTERED USER] Bojan received filtered email from FINKI: Exam schedule is available
[ADMIN LOG] MailingList=FINKI | Message=Exam schedule is available
[FILTERED USER] Bojan received filtered email from FINKI: Final exam info
[ADMIN LOG] MailingList=FINKI | Message=Final exam info
```
