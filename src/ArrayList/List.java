package ArrayList;

public interface List<E> {
    /**
     * 清除所有元素
     */
     void clear();

    /**
     * 数组的数量
     * @return
     */
     int size();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     */
    boolean contains(E element);

    /**
     * 添加元素
     * @param element
     */
    void add(E element);

    /**
     * 获取元素
     * @param index
     * @return
     */
     E get(int index);

    /**
     * 在指定位置插入元素
     * @param index
     * @param element
     * @return
     */
     void add(int index,E element);

    /**
     * 根据位置删除元素
     * @param index
     */
     E remove(int index);

    /**
     * 找到元素对应的位置
     * @param element
     * @return
     */
    int indexOf(E element);
}
