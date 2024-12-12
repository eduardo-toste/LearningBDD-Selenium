package br.com.eduardo.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class CadastroContasSteps {

    private WebDriver driver;

    @Given("que estou acessando a aplicação")
    public void que_estou_acessando_a_aplicação() {
        System.setProperty("webdriver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
    }
    @When("informo o usuário {string}")
    public void informo_o_usuário(String arg1) {
        driver.findElement(By.id("email")).sendKeys(arg1);
    }
    @When("a senha {string}")
    public void a_senha(String arg1) {
        driver.findElement(By.id("senha")).sendKeys(arg1);
    }
    @When("seleciono entrar")
    public void seleciono_entrar() {
        driver.findElement(By.tagName("button")).click();
    }
    @Then("visualizo a página inicial")
    public void visualizo_a_página_inicial() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Bem vindo, Rodrigo Garro!", texto);
    }
    @When("seleciono Contas")
    public void seleciono_contas() {
        driver.findElement(By.linkText("Contas")).click();
    }
    @When("seleciono Adicionar")
    public void seleciono_adicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }
    @When("informo a conta {string}")
    public void informo_a_conta(String string) {
        driver.findElement(By.id("nome")).sendKeys(string);
    }
    @When("seleciono Salvar")
    public void seleciono_salvar() {
        driver.findElement(By.tagName("Button")).click();
    }
    @Then("a conta é inserida com sucesso")
    public void a_conta_é_inserida_com_sucesso() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }
    @Then("sou notificado que o nome da conta é obrigatório")
    public void sou_notificado_que_o_nome_da_conta_é_obrigatório() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Informe o nome da conta", texto);
    }
    @Then("sou notificado que já existe uma conta com esse nome")
    public void sou_notificado_que_já_existe_uma_conta_com_esse_nome() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", texto);
    }
    @Then("recebo a mensagem {string}")
    public void recebo_a_mensagem(String string) {
        String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(string, texto);
    }

    @After(order = 1, value = "@funcionais")
    public void screenshot(Scenario cenario){
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/screenshots/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After(order = 0, value = "@funcionais")
    public void fecharBrowser(){
        driver.quit();
    }

}
