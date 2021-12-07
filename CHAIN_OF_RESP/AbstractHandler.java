package CHAIN_OF_RESP;

public abstract class AbstractHandler implements ImageHandler{
    public AbstractHandler next_in_chain;
    private EditType type;

    public AbstractHandler(EditType type){
        this.type = type;
    }
    @Override
    public void set_next(AbstractHandler handler) {
        this.next_in_chain = handler;
    }

    public void handle(EditRequest r){
        if (r.type==type) {
            this.apply(r);
        } else {
            if (next_in_chain != null) {
                next_in_chain.handle(r);
            }
        }
    }
    @Override
    public abstract void apply(EditRequest r);
}
