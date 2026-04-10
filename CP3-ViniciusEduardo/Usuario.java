package Application;

import java.util.ArrayList;

public class Usuario {
    
   
    private String name;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    
    public Usuario() {
    	this("Convidado");
    }
    
    public Usuario(String name) {
    	setName(name);
    }
   
    public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio.");
        }
        this.name = name.trim();
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}


	public void criarPlaylist(String name) {
		try {
            Playlist novaPlaylist = new Playlist(name); 
            this.playlists.add(novaPlaylist);
            System.out.println("Playlist '" + name + "' criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar playlist: " + e.getMessage());
        }
    }

    public Playlist encontrarPlaylist(int indice) {
        if (indice >= 0 && indice < this.playlists.size()) {
            return this.playlists.get(indice);
        }
        
        System.out.println("Playlist não encontrada.");
        return null;
    }

    public void listarPlaylists() {
        System.out.println("\n--- Playlists de " + this.getName() + " ---");
        
        if (this.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada ainda.");
            return;
        }
        
        for (int i = 0; i < this.playlists.size(); i++) {
            System.out.println((i + 1) + ". " + this.playlists.get(i).getName());
        }
    }
}
