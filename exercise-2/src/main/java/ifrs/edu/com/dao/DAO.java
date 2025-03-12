package ifrs.edu.com.dao;

import java.util.List;

public interface DAO<M, K> {
    public void insert(M model);

    public void delete(K key);

    public void update(K key, M model);

    public M get(K key);

    public List<M> list(int limit, int offset);
}
