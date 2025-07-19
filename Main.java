public class Main {

    public static void main(String[] args) {

        System.out.println("===  PARTIDA COMPLETA ===");
        Game game = Game.getInstance();
        game.setDificuldade(Dificuldade.DIFICIL);

        try {
            game.iniciarJogo();
            game.gerenciarTurnos();
            game.terminarJogo();

            System.out.println("\n=== LOG DA PARTIDA ===");
            game.getLog().mostrarLog();
        } catch (Exception e) {
            System.err.println("Erro na partida de exemplo: " + e.getMessage());
        }
    }

}
