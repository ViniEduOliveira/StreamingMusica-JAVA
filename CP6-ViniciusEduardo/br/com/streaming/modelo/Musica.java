package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import br.com.streaming.util.FormatadorTempo;
import java.util.ArrayList;
import java.util.List;

// ALTERADO: Herda de ItemReproducao e implementa Baixavel
public class Musica extends ItemReproducao implements Baixavel {
    private String artista;
    private int duracaoSegundos;
    private String genero;
    private static ArrayList<String> generosPermitidos = new ArrayList<>();
    private static ArrayList<Musica> catalogo = new ArrayList<>();

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        super(titulo); // ALTERADO: Passa o título para a classe pai
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    // NOVO: Método obrigatório da interface Reproduzivel
    @Override
    public void reproduzir() {
        System.out.println("🎵 Reproduzindo: " + getTitulo() + " - " + this.artista);
    }

    // NOVO: Método obrigatório da interface Baixavel
    @Override
    public void baixar() {
        System.out.println("⬇️ Baixando a música: " + getTitulo() + " para ouvir offline.");
    }

    public String exibir() {
        // ALTERADO: Uso do FormatadorTempo
        return "Titulo: " + getTitulo() + " | Artista: " + this.artista + 
               " | Duração: " + FormatadorTempo.formatar(this.duracaoSegundos) + 
               " | Gênero: " + this.genero;
    }

    public boolean contemTitulo(String busca) {
        return getTitulo().toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return this.artista.toLowerCase().contains(busca.toLowerCase());
    }

    public String getArtista() { return artista; }
    
    public void setArtista(String artista) {
        if (artista == null || artista.trim().isEmpty()) {
            throw new IllegalArgumentException("Artista inválido");
        }
        this.artista = artista.trim();
    }

    public int getDuracaoSegundos() { return duracaoSegundos; }
    
    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos >= 0 && duracaoSegundos <= 3600) {
            this.duracaoSegundos = duracaoSegundos;
        } else {
            throw new IllegalArgumentException("Duração deve ser entre 1 e 3600 segundos.");
        }
    }

    public String getGenero() { return genero; }
    
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

    public static ArrayList<String> getGenerosPermitidos() { return generosPermitidos; }
    
    public static ArrayList<Musica> getCatalogo() { return catalogo; }

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
    
    public static void catalogoMusica() {
    	catalogo.clear();
    	catalogo.addAll(List.of(
                new Musica("Artista genérico", "Veigh", 145, "Trap"),
                new Musica("Talvez você precise de mim", "Veigh", 152, "Trap"),
                new Musica("Pela última vez", "Menos é Mais", 186, "Pagode"),
                new Musica("P do pecado", "Menos é Mais", 175, "Pagode"),
                new Musica("Aquele lugar", "Menos é Mais", 225, "Pagode"),
                new Musica("Estilo Cachorro", "Racionais MC's", 378, "Hip-Hop"),
                new Musica("Jesus chorou", "Racionais MC's", 474, "Hip-Hop"),
                new Musica("Diário de um detento", "Racionais MC's", 451, "Hip-Hop"),
                new Musica("Six Days", "DJ Shadow", 229, "Eletrônica"),
                new Musica("DtMF", "Bad Bunny", 205, "Trap"),
                new Musica("See you again(feat. Kali Uchis)", "Tyler The Creator", 180, "Hip-Hop"),
                new Musica("21 questions", "50 Cent", 224, "Hip-Hop"),
                new Musica("Não quero só dinheiro", "Tim Maia", 153, "MPB"),
                new Musica("País do futebol", "MC Guimê", 147, "Funk"),
                new Musica("Céu azul", "Charlie Brown Jr.", 198, "Rock"),
                new Musica("Vagalumes", "Pollo", 174, "Pop"),
                new Musica("Boate azul", "Joaquim & Manuel", 198, "Sertanejo"),
                new Musica("Sonhar", "MC Gui", 182, "Funk"),
                new Musica("Calculista", "MC Joãozinho VT", 168, "Funk"),
                new Musica("365 dias", "MC Marks", 210, "Funk")
        ));	
    }
}