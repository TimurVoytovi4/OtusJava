package ru.otus.homework5;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.LinkedList;
import java.util.List;

public class ObjectProducer {
    private int counter;
    long collectionTime = 0;

    public ObjectProducer(int counter) {
        this.counter = counter;
    }

    public void run() {
        List<Object> objects = new LinkedList<>();
        for (int i = 0; i < counter; i++) {
            gcReport();
            objects.add(new byte[10 * 1024 * 1024]);
            if (collectionTime <= 120000000)
                if (objects.size() > 30)
                    objects.subList(0, objects.size() / 2).clear();
        }
    }

    public void gcReport() {
        for (GarbageCollectorMXBean bean :
                ManagementFactory.getGarbageCollectorMXBeans()) {
            collectionTime += bean.getCollectionTime();
        }
    }
}
