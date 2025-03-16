package Journal.example.Journal.project.Cache;

import Journal.example.Journal.project.Entity.config_journal_app;
import Journal.example.Journal.project.Repository.ConfigureJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigureJournalAppRepository configJournalAppRepository;

    public Map<String, String> appcache;

    @PostConstruct
    public void init() {
        appcache = new HashMap<>();
        List<config_journal_app> all = configJournalAppRepository.findAll();
        for (config_journal_app app : all) {
            appcache.put(app.getKey(), app.getValue());
        }
    }


}
