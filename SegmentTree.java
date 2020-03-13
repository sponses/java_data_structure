public class SegmentTree {
  public static void main(String[] args) {
    int[] test = { 2, 4, 5, 7, 8, 9 };
    SegmentTree aTree = new SegmentTree(test);
    System.out.println(aTree.tree[1]);
    System.out.println(aTree.find(3, 4));
    aTree.update(0, 3);
    System.out.println(aTree.tree[1]);
  }

  int[] tree;

  /**
   * constructor
   * 
   * @param nums
   */
  public SegmentTree(int[] nums) {
    int len = nums.length;
    tree = new int[len * 2];
    buildTree(tree, nums);
  }

  /**
   * build tree
   * 
   * @param tree
   * @param nums
   */
  public void buildTree(int[] tree, int[] nums) {
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      tree[len + i] = nums[i];
    }
    for (int i = len - 1; i > 0; i--) {
      tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
  }

  /**
   * update treeNode
   * 
   * @param index
   * @param val
   */
  public void update(int index, int val) {
    index += tree.length / 2;
    tree[index] = val;
    while (index > 1) {
      int parent = index / 2;
      int other = index % 2 == 1 ? index - 1 : index + 1;
      tree[parent] = tree[index] + tree[other];
      index = parent;
    }
  }

  /**
   * range retrieval
   * 
   * @param i
   * @param j
   * @return
   */
  public int find(int i, int j) {
    int l = tree.length / 2 + i;
    int r = tree.length / 2 + j;
    int sum = 0;
    while (l <= r) {
      if (l % 2 == 1) {
        sum += tree[l];
        l++;
      }
      if (r % 2 == 0) {
        sum += tree[r];
        r--;
      }
      l /= 2;
      r /= 2;
    }
    return sum;
  }
}