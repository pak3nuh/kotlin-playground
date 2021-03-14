package io.github.pak3nuh.kotlin.playground.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

public class AcyclicVisitor {
    public static void main(String[] args) {
        var visitor = new PrintVisitor();
        var nodeList = List.of(new HtmlNode(), new HeadNode(), new BodyNode(), new H1Node());

        nodeList.forEach(it -> it.accept(visitor));
    }

    public static class Node {
        /**
         * <p>Generic entrypoint for the accept method. Tries to find the correct overload for the arguments
         * or dispatches it to the most generic method available.</p>
         * @param visitor
         */
        public void accept(NodeVisitor visitor) {
            try {
                visitSpecific(visitor);
            } catch (NoSuchMethodException exception) {
                visitor.visit(this);
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }

        /**
         * <p>Finds the specific method handle and tries to dispatch to the correct method.</p>
         *
         * <p>Can't use invokeExact because the point of the acyclic visitor is to allow evolution
         * of both the nodes and visitors in different stages. In this case, by using invokeExact
         * we would need to know the exact type of the receiver (HtmlNode, HeadeNode).</p>
         *
         * <p>By using invoke we allow the receiver to change, but we have fixed the method arguments
         * to the correct types.</p>
         * @param visitor
         * @throws Throwable
         * NoSuchMethodException when the specific overload is not found
         * <p>Throwable for everything else</p>
         */
        private void visitSpecific(NodeVisitor visitor) throws Throwable {
            MethodHandles.Lookup lookup = MethodHandles.publicLookup();
            MethodType type = MethodType.methodType(void.class, getClass());
            MethodHandle handle = lookup.findVirtual(visitor.getClass(), "visit", type);
            handle.invoke(visitor, this);
        }
    }

    public static class HtmlNode extends Node { }
    public static class HeadNode extends Node { }
    public static class BodyNode extends Node { }
    public static class H1Node extends Node { }

    public interface NodeVisitor {
        void visit(Node node);
    }

    public interface HtmlNodeVisitor extends NodeVisitor {
        void visit(HtmlNode node);
    }

    public interface HeadNodeVisitor extends NodeVisitor {
        void visit(HeadNode node);
    }

    public static class PrintVisitor implements HtmlNodeVisitor, HeadNodeVisitor {

        @Override
        public void visit(Node node) {
            System.out.println("Visiting generic node " + node.getClass());
        }

        @Override
        public void visit(HtmlNode node) {
            System.out.println("Visiting Html node");
        }

        @Override
        public void visit(HeadNode node) {
            System.out.println("Visiting Head node");
        }
    }

}
