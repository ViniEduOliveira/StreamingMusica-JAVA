package Application;

import java.util.ArrayList;

public class PlaylistAutomatica extends Playlist {
    private String criterio;

    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.descricao = "Gerada automaticamente pelo sistema";
        this.criterio = criterio;
    }

    @Override
    public void reproduzir() {
        System.out.println("🤖 Playlist Automática: " + nome);
        System.out.println("📊 Critério: " + criterio);
        super.reproduzir(); 
    }

    public void atualizar(ArrayList<Musica> todasMusicas) {
        this.musicas.clear();

        if (todasMusicas == null || todasMusicas.isEmpty()) {
            return;
        }

        if (criterio.equals("top")) {
            int limite = Math.min(5, todasMusicas.size());
            for (int i = 0; i < limite; i++) {
                this.musicas.add(todasMusicas.get(i));
            }
            System.out.println("🔄 " + nome + " atualizada com as Top 5!");
            
        } else if (criterio.equals("recomendadas")) {
            for (int i = 0; i < todasMusicas.size(); i += 2) {
                this.musicas.add(todasMusicas.get(i));
                if (this.musicas.size() >= 7) break;
            }
            System.out.println("🔄 " + nome + " atualizada com 7 recomendações!");
        } else {
            System.out.println("Critério desconhecido.");
        }
    }
    

    public void adicionarMusica(Musica musica) {
        System.out.println("Erro: Não é possível editar uma playlist do sistema.");
    }
}