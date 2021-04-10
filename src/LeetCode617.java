import java.util.LinkedList;

/**
 * <a href="https://leetcode-cn.com/problems/merge-two-binary-trees/">617. 合并二叉树</a><br>
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <br>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <br>
 * <pre>
 * 示例 1:
 *
 * 输入:
 *        Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 * </pre>
 * 注意: 合并必须从两个树的根节点开始。
 *
 */
public class LeetCode617 {
    public static void main(String[] args) {
//        TreeNode root = TreeNode.createBinaryTree(4);
//        TreeNode.preOrderTraveral(root);
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i < 16; i++) {
            linkedList.add(i);
        }
        TreeNode root = TreeNode.createBinaryTree(linkedList);
        TreeNode.preOrderTraveral(root);
        System.out.println();
        TreeNode.inOrderTraveral(root);
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return null;
    }
}
