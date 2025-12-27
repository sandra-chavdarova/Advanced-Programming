# 1. [MP3 Player - Pattern](PatternTest.java)
Да се напише класа за MP3Player во која се чуваат листа со песни (List<Song>) и песната која моментално се слуша (е на ред да се пушти). MP3Player-от има четири копчиња Play, Stop, FWD и REW.

- Ако се притисне копчето Play се пушта моменталната песна (на екранот се испишува "Song i is playing", каде i е редниот број на моменталната песна, почнувајќи од 0).
- Ако се притисне копчето Stop:
  - моменталната песна кој е пуштена се паузира (на екран се испишува "Song i is paused" каде i е моменталната песна која била пуштена).
  - листата целосно се ресетира од почеток, ако моменталната песна веќе била паузирана (на екран се испишува "Songs are stopped").
- Ако се притисне копчето FWD песната се паузира и следната песна од листата станува моментална (да се земе во предвид кружното повторување на песните).
- Ако се притисне копчето REW песната се паузира и претходната песна од листата станува моментална (да се земе во предвид кружното повторување на песните).

За секоја песна (Song) се чуваат насловот на песната (String) и изведувачот на песната (String).

Output:
```
MP3Player{currentSong = 0, songList = [Song{title=first-title, artist=first-artist}, Song{title=second-title, artist=second-artist}, Song{title=third-title, artist=third-artist}, Song{title=fourth-title, artist=fourth-artist}, Song{title=fifth-title, artist=fifth-artist}]}
First test
Song 0 is playing
Song{title=first-title, artist=first-artist}
Song is already playing
Song{title=first-title, artist=first-artist}
Song is already playing
Song{title=first-title, artist=first-artist}
Song 0 is paused
Song{title=first-title, artist=first-artist}
Song 0 is playing
Song{title=first-title, artist=first-artist}
Forward...
Song{title=second-title, artist=second-artist}
Song 1 is playing
Song{title=second-title, artist=second-artist}
Reward...
Song{title=first-title, artist=first-artist}
MP3Player{currentSong = 0, songList = [Song{title=first-title, artist=first-artist}, Song{title=second-title, artist=second-artist}, Song{title=third-title, artist=third-artist}, Song{title=fourth-title, artist=fourth-artist}, Song{title=fifth-title, artist=fifth-artist}]}
Second test
Songs are stopped
Song{title=first-title, artist=first-artist}
Songs are already stopped
Song{title=first-title, artist=first-artist}
Songs are already stopped
Song{title=first-title, artist=first-artist}
Song 0 is playing
Song{title=first-title, artist=first-artist}
Song 0 is paused
Song{title=first-title, artist=first-artist}
Forward...
Song{title=second-title, artist=second-artist}
Songs are stopped
Song{title=first-title, artist=first-artist}
Reward...
Song{title=fifth-title, artist=fifth-artist}
MP3Player{currentSong = 4, songList = [Song{title=first-title, artist=first-artist}, Song{title=second-title, artist=second-artist}, Song{title=third-title, artist=third-artist}, Song{title=fourth-title, artist=fourth-artist}, Song{title=fifth-title, artist=fifth-artist}]}
Third test
Forward...
Song{title=first-title, artist=first-artist}
Forward...
Song{title=second-title, artist=second-artist}
Forward...
Song{title=third-title, artist=third-artist}
Song 2 is playing
Song{title=third-title, artist=third-artist}
Forward...
Song{title=fourth-title, artist=fourth-artist}
Songs are stopped
Song{title=first-title, artist=first-artist}
Forward...
Song{title=second-title, artist=second-artist}
Reward...
Song{title=first-title, artist=first-artist}
MP3Player{currentSong = 0, songList = [Song{title=first-title, artist=first-artist}, Song{title=second-title, artist=second-artist}, Song{title=third-title, artist=third-artist}, Song{title=fourth-title, artist=fourth-artist}, Song{title=fifth-title, artist=fifth-artist}]}
```

