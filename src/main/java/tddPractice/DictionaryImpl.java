package tddPractice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DictionaryImpl implements Dictionary {

    Map<String, Dictionary.CaseSensitivity> dictionaryMap = new LinkedHashMap<>();

    @Override
    public void addWord(String newWord, Dictionary.CaseSensitivity caseSensitivity) throws IllegalArgumentException {

        if(caseSensitivity == null) throw new IllegalArgumentException() ;
        if(newWord == null) throw new IllegalArgumentException();

        boolean alreadyExists = dictionaryMap
                .entrySet()
                .stream()
                .anyMatch(s -> ( newWord.toLowerCase().equals(s.getKey().toLowerCase()) && s.getValue() == Dictionary.CaseSensitivity.CASE_INSENSITIVE));

        if (alreadyExists) throw new IllegalArgumentException();

        switch (caseSensitivity) {
            case CASE_INSENSITIVE :
                dictionaryMap
                        .entrySet()
                        .removeIf(x -> x.getKey().toLowerCase().equals(newWord.toLowerCase()) && (x.getValue() == Dictionary.CaseSensitivity.CASE_SENSITIVE));
                dictionaryMap.put(newWord, Dictionary.CaseSensitivity.CASE_INSENSITIVE);
                break;

            case CASE_SENSITIVE :
                if (dictionaryMap.containsKey(newWord)) {
                    throw new IllegalArgumentException();
                } else {
                    dictionaryMap.put(newWord, Dictionary.CaseSensitivity.CASE_SENSITIVE);
                }
                break;
            default:

        }
    }

    @Override
    public void removeWord(String word) throws NoSuchElementException, IllegalArgumentException {

        if (word == null) { throw new IllegalArgumentException();}

        dictionaryMap.entrySet().removeIf(s -> (word.toLowerCase().equals(s.getKey().toLowerCase())) &&
                ( s.getValue() == Dictionary.CaseSensitivity.CASE_INSENSITIVE));

        if (dictionaryMap.containsKey(word)) {
            dictionaryMap.remove(word);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int getWordCount() {
        return dictionaryMap.size();
    }

    @Override
    public boolean isSpeltCorrectly(String word) throws IllegalArgumentException {
        if (word == null) {
            throw new IllegalArgumentException();
        } else if (dictionaryMap
                .entrySet()
                .stream()
                .anyMatch(s -> word.toLowerCase().equals(s.getKey().toLowerCase()))) {
            return true;
        } else {
            return false;
        }
    }
}
