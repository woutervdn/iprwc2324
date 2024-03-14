package nl.hsleiden.iprwc2324.responses;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.hsleiden.iprwc2324.models.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {

    public UserResponse(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.isAdmin();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }

    private Long id;

    private String username;

    private boolean isAdmin;

    private Date createdAt;

    private Date updatedAt;

}
