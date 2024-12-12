package br.com.eduardo.steps;

import entities.Filme;
import entities.NotaAluguel;
import entities.TipoAluguel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import services.AluguelService;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel nota;
    private String erro;
    private TipoAluguel tipoAluguel =  TipoAluguel.COMUM;
    private Integer pontuacao;

    @Given("um filme com estoque de {int} unidades")
    public void um_filme_com_estoque_de_unidades(Integer int1) {
        filme = new Filme();
        filme.setEstoque(int1);
    }

    @Given("que o preço de aluguel seja R$ {int}")
    public void que_o_preço_de_aluguel_seja_r$(Integer int1) {
        filme.setAluguel(int1);
    }

    @Given("um filme")
    public void um_filme(io.cucumber.datatable.DataTable table) {
        Map<String, String> map = table.asMap(String.class, String.class);
        filme = new Filme();
        filme.setEstoque(Integer.parseInt(map.get("estoque")));
        filme.setAluguel(Integer.parseInt(map.get("preco")));
    }

    @When("alugar")
    public void alugar() {
        try {
            nota = aluguel.alugar(filme, tipoAluguel);
        } catch(RuntimeException e) {
            erro =  e.getMessage();
        }
    }

    @Then("o preço do aluguel será R$ {int}")
    public void o_preço_do_aluguel_será_r$(Integer int1) {
        Assert.assertEquals(int1, nota.getPreco());
    }

    @Then("o estoque do filme será {int} unidade")
    public void o_estoque_do_filme_será_unidade(Integer int1) {
        Assert.assertEquals(int1, filme.getEstoque());
    }

    @Then("não será possível por falta de estoque")
    public void não_será_possível_por_falta_de_estoque() {
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @Given("que o tipo de aluguel seja (.*)$")
    public void que_o_tipo_de_aluguel_seja_extendido(String tipo) {
        tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL : tipo.equals("extendido") ? TipoAluguel.EXTENDIDO : TipoAluguel.COMUM ;
    }

    @Then("a data de entrega será em {int} dia(s)")
    public void a_data_de_entrega_será_em_dias(Integer int1) {
        Date dataEsperada = DateUtils.obterDataComDiferencaDias(int1);
        Date dataReal = nota.getDataEntrega();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));
    }

    @Then("a pontuação será de {int} ponto(s)")
    public void a_pontuação_será_de_pontos(Integer int1) {
        Assert.assertEquals(int1, nota.getPontuacao());
    }

}
