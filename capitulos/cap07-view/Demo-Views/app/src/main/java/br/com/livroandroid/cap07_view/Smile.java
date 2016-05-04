package br.com.livroandroid.cap07_view;


public class Smile {
    public static final int FELIZ = 0;
    public static final int TRISTE = 1;
    public static final int LOUCO = 2;

    public String nome;
    private final int tipo;

    public Smile(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    /**
     * Retorna a imagem do Smile.
     * <p/>
     * As imagens foram inseridas no /res/drawable
     *
     * @return
     */
    public int getImagem() {
        switch (tipo) {
            case FELIZ:
                return R.drawable.feliz;
            case TRISTE:
                return R.drawable.triste;
            case LOUCO:
                return R.drawable.louco;
        }
        return R.drawable.naoencontrado;
    }

    @Override
    public String toString() {
        return nome;
    }
}
