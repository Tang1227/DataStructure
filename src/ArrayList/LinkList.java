package ArrayList;

/**
 * 链表
 *
 * @param <E>
 */
public class LinkList<E> extends AbstrctList<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }


    @Override
    public void add(int index, E element) {
        checkadd(index);
        if (index == size) { //新增
            Node<E> oldlast = last;
            last = new Node<E>(oldlast, element, null);
            if(oldlast==null){  //当前没有节点
                first = last;
            }else {
                oldlast.next = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            next.prev = node;
            if (prev == null) {  //头结点
                first = node;
            } else {
                prev.next = node;
            }
        }
        size++;
    }

    @Override
    public E remove(int index) {
        Node<E> node =  node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;
        if(prev==null){
         first =next;
        }else {
            prev.next = next;
        }
        if(next==null){
            last = prev;
        }else {
            next.prev= prev;
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element.equals(element)) {
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
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                s.append(",");
            }
            s.append(node.element);
            node = node.next;
        }
        s.append("}");
        return s.toString();
    }

    /**
     * 返回对应节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        checklegal(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }

    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkList<Integer> ls = new LinkList<Integer>();
//        ls.add(1);
//        ls.add(2);
//        ls.add(3);
//        ls.add(4);
//        ls.add(5);
//        ls.remove(3);
//        System.out.println(ls.get(1));
//        System.out.println(ls.toString());
    }

}
