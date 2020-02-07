package ArrayList;

import java.util.Arrays;

/**
 * ��̬����
 * ��ѯO(1)
 * ��ɾ��O(N)
 *
 */
public class ArrayList<E> extends AbstrctList<E>{

    private E[] elements;

    private static final int DEFAULT_CAPACITY =10;

    public ArrayList(int capacity){
        capacity = capacity<10?DEFAULT_CAPACITY:capacity;//����С��10 ��ʹ��Ĭ������
        elements = (E[]) new Object[capacity];  //��������
    }

    public ArrayList(){
       this(DEFAULT_CAPACITY);
    }

    /**
     * �������Ԫ��
     */
    public void clear(){
       for(int i=0;i<size;i++){
           elements[i] = null;
       }
       size=0;
    }
    private void ensureCapaticy(int capacity){
        int oldcapacity = elements.length;
        if(capacity<=oldcapacity){
            return;
       }else {//��Ҫ����
            int newcapacity = oldcapacity + (oldcapacity>>1);
            E[] newelements = (E[]) new Object[newcapacity];
            for(int i=0;i<size;i++){
                newelements[i] = elements[i];
            }
            System.out.println("---");
            elements = newelements;
        }
    }
    /**
     * ��ȡԪ��
     * @param index
     * @return
     */
    public E get(int index){
        super.checklegal(index);
      return elements[index];
    }

    /**
     * ��ָ��λ�ò���Ԫ��
     * @param index
     * @param element
     * @return
     */
    public void  add(int index,E element){
        ensureCapaticy(size+1);  //������Ҫsize=1
        if(index<0 || index>size){  //��ӵ�Ԫ�ؿ���������,���index>size
            throw new IllegalArgumentException();
        }
       for(int i=size-1;i>=index;i--){
           elements[i+1] = elements[i];
       }
        size++;
        elements[index] = element;

    }

    /**
     * ����λ��ɾ��Ԫ��
     * @param index
     */
    public E remove(int index){
        checklegal(index);
        E element = elements[index];

        for(int i=index;i<size-1;i++){
           elements[i] = elements[i+1];  //������ǰ
       }
       elements[--size] =null;
       return element; //���ر�ɾ����Ԫ��
    }

    /**
     * �ҵ�Ԫ�ض�Ӧ��λ��
     * @param element
     * @return
     */
    public int indexOf(E element){
     if(element==null){ //Ԫ��Ϊnullʱ
         for(int i=0;i<size;i++){
             if(elements[i]==null) return i; //null���ܵ��÷���
         }
     }
     for (int i=0;i<size;i++){  //Ԫ�ز�Ϊnullʱ
         if(elements[i].equals(element)) return i;
     }
     return -1;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "size=" + size +
                ", elements=" + Arrays.toString(elements) +
                '}';
    }

    public static void main(String[] args) {
//        ArrayList<Integer> a = new ArrayList<Integer>();
//        a.add(1);
//        a.add(2);
//        a.add(3);
//        a.add(4);
//        a.add(0,2);
//        a.remove(2);
//        System.out.println(a);

       // a.clear();
      //  System.out.println(a);

    }

}
