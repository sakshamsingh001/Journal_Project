package Journal.example.Journal.project.Service;

import Journal.example.Journal.project.Entity.JournalEntry;
import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Repository.journal_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Journal_Service {

@Autowired
    private journal_repo jr;
    @Autowired
    private User_Service us ;

//SAVE ENTRY
public void saveentry(JournalEntry entry)
{
    jr.save(entry);
}
public void saveentry(JournalEntry entry,String username)
{
// 7:34
    User user=us.findbyusername(username);
    List<JournalEntry> entries=new ArrayList<>();
    entries=user.getJournalEntries();
    entries.add(entry);
    user.setJournalEntries(entries);
    jr.save(entry);
    us.saveuser(user);
}

//DELETE ENTRY
    public void deleteentry(String id)
    {
        jr.deleteById(id);
    }
//  FIND ENTRY BY ID
    public Optional<JournalEntry> findentry(String id)
    {
        // Step 1: Find the user (adjust this based on how you query users)
        Optional<User> optionalUser = us.finduser(id); // Replace "userId" with actual user lookup logic

        // Step 2: If user exists, search their entries for the specific entryId
        return optionalUser.flatMap(user ->
                user.getJournalEntries().stream()
                        .filter(entry -> entry.getId().equals(id)) // Match entry ID
                        .findFirst()
        );
    }

//  FIND ENTRY BY CONTENT
//  FIND ENTRY BY TITLE
//  FIND ALL THE ENTRIES
    public List<JournalEntry> findAll()
    {
        return jr.findAll();
    }
    public List<JournalEntry> findbyUsername(String username)
    {
        User user=us.findbyusername(username);
        return user.getJournalEntries();
    }






}
