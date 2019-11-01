package test.programers.jobfair191026;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Exam2 {

    public class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        public TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    @Test
    public void main() {
        int n = 3;

        TreeNode root = buildTreeLoop(1, n, new TreeNode(0));
        List<Integer> result = new ArrayList<>();
        traverseInorderSearch(root, result);
        System.out.println(result);
    }

    private TreeNode buildTreeLoop(int count, int n, TreeNode startNode) {
        if(count == n) {
            return startNode;
        }

        int nextCount = count + 1;
        TreeNode left = buildTreeLoop(nextCount, n, new TreeNode(0));
        TreeNode right = buildTreeLoop(nextCount, n, new TreeNode(1));
        startNode.left = left;
        startNode.right = right;

        return startNode;

    }

    private void traverseInorderSearch(TreeNode root, List<Integer> result) {
        // Tree 중위 우선 탐색 sudo 코드 = https://ko.wikipedia.org/wiki/%ED%8A%B8%EB%A6%AC_%EC%88%9C%ED%9A%8C
        if(root.left != null) traverseInorderSearch(root.left, result);
        result.add(root.data);
        if(root.right != null) traverseInorderSearch(root.right, result);
    }

}
