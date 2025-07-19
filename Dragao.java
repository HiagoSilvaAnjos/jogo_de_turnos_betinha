public class Dragao extends Monstro {
    public Dragao(Dificuldade dificuldade, String nomePersonalizado) {
        super(nomePersonalizado,
              150 + (dificuldade.ordinal() * 30), 
              35 + (dificuldade.ordinal() * 8), 
              20 + (dificuldade.ordinal() * 5), 
              6 + (dificuldade.ordinal() * 2), 
              5 + dificuldade.ordinal());
    }

    public Dragao(Dificuldade dificuldade) {
        this(dificuldade, "Dragão");
    }
}
