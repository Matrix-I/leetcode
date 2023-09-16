public class Demo {
  // Abstract handler using Java 17 sealed classes
  abstract static sealed class Handler
      permits ConcreteHandler1, ConcreteHandler2, ConcreteHandler3 {
    protected Handler successor;

    public void setSuccessor(Handler successor) {
      this.successor = successor;
    }

    public abstract void handleRequest(int request);
  }

  // Concrete handler 1 using Java 17 switch expressions
  static final class ConcreteHandler1 extends Handler {
    @Override
    public void handleRequest(int request) {
      if (request >= 0 && request < 10) {
        System.out.println("ConcreteHandler1 handled request " + request);
      } else {
        // Using switch expression for successor handling
        var result =
            switch (successor != null ? "delegate" : "none") {
              case "delegate" -> {
                successor.handleRequest(request);
                yield "delegated";
              }
              case "none" -> "no_successor";
              default -> "unknown";
            };
      }
    }
  }

  // Concrete handler 2 using Java 17 switch expressions
  static final class ConcreteHandler2 extends Handler {
    @Override
    public void handleRequest(int request) {
      if (request >= 10 && request < 20) {
        System.out.println("ConcreteHandler2 handled request " + request);
      } else {
        // Using switch expression for successor handling
        var result =
            switch (successor != null ? "delegate" : "none") {
              case "delegate" -> {
                successor.handleRequest(request);
                yield "delegated";
              }
              case "none" -> "no_successor";
              default -> "unknown";
            };
      }
    }
  }

  // Concrete handler 3 using Java 17 switch expressions
  static final class ConcreteHandler3 extends Handler {
    @Override
    public void handleRequest(int request) {
      if (request >= 20 && request < 30) {
        System.out.println("ConcreteHandler3 handled request " + request);
      } else {
        // Using switch expression for successor handling with fallback
        var result =
            switch (successor != null ? "delegate" : "final") {
              case "delegate" -> {
                successor.handleRequest(request);
                yield "delegated";
              }
              case "final" -> {
                System.out.println("No handler for request " + request);
                yield "unhandled";
              }
              default -> "unknown";
            };
      }
    }
  }

  // Client code
  public static class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
      // Create the chain of responsibility
      Handler handler1 = new ConcreteHandler1();
      Handler handler2 = new ConcreteHandler2();
      Handler handler3 = new ConcreteHandler3();

      handler1.setSuccessor(handler2);
      handler2.setSuccessor(handler3);

      // Send requests to the chain
      int[] requests = {2, 15, 25, 35};
      for (int request : requests) {
        handler1.handleRequest(request);
      }
    }
  }
}
