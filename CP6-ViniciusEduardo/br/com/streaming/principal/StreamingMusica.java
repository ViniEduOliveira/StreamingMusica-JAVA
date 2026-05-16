package br.com.streaming.principal;

import br.com.streaming.modelo.*;
import br.com.streaming.servico.GeradorRecomendacoes;
import br.com.streaming.util.Validador;
import java.util.ArrayList;
import java.util.Scanner;

// ALTERADO: Classe principal ajustada para importar e usar os novos pacotes/classes
public class StreamingMusica {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;
    static GeradorRecomendacoes gerador = new GeradorRecomendacoes(); 

    public static void main(String[] args) {
        Musica.adicionarGeneros();
        Musica.catalogoMusica(); 

        int opcaoEntrada;
        do {
            menuEntrada();
            opcaoEntrada = Validador.lerOpcao(scanner);
            processarOpcaoEntrada(opcaoEntrada);
        } while (opcaoEntrada != 0);
    }

    public static void menuEntrada() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Criar novo usuário");
        System.out.println("2. Login");
        System.out.println("3. Listar usuários");
        System.out.println("4. Exibir estatísticas globais");
        System.out.println("0. Sair do programa");
        System.out.print("Escolha: ");
    }

    public static void processarOpcaoEntrada(int opcao) {
        switch (opcao) {
            case 1: criarUsuario(); break;
            case 2: login(); break;
            case 3: listarUsuarios(); break;
            case 4: exibirEstatisticasGlobais(); break;
            case 0: System.out.println("Encerrando o servidor..."); break;
            default: System.out.println("Opção inválida.");
        }
    }

    public static void criarUsuario() {
        System.out.println("\n--- CRIAR NOVO USUÁRIO ---");
        String nome = Validador.validarTexto("Nome: ", scanner);
        String email = Validador.validarTexto("Email: ", scanner);

        System.out.println("\nTipo de conta:");
        System.out.println("1. Free");
        System.out.println("2. Premium");
        System.out.print("Escolha: ");
        int tipo = Validador.lerOpcao(scanner);

        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
            System.out.println("Usuário criado com sucesso!");
        } else if (tipo == 2) {
            System.out.println("Plano (1. Mensal / 2. Anual / 3. Familiar): ");
            int opcaoPlano = Validador.lerOpcao(scanner);
            String plano = (opcaoPlano == 1) ? "Mensal" : (opcaoPlano == 2) ? "Anual" : "Familiar";
            usuarios.add(new UsuarioPremium(nome, email, plano));
            System.out.println("Usuário criado com sucesso!");
        } else {
            System.out.println("Opção inválida. Operação cancelada.");
        }
    }

    public static void login() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no momento.");
            return;
        }

        listarUsuarios();
        System.out.print("\nEscolha o número do usuário: ");
        int id = Validador.lerOpcao(scanner) - 1;

        if (id >= 0 && id < usuarios.size()) {
            usuarioLogado = usuarios.get(id);
            String tipoConta = (usuarioLogado instanceof UsuarioPremium) ? "Premium" : "Free";
            System.out.println("Login realizado: " + usuarioLogado.getName() + " (" + tipoConta + ")");
            loopUsuarioLogado(); 
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void listarUsuarios() {
        System.out.println("\nUsuários cadastrados:");
        if (usuarios.isEmpty()) {
            System.out.println("A lista está vazia.");
            return;
        }
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            String tipo = (u instanceof UsuarioPremium) ? "Premium" : "Free";
            System.out.println((i + 1) + ". " + u.getName() + " (" + tipo + ")");
        }
    }

    public static void loopUsuarioLogado() {
        int opcao;
        do {
            if (usuarioLogado instanceof UsuarioPremium) {
                menuPremium();
            } else {
                menuFree();
            }
            opcao = Validador.lerOpcao(scanner);
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("Fazendo logout...");
        usuarioLogado = null; 
    }

    public static void menuFree() {
        System.out.println("\n=== ÁREA DO USUÁRIO (" + usuarioLogado.getName() + ") ===");
        System.out.println("1. Listar catálogo de músicas");
        System.out.println("2. Buscar música");
        System.out.println("3. Criar playlist personalizada (máx. 3)");
        System.out.println("4. Gerar playlist automática");
        System.out.println("5. Gerenciar minhas playlists");
        System.out.println("6. Reproduzir música");
        System.out.println("7. Ver meu histórico");
        System.out.println("8. 💎 Fazer upgrade para Premium");
        System.out.println("0. Logout");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuPremium() {
        System.out.println("\n=== ÁREA PREMIUM (" + usuarioLogado.getName() + ") ===");
        System.out.println("1. Listar catálogo de músicas");
        System.out.println("2. Buscar música");
        System.out.println("3. Criar playlist personalizada");
        System.out.println("4. Gerar playlist automática");
        System.out.println("5. Gerenciar minhas playlists");
        System.out.println("6. Reproduzir música (Alta Qualidade)");
        System.out.println("7. Ver meu histórico");
        System.out.println("8. Baixar música offline");
        System.out.println("9. Ver músicas baixadas");
        System.out.println("0. Logout");
        System.out.print("Escolha uma opção: ");
    }

    public static void processarOpcao(int opcao) {
        if (usuarioLogado == null) return;

        if (usuarioLogado instanceof UsuarioFree) {
            switch (opcao) {
                case 1: listarBiblioteca(); break;
                case 2: buscarMusica(); break;
                case 3: criarPlaylist(); break;
                case 4: gerarPlaylistAutomatica(); break;
                case 5: gerenciarPlaylists(); break;
                case 6: reproduzirMusica(); break;
                case 7: usuarioLogado.exibirHistorico(); break;
                case 8: fazerUpgrade(); break;
                case 0: break; 
                default: System.out.println("Opção inválida!");
            }
        } else if (usuarioLogado instanceof UsuarioPremium) {
            switch (opcao) {
                case 1: listarBiblioteca(); break;
                case 2: buscarMusica(); break;
                case 3: criarPlaylist(); break;
                case 4: gerarPlaylistAutomatica(); break;
                case 5: gerenciarPlaylists(); break;
                case 6: reproduzirMusica(); break;
                case 7: usuarioLogado.exibirHistorico(); break;
                case 8: baixarMusica(); break;
                case 9: listarMusicasBaixadas(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    public static void gerarPlaylistAutomatica() {
        System.out.println("\n=== PLAYLISTS AUTOMÁTICAS ===");
        System.out.println("1. Top Mais Tocadas");
        System.out.println("2. Recomendadas para Você");
        System.out.print("Escolha: ");
        int esc = Validador.lerOpcao(scanner);

        Playlist gerada = null;
        if (esc == 1) { 
            gerada = gerador.gerarTopMaisTocadas(Musica.getCatalogo());
        } else if (esc == 2) { 
            gerada = gerador.gerarRecomendadas(Musica.getCatalogo());
        } else { 
            System.out.println("Opção inválida."); 
            return; 
        }

        usuarioLogado.getPlaylists().add(gerada);
        System.out.println("Playlist '" + gerada.getTitulo() + "' criada com " + gerada.getQuantidadeMusicas() + " músicas!");
    }

    public static void exibirEstatisticasGlobais() {
        System.out.println("\n=== ESTATÍSTICAS DO SISTEMA ===");
        int totalFree = 0;
        int totalPremium = 0;
        int repFree = 0;
        int repPremium = 0;
        int anuncios = 0;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree) {
                totalFree++;
                repFree += u.getHistoricoReproducao().size();
                anuncios += ((UsuarioFree) u).getContadorReproducoes() / 3;
            } else if (u instanceof UsuarioPremium) {
                totalPremium++;
                repPremium += u.getHistoricoReproducao().size();
            }
        }
        
        int totalUsuarios = totalFree + totalPremium;
        int totalRep = repFree + repPremium;

        System.out.println("Total de usuários: " + totalUsuarios);
        System.out.println("- Free: " + totalFree + " usuários");
        System.out.println("- Premium: " + totalPremium + " usuários\n");

        System.out.println("Reproduções totais: " + totalRep);
        int pctFree = (totalRep == 0) ? 0 : (repFree * 100) / totalRep;
        int pctPrem = (totalRep == 0) ? 0 : (repPremium * 100) / totalRep;
        
        System.out.println("- Free: " + repFree + " reproduções (" + pctFree + "%)");
        System.out.println("- Premium: " + repPremium + " reproduções (" + pctPrem + "%)");

        System.out.println("\nAnúncios exibidos: " + anuncios);
    }

    public static void listarBiblioteca() {
        ArrayList<Musica> catalogo = Musica.getCatalogo();
        System.out.println("\n--- CATÁLOGO DE MÚSICAS ---");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " + catalogo.get(i).exibir());
        }
    }

    public static void buscarMusica() {
        String termo = Validador.validarTexto("Digite o título ou artista para buscar: ", scanner);
        boolean encontrou = false;
        for (Musica m : Musica.getCatalogo()) {
            if (m.contemTitulo(termo) || m.contemArtista(termo)) {
                System.out.println("- " + m.exibir());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma música encontrada.");
    }

    public static void criarPlaylist() {
        if (usuarioLogado instanceof UsuarioFree && usuarioLogado.getPlaylists().size() >= UsuarioFree.getMaxPlaylists()) {
            System.out.println("Limite de " + UsuarioFree.getMaxPlaylists() + " playlists atingido!");
            return; 
        }
        String nomePlaylist = Validador.validarTexto("Digite o nome da nova playlist: ", scanner);
        usuarioLogado.criarPlaylist(nomePlaylist);
    }

    public static void reproduzirMusica() {
        listarBiblioteca();
        System.out.print("Escolha o número da música: ");
        int indice = Validador.lerOpcao(scanner) - 1;
        if (indice >= 0 && indice < Musica.getCatalogo().size()) {
            usuarioLogado.reproduzirMusica(Musica.getCatalogo().get(indice));
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void fazerUpgrade() {
        System.out.println("Plano (1. Mensal / 2. Anual / 3. Familiar): ");
        int opcaoPlano = Validador.lerOpcao(scanner);
        String planoEscolhido = (opcaoPlano == 1) ? "Mensal" : (opcaoPlano == 2) ? "Anual" : "Familiar";

        UsuarioPremium novoPremium = new UsuarioPremium(usuarioLogado.getName(), usuarioLogado.getEmail(), planoEscolhido);
        novoPremium.getPlaylists().addAll(usuarioLogado.getPlaylists());
        novoPremium.getHistoricoReproducao().addAll(usuarioLogado.getHistoricoReproducao());
        
        usuarios.set(usuarios.indexOf(usuarioLogado), novoPremium);
        usuarioLogado = novoPremium;
        System.out.println("Upgrade realizado com sucesso!");
    }

    public static void baixarMusica() {
        listarBiblioteca();
        System.out.print("Escolha a música para baixar: ");
        int indice = Validador.lerOpcao(scanner) - 1;
        if (indice >= 0 && indice < Musica.getCatalogo().size()) {
            ((UsuarioPremium) usuarioLogado).baixarMusica(Musica.getCatalogo().get(indice));
        }
    }

    public static void listarMusicasBaixadas() {
        ((UsuarioPremium) usuarioLogado).listarMusicasBaixadas();
    }

    public static void gerenciarPlaylists() {
        int opcaoSub;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar minhas playlists");
            System.out.println("2. Adicionar música a uma playlist");
            System.out.println("3. Remover música de uma playlist");
            System.out.println("4. Exibir detalhes de uma playlist");
            System.out.println("5. Reproduzir uma playlist inteira");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcaoSub = Validador.lerOpcao(scanner);
            
            switch (opcaoSub) {
                case 1: usuarioLogado.listarPlaylists(); break;
                case 2: adicionarMusicaNaPlaylist(); break;
                case 3: removerMusicaDaPlaylist(); break;
                case 4: exibirDetalhesPlaylist(); break;
                case 5: reproduzirPlaylist(); break;
            }
        } while (opcaoSub != 0);
    }

    public static void adicionarMusicaNaPlaylist() {
        usuarioLogado.listarPlaylists();
        System.out.print("Digite o número da playlist: ");
        Playlist p = usuarioLogado.encontrarPlaylist(Validador.lerOpcao(scanner) - 1);
        if (p == null) return;

        if (p.getTitulo().equals("Top Mais Tocadas") || p.getTitulo().equals("Recomendadas para Você")) {
            System.out.println("Você não pode modificar playlists geradas pelo sistema.");
        } else {
            listarBiblioteca();
            System.out.print("Digite o número da música: ");
            int id = Validador.lerOpcao(scanner) - 1;
            if (id >= 0 && id < Musica.getCatalogo().size()) {
                p.adicionarMusica(Musica.getCatalogo().get(id));
            }
        }
    }

    public static void removerMusicaDaPlaylist() {
        usuarioLogado.listarPlaylists();
        System.out.print("Digite o número da playlist: ");
        Playlist p = usuarioLogado.encontrarPlaylist(Validador.lerOpcao(scanner) - 1);
        if (p == null) return;

        if (p.getTitulo().equals("Top Mais Tocadas") || p.getTitulo().equals("Recomendadas para Você")) {
            System.out.println("Você não pode modificar playlists geradas pelo sistema.");
        } else {
            p.listarMusicas();
            System.out.print("Música para remover: ");
            p.removerMusica(Validador.lerOpcao(scanner) - 1);
        }
    }

    public static void exibirDetalhesPlaylist() {
        usuarioLogado.listarPlaylists();
        System.out.print("Digite o número da playlist: ");
        Playlist p = usuarioLogado.encontrarPlaylist(Validador.lerOpcao(scanner) - 1);
        if (p != null) {
            p.listarMusicas();
            System.out.println("Quantidade: " + p.getQuantidadeMusicas() + " | Duração total: " + p.getDuracaoTotal() + " seg.");
        }
    }

    public static void reproduzirPlaylist() {
        usuarioLogado.listarPlaylists();
        System.out.print("Digite o número da playlist: ");
        Playlist p = usuarioLogado.encontrarPlaylist(Validador.lerOpcao(scanner) - 1);
        if (p != null) p.reproduzir();
    }
}