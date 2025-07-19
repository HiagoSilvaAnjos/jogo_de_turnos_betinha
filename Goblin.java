public class Goblin extends Monstro {
    public Goblin(Dificuldade dificuldade) {
        super("Goblin", 
              40 + (dificuldade.ordinal() * 15), 
              15 + (dificuldade.ordinal() * 4), 
              4 + (dificuldade.ordinal() * 2), 
              8 + (dificuldade.ordinal() * 2), 
              7 + (dificuldade.ordinal() * 2));
    }
}
