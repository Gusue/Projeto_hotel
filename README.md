Sistema de Reserva e Controle de Quartos em um Hotel
Este projeto em Java 17 simula um sistema de reserva e controle de quartos em um hotel, utilizando threads para representar diferentes entidades, como hóspedes, camareiras e recepcionistas. O sistema abrange várias regras e situações, proporcionando uma simulação abrangente do funcionamento de um hotel.

Entidades Representadas
Quarto
O sistema conta com no mínimo 10 quartos.
Cada quarto tem capacidade para até 4 hóspedes e uma única chave.
Hóspede
Cada hóspede é representado por uma thread.
No mínimo, existem 50 hóspedes.
Caso um grupo ou família possua mais de 4 membros, eles são divididos em vários quartos.
Camareira
Cada camareira é representada por uma thread.
No mínimo, existem 10 camareiras.
Recepcionista
Cada recepcionista é representado por uma thread.
No mínimo, existem 5 recepcionistas.
Regras do Sistema
Os recepcionistas alocam hóspedes apenas em quartos vagos.
Quando os hóspedes saem para passear, devem deixar a chave na recepção.
Uma camareira só pode limpar um quarto vago ou quando os hóspedes não estão nele.
A limpeza dos quartos é feita sempre após a saída dos hóspedes.
Um quarto vago que passa por limpeza não pode ser alocado para um novo hóspede imediatamente.
Hóspedes que não encontram quartos vagos esperam em uma fila.
Se a espera por um quarto demorar muito, o hóspede passeia pela cidade e tenta novamente.
Se um hóspede tenta alugar um quarto duas vezes sem sucesso, ele deixa uma reclamação e vai embora.
Observações
Não é possível que apenas parte dos hóspedes de um quarto saiam para passear.
A implementação deve ser abrangente e simular várias situações, como diferentes números de hóspedes, grupos grandes, quartos lotados, etc.
Implementação
O código Java implementa todas essas regras e simulações, garantindo sincronia e coordenação entre as entidades do sistema.

Este projeto foi desenvolvido como parte da atividade prática coletiva do bimestre N1 da disciplina T3.
