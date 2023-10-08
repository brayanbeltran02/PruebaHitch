package org.example;

import com.codeup.jdvc.ConexionDB;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Selenium {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.ktronix.com/computadores-tablet/computadores-portatiles/c/BI_104_KTRON");

        WebElement producto1 = driver.findElement(By.className("product__item"));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        wait.until(d -> producto1.isDisplayed());

        List<WebElement> productos = driver.findElements(By.className("product__item"));

        ConexionDB conexionDB = new ConexionDB();

        for(WebElement producto: productos ){
            WebElement nombre = producto.findElement(By.className("js-algolia-product-title"));
            WebElement precio = producto.findElement(By.className("price"));
            String codigo = nombre.getAttribute("data-id");
            String link = nombre.getAttribute("data-url");

            System.out.println(nombre.getText()+precio.getText()+codigo+link);
            Producto producto2 = new Producto();
            producto2.codigo = codigo;
            producto2.nombre = nombre.getText();
            producto2.precio = precio.getText();
            producto2.url = link;
            conexionDB.insertar(producto2);
        }






        driver.quit();
    }
}
