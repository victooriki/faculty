import java.util.Scanner;

class Aviao {
    int numero;
    String origem;
    String destino;
    int assentosDisponiveis;

    public Aviao(int numero, String origem, String destino, int assentosDisponiveis) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.assentosDisponiveis = assentosDisponiveis;
    }
}

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int totalAviões = 12;
        Aviao[] avioes = new Aviao[totalAviões];

        preencherVoos(input, avioes);

        int escolha;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Consultar voos");
            System.out.println("2. Efetuar reserva");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    consultarVoos(input, avioes);
                    break;
                case 2:
                    efetuarReserva(input, avioes);
                    break;
                case 3:
                    System.out.println("Você saiu do programa!");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha novamente.");
                    break;
            }
        } while (escolha != 3);

        input.close();
    }

    public static void preencherVoos(Scanner input, Aviao[] avioes) {
        System.out.println("Preenchendo informações sobre os voos...");
        for (int i = 0; i < avioes.length; i++) {
            System.out.print("Digite o número do voo " + (i + 1) + ": ");
            int numero = input.nextInt();
            input.nextLine();

            System.out.print("Digite a origem do voo " + (i + 1) + ": ");
            String origem = input.nextLine();

            System.out.print("Digite o destino do voo " + (i + 1) + ": ");
            String destino = input.nextLine();

            System.out.print("Digite o número de assentos disponíveis do voo " + (i + 1) + ": ");
            int assentosDisponiveis = input.nextInt();

            avioes[i] = new Aviao(numero, origem, destino, assentosDisponiveis);
        }
    }

    public static void consultarVoos(Scanner input, Aviao[] avioes) {
        System.out.println("\nMenu de Consulta:");
        System.out.println("1. Consultar por número de voo");
        System.out.println("2. Consultar por origem");
        System.out.println("3. Consultar por destino");
        System.out.println("4. Voltar ao menu principal");
        System.out.print("Escolha uma opção: ");
        int escolhaConsultar = input.nextInt();
        input.nextLine();

        switch (escolhaConsultar) {
            case 1:
                System.out.print("Digite o número do voo a ser consultado: ");
                int numVoo = input.nextInt();
                consultarPorNumero(numVoo, avioes);
                break;
            case 2:
                System.out.print("Digite a origem do voo a ser consultada: ");
                String origem = input.nextLine();
                consultarPorOrigem(origem, avioes);
                break;
            case 3:
                System.out.print("Digite o destino do voo a ser consultado: ");
                String destino = input.nextLine();
                consultarPorDestino(destino, avioes);
                break;
            case 4:
                System.out.println("Voltando ao menu principal...");
                break;
            default:
                System.out.println("Opção inválida! Por favor, escolha novamente.");
                break;
        }
    }

    public static void consultarPorNumero(int numVoo, Aviao[] avioes) {
        boolean vooEncontrado = false;
        for (Aviao aviao : avioes) {
            if (aviao.numero == numVoo) {
                vooEncontrado = true;
                System.out.println("Voo encontrado:");
                System.out.println("Número do voo: " + aviao.numero);
                System.out.println("Origem: " + aviao.origem);
                System.out.println("Destino: " + aviao.destino);
                System.out.println("Assentos disponíveis: " + aviao.assentosDisponiveis);
                break;
            }
        }
        if (!vooEncontrado) {
            System.out.println("Voo inexistente.");
        }
    }

    public static void consultarPorOrigem(String origem, Aviao[] avioes) {
        boolean vooEncontrado = false;
        for (Aviao aviao : avioes) {
            if (aviao.origem.equalsIgnoreCase(origem)) {
                vooEncontrado = true;
                System.out.println("Voo encontrado:");
                System.out.println("Número do voo: " + aviao.numero);
                System.out.println("Origem: " + aviao.origem);
                System.out.println("Destino: " + aviao.destino);
                System.out.println("Assentos disponíveis: " + aviao.assentosDisponiveis);
            }
        }
        if (!vooEncontrado) {
            System.out.println("Nenhum voo encontrado com a origem especificada.");
        }
    }

    public static void consultarPorDestino(String destino, Aviao[] avioes) {
        boolean vooEncontrado = false;
        for (Aviao aviao : avioes) {
            if (aviao.destino.equalsIgnoreCase(destino)) {
                vooEncontrado = true;
                System.out.println("Voo encontrado:");
                System.out.println("Número do voo: " + aviao.numero);
                System.out.println("Origem: " + aviao.origem);
                System.out.println("Destino: " + aviao.destino);
                System.out.println("Assentos disponíveis: " + aviao.assentosDisponiveis);
            }
        }
        if (!vooEncontrado) {
            System.out.println("Nenhum voo encontrado com o destino especificado.");
        }
    }

    public static void efetuarReserva(Scanner input, Aviao[] avioes) {
        System.out.print("Digite o número do voo em que deseja efetuar a reserva: ");
        int numVoo = input.nextInt();
        boolean vooEncontrado = false;

        for (Aviao aviao : avioes) {
            if (aviao.numero == numVoo) {
                vooEncontrado = true;
                if (aviao.assentosDisponiveis > 0) {
                    aviao.assentosDisponiveis--;
                    System.out.println("Reserva confirmada! Assentos restantes no voo: " + aviao.assentosDisponiveis);
                } else {
                    System.out.println("Voo lotado! Nenhum assento disponível.");
                }
                break;
            }
        }

        if (!vooEncontrado) {
            System.out.println("Voo inexistente.");
        }
    }
}
