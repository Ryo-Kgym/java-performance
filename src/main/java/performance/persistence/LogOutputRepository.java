package performance.persistence;

import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;
import performance.core.usecase.instanceComparison.InstanceComparisonOutput;
import performance.gateway.SaveGateway;

@Repository
@Slf4j
public class LogOutputRepository implements SaveGateway<InstanceComparisonOutput> {
    @Override
    public void save(InstanceComparisonOutput o) {
        if (o.getIndex() % 1000000 == 0) {
            log.info("Writer index: {}", o.getIndex());
        }
    }
}
