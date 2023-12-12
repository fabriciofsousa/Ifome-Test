package cucumber;

import br.com.ada.ifome.model.Veiculo;
import br.com.ada.ifome.util.ValidaVeiculoUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;

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
        response.assertThat().statusCode(statusCode).body("codigo", equalTo(codigo));
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
}
