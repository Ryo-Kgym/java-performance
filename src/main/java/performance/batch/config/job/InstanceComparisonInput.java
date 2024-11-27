package performance.batch.config.job;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class InstanceComparisonInput {
    long index;
}
