package br.com.streaming.util;

// NOVO: Centraliza formatação de duração
public class FormatadorTempo {
    public static String formatar(int segundos) {
        int min = segundos / 60;
        int seg = segundos % 60;
        return String.format("%02d:%02d", min, seg);
    }
}