package com.chafik.wordFrequency;

public class WordFrequencyImpl implements WordFrequency {
	private String word;
	private int frequency;

	public WordFrequencyImpl(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}

	@Override
	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
