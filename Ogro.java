public class Ogro extends Monstro {
    public Ogro(Dificuldade dificuldade, String nomePersonalizado) {
        super(nomePersonalizado,
              60 + (dificuldade.ordinal() * 20), 
              18 + (dificuldade.ordinal() * 5), 
              8 + (dificuldade.ordinal() * 2), 
              4 + dificuldade.ordinal(), 
              3 + dificuldade.ordinal());
    }

    public Ogro(Dificuldade dificuldade) {
        this(dificuldade, "Ogro");
    }
}
