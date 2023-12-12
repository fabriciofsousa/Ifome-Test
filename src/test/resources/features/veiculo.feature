Feature: CRUD de veículo

  Scenario Outline: Criar um veículo com sucesso
    Given que o endpoint <endpoint> é do tipo <tipo>
    When eu faço uma requisição para salvar um novo veículo com o Renavan <renavan> , Data Modelo <dataModelo> e Placa <placa>
    And as informações são válidas
    Then o veículo deve ser cadastrado com sucesso, e a API deve retornar o código 201. Código: <codigo>
    Examples:
      | endpoint           | tipo   | renavan       | dataModelo   | placa     | codigo |
      | "/api/v1/veiculos" | "POST" | "37759184770" | "11/12/2025" | "ZZZ9999" | "201"  |