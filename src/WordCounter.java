import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {
    // Метод для подсчета уникальных слов и их количества
    public Map<String, Integer> countWords(String[] wordsArray) {
        // Используем HashMap для хранения уникальных слов и их количеств
        Map<String, Integer> wordCount = new HashMap<>();

        // Проходим по каждому слову в массиве
        for (String word : wordsArray) {
            // Если слово уже есть в словаре, увеличиваем счетчик
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        return wordCount; // Возвращаем итоговый словарь
    }
}
