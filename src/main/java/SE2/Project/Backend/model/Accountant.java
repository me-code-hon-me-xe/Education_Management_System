package SE2.Project.Backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accountant")
public class Accountant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountant_id")
    private Long accountantId;

    @OneToOne
    @JoinColumn(name = "userID", referencedColumnName = "userID")
    private User user;

    public Long getAccountantId() {
        return accountantId;
    }

    public void setAccountantId(Long accountantId) {
        this.accountantId = accountantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}