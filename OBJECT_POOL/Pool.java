package OBJECT_POOL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Supplier;

public class Pool<T> {
    private Supplier<T> supp;
    private LinkedList<T> currently_used = new LinkedList<>();
    private LinkedList<T> reusable_pool;
    private int maxSize;
    public Pool(int startSize, int maxSize, Supplier<T> supp){
        this.supp = supp;
        this.reusable_pool = new LinkedList<T>();
        for (int i = 0; i < startSize; i++) {
            this.reusable_pool.add(this.supp.get());
        }
        this.maxSize = maxSize;
    }
    public T getReusable() throws NotAvailableException {
        if (reusable_pool.size() == 0) {
            if (getTotal() < maxSize) {
                T obj = this.supp.get();
                currently_used.add(obj);
                System.out.println("New object has been created for the pool");
                return obj;
            }else{
                throw new NotAvailableException("Please wait until some objects are available");
            }
        }else{
            T obj = this.reusable_pool.pop();
            currently_used.add(obj);
            System.out.println("Obtained new object from existing pool");
            return obj;
        }
    }

    public void returnReusable(T obj){
        if (currently_used.contains(obj) && !reusable_pool.contains(obj)){
            currently_used.remove(obj);
            reusable_pool.add(obj);
            System.out.println("Returned used object to existing pool");
        }
    }
    public int getTotalUsed(){
        return currently_used.size();
    }

    public int getTotal(){
        return currently_used.size()+reusable_pool.size();
    }
}
