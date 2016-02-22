# PatternGames

### Exercício 1 de Padrões de Projetos - 2015.2


1 - Requisitos
--------------

A Pattern Games é uma loja de compra, venda e locação de jogos e necessita de um sistema para controlar os aluguéis de seus jogos. Para iniciar o uso do sistema, será necessário um cadastro simples de clientes (nome, cpf e email), o usuário do sistema (atendente) não irá necessitar de login e senha. O sistema deve validar se o CPF digitado e o email são válidos.
Uma locação pode estar contido em dois cenários:

*   Locação comum (Dia da semana).
    
    _Preço_: 3 reais
    
    _Duração_: 1 dia
  
  
*   Locação especial (Fim de semana). 

    _Preço_: 5 reais
  
    _Duração_: 2 dias
  
  
As operações que devem estar disponíveis no sistema são:

#### Alugar um jogo

Muda o estado do jogo para ’Alugado’ e o funcionário pode então entregar o jogo para o cliente. O sistema deve verificar o dia em que a locação está sendo feita para determinar o cenário (comum ou especial).

#### Devolver um jogo

Ao término do período de locação, o cliente devolve o jogo e o sistema verifica se há pendências como multa e juros. Em caso de locação comum, multa = R$1.00 + R$3.00 por dia além da data de devolução, em caso de locação especial, multa = R$ 3.00 + R$3.00 por dia além da data de devolução;


#### Observar um jogo


O sistema vai associar um cliente a um jogo para que este observe suas alterações (especificamente a devolução). Isso é útil para que o cliente saiba quando o jogo está disponível, por exemplo. Qualquer alteração no jogo deve ser informado para o cliente através de email (que será solicitado no cadastro).
Formato da mensagem: ”Caro Sr. Nome, o jogo <NOME DO JOGO>está disponível para locação! Corra agora para a Pattern Games para garantir sua jogatina!


Um jogo pode ter 2 estados:

* Alugado

Se tentar alugar de novo, lança uma exceção informando a próxima data em que ele estará disponível (1 dia após a data fim do aluguel); Se devolver, retorna o jogo ao estado ’disponível’;

* Disponível

No caso de alugar, altera o estado para ’alugado’; No caso de devolver, lança exceção informando que o jogo já está disponível;


2 - DETALHES DE IMPLEMENTAÇÃO
-----------------------------

*   Para facilitar a validação do CPF, uma vez que não é o foco do sistema, utilize a biblioteca Caelum Stella, disponível em: stella.caelum.com.br/

*   Para enviar o email, utilize-se da API JavaMail (Oficial da Oracle). Além disso, alguns frameworks podem ajudar na utilização do JavaMail (EmailBuilder (https://github.com/jduv/EmailBuilder, por exemplo)

*   Fique a vontade para adicionar qualquer framework que desejar!

*   O cliente do sistema pode ser feito para desktop ou web, fique a vontade para escolher.

*   O sistema pode utilizar qualquer mecanismo para persistências de dados (Arquivo Texto, XML, Bancos de Dados Relacionais ou OO)


3 - AVALIAÇÃO
-------------

> Este exercício deve ser entregue até o dia 08/03/2016 com o código-fonte e um documento simples explicando quais padrões foram utilizados, onde e a motivação de ter utilizado. Caso não utilize Maven ou Gradle, o documento deve informar qual IDE foi utilizada para construir o aplicativo (apenas para facilitar a avaliação). Caso se utilize de banco de dados, inclua no pacote o script para criação das tabelas.

> Não se sinta pressionado a utilizar os padrões vistos na disciplina, pesquisa outros padrões dentro desta categoria e aplique-os se possível. O principal objetivo desse mini-projeto é utilizar o maior nu ́mero de padrões comportamentais possíveis de forma justificável. Pense em formas de maximizar a flexibilidade do código, mas procure não forçar a utilização de padrões só por causa do escopo da disciplina. A aplicação adequada dos padrões será considerada positivamente na avaliação do mini-projeto, enquanto que uma aplicação forçada/inadequada acarretará em perca de pontos.
