/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BinaryTree<E> extends Tree<E> {
    public Position<E> left(Position<E> v) throws Exception;
    public Position<E> right(Position<E> v) throws Exception;
    public boolean hasLeft(Position<E> v) throws Exception;
    public boolean hasRight(Position<E> v) throws Exception;
}
