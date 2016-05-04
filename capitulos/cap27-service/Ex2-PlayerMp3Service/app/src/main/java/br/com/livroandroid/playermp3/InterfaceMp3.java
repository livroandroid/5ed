package br.com.livroandroid.playermp3;

/**
 * Interface para fazer o bind do Service MediaPlayerService que a implementa
 * 
 * @author ricardo
 *
 */
public interface InterfaceMp3 {
	// Inicia a musica
	void play(String mp3);
	// Faz pause da musica
	void pause();
	// Para a musica
	void stop();
	// true se esta tocando
	boolean isPlaying();
	// Caminho da musica
	String getMp3();
}
