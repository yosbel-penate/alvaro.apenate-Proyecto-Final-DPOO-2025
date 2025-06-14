package pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParentSingleton {
    protected static Map<Class<? extends ParentSingleton>, ParentSingleton> instances =
            new ConcurrentHashMap<>();

    protected ParentSingleton() {
        synchronized (ParentSingleton.class) {
            if (instances.containsKey(this.getClass())) {
                throw new IllegalStateException("Singleton ya instanciado");
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends ParentSingleton> T getInstance(Class<T> clazz) {
        synchronized (ParentSingleton.class) {
            if (!instances.containsKey(clazz)) {
                try {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    instances.put(clazz, instance);
                } catch (Exception e) {
                    throw new RuntimeException("Error al crear instancia singleton", e);
                }
            }
            return (T) instances.get(clazz);
        }
    }
}