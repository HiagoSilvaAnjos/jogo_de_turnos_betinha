public class Goblin extends Monstro {
    public Goblin(Dificuldade dificuldade,  String nomePersonalizado) {
        super(nomePersonalizado,
              40 + (dificuldade.ordinal() * 15), 
              15 + (dificuldade.ordinal() * 4), 
              4 + (dificuldade.ordinal() * 2), 
              8 + (dificuldade.ordinal() * 2), 
              7 + (dificuldade.ordinal() * 2));
    }

    public Goblin(Dificuldade dificuldade) {
        this(dificuldade, "Goblin");
    };
}
