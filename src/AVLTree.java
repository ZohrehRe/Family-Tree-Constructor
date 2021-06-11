/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Comparator;

public class AVLTree<K,V> extends BinarySearchTree<K,V> implements Dictionary<K,V> {
    public AVLTree(Comparator<K> c) throws Exception { super(c); }
    public AVLTree() throws Exception { super(); }
    protected static class AVLNode<K,V> extends BTNode<Entry<K,V>> {
        protected int height;
        AVLNode() {}
        AVLNode(Entry<K,V> element, BTPosition<Entry<K,V>> parent,
                BTPosition<Entry<K,V>> left, BTPosition<Entry<K,V>> right) {
            super(element, parent, left, right);
            height = 0;
            if (left != null)
                height = Math.max(height, 1 + ((AVLNode<K,V>) left).getHeight());
            if (right != null)
                height = Math.max(height, 1 + ((AVLNode<K,V>) right).getHeight());
        }         public void setHeight(int h) { height = h; }
        public int getHeight() { return height; }
    }
    protected BTPosition<Entry<K,V>> createNode(Entry<K,V> element,
                                                BTPosition<Entry<K,V>> parent, BTPosition<Entry<K,V>> left,
                                                BTPosition<Entry<K,V>> right) {
        return new AVLNode<K,V>(element,parent,left,right);
    }
    protected int height(Position<Entry<K,V>> p)  {
        return ((AVLNode<K,V>) p).getHeight();
    }
    protected void setHeight(Position<Entry<K,V>> p) throws Exception {
        ((AVLNode<K,V>) p).setHeight(1+Math.max(height(left(p)), height(right(p))));
    }
    protected boolean isBalanced(Position<Entry<K,V>> p) throws Exception {
        int bf = height(left(p)) - height(right(p));
        return ((-1 <= bf) &&  (bf <= 1));
    }
    protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) throws Exception {
        if (height(left(p)) > height(right(p))) return left(p);
        else if (height(left(p)) < height(right(p))) return right(p);
        if (isRoot(p)) return left(p);
        if (p == left(parent(p))) return left(p);
        else return right(p);
    }
    protected void rebalance(Position<Entry<K,V>> zPos) throws Exception {
        if(isInternal(zPos))
            setHeight(zPos);
        while (!isRoot(zPos)) {
            zPos = parent(zPos);
            setHeight(zPos);
            if (!isBalanced(zPos)) {
                Position<Entry<K,V>> xPos =  tallerChild(tallerChild(zPos));
                zPos = restructure(xPos);
                setHeight(left(zPos));
                setHeight(right(zPos));
                setHeight(zPos);
            }
        }
    }
    public Entry<K,V> insert(K k, V v) throws Exception {
        Entry<K,V> toReturn = super.insert(k, v);
        rebalance(actionPos);
        return toReturn;
    }
    public Entry<K,V> remove(Entry<K,V> ent) throws Exception {
        Entry<K,V> toReturn = super.remove(ent);
        if (toReturn != null)
            rebalance(actionPos);
        return toReturn;
    }
}
