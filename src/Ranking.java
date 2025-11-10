import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Gere o sistema de ranking (Top 3).
 * (Este ficheiro NÃO TEM "package" no topo, está no pacote padrão)
 */
public class Ranking {

    private static final String RANKING_FILE = "ranking.dat";

    public static class ScoreEntry implements Serializable {
        private static final long serialVersionUID = 1L; 
        public String name;
        public int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return String.format("%-10s %d", name, score); 
        }
    }

    @SuppressWarnings("unchecked")
    public static List<ScoreEntry> loadRanking() {
        List<ScoreEntry> scores = new ArrayList<>();
        File file = new File(RANKING_FILE);
        
        if (!file.exists()) {
            return scores; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            scores = (List<ScoreEntry>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar ranking: " + e.getMessage());
        }
        return scores;
    }

    public static void saveScore(String playerName, int score) {
        if (score == 0) return; 

        List<ScoreEntry> scores = loadRanking();
        scores.add(new ScoreEntry(playerName, score));

        Collections.sort(scores, new Comparator<ScoreEntry>() {
            @Override
            public int compare(ScoreEntry s1, ScoreEntry s2) {
                return Integer.compare(s2.score, s1.score); 
            }
        });

        List<ScoreEntry> top3 = new ArrayList<>();
        for (int i = 0; i < Math.min(3, scores.size()); i++) {
            top3.add(scores.get(i));
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RANKING_FILE))) {
            oos.writeObject(top3);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o ranking: " + e.getMessage());
        }
    }
}