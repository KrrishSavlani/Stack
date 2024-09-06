import java.util.*;

public class expression {
    static Set<String> operators = new HashSet<>();
    static StringBuffer prefix = new StringBuffer();

    static {
        operators.add("+");
        operators.add("-");
        operators.add("/");
        operators.add("*");
        operators.add("^");
        operators.add("(");
        operators.add(")");
        operators.add("{");
        operators.add("}");
        operators.add("[");
        operators.add("]");
    }

    public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
        System.out.println("infix : "+input);
        StringBuffer exp = new StringBuffer(input);
        expression obj = new expression();
        obj.process(exp);
        obj.getResult();
    }

    public static void process(StringBuffer exp) {
        stack stackk = new stack();
        for (int i = 0; i < exp.length(); i++) {
            String ch = String.valueOf(exp.charAt(i)); // Convert char to String

            if (operators.contains(ch)) { // Use Set<String> to check if it's an operator
               // System.out.println("i entered");

                order obj = new order();
                String last = ch;

                String s_last;
                do {
                    if (stackk.top() > -1) {
                        s_last = stackk.stack[stackk.top()].toString();
                    }
                    if (stackk.top() == -1 || obj.GETorder(new StringBuffer(last)) > obj.GETorder(stackk.stack[stackk.top()])) {
                     //   System.out.println("i entered in pushing if");

					   stackk.push(new StringBuffer(ch));
					   break;
                    }
					else if (obj.GETorder(new StringBuffer(last)) <= obj.GETorder(stackk.stack[stackk.top()])) {
						// System.out.println("i entered in else");

                        prefix = prefix.append(stackk.pop());
                    }
                } while (stackk.top() > -1 );
				if(stackk.top() == -1) stackk.push(new StringBuffer(last));
            } else {
                prefix.append(ch); // Append non-operator directly
            }
        }
    }

    public static void getResult() {
        stack obj = new stack();

        while (obj.top() > -1) {
			 //System.out.println("i entered");

            prefix.append(obj.pop());
        }

        System.out.println("prefix :- " + prefix);
    }
}

class stack {
    static StringBuffer[] stack = new StringBuffer[100000];
    static int top = -1;

    public static int top() {
        return top;
    }

    public static void push(StringBuffer op) {
        stack[++top] = op;
    }

    public static StringBuffer pop() {
        return stack[top--];
    }
}

class order {
    public static int GETorder(StringBuffer operator) {
        return switch (operator.toString()) {
            case "(", ")", "{", "}", "[", "]" -> 4;
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> {
                System.out.println("Unknown operator: " + operator);
                yield -1;
            }
        };
    }
}