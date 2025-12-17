class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    // @Override
    // protected Object clone() throws CloneNotSupportedException {
    //     return super.clone();
    // }

    // @Override
    // public String toString() {
    //     return "Person{name='" + name + "'}";
    // }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    // @Override
    // protected void finalize() throws Throwable {
    //     System.out.println(name + "is collected");
    // }
}

class Shared {
    void doWaitNotify() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting");
                wait();
                System.out.println(Thread.currentThread().getName() + " resumed");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void doNotify() {
        notify();
    }
}

class Main {
    public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
        // Person p1 = new Person("Alice");
        // Person p2 = (Person) p1.clone();
        // System.out.println(p1);
        // System.out.println(p2);
        // System.out.println(p1 == p2);

        // Person p1 = new Person("Alice");
        // Person p2 = new Person("Alice");
        // System.out.println(p1.equals(p2));

        // Person p = new Person("Alice");
        // System.out.println(p.getClass());

        // Person p1 = new Person("Alice");
        // Person p2 = new Person("Alice");
        // System.out.println(p1.hashCode()); 
        // System.out.println(p2.hashCode());

        Shared shared = new Shared();

        Thread t1 = new Thread(shared::doWaitNotify, "Thread-1");
        t1.start();

        Thread.sleep(1000);
        synchronized (shared) {
            shared.doNotify();
        }
    }    
}
