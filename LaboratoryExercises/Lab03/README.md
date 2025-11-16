# 1. Movie Theater
Имплементирајте систем за кино - MovieTheater. Киното ќе води листа од филмови кои ќе може да се сортираат по наслов, година и рејтинзи.

Класа Movie
- title: String - наслов
- genre: String - жанр
- year: int - година на издавање
- avgRating: double - процечна оцена
- Да се имплементира toString() кој ќе го печати филмот во следниот формат:
Наслов, Жанр, Година, Оцена

Класа MovieTheater
- movies: ArrayList<Movie> - листа на филмови во Киното

Методи:
- readMovies(InputStream is) - метод за читање од InputStream кој ќе ги додава филмовите директно во листата со BufferedReader
- printByGenreAndTitle() - ги прикажува филмовите сортирани според жанр, па според наслов
- printByYearAndTitle() - ги прикажува филмовите сортирани според година, па според наслов
- printByRatingAndTitle() - ги прикажува филмовите сортирани според оцена, па според наслов

Input:
```angular2html
18
Interstellar
Sci-Fi
2014
10 10 9 9 10 9 10 10 9 10 9 9 10 9 10 10 9
Following
Thriller
1998
7 8 7 8 7 8 7 7 8 8 7 8 8 7 8 7 8
Memento
Mystery
2000
9 9 10 8 9 9 10 9 9 8 10 9 9 10 8 9 9
Insomnia
Thriller
2002
8 8 9 7 8 8 9 8 7 8 9 8 7 8 8 8 9
Batman Begins
Action
2005
9 9 8 9 8 9 9 8 9 9 8 9 9 8 9 8 9
The Prestige
Mystery
2006
9 9 10 8 9 9 9 8 10 9 9 10 8 9 9 8 9
The Dark Knight
Action
2008
10 10 9 10 9 9 10 10 9 9 10 9 10 9 9 10 9
Inception
Sci-Fi
2010
10 10 9 9 10 9 10 9 10 10 9 9 9 10 10 9 9
The Dark Knight Rises
Action
2012
9 9 8 9 9 8 9 8 8 9 8 9 9 8 8 9 9
Dunkirk
War
2017
8 9 9 9 8 9 8 9 9 9 8 8 9 9 9 8 9
Tenet
Sci-Fi
2020
8 8 9 8 7 8 8 8 9 7 8 9 8 9 7 8 8
Oppenheimer
Biography
2023
10 10 9 10 9 10 9 9 10 10 9 10 10 9 10 9 10
The Batman
Action
2022
8 9 8 8 9 8 9 8 8 9 9 8 8 9 9 8 8
Man of Steel
Action
2013
7 8 7 8 7 7 8 7 7 8 8 7 8 8 7 8 8
Transcendence
Sci-Fi
2014
6 7 7 6 7 7 6 7 7 7 6 6 7 7 7 6 7
Inception: The Cobol Job
Animation
2010
8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
Doodlebug
Short
1997
7 6 7 7 6 7 7 6 7 7 6 7 7 6 7 7 6
Quay
Documentary
2015
6 7 6 6 7 6 6 6 7 6 6 7 7 6 7 6 6
```

Output:
```angular2html
SORTING BY RATING
Oppenheimer, Biography, 2023, 9.59
Interstellar, Sci-Fi, 2014, 9.53
Inception, Sci-Fi, 2010, 9.47
The Dark Knight, Action, 2008, 9.47
Memento, Mystery, 2000, 9.06
The Prestige, Mystery, 2006, 8.94
Batman Begins, Action, 2005, 8.65
Dunkirk, War, 2017, 8.65
The Dark Knight Rises, Action, 2012, 8.59
The Batman, Action, 2022, 8.41
Insomnia, Thriller, 2002, 8.06
Tenet, Sci-Fi, 2020, 8.06
Inception: The Cobol Job, Animation, 2010, 8.00
Following, Thriller, 1998, 7.53
Man of Steel, Action, 2013, 7.53
Doodlebug, Short, 1997, 6.65
Transcendence, Sci-Fi, 2014, 6.65
Quay, Documentary, 2015, 6.35

SORTING BY GENRE
Batman Begins, Action, 2005, 8.65
Man of Steel, Action, 2013, 7.53
The Batman, Action, 2022, 8.41
The Dark Knight, Action, 2008, 9.47
The Dark Knight Rises, Action, 2012, 8.59
Inception: The Cobol Job, Animation, 2010, 8.00
Oppenheimer, Biography, 2023, 9.59
Quay, Documentary, 2015, 6.35
Memento, Mystery, 2000, 9.06
The Prestige, Mystery, 2006, 8.94
Inception, Sci-Fi, 2010, 9.47
Interstellar, Sci-Fi, 2014, 9.53
Tenet, Sci-Fi, 2020, 8.06
Transcendence, Sci-Fi, 2014, 6.65
Doodlebug, Short, 1997, 6.65
Following, Thriller, 1998, 7.53
Insomnia, Thriller, 2002, 8.06
Dunkirk, War, 2017, 8.65

SORTING BY YEAR
Doodlebug, Short, 1997, 6.65
Following, Thriller, 1998, 7.53
Memento, Mystery, 2000, 9.06
Insomnia, Thriller, 2002, 8.06
Batman Begins, Action, 2005, 8.65
The Prestige, Mystery, 2006, 8.94
The Dark Knight, Action, 2008, 9.47
Inception, Sci-Fi, 2010, 9.47
Inception: The Cobol Job, Animation, 2010, 8.00
The Dark Knight Rises, Action, 2012, 8.59
Man of Steel, Action, 2013, 7.53
Interstellar, Sci-Fi, 2014, 9.53
Transcendence, Sci-Fi, 2014, 6.65
Quay, Documentary, 2015, 6.35
Dunkirk, War, 2017, 8.65
Tenet, Sci-Fi, 2020, 8.06
The Batman, Action, 2022, 8.41
Oppenheimer, Biography, 2023, 9.59
```

