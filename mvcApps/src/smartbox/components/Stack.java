package smartbox.components;

import smartbox.*;

public class Stack extends Component implements IStack {

	private java.util.Stack<Double> stack;

	public Stack() {
		super();
		stack = new java.util.Stack<Double>();
	}

    public void push(Double num) {
        stack.push(num);
    }

    public void pop() {
        stack.pop();
    }

    public Double top() {
 		return stack.peek();
    }

    public void clear() {
        stack.clear();
    }

    public Boolean isEmpty() {
        return stack.isEmpty();
    }
}