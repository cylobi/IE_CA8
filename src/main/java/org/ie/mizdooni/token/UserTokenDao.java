package org.ie.mizdooni.token;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserTokenDao extends JpaRepository<UserToken, Integer> {

    @Query(value = """
            select t from UserToken t inner join UserModel u\s
            on t.user.username = u.username\s
            where u.username = :username and (t.expired = false or t.revoked = false)\s
            """)
    List<UserToken> findAllValidTokenByUser(String username);

    Optional<UserToken> findByToken(String token);

}
