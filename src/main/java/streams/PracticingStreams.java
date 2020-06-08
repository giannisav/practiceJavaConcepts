package streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PracticingStreams {

    public static void main(String[] args) {

        String[] words = getWords();

        List<String> wordList = Arrays.asList(words);

        System.out.println();
        countWordsLength(wordList);

        System.out.println("\n");
        countWordsOccurrences(wordList);

        System.out.println("\n");
        findLongestWord(wordList);

        System.out.println();
        findMostOccurredWord(wordList);

        System.out.println("\n");
        printDistinctWordsInLexicographicOrder(wordList);

        System.out.println("\n");
        removeWordsThatOccurExactlyThreeTimes(wordList);

        System.out.println("\n");
        removeWordsWithLengthThreeOrMore(wordList);
    }

    private static String[] getWords() {
        String text = "We choose to go to the moon. " +
                "We choose to go to the moon in this decade and do the other things, " +
                "not because they are easy, " +
                "but because they are hard, " +
                "because that goal will serve to organize and measure the best of our energies and skills, " +
                "because that challenge is one that we are willing to accept, " +
                "one we are unwilling to postpone, " +
                "and one which we intend to win, " +
                "and the others, too.";

        return text
                .toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .split(" ");
    }

    private static void countWordsLength(List<String> wordList) {
        System.out.println("Words with their length :");
        wordList.stream()
                .distinct()
                .forEach(w -> System.out.print(w + ": " + w.length() + ", "));
    }

    private static void countWordsOccurrences(List<String> wordList) {
        System.out.println("Words with their occurrences : ");
        wordList.stream()
                .distinct()
                .forEach(w -> System.out.print(w + ": " + Collections.frequency(wordList, w) + ", "));
    }

    private static void findLongestWord(List<String> wordList) {
        System.out.println("Longest word : ");
        System.out.println(wordList.stream()
                .max(Comparator.comparingInt(w -> w.length()))
                .orElse("Not Found"));
    }

    private static void findMostOccurredWord(List<String> wordList) {
        System.out.println("Most occurred word : ");
        String mostFrequent = wordList.stream()
                .max(Comparator.comparingInt(w -> Collections.frequency(wordList, w))).orElse("Not found");

        System.out.println(mostFrequent + ": " + Collections.frequency(wordList, mostFrequent));
    }

    private static void printDistinctWordsInLexicographicOrder(List<String> wordList) {
        System.out.println("Distinct words in lexicographical order : ");
        wordList.stream()
                .filter(w -> Collections.frequency(wordList, w) == 1)
                .sorted()
                .forEach(w -> System.out.print(w + ", "));
    }

    private static void removeWordsThatOccurExactlyThreeTimes(List<String> wordList) {
        System.out.println("Filtered words that occurred 3 times : ");
        wordList.stream()
                .distinct()
                .filter(w -> Collections.frequency(wordList, w) != 3)
                .forEach(w -> System.out.print(w + ", "));

    }

    private static void removeWordsWithLengthThreeOrMore(List<String> wordList) {
        System.out.println("Lexicographical order of words with length less or equal 3: ");
        wordList.stream()
                .distinct()
                .filter(w -> w.length() <= 3)
                .sorted()
                .forEach(w -> System.out.print(w + ", "));
    }
}