# 2. [Refactor Code](TriviaGame.java)
Рефакторирајте го кодот:
```
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class TriviaQuestion {

    public static final int TRUEFALSE = 0;
    public static final int FREEFORM = 1;
    public String question;		// Actual question
    public String answer;		// Answer to question
    public int value;			// Point value of question
    public int type;			// Question type, TRUEFALSE or FREEFORM

    public TriviaQuestion() {
        question = "";
        answer = "";
        value = 0;
        type = FREEFORM;
    }

    public TriviaQuestion(String q, String a, int v, int t) {
        question = q;
        answer = a;
        value = v;
        type = t;
    }
}

class TriviaData {

    private ArrayList<TriviaQuestion> data;

    public TriviaData() {
        data = new ArrayList<TriviaQuestion>();
    }

    public void addQuestion(String q, String a, int v, int t) {
        TriviaQuestion question = new TriviaQuestion(q, a, v, t);
        data.add(question);
    }

    public void showQuestion(int index) {
        TriviaQuestion q = data.get(index);
        System.out.println("Question " + (index + 1) + ".  " + q.value + " points.");
        if (q.type == TriviaQuestion.TRUEFALSE) {
            System.out.println(q.question);
            System.out.println("Enter 'T' for true or 'F' for false.");
        } else if (q.type == TriviaQuestion.FREEFORM) {
            System.out.println(q.question);
        }
    }

    public int numQuestions() {
        return data.size();
    }

    public TriviaQuestion getQuestion(int index) {
        return data.get(index);
    }
}

public class TriviaGame {

    public TriviaData questions;	// Questions

    public TriviaGame() {
        // Load questions
        questions = new TriviaData();
        questions.addQuestion("The possession of more than two sets of chromosomes is termed?",
                "polyploidy", 3, TriviaQuestion.FREEFORM);
        questions.addQuestion("Erling Kagge skiied into the north pole alone on January 7, 1993.",
                "F", 1, TriviaQuestion.TRUEFALSE);
        questions.addQuestion("1997 British band that produced 'Tub Thumper'",
                "Chumbawumba", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("I am the geometric figure most like a lost parrot",
                "polygon", 2, TriviaQuestion.FREEFORM);
        questions.addQuestion("Generics were introducted to Java starting at version 5.0.",
                "T", 1, TriviaQuestion.TRUEFALSE);
    }
    // Main game loop

    public static void main(String[] args) {
        int score = 0;			// Overall score
        int questionNum = 0;	// Which question we're asking
        TriviaGame game = new TriviaGame();
        Scanner keyboard = new Scanner(System.in);
        // Ask a question as long as we haven't asked them all
        while (questionNum < game.questions.numQuestions()) {
            // Show question
            game.questions.showQuestion(questionNum);
            // Get answer
            String answer = keyboard.nextLine();
            // Validate answer
            TriviaQuestion q = game.questions.getQuestion(questionNum);
            if (q.type == TriviaQuestion.TRUEFALSE) {
                if (answer.charAt(0) == q.answer.charAt(0)) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            } else if (q.type == TriviaQuestion.FREEFORM) {
                if (answer.toLowerCase().equals(q.answer.toLowerCase())) {
                    System.out.println("That is correct!  You get " + q.value + " points.");
                    score += q.value;
                } else {
                    System.out.println("Wrong, the correct answer is " + q.answer);
                }
            }
            System.out.println("Your score is " + score);
            questionNum++;
        }
        System.out.println("Game over!  Thanks for playing!");
    }
}
```

# 3. [Game Room System - Thread-safe](GameRoomSystem.java)
Потребно е да се имплементира систем за управување со повеќе игрички соби во кој играчите можат да се приклучат, да нападаат и да ја напуштат играта.

Системот менаџира со:
- Играчи (Players)
- Акции на играчи (Player Actions)
- Игрички соби (Game Rooms)
- Централен сервер (Game Server)

Дадена ви е класата Player која содржи податоци за:
- id: String - единствен идентификатор на играчот
- score: int - тековен резултат на играчот

**Да се имплементира синхронизираниот метод addScore(int delta) кој го зголемува резултатот на играчот за дадената вредност. Методот мора да биде синхронизиран за да се обезбеди thread-safe пристап до резултатот.**

Дефиниран е и enum ActionType со следните вредности:
- JOIN_GAME - акција за приклучување во игра
- LEAVE_GAME - акција за напуштање на игра
- ATTACK - акција за напад

