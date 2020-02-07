package Queue;

import ArrayList.LinkList;
import ArrayList.List;

/**
 * ����
 * ��ͷβ���˲��� ��ʹ��˫��������ʵ��
 */
public class Queue<E> {
    private List<E> list = new LinkList<E>();
    public int size(){
        return 0;
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /**
     * ��ͷ��Ԫ��
     * @return
     */
    public E front(){
      return  list.get(0);
    }

    /**
     * ���
     * @param element
     */
    public void enQueue(E element){
       list.add(element); //�������һ��λ��
    }

    /**
     * ����
     * @return
     */
    public E deQueue(){
       return list.remove(0);
    }

    @Override
    public String toString() {
        return "Queue{"
                 + list +
                '}';
    }

    public static void main(String[] args) {
      Queue<Integer> q = new Queue<Integer>();
      q.enQueue(1);
      q.enQueue(2);
      q.enQueue(3);
      q.enQueue(4);
      q.enQueue(5);
      q.deQueue();
      System.out.println(q);
    }
}