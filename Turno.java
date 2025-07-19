import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Turno {
    public void executar(List<Player> jogadores) {
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
                Game.getInstance().getLog().registrar(
                    heroi.getNome() + " atacou " + jogador.getNome() + " - " + resultado);
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
            Game.getInstance().getLog().registrar(
                monstro.getNome() + " atacou " + alvo.getNome() + " - " + resultado);
        }
    }
}
