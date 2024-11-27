/*
 * Copyright (c) 2023 Ryo-Kgym.
 */

package java.performance.batch.component.factory;

import java.performance.gateway.SaveGateway;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;


/**
 * ItemWriterを生成するクラスです。
 *
 * @param <O> 出力データの型
 */
public class ItemWriterBuilder<O> implements ItemWriter<O> {
    private SaveGateway<O> saveGateway;

    @Override
    public void write(Chunk<? extends O> chunk) throws Exception {
        chunk.forEach(saveGateway::save);
    }

    public ItemWriterBuilder<O> writer(SaveGateway<O> repository) {
        this.saveGateway = repository;
        return this;
    }

    public ItemWriter<O> build() {
        return this;
    }
}
