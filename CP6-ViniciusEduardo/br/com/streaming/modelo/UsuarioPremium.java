package br.com.streaming.modelo;

import java.util.ArrayList;

// ALTERADO: Apenas ajuste de pacote
public class UsuarioPremium extends Usuario {
    private String plano;
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium() {
        super();
    }

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo() + " - " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    public void baixarMusica(Musica musica) {
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            musica.baixar(); // ALTERADO: Utilizando o método da interface Baixavel
        } else {
            System.out.println("Música já está baixada!");
        }
    }

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.println((i + 1) + ". " + musicasBaixadas.get(i).exibir());
        }
    }

    public String getPlano() { return plano; }

    public void setPlano(String plano) { this.plano = plano; }

    public ArrayList<Musica> getMusicasBaixadas() { return musicasBaixadas; }

    public void setMusicasBaixadas(ArrayList<Musica> musicasBaixadas) { this.musicasBaixadas = musicasBaixadas; }
}