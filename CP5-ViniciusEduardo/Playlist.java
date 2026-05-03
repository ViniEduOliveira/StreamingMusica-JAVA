package Application;

import java.util.ArrayList;

public class Playlist {
    protected String nome;
    protected ArrayList<Musica> musicas;
    protected String descricao;

    public Playlist(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();
        this.descricao = "Criada pelo usuário";
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { 
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da playlist não pode ser nulo ou vazio.");
        }
        this.nome = nome.trim(); 
    }

    public ArrayList<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica musica) {
        if (musica == null) {
            System.out.println("Erro: Não é possível adicionar uma música nula.");
            return;
        }
        this.musicas.add(musica);
        System.out.println("Música adicionada com sucesso!");
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < this.musicas.size()) {
            this.musicas.remove(indice);
            System.out.println("Música removida da playlist.");
        } else {
            System.out.println("Índice não encontrado.");
        }
    }

    public void reproduzir() {
        System.out.println("🎵 Reproduzindo playlist: " + nome);
        if (musicas.isEmpty()) {
            System.out.println("A playlist está vazia!");
            return;
        }
        for (Musica m : musicas) {
            System.out.println(" ▶ " + m.getTitulo() + " - " + m.getArtista());
        }
    }

    public void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.nome + " ---");
        if (this.musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
            return;
        }
        for (int i = 0; i < this.musicas.size(); i++) {
            System.out.println((i + 1) + ". " + this.musicas.get(i).exibir());
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : this.musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}