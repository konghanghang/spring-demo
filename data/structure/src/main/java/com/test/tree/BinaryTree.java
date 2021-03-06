package com.test.tree;

import java.util.Stack;

/**
 * https://blog.csdn.net/gaoweijiegwj/article/details/105941422
 * @author yslao@outlook.com
 * @since 2021/1/29
 */
public class BinaryTree {

    /**
     * 先序遍历递归方式
     * @param treeNode
     */
    public static void preOrderRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.getData() + "\t");
        // 向左递归
        preOrderRecursion(treeNode.getLeft());
        // 向右递归
        preOrderRecursion(treeNode.getRight());
    }

    /**
     * 先序遍历非递归方式
     * @param treeNode
     */
    public static void preOrderNoRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            System.out.print(pop.getData() + "\t");
            // 添加右子树
            if (pop.getRight() != null) {
                stack.push(pop.getRight());
            }
            // 添加左子树
            if (pop.getLeft() != null) {
                stack.push(pop.getLeft());
            }
        }
    }

    /**
     * 中序遍历递归方式
     * @param treeNode
     */
    public static void midOrderRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 向左递归
        midOrderRecursion(treeNode.getLeft());
        System.out.print(treeNode.getData() + "\t");
        // 向右递归
        midOrderRecursion(treeNode.getRight());
    }

    /**
     * 中序遍历非递归方式
     * @param treeNode
     */
    public static void midOrderNoRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            System.out.println("树为空，无法遍历");
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            } else {
                BinaryTreeNode pop = stack.pop();
                System.out.print(pop.getData() + "\t");
                treeNode = pop.getRight();
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历递归方式
     * @param treeNode
     */
    public static void postOrderRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 向左递归
        postOrderRecursion(treeNode.getLeft());
        // 向右递归
        postOrderRecursion(treeNode.getRight());
        System.out.print(treeNode.getData() + "\t");
    }

    /**
     * 后序遍历非递归方式
     * @param treeNode
     */
    public static void postOrderNoRecursion(BinaryTreeNode treeNode) {
        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();
        stack1.push(treeNode);
        while (!stack1.isEmpty()) {
            BinaryTreeNode pop = stack1.pop();
            stack2.push(pop);
            if (pop.getLeft() != null) {
                stack1.push(pop.getLeft());
            }
            if (pop.getRight() != null) {
                stack1.push(pop.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            BinaryTreeNode pop = stack2.pop();
            System.out.print(pop.getData() + "\t");
        }
    }

    /**
     * 删除节点递归方式
     * 此方法需要在调用前先判断根节点是不是要删除的数据
     * @param treeNode
     * @param no
     */
    public static void deleteNodeRecursion(BinaryTreeNode treeNode, int no) {
        // 判断左子树是否是要删除的节点
        if (treeNode.getLeft() != null && treeNode.getLeft().getData() == no) {
            treeNode.setLeft(null);
            return;
        }
        // 判断右子树是否是要删除的节点
        if (treeNode.getRight() != null && treeNode.getRight().getData() == no) {
            treeNode.setRight(null);
            return;
        }
        // 左右子树都不是要删除的节点，则向下递归删除
        if (treeNode.getLeft() != null) {
            deleteNodeRecursion(treeNode.getLeft(), no);
        }
        if (treeNode.getRight() != null) {
            deleteNodeRecursion(treeNode.getRight(), no);
        }
    }

    /**
     * 删除节点非递归方式
     * @param treeNode
     * @param no
     */
    public static void deleteNodeNoRecursion(BinaryTreeNode treeNode, int no) {
        // 先判断根节点是不是要删除的对象
        if (treeNode.getData() == no) {
            treeNode = null;
            return;
        }
        // 使用先序遍历的方法进行查找并删除
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            BinaryTreeNode pop = stack.pop();
            // 判断右子树是不是要删除的对象，如果是则删除退出循环，如果不是则先加入到栈
            if (pop.getRight() != null) {
                if (pop.getRight().getData() == no) {
                    pop.setRight(null);
                    break;
                } else {
                    stack.push(pop.getRight());
                }
            }
            // 判断左子树是不是要删除的对象，如果是则删除退出循环，如果不是则先加入到栈
            if (pop.getLeft() != null) {
                if (pop.getLeft().getData() == no) {
                    pop.setLeft(null);
                    break;
                } else {
                    stack.push(pop.getLeft());
                }
            }
        }
    }

    /**
     * 获取二叉树高度,递归方式
     * @param treeNode
     */
    public static int getHeightRecursion(BinaryTreeNode treeNode) {
        if (treeNode == null){
            return 0;
        }
        int leftHeight = getHeightRecursion(treeNode.getLeft());
        int rightHeight = getHeightRecursion(treeNode.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 获取二叉树高度,非递归方式
     * @param treeNode
     * @return
     */
    public static int getHeightNoRecursion(BinaryTreeNode treeNode) {
        int height = 0;
        // 存放节点
        Stack<BinaryTreeNode> nodes = new Stack<>();
        // 存放与nodes中节点对应的是否需要移除标识0:正常 1:需要删除
        Stack<Integer> flags = new Stack<>();
        while (treeNode != null || !nodes.isEmpty()) {
            // 这里向当于先序,找到一个节点子树的全部左节点,入栈
            while (treeNode != null) {
                nodes.push(treeNode);
                flags.push(0);
                treeNode = treeNode.getLeft();
            }
            if (flags.peek() == 1) {
                // 走到这里,说明已经走到一个分支的最底,需要计算这条分支的高度
                // nodes中的节点数就是当前分支的高度
                height = Math.max(height, nodes.size());
                // 最后从nodes栈中移除最上边一个节点
                nodes.pop();
                // 移除待删除标识
                flags.pop();
                // 将treeNode设置为null,读取nodes中的下一个节点
                treeNode = null;
            } else {
                // 走到这里说明已经到了一个分支的最下一个节点,该节点是没有左子树的
                treeNode = nodes.peek();
                // 标识这个节点可以被删除
                flags.pop();
                flags.push(1);
                // 找当前节点的右子树
                treeNode = treeNode.getRight();
            }
        }
        return height;
    }

}
