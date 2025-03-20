public class LinkedList<E> {
    Node<E> head;
    public LinkedList() {
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public void addNode(E value) {
        Node<E> n = new Node<E>(value);
        if (head == null) {
            head = n;
            return;
        }
        n.previous = head;
        head.next = n;
        head = n;

    }
}
