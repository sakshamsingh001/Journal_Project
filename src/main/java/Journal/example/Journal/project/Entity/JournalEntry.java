package Journal.example.Journal.project.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "JournalEntry")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;



    private String content;

    private String title;
}
