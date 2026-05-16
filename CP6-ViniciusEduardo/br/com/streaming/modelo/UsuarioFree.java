package br.com.streaming.modelo;

// ALTERADO: Apenas ajuste de pacote
public class UsuarioFree extends Usuario {
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;

    public UsuarioFree() {
        super();
    }

    public UsuarioFree(String name, String email) {
        super(name, email);
        this.contadorReproducoes = 0;
    }

    @Override
    public void reproduzirMusica(Musica musica) {
        this.contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        super.reproduzirMusica(musica);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de playlists atingido!");
            System.out.println("💎 Assine Premium para playlists ilimitadas!");
            return;
        }
        Playlist playlist = new Playlist(nome);
        playlists.add(playlist);
        System.out.println("✅ Playlist criada!");
    }

    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }

    public int getContadorReproducoes() { return contadorReproducoes; }

    public void setContadorReproducoes(int contadorReproducoes) { this.contadorReproducoes = contadorReproducoes; }

    public static int getMaxPlaylists() { return MAX_PLAYLISTS; }
}