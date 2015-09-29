package br.com.livroandroid.carros.domain;

@org.parceler.Parcel
public class Carro {
    private static final long serialVersionUID = 6601006766832473959L;
    public long id;
    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" + "nome='" + nome + '\'' + '}';
    }
}
