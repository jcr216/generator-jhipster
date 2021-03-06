package <%=packageName%>.web.rest.dto;
<% if (socialAuth == 'yes') { %>
import com.mycompany.myapp.domain.ExternalAccount;
<% } %>
import javax.validation.constraints.Pattern;<% if (socialAuth == 'yes') { %>
import java.util.Collections;
import java.util.HashSet;<% } %>
import java.util.List;<% if (socialAuth == 'yes') { %>
import java.util.Set;<% } %>

public class UserDTO {

    @Pattern(regexp = "^[a-z0-9]*$")
    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String langKey;

    private List<String> roles;<% if (socialAuth == 'yes') { %>

    private Set<ExternalAccount> externalAccounts = new HashSet<>();
<% } %>
    public UserDTO() {
    }

    public UserDTO(String login, String password, String firstName, String lastName, String email, String langKey,
                   List<String> roles) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
        this.roles = roles;
    }<% if (socialAuth == 'yes') { %>

    public UserDTO(String login, String password, String firstName, String lastName, String email, String langKey,
                   List<String> roles, Set<ExternalAccount> externalAccounts) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.langKey = langKey;
        this.roles = roles;
        this.externalAccounts = externalAccounts;
    }

    public UserDTO(String firstName, String lastName, String email, ExternalAccount externalAccount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.externalAccounts.add(externalAccount);
    }
    <% } %>

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLangKey() {
        return langKey;
    }

    public List<String> getRoles() {
        return roles;
    }<% if (socialAuth == 'yes') { %>

    public Set<ExternalAccount> getExternalAccounts() {
        return Collections.unmodifiableSet(externalAccounts);
    }
    <% } %>
    @Override
    public String toString() {
        return "UserDTO{" +
        "login='" + login + '\'' +
        ", password='" + password + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", langKey='" + langKey + '\'' +
        ", roles=" + roles +<% if (socialAuth == 'yes') { %>
        ", externalAccounts=" + externalAccounts + <% } %>
        '}';
    }
}
