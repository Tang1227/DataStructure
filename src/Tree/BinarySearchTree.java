package Tree;
/**
 * ����������
 *  ��ӽڵ�(�Զ���ڵ�ȽϷ���)
 *  ��ȡԪ�ظ���
 *  �������ĸ߶�
 *  �������
 *  �������
 *  �������
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {
    private int size;
    private Node<E> root; //���ڵ�
    private Comparator<E> comparator; //�Ƚ���

    public BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }
    public BinarySearchTree(){
        this(null);
    }
    public int size(){
        return size;
    }

    /**
     * �Ƿ�Ϊ��
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * ���Ԫ�ظ���
     * @param element
     */
    public void add(E element){
        elementNotNullCheck(element); //��Ԫ�ؼ��
        if(root==null){
            root = new Node<>(element,null); //��Ӹ��ڵ�
            size++;
            return;
        }
        //�ӽڵ� //�ҵ����ڵ� �����ڵ�Ƚ��Ƿ���߻��Ƿ��ұ�
        Node<E> parent = root; //���ڵ�
        Node<E> node = root;
        int cmp =0 ;
        //�ҵ�Ӧ�ò����λ��
        while (node!=null){
            parent =node ;
            cmp= Compare(element,node.element); //�Ƚ�
            if(cmp>0){
                node = node.right;
            }else if(cmp<0){
                node = node.left;
            }else {
                node.element= element; //����
            }
        }
        //����
        if(cmp>0){
            parent.right = new Node<E>(element,parent);
        }
        if(cmp<0){
            parent.left = new Node<E>(element,parent);
        }
        size++;
        return;
    }

    /**
     * ���ĸ߶�
     * ���ĸ߶Ⱦ��Ǽ�����ڵ�ĸ߶�
     * @return
     */
    public int treeHeight(){
        return height(root);
    }

    /**
     * ����һ���ڵ�ĸ߶�
     * ���Ǽ��������ӽڵ����ߵĸ߶�
     * @param node
     * @return
     */
    private int height(Node<E> node){
        if(node==null) return 0;
        return 1+Math.max(height(node.left),height(node.right));
    }
    public void delete(E element){

    }
    public boolean contains(E element){
        return false;
    }

    /**
     * �Ƚ�����Ԫ�ش�С
     * ���ַ�ʽ
     * ���û�ж���Ƚ���������Ķ�����Ҫʵ��Comparable��compareTo����
     * @param e1
     * @param e2
     * @return
     */
    private int Compare(E e1,E e2){
        if(comparator==null){
            return  ((Comparable<E>)e1).compareTo(e2);
        }else {
            return comparator.compare(e1,e2);
        }
    }

    /**
     * ��Ԫ�ؼ��
     * @param element
     */
    private void elementNotNullCheck(E element){
        if(element==null){
            throw new IllegalArgumentException("element not be null");
        }
    }

    /**
     * ǰ�����
     * ������
     * @param visitor
     */
    public void RLD(Visitor<E> visitor){
        if (visitor==null) return;
        RLD(root,visitor);
    }
    private void RLD(Node<E> node,Visitor<E> visitor){
        if(node==null || visitor.stop) return;//��ֹ�ݹ�
        visitor.stop = visitor.visit(node.element);
        RLD(node.left,visitor);
        RLD(node.right,visitor);
    }

    /**
     * �������
     * �����
     * @param visitor
     */
    public void LDR(Visitor<E> visitor){
        if(visitor ==null) return;
        LDR(root,visitor);
    }
    private void LDR(Node<E> node,Visitor<E> visitor){
        if(node==null || visitor.stop) return;
        LDR(node.left,visitor);
        visitor.stop = visitor.visit(node.element);
        LDR(node.right,visitor);
    }

    /**
     * �������
     * ���Ҹ�
     * @param visitor
     */
    public void LRD(Visitor<E> visitor){
        if(visitor ==null) return;
        LRD(root,visitor);
    }
    private void LRD(Node<E> node,Visitor<E> visitor){
        if(node==null || visitor.stop) return;
        LRD(node.left,visitor);
        LRD(node.right,visitor);
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * �������
     * ʹ�ö���
     * ��ÿ��ͷ������
     * ���Ű����ӽڵ㶼���
     */
//    public void LeverOrderTraversal(){
//        if(root==null) return;
//        Queue<Node<E>> queue = new LinkedList<>();
//        queue.offer(root);
//        while (!queue.isEmpty()){
//            Node<E> node = queue.poll();
//            System.out.println(node.element);
//            if(node.left!=null){
//                queue.offer(node.left);
//            }
//            if(node.right!=null){
//                queue.offer(node.right);
//            }
//        }
//    }
    public void LeverOrderVisit(Visitor<E> visitor){
        if(root==null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if(visitor.visit(node.element)) return; //ֹͣѭ��
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
    }

    /**
     *
     * @param <E>
     */
    public static abstract class Visitor<E>{
        boolean stop =false;
        abstract boolean visit(E element);
    }
    //�ڲ�˽���� ���ܱ��ⲿ����
    private static class Node<E>{
        E element;      //Ԫ��
        Node<E> left;   //��ڵ�
        Node<E> right;  //�ҽڵ�
        Node<E> parent; //���ڵ�
        public Node(E element,Node<E> parent){
            this.element =element;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        Integer[] nums =new Integer[]{
                7,4,9,2,5,8,11,12
        };
        BinarySearchTree<Integer> b= new BinarySearchTree<>();

        for(int i=0;i<nums.length;i++){
            b.add(nums[i]);
        }
//        BinarySearchTree<Person> b= new BinarySearchTree<Person>(new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getAge()-o2.getAge();
//            }
//        });
//        b.add(new Person(7));
//        b.add(new Person(4));
//        b.add(new Person(9));
//        b.add(new Person(2));
//        b.add(new Person(5));
//        b.add(new Person(8));
//        b.add(new Person(11));
//      b.preorderTraversal();
//    }
//        b.LeverOrderVisit(new Visitor<Integer>() {
//            @Override
//            public boolean visit(Integer element) {
//                if(element ==0)
//                return true;
//                System.out.println(element);
//                return  false;
//            }
//        });
        b.LRD(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return  false;
            }
        });
        System.out.println("----------");
        b.LeverOrderVisit(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.println(element);
                return  false;
            }
        });
        System.out.println("----------");
        System.out.println(b.treeHeight());
    }
}
