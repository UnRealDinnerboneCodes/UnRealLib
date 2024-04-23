package json;

import com.unrealdinnerbone.unreallib.json.api.IID;

public enum IDTestEnum implements IID {

    ONE(1),
    TWO(2),
    THREE(3);


    private final int id;
    IDTestEnum(int i) {
        this.id = i;
    }

    @Override
    public int getId() {
        return id;
    }
}
