package magnus.dust.core;

public interface Repository<Aggregate, Key> {
    default Aggregate getById(Key id) {
        return null;
    }
}
