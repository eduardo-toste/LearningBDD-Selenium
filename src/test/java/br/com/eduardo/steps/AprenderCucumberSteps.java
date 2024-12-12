package br.com.eduardo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AprenderCucumberSteps {

    /*/
    Inicio do Cenario 1
     */
    @Given("que criei o arquivo corretamente")
    public void que_criei_o_arquivo_corretamente() {

    }

    @When("executar")
    public void executar() {

    }

    @Then("a especificação deve finalizar com sucesso")
    public void a_especificação_deve_finalizar_com_sucesso() {

    }

    /*/
    Fim do Cenario 1
     */

    /*/
    Inicio do Cenario 2
     */

    private Integer contador = 0;
    @Given("que o valor do contador é {int}")
    public void que_o_valor_do_contador_é(Integer int1) {
        contador = int1;
    }

    @When("eu incrementar em {int}")
    public void eu_incrementar_em(Integer int1) {
        contador = contador + int1;
    }

    @Then("o valor do contador será {int}")
    public void o_valor_do_contador_será(Integer int1) {
        Assert.assertEquals(int1, contador);
    }

    /*/
    Fim do Cenario 2
     */

    /*/
    Inicio do Cenario 3
     */

    Date entrega = new Date();
    @Given("que a entrega é dia {int}\\/{int}\\/{int}")
    public void que_a_entrega_é_dia(Integer dia, Integer mes, Integer ano) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dia);
        cal.set(Calendar.MONTH, mes - 1);
        cal.set(Calendar.YEAR, ano);
        entrega = cal.getTime();
    }

    @When("a entrega atrasar em {int} {word}")
    public void a_entrega_atrasar_em_dias(Integer quantidade, String unidade) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(entrega);

        switch (unidade.toLowerCase()) {
            case "dias":
                cal.add(Calendar.DAY_OF_MONTH, quantidade);
                break;
            case "meses":
                cal.add(Calendar.MONTH, quantidade);
                break;
            case "anos":
                cal.add(Calendar.YEAR, quantidade);
                break;
            default:
                throw new IllegalArgumentException("Unidade de tempo não suportada: " + unidade);
        }
        entrega = cal.getTime();
    }

    @Then("a entrega será efetuada em {int}\\/{int}\\/{int}")
    public void a_entrega_será_efetuada_em(Integer dia, Integer mes, Integer ano) {
        // Constrói a data recebida no formato "dd/MM/yyyy"
        String dataRecebida = String.format("%02d/%02d/%04d", dia, mes, ano);

        // Formata a data do sistema ou outra fonte para o mesmo formato
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = format.format(entrega);

        // Verifica se as datas são iguais
        Assert.assertEquals(dataRecebida, dataFormatada);
    }

    /*/
    Fim do Cenario 3
     */

    /*/
    Inicio do Cenario 4
    */

    @Given("que o ticket( especial)? é (A.\\d{3})$")
    public void que_o_ticket_é(String tipo, String int1) {
    }

    @Given("que o valor da passagem é R$ {double}")
    public void que_o_valor_da_passagem_é_r$(Double double1) {
    }

    @Given("^que o nome do passageiro é \"(.{5,20})\"$")
    public void que_o_nome_do_passageiro_é(String nome) {
    }

    @Given("que o telefone do passageiro é (9\\d{3}-\\d{4})$")
    public void que_o_telefone_do_passageiro_é(String telefone) {
    }

    @When("criar os steps")
    public void criar_os_steps() {
    }

    @Then("o teste vai funcionar")
    public void o_teste_vai_funcionar() {
    }

    /*/
    Fim do Cenario 4
     */
}