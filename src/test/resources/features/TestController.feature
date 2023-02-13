# language: pt

Funcionalidade: Testando End-Points do Controller

  Esquema do Cenário: Cadastro de usuario CPF valido
    Dado Quando tenho um usuario com nome <nome>, CPF <cpf>, com endereço <rua>, <numero>, <bairro>, <cidade>, <estado>
    Quando Faco um requisicao de cadastro
    Entao Devo receber o status <status>
    Exemplos:
      | nome             | cpf              |  rua           | numero | bairro     | cidade                    | estado |status |
      | "Teste Cucumber" | "682.796.930-41" | "Rua Alemanha" | 117    | "Sucupira" | "Jaboatão dos Guararapes" | "PE"   |"201"  |