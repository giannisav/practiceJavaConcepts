package tddPractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class DictionaryImplTest {

    Dictionary dictionary;

    @Before
    public void setUp() {
        System.out.println("Setting up the mess of the method");
        dictionary = new DictionaryImpl();
    }

    @After
    public void tearDown() {
        System.out.println("Cleaning up the mess of the method");
    }

    @Test
    public void givenAWordAddWordShouldAddItToDictionary() {
        //Given
        String word = "heLlo";

        //When
        dictionary.addWord(word,Dictionary.CaseSensitivity.CASE_SENSITIVE);

        //Then
        assertEquals(1, dictionary.getWordCount());
        assertTrue(dictionary.isSpeltCorrectly(word));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenCaseSensitiveWordThatAlreadyExistsAsCaseInsensitiveAddWordShouldThrowIllegalArgumentException() {
        //Given
        dictionary.addWord("new", Dictionary.CaseSensitivity.CASE_INSENSITIVE);
        String newWord = "nEw";

        //When
        dictionary.addWord(newWord, Dictionary.CaseSensitivity.CASE_INSENSITIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenCaseInsensitiveWordThatAlreadyExistsAsCaseInsensitiveAddWordShouldThrowIllegalArgumentException() {
        //Given
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_INSENSITIVE);
        String newWord = "NEW";

        //When
        dictionary.addWord(newWord, Dictionary.CaseSensitivity.CASE_INSENSITIVE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenWordAsCaseSensitiveThatAlreadyExistsAsCaseSensitiveAddWordShouldThrowIllegalArgumentException() {
        //Given
        dictionary.addWord("nEw", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        String newWord = "nEw";

        //When
        dictionary.addWord(newWord, Dictionary.CaseSensitivity.CASE_SENSITIVE);
    }

    @Test
    public void givenWordAsCaseInsensitiveThatAlreadyExistsAsCaseSensitiveAddWordShouldRemoveAllExistingMatchingWordsAndAddNewWordAsCaseInsensitive() {
        //Given
        dictionary.addWord("neW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("nEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("worD", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("WoRd", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String newWord = "nEw";
        String word2 = "word";

        //When
        dictionary.addWord(newWord, Dictionary.CaseSensitivity.CASE_INSENSITIVE);
        dictionary.addWord(word2,Dictionary.CaseSensitivity.CASE_INSENSITIVE);

        //Then
        assertEquals(2,dictionary.getWordCount());
        assertTrue(dictionary.isSpeltCorrectly(newWord));
        assertTrue(dictionary.isSpeltCorrectly(word2));

    }

    @Test (expected = IllegalArgumentException.class)
    public void givenNullWordAddWordShouldThrowIllegalArgumentException() {
        //Given
        String word = null;

        //When
        dictionary.addWord(word, Dictionary.CaseSensitivity.CASE_SENSITIVE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void givenNullCaseSensitivityAddWordShouldThrowIllegalArgumentException() {
        //Given
        String word = "word";

        //When
        dictionary.addWord(word,null);
    }

    @Test
    public void givenAnExistingWordInTheDictionaryMatchesThisWordInExactCaseWhenRemoveWordIsCalledThenThisWordShouldBeRemoved() {
        //Given
        dictionary.addWord("neW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String wordToRemove = "neW";

        //When
        dictionary.removeWord(wordToRemove);

        //Then
        assertEquals(1,dictionary.getWordCount());
    }

    @Test(expected = NoSuchElementException.class)
    public void givenANoExistingWordInTheDictionaryRemoveWordThrowsNoSuchElementException() {
        //Given
        dictionary.addWord("neW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("new", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String wordToRemove = "nEW";

        //When
        dictionary.removeWord(wordToRemove);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNullRemoveWordThrowsIllegalArgumentException() {
        //Given
        dictionary.addWord("neW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("new", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String wordToRemove = null;

        //When
        dictionary.removeWord(wordToRemove);
    }

    @Test
    public void getWordCount() {
        //Given
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("count", Dictionary.CaseSensitivity.CASE_INSENSITIVE);

        //When
        int count = dictionary.getWordCount();

        //Then
        assertEquals(3,count);
    }

    @Test (expected = IllegalArgumentException.class)
    public void givenNullWordWhenIsSpeltCorrectlyIsCalledThrowIllegalArgumentException() {
        //Given
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String word = null;

        //When
        dictionary.isSpeltCorrectly(word);
    }

    @Test
    public void givenWordThatExistsInTheDictionaryWhenIsSpeltCorrectlyIsCalledThenReturnTrue() {
        //Given
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String word = "nEEw";

        //When
        boolean speltCorrectly = dictionary.isSpeltCorrectly(word);

        //Then
        assertTrue(speltCorrectly);
    }

    @Test
    public void givenWordThatDoesntExistsInTheDictionaryWhenIsSpeltCorrectlyIsCalledThenReturnFalse() {
        //Given
        dictionary.addWord("NeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NEW", Dictionary.CaseSensitivity.CASE_SENSITIVE);
        dictionary.addWord("NeeW", Dictionary.CaseSensitivity.CASE_SENSITIVE);

        String word = "NEWS";

        //When
        boolean speltCorrectly = dictionary.isSpeltCorrectly(word);

        //Then
        assertFalse(speltCorrectly);
    }

}