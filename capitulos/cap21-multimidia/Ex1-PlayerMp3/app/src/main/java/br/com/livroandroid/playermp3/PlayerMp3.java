package br.com.livroandroid.playermp3;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;

/**
 * Classe para encapsular o acesso ao MediaPlayer
 * 
 * @author ricardo
 *
 */
public class PlayerMp3 implements OnCompletionListener {
	private static final String CATEGORIA = "livroandroid";
	private static final int NOVO 		= 0;
	private static final int INICIADO 	= 1;
	private static final int PAUSADO 	= 2;
	private static final int PARADO 	= 3;
	// Começa o status zerado
	private int status = NOVO;
	private MediaPlayer player;
    // Caminho da música
	private String mp3;

	public PlayerMp3() {
		// Cria o MediaPlayer
		player = new MediaPlayer();

		// Executa o listener quando terminar a música
		player.setOnCompletionListener(this);
	}

	public void start(String mp3) {
		this.mp3 = mp3;

		try {
			switch (status) {
				case INICIADO:
					player.stop();
				case PARADO:
					player.reset();
				case NOVO:
					player.setDataSource(mp3);
					player.prepare();
				case PAUSADO:
					player.start();
					break;
			}

			status = INICIADO;
		} catch (Exception e) {
			Log.e(CATEGORIA,e.getMessage(),e);
		}
	}

	public void pause() {
		player.pause();
		status = PAUSADO;
	}

	public void stop() {
		player.stop();
		status = PARADO;
	}
	// Encerra o MediaPlayer e libera a memória
	public void close() {
		stop();
		player.release();
		player = null;
	}
	/**
	 * @see android.media.MediaPlayer.OnCompletionListener#onCompletion(android.media.MediaPlayer)
	 */
	public void onCompletion(MediaPlayer mp) {
		Log.d(CATEGORIA, "Fim da música: " + mp3);
	}
}
