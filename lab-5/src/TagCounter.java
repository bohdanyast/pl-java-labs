import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagCounter {
    public String readHtml(String link) {
        StringBuilder htmlContent = new StringBuilder();
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method (GET is default for openConnection)
            connection.setRequestMethod("GET");

            // Get the input stream from the connection
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return htmlContent.toString();
    }

    public Map<String, Integer> getTagsFrequencies(String htmlContent) {
        Pattern tagPattern = Pattern.compile("<(\\w+)");
        Matcher matcher = tagPattern.matcher(htmlContent);

        Map<String, Integer> tagFrequencies = new HashMap<>();

        while (matcher.find()) {
            String tagName = matcher.group(1);
            tagFrequencies.put(tagName, tagFrequencies.getOrDefault(tagName, 0) + 1);
        }

        return tagFrequencies;
    }

    public void displayTagsFrequenciesInAlphabetOrder(Map<String, Integer> tagFrequencies) {
        List<String> tagNames = new ArrayList<>(tagFrequencies.keySet());
        Collections.sort(tagNames);

        for (String tagName : tagNames) {
            Integer tagCount = tagFrequencies.get(tagName);
            System.out.println(tagName + " : " + tagCount);
        }
    }

    public void displayTagsFrequenciesInDescendingOrder(Map<String, Integer> tagFrequencies) {
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(tagFrequencies.entrySet());
        sortedEntries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            String tagName = entry.getKey();
            Integer tagCount = entry.getValue();
            System.out.println(tagName + "> : " + tagCount);
        }
    }
}
