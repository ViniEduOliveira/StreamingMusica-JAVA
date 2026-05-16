package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import br.com.streaming.modelo.Playlist;
import java.util.ArrayList;

// NOVO: Classe para gerar playlists (substituindo a antiga PlaylistAutomatica)
public class GeradorRecomendacoes {
    
    public Playlist gerarTopMaisTocadas(ArrayList<Musica> catalogo) {
        Playlist top = new Playlist("Top Mais Tocadas");
        int limite = Math.min(5, catalogo.size());
        for (int i = 0; i < limite; i++) {
            top.adicionarMusica(catalogo.get(i));
        }
        return top;
    }

    public Playlist gerarRecomendadas(ArrayList<Musica> catalogo) {
        Playlist recomendadas = new Playlist("Recomendadas para Você");
        for (int i = 0; i < catalogo.size(); i += 2) {
            recomendadas.adicionarMusica(catalogo.get(i));
            if (recomendadas.getMusicas().size() >= 7) break;
        }
        return recomendadas;
    }
}