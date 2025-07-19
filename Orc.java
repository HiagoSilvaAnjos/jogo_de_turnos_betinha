public class Orc extends Monstro {
    public Orc(Dificuldade dificuldade) {
        super("Orc", 
              60 + (dificuldade.ordinal() * 20), 
              18 + (dificuldade.ordinal() * 5), 
              8 + (dificuldade.ordinal() * 2), 
              4 + dificuldade.ordinal(), 
              3 + dificuldade.ordinal());
    }
}
