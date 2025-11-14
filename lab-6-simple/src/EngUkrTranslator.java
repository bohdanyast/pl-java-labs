import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EngUkrTranslator {
    private HashMap<String, String> words;

    public EngUkrTranslator() {
        this.words = new HashMap<>();
    }

    public void addWord(String ukrVersion, String engVersion) {
        words.put(engVersion, ukrVersion);
    }

    public String translateFromEnglish(String engVersion) {
        return words.get(engVersion);
    }

    public void getAllWordsAndTranslations() {
        List<String> engWords = new ArrayList<>(words.keySet());

        for (String engWord : engWords) {
            String ukrWord =  words.get(engWord);
            System.out.println( engWord + " - " + ukrWord);
        }
    }
}
