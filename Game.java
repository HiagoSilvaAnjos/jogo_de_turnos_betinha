import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private static Game instance;
    private List<Heroi> herois;
    private List<Monstro> monstros;
    private Dificuldade dificuldade;
    private Log log;
    private Turno turno;

    private Game() {
        this.herois = new ArrayList<>();
        this.monstros = new ArrayList<>();
        this.log = new Log();
        this.turno = new Turno();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void iniciarJogo() {
        herois.clear();
        monstros.clear();
        log = new Log();

        herois.add(new Guerreiro("Arthas"));
        herois.add(new Mago("Gandalf"));
        herois.add(new Arqueiro("Legolas"));
        herois.add(new Furtivo("Ezio"));

        int numMonstros = 2 + dificuldade.ordinal();
        for (int i = 0; i < numMonstros; i++) {
            monstros.add(gerarMonstroAleatorio(i));
        }

        log.registrar("=== INÍCIO DO JOGO ===");
        log.registrar("Dificuldade: " + dificuldade);
        log.registrar("Heróis: " + herois.size());
        log.registrar("Monstros: " + monstros.size());
    }

    public void terminarJogo() {
        boolean heroiVenceu = herois.stream().anyMatch(Player::estaVivo);
        boolean monstroVenceu = monstros.stream().anyMatch(Player::estaVivo);

        if (heroiVenceu && !monstroVenceu) {
            log.registrar("=== HERÓIS VENCERAM! ===");
        } else if (!heroiVenceu && monstroVenceu) {
            log.registrar("=== MONSTROS VENCERAM! ===");
        } else {
            log.registrar("=== EMPATE! ===");
        }
    }

    public void gerenciarTurnos() {
        int turnoAtual = 1;

        while (herois.stream().anyMatch(Player::estaVivo) &&
               monstros.stream().anyMatch(Player::estaVivo)) {

            log.registrar("\n--- TURNO " + turnoAtual + " ---");

            List<Player> jogadores = new ArrayList<>();
            jogadores.addAll(herois.stream().filter(Player::estaVivo).toList());
            jogadores.addAll(monstros.stream().filter(Player::estaVivo).toList());

            turno.executar(jogadores);
            turnoAtual++;

            if (turnoAtual > 50) {
                log.registrar("Limite de turnos atingido!");
                break;
            }
        }
    }

//    public Player gerarPlayerAleatoria() {
//        int tipo = (int) (Math.random() * 7);
//        switch (tipo) {
//            case 0: return new Guerreiro("Guerreiro Aleatório");
//            case 1: return new Mago("Mago Aleatório");
//            case 2: return new Arqueiro("Arqueiro Aleatório");
//            case 3: return new Furtivo("Furtivo Aleatório");
//            case 4: return new Ogro(Dificuldade.MEDIO);
//            case 5: return new Goblin(Dificuldade.MEDIO);
//            case 6: return new Dragao(Dificuldade.MEDIO);
//            default: return new Guerreiro("Padrão");
//        }
//    }

    private Monstro gerarMonstroAleatorio(int indice) {
        int tipo = (int) (Math.random() * 3);
        String sufixo = " #" + (indice + 1);
        switch (tipo) {
            case 1: return new Goblin(dificuldade, "Goblin" + sufixo);
            case 2: return new Dragao(dificuldade, "Dragão" + sufixo);
            default: return new Ogro(dificuldade, "Ogro" + sufixo);
        }
    }

    public List<Heroi> getHerois() { return herois; }
    public List<Monstro> getMonstros() { return monstros; }
    public Dificuldade getDificuldade() { return dificuldade; }
    public void setDificuldade(Dificuldade dificuldade) { this.dificuldade = dificuldade; }
    public Log getLog() { return log; }

    public boolean heroiVenceu() {
        return herois.stream().anyMatch(Player::estaVivo) &&
               monstros.stream().noneMatch(Player::estaVivo);
    }

    public boolean monstroVenceu() {
        return monstros.stream().anyMatch(Player::estaVivo) &&
               herois.stream().noneMatch(Player::estaVivo);
    }
}
