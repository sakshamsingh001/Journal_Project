package Journal.example.Journal.project.Service;

import Journal.example.Journal.project.Entity.User;
import Journal.example.Journal.project.Repository.User_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    User_repo user_repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = user_repo.findByUsername(username);
        if(user != null) {
          return  org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).
                    password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0])).build();

        }
        throw new UsernameNotFoundException("Username not found"+username);

    }
}
