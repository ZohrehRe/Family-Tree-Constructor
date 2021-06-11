/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Iterator;

public interface PositionList<E> extends Iterable<E> {
    public int size();
    public boolean isEmpty();
    public Position<E> first() throws Exception;
    public Position<E> last() throws Exception;
    public Position<E> next(Position<E> p)throws Exception;
    public Position<E> prev(Position<E> p) throws Exception;
    public void addFirst(E e);
    public void addLast(E e);
    public void addAfter(Position<E> p, E e)throws Exception;
    public void addBefore(Position<E> p, E e) throws Exception;
    public E remove(Position<E> p) throws Exception;
    public E set(Position<E> p, E e) throws Exception;
    public Iterable<Position<E>> positions() throws Exception;
    public Iterator<E> iterator();
}
