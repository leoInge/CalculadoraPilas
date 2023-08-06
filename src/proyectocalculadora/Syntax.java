package proyectocalculadora;
import java.util.Stack;

public class Syntax {

    public Syntax() {}

    public boolean isCorrect(String operation) {
        if (!haveLetters(operation)) {
            Stack<Character> aux = new Stack<>();
            for (int i = 0; i < operation.length(); ++i) {
                if (operation.charAt(i) == '(') {
                    aux.push(operation.charAt(i));
                } else if (operation.charAt(i) == ')') {
                    if (!aux.isEmpty()) {
                        aux.pop();
                    } else {
                        return false;
                    }
                }
            }
            return aux.isEmpty();
        } else {
            return false;
        }
    }

    private boolean haveLetters(String operation) {
        if (!operation.isEmpty()) {
            for (int i = 0; i < operation.length(); ++i) {
                if (Character.isLetter(operation.charAt(i))) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

   
}

