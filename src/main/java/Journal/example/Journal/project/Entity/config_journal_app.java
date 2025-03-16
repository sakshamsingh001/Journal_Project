package Journal.example.Journal.project.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class config_journal_app {
    String key;
    String value;

}
