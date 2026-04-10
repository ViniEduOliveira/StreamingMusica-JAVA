import java.util.ArrayList;

public class Usuario {
    
   
    String nome;
    ArrayList<Playlist> playlists = new ArrayList<>();

    
    
    void criarPlaylist(String nome) {
        
        Playlist novaPlaylist = new Playlist();
        novaPlaylist.nome = nome;
        
        this.playlists.add(novaPlaylist);
        System.out.println("Playlist '" + nome + "' criada com sucesso!");
    }

    Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        
        System.out.println("Playlist não encontrada.");
        return null;
    }

    void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.nome + " ---");
        
        if (this.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada ainda.");
            return;
        }
        
        for (int i = 0; i < this.playlists.size(); i++) {
            System.out.println((i + 1) + ". " + this.playlists.get(i).nome);
        }
    }
}
