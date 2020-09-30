/** Utility class that deals with converting to and from infix, 
 *  can also evaluatate postfix
 * 
 * @author Michael Amaya
 *
 */
public class Notation {
	
	/** Converts from infix notation to postfix notation using Stacks and Queues, stack for holding
	 *  operands before putting them into the queue
	 * 
	 * @param infix The infix to be turned into postfix
	 * @return The postfix notation version of what was passed in
	 * @throws InvalidNotationFormatException Occurs if the format of the infix notation was incorrect
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		// Create char array to work through infix
		char[] infixArray = infix.toCharArray();
		
		// Create stack and queue, one for temporarily holding operators and one for the solution
		NotationStack<Character> tempStack = new NotationStack<>(infixArray.length);
		NotationQueue<String> solutionQueue = new NotationQueue<>(infixArray.length);
		
		// Loop through the infix char array
		for (char infixChar : infixArray) {
			// Check if the character is not a space because spaces are ignored
			if (infixChar != ' ') {
				// Check if char is an operand
				if (isOperand(infixChar)) {
					// If it is, add it to the queue. If an error occurs, then throw an InvalidNotationFormatException
					try {
						solutionQueue.enqueue(Character.toString(infixChar));
					} catch (QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				// Check if the char is a left parenthesis
				} else if (infixChar == '(') {
					// If it is, push it into the temp stack. If an error occurs, throw an InvalidNotationFormatException
					try {
						tempStack.push(infixChar);
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				// Check if the char is an operator
				} else if (isOperator(infixChar)) {
					// If an error occurs in this block, throw an InvalidNotationFormatException 
					try {
						// Do the following while the stack isn't empty and while the top item is an operator:
						// - Check if the top item in the stack is a higher operator than the current operator
						//    - If it is, then add the operator to the queue
						while(!tempStack.isEmpty() && isOperator(tempStack.top())) {
							if(higherOperator(tempStack.top(), infixChar) == tempStack.top())
								solutionQueue.enqueue(Character.toString(tempStack.pop()));
						}
						
						// Add the current operator to the stack
						tempStack.push(infixChar);
					} catch (StackUnderflowException | StackOverflowException | QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				// Check if the current character is a right parenthesis
				} else if (infixChar == ')') {
					// If an error occurs in this block, throw an InvalidNotationFormatException
					try {
						// Add the top item from the tempstack to the solution queue and remove from tempstack if the 
						// top item is not a left parenthesis
						while(tempStack.top() != '(') {
							solutionQueue.enqueue(Character.toString(tempStack.pop()));
						}
						
						// Discard the parenthesis
						tempStack.pop();
					} catch (StackUnderflowException | QueueOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				}
			}
		}
		
		// If the tempstack is not empty, then add everything from that stack to the queue.
		while (!tempStack.isEmpty()) {
			try {
				solutionQueue.enqueue(Character.toString(tempStack.pop()));
			} catch (QueueOverflowException | StackUnderflowException e) {
				throw new InvalidNotationFormatException();
			}
		}
		
		// Return the solutionQueue as a string
		return solutionQueue.toString();
	}
	
	/** Converts from postfix notation to infix notation using a stack as the
	 *  data structure
	 * 
	 * @param postfix The postfix to be converted to infix
	 * @return The converted postfix that should be in infix notation
	 * @throws InvalidNotationFormatException Occurs if the infix notation is not in a valid format
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		// Postfix array so that we can loop through each item
		char[] postfixArray = postfix.toCharArray();
		
		// Stack that will hold the solution of the stack
		NotationStack<String> solutionStack = new NotationStack<>(postfixArray.length);
		
		// Loop through the postfix char array
		for (char postfixChar : postfixArray) {
			// Ignore the character if its a space
			if (postfixChar != ' ') {
				// Check if the character is an operand
				if (isOperand(postfixChar)) {
					// Push the operand to the solution stack and if an error occurs, then throw an invalid notationformat exception
					try {
						solutionStack.push(Character.toString(postfixChar));
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				// Check if the character is an operator
				} else if (isOperator(postfixChar)) {
					// Throw an InvalidNotationFormatException if the size of the stack is less than two
					if (solutionStack.size() < 2)
						throw new InvalidNotationFormatException();
					
					// If the code in this block has an error, throw an invalid notation format exception
					try {
						// Get the first and second value of the expression
						String secondValue = solutionStack.pop();
						String firstValue = solutionStack.pop();
						
						// Put the values together with the current operand separating them, then add parenthesis
						String together = firstValue + Character.toString(postfixChar) + secondValue;
						String togetherWithParenthesis = "(" + together + ")";
						
						// Add the previous string into the solution stack
						solutionStack.push(togetherWithParenthesis);
					} catch (StackUnderflowException | StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
					
				} else {
					// I know this is gross and I should not be doing
					// this, but I didn't want the tests to fail because
					// I would need to add this Exception to the method's
					// signature, and I don't want to modify the tests,
					// so for now to make this nice, I will just print
					// a message, but know that I wanted to add this extra 
					// exception to the signature instead for usability
					try {
						throw new InvalidCharacterException();
					} catch (InvalidCharacterException e) {
						// System.err.println("An invalid character was used!");
						throw new InvalidNotationFormatException();
					}
				}
			}
				
		}
		
		// Check if the solution stack has more operands/operators
		// If it does, and it shouldn't if valid, then throw an invalid notation format exception
		if (solutionStack.size() > 1)
			throw new InvalidNotationFormatException();
		
		// Return the solution stack as a string
		return solutionStack.toString();
	}
	
	/** Evaluates a postfix expression, no matter how complicated
	 * 
	 * @param postdixExpr The postfix expression to evaluate
	 * @return The result of the postfix expression
	 * @throws InvalidNotationFormatException Occurs when the notation is passed in an invalid format
	 */
	public static double evaluatePostfixExpression(String postdixExpr) throws InvalidNotationFormatException {
		// The postfix char array used to go through the postfix expression
		char[] postfixArray = postdixExpr.toCharArray();
		
		// The solution stack of the evaluated postfix expression
		NotationStack<String> stack = new NotationStack<>(postfixArray.length);
		
		// Loop through the postfix array
		for (char postfixChar : postfixArray) {
			// Ignore the spaces
			if(postfixChar != ' ') {
				// Check if the current character is an operand
				if (isOperand(postfixChar)) {
					// Push the operand to the stack, and if an error occurs, throw an invalid notation format exception
					try {
						stack.push(Character.toString(postfixChar));
					} catch (StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				// Check if the current character is an operator
				} else if (isOperator(postfixChar)) {
					// If the size of the stack is less than two, throw an invalid notation format exception
					if (stack.size() < 2)
						throw new InvalidNotationFormatException();
					// If the following code block has an error, throw an invalid notation format exception 
					try {
						// Get the first and second value from the stack
						String secondValue = stack.pop();
						String firstValue = stack.pop();
						
						// Do the calculation of the two values with the current operator
						double result = doCalculation(postfixChar, firstValue, secondValue);
						
						// Push the result to the stack
						stack.push(String.valueOf(result));
						
					} catch (StackUnderflowException | StackOverflowException e) {
						throw new InvalidNotationFormatException();
					}
				}
			}
		}
		
		// If the size of the stack is greater than one, then throw an invalid notation format exception
		if (stack.size() > 1)
			throw new InvalidNotationFormatException();
		
		// Return the value of the stack, which should just be one string double
		return Double.parseDouble(stack.toString());
	}
	
	/* ************************************************************ */
	/*           Set of methods I use to figure things out          */
	/* ************************************************************ */
	
	/** Checks to see if what was passed through is 
	 *  an operator or not, operator is defined as 
	 *  any of the following: +*-/
	 * 
	 *  @param item The character to check if its an operator
	 *  @return True if the item is an operator, false if not
	 */
	private static boolean isOperator(char item) {
		char[] operators = "+-*/".toCharArray();
		for (char operator : operators)
			if (item == operator)
				return true;
		
		return false;
	}
	
	/** Checks to see if what was passed through is
	 *  an operand or not, operand is defined as
	 *  numbers 0-9
	 * 
	 * @param item The character to check if its an operand
	 * @return True if the item is an operand, false if not
	 */
	private static boolean isOperand(char item) {
		char[] numbers = "0123456789".toCharArray();
		for (char number : numbers)
			if (item == number)
				return true;
		return false;
	}
	
	/** Checks the precedence of the operators,
	 *  meaning figuring out which operator should
	 *  go first, and which goes after
	 *  * and / are always first, then goes +-
	 *  Lastly, you got ()
	 * 
	 * @param operator1 An operator you want to check the precedence of
	 * @param operator2 An operator you want to check the precedence of
	 * @return The higher of the two operators or operator1 if they're the same
	 */
	private static char higherOperator(char operator1, char operator2) {
		char[] higherOperators = "*/".toCharArray();
		char[] middleOperators = "+-".toCharArray();
		char[] lowestOperators = "()".toCharArray();
		
		if (operator1 == operator2)
			return operator1;
		
		for (char operator : higherOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		for (char operator : middleOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		for (char operator : lowestOperators)
			if (operator1 == operator)
				return operator1;
			else if (operator2 == operator)
				return operator2;
		
		return operator1;
	}
	
	/** Does a calculation based on the operator passed through,
	 *  and the two numbers passed through. Be careful because for
	 *  subtraction or division, the numbers need to be in a certain
	 *  order. Valid operators are +*-/
	 * 
	 * @param operator The operator needed for the calculation
	 * @param num1 The left operand of the equation
	 * @param num2 The right operand of the equation
	 * @return The calculation with the two numbers and the operator
	 */
	private static double doCalculation(char operator, String num1, String num2) {
		double result = 0.0;
		
		switch (operator) {
			case '+':
				result = Double.parseDouble(num1) + Double.parseDouble(num2);
				break;
			case '-':
				result = Double.parseDouble(num1) - Double.parseDouble(num2);
				break;
			case '*':
				result = Double.parseDouble(num1) * Double.parseDouble(num2);
				break;
			case '/':
				result = Double.parseDouble(num1) / Double.parseDouble(num2);
				break;
		}
		
		return result;
	}
	
}