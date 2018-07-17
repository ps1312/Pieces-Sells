# Justificativa do projeto

### Qual é a sua ideia de aplicativo? Inclua uma breve justificativa.
Um aplicativo de gerenciamento de peças de veículos em uma oficina. O aplicativo será desenvolvido para suprir a demanda de uma oficina de veículos onde existe a necessidade do controle das peças que precisam ser compradas, outro requisito é que essa aplicação precisa ser acessada a qualquer momento com facilidade (através de um dispositivo mobile).

### Quem usará seu aplicativo e por que eles o usarão?
O dono do negócio e os funcionários. O aplicativo será usado para acelerar e facilitar o processo de controle para compra de novas peças. Atualmente não existe um processo ou documentação definida, o aplicativo irá prover um histórico de peças compradas e disponibilizará em tempo real quais peças estão em falta.

### Existe um aplicativo similar? Se sim, como o seu será diferente?
Como é uma demanda muito específica do estabelecimento não foi encontrado um aplicativo que atendesse as necessidades. Foram encontradas  aplicações que prometem gerenciar mais coisas que o necessário, por exemplo:

 1. Controle de vendas:
    - https://play.google.com/store/apps/details?id=br.thiagopacheco.vendas
   
 2. MG Ordem de Serviço Free
    - https://play.google.com/store/apps/details?id=com.emerson.barcellos.mgordenservico

Outro ponto negativo desses aplicativos é que só é possível um usuário acessar as informações por vez, o diferencial do nosso aplicativo é que as informações serão integradas, ou seja, múltiplos usuários terão acesso às mesmas informações.

### Como sua aplicação será estruturada? Quais telas o usuário irá interagir, e o que elas fazem? Qual é o fluxo de trabalho?
A primeira tela do aplicativo é a tela de login, nessa tela o usuário pode se conectar ao servidor ou ir para a tela de criar conta
- lo-fi: https://imgur.com/eLob27V

Depois da autorização, a próxima tela será uma lista de todas as peças que precisam ser compradas neste momento para aquele usuário específico, ao clicar no botão de adicionar peça o usuário vai ser redirecionado para a tela “Adicionar peça”. Clicando em um elemento da lista, o usuário vai ser redirecionado a tela de “Detalhes” onde aparecerá uma tela específica daquela peça, nessa tela o usuário pode clicar em “Comprar” e o item não irá mais aparecer na lista de pedidos pendentes.
- lo-fi: https://i.imgur.com/3jJNZ3w.png
	
### Quais componentes Android serão utilizados, além de classes, bibliotecas de terceiros, paradigmas de design, etc? Sua aplicação deve usar pelo menos 3 componentes básicos de Android.
Serão usadas activities, broadcast receivers e services.
- A aplicação consumirá dados de um servidor Back4App através do download desses dados realizados a partir de um service, o Back4App foi utilizado por causa da funcionalidade de login "out of the box".
- Além do Back4App a aplicação salva os dados baixados pelo service no banco SQLite do smartphone, utilizou-se o o SQLite por ele ser o mais simples e direto na hora de desenvolver os aplicativos para essa plataforma
- Um broadcast dinâmico será utilizado para avisar a aplicação que os dados foram baixados e que a lista pode ser exibida.

A aplicação também realiza o download dos dados mesmo que o aplicativo não esteja em primeiro plano, para isso é utilizado o JobScheduler para executar o service de tempos em tempos, o usuário pode configurar esse tempo e quando o service terminar, se existir alguma peça nova, um broadcast estático mostra uma notificação avisando ao usuário que uma nova peça foi adicionada.

O login foi feito junto com o Back4App, o SDK usado é: 'com.parse:parse-android:1.16.3' como está descrito em
https://www.back4app.com/docs/android/login-android-tutorial

