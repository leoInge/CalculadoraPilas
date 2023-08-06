
package proyectocalculadora;

import java.util.ArrayList;
import java.util.List;

public class Convert_EvaluateToPosfix {

    public Convert_EvaluateToPosfix() {
    }

    private int priority(String c) {
        if (c.equals("**")) {
            return 2;
        } else if (c.equals("*") || c.equals("/")) {
            return 3;
        } else if (c.equals("+") || c.equals("-")) {
            return 4;
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private boolean isOperand(char c) {
        return (c >= '0' && c <= '9');
    }

    public List<String> infixToPostfix(String infix) {
        int j = 0;
        List<String> out = new ArrayList<>();
        Pila<String> stack = new Pila<>();

        Pila<String> stackPost = new Pila<>();
        String postfix = "";

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (c == '(') {
                stack.addNode(Character.toString(c));
            } else if (c == ')') {
                while (!stack.getTop().equals("(")) {
                    stackPost.addNode(stack.getTop());
                    stack.deleteTop();
                }
                stack.deleteTop();
            } else if (isOperand(c)) {
                postfix += c;
                String toString = Character.toString(c);
                while (i + 1 < infix.length() && isOperand(infix.charAt(i + 1))) {
                    i++;
                    toString += infix.charAt(i);
                }
                stackPost.addNode(toString);
                if (i + 1 < infix.length() && infix.charAt(i + 1) == '(') {
                    j = 1;
                }
                if (i - 1 >= 0 && infix.charAt(i - 1) == ')') {
                    stack.addNode("*");
                }

            } else {
                String string1 = "";
                if ((c == '+' && infix.charAt(i + 1) == '-')) {
                    i++;
                } else if ((c == '-' && infix.charAt(i + 1) == '+')) {
                    string1 += c;
                    i++;
                }
                String str = Character.toString(c);
                if (c == '*') {
                    if (i + 1 < infix.length() && infix.charAt(i + 1) == '*') {
                        string1 += c;
                        string1 += infix.charAt(i + 1);
                        i++;
                    }
                }
                if (!str.equals(" ")) {
                    if (string1.equals("**") || string1.equals("-")) {
                        while (!stack.isEmpty() && priority(string1) >= priority(stack.getTop())) {
                            stackPost.addNode(stack.getTop());
                            stack.deleteTop();
                        }
                        stack.addNode(string1);
                    } else {
                        while (!stack.isEmpty() && priority(str) >= priority(stack.getTop())) {
                            stackPost.addNode(stack.getTop());
                            stack.deleteTop();
                        }
                        if (j == 1) {
                            stack.addNode("*");
                            j = 0;
                        }
                        stack.addNode(str);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            stackPost.addNode(stack.getTop());
            stack.deleteTop();
        }
        while (!stackPost.isEmpty()) {
            out.add(stackPost.getFirst());
            stackPost.deleteFirst();
        }

        return out;
    }

    public double result(List<String> expression) {
        Pila<Double> stack = new Pila<>();
        for (String c : expression) {
            if (isOperand(c.charAt(0))) {
                double a = Double.parseDouble(c);
                stack.addNode(a);
            } else if (c.equals("+")) {
                double a = stack.getTop();
                stack.deleteTop();
                double b = stack.getTop();
                stack.deleteTop();
                stack.addNode(a + b);
            } else if (c.equals("-")) {
                double a = stack.getTop();
                stack.deleteTop();
                double b = stack.getTop();
                stack.deleteTop();
                stack.addNode(b - a);
            } else if (c.equals("/")) {
                double a = stack.getTop();
                stack.deleteTop();
                double b = stack.getTop();
                stack.deleteTop();
                stack.addNode(b / a);
            } else if (c.equals("*")) {
                double a = stack.getTop();
                stack.deleteTop();
                double b = stack.getTop();
                stack.deleteTop();
                stack.addNode(a * b);
            } else if (c.equals("**")) {
                double a = stack.getTop();
                stack.deleteTop();
                double b = stack.getTop();
                stack.deleteTop();
                stack.addNode(Math.pow(b, a));
            }
        }
        return stack.getTop();
    }
}

