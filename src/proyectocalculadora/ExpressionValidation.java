package proyectocalculadora;
public class ExpressionValidation {
    public ExpressionValidation() {
    }

    public boolean balanceEquation(String equation) {
        Pila<Character> stack = new Pila<>();
        for (char c : equation.toCharArray()) {
            if (c == '(') {
                stack.addNode(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.deleteTop();
            }
        }
        return stack.isEmpty();
    }

    public boolean validateOperators(String equation) {
        for (int i = 0; i < equation.length() - 1; i++) {
            if ((equation.charAt(i) == '+' && equation.charAt(i + 1) == '-') ||
                    (equation.charAt(i) == '-' && equation.charAt(i + 1) == '+')) {
                return true;
            } else if (equation.charAt(i) == '+' || equation.charAt(i) == '-' || equation.charAt(i) == '/') {
                if (equation.charAt(i) == equation.charAt(i + 1) ||
                        equation.charAt(i + 1) == '+' || equation.charAt(i + 1) == '*' ||
                        equation.charAt(i + 1) == '/' || equation.charAt(i + 1) == '-') {
                    return false;
                }
            } else if (equation.charAt(i) == '*') {
                if (equation.charAt(i + 1) == equation.charAt(i + 2) ||
                        equation.charAt(i + 1) == '+' || equation.charAt(i + 1) == '/' || equation.charAt(i + 1) == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateDigits(String expression) {
        for (char c : expression.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return false;
            }
        }
        return true;
    }
}

