/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Iterator;
public interface Tree<E> {
    public int size();
    public boolean isEmpty();
    public Iterator<E> iterator() throws Exception;
    public Iterable<Position<E>> positions() throws Exception;
    public E replace(Position<E> v, E e)throws Exception;
    public Position<E> root() throws Exception;
    public Position<E> parent(Position<E> v)throws Exception, Exception;
    public Iterable<Position<E>> children(Position<E> v)throws Exception;
    public boolean isInternal(Position<E> v)throws Exception;
    public boolean isExternal(Position<E> v)throws Exception;
    public boolean isRoot(Position<E> v)throws Exception;
}
