package <%=packageName%>.repository;

<% if (socialAuth == 'yes') { %>
import com.mycompany.myapp.domain.ExternalAccountProvider; <% } %>
import <%=packageName%>.domain.User;

import org.joda.time.DateTime;<% if (databaseType == 'sql') { %>
import org.springframework.data.jpa.repository.JpaRepository;<% if (javaVersion == '8') { %>
import java.util.Optional;<% } %>
import org.springframework.data.jpa.repository.Query;<% } %><% if (databaseType == 'mongodb') { %>
import org.springframework.data.mongodb.repository.MongoRepository;<% } %>

import java.util.List;<% if (javaVersion == '8') { %>
import java.util.Optional;<%}%>

<% if (databaseType == 'sql') { %>/**
 * Spring Data JPA repository for the User entity.
 */<% } %><% if (databaseType == 'mongodb') { %>/**
 * Spring Data MongoDB repository for the User entity.
 */<% } %><% if (javaVersion == '8') { %>
public interface UserRepository extends <% if (databaseType == 'sql') { %>JpaRepository<User, Long><% } %><% if (databaseType == 'mongodb') { %>MongoRepository<User, String><% } %> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);
    void delete(User t);

    <% if (socialAuth == 'yes') { if (databaseType == 'sql') { %>
    @Query("select u from User u inner join u.externalAccounts ea where ea.externalProvider = ?1 and ea.externalId = ?2")<% } else if (databaseType == 'nosql') { %>
    @Query("{externalAccounts: { $in: [ {externalProvider: ?0, externalId: ?1} ]}}")<% } %>
    Optional<User> getUserByExternalAccount(ExternalAccountProvider provider, String externalAccountId);<% } %>

}<% } else { %>
public interface UserRepository extends <% if (databaseType == 'sql') { %>JpaRepository<User, Long><% } %><% if (databaseType == 'mongodb') { %>MongoRepository<User, String><% } %> {

    User findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    User findOneByLogin(String login);

    User findOneByEmail(String email);

    <% if (socialAuth == 'yes') { if (databaseType == 'sql') { %>
    @Query("select u from User u inner join u.externalAccounts ea where ea.externalProvider = ?1 and ea.externalId = ?2")<% } else if (databaseType == 'nosql') { %>
    @Query("{externalAccounts: { $in: [ {externalProvider: ?0, externalId: ?1} ]}}")<% } %>
    User getUserByExternalAccount(ExternalAccountProvider provider, String externalAccountId);<% } %>
}<% } %>
