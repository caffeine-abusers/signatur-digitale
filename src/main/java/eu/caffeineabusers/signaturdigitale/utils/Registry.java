package eu.caffeineabusers.signaturdigitale.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is a wrapper for {@link Map}. It contains some general methods,
 * that registries always have.
 *
 * @param <K> Type of the key.
 * @param <V> Type of the value.
 * @author Tomáš Plánský
 */
public abstract class Registry<K, V> {

    private final Map<K, V> map;

    /**
     * Creates a new instance of {@link Registry}.
     */
    public Registry() {
        this.map = new ConcurrentHashMap<>();
    }

    public void register(@NotNull K key, @NotNull V value) {
        this.map.put(key, value);
    }

    public V get(@NotNull K key) {
        return this.map.get(key);
    }

    public V remove(@NotNull K key) {
        return this.map.remove(key);
    }

    public boolean contains(@NotNull K key) {
        return this.map.containsKey(key);
    }

}
