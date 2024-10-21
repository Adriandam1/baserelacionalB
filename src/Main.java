import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0; // Inicializar opcion

        do {
            System.out.println("----- XESTIÓN DE PRODUTOS -----");
            System.out.println("1. Listar produtos");
            System.out.println("2. Actualizar produto");
            System.out.println("3. Inserir produto");
            System.out.println("4. Borrar produto");
            System.out.println("5. Sair");
            System.out.print("Elige unha opción: ");

            try {
                opcion = sc.nextInt(); // Intentar leer la opción
                sc.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        Metodos.listarProdutos();
                        break;
                    case 2:
                        Metodos.actualizarProduto();
                        break;
                    case 3:
                        Metodos.inserirProduto();
                        break;
                    case 4:
                        Metodos.borrarProduto();
                        break;
                    case 5:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opción non válida, por favor intenta de novo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Por favor, introduce un número válido."); // Mensaje de error para entrada no válida
                sc.nextLine(); // Limpiar el buffer de Scanner
            }

        } while (opcion != 5);

        sc.close(); // Cerrar el scanner al finalizar
    }
}
