package Application;

import java.util.ArrayList;
import java.util.List;

public class Musica {
    
   
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;
    
    private static List<String> generosPermitidos = new ArrayList<>();
    
    public Musica() {
    	this("Sem Título", "Sem Artista", 0, "Pop");
    }
    
    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
    	setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }
        
    public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido");
        }
        this.titulo = titulo.trim();		
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();	
	}

	public int getDuracaoSegundos() {
		return duracaoSegundos;
	}

	public void setDuracaoSegundos(int duracaoSegundos) {
		if ( duracaoSegundos >= 0 && duracaoSegundos <= 3600) {
			this.duracaoSegundos = duracaoSegundos;
		} else {
	        throw new IllegalArgumentException("Duração deve ser entre 1 e 3600 segundos.");
	    }	
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
	    if (genero == null) throw new IllegalArgumentException("Gênero não pode ser nulo");
	    
	    boolean valido = false;
	    for (String g : generosPermitidos) {
	        if (g.equalsIgnoreCase(genero.trim())) {
	            this.genero = g;
	            valido = true;
	            break;
	        }
	    }
	    if (!valido) {
	        throw new IllegalArgumentException("Gênero musical não permitido.");
	    }
	}
	
	public static List<String> getGenerosPermitidos() {
		return generosPermitidos;
	}

	public String exibir() {
		StringBuilder sb = new StringBuilder();
		sb.append("Titulo: ");
		sb.append(this.titulo);
		sb.append(" | Artista: ");
		sb.append(this.artista);
		sb.append(" | Duração: ");
		sb.append(this.getDuracaoFormatada());
		sb.append(" | Gênero: ");
		sb.append(this.genero);
		
		return sb.toString();
	}

    public String getDuracaoFormatada() {
        int min = this.duracaoSegundos / 60;
        int seg = this.duracaoSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

    public boolean contemTitulo(String busca) {
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }
    
    public static void adicionarGeneros() {
    	generosPermitidos.clear();
        generosPermitidos.add("Pop");
        generosPermitidos.add("Rock");
        generosPermitidos.add("Jazz");
        generosPermitidos.add("Eletrônica");
        generosPermitidos.add("Hip-Hop");
        generosPermitidos.add("Clássica");
        generosPermitidos.add("Pagode");
        generosPermitidos.add("Sertanejo");
        generosPermitidos.add("Trap");
        generosPermitidos.add("Funk");
        generosPermitidos.add("MPB");
    }
}