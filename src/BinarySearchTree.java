/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Comparator;
import java.util.Iterator;
public class BinarySearchTree<K,V>
        extends LinkedBinaryTree<Entry<K,V>> implements Dictionary<K,V> {
    protected Comparator<K> C;
    protected Position<Entry<K,V>>
            actionPos;
    protected int numEntries = 0;
    public BinarySearchTree() throws Exception {
        C = new DefaultComparator<K>();
        addRoot(null);
    }
    public BinarySearchTree(Comparator<K> c) throws Exception {
        C = c;
        addRoot(null);
    }
    protected static class BSTEntry<K,V> implements Entry<K,V> {
        protected K key;
        protected V value;
        protected Position<Entry<K,V>> pos;
        BSTEntry() { }
        BSTEntry(K k, V v, Position<Entry<K,V>> p) {
            key = k; value = v; pos = p;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public Position<Entry<K,V>> position() { return pos; }
    }

    protected K key(Position<Entry<K,V>> position) throws Exception {
        return position.element().getKey();
    }
    protected V value(Position<Entry<K,V>> position) throws Exception {
        return position.element().getValue();
    }
    protected Entry<K,V> entry(Position<Entry<K,V>> position) throws Exception {
        return position.element();
    }
    protected void replaceEntry(Position <Entry<K,V>> pos, Entry<K,V> ent) throws Exception {
        ((BSTEntry<K,V>) ent).pos = pos;
        replace(pos, ent);
    }
    protected void checkKey(K key) throws Exception {
        if(key == null)
            throw new Exception("null key");
    }
    protected void checkEntry(Entry<K,V> ent) throws Exception {
        if(ent == null || !(ent instanceof BSTEntry))
            throw new Exception("invalid entry");
    }
    protected Entry<K,V> insertAtExternal(Position<Entry<K,V>> v, Entry<K,V> e) throws Exception {
        expandExternal(v,null,null);
        replace(v, e);
        numEntries++;
        return e;
    }
    protected void removeExternal(Position<Entry<K,V>> v) throws Exception {
        removeAboveExternal(v);
        numEntries--;
    }
    protected Position<Entry<K,V>> treeSearch(K key, Position<Entry<K,V>> pos) throws Exception {
        if (isExternal(pos)) return pos;
        else {
            K curKey = key(pos);
            int comp = C.compare(key, curKey);
            if (comp < 0)
                return treeSearch(key, left(pos));
            else if (comp > 0)
                return treeSearch(key, right(pos));
            return pos;
        }
    }
     protected void addAll(PositionList<Entry<K,V>> L,
                          Position<Entry<K,V>> v, K k) throws Exception {
        if (isExternal(v)) return;
        Position<Entry<K,V>> pos = treeSearch(k, v);
        if (!isExternal(pos))  {
            addAll(L, left(pos), k);
            L.addLast(pos.element());
            addAll(L, right(pos), k);
        }
    }
    public int size() { return numEntries; }
    public boolean isEmpty() { return size() == 0; }
    public Entry<K,V> find(K key) throws Exception {
        checkKey(key);
        Position<Entry<K,V>> curPos = treeSearch(key, root());
        actionPos = curPos;
        if (isInternal(curPos)) return entry(curPos);
        return null;
    }
    public Iterable<Entry<K,V>> findAll(K key) throws Exception {
        checkKey(key);
        PositionList<Entry<K,V>> L = new NodePositionList<Entry<K,V>>();
        addAll(L, root(), key);
        return L;
    }
    public Entry<K,V> insert(K k, V x) throws Exception {
        checkKey(k);
        Position<Entry<K,V>> insPos = treeSearch(k, root());
        while (!isExternal(insPos))
            insPos = treeSearch(k, left(insPos));
        actionPos = insPos;
        return insertAtExternal(insPos, new BSTEntry<K,V>(k, x, insPos));
    }

    public Entry<K,V> remove(Entry<K,V> ent) throws Exception {
        checkEntry(ent);
        Position<Entry<K,V>> remPos = ((BSTEntry<K,V>) ent).position();
        Entry<K,V> toReturn = entry(remPos);
        if (isExternal(left(remPos))) remPos = left(remPos);
        else if (isExternal(right(remPos))) remPos = right(remPos);
        else {
            Position<Entry<K,V>> swapPos = remPos;
            remPos = right(swapPos);
            do
                remPos = left(remPos);
            while (isInternal(remPos));
            replaceEntry(swapPos, (Entry<K,V>) parent(remPos).element());
        }
        actionPos = sibling(remPos);
        removeExternal(remPos);
        return toReturn;
    }
    public Iterable<Entry<K,V>> entries() throws Exception {
        PositionList<Entry<K,V>> entries = new NodePositionList<Entry<K,V>>();
        Iterable<Position<Entry<K,V>>> positer = positions();
        for (Position<Entry<K,V>> cur: positer)
            if (isInternal(cur))
                entries.addLast(cur.element());
        return entries;
    }

    protected Position<Entry<K,V>> restructure(Position<Entry<K,V>> x) throws Exception {
        BTPosition<Entry<K,V>> a, b, c, t1, t2, t3, t4;
        Position<Entry<K,V>> y = parent(x);
        Position<Entry<K,V>> z = parent(y);
        boolean xLeft = (x == left(y));
        boolean yLeft = (y == left(z));
        BTPosition<Entry<K,V>> xx = (BTPosition<Entry<K,V>>)x,
                yy = (BTPosition<Entry<K,V>>)y, zz = (BTPosition<Entry<K,V>>)z;
        if (xLeft && yLeft) {
            a = xx; b = yy; c = zz;
            t1 = a.getLeft(); t2 = a.getRight(); t3 = b.getRight(); t4 = c.getRight();
        }
        else if (!xLeft && yLeft) {
            a = yy; b = xx; c = zz;
            t1 = a.getLeft(); t2 = b.getLeft(); t3 = b.getRight(); t4 = c.getRight();
        }
        else if (xLeft && !yLeft) {
            a = zz; b = xx; c = yy;
            t1 = a.getLeft(); t2 = b.getLeft(); t3 = b.getRight(); t4 = c.getRight();
        }
        else {
            a = zz; b = yy; c = xx;
            t1 = a.getLeft(); t2 = b.getLeft(); t3 = c.getLeft(); t4 = c.getRight();
        }
        if (isRoot(z)) {
            root = b;
            b.setParent(null);
        }
        else {
            BTPosition<Entry<K,V>> zParent = (BTPosition<Entry<K,V>>)parent(z);
            if (z == left(zParent)) {
                b.setParent(zParent);
                zParent.setLeft(b);
            }
            else {
                b.setParent(zParent);
                zParent.setRight(b);
            }
        }
        b.setLeft(a);
        a.setParent(b);
        b.setRight(c);
        c.setParent(b);
        a.setLeft(t1);
        t1.setParent(a);
        a.setRight(t2);
        t2.setParent(a);
        c.setLeft(t3);
        t3.setParent(c);
        c.setRight(t4);
        t4.setParent(c);
        ((BSTEntry<K,V>) a.element()).pos = a;
        ((BSTEntry<K,V>) b.element()).pos = b;
        ((BSTEntry<K,V>) c.element()).pos = c;
        return b;
    }
}

