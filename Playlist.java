import java.util.ArrayList;

public class Playlist {
    String nome;
    
    ArrayList<Musica> musicas = new ArrayList<>();


    
    void adicionarMusica(Musica musica) {
       
        this.musicas.add(musica);
        System.out.println("Música adicionada com sucesso!");
    }

    void removerMusica(int indice) {
        if (indice >= 0 && indice < this.musicas.size()) {
            this.musicas.remove(indice);
            System.out.println("Música removida da playlist.");
        } else {
            System.out.println("Índice não encontrado.");
        }
    }

    void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        
        if (this.musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
            return;
        }
        
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            this.musicas.get(i).exibir(); 
        }
    }

    int getDuracaoTotal() {
        int total = 0;
        for (int i = 0; i < this.musicas.size(); i++) {
            total += this.musicas.get(i).duracaoSegundos; 
        }
        return total;
    }

    int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}