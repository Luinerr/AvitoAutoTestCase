package Settings.Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
    /**
     * Драйвер WebDriver
     */
    protected static WebDriver driver;
    /**
     * Ожидание для поиска элемента на странице
     */
    protected static WebDriverWait driverWaitForElement;
    /**
     * Ожидание между тестами для не получения бана
     */
    protected static WebDriverWait driverWaitBeforeTest;

    /**
     * Из урл берет конец строки, где указан id продукта и записывает в item
     * Пока не реализовано, тк аннотация @FindBy не принимает параметры
     */
    protected static String item;
    /**
     * Время для записи логов и скриншотов
     */
    protected static String timeStamp;
    /**
     * Паттерн для времени, который переходит для создания скринов,
     * папок и скриншотов
     */
    protected static String pattern;
    /**
     * Файл для создания директории, куда складываются все скриншоты и логи от теста
     */
    protected static File directory;

    protected static File fileLog;

    protected static String nameTest;

    protected static String statusTest;

    /**
     * Инициализация настроек браузера и окружения для тестов
     * Надо добавить название класс?
     */
    @BeforeClass
    public static void setUp() {
        //Создание папки со временем, когда были запущен тесты
        pattern = "yyyy_MM_dd___HH_mm_ss";
        timeStamp = new SimpleDateFormat(pattern).format((new Date()));
        directory = new File("src/test/log/" + timeStamp);
        directory.mkdir();

        //Создание файла логов
        fileLog = new File(directory.getAbsolutePath() + "/logs.txt");
        try {
            fileLog.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //инициализация драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //инициализация веб-драйвера для задержки элемента
        driverWaitForElement = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Задаем время опроса для обхода бана
        driverWaitForElement.pollingEvery(Duration.ofSeconds(2));
        //Инициализация веб-драйвера для задержки перед тестом
        driverWaitBeforeTest = new WebDriverWait(driver, Duration.ofSeconds(4));

        /*
        Настройка окружения.
        Максимально большое окно браузера
        Время ожидания загрузки страницы - 30 секунд.
        Время неявного ожидания элемента - 3 секуды
        Передача драйверов в BaseSeleniumPage
         */
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        BaseSeleniumPage.setDriver(driver, driverWaitForElement);
    }
    /**
     * Метод для анализа и вывода логов в консоль
     * Перебросить в ScreenShotRule
     */
    public static void analyzeLog(Boolean a) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        try {
            FileWriter writerUp = new FileWriter(fileLog, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writerUp);
            bufferedWriter.write("Starting test:" + nameTest);
            bufferedWriter.write(System.getProperty("line.separator"));
            if (a) {
                System.out.println("StatusTest: passed");
                bufferedWriter.write("StatusTest: passed");
                bufferedWriter.write(System.getProperty("line.separator"));
            } else {
                System.out.println("StatusTest: failed");
                bufferedWriter.write("StatusTest: failed");
                bufferedWriter.write(System.getProperty("line.separator"));
            }
            bufferedWriter.close();
            writerUp.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (LogEntry entry : logEntries) {
            String log = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            try {
                FileWriter writer = new FileWriter(fileLog, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(log);
                bufferedWriter.write(System.getProperty("line.separator"));
                bufferedWriter.close();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Завершение тестов
     */
    @AfterClass
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    /**
     * Парсер урла. Пока бесполезен.
     *
     * @param url
     * @return item
     */
    public String parseStringItemFromUrl(String url) {
        String itemProduct = url.substring(url.lastIndexOf('_') + 1);
        return itemProduct;
    }

    /**
     * Возвратить копию номера продукта. Пока не работает.
     *
     * @return
     */
    public static String getItem() {
        return new String(item);
    }

    public static void setNameTest(String name) {
        nameTest = name;
    }

    public static void setStatusTest(String status) {
        statusTest = status;
    }
}
