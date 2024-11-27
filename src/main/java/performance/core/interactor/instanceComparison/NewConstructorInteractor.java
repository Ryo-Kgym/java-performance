package performance.core.interactor.instanceComparison;

import lombok.extern.slf4j.Slf4j;
import performance.core.usecase.instanceComparison.InstanceComparisonInput;
import performance.core.usecase.instanceComparison.InstanceComparisonOutput;
import performance.core.domain.performance.Result;
import performance.core.usecase.instanceComparison.NewConstructorUsecase;

@Slf4j
public class NewConstructorInteractor implements NewConstructorUsecase {
    @Override
    public InstanceComparisonOutput handle(InstanceComparisonInput inputData) {

        var ignore = InstanceComparisonInput.builder().build();

        return InstanceComparisonOutput.builder()
            .index(inputData.getIndex())
            .result(Result.OK)
            .build();
    }
}
