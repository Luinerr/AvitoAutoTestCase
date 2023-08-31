# Тестовое задание Avito
## Решение
1. Тестовые артефакты для тестирования функциональности добавления в избранное находятся в папке TestArt https://github.com/Luinerr/AvitoAutoTestCase/tree/main/Test
2. Запуск автотестов с помощью библиотеки Selenium описан ниже

## Инструкция по запуску автотестов
### **Требования**
1. Для запуска проекта требуется: 

  - JDK не ниже 8 версии
  - Apache-maven версии 3.x.x
  - Google Chrome версии 115-116 

&nbsp; Также необходимо наличие прописанных путей для переменных JDK и Maven.

&nbsp; Проверить версии:
```
java -version
mvn -version
```
2. Склонировать репозиторий:
```
git clone https://github.com/Luinerr/AvitoAutoTestCase.git
```
3. В папке проекта запустить тесты:
```
mvn test
```
4. Отчеты по пройденым тестам храняться в папке src\test\log\yyyy_MM_dd___HH_mm_ss (логи и скриншоты в случае проваленных тестов)
5. В случае экстренных завершений во время тестов стоит проверить наличие chromedriver в менеджере задач:
```
tasklist
```
&nbsp; При наличии chromedriver:
```
Taskkill /IM chromedriver /F
```
