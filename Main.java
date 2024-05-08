/* Não consegui fazer todas as funcionalidades rodarem porém tentei criar uma lógica para cada uma delas em suas respectivas classes */

import java.util.ArrayList;
import java.util.List;

// Classe principal Main
public class Main {

    // Método main
    public static void main(String[] args) {
        // Criação do hotel
        Hotel hotel = new Hotel();
        List<Grupo> grupos = new ArrayList<>();
        
        // Criação de 13 grupos de hóspedes
        for(int i = 1; i < 14; i++) {
            grupos.add(new Grupo(i));
        }

        // Criação de 50 hóspedes divididos nos grupos
        List<Hospede> hospedes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int grupo = i % 13; // Distribuição dos hóspedes pelos grupos
            Hospede hospede = new Hospede(i, grupos.get(grupo), hotel);
            hospedes.add(hospede);
            hospede.start(); // Inicia a thread do hóspede
            try {
                Thread.sleep(250); // Aguarda um tempo antes de criar o próximo hóspede
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Criação de 10 camareiras
        List<Camareira> camareiras = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Camareira camareira = new Camareira(hotel);
            camareiras.add(camareira);
            camareira.start(); // Inicia a thread da camareira
        }

        // Criação de 5 recepcionistas
        List<Recepcionista> recepcionistas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Recepcionista recepcionista = new Recepcionista(hotel);
            recepcionistas.add(recepcionista);
            recepcionista.start(); // Inicia a thread do recepcionista
        }
    }
}
