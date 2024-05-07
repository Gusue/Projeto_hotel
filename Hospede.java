import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Thread {
    // Identificação do hóspede
    private final int id;
    // Grupo ao qual o hóspede pertence
    private final Grupo grupo;
    // Hotel em que o hóspede está hospedado
    private final Hotel hotel;
    // Número de tentativas de check-in
    private int tentativasDeCheckIn = 0;

    public Hospede(int id, Grupo grupo, Hotel hotel) {
        this.id = id;
        this.grupo = grupo;
        this.hotel = hotel;
        this.grupo.addIntegrante(this);
    }

    @Override
    public void run() {
        while (true) {
            if (grupo.getQuarto() == null) {
                if (grupo.isChavePega() == false) 
                    pegarChave();
                    
                // List<Hospede> grupoHospedes = obterHospedesDoGrupo();
                // hotel.checkInGrupo(grupoHospedes);
                if (grupo.getQuarto() == null) {
                    // Se não houver quartos disponíveis, o grupo tentará novamente ou deixará uma reclamação e sairá
                    System.out.println("Não há quartos disponíveis para o grupo: " + grupo + ". Indo passear pela cidade e tentando novamente em breve.");
                    tentativasDeCheckIn++;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }             
                    
                    if (tentativasDeCheckIn >= 2) {
                        // Reclamação do grupo e saída
                        System.out.println("grupo: "+ grupo + " deixou uma reclamação e foi embora.");
                        hotel.addComplaint();
                        break;
                    } else {
                        // Sem quarto disponível, espera por um tempo e tenta novamente
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                
            }
        }
    }

    // Método para pegar a chave do quarto do grupo
    public void pegarChave() {
        Optional<Recepcionista> haRecepcionista = hotel.getRecepcionistaDisponivel();
        if (!grupo.isChavePega() && haRecepcionista.isPresent()) {
            Recepcionista recepcionista = haRecepcionista.get();
            if(recepcionista.entregarChave(grupo)){
                grupo.setChavePega(true);
                System.out.println(id + " pegou a chave para o " + grupo);
            }
            recepcionista.terminouDeAtenderHospede();
        } else if(!hotel.haRecepcionistasLivres()) {
            // Se não houver recepcionistas disponíveis, informa ao hóspede
            System.out.println("Não há recepcionistas para atender o hospede " + id);
        } else {
            // Se a chave já foi pega por outro membro do grupo
            System.out.println("A chave do grupo foi pega por outro membro do grupo, enquanto o hospede " + id + " tentava pegar");
        }
    }

    // Método para o hóspede sair para passear
    public void sairParaPassear() {
        if (grupo.getQuarto() != null && grupo.getQuarto().getGrupoAtual().equals(grupo)) {
            grupo.getQuarto().sairParaPassear();
        }
    }

    // Método para o hóspede voltar do passeio
    public void voltarDoPasseio() {
        if (grupo.getQuarto() != null && grupo.getQuarto().isLimpo() && grupo.getQuarto().getGrupoAtual() == null) {
            grupo.getQuarto().voltarDoPasseio(this);
        }
    }

    // Retorna o grupo ao qual o hóspede pertence
    public Grupo getGrupo() {
        return this.grupo;
    }

    // Método para obter todos os hóspedes do grupo
    public List<Hospede> obterHospedesDoGrupo() {
        return new ArrayList<Hospede>();
    }
}
