public class Simulador {
    public static void main(String[] args) {
        System.out.println("=== SIMULADOR DE JOGO DE TURNOS ===\n");

        for (Dificuldade dificuldade : Dificuldade.values()) {
            System.out.println("Testando dificuldade: " + dificuldade);

            int vitoriasHerois = 0;
            int vitoriasMonstros = 0;

            for (int i = 0; i < 100; i++) {
                Game game = Game.getInstance();
                game.setDificuldade(dificuldade);

                try {
                    game.iniciarJogo();
                    game.gerenciarTurnos();
                    game.terminarJogo();

                    if (game.heroiVenceu()) {
                        vitoriasHerois++;
                    } else if (game.monstroVenceu()) {
                        vitoriasMonstros++;
                    }
                } catch (Exception e) {
                    System.err.println("Erro na simulação " + (i + 1) + ": " + e.getMessage());
                }
            }

            System.out.println("Vitórias dos Heróis: " + vitoriasHerois );
            System.out.println("Vitórias dos Monstros: " + vitoriasMonstros );
            System.out.println("----------------------------------------\n");
        }

    }
}
