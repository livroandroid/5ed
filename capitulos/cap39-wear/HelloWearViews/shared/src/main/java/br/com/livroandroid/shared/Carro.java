package br.com.livroandroid.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo on 19/04/15.
 */
public class Carro implements Serializable {
    public String nome;
    public int img;

    public Carro(String nome, int img) {
        this.nome = nome;
        this.img = img;
    }

    public static List<Carro> getListEsportivos() {
        List<Carro> list = new ArrayList<Carro>();
        list.add(new Carro("Ferrari FF", R.drawable.ferrari_ff));
        list.add(new Carro("AUDI GT Spyder", R.drawable.audi_spyder));
        list.add(new Carro("Porsche Panamera", R.drawable.porsche_panamera));
        list.add(new Carro("Lamborghini Aventador", R.drawable.lamborghini_aventador));
        list.add(new Carro("Chevrolet Corvette Z06", R.drawable.chevrolet_corvette_z06));
        list.add(new Carro("BMW M5", R.drawable.bmw));
        list.add(new Carro("Renault Megane", R.drawable.renault_megane_trophy));
        list.add(new Carro("Maserati Grancabrio Sport", R.drawable.maserati_grancabrio_sport));
        list.add(new Carro("McLAREN MP4-12C", R.drawable.mclaren));
        list.add(new Carro("MERCEDES-BENZ C63 AMG", R.drawable.mercedes_bens));
        return list;
    }

    public static List<Carro> getListClassicos() {
        List<Carro> list = new ArrayList<Carro>();
        list.add(new Carro("Tucker 1948", R.drawable.tucker));
        list.add(new Carro("Chevrolet Corvette", R.drawable.chevrolet_corvette));
        list.add(new Carro("Chevrolet Impala Coupe", R.drawable.chevrolet_impala_coupe));
        list.add(new Carro("Cadillac Deville Convertible", R.drawable.cadillac_deville_convertible));
        list.add(new Carro("Chevrolet Bel-Air", R.drawable.chevrolet_belair));
        list.add(new Carro("Cadillac Eldorado", R.drawable.cadillac_eldorado));
        list.add(new Carro("Ferrari 250 GTO", R.drawable.ferrari_250_gto));
        list.add(new Carro("Dodge Challenger", R.drawable.dodge_challenger));
        list.add(new Carro("Camaro SS 1969", R.drawable.camaro_ss));
        list.add(new Carro("Ford Mustang 1976", R.drawable.ford_mustang));
        return list;
    }

    public static List<Carro> getListLuxo() {
        List<Carro> list = new ArrayList<Carro>();
        list.add(new Carro("Bugatti Veyron", R.drawable.bugatti_veyron));
        list.add(new Carro("Ferrari Enzo", R.drawable.ferrari_enzo));
        list.add(new Carro("Lamborghini Reventon", R.drawable.lamborghini_reventon));
        list.add(new Carro("Leblanc Mirabeau", R.drawable.leblanc_mirabeau));
        list.add(new Carro("Shelby Supercars Ultimate", R.drawable.shelby_supercars_ultimate));
        list.add(new Carro("Pagani Zonda", R.drawable.pagani_zonda));
        list.add(new Carro("Koenigsegg CCX", R.drawable.koenigsegg_ccx));
        list.add(new Carro("Mercedes SLR McLaren", R.drawable.mercedes_mclaren));
        list.add(new Carro("Rolls Royce Phantom", R.drawable.rolls_royce_phantom));
        list.add(new Carro("Lexus LFA", R.drawable.lexus_lfa));
        return list;
    }
}
