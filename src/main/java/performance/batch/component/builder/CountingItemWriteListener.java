/*
 * Copyright (c) 2023 Ryo-Kgym.
 */

package performance.batch.component.builder;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountingItemWriteListener<O> implements ItemWriteListener<O> {
    @Override
    public void afterWrite(Chunk<? extends O> items) {
        log.info("実行済: {}", items.size());
    }
}
