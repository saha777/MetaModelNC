package dao.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import entities.mappers.ObjectMapper;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import metamodel.mapper.ParamsMapperService;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Cache<T> {
    private LoadingCache<Integer, T> cache;

    private Objects object;
    private ObjectMapper<T> mapper;

    public Cache(ObjectMapper<T> objectMapper,
                 ParamsMapperService paramsMapperService) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100)                                       // maximum 100 records can be cached
                .expireAfterAccess(5, TimeUnit.MINUTES)        // cache will expire after 5 minutes of access
                .build(new CacheLoader<Integer, T>() {                  // build the cacheloader
                    @Override
                    public T load(Integer tempId) throws Exception {
                        Map<String, Params> paramsMap = paramsMapperService.getParamsMap(tempId);
                        return objectMapper.mapObject(object, paramsMap);
                    }
                });
    }

    public T getByObject(Objects object) {
        try {
            this.object = object;
            return cache.get(object.getObjectId());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void put(Integer tempId, T temp) {
        cache.put(tempId, temp);
    }

    public void delete(Integer tempId) {
        cache.invalidate(tempId);
    }
}
