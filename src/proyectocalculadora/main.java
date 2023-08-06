package proyectocalculadora;
import java.util.Scanner;



public class main {

    public static void main(String[] args) {

        Convert_EvaluateToPosfix cep = new Convert_EvaluateToPosfix();
        ExpressionValidation ex = new ExpressionValidation();

        Scanner scanner = new Scanner(System.in);
        String equation;

        System.out.println("********************************************");
        System.out.println("| Ingresa la Ecuacion que quieres resolver |");
        System.out.println("********************************************");
        equation = scanner.nextLine();

        if (!ex.balanceEquation(equation)) {
            System.out.println("---->Los parentesis de la ecuacion no estan balanceados");
        }

        if (!ex.validateDigits(equation)) {
            System.out.println("---->Se deben ingresar solamente valores numericos");
        }

        if (!ex.validateOperators(equation)) {
            System.out.println("---->Hay operaciones no validas");
        }

        while (true) {
            if (ex.balanceEquation(equation) && ex.validateDigits(equation) && ex.validateOperators(equation)) {
                System.out.print("Ecuacion postfija:");
                for (String s : cep.infixToPostfix(equation)) {
                    System.out.print(s);
                }
                System.out.println();
                System.out.println("El resultado de la operacion es: " + cep.result(cep.infixToPostfix(equation)));

                System.out.println("Quieres salir del programa?");
                System.out.println("--->1.Si");
                System.out.println("--->2.No");

                int option = scanner.nextInt();
                if (option == 1) {
                    break;
                }
            }

            System.out.println("*******************************************************");
            System.out.println("| Ingresa nuevamente la ecuacion que quieres resolver |");
            System.out.println("*******************************************************");
            scanner.nextLine(); // Limpiar el buffer
            equation = scanner.nextLine();

            if (!ex.balanceEquation(equation)) {
                System.out.println("---->Los parentesis de la ecuacion no estan balanceados");
            }

            if (!ex.validateDigits(equation)) {
                System.out.println("---->Se deben ingresar solamente valores numericos");
            }

            if (!ex.validateOperators(equation)) {
                System.out.println("---->Hay operadores estan repetidos");
            }
        }
        System.out.println("Programa finalizado correctamente ;)");
    }
}
