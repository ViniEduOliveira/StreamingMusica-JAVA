package br.com.streaming.modelo;

import java.util.ArrayList;

// ALTERADO: Apenas ajuste de pacote
public class Usuario {
    protected String name;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historicoReproducao;

    public Usuario() {
    }

    public Usuario(String name, String email) {
        setName(name);
        setEmail(email);
        this.playlists = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    public final String getName() { return name; }
    
    public final void setName(String name) {
        if (name == null || name.trim().isEmpty()) { throw new IllegalArgumentException("Nome do usuário não pode ser vazio."); }
        this.name = name.trim();
    }

    public final String getEmail() { return email; }

    public final void setEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Erro: E-mail não pode ser nulo");
        email = email.trim().toLowerCase();
        if (!email.contains("@") || !(email.lastIndexOf(".") > email.indexOf("@"))) { throw new IllegalArgumentException("Erro: Formato de e-mail inválido"); }
        this.email = email;
    }

    public final ArrayList<Playlist> getPlaylists() { return playlists; }
    
    public final void setPlaylists(ArrayList<Playlist> playlists) { this.playlists = playlists; }
    
    public final ArrayList<Musica> getHistoricoReproducao() { return historicoReproducao; }
    
    public final void setHistoricoReproducao(ArrayList<Musica> historicoReproducao) { this.historicoReproducao = historicoReproducao; }

    public void criarPlaylist(String nome) {
        Playlist playlist = new Playlist(nome);
        this.playlists.add(playlist);
        System.out.println("Playlist '" + nome + "' criada com sucesso!");
    }

    public void reproduzirMusica(Musica musica) {
        System.out.println("🎵 Reproduzindo: " + musica.getTitulo() + " - " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    public final void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música foi reproduzida ainda.");
            return;
        }
        for (int i = 0; i < historicoReproducao.size(); i++) {
            System.out.println((i + 1) + ". " + historicoReproducao.get(i).exibir());
        }
    }

    public final void listarPlaylists() {
        if (this.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist encontrada.");
            return;
        }
        for (int i = 0; i < this.playlists.size(); i++) {
            System.out.println((i + 1) + ". " + this.playlists.get(i).getTitulo());
        }
    }

    public final Playlist encontrarPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        System.out.println("Playlist não encontrada.");
        return null;
    }
}