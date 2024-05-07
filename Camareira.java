// Definição da classe Camareira que estende a classe Thread
public class Camareira extends Thread {
    // Atributo da classe Camareira
    private final Hotel hotel; // Referência ao hotel em que a camareira trabalha

    // Construtor da classe Camareira
    public Camareira(Hotel hotel) {
        this.hotel = hotel; // Inicializa o atributo hotel com a instância passada como parâmetro
    }

    // Método run(), que é executado quando a thread da camareira é iniciada
    @Override
    public void run() {
        // Loop infinito para simular o funcionamento contínuo da camareira
        while (true) {
            // Obtém o próximo quarto para limpar do hotel
            Quarto quartoParaLimpar = hotel.getQuartoParaLimpar();

            // Verifica se há um quarto disponível para limpeza e se a chave está na recepção
            if (quartoParaLimpar != null && quartoParaLimpar.isChaveNaRecepcao()) {
                quartoParaLimpar.limpar(); // Realiza a limpeza do quarto
            }

            try {
                Thread.sleep(1000); // Aguarda 1 segundo antes de verificar novamente
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime o stack trace em caso de interrupção
            }
        }
    }
}
