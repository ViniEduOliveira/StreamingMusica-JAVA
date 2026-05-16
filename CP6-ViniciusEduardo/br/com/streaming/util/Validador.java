package br.com.streaming.util;

import java.util.Scanner;

// NOVO: Centraliza validação e leitura de dados
public class Validador {
    public static String validarTexto(String mensagem, Scanner scanner) {
        String texto = "";
        while (texto.trim().isEmpty()) {
            System.out.print(mensagem);
            texto = scanner.nextLine();
        }
        return texto;
    }

    public static int lerOpcao(Scanner scanner) {
        try { return Integer.parseInt(scanner.nextLine()); } 
        catch (NumberFormatException e) { return -1; }
    }
}