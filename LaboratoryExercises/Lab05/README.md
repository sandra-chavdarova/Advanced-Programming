# 1. [Chat System](ChatSystemTest.java)
Треба да се развие класа за администрација на чет‐систем(chat). Системот се состои од повеќе чет‐соби, објекти од класата ChatRoom. Во ChatRoom ги чуваме името на собата и имињата на корисниците кои тековно се наоѓаат во таа соба (за корисниците да се користи соодветен Set).
- ChatRoom(String name) ‐ креира нова празна соба за чет (празна значи без корисници). 
- addUser(String username) - го додава корисникот со тоа име во собата. 
- removeUser(String username) - го отстранува корисникот со тоа име од собата доколку има таков, во спротивно не прави ништо. 
- toString():String - враќа стринг кои ги содржи името на собата и сите корисници кои се во собата секој одделен со нов ред. Корисниците се подредени алфабетски. Ако собата е празна се враќа името на собата во еден ред, а во вториот ред стрингот "EMPTY" (наводници само за појаснување). 
- hasUser(String username):boolean - враќа true ако постои корисник со тоа име во собата. 
- numUsers():int - го враќа бројот на корисници во собата. 

Главната  класа ChatSystem  ги  содржи  сите  соби  и  сите  орисници. Корисниците може да се членови на една, повеќе или да не се членови на ниедна соба. За менаџмент на собите треба да ги понудите следните три методи:
- addRoom(String roomName) - додава нова празна соба во листата на соби. 
- removeRoom(String roomName) - ја отстранува собата од листата. 
- getRoom(String roomName):ChatRoom - го враќа објектот кој ја претставува собата со име roomName. Фрлете NoSuchRoomException(roomName) доколку не постои соба со тоа име.
Забелешка: Собите чувајте ги во TreeMap за да бидат секогаш подредени по нивното име.

Дополнително во класата ChatSystem постојат следните методи за работа со корисниците:
- ChatSystem() - default constructor 
- register(String userName) - го регистрира корисникот во системот. Го додава во собата со најмалку корисници. Доколку има повеќе такви соби тогаш го додава во првата соба по лексикоргафско подредување.
- registerAndJoin(String userName, String roomName) - го регистрира корисникот во системот. Дополнително го додава во собата со име roomName. 
- joinRoom(String userName, String roomName) - го  додава  корисникот  во  собата  со соодветно  име  доколку  таа  постои,  во  спротивно  фрла  исклучок  од  типот NoSuchRoomExcеption(roomName). Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).
- leaveRoom(String username, String roomName) - го отстранува корисникот од собата со соодветно  име  доколку  таа  постои.  во  спротивно  фрла  исклучок  од  типот NoSuchRoomExcеption(roomName). Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).
- followFriend(String username, String friend_username) – корисникот со име username го приклучува во сите соби во кој е член корисникот со име friendUsername. Ако не постои регистриран корисник со тоа име се фрла исклучок NoSuchUserException(userName).

Input:
```
1
 addRoom room1
 addRoom room2
 addRoom room3
 registerAndJoin user1 room1
 registerAndJoin user2 room1
 print room1
 register user3
 register user4 room3
 joinRoom user2 room2
 joinRoom user2 room3
 followFriend user4 user2
 print room1
 print room2
 print room3
stop
```

Output:
```
room1
user1
user2


room1
user1
user2
user4


room2
user2
user3
user4


room3
user2
user4
```

# 2. [Anagrams](Anagrams.java)
Да се напише програма која од дадена листа со зборови (секој збор е во нов ред) ќе ги најде групите со пет или повеќе анаграми (анаграм е збор составен од истите букви). Откако ќе ги најде групите треба да се отпечататат на стандарден излез сортирани според азбучен ред и тоа секоја група од анаграми во нов ред, а анаграмите одделени со празно место (внимавајте да нема празно место на крајот од редот). Редоследот на печатење на групите од анаграми е соодветен на редоследот на зборовите кои дошле на влез како први преставници на соодветната група од анаграми.

Input:
```
acres
alerting
alerts
altering
alters
ambled
angered
antlers
antrels
arced
arrest
arse
artiest
artiste
arts
attires
attunes
bedlam
beldam
blamed
cadre
canter
capers
cared
cares
caret
carets
caster
cater
cedar
centra
claimed
crate
crates
dales
deals
decimal
declaim
deist
deltas
derange
desalt
destain
detains
diets
drapes
drawer
earns
ears
east
eats
edits
emits
enlist
enraged
eras
esprit
ester
estral
estrange
filters
fliters
gapers
gasper
grandee
grantees
grapes
greatens
grenade
inlets
instead
integral
items
lades
lambed
lapse
lased
lasted
laster
laves
leads
leaps
leapt
least
lepta
lifters
listen
mates
meats
medalic
medical
merit
merits
mister
miter
miters
mites
mitre
mitres
nares
nears
nectar
negaters
notes
nutates
onset
opts
pacers
pagers
pales
palest
parers
pares
parleys
parse
parsec
parsed
parser
parses
parsley
parts
passer
paste
pastel
pates
peals
pears
persist
petal
petals
plate
plates
players
pleas
pleat
pleats
pores
poser
post
pots
priest
priests
prose
raced
races
rapers
rapes
rarest
rasped
rasper
raster
raters
rats
react
reacts
reagents
reaps
recant
recaps
recast
recta
redraw
reins
relating
remit
remits
rentals
replays
reset
resin
retests
reward
rinse
ripest
risen
ropes
sainted
salted
saltern
salve
saner
sate
scare
scrape
sear
seat
sepal
septa
sera
serac
sergeant
setters
silent
siren
skate
slate
slated
slave
smite
smiter
snare
spacer
spader
spare
spared
sparely
sparer
spares
sparge
sparse
spate
spear
spears
spore
spot
sprat
spread
spriest
sprite
sprites
stained
stake
stale
staler
staple
star
starer
steak
steal
steam
steer
steno
sternal
stied
stifler
stone
stop
strap
streets
striate
stripe
stripes
takes
tales
tames
tapes
tarps
tars
tastier
tautens
teaks
teals
teams
teas
terse
tersest
tesla
testers
tetanus
tides
timer
timers
times
tinsel
tones
tops
trace
traces
trance
traps
trees
triangle
trifles
tsar
unstate
vales
valse
veals
warder
warred
```

