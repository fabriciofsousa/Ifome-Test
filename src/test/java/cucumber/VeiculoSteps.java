package cucumber;

import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.util.ValidaVeiculoUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.mockito.internal.matchers.Equals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.*;

public class VeiculoSteps {

    private RequestSpecification request;
    private ValidatableResponse response;
    private static final String BASE_URL = "http://localhost:8080";
    private Veiculo veiculo;

    @Given("que o endpoint {string} é do tipo {string}")
    public void queOEndpointEDoTipo(String endpoint, String tipo) {
        RestAssured.baseURI = BASE_URL.concat(endpoint);
        request = RestAssured.given();

    }

    @When("eu faço uma requisição para salvar um novo veículo com o Renavan {string} , Data Modelo {string} e Placa {string}")
    public void euFacoUmaRequisicaoParaSalvarUmNovoVeiculoComORenavanDataModeloEPlaca(String renavan, String dataModelo, String placa) {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setDataModelo(stringToCalendar(dataModelo));
        veiculo.setPlaca(placa);
        veiculo.setRenavam(renavan);
        response = request.body(asJsonString(veiculo)).contentType(ContentType.JSON).post().then();
    }

    @And("as informações são válidas")
    public void asInformacoesSaoValidas() {
        ValidaVeiculoUtil.validacoesVeiculo(veiculo);
    }

    @Then("o veículo deve ser cadastrado com sucesso, e a API deve retornar o código {int}. Código: {string}")
    public void oVeiculoDeveSerCadastradoComSucessoEAAPIDeveRetornarOCodigoCodigo(int statusCode, String codigo) {
        response.assertThat().statusCode(equalTo(statusCode));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Calendar stringToCalendar(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = sdf.parse(data);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("que existem veículos cadastrados no sistema")
    public void queExistemVeiculosCadastradosNoSistema() {
        var response1 = request.body(asJsonString(new Veiculo(100L, "23015920860", stringToCalendar("13/12/2029"), "ZZZ9999"))).contentType(ContentType.JSON).post().then();
        //response1.assertThat().body(notNullValue());
        var response2 = request.body(asJsonString(new Veiculo(101L, "10316895668", stringToCalendar("13/12/2029"), "ZZZ9999"))).contentType(ContentType.JSON).post().then();
        //response2.assertThat().body(notNullValue());
    }

    @When("eu faço uma requisição para listar todos os veículos")
    public void euFacoUmaRequisicaoParaListarTodosOsVeiculos() {
        response = request.get().then();
    }

    @Then("a API deve retornar a lista de veículos cadastrados")
    public void aAPIDeveRetornarAListaDeVeiculosCadastrados() {
        response.assertThat().body(notNullValue());
    }

    @When("eu faço uma requisição para buscar todos veiculos")
    public void euFacoUmaRequisicaoParaBuscarTodosVeiculos() {
        response = request.get().then();
        response.extract().body();
    }

    @Then("a API deve retornar o veículo com o id extraido")
    public void aAPIDeveRetornarOVeiculoComOID() throws JsonProcessingException {
        var veiculoResponse = getVeiculoFromJsonRsponse(response.extract().response().asString());
        response = request.basePath(String.valueOf(veiculoResponse.getId())).get().then();
        response.assertThat().body(notNullValue());
    }

    private Veiculo getVeiculoFromJsonRsponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Converter a string JSON para um array de objetos JSON
        com.fasterxml.jackson.databind.JsonNode jsonNode = objectMapper.readTree(json);

        // Obter o primeiro objeto JSON do array
        com.fasterxml.jackson.databind.JsonNode primeiroObjeto = jsonNode.get(0);

        // Converter o objeto JSON para um objeto Veiculo
        return objectMapper.treeToValue(primeiroObjeto, Veiculo.class);
    }

    @And("Atualizar o veiculo pelo ID")
    public void atualizarOVeiculoPeloID() throws JsonProcessingException {
        var responseId = response.extract().response().as(Veiculo.class);
        veiculo = new Veiculo();
        veiculo.setId(responseId.getId());
        veiculo.setDataModelo(responseId.getDataModelo());
        veiculo.setPlaca("ABC12345");
        veiculo.setRenavam("55980515456");
        response = request.body(asJsonString(veiculo)).contentType(ContentType.JSON).basePath(String.valueOf(veiculo.getId())).put().then();
        response.assertThat().body(notNullValue());
    }

    @And("Deletar o veiculo pelo ID")
    public void deletarOVeiculoPeloID() {
        String responseId = String.valueOf(response.extract().response().as(Veiculo.class).getId());
        response = request.basePath(responseId).delete().then();
        response.assertThat().statusCode(204);
    }
}
