public class Musica {
    
   
    String titulo;
    String artista;
    int duracaoSegundos;
    String genero;

    
    void exibir() {
        System.out.println("Título: " + this.titulo + " | Artista: " + this.artista + 
                           " | Duração: " + this.getDuracaoFormatada() + " | Gênero: " + this.genero);
    }

    String getDuracaoFormatada() {
        int min = this.duracaoSegundos / 60;
        int seg = this.duracaoSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

    boolean contemTitulo(String busca) {
        return this.titulo.toLowerCase().contains(busca.toLowerCase());
    }

    boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }
}