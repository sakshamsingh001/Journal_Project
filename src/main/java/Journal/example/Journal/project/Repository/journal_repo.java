package Journal.example.Journal.project.Repository;
import Journal.example.Journal.project.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface journal_repo extends MongoRepository<JournalEntry, String>     {
}
