package Journal.example.Journal.project.Repository;

import Journal.example.Journal.project.Entity.JournalEntry;
import Journal.example.Journal.project.Entity.config_journal_app;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ConfigureJournalAppRepository extends MongoRepository<config_journal_app, String> {
}
