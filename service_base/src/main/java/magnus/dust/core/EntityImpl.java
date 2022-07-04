package magnus.dust.core;

public class EntityImpl<Key> implements Entity {

    private Key id;

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }
}
