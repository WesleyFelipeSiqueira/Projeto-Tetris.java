🎮 Projeto Tetris em Java
Este é um projeto de desenvolvimento do clássico jogo Tetris, construído inteiramente em Java usando a biblioteca gráfica Swing.

O objetivo deste projeto não é apenas recriar o jogo, mas também servir como um exemplo prático de Programação Orientada a Objetos (POO), aplicando princípios de design de software (como SOLID) e padrões de projeto (como Singleton e Repository) em um contexto real e divertido.

✨ Funcionalidades
Jogabilidade clássica de Tetris.

Rotação de peças (sentido horário).

Movimentação lateral, "Soft Drop" (acelerar para baixo) e "Hard Drop" (cair instantaneamente).

Visualização da "Próxima Peça".

Sistema de Pontuação.

Ranking Persistente: O jogo salva automaticamente os 10 maiores recordes em um arquivo (ranking.dat), para que suas conquistas nunca sejam perdidas!

Tela de "Game Over" e opção de reiniciar o jogo.

🚀 Como Executar o Jogo
Você não precisa de nenhuma ferramenta complicada para jogar. Apenas o Java Development Kit (JDK) instalado em seu computador.

Pré-requisitos
Certifique-se de ter o JDK (versão 8 ou superior) instalado e configurado no seu sistema.

Passo 1: Compilar o Código
Abra seu terminal ou prompt de comando, navegue até a pasta raiz do projeto (Projeto Tetris) e execute o seguinte comando:

Bash

javac -d out -sourcepath src src/Main.java
O que isso faz (para leigos): Este comando é o "tradutor". Ele lê todo o seu código-fonte humano (na pasta src) e o converte em "linguagem de máquina" Java (os arquivos .class), salvando-os na pasta out.

Passo 2: Executar o Jogo
Após a compilação, execute o jogo com o comando:

Bash

java -cp out Main
O que isso faz (para leigos): Este comando "liga" o programa. Ele diz ao Java para procurar os arquivos compilados na pasta out e iniciar o jogo pela classe Main.

⌨️ Controles
Seta para Esquerda: Mover a peça para a esquerda.

Seta para Direita: Mover a peça para a direita.

Seta para Cima: Rotacionar a peça.

Seta para Baixo: Acelerar a queda (Soft Drop).

Espaço: Derrubar a peça instantaneamente (Hard Drop).

(P): Pausar o Jogo (implementado no Engine.java).

🏗️ Como o Projeto Funciona (Arquitetura para Leigos)
Imagine que o projeto é uma "Fábrica de Carros". Cada parte tem um trabalho muito específico.

1. O "Botão de Ligar" (Main.java)
Esta é a classe mais simples. Ela apenas "aperta o botão" para ligar a fábrica, criando a Janela do Jogo (GameWindow) e iniciando o "Cérebro" (Engine).

2. O "Cérebro" da Operação (Engine.java)
Este é o gerente da fábrica. Ele funciona em um "Game Loop" contínuo (um while(true) que roda muito rápido). A cada "tick" desse loop, ele faz 3 perguntas:

O jogador apertou alguma tecla? (Move a peça)

Passou tempo suficiente para a peça cair? (Move a peça para baixo)

A peça colidiu com algo? (Verifica colisões)

Ele é quem toma todas as decisões e apenas manda ordens para as outras partes.

3. O "Palco" (GameWindow.java)
Esta é a janela que você vê. Ela é "burra" de propósito. Ela não sabe como o Tetris funciona. Sua única tarefa é ouvir o "Cérebro" (Engine) e desenhar na tela o que ele manda (desenhar o tabuleiro, as peças, a pontuação).

4. A "Planta Baixa" e os "Blocos" (Pacote Matrix)
Este pacote contém a lógica pura do jogo, sem nenhuma parte visual:

Board.java: É a planta baixa do jogo. É apenas uma matriz de dados que sabe onde tem e não tem peças.

Pieces/ (Ipiece, Jpiece, etc.): São as plantas de cada peça. Elas sabem seus formatos, suas cores e como devem rotacionar.

5. A "Pintura" e a "Decoração" (Pacote Visuals)
Este pacote contém o código que transforma a "Planta Baixa" (Matrix) em algo bonito na tela.

Block.java: Sabe como desenhar um único quadradinho colorido.

Block_Icons/: Contém as imagens .png de cada bloco, que dão a textura ao jogo.

Score.java: Sabe como desenhar o painel de pontuação.

6. O "Livro de Recordes" (Ranking.java)
Esta é a parte que cuida das pontuações máximas.

O que faz: Ele carrega os recordes do arquivo ranking.dat quando o jogo abre. Quando um jogo termina, ele adiciona o novo score, reordena a lista e salva os 10 melhores de volta no arquivo.

Padrões (Requisito): Embora o seu PDF mencione Singleton e Repository para um banco de dados, nós adaptamos! Seu Ranking.java (usando métodos estáticos) age como um Singleton (um ponto de acesso único e global para os scores) e como um Repository (ele mesmo gerencia a persistência dos dados no arquivo ranking.dat).

7. O "Diário de Bordo" (GameLogger.java)
Este é um "diário" que o jogo escreve enquanto roda. Se algo quebrar (um "Erro") ou algo importante acontecer (uma "Informação"), o Engine escreve uma nota no arquivo game_log.txt. Isso é essencial para que os desenvolvedores possam descobrir o que deu errado.

📂 Estrutura de Arquivos
Projeto Tetris/
├── src/                          # Onde fica todo o código-fonte (os "planos")
│   ├── Matrix/                   # Lógica do jogo (o tabuleiro e as peças)
│   │   ├── Board/
│   │   │   └── Board.java        # (Controla o tabuleiro principal)
│   │   └── Pieces/
│   │       ├── Piece.java        # (Classe "mãe" de todas as peças)
│   │       ├── Ipiece.java       # (etc. para todas as 7 peças)
│   │       └── ... (Jpiece, Lpiece, Opiece, Spiece, Tpiece, Zpiece)
│   │
│   ├── Visuals/                  # Parte gráfica (o que você vê)
│   │   ├── Background_Elements/  # (Imagens e fontes de fundo)
│   │   ├── Block_Icons/          # (As imagens de cada bloco colorido)
│   │   ├── Block.java            # (Sabe desenhar um bloco na tela)
│   │   ├── Score.java            # (Painel de pontuação)
│   │   ├── NextPiecePanel.java   # (Painel da próxima peça)
│   │   └── ... (e outras classes de UI)
│   │
│   ├── Engine.java               # O "Cérebro" do jogo (game loop, física, controles)
│   ├── GameWindow.java           # A "Janela" principal do jogo (desenha tudo)
│   ├── Main.java                 # O "Botão de Ligar" (inicia tudo)
│   ├── Ranking.java              # Onde os recordes são salvos e lidos
│   └── GameLogger.java           # O "Diário de Bordo" para registrar eventos
│
├── out/                          # (Pasta criada após a compilação, onde o Java roda)
│
└── ranking.dat                   # O arquivo binário onde os recordes são salvos
