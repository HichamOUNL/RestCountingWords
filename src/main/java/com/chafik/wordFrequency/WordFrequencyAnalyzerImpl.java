package com.chafik.wordFrequency;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@ApplicationScoped
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {
	private static final String REGEX_TEXT_INPUT = new String("^[a-zA-Z\\s]*$");

	@Override
	public int calculateHighestFrequency(String text) throws PatternSyntaxException {
		if (!text.isEmpty()) {
			Map<String, Integer> map = frequencyWords(text);
			return (Collections.max(map.values()));
		} else {
			return -1;
		}
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) throws PatternSyntaxException {
		if (!text.isEmpty() && !word.isEmpty()) {
			List<String> normalizedSentence = this.sentenceNormalizer(text);
			int frequency = 0;

			for (String nsStr : normalizedSentence) {
				if (nsStr.equalsIgnoreCase(word)) {
					frequency++;
				}
			}
			return frequency;
		} else {
			return -1;
		}
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) throws PatternSyntaxException {
		if (!text.isEmpty()) {
			Map<String, Integer> hmap = frequencyWords(text);
			List<WordFrequency> listWordFrequency = new ArrayList<>();
			List<WordFrequency> subListWordFrequency;

			hmap.entrySet().stream().sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue())).forEach(a -> {
				listWordFrequency.add(new WordFrequencyImpl(a.getKey(), a.getValue()));
			});

			if (n > listWordFrequency.size()) {
				subListWordFrequency = listWordFrequency.subList(0, listWordFrequency.size());
			} else {
				subListWordFrequency = listWordFrequency.subList(0, n);
			}

			return subListWordFrequency;
		} else {
			return null;
		}
	}

	public String calculateMostFrequentNWordsAsList(List<WordFrequency> words) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (WordFrequency word : words) {
			System.out.println(word.getWord() + ":" + word.getFrequency());
			sb.append("(");
			sb.append("\"");
			sb.append(word.getWord());
			sb.append("\"");
			sb.append(",");
			sb.append(word.getFrequency());
			sb.append(")");
			if (word != words.get(words.size() - 1)) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private List<String> sentenceNormalizer(String text) {
		List<String> normalizedSentence = new ArrayList<>();
		String inputStr = text.toLowerCase();
		String[] words = inputStr.split("\\s+");

		Pattern pattern = Pattern.compile(REGEX_TEXT_INPUT);
		Matcher matcher;

		for (String word : words) {
			matcher = pattern.matcher(word);
			if (matcher.matches()) {
				normalizedSentence.add(word);
			}
		}
		return normalizedSentence;
	}

	private HashMap<String, Integer> frequencyWords(String text) {
		List<String> normalizedSentence = this.sentenceNormalizer(text);

		HashMap<String, Integer> hMap = new HashMap<>();
		for (String word : normalizedSentence) {
			if (hMap.containsKey(word)) {
				int count = hMap.get(word);
				hMap.put(word, count + 1);
			} else {
				hMap.put(word, 1);
			}
		}
		return hMap;
	}
}
