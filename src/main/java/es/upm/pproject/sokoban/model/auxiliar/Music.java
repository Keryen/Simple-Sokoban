package es.upm.pproject.sokoban.model.auxiliar;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Music extends Thread {

	private static final Logger LOGGER = Logger.getLogger("es.upm.pproject.sokoban.model.SokobanImpl");
	private static final String EXCEPTION_MSG = "An exception has ocurr";
	
	@Override
	public void run() {
		try {
		Clip sonido;
		// Se obtiene un Clip de sonido
		sonido = AudioSystem.getClip();

		// Se carga con un fichero wav
		sonido.open(AudioSystem.getAudioInputStream(
				new File("resources/undertale-ost-ruins-extended.wav")));

		// Comienza la reproducción
		sonido.start();

		// Espera mientras se esté reproduciendo.
		sonido.loop(Clip.LOOP_CONTINUOUSLY);
		while (sonido.isRunning()) {Thread.sleep(1000);}
		}catch(Exception e){
			LOGGER.log(Level.SEVERE, EXCEPTION_MSG, e);
		}
	}
}
