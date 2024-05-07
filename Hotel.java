import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Hotel {
    // Fila de espera para os hóspedes
    private final Queue<Hospede> filaDeEspera = new ConcurrentLinkedQueue<>();
    // Lista de quartos do hotel
    private final List<Quarto> quartos;
    // Contador de reclamações
    private int complaints = 0;
    // Lista de recepcionistas do hotel
    private final List<Recepcionista> recepcionistas;
    // Array para representar o estado das chaves dos quartos
    private final boolean[] chaves;

    public Hotel() {
        // Inicialização da lista de quartos
        quartos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            quartos.add(new Quarto());
        }
        
        // Inicialização da lista de recepcionistas
        recepcionistas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // Criação e inicialização de cada recepcionista
            Recepcionista recepcionista = new Recepcionista(this);
            recepcionistas.add(recepcionista);
            recepcionista.start(); // Inicia a thread do recepcionista
        }
        
        // Inicialização do array de chaves
        chaves = new boolean[quartos.size() + 1]; 
    }
    
    // Retorna o próximo hóspede na fila de espera para check-in
    public Hospede getHospedeParaCheckIn() {
        return filaDeEspera.poll();
    }

    // Incrementa o contador de reclamações
    public void addComplaint() {
        complaints++;
    }

    // Retorna o próximo quarto a ser limpo
    public Quarto getQuartoParaLimpar() {
        for (Quarto quarto : quartos) {
            if (quarto.isChaveNaRecepcao() && !quarto.isLimpo() && !quarto.isEmLimpeza()) {
                return quarto;
            }
        }
        return null;
    }

    // Retorna o número de reclamações
    public int getComplaints() {
        return complaints;
    }

    // Retorna o estado das chaves dos quartos
    public boolean[] getChaves() {
        return this.chaves;
    }

    // Retorna a lista de quartos do hotel
    public List<Quarto> getQuartos() {
        return this.quartos;
    }

    // Verifica se há recepcionistas livres
    public boolean haRecepcionistasLivres() {
        return this.recepcionistas.stream()
            .map(Recepcionista::isOcupada)
            .toList().contains(Boolean.FALSE);
    }

    // Retorna um recepcionista disponível
    public Optional<Recepcionista> getRecepcionistaDisponivel() {
        Optional<Recepcionista> recepcionista = this.recepcionistas.stream()
            .filter(recep -> !recep.isOcupada())
            .findAny();
        recepcionista.ifPresent(recep -> recep.atenderHospede());
        return recepcionista;
    }

    // Verifica se há quartos disponíveis
    public boolean haQuartosDisponiveis(){
        return this.getQuartos().stream()
            .map(Quarto::isOcupado)
            .toList().contains(Boolean.FALSE);
    }

    // Retorna um quarto disponível
    public Optional<Quarto> getQuartoDisponivel() {
        return this.getQuartos().stream()
            .filter(quarto -> !quarto.isOcupado())
            .findAny();
    }
}
