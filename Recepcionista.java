//import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

// Definição da classe Recepcionista que estende a classe Thread
public class Recepcionista extends Thread {
    // Atributos da classe Recepcionista
    private final Hotel hotel; // Referência ao hotel em que o recepcionista trabalha
    private boolean ocupada = false; // Indica se o recepcionista está ocupado atendendo um hóspede


     // Construtor da classe Recepcionista
     public Recepcionista(Hotel hotel) {
        this.hotel = hotel; // Inicializa o atributo hotel com a instância passada como parâmetro
    }

    // Método run(), que é executado quando a thread do recepcionista é iniciada
    @Override
    public void run() {
        // Loop infinito para simular o funcionamento contínuo do recepcionista
        while (true) {         
            try {
                // Aguarda 1 segundo antes de verificar novamente
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime o stack trace em caso de interrupção
            }
        }
    }

    List<Grupo> grupos = new ArrayList<>();
     // Método para verificar se o recepcionista tem a chave de um determinado grupo
     public synchronized boolean temChave(int grupo) {
        // Verifica se o número do grupo está dentro do intervalo válido
        if (grupo <= 13 && grupo > 0) {
            return hotel.getChaves()[grupo]; // Retorna true se o recepcionista tiver a chave do grupo
        }
        return false; // Retorna false se o número do grupo estiver fora do intervalo válido
    }

    // Método para entregar a chave de um quarto para um grupo de hóspedes
    public synchronized boolean entregarChave(Grupo grupo) {
        // Verifica se há quartos disponíveis no hotel
        if(!hotel.haQuartosDisponiveis()){
            System.out.println("Não há quartos disponíveis"); // Imprime uma mensagem informando que não há quartos disponíveis
            return false; // Retorna false indicando que a entrega da chave não foi bem-sucedida
        }
         // Obtém um quarto disponível e atribui ao grupo de hóspedes
         Quarto quartoDisponivel = hotel.getQuartoDisponivel().get();
         grupo.setQuarto(quartoDisponivel); // Define o quarto para o grupo
         quartoDisponivel.alocar(grupo); // Aloca o grupo no quarto
         // Define que o recepcionista tem a chave do grupo (usando uma lógica específica)
         hotel.getChaves()[grupo.getId() % 10] = true;
         return true; // Retorna true indicando que a entrega da chave foi bem-sucedida
     }

     // Método para devolver a chave de um quarto
     public synchronized void devolverChave(int grupo) {
        // Verifica se o número do grupo está dentro do intervalo válido
        if (grupo <= 13 && grupo > 0) {
            hotel.getChaves()[grupo] = false; // Define que o recepcionista não tem mais a chave do grupo
        }
    }

    // Método para indicar que o recepcionista está atendendo um hóspede
    public synchronized void atenderHospede() {
        this.ocupada = true; // Define que o recepcionista está ocupado
    }

    // Método para indicar que o recepcionista terminou de atender um hóspede
    public synchronized void terminouDeAtenderHospede() {
        this.ocupada = false; // Define que o recepcionista não está mais ocupado
    }

    // Método para verificar se o recepcionista está ocupado
    public boolean isOcupada() {
        return this.ocupada; // Retorna true se o recepcionista estiver ocupado, false caso contrário
    }
}