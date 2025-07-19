import java.util.List;
import java.util.ArrayList;

public abstract class Monstro extends Player {
    public Monstro(String nome, double hp, double forcaAtaque, double defesa, int destreza, int velocidade) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        if (alvo == null || !alvo.estaVivo()) return ResultadoAtaque.ERROU;
        int chanceAcerto = Math.min(85, 40 + destreza * 2);
        int roll = (int) (Math.random() * 100);
        if (roll >= chanceAcerto) return ResultadoAtaque.ERROU;
        int chanceCritico = Math.min(20, 3 + destreza / 12);
        if (roll <= chanceCritico) {
            double danoCritico = forcaAtaque * 1.8 - alvo.getDefesa();
            alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, danoCritico)));
            return ResultadoAtaque.CRITICO;
        }
        double dano = forcaAtaque - alvo.getDefesa();
        alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, dano)));
        return ResultadoAtaque.ACERTOU;
    }

    public Heroi decidirAlvo(List<Heroi> herois) {
        herois.removeIf(h -> !h.estaVivo());
        if (herois.isEmpty()) return null;
        Heroi alvo = herois.get(0);
        for (Heroi h : herois) {
            if (h.getHP() < alvo.getHP()) alvo = h;
        }
        return alvo;
    }
}
