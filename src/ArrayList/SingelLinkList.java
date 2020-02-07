package ArrayList;
/**
 * ����
 * @param <E>
 */
public class SingelLinkList<E> extends AbstrctList<E> {

    private Node<E> head;

    @Override
    public void clear() {
        head = null;
        size=0;
    }

    @Override
    public E get(int index) {
       return node(index).element;
    }


    @Override
    public void add(int index, E element) {
        if(index==0) {
            head = new Node<>(element, head); //indexΪ0Ҫ�����жϣ����½ڵ�����Ϊͷ���
        }else {
            checkadd(index);
            Node<E> node = node(index-1); //��ȡ��ǰһ���ڵ��λ��
            node.next = new Node<E>(element,node.next); //ǰһ���ڵ�ָ���½ڵ㣬�½ڵ�ָ��ǰһ���ڵ�ĺ�ڵ�
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checklegal(index);
        //��ֹheadΪnullʱ// ����index
        if(index==0) {
            head=head.next;
        }else {
            checklegal(index);
            Node<E> node = node(index - 1);
            node.next = node.next.next;
            size--;
            return node.element;
        }
       return null;
    }

    @Override
    public int indexOf(E element) {
        if(element==null){
            Node<E> node = head;
            for(int i=0;i<size;i++){
                if(node.element ==null) return i;
                node = node.next;
            }
        }else {
            Node<E> node = head;
            for(int i=0;i<size;i++){
                if(node.element.equals(element)){
                    return i;
                }
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("size =").append(size).append(",{");
        Node<E> node = head;
        for(int i=0;i<size;i++){
            if(i!=0){
                s.append(",");
            }
            s.append(node.element);
            node = node.next;
        }
        s.append("}");
        return s.toString();
    }

    /**
     * ���ض�Ӧ�ڵ�
     * @param index
     * @return
     */
    private Node<E> node(int index){
        checklegal(index);
        Node<E> node = head;
        for(int i=0;i<index;i++){
            node = node.next;
        }
        return node;
    }

    private static class Node<E>{
        E element;
        Node<E> next;
        public Node(E element,Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        SingelLinkList<Integer> ls = new SingelLinkList<Integer>();
        ls.add(1);
        ls.add(2);
        ls.add(3);
        ls.add(4);
        ls.add(5);
        ls.remove(3);
        System.out.println(ls.get(1));
        System.out.println(ls.toString());
    }

}
