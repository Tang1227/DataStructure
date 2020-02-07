package Queue;

import java.util.Arrays;

/**
 * ѭ������
 * ���ڶ�̬����ʵ��
 * ��ʹ��Ӻͳ��ӵĲ�����ΪO(1)
 */
public class CircleQueue<E> {
    private static final int DEFAULT_CAPACITY =4;
    private E[] elements;
    private int size;
    private int front;

    public CircleQueue(int capaticy){
      capaticy =  capaticy<10 ?DEFAULT_CAPACITY:capaticy;
      elements = (E[]) new Object[capaticy];
    }
    public CircleQueue(){
        this(DEFAULT_CAPACITY);
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return this.size==0;
    }

    /**
     * ��ȡ��ͷԪ��
     * @return
     */
    public E front(){
        return elements[front];
    }

    /**
     * ���
     */
    public void offer(E element){
        ensureCapaticy(size+1);
        elements[index(size)] = element;
        size++;
    }

    /**
     * ����
     * ����frontλ��
     * @return
     */
    public E poll(){
        E element = elements[front];
        elements[front] =null;
        front = index(1);
        size--;
        return element;
    }

    private void ensureCapaticy(int capacity){
        int oldcapacity = elements.length;
        if(capacity<=oldcapacity){
            return;
        }else {//��Ҫ����
            int newcapacity = oldcapacity + (oldcapacity>>1);
            E[] newelements = (E[]) new Object[newcapacity];
            for(int i=0;i<size;i++){
            newelements[i] = elements[index(i)] ;
            }
            System.out.println("---");
            elements = newelements;

            front =0; //�����齫front��Ϊ0
        }
    }

    /**
     * ��ȡѭ�����е�����
     * @param index
     * @return
     */
    private int index(int index){
        return (front+index)%elements.length;
    }

    @Override
    public String toString() {
        return "CircleQueue{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                ", front=" + front +
                '}';
    }

    public static void main(String[] args) {
        CircleQueue<Integer> c = new CircleQueue<Integer>();
        c.offer(1);
        c.offer(2);
        c.offer(3);
        c.offer(4);
        c.offer(5);
        c.poll();
        c.poll();
        c.offer(6);
        c.offer(7);
        System.out.println(c);
    }
}
