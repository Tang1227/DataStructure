package ArrayList;
/**
 * 放公共的代码
 * @param <E>
 */
public abstract class AbstrctList<E> implements List<E>{
    /**
     * 元素的数量
     */
    protected int size;

    protected void checklegal(int index){
        if(index<0 || index>=size){
            throw new IllegalArgumentException();
        }
    }
    protected void checkadd(int index){
        if(index<0 || index>size){
            throw new IllegalArgumentException();
        }
    }
    /**
     *
     * @return
     */
    public int size(){
        return size;
    }
    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    public boolean contains(E element){
        return indexOf(element)!=-1;
    }


    public void add(E element){
        add(size,element);//新增元素
    }
}
