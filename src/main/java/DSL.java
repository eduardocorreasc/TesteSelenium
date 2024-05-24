import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
public class DSL {
	
	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Pesquisa(String texto){	
		driver.findElement(By.xpath("//*[@id=\"ast-desktop-header\"]/div[1]/div/div/div/div[3]/div[2]/div/div/a/span[2]")).click();
		driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys(texto);
		driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys(Keys.ENTER);
		}
	
	public String obterValorCampo(String id_campo){
		return driver.findElement(By.xpath(id_campo)).getText();
	}
}
