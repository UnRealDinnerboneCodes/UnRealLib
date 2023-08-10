package com.unrealdinnerbone.unreallib.either;

import com.unrealdinnerbone.unreallib.StoredValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Either<L,R> {

    public static <L, R> Either<L, R> left(@Nullable L value) {
        return new Either<>(new StoredValue<>(value), null);
    }

    public static <L, R> Either<L, R> right(@Nullable R value) {
        return new Either<>(null, new StoredValue<>(value));
    }

    @Nullable
    protected final StoredValue<L> left;
    @Nullable
    protected final StoredValue<R> right;

    protected Either(@Nullable StoredValue<L> left, @Nullable StoredValue<R> right) {
        if(left == null && right == null) {
            throw new IllegalArgumentException("Left or Right can't be null");
        }
        this.left = left;
        this.right = right;
    }

    public <T> T map(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc) {
        if(left != null) {
            return lFunc.apply(left.value());
        }else if (right != null) {
            return rFunc.apply(right.value());
        }else {
            throw new IllegalStateException("Left and Right are both null");
        }
    }


    public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc) {
        return left != null ? new Either<>(left.map(lFunc), right) : new Either<>(null, right);
    }

    public <T> Either<L, T> mapRight(Function<? super R, ? extends T> rFunc) {
        return right != null ? new Either<>(left, right.map(rFunc)) : new Either<>(left, null);
    }

    public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
        if(left != null) {
            lFunc.accept(left.value());
        }
        if(right != null) {
            rFunc.accept(right.value());
        }
    }

    public Object getValue() {
        return left != null ? left.value() : right != null ? right.value() : null;
    }

}
