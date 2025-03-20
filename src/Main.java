public class Main {
    public static void main(String[] args) {
        LinkedList lnk = new LinkedList();
        lnk.addNode(1);
        lnk.addNode(2);
        lnk.addNode(5);
        Node pointer = lnk.head.previous;
        System.out.println(pointer.value);
        System.out.println(pointer.next.value);
        System.out.println(pointer.previous.value);






    }
}