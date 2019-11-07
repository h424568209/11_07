import java.util.*;

public class LeeCode {
 
    public int[][] kClosests(int[][] points, int K) {
        PriorityQueue<int []> queue= new PriorityQueue<int []>(
                (o1, o2) -> {
                   return -(int)(Math.pow(o1[0],2)+Math.pow(o1[1],2) - Math.pow(o2[0],2)+Math.pow(o2[1],2));
                });
        for(int i = 0 ; i <K; i++){
            queue.offer(points[i]);
        }
        for(int i = K ; i<points.length ; i++){
            int[]p = queue.peek();
            int[] q = points[i];
            assert p != null;
            if(p[0]*p[0]+p[1]*p[1]>q[0]*q[0]+q[1]*q[1]){
                queue.poll();
                queue.offer(q);
            }
        }
        int [][]ans = new int[K][2];
        Iterator it = queue.iterator();
        int i =0 ;
        while(it.hasNext()){
            ans[i++] = (int[]) it.next();
        }
        return ans;
    }
    /**
     *  最靠近原点的K个结点
     *  首先进行排序  将第K大的数保存起来 然后遍历二维数组进行查找，如果符合条件 就将这个节点放到新的数组中
     * @param points 结点的集合
     * @param K 需要返回的个数
     * @return 二维数组保存最靠近K的个数
     */
    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int []dists = new int[N];
        //找出第K大的值
        for(int i =0 ; i <N;i++){
            dists[i] = dist(points[i]);
        }
        Arrays.sort(dists);
        //获得临界的值 只要比这个值小就是要找的
        //排序后的第K大的值
        int distK = dists[K-1];
        //遍历数组，找到小于等于第K大的值
        int [][]res = new int[K][2];
        int t = 0;
        for(int i = 0 ; i < N ; i++){
            if(distK >= dist(points[i])){
                res[t++] = points[i];
            }
        }
        return res;
    }

    private int dist(int[] point) {
        return point[0]*point[0]+point[1]*point[1];
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 构建不同的二叉搜索树
     * @param n 节点个数
     * @return  不同二叉搜索树的集合
     */

    public List<TreeNode> generateTrees(int n) {
        if(n == 0 ){
            return new ArrayList<>(null);
        }
        return generate_tree(1,n);
    }

    private LinkedList<TreeNode> generate_tree(int start, int end) {
        LinkedList<TreeNode> list = new LinkedList<>();
        if(start>end){
            list.add(null);
            return list;
        }
        for(int i = start ; i <= end ; i++){
            //当i为根的时候 构建他的左孩子
            LinkedList<TreeNode> left = generate_tree(start,i-1);
            //当i为根的时候，构建他的右孩子
            LinkedList<TreeNode> right = generate_tree(i+1,end);
            for(TreeNode t1 :left){
                for(TreeNode t2 :right){
                    //将左子树和右子树连接到以i为根的树上
                    TreeNode root = new TreeNode(i);
                    root.left = t1;
                    root.right = t2;
                    list.add(root);
                }
            }
        }
        return list;
    }

    /**
     * 不同的二叉搜索数
     * 由于只含一个结点和没有结点时候的二叉树只有一种，所以给他直接赋值
     * 假设n个结点存在二叉排序树的个数是G（n），令f(i)为以i为根的二叉搜索树的个数
     * 则G(n) = f(1)+f(2)+...+f(n);
     * 当以i为结点的时候  左子树结点个数为i-1 ,右子树结点个数为n-i
     * f(i) = G(i-1) * G(n-i)
     * 综合得到
     * G(n) = G(0)*G(n-1)+...+G(n-1)*G(0);
     * @param n 结点个数
     * @return 不同的二叉搜索树的个数
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];

    }

    public static void main(String[] args) {
        LeeCode l = new LeeCode();
      //  System.out.println(l.numTrees(5));
        int[][]num={{1,2},{3,4}};
        System.out.println(Arrays.deepToString(l.kClosests(num, 1)));
    }
}
