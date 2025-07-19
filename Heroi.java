public abstract class Heroi extends Player {
    public Heroi(String nome, double hp, double forcaAtaque, double defesa, int destreza, int velocidade) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
    }

    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {
        if (alvo == null || !alvo.estaVivo()) return ResultadoAtaque.ERROU;
        int chanceAcerto = Math.min(90, 50 + destreza * 2);
        int roll = (int) (Math.random() * 100);
        if (roll >= chanceAcerto) return ResultadoAtaque.ERROU;
        int chanceCritico = Math.min(25, 5 + destreza / 10);
        if (roll <= chanceCritico) {
            double danoCritico = forcaAtaque * 2 - alvo.getDefesa();
            alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, danoCritico)));
            return ResultadoAtaque.CRITICO;
        }
        double dano = forcaAtaque - alvo.getDefesa();
        alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, dano)));
        return ResultadoAtaque.ACERTOU;
    }
}
