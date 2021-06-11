/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Iterator;
public class NodePositionList<E> implements PositionList<E> {
    protected int numElts;
    protected DNode<E> header, trailer;
    public NodePositionList() {
        numElts = 0;
        header = new DNode<E>(null, null, null);
        trailer = new DNode<E>(header, null, null);
        header.setNext(trailer);
    }
    protected DNode<E> checkPosition(Position<E> p)
            throws Exception {
        if (p == null)
            throw new Exception
                    ("Null position passed to NodeList");
        if (p == header)
            throw new Exception
                    ("The header node is not a valid position");
        if (p == trailer)
            throw new Exception
                    ("The trailer node is not a valid position");
        try {
            DNode<E> temp = (DNode<E>) p;
            if ((temp.getPrev() == null) || (temp.getNext() == null))
                throw new Exception
                        ("Position does not belong to a valid NodeList");
            return temp;
        } catch (ClassCastException e) {
            throw new Exception
                    ("Position is of wrong type for this list");
        }
    }
    public int size() { return numElts; }
    public boolean isEmpty() { return (numElts == 0); }
    public Position<E> first()
            throws Exception {
        if (isEmpty())
            throw new Exception("List is empty");
        return header.getNext();
    }
    public Position<E> last()
            throws Exception {
        if (isEmpty())
            throw new Exception("List is empty");
        return trailer.getPrev();
    }
    public Position<E> prev(Position<E> p)
            throws Exception {
        DNode<E> v = checkPosition(p);
        DNode<E> prev = v.getPrev();
        if (prev == header)
            throw new Exception
                    ("Cannot advance past the beginning of the list");
        return prev;
    }
    public Position<E> next(Position<E> p)
            throws Exception {
        DNode<E> v = checkPosition(p);
        DNode<E> next = v.getNext();
        if (next == trailer)
            throw new Exception
                    ("Cannot advance past the end of the list");
        return next;
    }
    public void addBefore(Position<E> p, E element)
            throws Exception {
        DNode<E> v = checkPosition(p);
        numElts++;
        DNode<E> newNode = new DNode<E>(v.getPrev(), v, element);
        v.getPrev().setNext(newNode);
        v.setPrev(newNode);
    }
    public void addAfter(Position<E> p, E element)
            throws Exception {
        DNode<E> v = checkPosition(p);
        numElts++;
        DNode<E> newNode = new DNode<E>(v, v.getNext(), element);
        v.getNext().setPrev(newNode);
        v.setNext(newNode);
    }
    public void addFirst(E element) {
        numElts++;
        DNode<E> newNode = new DNode<E>(header, header.getNext(), element);
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
    }
    public void addLast(E element) {
        numElts++;
        DNode<E> oldLast = trailer.getPrev();
        DNode<E> newNode = new DNode<E>(oldLast, trailer, element);
        oldLast.setNext(newNode);
        trailer.setPrev(newNode);
    }
    public E remove(Position<E> p)
            throws Exception {
        DNode<E> v = checkPosition(p);
        numElts--;
        DNode<E> vPrev = v.getPrev();
        DNode<E> vNext = v.getNext();
        vPrev.setNext(vNext);
        vNext.setPrev(vPrev);
        E vElem = v.element();
        v.setNext(null);
        v.setPrev(null);
        return vElem;
    }
    public E set(Position<E> p, E element)
            throws Exception {
        DNode<E> v = checkPosition(p);
        E oldElt = v.element();
        v.setElement(element);
        return oldElt;
    }
    public Iterator<E> iterator()  {
        try {
            return new ElementIterator<E>(this);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    public Iterable<Position<E>> positions() throws Exception {
        PositionList<Position<E>> P = new NodePositionList<Position<E>>();
        if (!isEmpty()) {
            Position<E> p = first();
            while (true) {
                P.addLast(p);
                if (p == last())
                    break;
                p = next(p);
            }
        }
        return P;
    }
    public boolean isFirst(Position<E> p)
            throws Exception {
        DNode<E> v = checkPosition(p);
        return v.getPrev() == header;
    }
    public boolean isLast(Position<E> p)
            throws Exception {
        DNode<E> v = checkPosition(p);
        return v.getNext() == trailer;
    }
    public void swapElements(Position<E> a, Position<E> b)
            throws Exception {
        DNode<E> pA = checkPosition(a);
        DNode<E> pB = checkPosition(b);
        E temp = pA.element();
        pA.setElement(pB.element());
        pB.setElement(temp);
    }
    public static <E> String forEachToString(PositionList<E> L) {
        String s = "[";
        int i = L.size();
        for (E elem: L) {
            s += elem;
            i--;
            if (i > 0)
                s += ", ";
        }
        s += "]";
        return s;
    }
    public static <E> String toString(PositionList<E> l) throws Exception {
        Iterator<E> it = l.iterator();
        String s = "[";
        while (it.hasNext()) {
            s += it.next();
            if (it.hasNext())
                s += ", ";
        }
        s += "]";
        return s;
    }
    public String toString() {
        try {
            return toString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