Output:
```
acres cares races scare serac
alerting altering integral relating triangle
alerts alters estral laster staler
ambled bedlam beldam blamed lambed
angered derange enraged grandee grenade
antlers antrels rentals saltern sternal
arced cadre cared cedar raced
arrest rarest raster raters starer
arse ears eras sear sera
artiest artiste attires striate tastier
arts rats star tars tsar
attunes nutates tautens tetanus unstate
canter centra nectar recant trance
capers pacers parsec recaps scrape spacer
caret cater crate react recta trace
carets caster crates reacts recast traces
claimed decimal declaim medalic medical
dales deals lades lased leads
deist diets edits stied tides
deltas desalt lasted salted slated
destain detains instead sainted stained
drapes parsed rasped spader spared spread
drawer redraw reward warder warred
earns nares nears saner snare
east eats sate seat teas
emits items mites smite times
enlist inlets listen silent tinsel
esprit priest ripest sprite stripe
ester reset steer terse trees
estrange grantees greatens negaters reagents sergeant
filters fliters lifters stifler trifles
gapers gasper grapes pagers sparge
lapse leaps pales peals pleas sepal
laves salve slave vales valse veals
leapt lepta petal plate pleat
least slate stale steal tales teals tesla
mates meats steam tames teams
merit miter mitre remit timer
merits mister miters mitres remits smiter timers
notes onset steno stone tones
opts post pots spot stop tops
palest pastel petals plates pleats staple
parers parser rapers rasper sparer
pares parse pears rapes reaps spare spear
parleys parsley players replays sparely
parses passer spares sparse spears
parts sprat strap tarps traps
paste pates septa spate tapes
persist priests spriest sprites stripes
pores poser prose ropes spore
reins resin rinse risen siren
retests setters streets tersest testers
skate stake steak takes teaks
```


# 3. [Library](LibraryTester.java)
Да се имплементира класа LibrarySystem која ќе менаџира со резервации на книги во една библиотека. Класата треба да соддржи членови и книги.

Класата Book се состои од:
- идентификациски број - isbn
- наслов
- година на издавање
- и други атрибути потребни за успешно следење на копиите и издавањата.

Класата Member се состои од:
- идентификациски број - id
- име
- и други атрибути потребни за изнајмување на книги.

Методи за класата LibrarySystem
- Конструктор: LibrarySystem(String name)
- void registerMember(String id, String fullName) – регистрира член во останатите членови со тоа што секој член на почеток нема ниту една позајмена книга.
- addBook(String isbn, String title, String author, int year) – додава книга во библиотека, така што една книга може да има повеќе примероци.
  - Ако веќе постои книга со ист ISBN, тогаш бројот на примероци се зголемува за 1.
  - Ако не постои - се додава со 1 примерок.
- void borrowBook(String memberId, String isbn) – членот сака да позајми книга.
  Правила:
  - Ако книгата не постои, се игнорира акцијата.
  - Ако книгата постои, но нема слободни примероци, членот се става во листа на чекање за таа книга.
  - Ако има слободен примерок: му се доделува на членот и бројот на слободни примероци се намалува.
- void returnBook(String memberId, String isbn) – кога член ќе врати книга:
  - Бројот на слободни примероци се зголемува.
  - Ако постои листа на чекање за таа книга – на првиот член од листата автоматски му се доделува позајмица од книгата (исто како borrowBook).
- void printMembers() – да се испечатат сите членови сортирани според број на позајмени книги (опаѓачки) па ако е исто, по името на членот (растечки).
<br>Пример за еден ред:
<br>Gorazd (id27) - borrowed now: 5, total borrows: 17
- void printBooks() – да се испечатат сите книги сортирани според број на позајмувања досега (опаѓачки), па ако е исто по година на издавање (растечки).
<br>Пример за еден ред:
<br>isbn1 - “The Hobbit” by Goch (2025), available: 199, total borrows: 2
- void printBookCurrentBorrowers(String isbn) – да се испечатат моменталните ID броеви на изнајмувачи на книгата со тој ISBN, сортирани и одделени со запирка.
- void printTopAuthors() – да се испечатат авторите сортирани според број на позајмувања на нивните книги (опаѓачки), па ако е исто по име (растечки).
<br>Пример за еден ред:
<br>Goch - 127

Input:
```
CityLibrary
registerMember M1 Ana
registerMember M2 Bojan
addBook ISBN1 Hobbit Tolkien 1937
addBook ISBN1 Hobbit Tolkien 1937
borrowBook M1 ISBN1
borrowBook M2 ISBN1
printMembers
printBookCurrentBorrowers ISBN1
END
```

Output:
```
Ana (M1) - borrowed now: 1, total borrows: 1
Bojan (M2) - borrowed now: 1, total borrows: 1
M1, M2
```
