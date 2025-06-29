# Diplom_3
Для RegistrationTest.java:
Убрал параметризацию @ParameterizedTest и @ValueSource
Добавил поле browser с получением из системных свойств
Теперь используется системное свойство browser как в других тестах
Для запуска в разных браузерах теперь нужно использовать системное свойство при запуске тестов: 
mvn test -Dbrowser=yandex
mvn test -Dbrowser=chrome
