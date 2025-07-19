import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<String> eventos;

    public Log() {
        this.eventos = new ArrayList<>();
    }

    public void registrar(String evento) {
        eventos.add(evento);
    }

    public void mostrarLog() {
        for (String evento : eventos) {
            System.out.println(evento);
        }
    }

    public List<String> getEventos() {
        return new ArrayList<>(eventos);
    }
}
