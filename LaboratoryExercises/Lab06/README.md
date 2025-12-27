# 1. [University](UniversityTest.java)
Дадени ви се три класи: Course, Department и University.
Класите Course и Department се целосно имплементирани.
Вашата задача е да ги имплементирате сите методи во University користејќи само Java Streams (без циклуси).

- Course
  - Претставува универзитетски курс/предмет.
  - Полиња:
    - String code – код на курс
    - String name – име на курс
    - int credits – кредити
    - int difficulty – тежина (1–10)
    - int enrolledStudents – број на студенти
  - Содржи само getters.
- Department
  - Претставува департман/факултет.
  - Полиња:
    - String name – име на департман
    - List<Course> courses – курсеви во овој департман
  - Содржи само getters.
- University
  - Претставува универзитет составен од повеќе департмани.
  - Полиња:
    - List<Department> departments
    - Студентите треба да ги имплементираат сите методи користејќи Streams.

Методи за имплементација (со опис)
1. getAllCourseNames()
  - Врати листа од имињата на сите курсеви.
2. getCoursesWithMinCredits(int minCredits)
  - Врати ги курсевите со credits ≥ minCredits.
3. getTotalStudentCount()
  - Врати го вкупниот број студенти запишани во сите курсеви.
4. getHardestCourse()
  - Врати го курсот со највисока тежина.
5. groupByDifficulty()
  - Групирај ги сите курсеви по нивната тежина.
6. getCourseEnrollmentMap()
  - Врати мапа: код на курс → број студенти.
7. getAverageEnrollmentPerCourse()
  - Врати ја просечната бројка на студенти по курс.
8. getSortedCourseCodes()
  - Врати ги кодовите на сите курсеви, сортирани азбучно.
9. getDepartmentToCourseNames()
  - Врати мапа: име на департман → имиња на курсеви.
10. getAllCourses()
  - Врати една заедничка листа од сите курсеви.
11. getMostPopularDepartment()
  - Врати го департманот со најмногу студенти вкупно.
12. getStudentsByDifficulty()
  - Врати мапа: тежина → вкупно студенти.
13. getCoursesByDifficultyRange(int min, int max)
  - Врати курсеви со тежина меѓу min и max (вклучително).
14. getPopularCourseCodes(int minStudents)
  - Врати ги кодовите на курсевите со најмалку minStudents.
15. getTotalCreditsPerDepartment()
  - Врати мапа: департман → вкупни кредити.
16. getTop3HardestCourses()
  - Врати ги трите најтешки курсеви.
17. getAverageDifficultyPerDepartment()
  - Врати мапа: департман → просечна тежина.
18. getEnrollmentStatistics()
  - Врати IntSummaryStatistics за бројот на студенти (min, max, average, sum, count).
19. mergeFourSmallestDepartments()
  - Најди ги четирите департмани со најмалку студенти.
  -  Спои ги со reduce.
  -  Новото име треба да биде "DeptA & DeptB & DeptC & DeptD".
  -  Спои ги нивните курсеви.
  -  Врати нов University со ажурирана листа.

Input:
```
getAllCourseNames
```
Output:
```
Testing method: getAllCourseNames
Structural Programming
Algorithms
Object-Oriented Programming
Advanced Programming
Calculus I
Linear Algebra
Statistics
Calculus II
Mechanics
Electromagnetism
Quantum Physics
Optics
Organic Chemistry
Inorganic Chemistry
Physical Chemistry
Analytical Chemistry
Cell Biology
Genetics
Ecology
Biochemistry
Microeconomics
Macroeconomics
Finance
Accounting
English Language
German Language
French Language
Italian Language
Ancient History
Medieval History
Modern History
Contemporary History
```
