public class Book extends Product {

    protected String title;

    public Book(String title) {
        super();
        this.title = title;
    }

    protected String getTitle() {
        return this.title;
    }
}