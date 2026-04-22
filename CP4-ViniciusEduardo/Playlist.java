package Application;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Musica> musicas = new ArrayList<>();

    public Playlist() {
        this("Sem nome");
    }

    public Playlist(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da playlist não pode ser nulo ou vazio.");
        }
        this.name = name.trim();
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

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

    public void listarMusicas() {
        System.out.println("\n--- Playlist: " + this.getName() + " ---");
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
        for (int i = 0; i < this.musicas.size(); i++) {
            total += this.musicas.get(i).getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return this.musicas.size();
    }
}