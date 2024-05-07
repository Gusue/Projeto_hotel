import java.util.List;
import java.util.ArrayList;

// Definição da classe Grupo
public class Grupo {
    // Atributos da classe Grupo
    private int id; // Identificador do grupo
    private boolean chavePega = false; // Indica se a chave do grupo foi retirada da recepção
    private List<Hospede> integrantes = new ArrayList<>(); // Lista de integrantes do grupo
    private Quarto quarto; // Quarto atribuído ao grupo

    // Construtor da classe Grupo
    public Grupo(int id){
        this.id = id; // Inicializa o identificador do grupo com o valor passado como parâmetro
    }

    // Getter para o identificador do grupo
    public int getId(){
        return this.id; // Retorna o identificador do grupo
    }

    // Setter para o identificador do grupo
    public void setId(int id){
        this.id = id; // Define o identificador do grupo com o valor passado como parâmetro
    }

    // Getter para verificar se a chave do grupo foi retirada da recepção
    public boolean isChavePega() {
        return this.chavePega; // Retorna true se a chave do grupo foi retirada da recepção, false caso contrário
    }

    // Setter para definir se a chave do grupo foi retirada da recepção
    public void setChavePega(boolean chavePega) {
        this.chavePega = chavePega; // Define se a chave do grupo foi retirada da recepção
    }

    // Getter para obter a lista de integrantes do grupo
    public List<Hospede> getIntegrantes(){
        return this.integrantes; // Retorna a lista de integrantes do grupo
    }

    // Método para adicionar um hospede à lista de integrantes do grupo
    public void addIntegrante(Hospede hospede){
        this.integrantes.add(hospede); // Adiciona o hospede à lista de integrantes do grupo
    }

    // Getter para obter o quarto atribuído ao grupo
    public Quarto getQuarto() {
        return this.quarto; // Retorna o quarto atribuído ao grupo
    }

    // Setter para definir o quarto atribuído ao grupo
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto; // Define o quarto atribuído ao grupo
    }
    
    // Sobrescrita do método toString para retornar o nome do grupo
    @Override
    public String toString() {
        return "grupo" + this.id; // Retorna o nome do grupo
    }
}