Класата PlayerAction содржи податоци за:
- playerId: String - идентификатор на играчот
- action: ActionType - типот на акција

Имплементиран е и методот getProcessingTime() кој враќа време за процесирање на акцијата во милисекунди:
- JOIN_GAME: 20ms
- LEAVE_GAME: 30ms
- ATTACK: 5ms
- Default: 0ms

Класата RoomAction е помошна класа која содржи:
- roomId: String - идентификатор на собата
- action: PlayerAction - акцијата што треба да се изврши

Класата GameRoom претставува една игричка соба и содржи:
- roomId: String - единствен идентификатор на собата
- players: Map<String, Player> - мапа на играчи во собата (thread-safe)
- actionQueue: BlockingQueue<PlayerAction> - редица за чекање на акции
- executor: ExecutorService - single-thread executor за процесирање
- running: boolean - флаг дали собата е активна

**Да се имплементира методот startProcessor() кој стартува посебен thread за процесирање на акциите. Овој thread:**
**- Континуирано чита акции од редицата**
**- Ги процесира додека собата е активна или додека има акции во редицата**
**- Користи poll() со timeout од 100ms за да не блокира бесконечно**

Методот submitAction(PlayerAction action:
- Ја печати примената акција
- Ја додава акцијата во редицата за процесирање

Методот processAction(PlayerAction action) кој:
- Прво чека соодветно време според типот на акција (simulira processing time)
- Потоа ја извршува акцијата:
  - JOIN_GAME: Го додава играчот во мапата (ако не постои) и печати порака
  - LEAVE_GAME: Го отстранува играчот од мапата и печати порака (или печати дека играчот не е во собата)
  - ATTACK: Го зголемува резултатот на играчот за 10 поени (или печати дека играчот не е во собата)

**Да се имплементира методот shutdown() кој:**
**- Го поставува флагот running на false**
**- Го исклучува executor-от (со grace period од 5 секунди)**
**- Ги печати финалните резултати на сите играчи во собата**

Класата GameServer е централниот сервер кој менаџира со повеќе соби и содржи:
- inputQueue: BlockingQueue<RoomAction> - главна редица за сите акции
- rooms: ConcurrentHashMap<String, GameRoom> - мапа на активни соби
- dispatcher: ExecutorService - single-thread executor за диспечирање
- running: boolean - флаг дали серверот е активен

**Да се имплементира методот startDispatcher() кој стартува dispatcher thread што:**
**- Континуирано чита акции од главната редица**
**- Ја наоѓа соодветната соба (или ја креира ако не постои)**
**- Ја проследува акцијата до собата**

Методот submit(String roomId, PlayerAction action) кој ја додава акцијата во главната редица обвиткана во RoomAction.

**Да се имплементира методот shutdown() кој:**
**- Го исклучува dispatcher-от**
**- Ги исклучува сите активни игрички соби**

<br><br>
_Важни напомени:_
- Сите операции со споделени податоци мора да бидат thread-safe
- Користете ConcurrentHashMap за мапи што се пристапуваат од повеќе threads
- Користете BlockingQueue за комуникација меѓу threads
- Секоја соба има свој посебен thread за процесирање на акции (FIFO редослед)
- Dispatcher thread-от ги распределува акциите до соодветните соби
- При исклучување, обезбедете graceful shutdown со чекање на сите активни акции
- Поради недетерминистичката природа на тестовите, неуспешното извршување на поединечен тест не претставува индикација за погрешна имплементација на решението.

Input:
```
room1,p1,JOIN_GAME
room1,p1,ATTACK
room1,p1,LEAVE_GAME
```
Output:
```
[room1] RECEIVED: PlayerAction{playerId='p1', action=JOIN_GAME}
[room1] RECEIVED: PlayerAction{playerId='p1', action=ATTACK}
[room1] RECEIVED: PlayerAction{playerId='p1', action=LEAVE_GAME}
[room1] JOIN: p1
[room1] ATTACK: Player{id='p1', score=10}
[room1] LEAVE: p1
[room1] FINAL PLAYERS:
Game server stopped.
```
