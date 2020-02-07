package ArrayList;

public interface List<E> {
    /**
     * �������Ԫ��
     */
     void clear();

    /**
     * ���������
     * @return
     */
     int size();

    /**
     * �Ƿ�Ϊ��
     * @return
     */
    boolean isEmpty();

    /**
     * �Ƿ����ĳ��Ԫ��
     */
    boolean contains(E element);

    /**
     * ���Ԫ��
     * @param element
     */
    void add(E element);

    /**
     * ��ȡԪ��
     * @param index
     * @return
     */
     E get(int index);

    /**
     * ��ָ��λ�ò���Ԫ��
     * @param index
     * @param element
     * @return
     */
     void add(int index,E element);

    /**
     * ����λ��ɾ��Ԫ��
     * @param index
     */
     E remove(int index);

    /**
     * �ҵ�Ԫ�ض�Ӧ��λ��
     * @param element
     * @return
     */
    int indexOf(E element);
}
