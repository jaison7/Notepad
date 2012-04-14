import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.FreeTTS;

public class TTS {
	
	private static final String VOICENAME= "kevin16";

	void speakText(String str) 
	{
		try
		{
		
		Voice voice;
		VoiceManager voiceManager = VoiceManager.getInstance();
	        
		voice = voiceManager.getVoice(VOICENAME);
		
		voice.allocate();
		voice.speak(str);
		}
		catch(Exception ex){}
	}

}