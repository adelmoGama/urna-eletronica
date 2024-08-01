# urna-eletronica
# API Urna Eletrônica

Este projeto é uma API para gerenciar uma urna eletrônica, desenvolvida utilizando Spring Boot. A API permite realizar operações como registrar eleitores e candidatos, iniciar e encerrar sessões de votação, computar votos, e gerar relatórios com os resultados de cada sessão.

## Requisitos

1. **Controle de Votos**: Cada eleitor pode votar apenas uma vez.
2. **Validação de Duplicatas**: Não são permitidos candidatos ou eleitores duplicados.
3. **Restrição de Exclusão**: Candidatos e eleitores só podem ser excluídos se não tiverem votos computados.
4. **Sessão de Votação**: A votação só pode ocorrer quando a sessão estiver aberta, e a sessão não pode ser encerrada se não estiver aberta.
5. **Encerramento Imediato**: Uma sessão pode ser iniciada e encerrada sem tempo de espera entre elas, desde que esteja aberta.
6. **Anonimidade dos Votos**: A sessão só pode ser encerrada com 0 ou pelo menos 2 votos. Se houver apenas um voto, ele será desconsiderado para manter a anonimidade.
7. **Boletim de Urna**: Deve ser possível gerar um relatório com o resultado de cada sessão.
8. **Regra de Anonimidade no Boletim**: Se houver apenas um votante, o voto não será contabilizado e não haverá vencedor.
9. **Relatório de Sessões Encerradas**: O boletim de urna só pode ser gerado para sessões encerradas.
10. **Layout do Boletim**: O boletim de urna deve ter um layout específico com 40 colunas por linha.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Web**: Framework com suporte para criação de API REST.
- **Spring Data JPA**: Integração com JPA para acesso a dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar as informações.
- **Lombok**: Biblioteca para reduzir o boilerplate do código Java.
- **JasperReports**: Ferramenta para geração de relatórios.

## Como Executar

1. **Colnando o repositório**:
    git clone https://github.com/adelmoGama/urna-eletronica.git
2. **Coonfigurando o banco de dados**: Atualize as configurações de conexão com o banco de dados no arquivo `application.properties`.
3. **Compilando e executando o projeto**:
    mvn clean install
    mvn spring-boot:run
4. **Endpoints disponíveis**: Após iniciar a aplicação, você pode acessar os endpoints para gerenciar eleitores, candidatos, sessões e gerar relatórios.

## Melhorias

Contribuições e sugestões de melhorias são bem-vindas! Por favor, siga as diretrizes padrão de GitHub para fork, clone, criar branches e pull requests. E para qualquer necessidade estou a disposição!
