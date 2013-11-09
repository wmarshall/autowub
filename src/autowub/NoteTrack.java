package autowub;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class NoteTrack {
	final Note[] notes;
	final int instrument;
	
	public NoteTrack(Note[] notes, int instrument){
		this.notes = notes;
		this.instrument = instrument;
	}
	
	
	public void fillMIDITrack(Track t, int channel) throws InvalidMidiDataException{
		long timestamp = 0;
		t.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0), 0));
		for(int i =0; i< notes.length; i++){
			if(notes[i].pitch == null){
				timestamp += notes[i].noteLength();
			}else{
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
				timestamp += notes[i].noteLength();
				t.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, channel, notes[i].asMidi(), notes[i].velocity ), timestamp));
			}
		}
	}
}
