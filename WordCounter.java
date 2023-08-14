import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the text or provide the path to a file: ");
        String input = scanner.nextLine();

        // Step 9: Input validation to handle empty inputs or file errors
        if (input.trim().isEmpty()) {
            System.out.println("Input is empty. Exiting.");
            return;
        }

        String text = "";
        if (input.trim().endsWith(".txt")) {
            // If the user provides a file path, read the file and store its content in 'text'
            try {
                text = readFile(input);
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
                return;
            }
        } else {
            // If the user entered text directly, store it in 'text'
            text = input;
        }

        // Step 3: Split the string into an array of words using space or punctuation as delimiters
        String[] words = text.split("\\s+|\\p{Punct}");

        // Step 4: Initialize a counter variable to keep track of the number of words
        int wordCount = 0;

        // Step 7: Ignoring common words or stop words
      //  Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "is", "in", "of", "a", "an")); // Add more if needed

        // Step 8: Providing statistics like the number of unique words or the frequency of each word
        Map<String, Integer> wordFrequency = new HashMap<>();

        // Step 5: Iterate through the array of words and increment the counter for each word encountered
        for (String word : words) {
          //  if (!word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
                if (!word.isEmpty()){
                wordCount++;

                // Update word frequency
                wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
            }
        }

        // Step 6: Display the total count of words to the user
        System.out.println("Total word count: " + wordCount);

        // Step 8 (continued): Display the number of unique words and their frequency
        System.out.println("Number of unique words: " + wordFrequency.size());
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Helper method to read the content of a file
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}

