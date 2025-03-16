package Journal.example.Journal.project.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User {
    @Id
    private String   id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    private List<String> roles =new ArrayList<>();
//    give all the journal entries back that are alligned to the user in journalEntries
    @DBRef
    private List<JournalEntry> journalEntries =new ArrayList<>();
//    @JsonProperty("username")
    public @NonNull String getUserName() {
        return username;
    }

    public void setName(@NonNull String username) {
        this.username = username;
    }


}
