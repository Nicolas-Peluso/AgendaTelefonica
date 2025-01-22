Agenda telefonica pede Dados de n contatos, aqui eu aprendi sobre relacionamento entre classes 1 -> 1 e 1 -> *.
criei um cache para armazenar o ultimo contato pesquisado assim nao preciso ficar fazendo loops caso a pesquisa seja a mesma,
em um caso real de uso uma agenda pode ter n contatos entao um loop por 100 contatos é melhor que um loop por n, os contatos tambem devem ser armazenados por 
ordem alfabetica e nao por entrada assim fica mais facil de pesquisar.

Adicionei Um banco de dados Realacional, MySql. Vou criar um crud onde sera possivel manipular os contatos
esse é meu primeiro projeto com banco de dados (usei com o django o ORM mas acho que base é melhor que abstração), o foco aqui é treinar,
Quero aprender Sql E mySql antes do ir pro spring, a conexão com o banco é feita com o JDBC, como estou usando o maven nao preciso baixar o drive.
só settei a dependencia no pom.xml.