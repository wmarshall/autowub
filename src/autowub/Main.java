package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class Main {
	public static void main(String[] args){
		SongRenderer sr = null;
		try {
			sr = new SongRenderer();
			Song s = new Song();
			System.out.println("Song generated");
			s.bpm = 160;
			System.out.println("Playing, len = " + sr.play(s));
			while(sr.seqencer.isRunning()){
				sr.seqencer.getMicrosecondPosition();
				//System.out.println(sr.seqencer.getMicrosecondPosition() + " / " + sr.seqencer.getMicrosecondLength());
			}
			
			
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sr.seqencer.close();
			System.out.println("Done");
		}
	}
}
