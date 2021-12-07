package CHAIN_OF_RESP;

public interface ImageHandler {
    void set_next(AbstractHandler handler);
    void apply(EditRequest r);
}