# 2. Ad Network
Да се имплементира симулација на мрежа за прикажување реклами (Ad Network), која ги чита податоците за рекламите и за едно барање за прикажување реклама, а потоа ги избира најрелевантните реклами според комбинација од неколку фактори.

Да се имплементира класа Ad која ќе претставува една реклама. Класата треба да ги содржи следните атрибути:
- id:String – идентификатор на рекламата
- category:String – категорија (на пример: “tech”, “sports”, “food”)
- bidValue:double – понуда за прикажување на рекламата (во долари)
- ctr:double – просечна стапка на кликнување (Click-Through Rate)
- content:String – текстуална содржина (реченица)

Класата треба да има:
- toString() кој ќе го прикажува објектот во следниот формат:
ID CATEGORY (bid=…, ctr=…%) CONTENT

Да имплементира интерфејсот Comparable така што „природниот редослед“ ќе биде по bidValue во опаѓачки редослед, а доколку bidValue е ист, според id во растечки редослед.

Да се имплементира класа AdRequest која ќе претставува барање за прикажување реклама.
Класата треба да ги содржи следните атрибути:
- id:String – идентификатор на барањето
- category:String – категорија на барањето
- floorBid:double – минимална дозволена понуда за прикажување реклама
- keywords:String – клучни зборови поврзани со барањето (разделени со празно место)

Класата треба да има:
- toString() кој ќе го прикажува објектот во следниот формат:
ID [CATEGORY] (floor=…): KEYWORDS

Да се имплементира класа AdNetwork која ќе ја претставува мрежата на реклами и ќе управува со нивното прикажување.
Класата треба да има атрибут:
- ads:АrrayList<Ad> – листа со сите реклами
И следните методи:
- void readAds(InputStream in) - метод кој чита реклами од влезен тек користејќи BufferedReader. За секој ред се креира објект од класата Ad и се додава во листата ads. Секој ред е во следниот формат:
ID CATEGORY BID_VALUE CTR CONTENT
пример: AD001 tech 2.5 0.12 Amazing new phone
- List placeAds(InputStream inputStream, int k, OutputStream outputStream) - метод кој:

  1. Чита едно барање за реклама (AdRequest) од дадениот влезен тек во формат:
     ID CATEGORY FLOOR_BID KEYWORD1 KEYWORD2 KEYWORD3…
     пример: AR001 tech 2.0 technology phone application inches
  2. Ги исклучува сите реклами кои имаат bidValue помал од floorBid во барањето за реклама
  3. За секоја реклама ја пресметува вкупната вредност (score) според следната формула:
     totalScore = relevanceScore(ad, request) + x * bidValue + y * ctr
     каде: relevanceScore(ad, request) е функција која дава поени според категорија и клучни зборови (оваа функција е веќе дадена и не треба да се менува),
     x = 5.0 и y = 100.0 се константи со кои се нагласува влијанието на bid и CTR.
  4. Рекламите се подредуваат според totalScore во опаѓачки редослед, се земаат топ k реклами, а потоа се подредуваат според „природниот“ редослед на класата.
  5. Резултатите се печатат со PrintWriter во дадениот излезен тек во следниот формат:
     Top ads for request AR001:
     AD003 tech (bid=3.00, ctr=9.00%) Powerful gaming laptop
     AD001 tech (bid=2.50, ctr=12.00%) Amazing new phone
     …

Input:
```angular2html
0
AD001 tech 2.5 0.12 Amazing new phone
AD002 tech 1.5 0.10 Cheap used phone
AD003 sports 3.2 0.05 Great running shoes
AD004 tech 2.8 0.09 Laptop for professionals

AR001 tech 2.0 technology phone
```

Output:
```angular2html
Top ads for request AR001:
AD001 tech (bid=2.50, ctr=12.00%) Amazing new phone
```