## Создание тест-сьютов
1. По пути src\test\java\Tests\Pages создать класс Page{название страницы} наследуемый BaseSeleniumPage. В классе прописать локаторы и методы для взаимодействия со страницей. Создать конструктор: 
```
public PageFavourites(String item) {
        this.item = item;
        PageFactory.initElements(driver, this);
    }
```
2. В файле src\test\java\Settings\Assistant\Resourses\conf.properties добавить url страницы
3. Создать по пути src\test\java\Settings\SubCore создать абстрактный класс Base{Название тест-сьюта}. В классе прописать protected объекты страниц, которые необходимы для тестов и @Before public void setUpTest, где прописать инициализации необходимых страниц и предусловия для кейсов.
4. По пути src\test\java\Settings\TestValues создать TestValues{Название тест-сьюта} со значениями для сравнения
5. По пути src\test\java\Tests\Classes создать класс TestP{Название тест-сьюта} наследуемый Base{Название тест-сьюта} в 3 пункте
6. Создать в файле
```
@Rule
public RuleResultTests ruleResultTests = new RuleResultTests(driver);
```
7. Прописать тесты
8. Profit!
