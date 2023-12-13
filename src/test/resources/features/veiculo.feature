Feature: CRUD de veículo

  Scenario Outline: Criar um veículo com sucesso
    Given que o endpoint <endpoint> é do tipo <tipo>
    When eu faço uma requisição para salvar um novo veículo com o Renavan <renavan> , Data Modelo <dataModelo> e Placa <placa>
    And as informações são válidas
    Then o veículo deve ser cadastrado com sucesso, e a API deve retornar o código 201. Código: <codigo>
    Examples:
      | endpoint           | tipo       | renavan       | dataModelo   | placa     | codigo |
      | "/api/v1/veiculos" |   "POST"   | "07481819115" | "11/12/2025" | "ZZZ9999" | "<201>"  |


  Scenario Outline: Listar todos os veículos
    Given que o endpoint <endpoint> é do tipo <tipo>
    And que existem veículos cadastrados no sistema
    When eu faço uma requisição para listar todos os veículos
    Then a API deve retornar a lista de veículos cadastrados
    Examples:
      | endpoint           | tipo |
      | "/api/v1/veiculos" | "GET"  |


  Scenario Outline: Buscar veículo por ID
    Given que o endpoint <endpoint> é do tipo <tipo>
    When eu faço uma requisição para buscar todos veiculos
    Then a API deve retornar o veículo com o id extraido
    Examples:
      | endpoint           | tipo   |
      | "/api/v1/veiculos" | "GET"  |



  Scenario Outline: Update veículo por ID
    Given que o endpoint <endpoint> é do tipo <tipo>
    When eu faço uma requisição para buscar todos veiculos
    Then a API deve retornar o veículo com o id extraido
    And Atualizar o veiculo pelo ID
    Examples:
      | endpoint           | tipo  |
      | "/api/v1/veiculos" | "PUT"  |

  Scenario Outline: Remover veículo por ID
    Given que o endpoint <endpoint> é do tipo <tipo>
    When eu faço uma requisição para buscar todos veiculos
    Then a API deve retornar o veículo com o id extraido
    And Deletar o veiculo pelo ID
    Examples:
      | endpoint           | tipo  |
      | "/api/v1/veiculos" | "PUT"  |
