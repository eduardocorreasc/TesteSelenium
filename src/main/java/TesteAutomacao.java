import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAutomacao {
	private WebDriver driver;
	private DSL DSL;

	
	public void start(){
		System.setProperty("webdriver.chromedriver.driver", System.getProperty("user.dir") + "TesteAutomacao\\src\\test\\resourceschromedriver.exe");
		driver = new ChromeDriver();
		DSL = new DSL(driver);
		driver.manage().window().maximize();
		driver.get("https://blogdoagi.com.br/");
	}
	
	//Teste feito para procurar na busca a palavra "Teste", validar que foram encontrados resultados para essa busca, abrir um link e garantir que ele foi aberto.
	@Test
	public void TesteEncontraBusca() throws InterruptedException {
		start();
		DSL.Pesquisa("Teste");
		Thread.sleep(3000);
		//Validação se o texto "Resultados encontrados para: Teste" foi encontrado
		assertEquals(DSL.obterValorCampo("//*[@id=\"primary\"]/section/h1"), "Resultados encontrados para: Teste");
		driver.findElement(By.xpath("//*[@id=\"post-3622\"]/div/div[1]/div/a/img")).click();
		//Garante que foi aberto um link validando o titulo da página
		assertEquals(driver.getTitle(), "Nossos avanços em testes de transferências interbancárias via Drex. Veja mais detalhes! - Blog do Agi");
		driver.quit();
	}

	//Teste feito para procurar na busca a palavra "loreipsum" e validar que não foi encontrado nenhum resultado nessa busca e a mensagem de erro está correta
	@Test
	public void TesteNaoEncontraBusca() throws InterruptedException {
		start();
		DSL.Pesquisa("loreipsum");
		Thread.sleep(3000);
		assertEquals(DSL.obterValorCampo("//*[@id=\"main\"]/section/div/p"), "Lamentamos, mas nada foi encontrado para sua pesquisa, tente novamente com outras palavras.");
		//Validação que foi apresentada a mensagem = Lamentamos, mas nada foi encontrado para sua pesquisa, tente novamente com outras palavras.
		//Assim confirmando que não foi encontrado nada na busca
		driver.quit();
	}
	
	//Teste feito para fazer uma pesquisa e logo em seguida utilizar a pesquisa novamente para validar se ira abrir corretamente a pesquisa
		@Test
		public void ValidaPesquisaAposUmaBusca() throws InterruptedException {
			start();
			DSL.Pesquisa("Teste");
			Thread.sleep(3000);
			//Realiza uma pesquisa
			assertEquals(DSL.obterValorCampo("//*[@id=\"primary\"]/section/h1"), "Resultados encontrados para: Teste");
			//Realiza uma nova pesquisa após a primeira
			driver.findElement(By.xpath("//*[@id=\"ast-desktop-header\"]/div[1]/div/div/div/div[3]/div[2]/div/div/a/span[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"search-field\"]")).clear();
			Thread.sleep(3000);
			DSL.Pesquisa("Financeiro");
			Thread.sleep(3000);
			//Valida que a segunda pesquisa foi feita corretamente
			assertEquals(DSL.obterValorCampo("//*[@id=\"primary\"]/section/h1"), "Resultados encontrados para: Financeiro");
			driver.quit();
		}

	
	
}
