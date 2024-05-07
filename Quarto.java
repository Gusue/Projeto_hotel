import java.util.concurrent.locks.*;
import java.util.List;
import java.util.ArrayList;

// Definição da classe Quarto
public class Quarto {
    // Atributos da classe Quarto
    private final Lock lock = new ReentrantLock(); // Lock para sincronização
    private boolean ocupado = false; // Indica se o quarto está ocupado
    private List<Hospede> hospedes = new ArrayList<>(); // Lista de hóspedes no quarto
    private boolean chaveNaRecepcao = true; // Indica se a chave do quarto está na recepção
    private boolean limpo = true; // Indica se o quarto está limpo
    private boolean emLimpeza = false; // Indica se o quarto está em processo de limpeza
    private Grupo grupoAtual = null; // Grupo atualmente hospedado no quarto

    // Método para alocar um grupo no quarto
    public void alocar(Grupo grupo) {
        lock.lock(); // Bloqueia o acesso ao quarto
        try {
            if (!ocupado && hospedes.size() < 4 && !emLimpeza) { // Verifica se o quarto está disponível
                ocupado = true; // Define que o quarto está ocupado
                hospedes.add(grupo.getIntegrantes().get(0)); // Adiciona o primeiro hóspede do grupo ao quarto
                chaveNaRecepcao = false; // Define que a chave não está na recepção
                grupoAtual = grupo; // Define o grupo atualmente hospedado no quarto
            } else {
                // Quarto já está ocupado, cheio ou em limpeza
            }
        } finally {
            lock.unlock(); // Libera o acesso ao quarto
        }
    }

    // Método para desocupar o quarto
    public void desocupar(Hospede hospede) {
        lock.lock(); // Bloqueia o acesso ao quarto
        try {
            if (ocupado) { // Verifica se o quarto está ocupado
                ocupado = false; // Define que o quarto está desocupado
                hospedes.remove(hospede); // Remove o hóspede do quarto
                chaveNaRecepcao = true; // Define que a chave está na recepção
                grupoAtual = null; // Define que não há grupo hospedado no quarto
            } else {
                // Quarto já está desocupado
            }
        } finally {
            lock.unlock(); // Libera o acesso ao quarto
        }
    }

    // Método para registrar que o grupo saiu para passear
    public void sairParaPassear() {
        lock.lock(); // Bloqueia o acesso ao quarto
        try {
            if (ocupado) { // Verifica se o quarto está ocupado
                chaveNaRecepcao = true; // Define que a chave está na recepção
                System.out.println("Grupo " + grupoAtual + " saiu para passear");
                grupoAtual = null; // Define que não há grupo hospedado no quarto
            } else {
                // Quarto já está desocupado
            }
        } finally {
            lock.unlock(); // Libera o acesso ao quarto
        }
    }

    // Método para registrar que o grupo voltou do passeio
    public void voltarDoPasseio(Hospede hospede) {
        lock.lock(); // Bloqueia o acesso ao quarto
        try {
            if (!ocupado && chaveNaRecepcao) { // Verifica se o quarto está desocupado e se a chave está na recepção
                chaveNaRecepcao = false; // Define que a chave não está na recepção
                grupoAtual = hospede.getGrupo(); // Define o grupo que voltou do passeio como o grupo atualmente hospedado no quarto
                System.out.println("Grupo " + grupoAtual + " voltou do passeio");
            } else {
                // Quarto está ocupado ou a chave não está na recepção
            }
        } finally {
            lock.unlock(); // Libera o acesso ao quarto
        }
    }

    // Outros métodos

    // Getter para o grupo atualmente hospedado no quarto
    public Grupo getGrupoAtual() {
        return grupoAtual;
    }

    // Getter para verificar se o quarto está limpo
    public boolean isLimpo() {
        return limpo;
    }

    // Getter para verificar se a chave do quarto está na recepção
    public boolean isChaveNaRecepcao() {
        return chaveNaRecepcao;
    }

    // Método para realizar a limpeza do quarto
    public void limpar() {
        if (!ocupado && chaveNaRecepcao) { // Verifica se o quarto está desocupado e se a chave está na recepção
            emLimpeza = true; // Define que o quarto está em processo de limpeza
            System.out.println("Quarto em limpeza");
            try {
                Thread.sleep(2000); // Simula o tempo de limpeza
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            limpo = true; // Define que o quarto está limpo
            emLimpeza = false; // Define que o quarto não está mais em processo de limpeza
        } else {
            // Quarto está ocupado ou a chave não está na recepção
        }
    }

    // Getter para verificar se o quarto está em processo de limpeza
    public boolean isEmLimpeza() {
        return emLimpeza;
    }

    // Getter para verificar se o quarto está ocupado
    public boolean isOcupado() {
        return ocupado;
    }

    // Getter para obter a lista de hóspedes no quarto
    public List<Hospede> getHospedes() {
        return hospedes;
    }
}
