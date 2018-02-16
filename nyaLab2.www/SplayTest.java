//public class SplayTest {
//
//
//    private SplayWithGet<Integer> makeTree() {
//        SplayWithGet<Integer> tree = new SplayWithGet<>();
//
//        tree.add(4);        // root
//        tree.add(2);        // left
//        tree.add(1);        // left left
//        tree.add(3);        // left right
//        tree.add(6);        // right
//        tree.add(5);        // right left
//        tree.add(7);        // right right
//
//        return tree;
//    }
//
//
//    // left child to x
//    private boolean zigTest(SplayWithGet<Integer> t) {
//        t.zig(t.root);
//
//        return t.root.element == 2 &&
//                t.root.parent == null &&
//
//                t.root.left.element == 1 &&
//                t.root.left.parent.element == 2 &&
//                t.root.left.left == null &&
//                t.root.left.right == null &&
//
//                t.root.right.element == 4 &&
//                t.root.right.parent.element == 2 &&
//
//                t.root.right.left.element == 3 &&
//                t.root.right.left.parent.element == 4 &&
//
//                t.root.right.right.element == 6 &&
//                t.root.right.right.parent.element == 4 &&
//
//                t.root.right.right.left.element == 5 &&
//                t.root.right.right.left.parent.element == 6 &&
//                t.root.right.right.left.left == null &&
//                t.root.right.right.left.right == null &&
//
//                t.root.right.right.right.element == 7 &&
//                t.root.right.right.right.parent.element == 6 &&
//                t.root.right.right.right.left == null &&
//                t.root.right.right.right.right == null;
//    }
//
//    // right child to x
//    private boolean zagTest(SplayWithGet<Integer> t) {
//        t.zag(t.root);
//
//        return t.root.element == 6 &&
//                t.root.left.element == 4 &&
//                t.root.right.element == 7 &&
//                t.root.left.right.element == 5 &&
//                t.root.left.left.element == 2 &&
//                t.root.left.left.right.element == 3 &&
//                t.root.left.left.left.element == 1;
//
//    }
//
//    // right left to x
//    private boolean zigzagTest(SplayWithGet<Integer> t) {
//        t.zigzag(t.root);
//
//        return false;
//    }
//
//    // left left to x
//    private boolean zigzigTest(SplayWithGet<Integer> t) {
//        t.zigzig(t.root);
//
//        return t.root.element == 1 &&
//                t.root.parent == null &&
//                t.root.left == null &&
//
//                t.root.right.element == 2 &&
//                t.root.right.parent.element == 1 &&
//
//                t.root.right.left == null &&
//
//                t.root.right.right.element == 4 &&
//                t.root.right.right.parent.element == 2 &&
//
//                t.root.right.right.right.element == 6 &&
//                t.root.right.right.right.parent.element == 4 &&
//
//                t.root.right.right.right.left.element == 5 &&
//                t.root.right.right.right.left.parent.element == 6 &&
//                t.root.right.right.right.left.left == null &&
//                t.root.right.right.right.left.right == null &&
//
//                t.root.right.right.right.right.element == 7 &&
//                t.root.right.right.right.right.parent.element == 6 &&
//                t.root.right.right.right.right.left == null &&
//                t.root.right.right.right.right.right == null;
//    }
//
//    // right left to x
//    private boolean zagzigTest(SplayWithGet<Integer> t) {
//        t.zigzag(t.root);
//
//        return false;
//    }
//
//    // right right to x
//    private boolean zagzagTest(SplayWithGet<Integer> t) {
//        t.zagzag(t.root);
//
//        return false;
//    }
//
//    public static void main(String... test) {
//        SplayTest s = new SplayTest();
//        System.out.println("zig " + s.zigTest(s.makeTree()));
//        System.out.println("zag " + s.zagTest(s.makeTree()));
//        System.out.println("zigzag " + s.zigzagTest(s.makeTree()));
//        System.out.println("zigzig " + s.zigzigTest(s.makeTree()));
//        System.out.println("zagzig " + s.zagzigTest(s.makeTree()));
//        System.out.println("zagzag " + s.zagzagTest(s.makeTree()));
//    }
//}
