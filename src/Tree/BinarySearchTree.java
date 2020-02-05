package Tree;
/**
 * 二叉搜索树
 *  添加节点(自定义节点比较方法)
 *  获取元素个数
 *  计算树的高度
 *  先序遍历
 *  中序遍历
 *  后序遍历
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {
    private int size;
    private Node<E> root; //根节点
    private Comparator<E> comparator; //比较器

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
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * 添加元素个数
     * @param element
     */
    public void add(E element){
        elementNotNullCheck(element); //空元素检查
        if(root==null){
            root = new Node<>(element,null); //添加根节点
            size++;
            return;
        }
        //子节点 //找到父节点 跟父节点比较是放左边还是放右边
        Node<E> parent = root; //父节点
        Node<E> node = root;
        int cmp =0 ;
        //找到应该插入的位置
        while (node!=null){
            parent =node ;
            cmp= Compare(element,node.element); //比较
            if(cmp>0){
                node = node.right;
            }else if(cmp<0){
                node = node.left;
            }else {
                node.element= element; //覆盖
            }
        }
        //插入
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
     * 树的高度
     * 树的高度就是计算根节点的高度
     * @return
     */
    public int treeHeight(){
        return height(root);
    }

    /**
     * 计算一个节点的高度
     * 就是计算左右子节点的最高的高度
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
     * 比较两个元素大小
     * 两种方式
     * 如果没有定义比较器，传入的对象需要实现Comparable的compareTo方法
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
     * 空元素检查
     * @param element
     */
    private void elementNotNullCheck(E element){
        if(element==null){
            throw new IllegalArgumentException("element not be null");
        }
    }

    /**
     * 前序遍历
     * 根左右
     * @param visitor
     */
    public void RLD(Visitor<E> visitor){
        if (visitor==null) return;
        RLD(root,visitor);
    }
    private void RLD(Node<E> node,Visitor<E> visitor){
        if(node==null || visitor.stop) return;//终止递归
        visitor.stop = visitor.visit(node.element);
        RLD(node.left,visitor);
        RLD(node.right,visitor);
    }

    /**
     * 中序遍历
     * 左根右
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
     * 后序遍历
     * 左右根
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
     * 层序遍历
     * 使用队列
     * 把每个头结点入队
     * 接着把它子节点都入队
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
            if(visitor.visit(node.element)) return; //停止循环
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
    //内部私有类 不能被外部访问
    private static class Node<E>{
        E element;      //元素
        Node<E> left;   //左节点
        Node<E> right;  //右节点
        Node<E> parent; //父节点
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
