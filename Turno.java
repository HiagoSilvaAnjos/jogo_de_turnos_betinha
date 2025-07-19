import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Turno {
    public void executar(List<Player> jogadores) {
        // Ordena todos os jogadores vivos pela velocidade (maior primeiro)
        jogadores.sort(Comparator.comparingInt(Player::getVelocidade).reversed());

        for (Player jogador : jogadores) {
            if (!jogador.estaVivo()) continue;

            if (jogador instanceof Heroi) {
                executarTurnoHeroi((Heroi) jogador, jogadores);
            } else if (jogador instanceof Monstro) {
                executarTurnoMonstro((Monstro) jogador, jogadores);
            }
        }
    }

    private void executarTurnoHeroi(Heroi heroi, List<Player> jogadores) {
        for (Player jogador : jogadores) {
            if (jogador instanceof Monstro && jogador.estaVivo()) {
                ResultadoAtaque resultado = heroi.realizarAtaque(jogador);

                // Log detalhado
                String logMsg = heroi.getNome() + " atacou " + jogador.getNome() + " - " + resultado;
                if (!jogador.estaVivo()) {
                    logMsg += " [MORTE]";
                } else {
                    logMsg += " [HP restante: " + (int) jogador.getHP() + "]";
                }

                Game.getInstance().getLog().registrar(logMsg);
                break;
            }
        }
    }

    private void executarTurnoMonstro(Monstro monstro, List<Player> jogadores) {
        List<Heroi> herois = new ArrayList<>();
        for (Player jogador : jogadores) {
            if (jogador instanceof Heroi && jogador.estaVivo()) {
                herois.add((Heroi) jogador);
            }
        }

        Heroi alvo = monstro.decidirAlvo(herois);
        if (alvo != null) {
            ResultadoAtaque resultado = monstro.realizarAtaque(alvo);

            // Log detalhado
            String logMsg = monstro.getNome() + " atacou " + alvo.getNome() + " - " + resultado;
            if (!alvo.estaVivo()) {
                logMsg += " [MORTE]";
            } else {
                logMsg += " [HP restante: " + (int) alvo.getHP() + "]";
            }

            Game.getInstance().getLog().registrar(logMsg);
        }
    }
}
