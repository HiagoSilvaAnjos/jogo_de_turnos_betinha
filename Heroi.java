public abstract class Heroi extends Player {
    public Heroi(String nome, double hp, double forcaAtaque, double defesa, int destreza, int velocidade) {
        super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
    }

    // Implementação da lógica de ataque genérica para heróis.
    // Essa função define como qualquer herói (mago, guerreiro etc.) ataca um inimigo.
    // A lógica equilibra estratégia (atributos como destreza e força) com aleatoriedade (rolagem de dado).
    @Override
    public ResultadoAtaque realizarAtaque(Player alvo) {

        // Primeiro, valida se o alvo é válido (não nulo) e ainda está vivo.
        // Isso evita erros lógicos como atacar alguém que já foi derrotado ou não existe.
        if (alvo == null || !alvo.estaVivo()) return ResultadoAtaque.ERROU;

        // ============================================
        // 1. Cálculo da chance de acerto do ataque
        // ============================================
        // Começa com uma chance base de 50%.
        // Cada ponto de destreza adiciona 2% à chance de acerto.
        // Ex: destreza 10 → 50 + (10×2) = 70%
        // Mas a chance máxima é limitada a 90% para garantir que
        // nenhum herói seja infalível, mantendo a imprevisibilidade do jogo.
        int chanceAcerto = Math.min(90, 50 + destreza * 2);

        // Rola um número aleatório entre 0 e 99 (inclusive), simulando uma rolagem de dado de 100 lados.
        int roll = (int) (Math.random() * 100);

        // Se o número rolado for maior ou igual à chance de acerto, o ataque erra.
        // Isso introduz incerteza e emoção no combate — mesmo um herói com 90% de chance pode errar.
        if (roll >= chanceAcerto) return ResultadoAtaque.ERROU;

        // ============================================
        // 2. Verificação de ataque crítico
        // ============================================
        // Calcula a chance de causar um ataque crítico.
        // Começa em 5% e aumenta levemente com a destreza: cada 10 pontos dá +1%.
        // Ex: destreza 20 → 5 + (20/10) = 7%
        // O valor máximo é limitado a 25% para evitar que críticos sejam comuns demais.
        int chanceCritico = Math.min(25, 5 + destreza / 10);

        // Se a mesma rolagem que definiu o acerto for menor ou igual à chance crítica,
        // considera-se um acerto crítico. Isso reaproveita o valor `roll`, dando a sensação
        // de que um número baixo é “melhor” para o atacante (baixa rolagem = mais sorte).
        if (roll <= chanceCritico) {
            // O dano crítico é o dobro da força de ataque, menos a defesa do alvo.
            // Isso representa um golpe mais poderoso, que ignora parcialmente a resistência do oponente.
            double danoCritico = forcaAtaque * 2 - alvo.getDefesa();

            // Protege contra dano negativo: se a defesa for muito alta, ainda assim causa pelo menos 1 de dano.
            // Também impede que o HP do alvo fique abaixo de 0.
            alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, danoCritico)));

            // Retorna o resultado do ataque como CRÍTICO.
            return ResultadoAtaque.CRITICO;
        }

        // ============================================
        // 3. Dano normal (não crítico)
        // ============================================
        // O dano padrão é a força de ataque menos a defesa do alvo.
        double dano = forcaAtaque - alvo.getDefesa();

        // Garante que o dano mínimo seja sempre 1 (evita ataques inúteis),
        // e também impede que o HP vá abaixo de 0 (sem vida negativa).
        alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, dano)));

        // Retorna o resultado do ataque como ACERTO NORMAL.
        return ResultadoAtaque.ACERTOU;
    }

}
