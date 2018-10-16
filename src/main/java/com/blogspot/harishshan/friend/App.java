package com.blogspot.harishshan.friend;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {

			//RIFF (little-endian) data, WAVE audio, Microsoft PCM, 16 bit, mono 16000 Hz or
			//RIFF (little-endian) data, WAVE audio, Microsoft PCM, 16 bit, mono 8000 Hz
			
			Configuration configuration = new Configuration();
			configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
			configuration.setSampleRate(8000);

			LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
			System.out.println("Started");
			recognizer.startRecognition(true);
			SpeechResult result = recognizer.getResult();
			while ((result = recognizer.getResult()) != null) {
				System.out.format("Hypothesis: %s\n", result.getHypothesis());
			}
			recognizer.stopRecognition();
			for (WordResult r : result.getWords()) {
			    System.out.println(r);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
