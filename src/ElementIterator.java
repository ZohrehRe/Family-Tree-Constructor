/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;
public class ElementIterator<E> implements Iterator<E> {
    protected PositionList<E> list;
    protected Position<E> cursor;
    public ElementIterator(PositionList<E> L) throws Exception {
        list = L;
        cursor = (list.isEmpty())? null : list.first();
    }
    public boolean hasNext() { return (cursor != null);  }
    public E next() {
        if (cursor == null)
            throw new NoSuchElementException("No next element");
        E toReturn = null;
        try {
            toReturn = cursor.element();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cursor = (cursor == list.last())? null : list.next(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toReturn;
    }
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("remove");
    }
}
