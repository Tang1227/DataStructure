package ArrayList;

import java.util.Arrays;

/**
 * 动态数组
 * 查询O(1)
 * 增删改O(N)
 *
 */
public class ArrayList<E> extends AbstrctList<E>{

    private E[] elements;

    private static final int DEFAULT_CAPACITY =10;

    public ArrayList(int capacity){
        capacity = capacity<10?DEFAULT_CAPACITY:capacity;//容量小于10 就使用默认容量
        elements = (E[]) new Object[capacity];  //设置容量
    }

    public ArrayList(){
       this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
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
       }else {//需要扩容
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
     * 获取元素
     * @param index
     * @return
     */
    public E get(int index){
        super.checklegal(index);
      return elements[index];
    }

    /**
     * 在指定位置插入元素
     * @param index
     * @param element
     * @return
     */
    public void  add(int index,E element){
        ensureCapaticy(size+1);  //至少需要size=1
        if(index<0 || index>size){  //添加的元素可以是新增,因此index>size
            throw new IllegalArgumentException();
        }
       for(int i=size-1;i>=index;i--){
           elements[i+1] = elements[i];
       }
        size++;
        elements[index] = element;

    }

    /**
     * 根据位置删除元素
     * @param index
     */
    public E remove(int index){
        checklegal(index);
        E element = elements[index];

        for(int i=index;i<size-1;i++){
           elements[i] = elements[i+1];  //依次向前
       }
       elements[--size] =null;
       return element; //返回被删除的元素
    }

    /**
     * 找到元素对应的位置
     * @param element
     * @return
     */
    public int indexOf(E element){
     if(element==null){ //元素为null时
         for(int i=0;i<size;i++){
             if(elements[i]==null) return i; //null不能调用方法
         }
     }
     for (int i=0;i<size;i++){  //元素不为null时
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
