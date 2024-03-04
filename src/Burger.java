public class Burger extends Product {

    protected String type;

    public Burger(String type) {
        super();
        this.type = type;
    }

    protected String getType() {
        return this.type;
    }
}
