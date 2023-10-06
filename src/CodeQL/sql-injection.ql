// Import required libraries
import java

// Define a class for SQLInjection detection
class SQLInjection extends TaintTracking::Configuration {
  SQLInjection() {
    this = "Potential SQL Injection"
  }

  // Define a predicate for sources (user input)
  override predicate isSource(DataFlow::Node source) {
    exists(MethodAccess ma |
      source.asExpr() = ma.getExpr() and
      ma.getMethod().getName().toLowerCase(Locale.ROOT) = "getparameter"
    )
  }

  // Define a predicate for sinks (SQL execution statements)
  override predicate isSink(DataFlow::Node sink) {
    exists(MethodAccess ma |
      sink.asExpr() = ma.getExpr() and
      ma.getMethod().getDeclaringType().hasQualifiedName("java.sql", "Statement") and
      ma.getMethod().getName().matches("(?i).*execute(Query|Update|)$")
    )
  }
}

// Define a query to match SQL injection patterns
from SQLInjection, DataFlow::PathNode source, DataFlow::PathNode sink
where
  source.asExpr().getParent*().hasType("java.lang.String") and
  source.asExpr() = sink.asExpr().getParent*() and
  sink.asExpr().getParent*().hasType("java.sql.Statement")
select sink, source, "Potential SQL Injection"
