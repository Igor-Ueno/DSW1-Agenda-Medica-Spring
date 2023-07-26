# DSW1 - Agenda Médica Spring

## Diretórios com arquivos Java

- config: algumas configurações que não entendi muito bem.
- controller: lógica de acesso ao banco de dados (regras de negócios).
- conversor: converte o ID de String para Long.
- dao: herdado da superclasse "CrudRepository".
- domain: classes com as especificações do banco de dados sobre os atributos, além dos métodos get e set.
- security: ainda tenho que ver o que faz.
- service
    - impl: implementação da classe de serviço usando os métodos da classe dao.
    - spec: interface do serviço.
- validation: verifica se a chave (CPF ou CMR) é único.
- aplicação (para preencher o banco de dados): salva dados no banco de dados por meio de uma classe em vez de um arquivo .sql.

## Diretórios com arquivos HTML

- admin: menus de CRUD.
- consulta: listagem e cadastro.
- medico: listagem e cadastro.
- paciente: listagem e cadastro.
- fragments: adaptar os arquivos da livraria.
- Outros arquivos dentro de "templates": também adaptar.

### Outros arquivos

- Arquivos .properties.
- Diretório "static"
    - Arquivo CSS.
    - Imagens.
