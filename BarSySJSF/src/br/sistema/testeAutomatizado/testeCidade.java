package br.sistema.testeAutomatizado;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class testeCidade {
	private WebDriver driver;
	@Before
	public void inicia() {
		System.setProperty("webdriver.gecko.driver", "C:/teste/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void testeSucesso(){
		WebElement nomeCidadeSucesso = driver.findElement(By.name("formEditar:nome"));
		
	}

}
