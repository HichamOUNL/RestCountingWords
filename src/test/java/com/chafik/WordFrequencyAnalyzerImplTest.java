package com.chafik;

import com.chafik.wordFrequency.WordFrequencyAnalyzer;
import com.chafik.wordFrequency.WordFrequencyAnalyzerImpl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordFrequencyAnalyzerImplTest {
    private String testString = "one One oNe one234 two three The the tHe the";

    @Test
    public void testCalculateHighestFrequency() {
        WordFrequencyAnalyzer wordFrequencyAnalyzer =  new WordFrequencyAnalyzerImpl();
        assertEquals(4, wordFrequencyAnalyzer.calculateHighestFrequency(testString));
    }

    @Test
    public void testCalculateFrequencyForWord() {
        WordFrequencyAnalyzer wordFrequencyAnalyzer =  new WordFrequencyAnalyzerImpl();
        assertEquals(3, wordFrequencyAnalyzer.calculateFrequencyForWord(testString, "one"));
        assertEquals(1, wordFrequencyAnalyzer.calculateFrequencyForWord(testString, "two"));
        assertEquals(4, wordFrequencyAnalyzer.calculateFrequencyForWord(testString, "the"));
    }

    @Test
    public void testCalculateMostFrequentNWords() {
        WordFrequencyAnalyzer wordFrequencyAnalyzer =  new WordFrequencyAnalyzerImpl();
        assertEquals("the", wordFrequencyAnalyzer.calculateMostFrequentNWords(testString, 2).get(0).getWord());
        assertEquals(4, wordFrequencyAnalyzer.calculateMostFrequentNWords(testString, 2).get(0).getFrequency());
        assertEquals("one", wordFrequencyAnalyzer.calculateMostFrequentNWords(testString, 2).get(1).getWord());
        assertEquals(3, wordFrequencyAnalyzer.calculateMostFrequentNWords(testString, 2).get(1).getFrequency());
        assertEquals(2, wordFrequencyAnalyzer.calculateMostFrequentNWords(testString, 2).size());
    }
}
