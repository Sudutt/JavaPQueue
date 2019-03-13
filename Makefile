Assignment.class:
	javac Assignment.java
	java Assignment

test:
	javac Assignment.java
	java Assignment < tests/input.txt > tests/output.txt
	diff tests/output.txt tests/expected.txt

clean:
	rm Assignment.class Student.class tests/output.txt