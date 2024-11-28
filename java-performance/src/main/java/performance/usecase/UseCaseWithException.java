package performance.usecase;

import performance.error.JavaPerformanceException;

/**
 * UseCaseを生成するクラスです。
 *
 * @param <I> 入力データの型
 * @param <O> 出力データの型
 */
public interface UseCaseWithException<I, O> {

    /**
     * UseCaseを実行します。
     *
     * @param inputData 入力データ
     * @return 出力データ
     * @throws JavaPerformanceException 例外
     */
    O handle(I inputData) throws JavaPerformanceException;
}
