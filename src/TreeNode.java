import java.util.LinkedList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode generateTreeNode(int depth) {
        int value = (int)(Math.random() * 20);
        TreeNode root = new TreeNode(value);

        return root;
    }

    public static TreeNode generateCompleteBinaryTree(int depth) {
        int value = (int)(Math.random() * 20);
        TreeNode root = new TreeNode(value);

        return root;
    }

    private static TreeNode newNode(TreeNode node) {
        if (node == null) {
            return null;
        }
        int l = (int) (Math.random() * 20);
        int r = (int) (Math.random() * 20);
        node.left = new TreeNode(l);
        node.right = new TreeNode(r);

        return node;
    }

    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if(data!=null){
            node = new TreeNode(data);
            node.left = createBinaryTree(list);
            node.right = createBinaryTree(list);
        }
        return node;
    }

    public static TreeNode createBinaryTree(int depth){
        TreeNode node = null;
        if(depth == 0){
            return null;
        }
        int value = (int) (Math.random() * 20);
        node = new TreeNode(value);
        depth -= 1;
        node.left = createBinaryTree(depth);
        node.right = createBinaryTree(depth);
        return node;
    }

    /**
     * 二叉树前序遍历   根-> 左-> 右
     * @param node    二叉树节点
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.val+" ");
        preOrderTraveral(node.left);
        preOrderTraveral(node.right);
    }

    /**
     * 二叉树中序遍历   左-> 根-> 右
     * @param node   二叉树节点
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        inOrderTraveral(node.left);
        System.out.print(node.val+" ");
        inOrderTraveral(node.right);
    }
}
