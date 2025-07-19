    import java.util.List;
    import java.util.ArrayList;

    public abstract class Monstro extends Player {
        public Monstro(String nome, double hp, double forcaAtaque, double defesa, int destreza, int velocidade) {
            super(nome, hp, forcaAtaque, defesa, destreza, velocidade);
        }

        // Implementação da lógica de ataque genérica para monstros.
        // A estrutura é parecida com a dos heróis, mas com parâmetros ajustados para deixar
        // os monstros um pouco menos precisos e perigosos que os heróis, equilibrando o jogo.
        @Override
        public ResultadoAtaque realizarAtaque(Player alvo) {

            // Primeiro, verifica se o alvo é válido (não nulo) e ainda está vivo.
            // Isso evita que o monstro ataque um personagem derrotado ou inexistente.
            if (alvo == null || !alvo.estaVivo()) return ResultadoAtaque.ERROU;

            // ============================================
            // 1. Cálculo da chance de acerto do ataque
            // ============================================
            // Começa com uma chance base de 40%.
            // Cada ponto de destreza do monstro adiciona 2%.
            // Ex: destreza 8 → 40 + (8×2) = 56%
            // O valor é limitado a 85% para garantir que o monstro possa errar.
            int chanceAcerto = Math.min(85, 40 + destreza * 2);

            // Gera um número aleatório entre 0 e 99 (inclusive), simulando uma rolagem de dado.
            int roll = (int) (Math.random() * 100);

            // Se a rolagem for maior ou igual à chance de acerto, o ataque falha.
            // Mesmo monstros fortes podem errar, o que traz imprevisibilidade ao combate.
            if (roll >= chanceAcerto) return ResultadoAtaque.ERROU;

            // ============================================
            // 2. Verificação de ataque crítico
            // ============================================
            // Chance de crítico começa em 3%, com leve bônus por destreza.
            // Cada 12 pontos de destreza adicionam +1%.
            // Ex: destreza 12 → 3 + (12 / 12) = 4%
            // O valor é limitado a 20% para evitar críticos muito frequentes em monstros.
            int chanceCritico = Math.min(20, 3 + destreza / 12);

            // Se o mesmo valor da rolagem for menor ou igual à chance de crítico,
            // o ataque é considerado um golpe crítico.

            // Se for um ataque crítico, mata o alvo instantaneamente.
            if (roll <= chanceCritico) {
                alvo.setHP(0); // Insta kill
                return ResultadoAtaque.CRITICO;
            }

            // ============================================
            // 3. Dano normal (não crítico)
            // ============================================
            // Dano comum é simplesmente a força de ataque menos a defesa do oponente.
            double dano = forcaAtaque - alvo.getDefesa();

            // Como sempre, o dano mínimo é 1, e o HP não pode ir abaixo de 0.
            alvo.setHP(Math.max(0, alvo.getHP() - Math.max(1, dano)));

            // Retorna ACERTOU como resultado do ataque.
            return ResultadoAtaque.ACERTOU;
        }


        // Lógica de IA simples para o monstro decidir qual herói será atacado.
        // O monstro escolhe o herói com menor HP entre os que ainda estão vivos.
        // Isso cria uma lógica de "foco no mais fraco", típica de uma IA ingênua mas eficaz.
        public Heroi decidirAlvo(List<Heroi> herois) {

            // Primeiro, remove da lista todos os heróis que já estão mortos (HP <= 0).
            // Isso evita que o monstro tente atacar alguém já derrotado.
            herois.removeIf(h -> !h.estaVivo());

            // Se não restar nenhum herói vivo, retorna null.
            // Isso permite ao jogo saber que a luta acabou ou que o monstro não tem alvos válidos.
            if (herois.isEmpty()) return null;

            // Começa assumindo que o primeiro herói da lista é o alvo.
            Heroi alvo = herois.get(0);

            // Percorre todos os heróis vivos e compara os HPs.
            // Sempre que encontra um herói com HP menor que o atual alvo, ele passa a ser o novo alvo.
            for (Heroi h : herois) {
                if (h.getHP() < alvo.getHP()) {
                    alvo = h;
                }
            }

            // Ao final do laço, `alvo` será o herói mais enfraquecido da lista.
            return alvo;

            // ------------------------------
            // POSSÍVEL MELHORIA FUTURA:
            // Podemos evoluir essa IA para considerar outros critérios além do menor HP,
            // como:
            // - Menor defesa (para facilitar dano),
            // - Maior ataque (para eliminar heróis mais perigosos),
            // - Aleatoriedade controlada para dificultar previsibilidade do jogador,
            // - Peso por classe (focar curandeiros ou magos, por exemplo).
            // Isso aumentaria a complexidade e inteligência do comportamento do monstro.
            // ------------------------------
        }

    }
