/**
 * Created with IntelliJ IDEA.
 * User: zohre
 * Date: 1/12/15
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Dictionary<K,V> {
    public int size();
    public boolean isEmpty();
    public Entry<K,V> find(K key)throws Exception;
    public Iterable<Entry<K,V>> findAll(K key)throws Exception;
    public Entry<K,V> insert(K key, V value) throws Exception;
    public Entry<K,V> remove(Entry<K,V> e)throws Exception;
    public Iterable<Entry<K,V>> entries() throws Exception;
}

