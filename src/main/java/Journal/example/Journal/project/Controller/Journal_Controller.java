package Journal.example.Journal.project.Controller;

import Journal.example.Journal.project.Entity.JournalEntry;
import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Service.Journal_Service;
import Journal.example.Journal.project.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class Journal_Controller {

    @Autowired
    Journal_Service js ;

    @Autowired
    User_Service userService;

    @GetMapping()
    public ResponseEntity<?> findall()
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userService.findbyusername(username);
        List<JournalEntry> all=user.getJournalEntries();
        if(all.size()>0)
        {

            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<JournalEntry> Save(@RequestBody JournalEntry myentry)
    {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            js.saveentry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/id/{myid}")
    public ResponseEntity<?> find(@PathVariable String myid)
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username=auth.getName();
        User user=userService.findbyusername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty())
        {
            return new ResponseEntity<>(collect,HttpStatus.OK);

//            Optional<JournalEntry> journalEntry=js.findentry(myid);
//            if(journalEntry.isPresent())
//            {
//                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
//
//            }

        }
return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    here is a bug maybe password may get again encrypted
    @DeleteMapping("/delete/{myid}")
    public ResponseEntity<?> delete(@PathVariable String myid)
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findbyusername(auth.getName());
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {

            js.deleteentry(myid);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myid}")
    public ResponseEntity<?> updatebyId(@RequestBody JournalEntry journalEntry, @PathVariable String myid)
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user=userService.findbyusername(username);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
           JournalEntry old=collect.get(0);
//           JournalEntry newEntry=journalEntry;
           if(old.getId().equals(myid))
           {
               if(journalEntry.getContent()!=null)
               {
                   old.setContent(journalEntry.getContent());
               }
               if(journalEntry.getTitle()!=null)
               {
                   old.setTitle(journalEntry.getTitle());
               }
               if(journalEntry.getId()!=null)
               {
                   old.setId(journalEntry.getId());
               }
               js.saveentry(old);
               return new ResponseEntity<>(old,HttpStatus.OK);
           }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


//        if(js.findentry(myid)!=null)
//        {
//            js.deleteentry(myid);
//            journalEntry.setId(myid);
//            js.saveentry(journalEntry);
//        }
    }






}
