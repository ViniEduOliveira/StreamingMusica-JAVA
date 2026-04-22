package Application;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Musica> musica = new ArrayList<>();
    static Usuario usuarioAtual = new Usuario();

    public static void main(String[] args) {
        Musica.adicionarGeneros();
        System.out.println("=== BEM-VINDO AO STREAMING ===");

        String nome = "";
        String email = "";
        boolean dadosValidos = false;

        while (!dadosValidos) {
            try {
                nome = validarTexto("Digite seu nome: ");
                email = validarTexto("Digite seu email: ");
                
                new Usuario(nome, email); 
                
                dadosValidos = true; 
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Por favor, insira os dados novamente.\n");
            }
        }

        menuInicial();
        int tipoUsuario = lerOpcao();
        escolhaTipoUsuario(tipoUsuario, nome, email);

        int opcao;
        do {
            if (usuarioAtual instanceof UsuarioPremium) {
                menuPremium();
            } else {
                menuFree();
            }
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    public static void menuInicial() {
        System.out.println("\nEscolha o tipo de conta:");
        System.out.println("1. Free (Gratuito)");
        System.out.println("2. Premium (Pago)");
        System.out.print("Escolha: ");
    }

    public static void menuPreco() {
        System.out.println("\nEscolha o plano Premium:");
        System.out.println("1. Mensal (R$ 19,90)");
        System.out.println("2. Anual (R$ 199,00)");
        System.out.println("3. Familiar (R$ 29,90)");
        System.out.print("Escolha: ");
    }

    public static void escolhaTipoUsuario(int opcao, String nome, String email) {
        switch (opcao) {
            case 1:
                usuarioAtual = new UsuarioFree(nome, email);
                System.out.println("✅ Conta Free criada com sucesso!");
                break;
            case 2:
                menuPreco();
                int opcaoPlano = lerOpcao();
                String plano;
                switch (opcaoPlano) {
                    case 1:
                        plano = "Mensal";
                        break;
                    case 2:
                        plano = "Anual";
                        break;
                    case 3:
                        plano = "Familiar";
                        break;
                    default:
                        System.out.println("Plano inválido. Criando conta Free.");
                        usuarioAtual = new UsuarioFree(nome, email);
                        return;
                }
                usuarioAtual = new UsuarioPremium(nome, email, plano);
                System.out.println("✅ Conta Premium criada com sucesso!");
                break;
            default:
                System.out.println("Opção inválida. Criando conta Free.");
                usuarioAtual = new UsuarioFree(nome, email);
        }
    }

    public static void menuFree() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist (máx. 3)");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Reproduzir música");
        System.out.println("7. Ver histórico");
        System.out.println("8. Exibir estatísticas");
        System.out.println("9. 💎 Fazer upgrade para Premium");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuPremium() {
        System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
        System.out.println("1. Cadastrar música");
        System.out.println("2. Listar todas as músicas");
        System.out.println("3. Buscar música");
        System.out.println("4. Criar playlist (ilimitado)");
        System.out.println("5. Gerenciar playlists");
        System.out.println("6. Reproduzir música (Alta Qualidade)");
        System.out.println("7. Ver histórico");
        System.out.println("8. Baixar música");
        System.out.println("9. Ver músicas baixadas");
        System.out.println("10. Exibir estatísticas");
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
        if (usuarioAtual instanceof UsuarioFree) {
            switch (opcao) {
                case 1: cadastrarMusica(); break;
                case 2: listarBiblioteca(); break;
                case 3: buscarMusica(); break;
                case 4: criarPlaylist(); break;
                case 5: gerenciarPlaylists(); break;
                case 6: reproduzirMusica(); break;
                case 7: exibirHistorico(); break;
                case 8: exibirEstatisticas(); break;
                case 9: fazerUpgrade(); break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo, " + usuarioAtual.getName() + "!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } else if (usuarioAtual instanceof UsuarioPremium) {
            switch (opcao) {
                case 1: cadastrarMusica(); break;
                case 2: listarBiblioteca(); break;
                case 3: buscarMusica(); break;
                case 4: criarPlaylist(); break;
                case 5: gerenciarPlaylists(); break;
                case 6: reproduzirMusica(); break;
                case 7: exibirHistorico(); break;
                case 8: baixarMusica(); break;
                case 9: listarMusicasBaixadas(); break;
                case 10: exibirEstatisticas(); break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo, " + usuarioAtual.getName() + "!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public static void cadastrarMusica() {
        System.out.println("\n--- CADASTRAR MÚSICA ---");
        Musica m = new Musica();

        m.setTitulo(validarTexto("Digite o título da música: "));
        m.setArtista(validarTexto("Digite o artista: "));

        for (Musica mus : musica) {
            if (mus.getTitulo().equalsIgnoreCase(m.getTitulo().trim()) &&
                mus.getArtista().equalsIgnoreCase(m.getArtista().trim())) {
                System.out.println("Erro: Esta música já está cadastrada na biblioteca!");
                return;
            }
        }

        boolean duracaoValida = false;
        while (!duracaoValida) {
            try {
                System.out.print("Digite a duração: ");
                int seg = lerOpcao();
                m.setDuracaoSegundos(seg);
                duracaoValida = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Tente novamente...");
            }
        }

        boolean generoValido = false;
        while (!generoValido) {
            try {
                System.out.println("Gêneros disponíveis");
                System.out.println(Musica.getGenerosPermitidos());
                m.setGenero(validarTexto("Escolha o gênero: "));
                generoValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Gênero inválido\n");
                System.out.println("Por favor, tente novamente.\n");
            }
        }

        musica.add(m);
        System.out.println("Música cadastrada na biblioteca global com sucesso!");
    }

    public static void listarBiblioteca() {
        System.out.println("\n--- BIBLIOTECA GLOBAL DE MÚSICAS ---");
        if (musica.isEmpty()) {
            System.out.println("Nenhuma música cadastrada no sistema ainda.");
            return;
        }
        for (int i = 0; i < musica.size(); i++) {
            System.out.println((i + 1) + ". " + musica.get(i).exibir());
        }
    }

    public static void buscarMusica() {
        System.out.println("\n--- BUSCAR MÚSICA ---");
        String termo = validarTexto("Digite o título ou artista para buscar: ");

        boolean encontrou = false;
        System.out.println("Resultados da busca:");

        for (int i = 0; i < musica.size(); i++) {
            Musica m = musica.get(i);
            if (m.contemTitulo(termo) || m.contemArtista(termo)) {
                System.out.println("- " + m.exibir());
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada");
        }
        System.out.println();
    }

    public static void criarPlaylist() {
        if (usuarioAtual instanceof UsuarioFree && usuarioAtual.getPlaylists().size() >= UsuarioFree.getMaxPlaylists()) {
            System.out.println("\nLimite de " + UsuarioFree.getMaxPlaylists() + " playlists atingido!");
            System.out.println("💎 Fazer upgrade para Premium para playlists ilimitadas!");
            return; 
        }

        System.out.println("\n--- CRIAR PLAYLIST ---");
        String nomePlaylist = validarTexto("Digite o nome da nova playlist: ");
        usuarioAtual.criarPlaylist(nomePlaylist);
    }

    public static void exibirEstatisticas() {
        System.out.println("\n--- ESTATÍSTICAS DO SISTEMA ---");
        System.out.println("Usuário atual: " + usuarioAtual.getName());
        System.out.println("Email do usuário: " + usuarioAtual.getEmail());
        if (usuarioAtual instanceof UsuarioPremium) {
            System.out.println("Plano: PREMIUM (" + ((UsuarioPremium) usuarioAtual).getPlano() + ")");
        } else {
            System.out.println("Plano: FREE");
        }
        System.out.println("Total de músicas na biblioteca global: " + musica.size());
        System.out.println("Total de playlists criadas: " + usuarioAtual.getPlaylists().size());
    }

    public static void reproduzirMusica() {
        if (musica.isEmpty()) {
            System.out.println("Nenhuma música cadastrada no sistema.");
            return;
        }

        listarBiblioteca();
        System.out.print("Escolha o número da música: ");
        int indice = lerOpcao() - 1;

        if (indice >= 0 && indice < musica.size()) {
            usuarioAtual.reproduzirMusica(musica.get(indice));
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public static void exibirHistorico() {
        usuarioAtual.exibirHistorico();
    }

    public static void fazerUpgrade() {
        if (usuarioAtual instanceof UsuarioPremium) {
            System.out.println("Você já é um usuário Premium.");
            return;
        }

        System.out.println("\n--- UPGRADE PARA PREMIUM ---");
        menuPreco();
        int opcaoPlano = lerOpcao();
        String planoEscolhido;

        switch (opcaoPlano) {
            case 1: planoEscolhido = "Mensal"; break;
            case 2: planoEscolhido = "Anual"; break;
            case 3: planoEscolhido = "Familiar"; break;
            default:
                System.out.println("Opção inválida. Upgrade cancelado.");
                return;
        }

        UsuarioPremium novoPremium = new UsuarioPremium(usuarioAtual.getName(), usuarioAtual.getEmail(), planoEscolhido);
        novoPremium.getPlaylists().addAll(usuarioAtual.getPlaylists());
        novoPremium.getHistoricoReproducao().addAll(usuarioAtual.getHistoricoReproducao());
        usuarioAtual = novoPremium;
        System.out.println("✅ Upgrade realizado com sucesso! Aproveite os recursos Premium.");
    }

    public static void baixarMusica() {
        if (usuarioAtual instanceof UsuarioPremium) {
            listarBiblioteca();
            System.out.print("Escolha a música para baixar: ");
            int indice = lerOpcao() - 1;

            if (indice >= 0 && indice < musica.size()) {
                ((UsuarioPremium) usuarioAtual).baixarMusica(musica.get(indice));
            } else {
                System.out.println("Índice inválido.");
            }
        } else {
            System.out.println("Recurso exclusivo para usuários Premium.");
        }
    }

    public static void listarMusicasBaixadas() {
        if (usuarioAtual instanceof UsuarioPremium) {
            ((UsuarioPremium) usuarioAtual).listarMusicasBaixadas();
        } else {
            System.out.println("Recurso exclusivo para usuários Premium.");
        }
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

    public static void adicionarMusicaNaPlaylist() {
        try {
            if (usuarioAtual.getPlaylists().isEmpty() || musica.isEmpty()) {
                System.out.println("Erro: Você precisa de playlists e músicas cadastradas.");
                return;
            }

            usuarioAtual.listarPlaylists();
            System.out.print("Digite o número da playlist: ");
            int idPlaylist = lerOpcao() - 1;

            Playlist p = usuarioAtual.encontrarPlaylist(idPlaylist);
            if (p == null) return;

            listarBiblioteca();
            System.out.print("Digite o número da música: ");
            int idMusica = lerOpcao() - 1;

            if (idMusica >= 0 && idMusica < musica.size()) {
                p.adicionarMusica(musica.get(idMusica));
            } else {
                System.out.println("Música inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar números. Operação cancelada.");
        }
    }

    public static void removerMusicaDaPlaylist() {
        try {
            if (usuarioAtual.getPlaylists().isEmpty()) {
                System.out.println("Nenhuma playlist encontrada");
                return;
            }

            usuarioAtual.listarPlaylists();
            System.out.print("Digite o número da playlist escolhida: ");
            int idPlaylist = lerOpcao() - 1;

            Playlist p = usuarioAtual.encontrarPlaylist(idPlaylist);
            if (p == null) return;

            p.listarMusicas();
            if (p.getQuantidadeMusicas() > 0) {
                System.out.print("Digite o número da música para remover: ");
                int idMusica = lerOpcao() - 1;
                p.removerMusica(idMusica);
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar números. Operação cancelada.");
        }
    }

    public static void exibirDetalhesPlaylist() {
        try {
            if (usuarioAtual.getPlaylists().isEmpty()) {
                System.out.println("Nenhuma playlist encontrada.");
                return;
            }

            usuarioAtual.listarPlaylists();
            System.out.print("Digite o número da playlist para ver os detalhes: ");
            int idPlaylist = lerOpcao() - 1;

            Playlist p = usuarioAtual.encontrarPlaylist(idPlaylist);
            if (p != null) {
                p.listarMusicas();
                System.out.println("\nQuantidade de músicas: " + p.getQuantidadeMusicas());
                System.out.println("Duração total da playlist: " + p.getDuracaoTotal() + " segundos.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar números. Operação cancelada.");
        }
    }

    public static String validarTexto(String mensagem) {
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