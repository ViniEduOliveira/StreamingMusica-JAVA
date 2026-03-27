import java.util.ArrayList;
import java.util.Scanner;

public class StreamMusica {
    
   
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musica = new ArrayList<>();
    static ArrayList<String> generosPermitidos = new ArrayList<>();
    static Usuario usuarioAtual = new Usuario();

    public static void main(String[] args) {
    	adicionarGeneros();
        
        System.out.println("Bem-vindo ao Sistema de Streaming!");
        usuarioAtual.nome = validarTexto("Para começar, digite seu nome de usuário: ");

        
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0); 
    }
    

	public static void exibirMenu(){
	    System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
	    System.out.println("1. Cadastrar música");
	    System.out.println("2. Listar todas as músicas");
	    System.out.println("3. Buscar música");
	    System.out.println("4. Criar playlist");
	    System.out.println("5. Gerenciar playlists");
	    System.out.println("6. Exibir estatísticas");
	    System.out.println("0. Sair");
	    System.out.print("Escolha uma opção: ");
	}
	
	 public static int lerOpcao() {
	        try {
	            return Integer.parseInt(scanner.nextLine());
	            
	        } catch (NumberFormatException e) {
	        	System.out.println("Digite novamente");
	            return -1;
	        }
	    }

	 public static void processarOpcao(int opcao) {
	        switch (opcao) {
	        	case 1:
	        		cadastrarMusica();
	        		break;
	        	case 2:
	        		listarAcervo();
	        		break;
	        	case 3:
	        		buscarMusica();
	        		break;
	        	case 4:
	        		criarPlaylist();
	        		break;
	        	case 5: 
	        		gerenciarPlaylists();
	        		break;
	        	case 6:
	        		exibirEstatisticas();
	        		break;
	        	case 0:
                    System.out.println("Encerrando o sistema. Até logo, " + usuarioAtual.nome + "!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
	    }
	 
    static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        Musica m = new Musica();
        
        m.titulo = validarTexto("Digite o título da música: ");
        m.artista = validarTexto("Digite o artista: ");
        System.out.print("Digite a duração em segundos: ");
        m.duracaoSegundos = lerOpcao();
        
        m.genero = "";
        boolean generoValido = false;
        
        while (!generoValido) {
            System.out.println("Opções: " + generosPermitidos); 
            m.genero = validarTexto("Escolha o gênero: "); 
       
            for (String g : generosPermitidos) {
                if (g.equalsIgnoreCase(m.genero)) {
                    m.genero = g; 
                    generoValido = true;
                    break;
                }
            }
            
            if (!generoValido) {
                System.out.println("Gênero inválido! Tente novamente.");
            }
        }
        
        musica.add(m);
        System.out.println("Música cadastrada na biblioteca global com sucesso!");
    }

    static void listarAcervo() {
        System.out.println("\n--- BIBLIOTECA GLOBAL DE MÚSICAS ---");
        if (musica.isEmpty()) {
            System.out.println("Nenhuma música cadastrada no sistema ainda.");
            return;
        }
        for (int i = 0; i < musica.size(); i++) {
            System.out.print((i + 1) + ". ");
            musica.get(i).exibir();
        }
    }

    static void buscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        String termo = validarTexto("Digite o título ou artista para buscar: ");
        
        boolean encontrou = false;
        System.out.println("Resultados da busca:");
        
        for (int i = 0; i < musica.size(); i++) {
            Musica m = musica.get(i);
            if (m.contemTitulo(termo) || m.contemArtista(termo)) {
                System.out.print("- ");
                m.exibir();
                encontrou = true;
            }
        }
        
        if (!encontrou) {
            System.out.println("Nenhuma música encontrada");
        }
    }

    static void criarPlaylist() {
        System.out.println("\n--- CRIAR PLAYLIST ---");
        String nomePlaylist = validarTexto("Digite o nome da nova playlist: ");
        usuarioAtual.criarPlaylist(nomePlaylist);
    }

    static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        System.out.println("Usuário atual: " + usuarioAtual.nome);
        System.out.println("Total de músicas na biblioteca global: " + musica.size());
        System.out.println("Total de playlists criadas: " + usuarioAtual.playlists.size());
    }

    
    public static void gerenciarPlaylists() {
	    int opcaoSub;
	    do {
	        exibirMenuPlaylist();
	        opcaoSub = lerOpcao(); 
	        processarOpcaoPlaylist(opcaoSub);
	    } while (opcaoSub != 0);
	}

	public static void exibirMenuPlaylist() {
	    System.out.println("\n=== GERENCIAR PLAYLISTS ===");
	    System.out.println("1. Listar minhas playlists");
	    System.out.println("2. Adicionar música a uma playlist");
	    System.out.println("3. Remover música de uma playlist");
	    System.out.println("4. Exibir detalhes de uma playlist");
	    System.out.println("0. Voltar");
	    System.out.print("Escolha uma opção: ");
	}

	public static void processarOpcaoPlaylist(int opcaoSub) {
	    switch (opcaoSub) {
	        case 1:
	            usuarioAtual.listarPlaylists();
	            break;
	        case 2:
	            adicionarMusicaNaPlaylist();
	            break;
	        case 3:
	            removerMusicaDaPlaylist();
	            break;
	        case 4:
	            exibirDetalhesPlaylist();
	            break;
	        case 0:
	            System.out.println("Voltando ao menu principal...");
	            break;
	        default:
	            System.out.println("Opção inválida! Tente novamente.");
	    }
	}

    static void adicionarMusicaNaPlaylist() {
        if (usuarioAtual.playlists.isEmpty() || musica.isEmpty()) {
            System.out.println("Você precisa ter pelo menos uma playlist criada e uma música na biblioteca!");
            return;
        }

        usuarioAtual.listarPlaylists();
        System.out.print("Digite o número da playlist escolhida: ");
        int idPlaylist = lerOpcao();
        
        
        Playlist p = usuarioAtual.getPlaylist(idPlaylist);
        if (p == null) return; 

        listarAcervo();
        System.out.print("Digite o número da música da biblioteca para adicionar: ");
        int idMusica = lerOpcao();

        if (idMusica >= 0 && idMusica < musica.size()) {
            p.adicionarMusica(musica.get(idMusica));
        } else {
            System.out.println("Música não encontrada na biblioteca.");
        }
    }

    static void removerMusicaDaPlaylist() {
        if (usuarioAtual.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist encontrada");
            return;
        }

        usuarioAtual.listarPlaylists();
        System.out.println("Digite o número da playlist escolhida: ");
        
        int idPlaylist = lerOpcao() - 1;
        
        Playlist p = usuarioAtual.getPlaylist(idPlaylist);
        if (p == null) {
        	return;
        }
        	

        p.listarMusicas();
        if (p.getQuantidadeMusicas() > 0) {
        	System.out.println("Digite o número da música para remover: ");
        	int idMusica = lerOpcao();
            p.removerMusica(idMusica);
        }
    }

    static void exibirDetalhesPlaylist() {
        if (usuarioAtual.playlists.isEmpty()) {
            System.out.println("Nenhuma playlist encontrada.");
            return;
        }

        usuarioAtual.listarPlaylists();
        System.out.println("Digite o número da playlist para ver os detalhes: ");
        int idPlaylist = lerOpcao();
        
        Playlist p = usuarioAtual.getPlaylist(idPlaylist);
        if (p != null) {
            p.listarMusicas();
            System.out.println("\nQuantidade de músicas: " + p.getQuantidadeMusicas());
            System.out.println("Duração total da playlist: " + p.getDuracaoTotal() + " segundos.");
        }
    }

     static void adicionarGeneros() {
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

    static String validarTexto(String mensagem) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensagem);
            texto = scanner.nextLine();
            if (texto.trim().isEmpty()) {
                System.out.println("A entrada não pode ser vazia. Tente novamente.");
            }
        }
        return texto;
    }
}