package Application;

import java.util.ArrayList;
import java.util.Scanner;
 
public class Main {
 
 
    static ArrayList<String> titulos = new ArrayList<>();
    static ArrayList<String> artistas = new ArrayList<>();
    static ArrayList<Integer> duracoes = new ArrayList<>();
    static ArrayList<String> generos = new ArrayList<>();
    static ArrayList<String> generosPermitidos = new ArrayList<>();
 
 
    static Scanner scanner = new Scanner(System.in);
 
 
    public static void main(String[] args) {
        adicionarMusicasTeste();
        adicionarGeneros();
 
 
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
 
 
        System.out.println("\n🎵 Até logo! 🎵");
        scanner.close();
    }
 
 
    public static void exibirMenu() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar músicas");
        System.out.println("3. Buscar música por título");
        System.out.println("4. Buscar músicas por artista");
        System.out.println("5. Buscar músicas por gênero");
        System.out.println("6. Exibir estatísticas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
 
 
    
    public static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
            
        } catch (NumberFormatException e) {
            return -1;
        }
    }
 
 
    
    public static void processarOpcao(int opcao) {
        switch (opcao) {
        	case 1:
        		cadastrarMusica();
        		break;
        	case 2:
        		listarMusicas();
        		break;
        	case 3:
        		buscarPorTitulo();
        		break;
        	case 4:
        		buscarPorArtista();
        		break;
        	case 5: 
        		buscarPorGenero();
        		break;
        	case 6:
        		estatisticas();
        		break;
        	default:
        		System.out.println("Digite de novo");	
        }
    }
 
 
   
    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        
        String titulo = validarTexto("Digite o título da música: ");
        String artista = validarTexto("Digite o nome do artista da música " + titulo + ": ");
        String duracao =  validarTexto("Digite o tempo da duração em segundos: ");
        int duracaoInt = Integer.parseInt(duracao); 
       
        String genero = "";
        boolean generoValido = false;
        
        while (!generoValido) {
            System.out.println("Opções: " + generosPermitidos); 
            genero = validarTexto("Escolha o gênero: "); 
       
            for (String g : generosPermitidos) {
                if (g.equalsIgnoreCase(genero)) {
                    genero = g; 
                    generoValido = true;
                    break;
                }
            }
            
            if (!generoValido) {
                System.out.println("Gênero inválido! Tente novamente.");
            }
        }
 
 
        titulos.add(titulo);
        artistas.add(artista);
        duracoes.add(duracaoInt);
        generos.add(genero);   
        
        System.out.println("Música Cadastrada com Sucesso!");
    }
 
 
    public static void listarMusicas() {
        System.out.println("\n--- MÚSICAS CADASTRADAS ---");
        
        if (titulos.isEmpty()) {
        	System.out.println("Não tem músicas cadastradas");
        } else {
        	for (int i = 0; i < titulos.size(); i++) {
        		int numero = i + 1;
        		String titulo = titulos.get(i);
        	    String artista = artistas.get(i);
        	    String genero = generos.get(i);
        	    String duracaoFormatada = formatarDuracao(duracoes.get(i));
        	    
        	    System.out.println(numero + ". Título: " + titulo + " | Artista: " + artista + 
                        " | Duração: " + duracaoFormatada + " | Gênero: " + genero); 
        	}
        }
        System.out.println("Total: " + titulos.size() + " música(s)");
    }
 
 
    public static void buscarPorTitulo() {
        System.out.println("\n--- BUSCAR POR TÍTULO ---");
        System.out.print("Digite o título: ");
        boolean encontrado = false;  
      
        String busca = scanner.nextLine().toLowerCase();
        
        for (int i = 0; i < titulos.size(); i++) {
        	if (titulos.get(i).toLowerCase().contains(busca)) {
        		System.out.println("\n--- MÚSICA ENCONTRADA ---");
                System.out.println("Título: " + titulos.get(i));
                System.out.println("Artista: " + artistas.get(i));
                System.out.println("Gênero: " + generos.get(i));
                System.out.println("Duração: " + formatarDuracao(duracoes.get(i)));
                
                encontrado = true;
                break;
        	} 	
        }
        if (!encontrado) {
    		System.out.println("Música não encontrada");
        }
    }
    
    public static void buscarPorArtista() {
        System.out.println("\n--- BUSCAR POR ARTISTA ---");
        boolean encontrado = false;  
        boolean artistaImpresso = false;
        
        System.out.print("Digite o artista: ");
        String busca = scanner.nextLine().toLowerCase();
        
        for (int i = 0; i < artistas.size(); i++) {
        	if (artistas.get(i).toLowerCase().contains(busca)) {
        		
        		if(!artistaImpresso) {
        			System.out.println("\n--- MÚSICAS DO ARTISTA: " + artistas.get(i).toUpperCase() + " ---");
        			artistaImpresso = true;
        		}
        		
                System.out.println("Título: " + titulos.get(i) + " | " + "Gênero: " + generos.get(i));
                encontrado = true;
        	} 	
        }
        if (!encontrado) {
    		System.out.println("Artista não encontrado(a)");
        }
    }
    
    public static void buscarPorGenero() {
        System.out.println("\n--- BUSCAR POR GÊNERO ---");
        System.out.print("Digite o gênero: ");
        boolean encontrado = false;  
        boolean generoImpresso = false;
      
        String busca = scanner.nextLine().toLowerCase();
        
        for (int i = 0; i < generos.size(); i++) {
        	if (generos.get(i).toLowerCase().contains(busca)) {
        		System.out.println("\n--- GÊNERO ENCONTRADO ---");
        		
        		if (!generoImpresso) {
        			System.out.println("\n--- GêNERO ESCOLIDO" + generos.get(i).toUpperCase() + " ---");
        			generoImpresso = true;
        		}
        	
                System.out.println("Título: " + titulos.get(i) + " | " + "Artista: " + artistas.get(i));
                encontrado = true;  
        	} 	
        }
        if (!encontrado) {
    		System.out.println("Música não encontrada");
        }
    }
 
 
    public static void estatisticas() {
    	System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
    	int somaSeg = 0;
    	String popular = "";
    	int vezesPopular = 0;
    	
    	for (int i = 0; i < duracoes.size(); i++) {
    		somaSeg += duracoes.get(i);
    	}
    	int mediaSeg = somaSeg / duracoes.size();
    	String tempoTotal = formatarDuracao(somaSeg);
    	String MediaTotal = formatarDuracao(mediaSeg);
    	
    	for (int i = 0; i < generos.size(); i++) {
    		String genero = generos.get(i);
    		int contagemGenero = 0;
    		
    		for (int j = 0; j < generos.size();j++) {
    			if (generos.get(j).equalsIgnoreCase(genero)) {
    				contagemGenero++;
                }
    		}
    		if (contagemGenero > vezesPopular) {
    			vezesPopular = contagemGenero;
    			popular = genero;
            }
    	}
    	
    	System.out.println("Total de músicas: " + titulos.size());
    	System.out.println("Duração total: " + tempoTotal);
    	System.out.println("Duração Média: " + MediaTotal);
    	System.out.println("Gênero mais cadastrado: " + popular + " (" + vezesPopular + " músicas)");
    }
   
    public static String formatarDuracao(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%d:%02d", min, seg);
    }
 
 
    
    public static void adicionarMusicasTeste() {
        titulos.add("Artista Genérico");
        artistas.add("Veigh");
        duracoes.add(150);
        generos.add("Trap");
 
 
        titulos.add("Vitamina C");
        artistas.add("Menos É Mais");
        duracoes.add(154);
        generos.add("Pagode");
    }
    
    public static void adicionarGeneros() {
        generosPermitidos.add("Pop");
        generosPermitidos.add("Rock");
        generosPermitidos.add("Jazz");
        generosPermitidos.add("Eletrônica");
        generosPermitidos.add("Hip-Hop");
        generosPermitidos.add("Clássica");
        generosPermitidos.add("Pagode");
        generosPermitidos.add("Sertanejo");
        generosPermitidos.add("Trap");
    }
    
    //Vai validar os textos(artista...)
    public static String validarTexto(String mensagem) {
    	String texto = "";
        
        while (texto.trim().isEmpty()) {
        	System.out.print(mensagem);
        	texto = scanner.nextLine();
        	
        	if (texto.trim().isEmpty()) {
                System.out.println("O título não pode ser vazio. Tente novamente.");
            }
        }
    	
    	return texto;
    }
   
}