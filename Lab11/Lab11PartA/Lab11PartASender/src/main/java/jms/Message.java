package jms;


public class Message {

	private String operator;
	private Integer operand;

	public Message() {
	}

	public Message(String operator, Integer operand) {
		this.operator = operator;
		this.operand = operand;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getOperand() {
		return operand;
	}

	public void setOperand(Integer operand) {
		this.operand = operand;
	}
}
