/*
 * GameLogger.java
 *
 * Ultima modificação: 08/11/2025 (Refatorado)
 *
 * Classe utilitária para logar o estado do jogo (matriz e score) em um arquivo.
 */

// Este arquivo deve estar no mesmo pacote de Main/Window para ser acessível, ou em Matrix.Logger,
// que exigiria alterações na Window.java. Manteremos o pacote 'raiz' para facilitar.

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Matrix.Color;

/**
 * Classe GameLogger
 * Gerencia o registro do estado do jogo (tabuleiro e pontuação) para relatórios.
 */
public class GameLogger { // Alterado de interface para classe
    private static final List<Color[][]> texts = new ArrayList<>();
    private static final List<Integer> scores = new ArrayList<>();
    private static final String LOG_FILE_NAME = "TETRIS";

    /**
     * Escreve o log completo do jogo (estados da matriz e pontuações) no arquivo.
     * Chamado apenas no final do jogo para otimizar I/O.
     * @throws IOException Se ocorrer um erro ao escrever no arquivo.
     */
    public static void reportLog() throws IOException {
        try (BufferedWriter gametxt = new BufferedWriter(new FileWriter(LOG_FILE_NAME))) {
            gametxt.write("This is a report on the game! (Relatório gerado em: " + java.time.LocalDateTime.now() + ")");
            for (int i = 0; i < texts.size(); i++) {
                gametxt.write("\n\nThis current game score is: " + scores.get(i) + Arrays.deepToString((texts.get(i))));
            }
        }
    }

    /**
     * Registra o estado atual da matriz (Board) no log, fazendo uma cópia profunda.
     * @param text Matriz de cores do jogo.
     */
    public static void gameLog(Color[][] text){
        // Cria uma cópia profunda da matriz para armazenar o estado a cada frame
        if (text != null && text.length > 0) {
            Color[][] copy = new Color[text.length][text[0].length];
            for (int i = 0; i < text.length; i++) {
                System.arraycopy(text[i], 0, copy[i], 0, text[i].length);
            }
            texts.add(copy);
        }
    }

    /**
     * Registra a pontuação atual do jogo.
     * @param score Pontuação atual.
     */
    public static void gameScoreLog(int score){
        scores.add(score);
    }
}