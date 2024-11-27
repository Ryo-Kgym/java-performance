package performance.core.usecase.instanceComparison;

import lombok.Builder;
import lombok.Value;
import performance.core.domain.performance.Result;

@Builder
@Value
public class InstanceComparisonOutput {
    long index;
    Result result;
}

