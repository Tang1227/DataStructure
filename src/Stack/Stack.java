package Stack;

import ArrayList.ArrayList;
import ArrayList.List;

/**
 * 栈
 * @param <E>
 */
public class Stack<E> {
    private List<E> list = new ArrayList<E>();

    public void push(E element){
      list.add(element);
    }

    public E pop(){
      return list.remove(list.size()-1);
    }
    public E top(){
      return list.get(list.size()-1);
    }

    @Override
    public String toString() {
        return "Stack{" +
                "list=" + list +
                '}';
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        s.push(1);
        s.push(2);
      //  s.pop();
        System.out.println(s.toString());
    }
}

