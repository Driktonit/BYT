package OBJECT_POOL;

public class Main {
    public static void main(String[] args) {
        //imaginary situation:
        // there are 2 coffee machines in the office kitchen
        //and third one is usually off, but can be turned on on request
        Pool<CoffeeMachine> office_kitchen = new Pool<>(2,3, CoffeeMachine::new);
        CoffeeMachine cm1 = null;
        CoffeeMachine cm2 = null;
        CoffeeMachine cm3 = null;
        CoffeeMachine cm4 = null;
        //person 1 walks in and uses machine
        try {
            cm1 = office_kitchen.getReusable();
            System.out.println("Used: "+office_kitchen.getTotalUsed());
            System.out.println("Total: "+office_kitchen.getTotal());
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
        //person 2 walks in and uses machine
        try {
            cm2 = office_kitchen.getReusable();
            System.out.println("Used: "+office_kitchen.getTotalUsed());
            System.out.println("Total: "+office_kitchen.getTotal());
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
        //person 3 walks in and uses machine
        //a new coffee machine has to be turned on
        try {
            cm3 = office_kitchen.getReusable();
            System.out.println("Used: "+office_kitchen.getTotalUsed());
            System.out.println("Total: "+office_kitchen.getTotal());
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
        //person 4 walks in and cant find a machine to use, has to wait
        //gets exception here
        try {
            cm4 = office_kitchen.getReusable();
            System.out.println("Used: "+office_kitchen.getTotalUsed());
            System.out.println("Total: "+office_kitchen.getTotal());
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
        office_kitchen.returnReusable(cm1);

        //person 4 is able to get a machine now
        try {
            cm4 = office_kitchen.getReusable();
            System.out.println("Used: "+office_kitchen.getTotalUsed());
            System.out.println("Total: "+office_kitchen.getTotal());
        } catch (NotAvailableException e) {
            e.printStackTrace();
        }
    }
}
