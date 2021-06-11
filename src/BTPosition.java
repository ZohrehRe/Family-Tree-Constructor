/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BTPosition<E> extends Position<E> { 	// inherits element()
    public void setElement(E o);
    public BTPosition<E> getLeft();
    public void setLeft(BTPosition<E> v);
    public BTPosition<E> getRight();
    public void setRight(BTPosition<E> v);
    public BTPosition<E> getParent();
    public void setParent(BTPosition<E> v);
}

