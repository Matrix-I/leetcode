import java.util.List;

public class LeetNode {
    public int val;
    public List<LeetNode> children;

    public LeetNode() {}

    public LeetNode(int _val) {
        val = _val;
    }

    public LeetNode(int _val, List<LeetNode> _children) {
        val = _val;
        children = _children;
    }
}
