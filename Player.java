public abstract class Player {
    protected String nome;
    protected double hp;
    protected double forcaAtaque;
    protected double defesa;
    protected int destreza;
    protected int velocidade;

    public Player(String nome, double hp, double forcaAtaque, double defesa, int destreza, int velocidade) {
        this.nome = nome;
        this.hp = hp;
        this.forcaAtaque = forcaAtaque;
        this.defesa = defesa;
        this.destreza = destreza;
        this.velocidade = velocidade;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getHP() { return hp; }
    public void setHP(double hp) { this.hp = hp; }

    public double getForcaAtaque() { return forcaAtaque; }
    public void setForcaAtaque(double forcaAtaque) { this.forcaAtaque = forcaAtaque; }

    public double getDefesa() { return defesa; }
    public void setDefesa(double defesa) { this.defesa = defesa; }

    public int getDestreza() { return destreza; }
    public void setDestreza(int destreza) { this.destreza = destreza; }

    public int getVelocidade() { return velocidade; }
    public void setVelocidade(int velocidade) { this.velocidade = velocidade; }

    public abstract ResultadoAtaque realizarAtaque(Player alvo);

    public boolean estaVivo() {
        return hp > 0;
    }
}
