# Movie Theater
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