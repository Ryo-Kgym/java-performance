/*
 * Copyright (c) 2023 Ryo-Kgym.
 */

package java.performance.batch.component.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.batch.item.ItemWriter;

public class CompositeItemWriterBuilder<O> {
    private final List<Consumer<O>> suppliers = new ArrayList<>();

    @SafeVarargs
    public final CompositeItemWriterBuilder<O> append(Consumer<O>... suppliers) {
        Collections.addAll(this.suppliers, suppliers);
        return this;
    }

    public ItemWriter<O> build() {
        return chunk -> {
            for (O item : chunk) {
                suppliers.forEach(supplier -> supplier.accept(item));
            }
        };
    }
}
