package proyectocalculadora;
import java.util.ArrayList;
import java.util.List;

public class Pila<T> {
    private Node<T> head; // Puntero al primer nodo de la pila

    public Pila() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addNode(T info) {
        Node<T> newNode = new Node<>(info);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> aux = head;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public T deleteTop() {
        Node<T> act = head;
        Node<T> ant = null;

        while (act.getNext() != null) {
            ant = act;
            act = act.getNext();
        }
        if (act == head) {
            head = null;
        } else {
            ant.setNext(null);
        }
        return act.getInfo();
    }

    public List<T> getStack() {
        List<T> out = new ArrayList<>();
        Node<T> aux = head;

        while (aux != null) {
            out.add(aux.getInfo());
            aux = aux.getNext();
        }

        return out;
    }

    public T getTop() {
        Node<T> aux = head;
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }
        return aux.getInfo();
    }

    public T getFirst() {
        return head.getInfo();
    }

    public void deleteFirst() {
        head = head.getNext();
    }

    public void clear() {
        head = null;
    }
}

