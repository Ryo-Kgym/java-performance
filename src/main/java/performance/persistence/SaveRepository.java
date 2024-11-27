package performance.persistence;

import performance.batch.config.job.InstanceComparisonInput;
import performance.gateway.SaveGateway;

import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SaveRepository implements SaveGateway<InstanceComparisonInput> {
    @Override
    public void save(InstanceComparisonInput o) {
        log.info("Saving: " + o.getIndex());
    }
}
