package tools;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Publisher {
    private Set<Subscriber> subscribers = new HashSet();

    public Publisher() {
    }

    public void notifySubscribers() {
        Iterator var1 = this.subscribers.iterator();

        while(var1.hasNext()) {
            Subscriber s = (Subscriber)var1.next();
            s.update();
        }

    }

    public void subscribe(Subscriber s) {
        this.subscribers.add(s);
    }

    public void unSubscribe(Subscriber s) {
        this.subscribers.remove(s);
    }
}

