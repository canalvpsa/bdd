package loop;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SimpleRepeatRule implements TestRule {

    private static class SimpleRepeatStatement extends Statement {

        private final Statement statement;

        private SimpleRepeatStatement(Statement statement) {
            this.statement = statement;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < 5000; i++) {
                statement.evaluate();
                System.out.println("Contador de execuções"+i+1);
            }
        }
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        return new SimpleRepeatStatement(statement);
    }
}
