package tddPractice;

import java.util.NoSuchElementException;

/**
 * A Dictionary is given a set of correctly spelled words, and is able to check if new words are spelled correctly.
 * Each word in the dictionary is either marked as being case sensitive, or case insensitive when being compared
 * with other words.
 * - For example, the word "hello" would be case insensitive, as "Hello" and "heLLO" are also spelt correctly
 *   (albeit with unexpected capitalization).
 * - For example, the word "Bobby" would be case sensitive, as "bobby" and "BOBBY" are both spelt incorrectly.
 *
 * It is up to the client code to tell the Dictionary which words are case sensitive or not.
 *
 *                      ***** YOU MAY NOT MODIFY THIS INTERFACE IN ANY WAY ******
 *
 */
public interface Dictionary {
    enum CaseSensitivity {
        CASE_INSENSITIVE,
        CASE_SENSITIVE
    }

    /**
     * Add a new word to the dictionary, recording if it should be checked in a case sensitive, or insensitive
     * manner. For example, a name like "Bob" would be case sensitive, but words like "Then" should be insensitive.
     * The client code decides if it should be case sensitive or not.
     *
     * - If newWord (in any case) is already in the Dictionary as case insensitive,
     *   then throw IllegalArgumentException
     *
     * - If newWord (exact case) is already in the Dictionary as case sensitive,
     *   and trying to add newWord as case sensitive, then throw IllegalArgumentException
     *
     * - If newWord (in any case) is already in the Dictionary and case sensitive, and is now being added as
     *   case insensitive, then remove all existing matching words and add newWord as case insensitive.
     *
     * - If newWord is already in the Dictionary as case sensitive, and now being added as case sensitive with a
     *   different case (such as 'Hello' vs 'hEllo', then add newWord to the dictionary.
     *
     * @param newWord The word to add (in the correct case, if it is case sensitive)
     * @param caseSensitivity Should the word be checked in a case sensitive, or insensitive way
     * @throws IllegalArgumentException Thrown when word already in dictionary matches newly added word
     * @throws IllegalArgumentException when newWord or caseSensitivity are null
     */
    void addWord(String newWord, CaseSensitivity caseSensitivity) throws IllegalArgumentException;

    /**
     * Remove all matching occurrences of word from the Dictionary.
     * If an existing word in the dictionary matches this word in exact case, then it is removed.
     * If an existing word in the dictionary is case insensitive and matches this word ignoring case, then it is removed.
     * @param word Word to remove from the dictionary
     * @throws NoSuchElementException if no matches to word in the Dictionary
     * @throws IllegalArgumentException word is null
     */
    void removeWord(String word) throws NoSuchElementException, IllegalArgumentException;

    /**
     * Count how many words are stored in the Dictionary.
     * A word entered with two different capitalizations (such as "Hi" and "hi") is considered two words.
     * @return Number of words.
     */
    int getWordCount();

    /**
     * Check if the Dictionary knows word to be spelt correctly.
     * @param word The word to check if it matches an entry in the Dictionary.
     * @return true if a Dictionary word matches word; false otherwise
     * @throws IllegalArgumentException word is null
     */
    boolean isSpeltCorrectly(String word) throws IllegalArgumentException;
}


