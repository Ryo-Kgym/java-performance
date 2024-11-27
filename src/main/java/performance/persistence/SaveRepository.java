package performance.persistence;

import performance.batch.config.job.InstanceComparisonInput;
import performance.gateway.SaveGateway;

import org.springframework.stereotype.Repository;

@Repository
public class SaveRepository implements SaveGateway<InstanceComparisonInput> {
    @Override
    public void save(InstanceComparisonInput o) {

    }
}
